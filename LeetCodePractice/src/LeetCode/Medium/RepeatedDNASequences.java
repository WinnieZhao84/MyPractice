package LeetCode.Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". 
 * When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
 * 
 * Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
 * 
 * For example, Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",
 * Return: ["AAAAACCCCC", "CCCCCAAAAA"].

 * @author WinnieZhao
 *
 */
public class RepeatedDNASequences {

    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<String>();
        if (s == null || s.isEmpty()) {
            return res;
        }
        
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i=0; i<s.length(); i++) {
            if (i+10 > s.length()) {
                break;
            }
            
            String subStr = s.substring(i, i+10);
            
            if (map.containsKey(subStr)) {
                map.put(subStr, map.get(subStr) + 1);
            }
            else {
                map.put(subStr, 1);
            }
        }
        
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            Integer count = entry.getValue();
            if (count > 1) {
                res.add(key);
            }
        }
        
        return res;
    }

    /**
     * So we have only 4 possible letters, and we can use as little bits as possible to store
     * each character of our 10-letter string. We really need only 2 bits (bits, not bytes)
     * for this. Specifically the solution uses the following coding:
     * 0 = 00 (bits in binary number system) = 'A'
     * 1 = 01 (bits in binary number system) = 'C'
     * 2 = 10 (bits in binary number system) = 'G'
     * 3 = 11 (bits in binary number system) = 'T'
     *
     * Note that since there 10 letters and each letter requires only 2 bits,
     * we will need only 10 * 2= 20 bits to code the string (which is less then
     * size of integer in java (as well as in all othere popular languages),
     * which is 4 bytes = 32 bits).
     *
     * For example, this is how "AACCTCCGGT" string will be coded:
     * A A C C T C C G G T
     * 00 00 01 01 11 01 01 10 10 11 = 00000101110101101011 (binary) = 23915 (decimal)

     * @param s
     * @return
     */
    public List<String> findRepeatedDnaSequences_better(String s) {
        Set<Integer> words = new HashSet<>();
        Set<Integer> doubleWords = new HashSet<>();
        List<String> rv = new ArrayList<>();
        char[] map = new char[26];
        //map['A' - 'A'] = 0;
        map['C' - 'A'] = 1;
        map['G' - 'A'] = 2;
        map['T' - 'A'] = 3;

        for(int i = 0; i < s.length() - 9; i++) {
            int v = 0;
            for(int j = i; j < i + 10; j++) {
                v <<= 2;
                v |= map[s.charAt(j) - 'A'];
            }
            if(!words.add(v) && doubleWords.add(v)) {
                rv.add(s.substring(i, i + 10));
            }
        }
        return rv;
    }
    
    public static void main(String[] args) {
        RepeatedDNASequences solution = new RepeatedDNASequences();
        
        System.out.println(solution.findRepeatedDnaSequences_better("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
    }
}
