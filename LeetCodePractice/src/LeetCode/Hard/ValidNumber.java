package LeetCode.Hard;

/**
 * Validate if a given string is numeric.
 *
 * Some examples:
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 *
 * Note: It is intended for the problem statement to be ambiguous.
 * You should gather all requirements up front before implementing one.

 * Created by WinnieZhao on 2017/6/20.
 */
public class ValidNumber {

    /**
     * If we see [0-9] we reset the number flags.
     * We can only see . if we didn't see e or ..
     * We can only see e if we didn't see e but we did see a number. We reset numberAfterE flag.
     * We can only see + and - in the beginning and after an e
     * any other character break the validation.
     * At the end it is only valid if there was at least 1 number and if we did see an e then a number after it as well.

     * @param s
     * @return
     */
    public boolean isNumber(String s) {
        if (s == null) {
            return false;
        }

        s = s.trim();

        if (s.isEmpty()) {
            return false;
        }

        boolean pointSeen = false;
        boolean eSeen = false;
        boolean numberSeen = false;
        boolean numberAfterE = true;

        for (int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);

            if (ch >= '0' && ch <= '9') {
                numberSeen = true;
                numberAfterE = true;
            }
            else if (ch  == '.') {
                if (pointSeen || eSeen) {
                    return false;
                }
                pointSeen = true;
            }
            else if (ch == 'e') {
                if (eSeen || !numberSeen) {
                    return false;
                }
                numberAfterE = false;
                eSeen = true;
            }
            else if (ch == '+' || ch == '-') {
                if (i != 0 && s.charAt(i-1) != 'e') {
                    return false;
                }
            }
            else {
                return false;
            }
        }

        return numberSeen && numberAfterE;
    }
}
