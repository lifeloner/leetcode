package campus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fuyang on 2018/4/2.
 */
public class MicroSoft {

    public void dfs(int node, int num, Map<Integer, List<Integer>> edge, Map<Integer, Integer> value, int[] res) {
        List<Integer> nodes = edge.get(node);
        if (nodes == null) {
            if (res[0] < num) {
                res[0] = num;
            }
            return;
        }
        for (int k : nodes) {
            dfs(k, num * value.get(k), edge, value, res);
        }
    }

    public int calculateMaxScore(int numNodes, int rootNode, int[] values, int[][] edges) {
        if (numNodes == 0) {
            return 0;
        }
        Map<Integer, List<Integer>> edge = new HashMap<>(edges.length);
        Map<Integer, Integer> value = new HashMap<>(numNodes);
        int k = 0;
        for (int[] array : edges) {
            if (!value.containsKey(array[0])) {
                value.put(array[0], values[k++]);
            }
            if (!value.containsKey(array[1])) {
                value.put(array[1], values[k++]);
            }
            List<Integer> nodes = edge.get(array[0]);
            if (nodes == null) {
                nodes = new ArrayList<>();
            }
            nodes.add(array[1]);
            edge.put(array[0], nodes);
//            nodes = edge.get(array[1]);
//            if (nodes == null) {
//                nodes = new ArrayList<>();
//            }
//            nodes.add(array[0]);
//            edge.put(array[1],nodes);
        }
        int[] res = new int[1];
        res[0] = Integer.MIN_VALUE;
        dfs(rootNode, value.get(rootNode), edge, value, res);
        return res[0];
    }

    public static void main(String[] args) {
        MicroSoft microSoft = new MicroSoft();
        int numNodes = 4, rootNode = 1;
        int[] values = new int[]{-1, 2, 3, 2};
        int[][] edges = new int[][]{{1, 2}, {1, 3}, {3, 4}};
        System.out.println(microSoft.calculateMaxScore(numNodes, rootNode, values, edges));
    }
}
