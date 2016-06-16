package LeetCode.Easy;

/**
 * Compare two version numbers version1 and version2.
If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.

You may assume that the version strings are non-empty and contain only digits and the . character.
The . character does not represent a decimal point and is used to separate number sequences.
For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

Here is an example of version numbers ordering:

0.1 < 1.1 < 1.2 < 13.37
Credits:

 * @author WinnieZhao
 *
 */
public class CompareVersionNumbers {

    public int compareVersion(String version1, String version2) {
        String[] version1Array = version1.split("\\.");
        String[] version2Array = version2.split("\\.");
        
        int version1Length = version1Array.length;
        int version2Length = version2Array.length;
        
        int index1 = 0;
        int index2 = 0;
        while(index1 < version1Length && index2 < version2Length) {
            Integer value1 = Integer.valueOf(version1Array[index1]);
            Integer value2 = Integer.valueOf(version2Array[index2]);
            
            if (value1 > value2) {
                return 1;
            }
            else if (value1 < value2) {
                return -1;
            }
            else if (value1 == value2) {
                index1++;
                index2++;
            }
        }
        
        Integer value1 = Integer.valueOf(version1Array[version1Length-1]);
        Integer value2 = Integer.valueOf(version2Array[version2Length-1]);
        
        if (version1Length > version2Length && value1 > 0) {
            return 1;
        }
        else if (version1Length < version2Length && value2 > 0) {
            return -1;
        }

        return 0;
    }
    
    public static void main(String[] args) {
        CompareVersionNumbers solution = new CompareVersionNumbers();
        
        System.out.println(solution.compareVersion("1.0", "1"));
    }
    
}
