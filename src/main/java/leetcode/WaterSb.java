package leetcode;

import java.util.*;

/**
 * Created by yang on 2016/9/12.
 */
public class WaterSb {
    public int integerReplacement(int n) {
        if (n == 1) {
            return 0;
        } else if (n == Integer.MAX_VALUE) {
            return Math.min(integerReplacement(n - 1), integerReplacement(n / 2 + 1) + 1) + 1;
        } else if (n % 2 == 0) {
            return integerReplacement(n / 2) + 1;
        }
        return Math.min(integerReplacement(n + 1), integerReplacement(n - 1)) + 1;
    }

    public int maxRotateFunction(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int sum = 0, max = Integer.MIN_VALUE, current = 0, len = A.length;
        for (int i = 0; i < len; i++) {
            sum += A[i];
            current += i * A[i];
        }
        if (current > max) {
            max = current;
        }
        for (int i = 1; i < len; i++) {
            current = current + len * A[i - 1] - sum;
            if (max < current) {
                max = current;
            }
        }
        return max;
    }

    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        if (queries == null || queries.length == 0) {
            return null;
        }
        double[] result = new double[queries.length];
        Map<String, Integer> map = new HashMap<>();
        int len = 0, x, y;
        GraphNode[] graph = new GraphNode[2*equations.length];
        GraphNode temp;
        for (int i = 0; i < equations.length; i++) {
            if (!map.containsKey(equations[i][0])) {
                graph[len]=new GraphNode(len,1.0,true);
                map.put(equations[i][0], len++);
            }
            if (!map.containsKey(equations[i][1])) {
                graph[len]=new GraphNode(len,1.0,true);
                map.put(equations[i][1], len++);
            }
            x=map.get(equations[i][0]);
            y=map.get(equations[i][1]);
            GraphNode a=new GraphNode(y,values[i],true);
            GraphNode b=new GraphNode(x,values[i],false);
            temp=graph[x].next;
            graph[x].next=a;
            a.next=temp;
            temp=graph[y].next;
            graph[y].next=b;
            b.next=temp;
        }
        for(int i=0;i<queries.length;i++){
            if(!map.containsKey(queries[i][0])||!map.containsKey(queries[i][1])){
                result[i]=-1.0;
                continue;
            }
            if(queries[i][0].equals(queries[i][1])){
                result[i]=1.0;
                continue;
            }
            result[i]=getVaule(graph,map.get(queries[i][0]),map.get(queries[i][1]),map.size());
        }
        return result;
    }

    public double getVaule(GraphNode[]graph,int begin,int end,int size) {
        Queue<Integer> queue = new ArrayDeque<>();
        Queue<Double> nums = new ArrayDeque<>();
        boolean []visited=new boolean[size];
        for(int i=0;i<size;i++){
            visited[i]=false;
        }
        boolean up = true;
        GraphNode node;
        double num=-1;
        queue.add(begin);
        nums.add(1.0);
        visited[begin]=true;
        while (!queue.isEmpty()) {
            node = graph[queue.poll()];
            num = nums.poll();
            if(node.num==end){
                if(up){
                    return num;
                }
                else{
                    if(num!=0){
                        return 1/num;
                    }
                    return -1;
                }
            }
            while (node.next != null) {
                node = node.next;
                if(visited[node.num]){
                    continue;
                }
                visited[node.num]=true;
                queue.add(node.num);
                if (up && node.status) {
                    up = true;
                    nums.add(num * node.value);
                } else if (up && !node.status && node.value != 0) {
                    up = true;
                    nums.add(num / node.value);
                } else if (!up && node.status && node.value != 0) {
                    up = true;
                    nums.add(node.value / num);
                } else {
                    up = false;
                    nums.add(num * node.value);
                }
            }
        }
        return -1;
    }
    private class GraphNode{
        private int num;
        private double value;
        private boolean status;
        private GraphNode next;
        public GraphNode(int num, double value,boolean status) {
            this.num = num;
            this.status=status;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        WaterSb waterSb = new WaterSb();
        System.out.println(Arrays.toString(waterSb.calcEquation(new String[][]{{"a","b"},{"b","c"},{"d","e"}},new double[]{2.0,3.0,0},new String[][]{{"d","a"}})));
    }
}
