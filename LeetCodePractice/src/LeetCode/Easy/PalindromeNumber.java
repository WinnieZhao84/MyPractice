package LeetCode.Easy;

/**
 * Determine whether an integer is a palindrome. Do this without extra space.
 * 
 * Could negative integers be palindromes? (ie, -1)

If you are thinking of converting the integer to string, note the restriction of using extra space.

You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", 
you know that the reversed integer might overflow. How would you handle such case?

There is a more generic way of solving this problem.

 * @author WinnieZhao
 *
 */
public class PalindromeNumber {

    public boolean isPalindrome(int x) {
        
    	if (x<=9 && x>=0) return true;
    	String s = String.valueOf(x);
    	int length = s.length();

    	int end = -1;
    	if (length % 2 == 0) {
    		end = length / 2;
    	}
    	else {
    		end = length / 2 + 1;
    	}
    	
    	int i=0;
    	int j = length-1;
    	while (i < length / 2 && j >= end) {
    		if (s.charAt(i) != s.charAt(j)) {
    			return false;
    		}
    		else if (s.charAt(i) == s.charAt(j) && i == length/2-1 && j==end) {
    			return true;
    		}
			i++;
			j--;
    	}
    	return false;
    }
    
    public boolean isPalindromeBetter(int x) {
    	return x == reverse(x);
    }
    
    private int reverse(int x) {
    	int rst = 0;
    	while (x != 0) {
    		rst = rst * 10 + x % 10;
    		x = x/10;
    	}
    	
    	return rst;
    }
    
    public static void main(String[] args) {
    	PalindromeNumber solution = new PalindromeNumber();
        
        System.out.println(solution.isPalindromeBetter(232));
    }
}
