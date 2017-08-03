package LeetCode.Medium;

import java.util.ArrayList;
import java.util.List;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: 
 * (you may want to display this pattern in a fixed font for better legibility)
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 
 * And then read line by line: "PAHNAPLSIIGYIR"
 * Write the code that will take a string and make this conversion given a number of rows:
 * string convert(string text, int nRows);
 * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 * 
 * @author ASUS-PC
 *
 */
public class ZigZagConversion {
    public String convert(String s, int numRows) {
        String result = "";
        if (s == null) {
        	return null;
        }
        if (numRows <=1) {
        	return s;
        }
    	List<List<String>> list = new ArrayList<List<String>>();
    	for (int num=0; num<numRows; num++) {
    		List<String> l = new ArrayList<String>();
    		list.add(l);
    	}
    	int index=-1;
    	int direction = 1;
    	for (int i=0; i<s.length(); i++) {
    		if (index == numRows-1) {
    			direction = -1;
    		}
    		else if (index == 0) {
    			direction = +1;
    		}
    		
    		if (direction == 1) {
    			index++;
    		}
    		else if (direction == -1) {
    			index--;
    		}
    		
    		list.get(index).add(String.valueOf(s.charAt(i)));
    	}
    	
    	for (int i=0; i<numRows; i++) {
    		for (String str : list.get(i)) {
    			result += str;
    		}
    	}
        
        return result;
    }

    public static void main(String[] args) {
    	ZigZagConversion solution = new ZigZagConversion();
    	System.out.print(solution.convert("PAYPALISHIRING",1));
    }
}
