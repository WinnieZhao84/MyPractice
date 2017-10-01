package LeetCode.Interview.Amazon.LevelOne;

/**
 * Created by WinnieZhao on 9/30/2017.
 */
public class RemoveVowel {
    public static String removeVowel(String s) {
        String t = "aeiouAEIOU";
        StringBuilder sb = new StringBuilder();

        for(char c: s.toCharArray()) {
            if(t.indexOf(c) >= 0) continue;
            sb.append(c);
        }
        return sb.toString();
    }
}
