package LeetCode.Easy;

/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 * @author WinnieZhao
 *
 */
public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        
        StringBuffer buffer = new StringBuffer();

        int length = strs[0].length();
        int size = strs.length;
        
        for (int i=0; i<length; i++) {
            int compare = 0;
            char ch = strs[0].charAt(i);
            
            for (int index=1; index<size; index++) {
                String str = strs[index];
                compare++;
                if (i > str.length()-1) {
                    return buffer.toString();
                }

                if (ch != str.charAt(i)) {
                    return buffer.toString();
                }
                
                if (ch == str.charAt(i) && compare == size-1) {
                    buffer.append(ch);
                }
                
                ch = str.charAt(i);
            }
        }

        return buffer.toString();
    }
    
    public static void main(String[] args) {
        LongestCommonPrefix solution = new LongestCommonPrefix();
        
        String[] strs = {"aca", "cba"};
        
        System.out.println(solution.longestCommonPrefix(strs));
    }
}
