package LeetCode.Interview.Amazon.LevelTwo;

import java.util.*;

/**
 * 给十几个城市供电，连接不同城市的花费不同, 让花费最小同时连到所有的边.
 * 给出一系列connection类，里面是edge两端的城市名和它们之间的一个cost, 找出要你挑一些边，把所有城市连接起来并且总花费最小.
 * 不能有环，最后所以城市要连成一个连通块.
 * 不能的话输出空表, 最后还要按城市名字排序输出, 按照node1来排序,如果一样的话再排node2.
 *
 * 输入:
 * {"Acity","Bcity",1}
 * {"Acity","Ccity",2}
 * {"Bcity","Ccity",3}
 *
 * 输出:
 * {"Acity","Bcity",1}
 * {"Acity","Ccity",2}

 * Created by WinnieZhao on 9/30/2017.
 *
 * More explanation: http://www.jianshu.com/p/04ca0fc1afab
 */
public class MinimumSpanningTree {
    static class Connection {
        String city1;
        String city2;
        int cost;
        public Connection(String a, String b, int c){
            city1 = a;
            city2 = b;
            cost = c;
        }
    }

    public static class UnionFind {
        Map<String, Integer> map; //map中装的是城市->城市所在的集合代号
        int setNum; //城市集合代号

        public UnionFind() {
            map = new HashMap<>();
            setNum = 0;
        }

        /**
         * 对每一个connection做union操作
         * 如果没有union操作，返回FALSE，如果有union操作，返回TRUE
         * 这里跟算法描述中不太一样：这里合并过的城市才会被分配集合代号
         */
        public boolean union(Connection conn) {
            // 两个城市都没有城市代号（都存在于单独的集合，都没有被合并过）
            if (!map.containsKey(conn.city1) && !map.containsKey(conn.city2)) {
                map.put(conn.city1, setNum);
                map.put(conn.city2, setNum);
                setNum++;
                return true;
            }
            // 有一个城市有代号（说明其中有一个是之前合并过的）
            if (map.containsKey(conn.city1) && !map.containsKey(conn.city2)) {
                map.put(conn.city2, map.get(conn.city1));
                return true;
            }
            if (!map.containsKey(conn.city1) && map.containsKey(conn.city2)) {
                map.put(conn.city1, map.get(conn.city2));
                return true;
            }
            // 两个都有代号（那么合并它们分别所在的集合中的所有城市）
            if (map.containsKey(conn.city1) && map.containsKey(conn.city2)) {
                int num1 = map.get(conn.city1);
                int num2 = map.get(conn.city2);
                if (num1 == num2) { //避免出现环
                    return false;
                }
                for (String city : map.keySet()) { //把city1在集合中的所有城市代号改为city2的代号
                    if (map.get(city) == num1) {
                        map.put(city, num2);
                    }
                }
                return true;
            }
            return false;
        }
    }

    public static List<Connection> getMST(List<Connection> connections) {
        List<Connection> res = new ArrayList<>();

        if (connections == null || connections.isEmpty()) {
            return res;
        }

        UnionFind uf = new UnionFind();
        // 首先把边以权重来排序
        connections.sort(Comparator.comparingInt(c -> c.cost));

        Set<String> distinctCities = new HashSet<>();

        // 遍历每一条边，进行处理
        for (Connection conn : connections) {
            distinctCities.add(conn.city1);
            distinctCities.add(conn.city2);
            if (uf.union(conn)) {
                res.add(conn);
            }
        }

        //如果点的个数不是比边多一条的话,那说明所有点不在同一个联盟啊
        if ((distinctCities.size() - 1) != res.size()){
            return new ArrayList<>();
        }

        res.sort((c1, c2) -> {
            if (c1.city1.equals(c2.city1)) {
                return c1.city2.compareTo(c2.city2);
            }
            return c1.city1.compareTo(c2.city1);
        });

        return res;
    }

    public static void main(String[] args) {
        Connection c1 = new Connection("A", "D", 1);
        Connection c2 = new Connection("A", "B", 3);
        Connection c3 = new Connection("D", "B", 3);
        Connection c4 = new Connection("B", "C", 1);
        Connection c5 = new Connection("C", "D", 1);
        Connection c6 = new Connection("E", "D", 6);
        Connection c7 = new Connection("C", "E", 5);
        Connection c8 = new Connection("C", "F", 4);
        Connection c9 = new Connection("E", "F", 2);
        List<Connection> list = new ArrayList<>(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9));
        List<Connection> result = getMST(list);
        for (Connection conn : result) {
            System.out.println(conn.city1 + "-" + conn.cost + "-" + conn.city2);
        }
    }
}
