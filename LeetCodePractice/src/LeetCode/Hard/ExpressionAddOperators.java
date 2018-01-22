package LeetCode.Hard;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators
 * (not unary) +, -, or * between the digits so they evaluate to the target value.
 *
 * Examples:
 * "123", 6 -> ["1+2+3", "1*2*3"]
 * "232", 8 -> ["2*3+2", "2+3*2"]
 * "105", 5 -> ["1*0+5","10-5"]
 * "00", 0 -> ["0+0", "0-0", "0*0"]
 * "3456237490", 9191 -> []

 * Created by WinnieZhao on 2017/5/2.
 */
public class ExpressionAddOperators {

    public List<String> addOperators(String num, int target) {

        List<String> res = new ArrayList<>();

        this.dfs(res, num, target, "", 0, 0, 0);

        return res;
    }

    private void dfs(List<String> res, String num, int target, String path, int pos, long prev, long multi) {
        if (pos == num.length()) {
            if (prev == target) {
                res.add(path);
            }
            return;
        }

        for (int i=pos; i<num.length(); i++) {

            if (pos != i && num.charAt(pos) == '0') {
                break;
            }
            Long cur = Long.parseLong(num.substring(pos, i+1));

            if (pos == 0) {
                this.dfs(res, num, target, path + cur, i+1, cur, cur);
            }
            else {
                this.dfs(res, num, target, path + "+" + cur, i+1, prev + cur, cur);

                this.dfs(res, num, target, path + "-" + cur, i+1, prev - cur, -cur);

                // 12345 => 1+ 2 + 3 * 4 => 1 + 2 + 3 - 3 + 3 * 4
                this.dfs(res, num, target, path + "*" + cur, i+1, prev - multi + multi * cur, multi * cur);
            }
        }
    }

    public static void main(String[] args) {
        ExpressionAddOperators solution = new ExpressionAddOperators();

        System.out.println(solution.addOperators("105", 5));
    }

}


