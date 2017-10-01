package LeetCode.Interview.Amazon.LevelTwo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给了一个codeList， 里面装的是他买的水果，给了一个shoppingCart里面装的是target水果，
 * 目标是检查codelist里的水果顺序是否和shoppingCart里的顺序匹配。
 *
 * 注意的是只有codelist中的所有链表的item之后加起来小于等于shoppingcart里item之和才可能返回1。 另
 * 外在codelist中有可能出现item时 "anything"， 它可以和任意的水果匹配。
 *
 * Ex1:
 * codelist:
 * [ [apple, apple],
 *   [orange, banana, orange]
 * ]
 * shoppingCart: [orange, apple, apple, orange, banana, orange]
 * return 1, 因为codelist里的顺序和shoppingcart里除了第一个orange之后的水果顺序匹配
 *
 * Ex2:
 * codelist:
 * [ [orange, banana, orange]，
 *   [apple, apple]
 * ]
 * shoppingCart: [orange, apple, apple, orange, banana, orange]
 * return 0, 因为codeList里的顺序和shoppingcart没法匹配。
 *
 * Ex3:
 * codelist:
 * [  [apple, apple],
 *    [orange, banana, orange],
 *    [pear, orange, grape]
 * ]
 * shoppingCart: [orange, apple, apple, orange, banana, orange, pear, grape]
 * return 0, 因为codelist里最后一个[pear, orange, grape]中的orange没法和shoppingcart里的水果匹配
 *
 * Ex4:
 * codeList:
 * [ [apple, apple],
 *   [orange, anything, orange]
 * ]
 * shoppingCart: [orange, apple, apple, orange, mango, orange]
 * return 1
 *
 * Created by WinnieZhao on 9/29/2017.
 */
public class Fruits {

    public static void main(String[] args){
        List<List<String>> fruitlist = new ArrayList<>();
        List<String> chart = Arrays.asList("o","a","a","o","b","o","p","g");
        fruitlist.add(Arrays.asList("a", "a"));
        fruitlist.add(Arrays.asList("o", "b", "o"));

        System.out.println(checkWinner(fruitlist, chart));
    }

    public static int checkWinner (List<List<String>> codeList, List<String> shoppingCart) {
        int codeListIndex = 0;
        int codeIndex = 0;
        int shippingCartIndex = 0;

        while (codeListIndex < codeList.size()){
            codeIndex = 0;
            while(codeIndex < codeList.get(codeListIndex).size() && shippingCartIndex < shoppingCart.size()){
                if (codeList.get(codeListIndex).get(codeIndex) == shoppingCart.get(shippingCartIndex) || codeList.get(codeListIndex).get(codeIndex) == "*"){
                    codeIndex++;
                    shippingCartIndex++;
                }
                else{
                    shippingCartIndex++;
                }
            }
            if (shippingCartIndex == shoppingCart.size() && (codeListIndex != codeList.size()-1 || codeIndex != codeList.get(codeListIndex).size()-1)){
                return 0;
            }
            codeListIndex++;
        }
        return 1;
    }
}
