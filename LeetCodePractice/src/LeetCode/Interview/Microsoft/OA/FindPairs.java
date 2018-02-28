package LeetCode.Interview.Microsoft.OA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 给一堆点,每个点有 x 和 y,find any one pair of points that their
 * midpoint is also integer point. Integer point 是说 x 和 y 都是 integer.
 * Use x_odd_y_odd, x_even_y_odd, x_odd_y_even, x_even_y_even, any one is no less than 2, then will be true.
 */
public class FindPairs {

    public void midPoints2D() {
        List<int[]> list = new ArrayList<>();
        list.add( new int[]{4,5} );
        list.add( new int[]{8,9} );
        list.add( new int[]{1,2} );
        list.add( new int[]{4,6} );
        list.add( new int[]{3,8} );
        list.add( new int[]{8,4} );
        list.add( new int[]{9,2} );
        list.add( new int[]{8,5} );
        list.add( new int[]{8,1} );

        HashMap<String, List<int[]>> map = new HashMap<>();
        map.put("even-even", new ArrayList<>());
        map.put("even-odd", new ArrayList<>());
        map.put("odd-even", new ArrayList<>());
        map.put("odd-odd", new ArrayList<>());

        for ( int[] arr : list ) {
            if (arr[0] % 2 == 0 && arr[1] % 2 == 0 ) {
                List<int[]> l = map.get("even-even");
                l.add(arr);
                map.put("even-even", l);
            }
            else if ( arr[0] % 2 == 0 && arr[1] % 2 != 0 ) {
                List<int[]> l = map.get("even-odd");
                l.add(arr);
                map.put("even-odd", l);
            }
            else if ( arr[0] % 2 != 0 && arr[1] % 2 == 0 ) {
                List<int[]> l = map.get("odd-even");
                l.add(arr);
                map.put("odd-even", l);
            }
            else {
                List<int[]> l = map.get("odd-odd");
                l.add(arr);
                map.put("odd-odd", l);
            }
        }

        System.out.println(map);

        for ( String key : map.keySet() ) {
            List<int[]> li = map.get(key);
            if ( li.size() > 1 ) {
                for (int i = 0 ; i < li.size(); i++) {
                    int [] arr1 = li.get(i);
                    for (int j = i+1 ; j < li.size(); j++) {
                        int [] arr2 = li.get(j);
                        System.out.print("[" + arr1[0] + "," + arr1[1] + "] and " + "[" + arr2[0] + "," + arr2[1] + "] --> ");
                        System.out.print("[" +  (arr1[0]+arr2[0])/2 + "," + (arr1[1]+arr2[1])/2  + "]\n");
                    }
                }
            }
        }
    }
}
