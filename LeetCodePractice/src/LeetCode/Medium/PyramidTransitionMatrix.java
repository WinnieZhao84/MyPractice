package LeetCode.Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * We are stacking blocks to form a pyramid. Each block has a color which is a one letter string, like `'Z'`.
 *
 * For every block of color 'C' we place not in the bottom row, we are placing it on top of a left block of color 'A'
 * and right block of color 'B'. We are allowed to place the block there only if '(A, B, C)' is an allowed triple.
 *
 * We start with a bottom row of bottom, represented as a single string. We also start with a list of allowed triples
 * allowed. Each allowed triple is represented as a string of length 3.
 *
 * Return true if we can build the pyramid all the way to the top, otherwise false.
 *
 * Example 1:
 * Input: bottom = "XYZ", allowed = ["XYD", "YZE", "DEA", "FFF"]
 * Output: true
 *
 * Explanation:
 * We can stack the pyramid like this:
 *        A
 *       / \
 *      D   E
 *     / \ / \
 *    X   Y   Z
 *
 *  This works because ('X', 'Y', 'D'), ('Y', 'Z', 'E'), and ('D', 'E', 'A') are allowed triples.
 *
 * Example 2:
 * Input: bottom = "XXYX", allowed = ["XXX", "XXY", "XYX", "XYY", "YXZ"]
 * Output: false
 *
 * Explanation:
 * We can't stack the pyramid to the top.
 * Note that there could be allowed triples (A, B, C) and (A, B, D) with C != D.
 *
 * Note:
 * bottom will be a string with length in range [2, 12].
 * allowed will have length in range [0, 343].
 * Letters in all strings will be chosen from the set {'A', 'B', 'C', 'D', 'E', 'F', 'G'}.

 * Created by WinnieZhao on 1/2/2018.
 */
public class PyramidTransitionMatrix {
    /**
     * 注意allowed中的三元组是可以重复利用的，这样我们定义dp：
     * dp[i][j][k]: 表示第i层上，第j个元素为k
     * 根据bottom，可以初始化每个位置j上含有的字符bottom[j]， dp更新式如下：
     * dp[i][j][k] = true if dp[i + 1][j][l] = true && dp[i + 1][j + 1][r] = true && lrk组成的字符串在allowed中出现过。
     */

    public boolean pyramidTransition(String bottom, List<String> allowed) {
        Map<String, List<String>> mem = new HashMap<>();
        boolean[][] dp = new boolean[20][7];
        int n = bottom.length();

        for (String allow : allowed) {
            mem.computeIfAbsent(allow.substring(0, 2), k -> new ArrayList<>()).add(allow.substring(2));
        }

        for (int i = 0; i < n; ++i) {
            dp[i][bottom.charAt(i) - 'A'] = true;
        }

        for (int i = n - 1; i >= 1; --i) {
            boolean[][] ndp = new boolean[20][7];
            for (int j = 0; j < i; ++j) {
                for (int l = 0; l < 7; ++l) {
                    for (int r = 0; r < 7; ++r) {
                        if (dp[j][l] && dp[j + 1][r]) {
                            if (mem.containsKey((char)(l + 'A') + "" + (char)(r + 'A'))) {
                                for (String s : mem.get((char)(l + 'A') + "" + (char)(r + 'A'))) {
                                    ndp[j][s.charAt(0) - 'A'] = true;
                                }
                            }
                        }
                    }
                }
            }
            dp = ndp;
        }

        for (int i = 0; i < 7; ++i) {
            if (dp[0][i]) return true;
        }
        return false;
    }
}
