package LeetCode.Hard;

import LeetCode.Helper.Interval;

import java.util.*;

/**
 *
 * Given a data stream input of non-negative integers a1, a2, ..., an, ..., summarize the numbers seen so far
 * as a list of disjoint intervals.
 *
 * For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary will be:
 *
 * [1, 1]
 * [1, 1], [3, 3]
 * [1, 1], [3, 3], [7, 7]
 * [1, 3], [7, 7]
 * [1, 3], [6, 7]
 *
 * Follow up:
 * What if there are lots of merges and the number of disjoint intervals are small compared to the data stream's size?

 * Created by WinnieZhao on 4/24/2017.
 */
public class DataStreamAsDisjointIntervals {

    TreeSet<Interval> set;

    /** Initialize your data structure here. */
    public DataStreamAsDisjointIntervals() {

        set = new TreeSet<Interval>(new Comparator<Interval>(){
            @Override
            public int compare(Interval i1, Interval i2){
                return i1.start - i2.start;
            }
        });
    }

    public void addNum(int val) {
        Interval v = new Interval(val, val);

        Interval f = set.floor(v);
        if(f != null){
            if(f.end >= val) return;

            if(f.end + 1 == val) {
                v.start = f.start;
                set.remove(f);
            }
        }

        Interval h = set.higher(v);

        if(h!= null && h.start == val +1){
            v.end = h.end;
            set.remove(h);
        }

        set.add(v);
    }

    public List<Interval> getIntervals() {
        List<Interval> list = new ArrayList<Interval>();
        list.addAll(set);
        return list;
    }


/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * List<Interval> param_2 = obj.getIntervals();
 */
}
