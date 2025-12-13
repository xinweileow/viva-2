## Problem description

To assist the English club with their word games, the teacher requires a program that can automatically identify and group "anagrams" from a list of user inputs.
The goal is to design a Java program that reads a specific number of words, cleans the data by removing duplicates (treating uppercase and lowercase as the same), and then organizes them into groups. In this specific context, words are grouped if they share the same character composition or if one is a subset of the other (e.g., "banana" and "ban").
The program must handle the input without using advanced Java collections (like `ArrayList` or `HashSet`), relying instead on standard arrays and logic to filter, compare, and display the results. Words that do not fit into any group must be listed separately at the end.
---

## Solution Explanation

### 1. Reading the Number of Words
The program first asks the user for the total number of words ($n$).
A `String` array `word[]` is created to store these raw inputs.
A loop runs $n$ times to capture each word.

### 2. Pre-processing: Removing Duplicates
Since advanced collections are not allowed, duplicate removal is handled manually using arrays:
- The program iterates through the raw `word[]` array.
- **For each word**, it checks previous indices to see if the word (ignoring case) already exists.
- **If unique**:
    * A counter `newArraySize` is incremented.
- A second loop creates a clean array `newWord[]` containing only the unique words.

### 3. Grouping Logic
The program uses a boolean array `grouped[]` to keep track of words that have already been assigned to an anagram group.
A nested loop structure is used to find matches:

**a. Outer Loop (The Anchor)**
- Iterates through the `newWord` array.
- If `grouped[i]` is already **TRUE**, it `continue` to skip the word.
- If **FALSE**, this word starts a potential new group.

**b. Inner Loop (The Search)**
- Looks ahead at subsequent words (`j = i`).
- Calls the helper method `isAnagram(newWord[i], newWord[j])`.
- **If match found**:
    * The word is added to the output string `group`.
    * `grouped[j]` is marked as `true`.
    * `groupSize` is incremented.

**c. Displaying Groups**
- If `groupSize > 1`, the program prints the group formatted as `Anagram group X: word1 word2...`.
- If `groupSize == 1` (no matches found), the status `grouped[i]` is reset to `false` so it can be handled by the leftover logic.

### 4. Anagram Verification Logic (`isAnagram`)
The method `boolean isAnagram(String a, String b)` determines if two words are related.
- **Normalization:** Both strings are converted to lowercase.
- **Frequency Analysis:** Two integer arrays `countA[26]` and `countB[26]` are created to store the frequency of each letter ('a' to 'z').
    * `countA[a.charAt(i) - 'a']++;`
- **Subset Checking:**
    * The program checks two conditions to support the specific game rules (where "ban" fits into "banana"):
        1. `aSubsetOfB`: Does word B contain all letters of word A?
        2. `bSubsetOfA`: Does word A contain all letters of word B?
    * `return bSubsetOfA || aSubsetOfB;`

### 5. Handling Leftovers
After identifying all groups, the program performs a final pass through the `newWord` list.
- If `grouped[i]` is **FALSE**, the word is appended to a `withoutGroup` string.
- Finally, if any words remain, they are printed under `Without Anagram group`.

---

## Sample input and output

### Sample 1
![Sample Input Output 1](sample-case/sample_input_output_(1).png)

### Sample 2
![Sample Input Output 2](sample-case/sample_input_output_(2).png)

### Sample 3
![Sample Input Output 3](sample-case/sample_input_output_(3).png)

---

## Source code
See [Q1.java](Q1.java)