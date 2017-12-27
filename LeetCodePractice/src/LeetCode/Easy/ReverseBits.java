package LeetCode.Easy;

import java.util.HashMap;
import java.util.Map;

/**
 * Reverse bits of a given 32 bits unsigned integer.
 * 
 * For example, given input 43261596 (represented in binary as 00000010100101000001111010011100), 
 * return 964176192                  (represented in binary as 00111001011110000010100101000000).
 * 
 * Follow up:
 * If this function is called many times, how would you optimize it?
 * Related problem: Reverse Integer

 * @author ASUS-PC
 *
 */
public class ReverseBits {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
    	
    	int result = 0;
    	for (int i=0; i<32; i++) {
        	int bit = n & 1;
        	n = n >> 1;
    		result = result + bit;
    		// for last digit, don't shift!
    		if (i<31) {
        		result = result << 1;
    		}
    	}
    	return result;
    }

	/**
	 * How to optimize if this function is called multiple times?
	 * We can divide an int into 4 bytes, and reverse each byte then combine into an int.
	 * For each byte, we can use cache to improve performance.
	 *
	 */
	public class Solution {
		// you need treat n as an unsigned value
		Map<Integer, Integer> map = new HashMap<>();
		public int reverseBits(int n) {
			int res = 0;
			for(int i = 0; i < 4; i++){
				int tmp = n & 0xFF;
				if(map.containsKey(tmp)){
					res = (res << 8) + map.get(tmp);
				} else{
					res = (res << 8) + reverse8Bits(tmp);
				}
				n >>= 8;
			}

			return res;
		}

		private int reverse8Bits(int n){
			int res = 0;
			int tmp = n;
			for(int i = 0; i < 8; i++){
				res = (res << 1) + (tmp & 1);
				tmp >>= 1;
			}
			map.put(n, res);
			return res;
		}
	}

	/**
	 * for 8 bit binary number abcdefgh, the process is as follow:
	 * abcdefgh -> efghabcd -> ghefcdab -> hgfedcba
	 *
	 * Time Complexity:  O(log sizeof(int))
	 * @param n
	 * @return
	 */
	public int reverseBits_better(int n) {
		int ret=n;
		ret = ret >>> 16 | ret<<16;
		ret = (ret & 0xff00ff00) >>> 8 | (ret & 0x00ff00ff) << 8;
		ret = (ret & 0xf0f0f0f0) >>> 4 | (ret & 0x0f0f0f0f) << 4;
		ret = (ret & 0xcccccccc) >>> 2 | (ret & 0x33333333) << 2;
		ret = (ret & 0xaaaaaaaa) >>> 1 | (ret & 0x55555555) << 1;
		return ret;
	}

	public static void main(String[] args) {
		//11111111000000001111111100000000
		System.out.println(Integer.toBinaryString(0xff00ff00));
		//11110000111100001111000011110000
		System.out.println(Integer.toBinaryString(0x0f0f0f0f));
		//11001100110011001100110011001100
		System.out.println(Integer.toBinaryString(0xcccccccc));
		//11001100110011001100110011001100
		System.out.println(Integer.toBinaryString(0x33333333));
		//10101010101010101010101010101010
		System.out.println(Integer.toBinaryString(0x55555555));
	}
}
