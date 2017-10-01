package LeetCode.Interview.Amazon.LevelOne;

/**
 * Given two words, find if second word is the round rotation of first word.
 *
 * For example: abc, cab
 * return 1
 * since cab is round rotation of abc
 *
 * Example2: ab, aa
 * return -1
 * since ab is not round rotation for aa

 * Created by WinnieZhao on 9/30/2017.
 */
public class RotateString {
    public static boolean isRotated(String s, String t) {

        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }

        String check = s.concat(s);
        return s.length() == t.length() && check.indexOf(t) >= 0;
    }
}
