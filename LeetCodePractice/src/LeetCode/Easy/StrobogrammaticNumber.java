package LeetCode.Easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 246
 *
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down)
 *
 * Write a function to determine if a number is strobogrammatic. The number is represented as a string.
 * For example, the numbers "69", "88", and "818" are all strobogrammatic.

 * Created by WinnieZhao on 2017/3/24.
 */
public class StrobogrammaticNumber {

    public boolean isStrobogrammatic(String num) {
        Map<Character, Character> map = new HashMap<>();
        map.put('0', '0');
        map.put('1', '1');
        map.put('8', '8');
        map.put('6', '9');
        map.put('9', '6');

        int length = num.length();
        int l = 0;
        int r = length - 1;

        while (l<=r) {
            char left = num.charAt(l);
            char right = num.charAt(r);

            if (map.containsKey(left) && map.containsKey(right) && (char)map.get(left) == right) {
                l++;
                r--;
            }
            else {
                return false;
            }
        }

        return true;
    }
}
