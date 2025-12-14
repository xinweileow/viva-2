import java.util.Scanner;

public class Q1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter a number: ");
        int n = input.nextInt();
        input.nextLine(); //leftover newline
        String [] word = new String[n];

        for (int i = 0; i < n; i++) {
            word[i] = input.next();
        }

        int newArraySize = 0;
        for (int i = 0; i < n; i++) {
            boolean isDuplicated = false;
            for (int j = 0; j < i; j++) {
                if (word[i].equalsIgnoreCase(word[j])) {
                    isDuplicated = true;
                    break;
                }
            }

            if (!isDuplicated) {
                newArraySize++;
            }
        }

        String [] newWord = new String[newArraySize];
        // Put unique word into array
        int index = 0;
        for (int i = 0; i < n; i++) {
            boolean isDuplicated = false;

            for (int j = 0; j < i; j++) { // Always start with comparing first word
                if (word[i].equalsIgnoreCase(word[j])) {
                    isDuplicated = true;
                    break;
                }
            }

            if (!isDuplicated) {
                newWord[index] = word[i]; // Replace newWord array with unique word
                index++;
            }
        }

        // Track which words have been assigned to a group
        boolean [] grouped = new boolean[newArraySize];
        // First there is no group
        int groupNum = 0;

        for (int i = 0; i < newArraySize; i++) {
            // Skip if this word is already in a group
            if (grouped[i]) {
                continue;
            }

            String group = ""; // String to store all words in current group
            int groupSize = 0;

            // Find all anagrams of newWord[i] (including itself)
            for (int j = i; j < newArraySize; j++) {
                // Check if word[j] is not grouped AND is anagram of word[i]
                if(!grouped[j] && isAnagram(newWord[i],newWord[j])) {
                    if (groupSize > 0) {
                        group += " ";
                    }

                    // Add the word to the group string
                    group += newWord[j];
                    // Mark current word as grouped
                    grouped[j] = true;
                    groupSize++;
                }
            }

            // Only print if there are 2 or more words in the group
            if(groupSize > 1) {
                groupNum++;
                System.out.println("Anagram group " + groupNum + ": " + group);
            }

            else {
                // If only 1 word, unmark it as grouped
                grouped[i] = false;
            }
        }

        String withoutGroup = "";
        boolean isWithoutGroup = false;
        // Find all words that weren't grouped
        for (int i = 0; i < newArraySize; i++) {
            if (!grouped[i]) {
                if(isWithoutGroup) {
                    withoutGroup += " ";
                }
                withoutGroup += newWord[i];
                isWithoutGroup = true;
            }
        }

        if (isWithoutGroup) {
            System.out.println("Without Anagram group: " + withoutGroup);
        }
    }

    public static int countChar(String word){
        int letterCount = 0;
        for (int i = 0; i < word.length(); i++) {
            letterCount++;
        }
        return letterCount;
    }

    public static boolean isAnagram(String a, String b){
        a = a.toLowerCase();
        b = b.toLowerCase();

        // Declare a index array for 26 alphabets
        int [] countA = new int[26];
        int [] countB = new int[26];

        // Store 1 for alphabet exists, 0 for not exist
        for (int i = 0; i < a.length(); i++) {
            /** If a.charAt(i) is a, means 'a' - 'a' = 0
             * Thus, countA[0];
             * Therefore, at index 0, location of alphabet a,
             * 0++, will return 1.
             */
            countA[a.charAt(i) - 'a']++;
        }

        /** Same logic with countA
         * A seperate loop is build
         * To allow the inclusion of SUBSET
         */
        for (int i = 0; i < b.length(); i++) {
            countB[b.charAt(i) - 'a']++;
        }

        boolean aSubsetOfB = true;
        boolean bSubsetOfA = true;

        for (int i = 0; i < 26; i++) {
            if (countA[i] > countB[i]) { // Test if wordB is the subset of wordA
                bSubsetOfA = false;
            }

            if (countA[i] < countB[i]) { // Test if wordA is the subset of wordB
                aSubsetOfB = false;
            }
        }

        // Return either TRUE, If both FALSE then it will be in withoutGroup
        return bSubsetOfA || aSubsetOfB;
    }
}
