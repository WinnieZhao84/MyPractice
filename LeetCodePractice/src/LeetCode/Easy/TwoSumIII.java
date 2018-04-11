package LeetCode.Easy;

import java.util.*;

/**
 * 170
 *
 * Design and implement a TwoSum class. It should support the following operations: add and find.
 *
 * add - Add the number to an internal data structure.
 * find - Find if there exists any pair of numbers which sum is equal to the value.
 *
 * For example,
 * add(1); add(3); add(5);
 * find(4) -> true
 * find(7) -> false
 *
 * Created by WinnieZhao on 2017/3/27.
 */
public class TwoSumIII {
    // Your TwoSum object will be instantiated and called as such:
    // TwoSum twoSum = new TwoSum();
    // twoSum.add(number);
    // twoSum.find(value);

    Map<Integer, Integer> map = new HashMap<>();

    // Add the number to an internal data structure.
    public void add(int number) {
        if(map.containsKey(number)){
            map.put(number, map.get(number) + 1);
        }
        else{
            map.put(number, 1);
        }
    }

    // Find if there exists any pair of numbers which sum is equal to the value.
    public boolean find(int value) {
        for(Integer key : map.keySet()) {
            int target = value - key;
            if (map.containsKey(target)) {
                if(target == key && map.get(key) <2) {
                    continue;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * When there are more find() than add()
     * Pre-operate sum in add() function
     */
    public class TwoSum {
        Set<Integer> sum;
        Set<Integer> num;

        TwoSum(){
            sum = new HashSet<>();
            num = new HashSet<>();
        }

        // Add the number to an internal data structure.
        public void add(int number) {
            if(num.contains(number)){
                sum.add(number * 2);
            }else{
                Iterator<Integer> iter = num.iterator();
                while(iter.hasNext()){
                    sum.add(iter.next() + number);
                }
                num.add(number);
            }
        }

        // Find if there exists any pair of numbers which sum is equal to the value.
        public boolean find(int value) {
            return sum.contains(value);
        }
    }
}
