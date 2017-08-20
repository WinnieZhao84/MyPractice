package LeetCode.Easy;

/**
 * Initially, there is a Robot at position (0, 0). Given a sequence of its moves, judge if this robot makes a circle,
 * which means it moves back to the original place.
 *
 * The move sequence is represented by a string. And each move is represent by a character.
 * The valid robot moves are R (Right), L (Left), U (Up) and D (down). The output should be true or false representing
 * whether the robot makes a circle.
 *
 * Example 1:
 * Input: "UD"
 * Output: true
 *
 * Example 2:
 * Input: "LL"
 * Output: false
 *
 * Created by WinnieZhao on 8/19/2017.
 */
public class JudgeRouteCircle {

    public boolean judgeCircle(String moves) {
        if (moves == null || moves.length() == 0) {
            return false;
        }
        int l_length=0;
        int r_length=0;
        int d_length=0;
        int u_length=0;
        for (char c : moves.toCharArray()) {
            if (c == 'U') u_length++;
            if (c == 'D') d_length++;
            if (c == 'L') l_length++;
            if (c == 'R') r_length++;
        }

        return l_length == r_length && d_length == u_length;
    }
}
