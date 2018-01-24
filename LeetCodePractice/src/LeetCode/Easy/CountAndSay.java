package LeetCode.Easy;

import java.util.HashMap;
import java.util.Map;

/**
 * The count-and-say sequence is the sequence of integers beginning as follows:
1, 11, 21, 1211, 111221, ...

1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth sequence.

Note: The sequence of integers will be represented as a string.
 * @author WinnieZhao
 *
 */
public class CountAndSay {

    public String countAndSay(int n) {
        if (n <= 0)
            return null;
     
        String result = "1";
        int i = 1;
     
        while (i < n) {
            StringBuilder sb = new StringBuilder();
            int count = 1;
            for (int j = 1; j < result.length(); j++) {
                if (result.charAt(j) == result.charAt(j - 1)) {
                    count++;
                } else {
                    sb.append(count);
                    sb.append(result.charAt(j - 1));
                    count = 1;
                }
            }
     
            sb.append(count);
            sb.append(result.charAt(result.length() - 1));
            result = sb.toString();
            i++;
        }
     
        return result;
    }

    class Solution {
        Map<Integer, String> map = new HashMap<>();

        public String countAndSay(int n) {
            if (n == 0) {
                return "";
            }

            return this.helper(n);
        }

        private String helper(int n) {
            if (map.containsKey(n)) {
                return map.get(n);
            }

            if (n == 1) {
                map.put(n, "1");
                return "1";
            }

            String s = String.valueOf(this.helper(n-1));

            int count=1;
            StringBuilder sb = new StringBuilder();
            for (int i=1; i<s.length(); i++) {
                if (s.charAt(i) == s.charAt(i-1)) {
                    count++;
                }
                else {
                    sb.append(count);
                    sb.append(s.charAt(i-1));

                    count = 1;
                }
            }

            sb.append(count);
            sb.append(s.charAt(s.length()-1));

            String res = sb.toString();
            map.put(n, res);
            return res;
        }
    }
    
    public static void main(String[] args) {
        CountAndSay solution = new CountAndSay();
        
        System.out.println(solution.countAndSay(11));
    }
}
