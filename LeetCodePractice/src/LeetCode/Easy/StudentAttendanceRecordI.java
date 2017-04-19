package LeetCode.Easy;

/**
 *
 * You are given a string representing an attendance record for a student. The record only contains the following three characters:
 * 'A' : Absent.
 * 'L' : Late.
 * 'P' : Present.
 *
 * A student could be rewarded if his attendance record doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).
 *
 * You need to return whether the student could be rewarded according to his attendance record.
 *
 * Example 1:
 * Input: "PPALLP"
 * Output: True
 *
 * Example 2:
 * Input: "PPALLL"
 * Output: False
 *
 *
 * Created by WinnieZhao on 4/18/2017.
 */
public class StudentAttendanceRecordI {

    public boolean checkRecord(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        int countA=0;
        int continuosL = 0;
        int charA = 'A';
        int charL ='L';
        for(int i=0;i<s.length();i++){
            if(s.charAt(i) == charA){
                countA++;
                continuosL = 0;
            }
            else if(s.charAt(i) == charL){
                continuosL++;
            }
            else{
                continuosL = 0;
            }
            if(countA >1 || continuosL > 2) {
                return false;
            }
        }
        return true;
    }
}
