package LeetCode.Medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 * 
 * For example:
 * Given "25525511135",
 * 
 * return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)

 * @author WinnieZhao
 *
 */
public class RestoreIPAddresses {
    
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<String>();
        int len = s.length();
        
        for(int i = 1; i<4 && i<len-2; i++) {
            
            for(int j = i+1; j<i+4 && j<len-1; j++) {
                
                for(int k = j+1; k<j+4 && k<len; k++) {
                    String s1 = s.substring(0,i);
                    String s2 = s.substring(i,j);
                    String s3 = s.substring(j,k);
                    String s4 = s.substring(k,len);
                    
                    if (isValid(s1) && isValid(s2) && isValid(s3) && isValid(s4)) {
                        res.add(s1+"."+s2+"."+s3+"."+s4);
                    }
                }
            }
        }
        return res;
    }
    
    private boolean isValid(String s){
        if (s.length()>3 || s.length()==0 || (s.charAt(0)=='0' && s.length()>1) || Integer.parseInt(s)>255) {
            return false;
        }
        return true;
    }
    
    public List<String> restoreIpAddresses_backtracking(String s) {
        List<String> result = new ArrayList<>();
        doRestore(result, "", s, 0);
        return result;
    }
    
    private void doRestore(List<String> result, String path, String s, int k) {
        if (s.isEmpty() || k == 4) {
            if (s.isEmpty() && k == 4) {
                result.add(path.substring(1));
            }
            return;
        }
        for (int i = 1; i <= 3 && i <= s.length(); i++) { // Avoid leading 0
            String part = s.substring(0, i);
            if (this.isValid(part)) {
                doRestore(result, path + "." + part, s.substring(i), k + 1);
            }
        }
    }
    
    public static void main(String[] args) {
        RestoreIPAddresses solution = new RestoreIPAddresses();
        
        System.out.println(solution.restoreIpAddresses_backtracking("25525511135"));
    }
}
