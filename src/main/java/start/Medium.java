package start;

import java.util.*;

/**
 * Created by yang on 2017/4/4.
 */
public class Medium {

    public static int findFather(int a, int[] father) {
        while (a != father[a]) {
            a = father[a];
        }
        return a;
    }

    public static int findCircleNums(int[][] M) {
        if (M == null || M.length == 0) {
            return 0;
        }
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] flag = new boolean[M.length];
        int sum = 0, k;
        for (int i = 0; i < M.length; i++) {
            if (flag[i]) {
                continue;
            }
            queue.add(i);
            flag[i] = true;
            while (!queue.isEmpty()) {
                k = queue.poll();
                for (int j = 0; j < M.length; j++) {
                    if (j != k && M[k][j] == 1 && !flag[j]) {
                        queue.add(j);
                        flag[j] = true;
                    }
                }
            }
            sum++;
        }
        return sum;
    }

    public static int findCircleNum(int[][] M) {
        if (M == null || M.length == 0) {
            return 0;
        }
        int[] father = new int[M.length];
        for (int i = 0; i < father.length; i++) {
            father[i] = i;
        }
        int a, b;
        for (int i = 0; i < M.length; i++) {
            for (int j = i + 1; j < M.length; j++) {
                if (M[i][j] == 1) {
                    a = findFather(i, father);
                    b = findFather(j, father);
                    father[a] = b;
                }
            }
        }
        boolean[] flag = new boolean[M.length];
        for (int i = 0; i < M.length; i++) {
            flag[findFather(i, father)] = true;
        }
        a = 0;
        for (int i = 0; i < flag.length; i++) {
            if (flag[i]) {
                a++;
            }
        }
        return a;
    }

    public static int findMinDifference(List<String> timePoints) {
        if (timePoints == null || timePoints.size() == 0) {
            return 0;
        }
        int[] nums = new int[timePoints.size()];
        String[] temp;
        for (int i = 0; i < nums.length; i++) {
            temp = timePoints.get(i).split(":");
            nums[i] = Integer.parseInt(temp[0]) * 60 + Integer.parseInt(temp[1]);
        }
        Arrays.sort(nums);
        int num = 23 * 60 + 59;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] < num) {
                num = nums[i] - nums[i - 1];
            }
        }
        if (24 * 60 - nums[nums.length - 1] + nums[0] < num) {
            num = 24 * 60 - nums[nums.length - 1] + nums[0];
        }
        return num;
    }

    public static String complexNumberMultiply(String a, String b) {
        int[] c = getNum(a), d = getNum(b);
        int e = c[0] * d[0] - c[1] * d[1], f = c[0] * d[1] + c[1] * d[0];
        return e + "+" + f + "i";
    }

    public static int[] getNum(String a) {
        int num;
        boolean flag;
        int[] nums = new int[2];
        if (a != null && a.length() != 0) {
            num = 0;
            flag = true;
            int index = 0;
            while (index < a.length()) {
                if (a.charAt(index) >= '0' && a.charAt(index) <= '9') {
                    break;
                } else if (a.charAt(index) == '-') {
                    flag = !flag;
                }
                index++;
            }
            while (index < a.length()) {
                if (a.charAt(index) >= '0' && a.charAt(index) <= '9') {
                    num = num * 10 + a.charAt(index) - '0';
                } else if (a.charAt(index) == '+') {
                    index++;
                    break;
                }
                index++;
            }
            nums[0] = num;
            if (!flag) {
                nums[0] = -num;
            }
            flag = true;
            while (index < a.length()) {
                if (a.charAt(index) >= '0' && a.charAt(index) <= '9') {
                    break;
                } else if (a.charAt(index) == '-') {
                    flag = !flag;
                }
                index++;
            }
            num = 0;
            while (index < a.length()) {
                if (a.charAt(index) >= '0' && a.charAt(index) <= '9') {
                    num = num * 10 + a.charAt(index) - '0';
                } else if (a.charAt(index) == 'i') {
                    break;
                }
                index++;
            }
            nums[1] = num;
            if (!flag) {
                nums[1] = -nums[1];
            }
        }
        return nums;
    }

    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return root;
        }
        visit(root, 0);
        return root;
    }

    public int visit(TreeNode node, int value) {
        if (node.right != null) {
            node.val += visit(node.right, value);
        } else {
            node.val += value;
        }
        if (node.left != null) {
            return visit(node.left, node.val);
        }
        return node.val;
    }

    public static int findPaths(int m, int n, int N, int i, int j) {
        if (N == 0 || m == 0 || n == 0) {
            return 0;
        }
        int[][] array = new int[m][n], start = new int[m][n], temp = new int[m][n], temps;
        for (int k = 0; k < m; k++) {
            array[k][0]++;
            start[k][0]++;
            array[k][n - 1]++;
            start[k][n - 1]++;
        }
        for (int k = 0; k < n; k++) {
            array[0][k]++;
            start[0][k]++;
            array[m - 1][k]++;
            start[m - 1][k]++;
        }
        for (int k = 1; k < N; k++) {
            for (int a = 0; a < m; a++) {
                for (int b = 0; b < n; b++) {
                    temp[a][b] = start[a][b];
                    if (a > 0) {
                        temp[a][b] = (temp[a][b] + array[a - 1][b]) % 1000000007;
                    }
                    if (a + 1 < m) {
                        temp[a][b] = (temp[a][b] + array[a + 1][b]) % 1000000007;
                    }
                    if (b > 0) {
                        temp[a][b] = (temp[a][b] + array[a][b - 1]) % 1000000007;
                    }
                    if (b + 1 < n) {
                        temp[a][b] = (temp[a][b] += array[a][b + 1]) % 1000000007;
                    }
                }
            }
            temps = temp;
            temp = array;
            array = temps;
        }
        return array[i][j];
    }

    public static List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        List<Integer> result = new ArrayList<>();
        if (pid == null || pid.size() == 0) {
            return result;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<Integer> child;
        for (int i = 0; i < pid.size(); i++) {
            child = map.getOrDefault(ppid.get(i), null);
            if (child == null) {
                child = new ArrayList<>();
                map.put(ppid.get(i), child);
            }
            child.add(pid.get(i));
        }
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(kill);
        while (!queue.isEmpty()) {
            kill = queue.poll();
            result.add(kill);
            child=map.getOrDefault(kill,null);
            if(child!=null) {
                for (int k:child){
                    queue.add(k);
                }
            }
        }
        return result;
    }

    public static int minDistance(String word1, String word2) {
        if(word1==null||word2==null){
            return -1;
        }
        int len1=word1.length(),len2=word2.length();
        if(len1==0||len2==0){
            return word1.length()+word2.length();
        }
        int [][]array=new int[len1+1][len2+1];
        for(int i=1;i<=len1;i++){
            for (int j=1;j<=len2;j++){
                if(word1.charAt(i-1)==word2.charAt(j-1)) {
                    array[i][j] = array[i-1][j-1]+1;
                }else {
                    array[i][j]=Math.max(array[i-1][j],array[i][j-1]);
                }
            }
        }
        return len1+len2-2*array[len1][len2];
    }

    public static void main(String[] args) {
//        System.out.println(findCircleNums(new int[][]{{1,1,0},{1,1,1},{0,1,1}}));
//        System.out.println(findMinDifference(new ArrayList<String>(){{add("00:01");
//        add("23:59");
//        }}));
//        System.out.println(complexNumberMultiply("1+-1i", "1+-1i"));
//        System.out.println(killProcess(Arrays.asList(1,3,10,5),Arrays.asList(3,0,5,3),3));
        System.out.println(minDistance("ac","ab"));
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
