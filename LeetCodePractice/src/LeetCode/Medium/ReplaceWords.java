package LeetCode.Medium;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * In English, we have a concept called root, which can be followed by some other words to form another longer word -
 * let's call this word successor. For example, the root an, followed by other, which can form another word another.
 *
 * Now, given a dictionary consisting of many roots and a sentence. You need to replace all the successor in the sentence
 * with the root forming it. If a successor has many roots can form it, replace it with the root with the shortest length.
 *
 * You need to output the sentence after the replacement.
 *
 * Example 1:
 * Input: dict = ["cat", "bat", "rat"]
 * sentence = "the cattle was rattled by the battery"
 * Output: "the cat was rat by the bat"
 *
 * Note:
 * The input will only have lower-case letters.
 * 1 <= dict words number <= 1000
 * 1 <= sentence words number <= 1000
 * 1 <= root length <= 100
 * 1 <= sentence words length <= 1000
 *
 * Created by WinnieZhao on 2017/7/23.
 */
public class ReplaceWords {

    public String replaceWords(List<String> dict, String sentence) {
        dict.sort(Comparator.comparing(String::length));

        String[] array = sentence.split(" ");
        StringBuilder res = new StringBuilder();

        for (int i=0; i<array.length; i++) {
            String str = array[i];

            Optional<String> optional = this.replace(str, dict);
            if (optional.isPresent()) {
                res.append(optional.get());
            }
            else {
                res.append(str);
            }
            if (i != array.length-1) {
                res.append(" ");
            }
        }

        return res.toString();
    }

    private Optional<String> replace (String s, List<String> dicts) {
        return  dicts.stream().filter(dict -> s.length() >= dict.length() && s.substring(0, dict.length()).equals(dict)).findFirst();
    }

    public static void main(String[] args) {
        ReplaceWords solution = new ReplaceWords();

        List<String> dict = new ArrayList<>();
        dict.add("cat");
        dict.add("bat");
        dict.add("rat");

        System.out.println(solution.replaceWords(dict, "the cattle was rattled by the battery"));
    }
}
