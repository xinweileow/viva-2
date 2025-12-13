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
        int index = 0;
        for (int i = 0; i < n; i++) {
            boolean isDuplicated = false;

            for (int j = 0; j < i; j++) {
                if (word[i].equalsIgnoreCase(word[j])) {
                    isDuplicated = true;
                    break;
                }
            }

            if (!isDuplicated) {
                newWord[index] = word[i];
                index++;
            }
        }

        boolean [] grouped = new boolean[newArraySize];
        int groupNum = 0;

        for (int i = 0; i < newArraySize; i++) {
            if (grouped[i]) {
                continue;
            }

            String group = "";
            int groupSize = 0;

            for (int j = i; j < newArraySize; j++) {
                if(!grouped[j] && isAnagram(newWord[i],newWord[j])) {
                    if (groupSize > 0) {
                        group += " ";
                    }

                    group += newWord[j];
                    grouped[j] = true;
                    groupSize++;
                }
            }

            if(groupSize > 1) {
                groupNum++;
                System.out.println("Anagram group " + groupNum + ": " + group);
            }

            else {
                grouped[i] = false;
            }
        }

        String withoutGroup = "";
        boolean isWithoutGroup = false;
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

        int [] countA = new int[26];
        int [] countB = new int[26];

        for (int i = 0; i < a.length(); i++) {
            countA[a.charAt(i) - 'a']++;
        }
        for (int i = 0; i < b.length(); i++) {
            countB[b.charAt(i) - 'a']++;
        }

        boolean aSubsetOfB = true;
        boolean bSubsetOfA = true;

        for (int i = 0; i < 26; i++) {
            if (countA[i] > countB[i]) {
                bSubsetOfA = false;
            }

            if (countA[i] < countB[i]) {
                aSubsetOfB = false;
            }
        }

        return bSubsetOfA || aSubsetOfB;
    }
}
