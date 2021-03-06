package LeetCode.Interview.Facebook;

/**
 * 157
 *
 * The API: int read4(char *buf) reads 4 characters at a time from a file.
 * The return value is the actual number of characters read.
 *
 * For example, it returns 3 if there is only 3 characters left in the file.
 * By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
 *
 * Note: The read function will only be called once for each test case.
 *
 * Created by WinnieZhao on 2/3/2018.
 */
public class ReadNCharactersGivenRead4 {
    /**
     *  The read4 API is defined in the parent class Reader4.
     *  int read4(char[] buf);
     **/
    int read4(char[] chars) {
        return 0;
    }

    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        int index =0;
        char[] r4 = new char[4];

        while(index < n){
            int c = read4(r4);
            for(int i=0; i<c && index < n; i++){
                buf[index++] = r4[i];
            }
            if(c < 4) break;
        }

        return index;
    }
}
