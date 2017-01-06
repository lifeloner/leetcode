package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yang on 2016/9/23.
 */
public class Hard {
    public boolean canCross(int[] stones) {
        if (stones == null || stones.length < 2) {
            return true;
        }
        int len = stones.length;
        Map<Integer, Integer> indexMap = new HashMap<>();
        boolean[][] visited = new boolean[len][len];
        for (int i = 0; i < visited.length; i++) {
            indexMap.put(stones[i], i);
            for (int j = 0; j < visited[i].length; j++) {
                visited[i][j] = false;
            }
        }
        return dfs(indexMap, visited, stones, 0, 1);
    }

    public boolean dfs(Map<Integer, Integer> index, boolean[][] visisted, int[] stones, int k, int n) {
        if (k == stones.length - 1) {
            return true;
        }
        if (visisted[k][n]) {
            return false;
        }
        visisted[k][n] = true;
        int len = stones.length - k;
        if (index.containsKey(stones[k] + n)) {
            if ((k + k + len - 1) * len / 2 < stones[stones.length - 1] - stones[k]) {
                return false;
            }
            if (n - 1 >= 1 && dfs(index, visisted, stones, index.get(stones[k] + n), n - 1)) {
                return true;
            }
            if (dfs(index, visisted, stones, index.get(stones[k] + n), n)) {
                return true;
            }
            if (dfs(index, visisted, stones, index.get(stones[k] + n), n + 1)) {
                return true;
            }
        }
        return false;
    }

    public int splitArray(int[] nums, int m) {
        if(nums==null||nums.length<m){
            return 0;
        }
        int [][]array=new int[m][nums.length];
        int max=0;
        for(int i=0;i<nums.length;i++){
            max+=nums[i];
            array[0][i]=max;
        }
        for(int i=1;i<m;i++){
            for(int j=i;j<nums.length;j++){
                array[i][j]=Math.max(nums[j],array[i-1][j-1]);
                max=nums[j];
                for(int k=j-1;k>i-1;k--){
                    max+=nums[k];
                    array[i][j]=Math.min(array[i][j],Math.max(array[i-1][k-1],max));
                }
            }
        }
        return array[m-1][nums.length-1];
    }

    public static void main(String[] args) {
        Hard hard = new Hard();
//        System.out.println(hard.canCross(new int[]{0,1,2,3,4,8,9,11}));
        System.out.println(hard.splitArray(new int[]{7,2,5,10,8},2));
    }

}
