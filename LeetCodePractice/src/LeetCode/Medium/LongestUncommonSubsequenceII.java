package LeetCode.Medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given a list of strings, you need to find the longest uncommon subsequence among them.
 * The longest uncommon subsequence is defined as the longest subsequence of one of these
 * strings and this subsequence should not be any subsequence of the other strings.
 *
 * A subsequence is a sequence that can be derived from one sequence by deleting some characters
 * without changing the order of the remaining elements. Trivially, any string is a subsequence
 * of itself and an empty string is a subsequence of any string.
 *
 * The input will be a list of strings, and the output needs to be the length of the longest uncommon
 * subsequence. If the longest uncommon subsequence doesn't exist, return -1.
 *
 * Example 1:
 * Input: "aba", "cdc", "eae"
 * Output: 3
 *
 * Note:
 * All the given strings' lengths will not exceed 10.
 * The length of the given list will be in the range of [2, 50].
 *
 * Created by WinnieZhao on 2017/4/3.
 */
public class LongestUncommonSubsequenceII {
    public int findLUSlength(String[] strs) {

        Arrays.sort(strs, (a, b) -> b.length() - a.length());
        for (int i=0;i<strs.length;i++){
            //check whether strs[i] is sub sequence of others
            boolean flag = false;
            for(int j=0;j<strs.length;j++){
                if (j!=i && isSub(strs[i], strs[j])){
                    flag = true;
                }
            }
            if(!flag){
                return strs[i].length();
            }
        }
        return -1;
    }

    //check if s1 is substring of s2
    private boolean isSub(String s1, String s2){
        //if len(s1)>len(s2) return false
        if(s1.length()>s2.length()) {
            return false;
        }

        //pos++ when same character
        int j=0;
        for(int i=0;j<s1.length()&&i<s2.length();i++) {
            if(s1.charAt(j)==s2.charAt(i)) {
                j++;
            }
        }

        return j==s1.length();
    }

    public static void main(String[] args) {
        LongestUncommonSubsequenceII solutin = new LongestUncommonSubsequenceII();

        String[] strs = {"aaa","a","aa"};
        System.out.println(solutin.findLUSlength(strs));

        String[] strs1 = {"cdddc","cdc","eae"};
        System.out.println(solutin.findLUSlength(strs1));
    }

}
