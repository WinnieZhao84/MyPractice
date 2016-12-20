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
