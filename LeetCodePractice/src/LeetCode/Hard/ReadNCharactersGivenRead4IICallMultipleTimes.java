package LeetCode.Hard;

/**
 * 158
 *
 * The API: int read4(char *buf) reads 4 characters at a time from a file.
 *
 * The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
 *
 * By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
 *
 * Note: The read function may be called multiple times.

 * Created by WinnieZhao on 2017/7/12.
 */
public class ReadNCharactersGivenRead4IICallMultipleTimes {

    /* The read4 API is defined in the parent class Reader4. int read4(char[] buf); */
    int read4(char[] chars) {
        return 0;
    }

    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    private int buffPtr = 0;
    private int buffCnt = 0;
    private char[] buff = new char[4];

    public int read(char[] buf, int n) {
        /**
         * The difference between this question and the first version is that the read() function will be called multiple times.
         * The trouble here will be as the following example if using the first version solution: file: “abcdefg”
         *
         * read(3) read(2) read(2) should be “abc” “de” “fg”
         * but using first version solution it will print “abc” “ef” “”
         * This is because when you use read4() to read, the pointer to read file has already moved to “e” after the
         * first call of read4().
         *
         * So it’s not correct any more
         * Use buffer pointer (buffPtr) and buffer Counter (buffCnt) to store the data received in previous calls.
         *
         * In the while loop, if buffPtr reaches current buffCnt, it will be set as zero to be ready to read new data.
         */
        int ptr = 0;
        while (ptr < n) {
            if (buffPtr == 0) {
                buffCnt = read4(buff);
            }

            if (buffCnt == 0) {
                break;
            }

            while (ptr < n && buffPtr < buffCnt) {
                buf[ptr++] = buff[buffPtr++];
            }
            if (buffPtr >= buffCnt) {
                buffPtr = 0;
            }
        }
        return ptr;
    }
}
