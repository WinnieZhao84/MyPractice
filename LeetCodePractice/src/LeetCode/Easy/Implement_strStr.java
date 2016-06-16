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
        int firstMatch = -1;
        if (haystack == null || needle == null) {
            return -1;
        }

        if (needle.isEmpty()) {
            return 0;
        }
        
        int needleLength = needle.length();
        int layStackLength = haystack.length();
        
        if (layStackLength < needleLength) {
            return -1;
        }
        
        for (int i=0; i<=layStackLength-needleLength; i++) {
            for (int j=0; j<=needleLength-1; j++) {
                char p = haystack.charAt(i+j);
                char c= needle.charAt(j);
                
                if (c==p) {
                    if (firstMatch == -1) {
                        firstMatch = i;
                    }
                    
                    if (j == needleLength-1) {
                        return firstMatch;
                    }
                }
                else {
                    j=0;
                    firstMatch = -1;
                    break;
                }
            }
        }
        
        return firstMatch;
    }
    
    public static void main(String[] args) {
        Implement_strStr solution = new Implement_strStr();
        
        System.out.println(solution.strStr("mississippi", "issipi"));
    }
}
