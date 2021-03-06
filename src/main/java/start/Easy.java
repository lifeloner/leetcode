package start;

import java.util.*;

/**
 * Created by fuyang on 2017/5/28.
 */
public class Easy {

    public static boolean checkRecord(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        int a = 0, b = 0;
        char c;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (c == 'A') {
                a++;
                b = 0;
            } else if (c == 'L') {
                b++;
            } else {
                b = 0;
            }
            if (a > 1 || b > 2) {
                return false;
            }
        }
        return true;
    }

    public static String optimalDivision(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        builder.append(nums[0]);
        if (nums.length == 2) {
            builder.append("/");
            builder.append(nums[1]);
        }
        if (nums.length > 2) {
            builder.append("/(");
            for (int i = 1; i < nums.length - 1; i++) {
                builder.append(nums[i]);
                builder.append("/");
            }
            builder.append(nums[nums.length - 1]);
            builder.append(")");
        }
        return builder.toString();
    }

    public static int leastBricks(List<List<Integer>> wall) {
        if (wall == null || wall.size() == 0) {
            return 0;
        }
        Map<Integer, Integer> count = new HashMap<>();
        int sum, k;
        for (List<Integer> list : wall) {
            if (list != null && list.size() > 0) {
                sum = 0;
                for (int i = 0; i < list.size() - 1; i++) {
                    sum += list.get(i);
                    k = count.getOrDefault(sum, -0);
                    k++;
                    count.put(sum, k);
                }
            }
        }
        sum = wall.size();
        k = wall.size();
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            if (k - entry.getValue() < sum) {
                sum = k - entry.getValue();
            }
        }
        return sum;
    }

    public static int nextGreaterElement(int n) {
        int k = n % 10, m = 0;
        n = n / 10;
        if (n == 0) {
            return -1;
        }
        List<Integer> list = new ArrayList<>();
        list.add(k);
        while (n != 0 && (m = n % 10) >= k) {
            list.add(m);
            k = m;
            n = n / 10;
        }
        if (n == 0) {
            return -1;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) > m) {
                n = n / 10 * 10 + list.get(i);
                list.set(i, m);
                break;
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (n > 214748364 || n == 214748364 && list.get(i) > 7) {
                return -1;
            }
            n = n * 10 + list.get(i);
        }
        return n;
    }

    public static String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        StringBuilder stringBuilder = new StringBuilder(s.length());
        List<Character> list = new ArrayList<>();
        char c;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (c == ' ') {
                for (int j = list.size() - 1; j >= 0; j--) {
                    stringBuilder.append(list.get(j));
                }
                stringBuilder.append(c);
                list.clear();
            } else {
                list.add(c);
            }
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            stringBuilder.append(list.get(i));
        }
        return stringBuilder.toString();
    }

    public static int singleNonDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        for (int i = 1; i < nums.length; i++) {
            nums[0] ^= nums[i];
        }
        return nums[0];
    }

    public static int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0, count = 0, m;
        for (int num : nums) {
            sum += num;
            m = map.getOrDefault(sum - k, 0);
            if (sum == k) {
                count++;
            }
            count += m;
            m = map.getOrDefault(sum, 0);
            map.put(sum, ++m);
        }
        return count;
    }

    public static int arrayPairSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i += 2) {
            sum += nums[i];
        }
        return sum;
    }

    public static int findTilt(TreeNode root) {
        int[] num = new int[]{0};
        postOrder(root, num);
        return num[0];
    }

    public static int postOrder(TreeNode node, int[] num) {
        if (node == null) {
            return 0;
        }
        int left = postOrder(node.left, num), right = postOrder(node.right, num);
        num[0] += Math.abs(left - right);
        return left + right + node.val;
    }

    public static int[][] matrixReshape(int[][] nums, int r, int c) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        int row = nums.length, column = nums[0].length;
        if (row * column < r * c) {
            return nums;
        }
        int[][] result = new int[r][c];
        int a = 0, b = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (b == column) {
                    b = 0;
                    a++;
                }
                result[i][j] = nums[a][b++];
            }
        }
        return result;
    }

    public static boolean check(int[] num) {
        for (int i = 0; i < num.length; i++) {
            if (num[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s2 == null || s2.length() < s1.length()) {
            return false;
        }
        int[] count = new int[26];
        for (int i = 0; i < 26; i++) {
            count[i] = 0;
        }
        for (int i = 0; i < s1.length(); i++) {
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
        }
        if (check(count)) {
            return true;
        }
        int len = s1.length();
        for (int i = len; i < s2.length(); i++) {
            count[s2.charAt(i - len) - 'a']++;
            count[s2.charAt(i) - 'a']--;
            if (check(count)) {
                return true;
            }
        }
        return false;
    }

    public static int findLHS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Map<Integer, Integer> count = new HashMap<>();
        int num, result = 0, temp;
        for (int i : nums) {
            num = count.getOrDefault(i, 0);
            count.put(i, ++num);
        }
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            if (entry.getKey() != Integer.MIN_VALUE) {
                temp = count.getOrDefault(entry.getKey() - 1, 0);
                if (temp > 0 && temp + entry.getValue() > result) {
                    result = temp + entry.getValue();
                }
            }
        }
        return result;
    }

    public static boolean isSquare(int[] p1, int[] p2, int[] p3) {
        int a = p1[0] - p2[0], b = p1[1] - p2[1], c = p1[0] - p3[0], d = p1[1] - p3[1];
        return a * c + b * d == 0 && a * a + b * b == c * c + d * d && a * a + b * b != 0 ? true : false;
    }

    public static boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        if (isSquare(p1, p3, p4) && isSquare(p2, p3, p4) && isSquare(p3, p1, p2)) {
            return true;
        }
        if (isSquare(p1, p2, p4) && isSquare(p3, p2, p4) && isSquare(p2, p1, p3)) {
            return true;
        }
        if (isSquare(p1, p2, p3) && isSquare(p4, p2, p3) && isSquare(p2, p1, p4)) {
            return true;
        }
        return false;
    }

    public static int maxCount(int m, int n, int[][] ops) {
        if (ops == null || ops.length == 0) {
            return m * n;
        }
        for (int i = 0; i < ops.length; i++) {
            if (ops[i][0] < m) {
                m = ops[i][0];
            }
            if (ops[i][1] < n) {
                n = ops[i][1];
            }
        }
        return m * n;
    }

    public static boolean isSameTree(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        if (s.val != t.val) {
            return false;
        }
        return isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }

    public static boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        if (s.val == t.val && isSameTree(s, t)) {
            return true;
        }
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    public static int distributeCandies(int[] candies) {
        if (candies == null || candies.length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (int candy : candies) {
            set.add(candy);
        }
        return Math.min(set.size(), candies.length / 2);
    }

    public static int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int end = 0, begin = nums.length - 1, max = nums[end], min = nums[begin], len = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            if (max <= nums[i]) {
                max = nums[i];
            } else {
                end = i;
            }
            if (min >= nums[len - i]) {
                min = nums[len - i];
            } else {
                begin = len - i;
            }
        }
        if (begin >= end) {
            return 0;
        }
        return end - begin + 1;

    }

    public static String[] findRestaurant(String[] list1, String[] list2) {
        List<String> list = new ArrayList<>();
        String[] result;
        if (list1 == null || list2 == null || list1.length == 0 || list2.length == 0) {
            result = new String[0];
            return list.toArray(result);
        }
        Map<String, Integer> mapIndex = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            mapIndex.put(list1[i], i);
        }
        int sum = Integer.MAX_VALUE, index;
        for (int i = 0; i < list2.length; i++) {
            index = mapIndex.getOrDefault(list2[i], -1);
            if (index >= 0) {
                if (index + i == sum) {
                    list.add(list2[i]);
                } else if (index + i < sum) {
                    list.clear();
                    list.add(list2[i]);
                    sum = index + i;
                }
            }
        }
        result = new String[list.size()];
        return list.toArray(result);
    }

    public static int findMaxDivisor(int a, int b) {
        int c;
        while (b != 0) {
            c = a % b;
            a = b;
            b = c;
        }
        return a;
    }

    public static void plus(int[] result, boolean flag) {
        if (result[2] != 0) {
            if (result[0] == 0) {
                if (!flag) {
                    result[2] *= -1;
                }
                result[0] = result[2];
                result[1] = result[3];
            } else {
                int divisor;
                divisor = findMaxDivisor(result[1], result[3]);
                if (flag) {
                    result[0] = result[0] * result[3] / divisor + result[2] * result[1] / divisor;
                    result[1] = result[3] / divisor * result[1];
                } else {
                    result[0] = result[0] * result[3] / divisor - result[2] * result[1] / divisor;
                    result[1] = result[1] / divisor * result[3];
                }
            }
        }
        result[2] = 0;
        result[3] = 0;
    }

    public static String fractionAddition(String expression) {
        if (expression == null || expression.length() == 0) {
            return "0/1";
        }
        boolean flag = true, isDenominator = false;
        char ch;
        int[] result = new int[]{0, 0, 0, 0};
        for (int i = 0; i < expression.length(); i++) {
            ch = expression.charAt(i);
            if (ch >= '0' && ch <= '9') {
                if (isDenominator) {
                    result[3] = result[3] * 10 + ch - '0';
                } else {
                    result[2] = result[2] * 10 + ch - '0';
                }
            } else if (ch == '/') {
                isDenominator = true;
            } else {
                isDenominator = false;
                plus(result, flag);
                if (ch == '-') {
                    flag = false;
                } else {
                    flag = true;
                }
            }
        }
        plus(result, flag);
        if (result[0] == 0) {
            return "0/1";
        }
        flag = true;
        if (result[0] < 0) {
            flag = false;
            result[0] *= -1;
        }
        result[2] = findMaxDivisor(result[0], result[1]);
        if (!flag) {
            result[0] *= -1;
        }
        return result[0] / result[2] + "/" + result[1] / result[2];
    }

    public static void preOrder(TreeNode t, StringBuilder builder) {
        if (t == null) {
            builder.append("()");
            return;
        }
        builder.append("(");
        builder.append(t.val);
        if (t.left == null && t.right == null) {
            builder.append(")");
            return;
        }
        preOrder(t.left, builder);
        if (t.right != null) {
            preOrder(t.right, builder);
        }
        builder.append(")");
    }

    public static String tree2str(TreeNode t) {
        if (t == null) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(t.val);
        if (t.left == null && t.right == null) {
            return builder.toString();
        }
        preOrder(t.left, builder);
        if (t.right != null) {
            preOrder(t.right, builder);
        }
        return builder.toString();
    }

    public static List<List<String>> findDuplicate(String[] paths) {
        if (paths == null || paths.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List<String>> map = new HashMap<>();
        String[] temp;
        String first, second;
        char ch;
        int k;
        for (String string : paths) {
            temp = string.split(" ");
            for (int i = 1; i < temp.length; i++) {
                StringBuilder builder = new StringBuilder();
                builder.append(temp[0]);
                builder.append("/");
                first = null;
                second = null;
                for (int j = 0; j < temp[i].length(); j++) {
                    ch = temp[i].charAt(j);
                    if (ch == '(') {
                        first = builder.toString();
                        builder = new StringBuilder();
                    } else if (ch == ')') {
                        second = builder.toString();
                    } else {
                        builder.append(ch);
                    }
                }
                if (map.containsKey(second)) {
                    map.get(second).add(first);
                } else {
                    List<String> list = new ArrayList<>();
                    list.add(first);
                    map.put(second, list);
                }
            }
        }
        List<List<String>> result = new ArrayList<>(map.size());
        for (String str : map.keySet()) {
            if (map.get(str).size() > 1) {
                result.add(map.get(str));
            }
        }
        return result;
    }

    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (n == 0) {
            return true;
        }
        if (flowerbed == null || flowerbed.length / 2 + 1 < n) {
            return false;
        }
        int count = 0, k = 0;
        while (k < flowerbed.length) {
            if (flowerbed[k] == 0) {
                if (k - 1 >= 0 && flowerbed[k - 1] == 1) {
                    k++;
                    continue;
                }
                if (k + 1 < flowerbed.length && flowerbed[k + 1] == 1) {
                    k++;
                    continue;
                }
                count++;
                if (count == n) {
                    return true;
                }
                flowerbed[k] = 1;
                k++;
            }
            k++;
        }
        return false;
    }


    public static void mergeTreeNode(TreeNode t1, TreeNode t2) {
        t1.val += t2.val;
        if (t1.left == null) {
            t1.left = t2.left;
        } else if (t1.left != null && t2.left != null) {
            mergeTreeNode(t1.left, t2.left);
        }
        if (t1.right == null) {
            t1.right = t2.right;
        } else if (t1.right != null && t2.right != null) {
            mergeTreeNode(t1.right, t2.right);
        }
    }

    public static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        mergeTreeNode(t1, t2);
        return t1;
    }

    public static int leastInterval(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0) {
            return 0;
        }
        int[] count = new int[26];
        for (char ch : tasks) {
            count[ch - 'A']++;
        }
        Arrays.sort(count);
        Queue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = count.length - 1; i >= 0; i--) {
            if (count[i] > 0) {
                queue.add(count[i]);
            } else {
                break;
            }
        }
        int result = 0, k = 0, t = 0;
        while (!queue.isEmpty()) {
            while (!queue.isEmpty()) {
                count[k] = queue.poll();
                count[k]--;
                k++;
                if (k == n + 1) {
                    break;
                }
            }
            result += 1 + n;
            t = k;
            while (k > 0) {
                if (count[k - 1] > 0) {
                    queue.add(count[k - 1]);
                }
                k--;
            }
        }
        if (t < 1 + n) {
            result -= 1 + n - t;
        }
        return result;
    }

    public static TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d <= 0) {
            return root;
        }
        if (d == 1) {
            TreeNode node = new TreeNode(v);
            node.left = root;
            return node;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        int dept = 2;
        queue.add(root);
        TreeNode temp, left, right;
        int size;
        while (dept < d && !queue.isEmpty()) {
            size = queue.size();
            while (size-- > 0) {
                temp = queue.poll();
                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
            }
            dept++;
        }
        while (!queue.isEmpty()) {
            temp = queue.poll();
            left = temp.left;
            right = temp.right;
            temp.left = new TreeNode(v);
            temp.right = new TreeNode(v);
            temp.left.left = left;
            temp.right.right = right;
        }
        return root;
    }

    public static int maxDistance(List<List<Integer>> arrays) {
        if (arrays == null || arrays.size() <= 1) {
            return 0;
        }
        int min = arrays.get(0).get(0), max = arrays.get(0).get(arrays.get(0).size() - 1), result = 0, a, b;
        List<Integer> array;
        for (int i = 1; i < arrays.size(); i++) {
            array = arrays.get(i);
            if (array != null && array.size() != 0) {
                a = array.get(0);
                b = array.get(array.size() - 1);
                if (max - a > result) {
                    result = max - a;
                }
                if (b - min > result) {
                    result = b - min;
                }
                if (b > max) {
                    max = b;
                }
                if (a < min) {
                    min = a;
                }
            }
        }
        return result;
    }

    public static int smallestFactorization(int a) {
        if (a <= 1) {
            return a;
        }
        int[] count = new int[11];
        while (a % 2 == 0) {
            count[2]++;
            a /= 2;
        }
        while (a % 3 == 0) {
            count[3]++;
            a /= 3;
        }
        while (a % 5 == 0) {
            count[5]++;
            a /= 5;
        }
        while (a % 7 == 0) {
            count[7]++;
            a /= 7;
        }
        if (a != 1) {
            return 0;
        }
        count[9] = count[3] / 2;
        count[3] = count[3] % 2;
        count[8] = count[2] / 3;
        count[2] = count[2] % 3;
        if (count[3] > 0 && count[2] > 0) {
            count[6] = 1;
            count[3] = 0;
            count[2]--;
        }
        if (count[2] == 2) {
            count[4] = 1;
            count[2] = 0;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < count[i]; j++) {
                builder.append(i);
            }
        }
        String result = builder.toString();
        String max = String.valueOf(Integer.MAX_VALUE);
        if (result.length() > max.length()) {
            return 0;
        }
        if (result.length() == max.length()) {
            for (int i = 0; i < max.length(); i++) {
                if (result.charAt(i) > max.charAt(i)) {
                    return 0;
                } else if (result.charAt(i) < max.charAt(i)) {
                    break;
                }
            }
        }
        return Integer.parseInt(result);
    }

    public static int maximumProduct(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        int a = Integer.MIN_VALUE, b = a, c = a, d = Integer.MAX_VALUE, e = d;
        for (int n : nums) {
            if (n > a) {
                c = b;
                b = a;
                a = n;
            } else if (n > b) {
                c = b;
                b = n;
            } else if (n > c) {
                c = n;
            }
            if (n < d) {
                e = d;
                d = n;
            } else if (n < e) {
                e = n;
            }
        }
        return Math.max(a * b * c, d * e * a);
    }

    public static int findDerangement(int n) {
        if (n == 1) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        int a = 1, b = 0, c, d;
        for (int i = 2; i <= n; i++) {
            System.out.println(i - 1 + " " + (a + b));
            c = 0;
            for (int j = 0; j < i - 1; j++) {
                c = (c + a + b) % 1000000007;
            }
//            c=(i-1)*(a+b)%1000000007;
            a = b;
            b = c;
        }
        return b;
    }

    public static int[] smallestRange(List<List<Integer>> nums) {
        if (nums == null || nums.size() == 0) {
            return null;
        }
        int[] result = new int[]{-100000, 100000};
        int[][] index = new int[nums.size()][3];
        Queue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        int min, max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.size(); i++) {
            index[i][0] = i;
            index[i][1] = 0;
            index[i][2] = nums.get(i).get(0);
            queue.add(index[i]);
            if (index[i][2] > max) {
                max = index[i][2];
            }
        }
        int[] num;
        while (true) {
            num = queue.poll();
            min = num[2];
            if (max - min < result[1] - result[0]) {
                result[0] = min;
                result[1] = max;
            }
            num[1]++;
            if (num[1] == nums.get(num[0]).size()) {
                break;
            }
            num[2] = nums.get(num[0]).get(num[1]);
            if (max < num[2]) {
                max = num[2];
            }
            queue.add(num);
        }
        return result;
    }

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        TreeNode node;
        double sum;
        int count, k;
        while (!queue.isEmpty()) {
            sum = 0;
            count = queue.size();
            k = count;
            while (k-- > 0) {
                node = queue.poll();
                sum += node.val;
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(sum / count);
        }
        return result;
    }

    public static void shopping(int m, int n, List<Integer> price, List<List<Integer>> special, List<Integer> needs, int[] array) {
        if (n == price.size()) {
            if (array[array.length - 1] > array[array.length - 2]) {
                array[array.length - 1] = array[array.length - 2];
            }
        } else if (m == special.size() + 1) {
            if (array[n] == needs.get(n)) {
                shopping(0, n + 1, price, special, needs, array);
            }
        } else if (m == 0) {
            for (int i = 0; i <= needs.get(n) - array[n]; i++) {
                array[n] += i;
                array[array.length - 2] += i * price.get(n);
                shopping(1, n, price, special, needs, array);
                array[n] -= i;
                array[array.length - 2] -= i * price.get(n);
            }
        } else {
            int k, t = needs.get(n) - array[n], j;
            if ((k = special.get(m - 1).get(n)) > 0) {
                for (int i = 1; i * k <= t; i++) {
                    for (j = 0; j < price.size(); j++) {
                        if (i * special.get(m - 1).get(j) + array[j] > needs.get(j)) {
                            break;
                        }
                    }
                    if (j == price.size()) {
                        for (int x = n; x < price.size(); x++) {
                            array[x] += i * special.get(m - 1).get(x);
                        }
                        array[array.length - 2] += i * special.get(m - 1).get(price.size());
                        shopping(m + 1, n, price, special, needs, array);
                        for (int x = n; x < price.size(); x++) {
                            array[x] -= i * special.get(m - 1).get(x);
                        }
                        array[array.length - 2] -= i * special.get(m - 1).get(price.size());
                    } else {
                        break;
                    }
                }
            }
            shopping(m + 1, n, price, special, needs, array);
        }
    }

    public static int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        if (needs == null || needs.size() == 0) {
            return 0;
        }
        int[] array = new int[needs.size() + 2];
        array[array.length - 1] = Integer.MAX_VALUE;
        shopping(0, 0, price, special, needs, array);
        return array[array.length - 1];
    }

    public static void main(String[] args) {
//        System.out.println("hello,world!");
//        System.out.println(checkRecord("LALL"));
//        System.out.println(optimalDivision(new int[]{100,10,1000,10}));
//        System.out.println(leastBricks(new ArrayList<List<Integer>>() {{
//            add(Arrays.asList(1,2,2,1));
//            add(Arrays.asList(3, 1, 2));
//            add(Arrays.asList(1, 3, 2));
//            add(Arrays.asList(3, 1, 2));
//            add(Arrays.asList(1, 3, 1, 1));
//        }}));
//        System.out.println(reverseWords("Let's take LeetCode contest   aaa"));
//        System.out.println(nextGreaterElement(123));
//        System.out.println(singleNonDuplicate(new int[]{1,1,2}));
//        System.out.println(subarraySum(new int[]{-1, -1, -1}, -1));
//        System.out.println(findLHS(new int[]{1,3,2,2,5,2,3,7}));
//        System.out.println(validSquare(new int[]{0,0},new int[]{1,0},new int[]{1,1},new int[]{0,1}));
//        System.out.println(maxCount(3, 3, new int[][]{{1, 3}, {3, 3}}));
//        System.out.println(findUnsortedSubarray(new int[]{6,5,4}));
//        System.out.println(Arrays.toString(findRestaurant(new String[]{"Shogun", "Tapioca Express", "Burger King", "KFC"}, new String[]{"Tapioca Express", "Shogun", "Burger King"})));
//        System.out.println(findMaxDivisor(8,12));
//        System.out.println(fractionAddition("-5/2+10/3+7/9"));
//        TreeNode one = new TreeNode(4);
//        TreeNode two = new TreeNode(2);
//        TreeNode three = new TreeNode(3);
//        TreeNode four = new TreeNode(1);
//        one.left = two;
//        two.left = three;
//        two.right = four;
//        System.out.println(tree2str(one));
//        System.out.println(findDuplicate(new String[]{"root/a 1.txt(abcd) 2.txt(efsfgh) 3.txt(efsfgh)","root/c 3.txt(abdfcd)","root/c/d 4.txt(efggdfh)"}));
//        System.out.println(canPlaceFlowers(new int[]{1, 0, 0, 0, 0, 0, 0, 0, 1}, 3));
//        System.out.println(leastInterval(new char[]{
//                'A', 'A', 'A', 'B', 'B', 'C'}, 2));
//        addOneRow(one, 1, 3);
//        List<List<Integer>> result = new ArrayList<>();
//        result.add(Arrays.asList(4, 10, 15, 24, 26));
//        result.add(Arrays.asList(1, 9, 12, 20));
//        result.add(Arrays.asList(5, 18, 22, 30));
//        System.out.println(maxDistance(result));
//        System.out.println(smallestFactorization(77));
//        System.out.println(maximumProduct(new int[]{1,2,3}));
//        System.out.println(findDerangement(14));
//        System.out.println(Arrays.toString(smallestRange(result)));
        List<Integer> price = new ArrayList<>();
        price.add(4);
        price.add(3);
        price.add(2);
        price.add(9);
        price.add(8);
        price.add(8);
        List<List<Integer>> special = new ArrayList<>();
        List<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(5);
        a.add(5);
        a.add(1);
        a.add(4);
        a.add(0);
        a.add(18);
        List<Integer> b = new ArrayList<>();
        b.add(3);
        b.add(3);
        b.add(6);
        b.add(6);
        b.add(4);
        b.add(2);
        b.add(32);
        special.add(a);
        special.add(b);
        List<Integer> needs = new ArrayList<>();
        needs.add(6);
        needs.add(5);
        needs.add(5);
        needs.add(6);
        needs.add(4);
        needs.add(1);
        System.out.println(shoppingOffers(price, special, needs));
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
