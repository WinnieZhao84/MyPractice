package LeetCode.Easy;

/**
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 * 
 * For example,
 * "A man, a plan, a canal: Panama" is a palindrome.
 * 
 * "race a car" is not a palindrome.
 * 
 * 
 * Note:
 * Have you consider that the string might be empty? This is a good question to ask during an interview.
 * For the purpose of this problem, we define empty string as valid palindrome.

 * @author ASUS-PC
 *
 */
public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        if (s == null) {
            return false;
        }
        if (s.isEmpty()) {
            return true;
        }

        int i=0;
        int j=s.length()-1;
        while (i<=j) {

            boolean letterFromLeft = (s.charAt(i) <='z' && s.charAt(i)>='a') || (s.charAt(i) <='Z' && s.charAt(i)>='A')
                    || (s.charAt(i) <='9' && s.charAt(i)>='0');
            boolean letterFromRight = (s.charAt(j) <='z' && s.charAt(j)>='a') || (s.charAt(j) <= 'Z' && s.charAt(j)>='A')
                    || (s.charAt(j) <='9' && s.charAt(j)>='0');

            if (letterFromLeft && letterFromRight) {

                if (String.valueOf(s.charAt(i)).equalsIgnoreCase(String.valueOf(s.charAt(j)))) {
                    i++;
                    j--;
                }
                else {
                    return false;
                }
            }
            else if (!letterFromRight) {
                j--;
            }
            else if (!letterFromLeft) {
                i++;
            }
        }

        return true;
    }
    
    public static void main(String[] args) {
    	ValidPalindrome solution = new ValidPalindrome();
    	//System.out.println(solution.isPalindrome("A man, a plan, a canal: Panama"));
    	//System.out.println(solution.isPalindrome("race a car"));
    	System.out.println(solution.isPalindrome("1a2"));
    }
}
