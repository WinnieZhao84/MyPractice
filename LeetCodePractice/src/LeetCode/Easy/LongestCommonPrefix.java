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

    public String longestCommonPrefix_better(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        if (strs.length == 1) {
            return strs[0];
        }

        String prefix = strs[0];
        int i=1;
        while (i<strs.length) {
            if (strs[i] == null || strs[i].isEmpty()) {
                return "";
            }
            if (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length()-1);
                i=1;
            }
            else {
                i++;
            }

        }

        return prefix;
    }
    
    public static void main(String[] args) {
        LongestCommonPrefix solution = new LongestCommonPrefix();
        
        String[] strs = {"c","acc","ccc"};
        
        System.out.println(solution.longestCommonPrefix_better(strs));
    }
}
