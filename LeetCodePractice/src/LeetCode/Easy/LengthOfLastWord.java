package LeetCode.Easy;

/**
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

If the last word does not exist, return 0.

Note: A word is defined as a character sequence consists of non-space characters only.

For example, 
Given s = "Hello World",
return 5.

 * @author WinnieZhao
 *
 */
public class LengthOfLastWord {

    public int lengthOfLastWord(String s) {
        int length = 0;
        
        if (s == null || s.isEmpty()) {
            return length;
        }

        String[] strArray = s.split(" ");
        int arrayLenght = strArray.length;
        if (arrayLenght <= 0) {
            return length;
        }
        
        return strArray[arrayLenght-1].length();
    }
    
    public static void main(String[] args) {
        LengthOfLastWord solution = new LengthOfLastWord();
        
        System.out.println(solution.lengthOfLastWord("    "));
    }
}
