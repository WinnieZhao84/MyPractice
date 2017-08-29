package LeetCode.Hard;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 248
 *
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.
 *
 * For example,
 * Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.
 *
 * Note: Because the range might be a large number, the low and high numbers are represented as string.

 *
 * Created by WinnieZhao on 2017/7/12.
 */
public class StrobogrammaticNumberIII {

    /**
     * With the solution in version II, we can easily get all the strobogrammatic string that has length of n.
     * We just need to only count the number that meets criteria low <= num <= high.
     * The easiest way is to get all the strobogrammatic strings, that has length of low.length() to high.length().
     * And iterate over every of them and check if they meets the criteria above.
     *
     * The solution below is adding some more if to avoid unnecessary iteration. It doesn't affect the running time in O.
     * Because in order to generate a list of n, it needs O(n), and if we add one more iteration on the list the time stays in O(n).

     */

    public int strobogrammaticInRange(String low, String high) {
        if (low.length() > high.length() || (low.length() == high.length() && low.compareTo(high) == 1)) {
            return 0;
        }
        int n1 = low.length();
        int n2 = high.length();
        int count = 0;
        if (n1 == n2) {
            List<String> tmp = findStrobogrammatic(n1);
            for (String s:tmp) {
                if (s.compareTo(low) >= 0 && s.compareTo(high) <= 0) {
                    count ++;
                }
            }
            return count;
        }
        List<String> tmp = findStrobogrammatic(n1);
        for (String s:tmp) {
            if (s.compareTo(low) >= 0) {
                count ++;
            }
        }
        tmp = findStrobogrammatic(n2);
        for (String s:tmp) {
            if (s.compareTo(high) <= 0) {
                count ++;
            }
        }
        for (int i = n1 + 1; i < n2; i ++) {
            int size = findStrobogrammatic(i).size();
            count += size;
        }
        return count;

    }

    //from solution of version II problem
    private List<String> findStrobogrammatic(int n) {
        Map<Character, Character> map = new HashMap<Character, Character>();
        map.put('1','1');
        map.put('0','0');
        map.put('8','8');
        map.put('6','9');
        map.put('9','6');
        List<String> result = new LinkedList<String>();
        if (n <= 0) {
            return result;
        }

        result.add("1");
        result.add("0");
        result.add("8");
        if (n == 1) {
            return result;
        }
        if (n % 2 != 0) {
            n--;
        }
        else {
            result.clear();
            result.add("");
        }
        while (n > 1) {
            List<String> newresult = new LinkedList<String>();
            for (String s : result) {
                for (Character c : map.keySet()) {
                    if (n != 2 || c != '0') {
                        newresult.add(c + s + map.get(c));
                    }
                }
            }
            result = newresult;
            n = n - 2;
        }
        return result;
    }

    private static final char[][] pairs = {{'0', '0'}, {'1', '1'}, {'6', '9'}, {'8', '8'}, {'9', '6'}};

    public int strobogrammaticInRange_recursive(String low, String high) {
        int[] count = {0};
        for (int len = low.length(); len <= high.length(); len++) {
            char[] c = new char[len];
            dfs(low, high, c, 0, len - 1, count);
        }
        return count[0];
    }

    public void dfs(String low, String high , char[] c, int left, int right, int[] count) {
        if (left > right) {
            String s = new String(c);

            if ((s.length() == low.length() && s.compareTo(low) < 0) || (s.length() == high.length() && s.compareTo(high) > 0)) {
                return;
            }
            count[0]++;
            return;
        }

        for (char[] p : pairs) {
            c[left] = p[0];
            c[right] = p[1];

            if (c.length != 1 && c[0] == '0') {
                continue;
            }
            if (left == right && p[0] != p[1]) {
                continue;
            }

            dfs(low, high, c, left + 1, right - 1, count);
        }
    }

}
