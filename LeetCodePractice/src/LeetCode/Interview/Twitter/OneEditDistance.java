package LeetCode.Interview.Twitter;

/**
 * 161
 *
 * Given two strings S and T, determine if they are both one edit distance apart.
 *
 * Edit distance:
 * a) Insert a character
 * b) Delete a character
 * c) Replace a character
 *
 * Created by WinnieZhao on 4/10/2017.
 */
public class OneEditDistance {

    public boolean isOneEditDistance(String s, String t) {
        if (Math.abs(s.length() - t.length()) > 1) return false;

        // Replace one char
        if(s.length() == t.length()){
            int count =0;
            int i = 0;
            while(i < s.length()){
                if(s.charAt(i) != t.charAt(i)) count++;
                if(count > 1) break;
                i++;
            }
            return count == 1;
        }
        // Insert or Delete one char use the same logic
        else{
            return s.length() > t.length() ? isOneDelete(s, t) : isOneDelete(t,s);
        }
    }

    private boolean isOneDelete(String longer, String shorter){
        int i=0;
        for(; i< shorter.length(); i++){
            if(longer.charAt(i) != shorter.charAt(i)) {
                break;
            }
        }

        if (i == shorter.length()) return true;
        return shorter.substring(i, shorter.length()).equals(longer.substring(i+1, longer.length()));
    }

}
