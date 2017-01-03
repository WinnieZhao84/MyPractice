package LeetCode.Medium;

/**
 * Given an input string, reverse the string word by word.
 * 
 * For example,
 * Given s = "the sky is blue",
 * return "blue is sky the".
 * 
 * @author WinnieZhao
 *
 */
public class ReverseWordsInAString {

    public String reverseWords(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        
        /**
         *  Using regex:
         *  String[] parts = s.trim().split("\\s+");
         */
        String[] array = s.split(" ");
        
        StringBuilder builder = new StringBuilder();
        for (int i=array.length-1; i>=0; i--) {
            if (array[i].trim().equals("")) {
                continue;
            }
            builder.append(array[i]);
            builder.append(" ");
        }
        
        return builder.toString().trim();
    }
    
    public static void main(String[] args) {
        ReverseWordsInAString solution = new ReverseWordsInAString();
        System.out.println(solution.reverseWords("   a   b "));
    }
}
