package LeetCode.Hard;

import java.util.Arrays;

/**
 *
 * Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it.
 * Find and return the shortest palindrome you can find by performing this transformation.
 *
 * For example:
 * Given "aacecaaa", return "aaacecaaa".
 * Given "abcd", return "dcbabcd".

 * Created by WinnieZhao on 2017/5/3.
 */
public class ShortestPalindrome {

    public String shortestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        String reverse = (new StringBuilder(s)).reverse().toString();
        String target = s + "*" + reverse;

        int[] table = getTable(target);

        //get the maximum palin part in s starts from 0
        return new StringBuilder(s.substring(table[table.length - 1])).reverse().toString() + s;
    }

    /**
     * KMP Algorithm (https://segmentfault.com/a/1190000003059361)
     */
    private int[] getTable(String s) {
        //get lookup table
        int[] table = new int[s.length()];

        //pointer that points to matched char in prefix part

        int index = 0;

        //skip index 0, we will not match a string with itself
        for(int i = 1; i < s.length(); i++){
            if (s.charAt(index) == s.charAt(i)){
                //we can extend match in prefix and postfix
                table[i] = table[i-1] + 1;
                index ++;
            }
            else {
                //match failed, we try to match a shorter substring

                //by assigning index to table[i-1], we will shorten the match string length, and jump to the
                //prefix part that we used to match postfix ended at i - 1
                index = table[i-1];

                while(index > 0 && s.charAt(index) != s.charAt(i)){
                    //we will try to shorten the match string length until we revert to the beginning of match (index 1)
                    index = table[index-1];
                }

                //when we are here may either found a match char or we reach the boundary and still no luck
                //so we need check char match
                if(s.charAt(index) == s.charAt(i)){
                    //if match, then extend one char
                    index ++ ;
                }

                table[i] = index;
            }

        }

        return table;
    }

    public static void main(String[] args) {
        ShortestPalindrome solution = new ShortestPalindrome();

        int[] table = solution.getTable("aabc*cbaa");
        System.out.println(Arrays.toString(table));
    }
}
