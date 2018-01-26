package LeetCode.Easy;

/**
 * Implement strStr().
 *
 * Returns the index of the first occurrence of needle in haystack, 
 * or -1 if needle is not part of haystack.
 * @author WinnieZhao
 *
 */
public class Implement_strStr {

    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null || haystack.length() < needle.length()) {
            return -1;
        }
        if (haystack.isEmpty()) {
            return needle.isEmpty() ? 0 : -1;
        }

        for(int i=0; i<=(haystack.length()-needle.length()); i++) {
            int j = 0;

            while (j<needle.length() && haystack.charAt(i+j)==needle.charAt(j)){
                j++;
            }

            if (needle.length()== j) {
                return i;
            }
        }
        return -1;
    }
    
    public static void main(String[] args) {
        Implement_strStr solution = new Implement_strStr();
        
        System.out.println(solution.strStr("mississippi", "issipi"));
    }
}
