package LeetCode.Medium;

/**
 * Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. 
 * You may assume that each word will contain only lower case letters. If no such two words exist, return 0.
 * 
 * Example 1:
 * 
 * Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
 * Return 16
 * The two words can be "abcw", "xtfn".
 * 
 * 
 * Example 2:
 * 
 * Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
 * Return 4
 * The two words can be "ab", "cd".
 * 
 * 
 * Example 3:
 * 
 * Given ["a", "aa", "aaa", "aaaa"]
 * Return 0
 * No such pair of words.

 * @author WinnieZhao
 *
 */
public class MaximumProductOfWordLengths {

    public int maxProduct(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }
        
        int length = words.length;
        int[] mark = new int[length];
        for (int i=0; i<length; i++) {
            String word = words[i];
            
            /**
             * Using Bit manipulation:
             * 1 << 0 (when char='a'): 1 move left 0 position = 1
             * 1 << 1 (when char='b'): 1 move left 1 position = 10 = 2
             */
            for (int j=0; j<word.length(); j++) {
                char w = word.charAt(j);
                
                int digit = w - 'a';
                digit = 1 << digit;
                mark[i] = mark[i] | digit;
            }
        }
        
        int maxProduct = 0;
        for (int i=0; i<length; i++) {
            for (int j=i+1; j<length; j++) {
                if ((mark[i] & mark[j]) == 0 && (words[i].length() * words[j].length() > maxProduct)) {
                    maxProduct = words[i].length() * words[j].length();
                }
            }
        }
        
        return maxProduct;
    }
    
    public static void main(String[] args) {
        MaximumProductOfWordLengths solution = new MaximumProductOfWordLengths();
        String[] words = {"ab", "abd"};
        System.out.println(solution.maxProduct(words));
    }
}
