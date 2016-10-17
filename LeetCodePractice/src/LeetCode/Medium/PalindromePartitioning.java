package LeetCode.Medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * 
 * Return all possible palindrome partitioning of s.
 * 
 * For example, given s = "aab",
 * 
 * Return
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 * 
 * @author WinnieZhao
 *
 */
public class PalindromePartitioning {

    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<List<String>>();
        List<String> list = new ArrayList<String>();

        this.partitionHelper(result, list, s, 0);
        
        return result;
    }
    
    private void partitionHelper(List<List<String>> result, List<String> list, String s, int pos) {
        if (pos==s.length()) {
            result.add(new ArrayList<String>(list));
        }
        
        for(int i=pos; i<s.length(); i++){
            if (isPalindrome(s, pos, i)){
                list.add(s.substring(pos, i+1));
                
                this.partitionHelper(result, list, s, i+1);
                
                list.remove(list.size()-1);
            }
        }
        
        
    }
    
    public boolean isPalindrome(String s, int low, int high){
        while (low<high) {
            if ( s.charAt(low++)  != s.charAt(high--)) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        PalindromePartitioning solution = new PalindromePartitioning();
        
        List<List<String>> result = solution.partition("aaba");
        
        result.forEach(list -> System.out.println(list));
    }
}
