package LeetCode.Interview.Microsoft.OA;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 求所有回文时间，例如1:21, 0:10, 12:21
 *
 * Created by WinnieZhao on 2/24/2018.
 */
public class GetAllPalindromicTime {

    public List<String> getAllPalindromicTime() {

        List<String> res = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (int i=0; i<=11; i++) {
            for (int j=0; j<=59; j++) {
                String h = String.valueOf(i);
                String m = String.valueOf(j);
                if (m.length() == 1) {
                    m = "0" + m;
                }
                String time = h + m;
                if (this.isPalindromic(time) && set.add(time)) {
                    res.add(h + ":" + m);
                }
            }
        }

        return res;
    }

    private boolean isPalindromic(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }

        char[] chs = s.toCharArray();
        int i=0;
        int j=chs.length-1;

        while (i<j) {
            if (chs[i] != chs[j]) {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }

    public static void main(String[] args) {
        GetAllPalindromicTime solution = new GetAllPalindromicTime();
        System.out.println(solution.getAllPalindromicTime().toString());
    }

}
