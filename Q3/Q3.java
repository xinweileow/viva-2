import java.util.Scanner;

public class Q3 {
    static char[] mirrorLeft = new char[40];
    static char[] mirrorRight = new char[40];
    static int mirrorCount = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        char[] L = {'A','H','I','M','O','T','U','V','W','X','Y',
                'o','u','v','w','b','d','p','q'};
        char[] R = {'A','H','I','M','O','T','U','V','W','X','Y',
                'o','u','v','w','d','b','q','p'};

        for (int i = 0; i < L.length; i++) {
            mirrorLeft[i] = L[i];
            mirrorRight[i] = R[i];
            mirrorCount++;
        }

        System.out.println("Enter additional pairs: ");
        int additionalPairs = 0;
        for (int i = 0; i < 10; i++) {
            String line = sc.nextLine();
            if (line.length() == 0) break;
            if (line.length() == 2) {
                addMirrorPair(line.charAt(0), line.charAt(1));
                additionalPairs++;
            }
        }
        System.out.println( additionalPairs + " pair(s) entered.");

        System.out.print("Enter word: ");
        String input = sc.nextLine();

        System.out.println("Longest palindrome substring: " + getLongestPalindrome(input));
        System.out.println("Longest mirrorable substring: " + getLongestMirrorWord(input));
    }

    static void addMirrorPair(char left, char right) {
        if (mirrorCount < mirrorLeft.length) {
            mirrorLeft[mirrorCount] = left;
            mirrorRight[mirrorCount] = right;
            mirrorCount++;
        }
    }

    static boolean isMirror(char a, char b) {
        for (int i = 0; i < mirrorCount; i++) {
            if (mirrorLeft[i] == a && mirrorRight[i] == b) return true;
        }
        return false;
    }

//This method checks every possible center, expands outward to find palindromes, and returns the longest palindrome substring.
    static String getLongestPalindrome(String s) {
        int n = s.length();
        int maxLen = 0;
        int start = 0;

        for (int i = 0; i < n; i++) {
            int len1 = expandPalindrome(s, i, i);
            int len2 = expandPalindrome(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > maxLen) {
                maxLen = len;
                start = i - (len - 1) / 2;
            }
        }
        return s.substring(start, start + maxLen);
    }

//This method expands from the center to both sides while characters match and returns the length of the longest palindrome found.
    static int expandPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && Character.toLowerCase(s.charAt(left)) == Character.toLowerCase(s.charAt(right))) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    static String getLongestMirrorWord(String s) {
        int n = s.length();
        int maxLen = 0;
        int start = 0;

        for (int i = 0; i < n; i++) {
            int len1 = expandMirror(s, i, i);
            int len2 = expandMirror(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > maxLen) {
                maxLen = len;
                start = i - (len - 1) / 2;
            }
        }
        return s.substring(start, start + maxLen);
    }

    static int expandMirror(String s, int left, int right) {
        while (left >= 0 && right < s.length() && isMirror(s.charAt(left), s.charAt(right))) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}
