import java.util.Arrays;
import java.util.Scanner;

public class TextAnalyzer {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Read message
        System.out.println("Enter message: ");
        String str = sc.nextLine();

        // Print word count
        System.out.print("Word Count: ");
        System.out.println(wordCount(str));

        // Print character count
        System.out.print("Character Count: ");
        System.out.println(characterCount(str));

        // Print character count without space
        System.out.print("Character Count without Space: ");
        System.out.println(characterCountWithoutSpaces(str));

        // Print sentence count
        System.out.print("Sentence Count: ");
        System.out.println(sentenceCount(str));

        // Print most frequent word
        System.out.print("Most Frequent Word: ");
        System.out.println(mostFrequentWord(str));

        // Print longest word
        System.out.print("Longest Word: ");
        System.out.println(longestWord(str));
    }

    /* Word count method */
    public static int wordCount(String str) {
        int wordCount = 0;
        for (int i = 0; i < str.length(); i++) {
            // If contain space, wordCount + 1
            if (str.charAt(i) == ' ') {
                wordCount++;
            }
        }
        return wordCount + 1;
    }

    /* Character count method */
    public static int characterCount(String str) {
        // Directly return string length
        return str.length();
    }

    /* Character count without spaces method */
    public static int characterCountWithoutSpaces(String str) {
        int chCWSpace =  0;
        for (int i = 0; i < str.length(); i++) {
            // If not spaces, count + 1
            if (str.charAt(i) != ' ') {
                chCWSpace ++;
            }
        }
        return chCWSpace;
    }

    /* Sentence count method */
    public static int sentenceCount(String str) {
        int sentenceCount = 0;
        for (int i = 0; i < str.length(); i++) {
            // If contain . ? !, count + 1
            if (str.charAt(i) == '.' || str.charAt(i) == '?' || str.charAt(i) == '!') {
                sentenceCount++;
            }
        }
        return sentenceCount;
    }

    /* Most frequent word method */
    public static String mostFrequentWord(String str) {
        // Split the word in the string when meet spaces
        String[] splited = str.split(" ");
        // Sort the word so for easier comparing later
        Arrays.sort(splited);
        int max = 1;                        // The highest frequency found
        int count = 1;                      // Occurrences of the current word
        String mostFrequent = splited[0];   // The current most frequent word
        // Loop inside the sorted array to find the most frequent word
        for (int i = 1; i < splited.length; i++) {
            if (splited[i].equals(splited[i-1])) {
                count++;
            }else{
                if (count > max) {
                    max = count;
                    mostFrequent = splited[i - 1];
                }
                count = 1;
            }
        }
        // Final check for the last word in case it is the most frequent
        if (count > max) {
            mostFrequent = splited[splited.length - 1];
        }
        return mostFrequent;
    }

    /* Longest word method */
    public static String longestWord(String str) {
        String[] words = str.split(" ");
        String longestWord = "";
        // Get all word from the array words
        for (String word : words) {
            if (word.length() > longestWord.length()) {
                longestWord = word;
            }
        }
        return longestWord;
    }

}