package LeetCode.Medium;

import java.util.*;

/**
 * Implement a MyCalendarTwo class to store your events. A new event can be added if adding the event will not cause a
 * triple booking.
 *
 * Your class will have one method, book(int start, int end). Formally, this represents a booking on the half open
 * interval [start, end), the range of real numbers x such that start <= x < end.
 *
 * A triple booking happens when three events have some non-empty intersection (ie., there is some time that is common
 * to all 3 events.)
 *
 * For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully without
 * causing a triple booking. Otherwise, return false and do not add the event to the calendar.
 *
 * Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
 * Example 1:
 * MyCalendar();
 *
 * MyCalendar.book(10, 20); // returns true
 * MyCalendar.book(50, 60); // returns true
 * MyCalendar.book(10, 40); // returns true
 * MyCalendar.book(5, 15); // returns false
 * MyCalendar.book(5, 10); // returns true
 * MyCalendar.book(25, 55); // returns true
 *
 * Explanation:
 * The first two events can be booked.  The third event can be double booked.
 * The fourth event (5, 15) can't be booked, because it would result in a triple booking.
 * The fifth event (5, 10) can be booked, as it does not use time 10 which is already double booked.
 * The sixth event (25, 55) can be booked, as the time in [25, 40) will be double booked with the third event;
 * the time [40, 50) will be single booked, and the time [50, 55) will be double booked with the second event.
 *
 * Note:
 *
 * The number of calls to MyCalendar.book per test case will be at most 1000.
 * In calls to MyCalendar.book(start, end), start and end are integers in the range [0, 10^9].

 * Created by WinnieZhao on 12/30/2017.
 */
public class MyCalendarII {

    /**
     * Time Complexity: O(N^2)
     * where NN is the number of events booked. For each new event, we process every previous event to decide whether
     * the new event can be booked
     *
     * Each time of book, instead of fail a book when there is 1 or more overlap with existing books as in MyCalendar I,
     * we just want to make sure these overlaps does not overlap - having overlap is now ok, but overlapped period
     * cannot be overlapped again.
     * So we just need to keep track of all the overlaps with any previous books
     *
     */
    List<int[]> calendars = new ArrayList<>();
    List<int[]> overlaps = new ArrayList<>();

    public MyCalendarII() {}

    public boolean book(int start, int end) {

        for (int[] b : overlaps) {
            if (b[0] < end && start < b[1]) {
                return false;
            }
        }
        for (int[] b : calendars) {
            if (b[0] < end && start < b[1]) {
                overlaps.add(new int[]{Math.max(start, b[0]), Math.min(end, b[1])});
            }
        }
        calendars.add(new int[]{start, end});
        return true;
    }

    TreeMap<Integer, Integer> delta = new TreeMap<>();

    /**
     * Boundary Count Solution
     * When booking a new event [start, end), count delta[start]++ and delta[end]--.
     * When processing the values of delta in sorted order of their keys,
     * the running sum active is the number of events open at that time.
     * If the sum is 3 or more, that time is (at least) triple booked.
     *
     * Time Complexity: O(N^2)
     * where NN is the number of events booked. For each new event, we traverse delta in O(N) time.
     */
    public boolean book_better(int start, int end) {
        delta.put(start, delta.getOrDefault(start, 0) + 1);
        delta.put(end, delta.getOrDefault(end, 0) - 1);

        int active = 0;
        for (int d: delta.values()) {
            active += d;
            if (active >= 3) {
                delta.put(start, delta.get(start) - 1);
                delta.put(end, delta.get(end) + 1);
                if (delta.get(start) == 0)
                    delta.remove(start);
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        MyCalendarII solution = new MyCalendarII();

        System.out.println(solution.book_better(10, 20));
        System.out.println(solution.book_better(50, 60));
        System.out.println(solution.book_better(10, 40));
        System.out.println(solution.book_better(5, 15));
        System.out.println(solution.book_better(5, 10));
        System.out.println(solution.book_better(25, 55));
    }
}
