package LeetCode.Interview.Microsoft.OA;

public class ValidatePalindromic {

    public boolean isPalindromic(String s, boolean ignoreWhiteSpace, boolean ignoreCase) {

        if (ignoreCase) {
            s = s.toLowerCase();
        }

        return this.isPalindromicHelper(s, ignoreWhiteSpace);
    }

    private boolean isPalindromicHelper(String s, boolean ignoreWhiteSpace) {
        int i=0;
        int j=s.length()-1;

        while (i<j) {
            if (ignoreWhiteSpace && s.charAt(i) == ' ') {
                i++;
                continue;
            }
            if (ignoreWhiteSpace && s.charAt(j) == ' ') {
                j--;
                continue;
            }

            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }

    public static void main(String[] args) {
        ValidatePalindromic solution = new ValidatePalindromic();

        System.out.println(solution.isPalindromic("abbba", true, true));
        System.out.println(solution.isPalindromic("abBBA", true, true));
        System.out.println(solution.isPalindromic("abBba", false, false));
        System.out.println(solution.isPalindromic("abB  ba", true, false));
    }
}
