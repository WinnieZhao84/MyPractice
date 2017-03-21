package LeetCode.Medium;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * Note: This is a companion problem to the System Design problem: Design TinyURL.
 * TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl
 * and it returns a short URL such as http://tinyurl.com/4e9iAk.
 *
 * Design the encode and decode methods for the TinyURL service. There is no restriction on how your encode/decode
 * algorithm should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be
 * decoded to the original URL.
 *
 * Subscribe to see which companies asked this question.
 *
 * Created by WinnieZhao on 2017/3/20.
 */

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));
public class EncodeAndDecodeTinyURL {

    String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    Map<String, String> code2url = new HashMap<>();
    Map<String, String> url2code = new HashMap<>();
    Random rand = new Random();

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        while (!url2code.containsKey(longUrl)) {
            // generate code
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 6; i++) {
                sb.append(alphabet.charAt(rand.nextInt(62)));
            }
            // put code-url pair
            String code = sb.toString();
            if (!code2url.containsKey(code)) {
                code2url.put(code, longUrl);
                url2code.put(longUrl, code);
            }
        }

        return url2code.get(longUrl);
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return code2url.containsKey(shortUrl) ? code2url.get(shortUrl) : "";
    }
}
