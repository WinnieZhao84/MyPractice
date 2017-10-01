package LeetCode.Interview.Amazon.LevelOne;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 写好了一个叫Result的类, 中文翻译成节点, 题目是输入是一堆节点,节点里面有两个属性学生id和分数,保证每个学生至少会有5个分数,
 * 求每个人最高的5个分数的平均分。返回的是Map, key是id，value是每个人最高5个分数的平均分double是平均分.
 *
 * Created by WinnieZhao on 9/30/2017.
 */
public class FiveScores {

    class Result{
        int id;
        int value;
        public Result(int id, int value){
            this.id = id;
            this.value = value;
        }
    }

    public static Map<Integer, Double> getHighFive(Result[] results) {
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for(Result res : results) {
            if(!map.containsKey(res.id)) {
                map.put(res.id, new PriorityQueue<>());
                map.get(res.id).offer(res.value);
            }
            else {
                if (map.get(res.id).size() < 5) {
                    map.get(res.id).offer(res.value);
                }
                else if(res.value > map.get(res.id).peek()) {
                    map.get(res.id).poll();
                    map.get(res.id).offer(res.value);
                }
            }
        }

        Map<Integer, Double> res = new HashMap<>();
        for(int id: map.keySet()) {
            int sum = 0;
            PriorityQueue<Integer> q = map.get(id);
            while(!q.isEmpty()) {
                sum += q.poll();
            }
            res.put(id, (sum + 0.0) / 5);
        }
        return res;
    }
}
