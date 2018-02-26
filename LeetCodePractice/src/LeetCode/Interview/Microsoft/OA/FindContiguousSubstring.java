package LeetCode.Interview.Microsoft.OA;

import java.util.ArrayList;
import java.util.List;

/**
 * 给字符串 BCCDEG expectation: {'BC', 'CD', 'CDE', 'DE'}
 *
 * Created by WinnieZhao on 2/25/2018.
 */
public class FindContiguousSubstring {

    public List<String> getRes(String s) {
        List<String> res = new ArrayList<>();

        if (s == null || s.isEmpty()) {
            return res;
        }

        for (int i=0; i<s.length(); i++) {
            for (int j=i+1; j<s.length(); j++) {

                if (s.charAt(j) - s.charAt(j-1) != 1) {
                    break;
                }

                if (j - i + 1 >= 2) {
                    res.add(s.substring(i, j+1));
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        FindContiguousSubstring solution = new FindContiguousSubstring();

        System.out.println(solution.getRes("BCCDEG").toString());
    }
}
