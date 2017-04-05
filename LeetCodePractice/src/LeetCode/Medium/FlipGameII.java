package LeetCode.Medium;

/**
 * 294
 *
 * You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -,
 * you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make
 * a move and therefore the other person will be the winner.
 *
 * Write a function to determine if the starting player can guarantee a win. For example, given s = "++++", return true.
 * The starting player can guarantee a win by flipping the middle "++" to become "+--+".
 *
 * Follow up: Derive your algorithm's runtime complexity.
 * T(N) = T(N-2) + T(N-3) + [T(2) + T(N-4)] + [T(3) + T(N-5)] + ... [T(N-5) + T(3)] + [T(N-4) + T(2)] + T(N-3) + T(N-2) = 2 * sum(T[i])  (i = 3..N-2)

 * Created by WinnieZhao on 2017/4/4.
 */
public class FlipGameII {

    public boolean canWin(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }

        char[] array = s.toCharArray();

        return canWin(array);
    }

    private boolean canWin(char[] array) {
        for (int i=0; i<array.length-1; i++) {
            if (array[i] == '+' && array[i+1] == '+') {
                array[i] = '-';
                array[i+1] = '-';

                boolean otherWin = this.canWin(array);

                array[i] = '+';
                array[i+1] = '+';

                if (!otherWin) {
                    return true;
                }
            }
        }

        return false;
    }
}
