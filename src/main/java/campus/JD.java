package campus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by yang on 2017/9/8.
 */
public class JD {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }
        int m;
        for (int i = 0; i < n - 1; i++) {
            m = scanner.nextInt();
            list.get(i + 1).add(m);
            list.get(m).add(i + 1);
        }
        int[] visited = new int[n+1];
        visited[0] = 1;
        dfs(visited,0,0,k,list);
        System.out.println(visited[visited.length-1]);
    }

    public static void  dfs(int[] visited, int city, int t, int k,List<List<Integer>> list) {
        if(t==k){
            int count=0;
            for(int i=0;i<visited.length-1;i++){
                if(visited[i]!=0){
                    count++;
                }
            }
            if(count>visited[visited.length-1]){
                visited[visited.length-1]=count;
            }
            return;
        }
        List<Integer>citys=list.get(city);
        for(int i=0;i<citys.size();i++){
            visited[citys.get(i)]++;
            dfs(visited,citys.get(i),t+1,k,list);
            visited[citys.get(i)]--;
        }
    }
}
