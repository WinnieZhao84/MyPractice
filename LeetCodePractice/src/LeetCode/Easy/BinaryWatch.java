package LeetCode.Easy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).
 * Each LED represents a zero or one, with the least significant bit on the right.
 * 
 * For example, the above binary watch reads "3:25".
 * Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.
 * 
 * Example:
 * 
 * Input: n = 1
 * Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 * 
 * Note: 
 * The order of output does not matter.
 * The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
 * The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".
 * 
 * @author WinnieZhao
 *
 */
public class BinaryWatch {

    public List<String> readBinaryWatch(int num) {
        
        if (num > 10) {
            return null;
        }
        
        List<String> result = new ArrayList<>();
        int[] top = {1, 2, 4, 8};
        int[] bottom = {1, 2, 4, 8, 16, 32};
        
        for (int i=0; i<=num; i++) {
            Set<Integer> topResult = new HashSet<>();
            Set<Integer> bottomResult = new HashSet<>();
            
            this.readBinaryWatchHelper(topResult, 0, top, 0, i);
            this.readBinaryWatchHelper(bottomResult, 0, bottom, 0, num-i);
            
            String str = "";
            for (Integer topNum : topResult) {
                if (topNum > 11) continue;
                for (Integer bottomNum : bottomResult) {
                    if (bottomNum > 59) continue;
                    
                    String bottomStr = String.valueOf(bottomNum);
                    if (bottomStr.length() == 1) {
                        bottomStr = "0" + bottomStr;
                    }
                    str = String.valueOf(topNum) + ":" + bottomStr;
                    
                    result.add(str);
                }
            }
        }
        
        return result;
    }
    
    private void readBinaryWatchHelper(Set<Integer> result, int value, int[] array, int start, Integer count) {
        if (count < 0) return;
        if (count == 0) {
            result.add(value);
            return;
        }
        
        for (int i=start; i<array.length; i++) {
            value += array[i];
            
            this.readBinaryWatchHelper(result, value, array, i+1, count-1);
            
            value = value - array[i];
        }
    }
    
    public List<String> readBinaryWatch_better(int num) {
        List<String> result = new ArrayList<String>();
     
        for(int i=0; i<12; i++){
            for(int j=0; j<60; j++){
                int total = countDigits(i)+countDigits(j);
                if(total==num){
                    String s="";
                    s+=i+":";
     
                    if(j<10){
                        s+="0"+j;
                    }else{
                        s+=j;
                    }
     
                    result.add(s);
                }
            }
        }
     
        return result;
    }
     
    public int countDigits(int num){
        int result=0;
     
        while(num>0){
            if((num&1)==1){
                result++;
            }
     
            num>>=1;
        }
     
        return result;
    }
    
    public static void main(String[] args) {
        BinaryWatch solution = new BinaryWatch();
        System.out.println(solution.readBinaryWatch(2));
    }
}
