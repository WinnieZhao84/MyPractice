package LeetCode.Medium;

/**
 * Additive number is a string whose digits can form additive sequence.
 * 
 * A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.
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

    public boolean isAdditiveNumber(String num) {
        if (num == null || num.length() <=1) {
            return false;
        }
        int length = num.length();
        for (int i=0; i<length; i++) {
            for (int j = i + 1; j < length - i - 1; j++) {
                
                String first = num.substring(0, i + 1);  
                String second = num.substring(i + 1, j + 1);  
                
                if(isValid(j + 1, num, first, second)) {
                    return true; 
                }
            }
        }
        
        return false;
    }
    
    private boolean isValid (int start, String num, String first, String second) {
        if (start == num.length()) {
            return true;
        }

        Long f = Long.valueOf(first);
        Long s = Long.valueOf(second);
        
        // The number can't be leading zeros
        if(!Long.toString(f).equals(first) || !Long.toString(s).equals(second)) {
            return false;
        }
        Long sum = f + s;
        String sumStr = Long.toString(sum);  
        if (start + sumStr.length() > num.length()) {
            return false;
        }
        String third = num.substring(start, start + sumStr.length());  
        long t = Long.parseLong(third); 
        
        if (!Long.toString(t).equals(third) || t != sum) {
            return false;
        }
        start = start + sumStr.length();
        return this.isValid(start, num, second, third);
    }
    
    public static void main(String[] args) {
        AdditiveNumber solution = new AdditiveNumber();
        System.out.println(solution.isAdditiveNumber("1023"));
    }
}
