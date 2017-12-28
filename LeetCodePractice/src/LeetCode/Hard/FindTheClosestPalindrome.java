package LeetCode.Hard;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an integer n, find the closest integer (not including itself), which is a palindrome.
 *
 * The 'closest' is defined as absolute difference minimized between two integers.
 * Example 1:
 * Input: "123"
 * Output: "121"
 *
 * Note:
 * The input n is a positive integer represented by string, whose length will not exceed 18.
 * If there is a tie, return the smaller one as answer.

 * Created by WinnieZhao on 2017/5/1.
 */
public class FindTheClosestPalindrome {

    // Brute Force[Time Limit Exceeded]
    public String nearestPalindromic(String n) {
        long num = Long.parseLong(n);
        for (long i = 1;; i++) {
            if (isPalindrome(num - i))
                return "" + (num - i);
            if (isPalindrome(num + i))
                return "" + (num + i);
        }
    }

    boolean isPalindrome(long x) {
        long t = x, rev = 0;
        while (t > 0) {
            rev = 10 * rev + t % 10;
            t /= 10;
        }
        return rev == x;
    }

    String mirroring(String s) {
        String x = s.substring(0, (s.length()) / 2);
        return x + (s.length() % 2 == 1 ? s.charAt(s.length() / 2) : "") + new StringBuilder(x).reverse().toString();
    }

    /**
     * From the above illustration, we can conclude that if replication is used to generate the palindromic number,
     * we should always replicate the first half to the second half.
     * In this implementation, we've stored such a number in aa at a difference of diff1 from nn.
     *
     * But, there exists another case as well, where the digit at the middle index is incremented or decremented.
     * In such cases, it could be profitable to make changes to the central digit only since such changes could lead
     * to a palindrome formation nearer to the original digit. e.g. 10987. Using the above criteria,
     * the palindrome obtained will be 10901 which is at a more difference from 10987 than 11011.
     * A similar situation occurs if a 0 occurs at the middle digit. But, again as discussed previously,
     * we need to consider only the first half digits to obtain the new palindrome.
     * This special effect occurs with 0 or 9 at the middle digit since, only decrementing 0 and incrementing 9
     * at that digit place can lead to the change in the rest of the digits towards their left.
     * In any other case, the situation boils down to the one discussed in the first paragraph.
     *
     * Now, whenever we find a 0 near the middle index, in order to consider the palindromes which are lesser than nn,
     * we subtract a 1 from the first half of the number to obtain a new palindromic half e.g. If the given number nn
     * is 20001, we subtract a 1 from 200 creating a number of the form 199xx. To obtain the new palindrome,
     * we replicate the first half to obtain 19991. Taking another example of 10000, (with a 1 at the MSB),
     * we subtract a 1 from 100 creating 099xx as the new number transforming to a 9999 as the new palindrome.
     * This number is stored in b having a difference of diff2 from nn
     *
     * Similar treatment needs to be done with a 9 at the middle digit, except that this time we need to consider the
     * numbers larger than the current number. For this, we add a 1 to the first half. e.g. Taking the number 10987,
     * we add a 1 to 109 creating a number of the form 110xx(11011 is the new palindrome).
     * This palindrome is stored in c having a difference of diff3 from nn.
     *
     * Out of these three palindromes, we can choose the one with a minimum difference from nn.
     * Further, in case of a tie, we need to return the smallest palindrome obtained.
     * For resolving this tie's conflict, we can observe that a tie is possible only if one number is larger
     * than nn and another is lesser than nn. Further, we know that the number bb is obtained by decreasing nn.
     * Thus, in case of conflict between bb and any other number, we need to choose bb. Similarly, cc is obtained
     * by increasing nn. Thus, in case of a tie between cc and any other number, we need to choose the number other than cc.

     * @param n
     * @return
     */
    public String nearestPalindromic_good(String n) {
        if (n.equals("1"))
            return "0";

        // e.g. num = 1234, mirroring = 1221
        String a = mirroring(n);
        long diff1 = Long.MAX_VALUE;
        diff1 = Math.abs(Long.parseLong(n) - Long.parseLong(a));
        if (diff1 == 0)
            diff1 = Long.MAX_VALUE;

        StringBuilder s = new StringBuilder(n);
        int i = (s.length() - 1) / 2;
        while (i >= 0 && s.charAt(i) == '0') {
            s.replace(i, i + 1, "9");
            i--;
        }
        if (i == 0 && s.charAt(i) == '1') {
            s.delete(0, 1);
            int mid = (s.length() - 1) / 2;
            s.replace(mid, mid + 1, "9");
        }
        else {
            s.replace(i, i + 1, "" + (char) (s.charAt(i) - 1));
        }

        String b = mirroring(s.toString());
        long diff2 = Math.abs(Long.parseLong(n) - Long.parseLong(b));


        s = new StringBuilder(n);
        i = (s.length() - 1) / 2;
        while (i >= 0 && s.charAt(i) == '9') {
            s.replace(i, i + 1, "0");
            i--;
        }
        if (i < 0) {
            s.insert(0, "1");
        }
        else {
            s.replace(i, i + 1, "" + (char) (s.charAt(i) + 1));
        }

        String c = mirroring(s.toString());
        long diff3 = Math.abs(Long.parseLong(n) - Long.parseLong(c));

        if (diff2 <= diff1 && diff2 <= diff3)
            return b;
        if (diff1 <= diff3 && diff1 <= diff2)
            return a;
        else
            return c;
    }

