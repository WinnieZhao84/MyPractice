package LeetCode.Interview.Amazon;

import java.util.*;

/**
 * 工午饭选择推荐系统，给两个list<string,string>, 第一个代表员工和其喜欢的菜系, 第二个代表菜系和其对应的菜，例如：
 * list1:
 * 张三 中国菜
 * 李四 美国菜
 * 王五 日本菜
 * 小明 *
 *
 * list2:
 * 中国菜 鱼香肉丝.
 * 中国菜 水煮鱼.
 * 美国菜 芝士汉堡
 *
 * 输出：
 * 张三 鱼香肉丝
 * 张三 水煮鱼
 * 李四 芝士汉堡
 * 小明 鱼香肉丝
 * 小明 水煮鱼
 * 小明 芝士汉堡
 *
 * 注意这里王五没有输出因为没有日本菜的菜单。小明 * 代表他百搭，吃什么都可以。
 *
 * Created by WinnieZhao on 9/28/2017.
 */
public class Menu {
    public static void main (String[] args){
        List<List<String>> list1 = new ArrayList<>();
        List<List<String>> list2 = new ArrayList<>();

        list1.add(Arrays.asList("Zhang","Chinese"));
        list1.add(Arrays.asList("Li","American"));
        list1.add(Arrays.asList("Wang","Japanese"));
        list1.add(Arrays.asList("Ming","*"));
        list2.add(Arrays.asList("Chinese","Pork"));
        list2.add(Arrays.asList("Chinese","fish"));
        list2.add(Arrays.asList("American","beef"));

        System.out.println(menu(list1,list2));
    }

    public static List<List<String>> menu (List<List<String>> list1, List<List<String>> list2) {
        Map<String,Set<String>> map = new HashMap<>();

        for(List<String> row : list2){
            if (!map.containsKey(row.get(0))){
                map.put(row.get(0), new HashSet<>(Arrays.asList(row.get(1))));
            }
            else{
                map.get(row.get(0)).add(row.get(1));
            }
        }

        List<List<String>> result = new ArrayList();
        for (int i = 0; i<list1.size(); i++){
            if (map.containsKey(list1.get(i).get(1))){
                for (String dish : map.get(list1.get(i).get(1))){
                    result.add(Arrays.asList(list1.get(i).get(0), dish));
                }

            }
            if (list1.get(i).get(1) == "*"){
                for (Set<String> dishes : map.values()){
                    for (String dish : dishes){
                        result.add(Arrays.asList(list1.get(i).get(0), dish));
                    }
                }
            }
        }
        return result;
    }

}
