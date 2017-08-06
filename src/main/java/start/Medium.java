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
            child = map.getOrDefault(kill, null);
            if (child != null) {
                for (int k : child) {
                    queue.add(k);
                }
            }
        }
        return result;
    }

    public static int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return -1;
        }
        int len1 = word1.length(), len2 = word2.length();
        if (len1 == 0 || len2 == 0) {
            return word1.length() + word2.length();
        }
        int[][] array = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    array[i][j] = array[i - 1][j - 1] + 1;
                } else {
                    array[i][j] = Math.max(array[i - 1][j], array[i][j - 1]);
                }
            }
        }
        return len1 + len2 - 2 * array[len1][len2];
    }

    public static int triangleNumber(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums);
        int count = 0, m = 0, n = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            m = Math.max(m, i + 2);
            n = m;
            for (int j = i + 1; j < nums.length - 1; j++) {
                n = Math.max(n, j + 1);
                for (int k = n; k < nums.length; k++) {
                    if (nums[i] + nums[j] > nums[k]) {
                        n = k;
                    } else {
                        break;
                    }
                }
                if (n > j && nums[i] + nums[j] > nums[n]) {
                    count += n - j;
                    if (j == i + 1) {
                        m = n;
                    }
                }
            }
        }
        return count;
    }

    public static int arrayNesting(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = 0, k, count;
        boolean[] state = new boolean[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (state[i] == true) {
                continue;
            }
            k = i;
            state[k] = true;
            k = nums[k];
            count = 1;
            while (state[k] == false) {
                count++;
                state[k] = true;
                k = nums[k];
            }
            if (count > max) {
                max = count;
            }
        }
        return max;
    }

    public static int scheduleCourse(int[][] courses) {
        if (courses == null || courses.length <= 1) {
            return courses.length;
        }
        Arrays.sort(courses, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        Queue<Integer> queue = new PriorityQueue(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        int sum = 0;
        for (int[] num : courses) {
            sum += num[0];
            queue.add(num[0]);
            if (sum > num[1]) {
                sum -= queue.peek();
                queue.poll();
            }
        }
        return queue.size();
    }

    public static boolean judgeSquareSum(int c) {
        if (c < 0) {
            return false;
        }
        if (c <= 2) {
            return true;
        }
        int max = (int) Math.sqrt(1.0 * Integer.MAX_VALUE);
        Set<Integer> nums = new HashSet<>();
        for (int i = 0; i * i <= c && i <= max; i++) {
            nums.add(i * i);
        }
        for (int num : nums) {
            if (nums.contains(c - num)) {
                return true;
            }
        }
        return false;
    }

    public static int[] exclusiveTime(int n, List<String> logs) {
        int[] result = new int[n];
        if (n == 0) {
            return result;
        }
        Stack<int[]> stack = new Stack<>();
        String[] string;
        int time, id, k;
        for (String log : logs) {
            string = log.split(":");
            id = Integer.parseInt(string[0]);
            time = Integer.parseInt(string[2]);
            if (string[1].equals("start")) {
                int[] num = new int[2];
                num[0] = 0;
                num[1] = time;
                stack.push(num);
            } else {
                int[]num=new int[2];
                k=0;
                while(!stack.empty()){
                    num=stack.pop();
                    if(num[0]==0){
                        result[id]+=time-k-num[1]+1;
                        k=num[1];
                        break;
                    }
                    k+=num[1];
                }
                num[0]=1;
                num[1]=time-k+1;
                stack.push(num);
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        System.out.println(findCircleNums(new int[][]{{1,1,0},{1,1,1},{0,1,1}}));
//        System.out.println(findMinDifference(new ArrayList<String>(){{add("00:01");
//        add("23:59");
//        }}));
//        System.out.println(complexNumberMultiply("1+-1i", "1+-1i"));
//        System.out.println(killProcess(Arrays.asList(1,3,10,5),Arrays.asList(3,0,5,3),3));
//        System.out.println(minDistance("ac","ab"));
//        System.out.println(triangleNumber(new int[]{1,1,3,4}));
//        System.out.println(arrayNesting(new int[]{5,4,0,3,1,6,2}));
//        System.out.println(scheduleCourse(new int[][]{{5,5},{4,6},{2,6}}));
//        System.out.println(judgeSquareSum(2038480973));
        List<String>logs=new ArrayList<>();
        logs.add("0:start:0");
        logs.add("0:start:2");
        logs.add("0:end:5");
        logs.add("0:end:10");
        System.out.println(Arrays.toString(exclusiveTime(1,logs)));
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