    /**
     * Easier understanding algorithm:
     *
     * Build a list of candidate answers for which the final answer must be one of those candidates.
     * Afterwards, choosing from these candidates is straightforward.
     *
     * If the final answer has the same number of digits as the input string S,
     * then the answer must be the middle digits + (-1, 0, or 1) flipped into a palindrome.
     * For example, 23456 had middle part 234, and 233, 234, 235 flipped into a palindrome yields 23332, 23432, 23532.
     * Given that we know the number of digits, the prefix 235 (for example) uniquely determines the corresponding
     * palindrome 23532, so all palindromes with larger prefix like 23732 are strictly farther away from S than 23532 >= S.
     *
     * If the final answer has a different number of digits, it must be of the form 999....999 or 1000...0001,
     * as any palindrome smaller than 99....99 or bigger than 100....001 will be farther away from S.
     *
     *
     * @param n
     * @return
     */
    public String nearestPalindromic_easy(String n) {
        if (n == null || n.isEmpty()) {
            return null;
        }

        int mid = (n.length()-1)/2;
        String halfStr = n.substring(0, mid+1);

        Set<Long> candidates = new HashSet<>();

        candidates.addAll(this.getCandidate(halfStr, n.length()));
        candidates.add(this.getAllNines(n.length()));
        candidates.add(this.getOneZero(n.length()));

        if (n.length()-1 > 0) {
            candidates.add(this.getAllNines(n.length()-1));
            candidates.add(this.getOneZero(n.length()-1));
        }

        long diff = Long.MAX_VALUE;
        long num = Long.parseLong(n);

        long res = Long.MAX_VALUE;
        for (Long l : candidates) {
            if (l == num) {
                continue;
            }
            if (diff > Math.abs(l-num)) {
                diff = Math.abs(l-num);
                res = l;
            }
            else if (diff == Math.abs(l-num)) {
                res = Math.min(res, l);
            }
        }

        return String.valueOf(res);
    }

    private Set<Long> getCandidate(String halfStr, int len) {
        Set<Long> res = new HashSet<>();

        Long num = Long.parseLong(halfStr);

        Set<Long> set = new HashSet<>();
        set.add(num);
        set.add(num-1);
        set.add(num+1);

        for (Long n : set) {
            String str = String.valueOf(n);

            String reverse = "";
            if (len % 2 == 0) {
                reverse = new StringBuilder(str).reverse().toString();
            }
            else {
                reverse = new StringBuilder(str.substring(0, str.length()-1)).reverse().toString();
            }
            str += reverse;
            res.add(Long.parseLong(str));
        }

        return res;
    }

    private Long getAllNines(int len) {
        String str="";
        for(int i=0;i<len;i++){
            str+='9';
        }
        return Long.parseLong(str);
    }

    public Long getOneZero(int len){
        return (long) Math.pow(10, len)+1;
    }

    public static void main(String[] args) {
        FindTheClosestPalindrome solution = new FindTheClosestPalindrome();
        System.out.println(solution.nearestPalindromic_easy("10"));
    }
}
