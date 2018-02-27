package LeetCode.Interview.Microsoft.OA;

/**
 * "aaaabbbbcccd" to "a4b4c3d1", encode & decode
 *
 * Created by WinnieZhao on 2/25/2018.
 */
public class EncodeAndDecodeString {

    public String encode(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuilder sb = new StringBuilder();

        char pre = ' ';
        int count = 1;
        for (int i=0; i<input.length(); i++) {
            char ch = input.charAt(i);

            if (ch >= 'a' && ch <= 'z') {
                if (pre == ' ') {
                    pre = ch;
                    continue;
                }

                if (pre != ch) {
                    sb.append(pre);
                    sb.append(count);

                    pre = ch;
                    count = 1;
                }
                else {
                    count++;
                }
            }
            else {
                throw new IllegalArgumentException("Invalid Input String");
            }
        }

        sb.append(pre);
        sb.append(count);

        return sb.toString();
    }

    public String decode(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuilder sb = new StringBuilder();

        int count = 0;
        char pre = ' ';
        for (int i=0; i<input.length(); i++) {
            char ch = input.charAt(i);

            if (ch >= '1' && ch <= '9') {
                count += count*10 + (ch-'0');
            }
            else if (ch >= 'a' && ch <= 'z') {
                if (pre == ' ') {
                    pre = ch;
                    continue;
                }
                if (pre != ch) {
                    while (count > 0) {
                        sb.append(pre);
                        count--;
                    }
                    pre = ch;
                }
            }
        }

        while(count-- > 0) {
            sb.append(pre);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        EncodeAndDecodeString solution = new EncodeAndDecodeString();

        System.out.println(solution.encode("aaaabbbbcccdd"));
        System.out.println(solution.decode("a4b4c3d2"));
    }
}
