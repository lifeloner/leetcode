package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yang on 2016/9/23.
 */
public class Hard {

    public boolean reslove(int[] A) {
        if (A.length < 7) {
            return false;
        }
        int[] left = new int[A.length], right = new int[A.length];
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            left[i] = sum;
        }
        sum = 0;
        for (int i = A.length - 1; i >= 0; i--) {
            sum += A[i];
            right[i] = sum;
        }
        int a = 1, b = A.length - 2, c = 0, d = 0, m = 0, n = 0, e = left[0], f = right[A.length - 1];
        while (a <= b) {
            if (e == f) {
                System.out.println(a+"\t"+b+"\t"+e+"\t"+c);
                if (c != 0) {
                    if(a!=b){
                        e = left[a] - m;
                        a++;
                        continue;
                    }
                    if (a == b && 2*left[c - 1] == e) {
                        return true;
                    }
                    e = left[c];
                    f = right[d];
                    m = 0;
                    n = 0;
                    c = 0;
                    d = 0;
                } else {
                    c = a++;
                    d = b--;
                    m = A[c];
                    n = A[d];
                }
            } else if (e < f) {
                e = left[a] - m;
                a++;
            } else {
                f = right[b] - n;
                b--;
            }
        }
        return false;
    }

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
        if (nums == null || nums.length < m) {
            return 0;
        }
        int[][] array = new int[m][nums.length];
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            max += nums[i];
            array[0][i] = max;
        }
        for (int i = 1; i < m; i++) {
            for (int j = i; j < nums.length; j++) {
                array[i][j] = Math.max(nums[j], array[i - 1][j - 1]);
                max = nums[j];
                for (int k = j - 1; k > i - 1; k--) {
                    max += nums[k];
                    array[i][j] = Math.min(array[i][j], Math.max(array[i - 1][k - 1], max));
                }
            }
        }
        return array[m - 1][nums.length - 1];
    }

    public boolean predictTheWinner(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return true;
        }
        int[][] array = new int[nums.length][nums.length];
        for (int i = 0; i < nums.length; i++) {
            array[i][i] = nums[i];
        }
        int m, n, max;
        for (int k = 1; k < nums.length; k++) {
            for (int i = 0; i + k < nums.length; i++) {
                m = nums[i] - nums[i + k];
                if (2 <= k) {
                    m += array[i + 1][i + k - 1];
                }
                n = nums[i] - nums[i + 1];
                if (2 <= k) {
                    n += array[i + 2][i + k];
                }
                max = Math.min(m, n);
                m = nums[i + k] - nums[i];
                if (2 <= k) {
                    m += array[i + 1][i + k - 1];
                }
                n = nums[i + k] - nums[i - 1 + k];
                if (2 <= k) {
                    n += array[i][i + k - 2];
                }
                if (m > n) {
                    m = n;
                }
                if (m > max) {
                    max = m;
                }
                array[i][i + k] = max;
            }
        }
        return array[0][nums.length - 1] >= 0 ? true : false;
    }

    public static void main(String[] args) {
        Hard hard = new Hard();
//        System.out.println(hard.canCross(new int[]{0,1,2,3,4,8,9,11}));
//        System.out.println(hard.splitArray(new int[]{7, 2, 5, 10, 8}, 2));
//        System.out.println(hard.predictTheWinner(new int[]{1, 5, 233, 7}));
        System.out.println(hard.reslove(new int[]{2, 5, 1, 1, 1, 1, 4, 1, 7, 3, 7}));
    }

}
