package LeetCode.Easy;

/**
 * Given two binary strings, return their sum (also a binary string).
 * For example,
 * a = "11"
 * b = "1"
 * Return "100"
 * 
 * @author WinnieZhao
 *
 */
public class AddBinary {

    public String addBinary(String a, String b) {
        int aLength = a.length();
        int bLength = b.length();
        int length = 0;
                
        if (aLength <= bLength) {
            a = concatBinaryString(a, bLength - aLength);
            length = bLength;
        }
        else {
            b = concatBinaryString(b, aLength - bLength);
            length = aLength;
        }
        
        int carrier = 0;
        
        String sum = "";
        
        while(length > 0) {
            int total = 0;
            
            int aPos = a.charAt(length-1) - '0';
            int bPos = b.charAt(length-1) - '0';
            
            if (aPos + bPos + carrier >= 2) {
                total = aPos + bPos + carrier - 2;
                carrier = 1;
            }
            else {
                total = aPos + bPos + carrier;
                carrier = 0;
            }
            
            sum = total + sum;
            
            if (length == 1 && carrier == 1) {
                sum = carrier + sum;
            }
            
            length--;
        }
        
        return sum;
    }
    
    private String concatBinaryString(String str, int length) {
        for (int i=0; i<=length-1; i++) {
            str = '0' + str;
        }
        return str;
    }
    
    public static void main(String[] args) {
        AddBinary solution = new AddBinary();
        
        System.out.println(solution.addBinary("100","11"));
        
        System.out.println(solution.addBinary("0","0"));
    }
    
}
