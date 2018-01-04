package LeetCode.Medium;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

/**
 * We are given an array asteroids of integers representing asteroids in a row.
 *
 * For each asteroid, the absolute value represents its size, and the sign represents its direction
 * (positive meaning right, negative meaning left). Each asteroid moves at the same speed.
 *
 * Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode.
 * If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.
 *
 * Example 1:
 * Input: asteroids = [5, 10, -5]
 * Output: [5, 10]
 * Explanation:
 * The 10 and -5 collide resulting in 10.  The 5 and 10 never collide.
 *
 * Example 2:
 * Input: asteroids = [8, -8]
 * Output: []
 * Explanation:
 * The 8 and -8 collide exploding each other.
 *
 * Example 3:
 * Input: asteroids = [10, 2, -5]
 * Output: [10]
 * Explanation:
 * The 2 and -5 collide resulting in -5.  The 10 and -5 collide resulting in 10.
 *
 * Example 4:
 * Input: asteroids = [-2, -1, 1, 2]
 * Output: [-2, -1, 1, 2]
 * Explanation:
 * The -2 and -1 are moving left, while the 1 and 2 are moving right.
 * Asteroids moving the same direction never meet, so no asteroids will meet each other.
 *
 * Note:
 * The length of asteroids will be at most 10000.
 * Each asteroid will be a non-zero integer in the range [-1000, 1000]..
 *
 * Created by WinnieZhao on 12/31/2017.
 */
public class AsteroidCollision {

    public int[] asteroidCollision(int[] asteroids) {

        if (asteroids == null || asteroids.length <= 1) {
            return asteroids;
        }

        LinkedList<Integer> res = new LinkedList<>();

        for (int a : asteroids) {
            while (!res.isEmpty() && res.getLast() > 0 && res.getLast() < -a) {
                res.removeLast();
            }
            if (res.isEmpty() || a > 0 || res.getLast() < 0) {
                res.add(a);
            }
            else if (!res.isEmpty() && res.getLast() == -a) {
                res.removeLast();
            }
        }

        return res.stream().mapToInt(i->i).toArray();
    }

    public static void main(String[] args) {
        AsteroidCollision solution = new AsteroidCollision();

        System.out.println(Arrays.toString(solution.asteroidCollision(new int[] {-2, 2, -1, -2})));
        System.out.println(Arrays.toString(solution.asteroidCollision(new int[] {5, 10, -5})));
        System.out.println(Arrays.toString(solution.asteroidCollision(new int[] {8, -8})));
        System.out.println(Arrays.toString(solution.asteroidCollision(new int[] {10, 2, -5})));
        System.out.println(Arrays.toString(solution.asteroidCollision(new int[] {-2, -1, 1, 2})));
    }

}