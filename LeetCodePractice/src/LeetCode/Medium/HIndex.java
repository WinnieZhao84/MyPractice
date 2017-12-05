package LeetCode.Medium;

/**
 * Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute
 * the researcher's h-index.
 * 
 * According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least
 * h citations each, and the other N − h papers have no more than h citations each."
 * 
 * For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total and each of them had
 * received 3, 0, 6, 1, 5 citations respectively.
 * Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations
 * each, his h-index is 3.
 * 
 * Note: If there are several possible values for h, the maximum one is taken as the h-index.
 * 
 * Hint:
 * An easy approach is to sort the array first.
 * What are the possible values of h-index?
 * A faster approach is to use extra space.
 * 
 * @author WinnieZhao
 *
 */
public class HIndex {

    public int hIndex(int[] citations) {

        /**
         * 使用一个大小为 N+1 buckets。buckets[i]表示有多少文章被引用了i次，
         * 这里如果一篇文章引用大于N次，我们就将其当为N次，因为H指数不会超过文章的总数。
         * 为了构建这个数组，我们需要先将整个文献引用数组遍历一遍，对相应的格子加一。
         * 统计完后，我们从N向1开始遍历这个统计数组。如果遍历到某一个引用次数时，
         * 大于或等于该引用次数的文章数量，大于引用次数本身时，我们可以认为这是H指数。之所以不用再向下找，
         * 因为我们要取最大的H指数。那如何求大于或等于某个引用次数的文章数量呢？
         * 我们可以用一个变量，从高引用次的文章数累加下来
         *
         */
        if (citations == null || citations.length == 0) {
            return 0;
        }

        int len = citations.length;
        int[] buckets = new int[len+1];

        for (int citation : citations) {
            if (citation >= len) {
                buckets[len]++;
            }
            else {
                buckets[citation]++;
            }
        }

        int count = 0;
        for (int i=len; i>=0; i--) {
            count += buckets[i];
            if (count >= i) {
                return i;
            }
        }

        return 0;
    }
    
    public static void main(String[] args) {
        HIndex solution = new HIndex();
        int[] citations = {3, 0, 6, 1, 5};
        
        System.out.println(solution.hIndex(citations));
    }
}
