package LeetCode.Easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Design and implement a data structure for a compressed string iterator.
 * It should support the following operations: next and hasNext.
 *
 * The given compressed string will be in the form of each letter followed by a positive integer
 * representing the number of this letter existing in the original uncompressed string.
 *
 * next() - if the original string still has uncompressed characters, return the next letter;
 *          Otherwise return a white space.
 * hasNext() - Judge whether there is any letter needs to be uncompressed.
 *
 * Note: Please remember to RESET your class variables declared in StringIterator,
 * as static/class variables are persisted across multiple test cases. Please see here for more details.
 *
 * Example:
 * StringIterator iterator = new StringIterator("L1e2t1C1o1d1e1");
 *
 * iterator.next(); // return 'L'
 * iterator.next(); // return 'e'
 * iterator.next(); // return 'e'
 * iterator.next(); // return 't'
 * iterator.next(); // return 'C'
 * iterator.next(); // return 'o'
 * iterator.next(); // return 'd'
 * iterator.hasNext(); // return true
 * iterator.next(); // return 'e'
 * iterator.hasNext(); // return false
 * iterator.next(); // return ' '

 * Created by WinnieZhao on 6/12/2017.
 */
public class DesignCompressedStringIterator {

    /**
     * Your StringIterator object will be instantiated and called as such:
     * StringIterator obj = new StringIterator(compressedString);
     * char param_1 = obj.next();
     * boolean param_2 = obj.hasNext();
     */

    List<Character> chars = new ArrayList<>();
    List<Integer> nums = new ArrayList<>();
    int index = 0;
    public DesignCompressedStringIterator(String compressedString) {
        if (compressedString != null) {
            int num = 0;
            for (int i=0; i<compressedString.length(); i++) {
                if (Character.isAlphabetic(compressedString.charAt(i))) {
                    chars.add(compressedString.charAt(i));
                    if (num > 0) {
                        nums.add(num);
                        num = 0;
                    }
                }
                else {
                    num = num*10 + (compressedString.charAt(i) - '0');
                }
            }
            if (chars.size() > nums.size()) {
                nums.add(num);
            }
        }

    }

    public char next() {
        if (!this.hasNext()) {
            return ' ';
        }

        int num = nums.get(index);
        int i = index;
        if (num == 1) {
            index++;
        }
        nums.set(i, num-1);
        return chars.get(i);
    }

    public boolean hasNext() {
        return index < chars.size();
    }

    public static void main(String[] args) {
        DesignCompressedStringIterator solution = new DesignCompressedStringIterator("x10");
        System.out.println(solution.next());
        System.out.println(solution.next());
        System.out.println(solution.next());
        System.out.println(solution.next());
    }
}
