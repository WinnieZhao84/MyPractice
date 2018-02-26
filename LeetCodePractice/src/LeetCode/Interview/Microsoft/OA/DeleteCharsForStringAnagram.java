package LeetCode.Interview.Microsoft.OA;

/**
 * 然后是给两个string，问一共删除多少个字符可以让这两个string变成anagram，大小写视为相同， 返回一共要删除的字符总数
 *
 * Created by WinnieZhao on 2/24/2018.
 */
public class DeleteCharsForStringAnagram {

    public int deleteCharsForStringAnagram(String a, String b) {
        if (a == null || b == null) {
            return -1;
        }
        if (a.isEmpty() || b.isEmpty()) {
            return a.isEmpty() ? b.length() : a.length();
        }

        a = a.toLowerCase();
        b = b.toLowerCase();

        int[] countA = new int[26];
        int[] countB = new int[26];

        for (char ch : a.toCharArray()) {
            countA[ch - 'a']++;
        }

        for (char ch : b.toCharArray()) {
            countB[ch - 'a']++;
        }

        int res = 0;
        for (int i=0; i<26; i++) {
            res += Math.abs(countA[i] - countB[i]);
        }

        return res;
    }

    public static void main(String[] args) {
        DeleteCharsForStringAnagram solution = new DeleteCharsForStringAnagram();

        System.out.println(solution.deleteCharsForStringAnagram("abc", "cba"));
        System.out.println(solution.deleteCharsForStringAnagram("abccca", "cbccca"));
        System.out.println(solution.deleteCharsForStringAnagram("abccca", "cccccc"));
    }
}
