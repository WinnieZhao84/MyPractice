package LeetCode.Interview.Amazon.LevelOne;

import java.util.ArrayList;
import java.util.List;

/**
 * Same as LeetCode
 * Created by WinnieZhao on 9/30/2017.
 */
public class GrayCode {
    public List<Integer> grayCode(int n) {
        if (n < 0) {
            return new ArrayList<>();
        }

        if (n == 0) {
            List<Integer> list = grayCode(n-1);
            list.add(n);
            return list;
        }

        List<Integer> temp = grayCode(n-1);
        List<Integer> res = new ArrayList<>(temp);

        int addNumber = 1 << (n-1);

        for (int i=temp.size()-1; i>=0; i--) {
            res.add(addNumber + temp.get(i));
        }

        return res;
    }
}
