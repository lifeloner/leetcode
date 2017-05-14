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
        if(root==null){
            return root;
        }
        visit(root,0);
        return root;
    }

    public int visit(TreeNode node,int value){
        if(node.right!=null){
            node.val+=visit(node.right,value);
        }else{
            node.val+=value;
        }
        if(node.left!=null){
           return visit(node.left,node.val);
        }
        return node.val;
    }

    public static void main(String[] args) {
//        System.out.println(findCircleNums(new int[][]{{1,1,0},{1,1,1},{0,1,1}}));
//        System.out.println(findMinDifference(new ArrayList<String>(){{add("00:01");
//        add("23:59");
//        }}));
        System.out.println(complexNumberMultiply("1+-1i", "1+-1i"));
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
