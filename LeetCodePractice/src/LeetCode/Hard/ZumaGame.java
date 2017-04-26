package LeetCode.Hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Think about Zuma Game. You have a row of balls on the table, colored red(R), yellow(Y), blue(B), green(G), and white(W).
 * You also have several balls in your hand. Each time, you may choose a ball in your hand, and insert it into the row
 * (including the leftmost place and rightmost place). Then, if there is a group of 3 or more balls in the same color touching,
 * remove these balls. Keep doing this until no more balls can be removed.
 *
 * Find the minimal balls you have to insert to remove all the balls on the table. If you cannot remove all the balls, output -1.
 *
 * Examples:
 * Input: "WRRBBW", "RB"
 * Output: -1
 * Explanation: WRRBBW -> WRR[R]BBW -> WBBW -> WBB[B]W -> WW
 *
 * Input: "WWRRBBWW", "WRBRW"
 * Output: 2
 * Explanation: WWRRBBWW -> WWRR[R]BBWW -> WWBBWW -> WWBB[B]WW -> WWWW -> empty
 *
 * Input:"G", "GGGGG"
 * Output: 2
 * Explanation: G -> G[G] -> GG[G] -> empty
 *
 * Input: "RBYYBBRRB", "YRBGB"
 * Output: 3
 * Explanation: RBYYBBRRB -> RBYY[Y]BBRRB -> RBBBRRB -> RRRB -> B -> B[B] -> BB[B] -> empty
 *
 * Note:
 * You may assume that the initial row of balls on the table won’t have any 3 or more consecutive balls with the same color.
 * The number of balls on the table won't exceed 20, and the string represents these balls is called "board" in the input.
 * The number of balls in your hand won't exceed 5, and the string represents these balls is called "hand" in the input.
 * Both input strings will be non-empty and only contain characters 'R','Y','B','G','W'.

 * Created by WinnieZhao on 4/25/2017.
 */
public class ZumaGame {

    public int findMinStep(String board, String hand) {
        if (hand == null || hand.equals("")) {
            return -1;
        }
        char[] boards = board.toCharArray();
        List<Character> boardChars = new ArrayList<>();

        Map<Character,Integer> handMap = new HashMap<>();
        handMap.put('R',0);
        handMap.put('Y',0);
        handMap.put('B',0);
        handMap.put('G',0);
        handMap.put('W',0);
        for (char h : hand.toCharArray()) {
            handMap.put(h, handMap.get(h) + 1);
        }
        for (char c : boards) {
            boardChars.add(c);
        }
        return findMin(boardChars, handMap);
    }


    private int findMin(List<Character> currentBoards, Map<Character, Integer> currentHands) {

        boolean isFind = true;
        while (isFind) {
            isFind = false;

            for (int i = 0; i < currentBoards.size() - 2; i++) {
                if (currentBoards.get(i) == currentBoards.get(i + 1) && currentBoards.get(i + 1) == currentBoards.get(i + 2)) {
                    int j = i + 2;
                    while (j + 1 < currentBoards.size() && currentBoards.get(i) == currentBoards.get(j + 1)) {
                        j++;
                    }
                    for (int m = j; m >= i; m--) {
                        currentBoards.remove(m);
                    }
                    isFind = true;
                    break;
                }
            }
        }

        if (currentBoards.isEmpty()) {
            return 0;
        }
        if (isHandEmpty(currentHands)) {
            return -1;
        }

        int count = 0;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < currentBoards.size(); i++) {
            char c = currentBoards.get(i);
            count++;

            if (i == currentBoards.size() - 1 || currentBoards.get(i+1) != c) {
                int missing = 3 - count;

                if (currentHands.get(c) >= missing) {
                    currentHands.put(c, currentHands.get(c) - missing);

                    List<Character> smallerBoard = new ArrayList<>(currentBoards);

                    for (int j = 0; j<count; j++) {
                        smallerBoard.remove(i-j);
                    }

                    int smallerFind = findMin(smallerBoard, currentHands);

                    if ( smallerFind != -1 ) {
                        min = Math.min(smallerFind + missing, min);
                    }

                    currentHands.put(c, currentHands.get(c) + missing);
                }

                count = 0;
            }
        }
        return (min == Integer.MAX_VALUE) ? -1 : min;
    }

    private boolean isHandEmpty(Map<Character,Integer> handMap) {
        for (int value : handMap.values()) {
            if (value > 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        ZumaGame solution = new ZumaGame();

        int min = solution.findMinStep("WRGY", "WRBRW");

        System.out.println(min);
    }

}
