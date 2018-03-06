package LeetCode.Easy;


/**
 * Write a function that takes an unsigned integer 
 * and returns the number of '1' bits it has (also known as the Hamming weight).
 * 
 * For example, the 32-bit integer '11' has binary representation 00000000000000000000000000001011,
 * so the function should return 3.
 * @author ASUS-PC
 *
 */
public class NumberOfOneBits {
    public int hammingWeight(int n) {
    	int count = 0;

        long longn = Integer.toUnsignedLong(n);

        while (longn > 0)
        {
            if (longn % 2 == 1)
                count++;

            longn = longn / 2;
        }

        return count;
    }
    
    public int hammingWeight_solution(int n) {
        int count = 0;
        while (n != 0) {

            count += (n & 1);
            n = n >>> 1;
        }

        return count;
    } 
    
    public static void main(String[] args) {
    	NumberOfOneBits solution = new NumberOfOneBits();
    	
    	int weight = solution.hammingWeight(214);
    	
        System.out.println(weight);

    }
}
