package LeetCode.Easy;

/**
 * Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
 * 
 * Example:
 * Given a = 1 and b = 2, return 3.
 * 
 * @author WinnieZhao
 *
 */
public class SumOfTwoIntegers {

    /**
     * 我们在做加法运算的时候，每位相加之后可能会有进位Carry产生，然后在下一位计算时需要加上进位一起运算，那
     * 我们来看一个例子759+674 如果我们不考虑进位，可以得到323
     * 如果我们只考虑进位，可以得到1110
     * 我们把上面两个数字假期323+1110=1433就是最终结果了
     * 然后我们进一步分析，如果得到上面的第一第二种情况，我们在二进制下来看，不考虑进位的加，0+0=0， 0+1=1, 1+0=1， 1+1=0，
     * 这就是异或的运算规则，如果只考虑进位的加0+0=0, 0+1=0, 1+0=0, 1+1=1，而这其实这就是与的运算，而第三步在将两者相加时，
     * 我们再递归调用这个算法，终止条件是当进位为0时，我们直接返回第一步的结果

     * @param a
     * @param b
     * @return
     */
    public int getSum(int a, int b) {
        if (b == 0) {
            return a;
        }
        else {
            return getSum(a^b, (a&b) << 1);
        }
    }
}
