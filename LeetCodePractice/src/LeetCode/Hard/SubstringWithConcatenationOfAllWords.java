package LeetCode.Hard;

import java.util.*;

/**
 * You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s)
 * in s that is a concatenation of each word in words exactly once and without any intervening characters.
 *
 * For example, given: s: "barfoothefoobarman"
 * words: ["foo", "bar"]
 *
 * You should return the indices: [0,9].
 * (order does not matter).

 * Created by WinnieZhao on 2017/6/20.
 */
public class SubstringWithConcatenationOfAllWords {

    /**
     * Say in words there are m strings with length n.
     * What string is required to match in S? A length of m*n string start with each position in S.
     * What is a match? In the m*n long string, every string in words[] appear only once.
     *
     * So the algorithm is:
     * Scan every m*n long string start from each position in S, see if all the strings in words have been appeared only once
     * using Map data structure. If so, store the starting position.

     * @param s
     * @param words
     * @return
     */
    //TODO Time limit exceed
    public List<Integer> findSubstring(String s, String[] words) {
        if (s == null || s.isEmpty() || words == null || words.length == 0) {
            return new ArrayList<>();
        }

        List<Integer> res = new ArrayList<>();
        int size = words.length;
        int length = words[0].length();

        if (s.length() < size * length) {
            return new ArrayList<>();
        }

        Map<String,Integer> covered = new HashMap<>();

        for (int j=0; j<size; j++) {
            if (s.indexOf(words[j]) < 0) {
                return res;
            }

            covered.compute(words[j], (k, v) -> v != null ? covered.get(k)+1 : 1);
        }

        int i=0;
        int sLength = s.length();
        while(sLength -i >= size * length){
            Map<String, Integer> temp = new HashMap<>(covered);

            for (int j=0; j<words.length; j++){
                String testStr = s.substring(i + j*length, i + (j+1)*length);

                if (temp.containsKey(testStr)){
                    if (temp.get(testStr) == 1)
                        temp.remove(testStr);
                    else
                        temp.put(testStr, temp.get(testStr)-1);
                }
                else {
                    break;
                }
            }

            if (temp.size() == 0) {
                res.add(i);
            }

            i++;
        }
        return res;
    }

    public static void main(String[] args) {
        SubstringWithConcatenationOfAllWords solution = new SubstringWithConcatenationOfAllWords();

        /**
         * "barfoofoobarthefoobarman"
         ["bar","foo","the"]
         */
        String[] words = {"bar","foo","the"};
        System.out.println(solution.findSubstring("barfoofoobarthefoobarman", words).toString());
    }
}
