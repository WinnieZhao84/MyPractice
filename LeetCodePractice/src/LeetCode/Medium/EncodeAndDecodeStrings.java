package LeetCode.Medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 271
 *
 * Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is
 * decoded back to the original list of strings.
 *
 * Machine 1 (sender) has the function:
 *
 * string encode(vector<string> strs) {
 *    // ... your code
 *
 *    return encoded_string;
 * }
 *
 * Machine 2 (receiver) has the function:
 *
 * vector<string> decode(string s) {
 *     //... your code
 *    return strs;
 * }
 *
 * So Machine 1 does: string encoded_string = encode(strs);
 * and Machine 2 does: vector<string> strs2 = decode(encoded_string);
 *
 * strs2 in Machine 2 should be the same as strs in Machine 1.

 * Created by WinnieZhao on 4/12/2017.
 */
public class EncodeAndDecodeStrings {

    // Your Codec object will be instantiated and called as such:
    // Codec codec = new Codec();
    // codec.decode(codec.encode(strs));
    // Encodes a list of strings to a single string.

    public String encode(List<String> strs) {

        StringBuilder sb = new StringBuilder();
        for(String s : strs){
            sb.append(s.length());
            sb.append("#");
            sb.append(s);
        }

        return sb.toString();

    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {

        List<String> res = new ArrayList<>();
        int k = 0;

        while(k < s.length()){
            int pound = s.indexOf("#", k);
            String str = s.substring(k, pound);
            int size = Integer.parseInt(str);
            res.add(s.substring(pound+1, pound+1+size));
            k = pound+1+size;
        }

        return res;

    }

    public static void main(String[] args) {
        List<String> strs = new ArrayList<>();
        strs.add("abc");
        strs.add("defg");

        EncodeAndDecodeStrings solution = new EncodeAndDecodeStrings();

        System.out.println(solution.encode(strs));
        System.out.println(solution.decode(solution.encode(strs)));
    }

}
