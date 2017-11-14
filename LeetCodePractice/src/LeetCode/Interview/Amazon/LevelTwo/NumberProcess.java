package LeetCode.Interview.Amazon.LevelTwo;

/**
 * Given n pairs of numbers (1122...nn) and arrange them so that the each number x is x spaces apart from another number x
 *
 * Created by WinnieZhao on 11/5/2017.
 */
public class NumberProcess {

    String result;
    public String getDistanceString(int n) {
        int[] visited = new int[n+1];
        dfs(n, visited, 0, "");
        return result;
    }

    public void dfs(int n, int[] visited, int count, String curr) {
        if (count==2*n) {
            result = curr;
            return;
        }

        for(int i=1;i<=n;i++) {
            if(visited[i]==0) {
                visited[i]++;
                dfs(n, visited, count+1, curr+i);
                visited[i]--;
            }
            else if(visited[i]==1) {
                if (curr.length()-i>=0 && curr.charAt(curr.length()-i) == '0'+i) {
                    visited[i]=2;
                    dfs(n, visited, count+1, curr+i);
                    visited[i]--;
                }
            }
        }
    }

    public static void main(String[] args) {
        NumberProcess l = new NumberProcess();
        System.out.println(l.getDistanceString(4));
    }
}
