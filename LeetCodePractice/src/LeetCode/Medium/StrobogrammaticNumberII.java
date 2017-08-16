package LeetCode.Medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 247
 *
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * Find all strobogrammatic numbers that are of length = n.
 * For example, Given n = 2, return ["11","69","88","96"].
 *
 * Created by WinnieZhao on 4/10/2017.
 */
public class StrobogrammaticNumberII {

    public List<String> findStrobogrammatic(int n) {
        List<String> res;

        // even number
        if( (n&1) == 0) {
            List<String> l0 = new ArrayList<>();
            l0.add("");
            res = l0;
        }
        // odd number
        else{
            List<String> l1 = new ArrayList<>();
            l1.add("1");
            l1.add("0");
            l1.add("8");
            res = l1;
        }

        int i = ((n&1) == 0) ? 2 : 3;
        for(; i<= n; i+=2){
            List<String> tmp = new ArrayList<>();
            for(String s : res){
                if(i != n) tmp.add("0" + s + "0");
                tmp.add("1" + s +"1");
                tmp.add("9" + s +"6");
                tmp.add("6" + s +"9");
                tmp.add("8" + s +"8");
            }

            res = tmp;
        }
        return res;
    }
}
