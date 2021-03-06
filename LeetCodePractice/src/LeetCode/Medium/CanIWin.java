package LeetCode.Medium;

import java.util.HashMap;
import java.util.Map;

/**
 * In the "100 game," two players take turns adding, to a running total, any integer from 1..10. 
 * The player who first causes the running total to reach or exceed 100 wins.
 * 
 * What if we change the game so that players cannot re-use integers?
 * 
 * For example, two players might take turns drawing from a common pool of numbers of 1..15 without replacement
 * until they reach a total >= 100.
 * Given an integer maxChoosableInteger and another integer desiredTotal, determine if the first player to move can
 * force a win, assuming both players play optimally.
 * You can always assume that maxChoosableInteger will not be larger than 20 and desiredTotal will not be larger than 300.
 * 
 * Example
 * Input: maxChoosableInteger = 10 
 * desiredTotal = 11
 * Output: false
 * 
 * Explanation:
 * No matter which integer the first player choose, the first player will lose.
 * The first player can choose an integer from 1 up to 10.
 * If the first player choose 1, the second player can only choose integers from 2 up to 10.
 * The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
 * Same with other integers chosen by the first player, the second player will always win.
 * 
 * @author WinnieZhao
 *
 */
public class CanIWin {
    Map<Integer, Boolean> map;
    boolean[] used;
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if(desiredTotal <= 0) {
            return true;
        }
        if (maxChoosableInteger > desiredTotal) {
            return true;
        }

        int sum = (1+maxChoosableInteger)*maxChoosableInteger/2;
        if (sum < desiredTotal) {
            return false;
        }

        used = new boolean[maxChoosableInteger+1];
        map = new HashMap<>();

        return helper(desiredTotal);
    }
    
    private boolean helper(int desiredTotal){
        if (desiredTotal <= 0) {
            return false;
        }

        int key = format(used);
        if (map.containsKey(key)) {
            return map.get(key);
        }

        for (int i=1; i<used.length; i++) {
            // try every unchosen number as next step
            if (!used[i]) {
                used[i] = true;

                // check whether this lead to a win (i.e. the other player lose)
                if (!helper(desiredTotal-i)) {
                    map.put(key, true);
                    used[i] = false;
                    return true;
                }

                used[i] = false;
            }
        }
        map.put(key, false);
        return false;
    }

    // Transfer boolean[] to an Integer
    // Say the boolean[] is {false, false, true, true, false}, then we can transfer it to an Integer
    // with binary representation as 00110. Since Integer is a perfect choice to be the key of HashMap,
    // then we now can "memorize" the sub-problems using Map<Integer, Boolean>.
    private int format(boolean[] used){
        int num = 0;
        for(boolean b: used){
            num <<= 1;
            if(b) num |= 1;
        }
        return num;
    }
    
    public static void main(String[] args) {
        CanIWin solution = new CanIWin();
        System.out.println(solution.canIWin(10,  11));
        System.out.println(solution.map);
    }
}
