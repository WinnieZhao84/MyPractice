package LeetCode.Easy;

/**
 * Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
 * For example:
 * Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.
 * 
 * Follow up:
 * Could you do it without any loop/recursion in O(1) runtime?

 * @author ASUS-PC
 *
 */
public class AddDigits {

    public int addDigits(int num) {
        return this.addDigitHelper(num);
    }
    
    private int addDigitHelper(int num) {
    	if (num <= 9 && num >=0) {
    		return num;
    	}
    	
    	int result = 0;
    	while (num > 0) {
    		result += num % 10;
    		num = num/10;
    	}
    	return addDigitHelper(result);
    }
    
    /**
     * O(1) solution
     * @param num
     * @return
     */
    public int addDigits_Better(int num) {
        if (num == 0){
            return 0;
        }
        if (num % 9 == 0){
            return 9;
        }
        else {
            return num % 9;
        }
    }
    
    public static void main(String[] args) {
    	AddDigits solution = new AddDigits();
    	System.out.print(solution.addDigits(38));
    }
}
