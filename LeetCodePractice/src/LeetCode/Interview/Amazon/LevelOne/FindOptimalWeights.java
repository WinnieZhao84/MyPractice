package LeetCode.Interview.Amazon.LevelOne;

import java.util.Arrays;

/**
 * Close Two Sum
 * 有一个容器double capacity, 還有一個array(double[] weights), 和int numOfContainers。
 *
 * 要求在array中选出两个weights總总和小于等于capacity但最接近capacity 然後指定到一個Container object並且return。

 * Created by WinnieZhao on 9/30/2017.
 */
public class FindOptimalWeights {

    static class Container {
        public double first;
        public double second;

        public Container(double first, double second) {
            this.first = first;
            this.second = second;
        }
    }

    public static Container findOptimalWeights(double capacity, double[] weights) {

        if (weights == null || weights.length == 0) {
            return null;
        }
        Arrays.sort(weights);

        double first = weights[0];
        double second = weights[1];
        if (first + second > capacity) {
            return null;
        }

        int left = 0;
        int right = weights.length-1;

        while (left<right) {
            if (weights[left] + weights[right] == capacity) {
                return new Container(weights[left], weights[right]);
            }
            else if (weights[left] + weights[right] > capacity) {
                right--;
            }
            else {
                if (weights[left] + weights[right] > first + second) {
                    first = weights[left];
                    second = weights[right];
                }
                left++;
            }
        }

        return new Container(first, second);
    }
}
