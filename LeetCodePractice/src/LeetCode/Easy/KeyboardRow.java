package LeetCode.Easy;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Given a List of words, return the words that can be typed using letters of alphabet on only one row's of American keyboard like the image below.
 * 
 * Input: ["Hello", "Alaska", "Dad", "Peace"]
 * Output: ["Alaska", "Dad"]
 * 
 * Note:
 * You may use one character in the keyboard more than once.
 * You may assume the input string will only contain letters of alphabet.
 * 
 * @author WinnieZhao
 *
 */
public class KeyboardRow {

    //Stream.of(words).filter(s -> s.toLowerCase().matches("[qwertyuiop]*|[asdfghjkl]*|[zxcvbnm]*")).toArray(String[]::new) 
    public String[] findWords(String[] words) {
        String[] rules = {"qwertyuiop", "asdfghjkl", "zxcvbnm"};

        return Stream.of(words).filter(word -> this.isSameLine(rules, word)).toArray(String[]::new) ;
    }
    
    private boolean isSameLine(String[] rules, String word) {
        word = word.toLowerCase();
        
        boolean isSameLine = true;
        for (int i=0; i<3; i++) {
            String rule = rules[i];
            isSameLine = true;
            for (Character ch : word.toLowerCase().toCharArray()) {

                if (rule.indexOf(ch) < 0) {
                    isSameLine = false;
                    break;
                }
            }
            if (isSameLine) return true;
            
            if (i==2 && !isSameLine) {
                return false;
            }
        }
        
        return isSameLine;
    }
    
    public static void main(String[] args) {
        KeyboardRow solution = new KeyboardRow();
        
        String[] words = {"Hello","Alaska","Dad","Peace"};
        System.out.println(Arrays.toString(solution.findWords(words)));
    }
}
