package LeetCode.Medium;

/**
 * Additive number is a string whose digits can form additive sequence.
 * 
 * A valid additive sequence should contain at least three numbers. Except for the first two numbers,
 * each subsequent number in the sequence must be the sum of the preceding two.
 * 
 * For example:
 * "112358" is an additive number because the digits can form an additive sequence: 1, 1, 2, 3, 5, 8.
 * 1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 * 
 * "199100199" is also an additive number, the additive sequence is: 1, 99, 100, 199.
 * 1 + 99 = 100, 99 + 100 = 199
 * Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.
 * 
 * Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.
 * 
 * Follow up:
 * How would you handle overflow for very large input integers?
 * 
 * @author WinnieZhao
 *
 */
public class AdditiveNumber {

    /**
     * Choose the first number A, it can be the leftmost 1 up to i digits.
     * i<=(L-1)/2 because the third number should be at least as long as the first number
     *
     * Choose the second number B, it can be the leftmost 1 up to j digits excluding the first number.
     * the limit for j is a little bit tricky, because we don't know whether A or B is longer.
     * The remaining string (with length L-j) after excluding A and B should have a length of at least max(length A, length B),
     * where length A = i and length B = j-i, thus L-j >= max(j-i, i)
     *
     * Calls the recursive checker function and returns true if passes the checker function, or continue to the next
     * choice of B (A) until there is no more choice for B or A, in which case returns a false.

     * @param num
     * @return
     */
    public boolean isAdditiveNumber(String num) {
        int L = num.length();

        // choose the first number A
        for(int i=1; i<=(L-1)/2; i++) {
            // A cannot start with a 0 if its length is more than 1
            if(num.charAt(0) == '0' && i>=2) break; //previous code: continue;

            // choose the second number B
            for(int j=i+1; j-i<=L-j && i<=L-j; j++) {
                // B cannot start with a 0 if its length is more than 1
                if(num.charAt(i) == '0' && j-i>=2) break; // previous: continue;

                long num1 = Long.parseLong(num.substring(0, i)); // A
                long num2 = Long.parseLong(num.substring(i, j)); // B
                String substr = num.substring(j); // remaining string

                if(isAdditive(substr, num1, num2)) return true; // return true if passes isAdditive test
                // else continue; // continue for loop if does not pass isAdditive test
            }
        }
        return false; // does not pass isAdditive test, thus is not additive
    }

    // Recursively checks if a string is additive
    private boolean isAdditive(String str, long num1, long num2) {
        if(str.equals("")) return true; // reaches the end of string means a yes

        long sum = num1+num2;
        String s = ((Long)sum).toString();
        if(!str.startsWith(s)) return false; // if string does not start with sum of num1 and num2, returns false

        return isAdditive(str.substring(s.length()), num2, sum); // recursively checks the remaining string
    }
    
    public static void main(String[] args) {
        AdditiveNumber solution = new AdditiveNumber();
        System.out.println(solution.isAdditiveNumber("1023"));
    }
}
