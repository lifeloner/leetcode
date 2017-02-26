package leetcode;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;

/**
 * Created by yang on 2016/9/23.
 */
public class Easy {

    public int findNthDigit(int n) {
        int base = 1, num = 0, k = 1;
        while (num < n && num < 700000000) {
            num += 9 * base * k;
            base *= 10;
            k++;
        }
        if (num > n) {
            base = base / 10;
            k--;
            num = num - base * 9 * k;
        }
        n = n - num;
        num = base;
        base = n / k;
        n = n % k;
        num = num + base - 1;
        if (n == 0) {
            return num % 10;
        }
        num = num + 1;
        k = k - n;
        while (k > 0) {
            num = num / 10;
            k--;
        }
        return num % 10;
    }

    //  public List<String> readBinaryWatch(int num) {
//        List<String> result = new ArrayList<>();
//        if (num == 0) {
//            result.add("0:00");
//            return result;
//        }
//        int[] numOne = new int[]{1, 2, 4, 8};
//        int[] numTwo = new int[]{1, 2, 4, 8, 16, 32};
//        List<Integer> listOne = new ArrayList<>();
//        List<Integer> listTwo = new ArrayList<>();
//        getNum(numOne, listOne, 0, 0, num - 1, 0, 12);
//        if (!listOne.isEmpty()) {
//            for (int i = 0; i < listOne.size(); i++) {
//                result.add(listOne.get(i) + ":00");
//            }
//            listOne.clear();
//        }
//        getNum(numTwo, listTwo, 0, 0, num - 1, 0, 60);
//        if (!listTwo.isEmpty()) {
//            for (int i = 0; i < listTwo.size(); i++) {
//                if (listTwo.get(i) >= 10) {
//                    result.add("0:" + listTwo.get(i));
//                } else {
//                    result.add("0:0" + listTwo.get(i));
//                }
//            }
//            listTwo.clear();
//        }
//        for (int i = 0; num - i - 2 >= 0 && i < num; i++) {
//            getNum(numOne, listOne, 0, 0, i, 0, 12);
//            getNum(numTwo, listTwo, 0, 0, num - i - 2, 0, 60);
//            if (!listOne.isEmpty() && !listTwo.isEmpty()) {
//                for (int k = 0; k < listOne.size(); k++) {
//                    for (int j = 0; j < listTwo.size(); j++) {
//                        if (listTwo.get(j) >= 10) {
//                            result.add(listOne.get(k) + ":" + listTwo.get(j));
//                        } else {
//                            result.add(listOne.get(k) + ":0" + listTwo.get(j));
//                        }
//                    }
//                }
//            }
//            listOne.clear();
//            listTwo.clear();
//        }
//        return result;
    //}

//    public void getNum(int[] nums, List<Integer> list, int k, int sum, int num, int pos, int n) {
//        if (k > num) {
//            list.add(sum);
//            return;
//        }
//        for (int i = pos; num - k + i < nums.length; i++) {
//            if (sum + nums[i] < n) {
//                sum = sum + nums[i];
//                getNum(nums, list, k + 1, sum, num, i + 1, n);
//                sum = sum - nums[i];
//            }
//        }
//    }

    public int sumOfLeftLeaves(TreeNode root) {
        return dfs(root, false);
    }

    public int dfs(TreeNode node, boolean isLeft) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null && isLeft) {
            return node.val;
        }
        int sum = 0;
        if (node.left != null) {
            sum += dfs(node.left, true);
        }
        if (node.right != null) {
            sum += dfs(node.right, false);
        }
        return sum;
    }

    public String toHex(int num) {
        if (num == 0) {
            return "0";
        }
        boolean flag = false;
        if (num < 0) {
            flag = true;
            num = Integer.MAX_VALUE + num + 1;
        }
        List<Integer> list = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        int k;
        while (num != 0) {
            k = num % 16;
            list.add(k);
            num = num >> 4;
        }
        int len = list.size();
        if (flag) {
            if (len == 0) {
                list.add(0);
                list.add(0);
                list.add(0);
                list.add(0);
                list.add(0);
                list.add(0);
                list.add(0);
                list.add(0);
                len = 8;
            }
            list.set(len - 1, list.get(len - 1) + 8);
        }
        for (int i = len - 1; i >= 0; i--) {
            switch (list.get(i)) {
                case 10:
                    builder.append('a');
                    break;
                case 11:
                    builder.append('b');
                    break;
                case 12:
                    builder.append('c');
                    break;
                case 13:
                    builder.append('d');
                    break;
                case 14:
                    builder.append('e');
                    break;
                case 15:
                    builder.append('f');
                    break;
                default:
                    builder.append(list.get(i));
                    break;
            }
        }
        return builder.toString();
    }

    public int longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] count = new int[52];
        char c;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (Character.isUpperCase(c)) {
                count[c - 'A']++;
            } else {
                count[c - 'a' + 26]++;
            }
        }
        int num = 0, k = 0;
        for (int i = 0; i < count.length; i++) {
            if (count[i] % 2 == 0) {
                num += count[i];
                k++;
            } else {
                num += count[i] - 1;
            }
        }
        if (k != count.length) {
            num++;
        }
        return num;
    }

    public String addStrings(String num1, String num2) {
        if (num1 == null || num1.length() == 0) {
            return num2;
        }
        if (num2 == null || num2.length() == 0) {
            return num1;
        }
        StringBuilder builder = new StringBuilder();
        int a, c = 0, len1 = num1.length() - 1, len2 = num2.length() - 1;
        while (len1 >= 0 && len2 >= 0) {
            a = num1.charAt(len1) - '0' + num2.charAt(len2) - '0' + c;
            builder.append(a % 10);
            c = a / 10;
            len1--;
            len2--;
        }
        while (len1 >= 0) {
            a = num1.charAt(len1) - '0' + c;
            builder.append(a % 10);
            c = a / 10;
            len1--;
        }
        while (len2 >= 0) {
            a = num2.charAt(len2) - '0' + c;
            builder.append(a % 10);
            c = a / 10;
            len2--;
        }
        if (c != 0) {
            builder.append(c);
        }
        return builder.reverse().toString();
    }

    public int pathSum(TreeNode root, int sum) {
        int count = 0;
        if (root != null) {
            count += dfsSum(root, sum);
            count += pathSum(root.left, sum);
            count += pathSum(root.right, sum);
        }
        return count;
    }


    public int dfsSum(TreeNode root, int sum) {
        int num = 0;
        if (root != null) {
            sum -= root.val;
            if (sum == 0) {
                num++;
            }
            num += dfsSum(root.left, sum) + dfsSum(root.right, sum);
        }
        return num;
    }

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if (s == null || p == null || s.length() == 0 || p.length() == 0) {
            return list;
        }
        int[] first = new int[26], second = new int[26];
        for (int i = 0; i < p.length(); i++) {
            first[p.charAt(i) - 'a']++;
        }
        int index, start = 0, end = 0;
        while (end < s.length()) {
            index = s.charAt(end) - 'a';
            second[index]++;
            if (second[index] > first[index]) {
                while (start <= end && second[index] != first[index]) {
                    second[s.charAt(start++) - 'a']--;
                }
            }
            if (end - start + 1 == p.length()) {
                list.add(start);
            }
            end++;
        }
        return list;
    }

    public int thirdMax(int[] nums) {
        int len = 0, k, t;
        int[] res = new int[4];
        for (int i = 0; i < nums.length; i++) {
            k = len;
            while (k > 0 && nums[i] > res[k - 1]) {
                res[k] = res[k - 1];
                k--;
            }
            if (k != 0 && res[k - 1] == nums[i]) {
                while (k < len) {
                    res[k] = res[k + 1];
                    k++;
                }
            } else {
                if (len < 3) {
                    len++;
                }
                res[k] = nums[i];
            }
        }
        if (len < 3) {
            return res[0];
        }
        return res[2];
    }

    public int arrangeCoins(int n) {
        if (n <= 0) {
            return 0;
        }
        int k = (int) Math.sqrt(2.0 * n), m;
        if (k % 2 == 0) {
            m = k / 2 * (k + 1);
            if (m > n || m < 0) {
                return k - 1;
            }
            m = (k + 2) / 2 * (k + 1);
            if (m > n || m < 0) {
                return k;
            }
            return k + 1;
        }
        m = (k + 1) / 2 * k;
        if (m > n || m < 0) {
            return k - 1;
        }
        m = (k + 1) / 2 * (k + 2);
        if (m > n || m < 0) {
            return k;
        }
        return k + 1;

    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return list;
        }
        int index, num;
        for (int i = 0; i < nums.length; i++) {
            index = nums[i];
            while (index > 0) {
                num = nums[index - 1];
                nums[index - 1] = 0;
                index = num;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                list.add(i + 1);
            }
        }
        return list;
    }

    public int minMoves(int[] nums) {
        int min = nums[0], count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < min) {
                min = nums[i];
            }
        }
        for (int i = 0; i < nums[i]; i++) {
            count += nums[i] - min;
        }
        return count;
    }

    public int findContentChildren(int[] g, int[] s) {
        if (g == null || s == null) {
            return 0;
        }
        Arrays.sort(g);
        Arrays.sort(s);
        int a = 0, b = 0;
        while (a < g.length && b < s.length) {
            if (s[b] >= g[a]) {
                a++;
            } else {
                b++;
            }
        }
        return a;
    }

    public boolean repeatedSubstringPattern(String str) {
        boolean flag = false;
        if (str == null) {
            return false;
        }
        int slow = 0, fast = 1;
        while (fast < str.length()) {
            flag = false;
            if (str.charAt(fast) == str.charAt(0) && (str.length() % fast == 0)) {
                slow = 0;
                while (fast < str.length() && str.charAt(slow) == str.charAt(fast)) {
                    slow++;
                    fast++;
                    flag = true;
                }
            } else {
                fast++;
            }
        }
        return flag;
    }

    public int minMoves2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int num = findMiddle(nums, 0, nums.length - 1, nums.length / 2), sum = 0;
        for (int k : nums) {
            sum += Math.abs(k - num);
        }
        return sum;
    }

    public int findMiddle(int[] nums, int start, int end, int mid) {
        int k = start++, m = end, temp;
        while (start <= end) {
            while (start <= end && nums[start] <= nums[k]) {
                start++;
            }
            while (start <= end && nums[end] >= nums[k]) {
                end--;
            }
            if (start < end) {
                temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
            }
        }
        temp = nums[k];
        nums[k] = nums[start - 1];
        nums[start - 1] = temp;
        if (start - 1 == mid) {
            return nums[mid];
        } else if (start - 1 > mid) {
            return findMiddle(nums, k, start - 2, mid);
        } else {
            return findMiddle(nums, start, m, mid);
        }
    }

    public int islandPerimeter(int[][] grid) {
        if (grid == null) {
            return 0;
        }
        Queue<Integer> queue = new ArrayDeque<>();
        int x, y, sum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    queue.add(i);
                    queue.add(j);
                    grid[i][j] = -1;
                    while (!queue.isEmpty()) {
                        x = queue.poll();
                        y = queue.poll();
                        if (x - 1 >= 0) {
                            if (grid[x - 1][y] == 0) {
                                sum++;
                            } else if (grid[x - 1][y] == 1) {
                                grid[x - 1][y] = -1;
                                queue.add(x - 1);
                                queue.add(y);
                            }
                        } else {
                            sum++;
                        }
                        if (x + 1 < grid.length) {
                            if (grid[x + 1][y] == 0) {
                                sum++;
                            } else if (grid[x + 1][y] == 1) {
                                grid[x + 1][y] = -1;
                                queue.add(x + 1);
                                queue.add(y);
                            }
                        } else {
                            sum++;
                        }
                        if (y - 1 >= 0) {
                            if (grid[x][y - 1] == 0) {
                                sum++;
                            } else if (grid[x][y - 1] == 1) {
                                grid[x][y - 1] = -1;
                                queue.add(x);
                                queue.add(y - 1);
                            }
                        } else {
                            sum++;
                        }
                        if (y + 1 < grid[x].length) {
                            if (grid[x][y + 1] == 1) {
                                grid[x][y + 1] = -1;
                                queue.add(x);
                                queue.add(y + 1);
                            } else if (grid[x][y + 1] == 0) {
                                sum++;
                            }
                        } else {
                            sum++;
                        }
                    }
                    return sum;
                }
            }
        }
        return sum;
    }

    public boolean find132pattern(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }
        int left = -1;
        for (int i = 1; i < nums.length - 1; i++) {
            if (left != -1 && nums[left] < nums[i]) {
            } else if (nums[i - 1] < nums[i]) {
                left = i - 1;
            } else {
                left = -1;
            }
            if (left != -1) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j] < nums[i] && nums[j] > nums[left]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int count = 0;
        Map<Integer, Integer> first = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                //   first.put(A[i] + B[j], first.getOrDefault(A[i] + B[j], 0) + 1);
            }
        }
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < D.length; j++) {
                //  count += first.getOrDefault(-C[i] - D[j], 0);
            }
        }
        return count;
    }

    public int findRadius(int[] houses, int[] heaters) {
        if (houses == null || houses.length == 0) {
            return 0;
        }
        if (heaters == null || heaters.length == 0) {
            return Integer.MAX_VALUE;
        }
        Arrays.sort(heaters);
        Arrays.sort(houses);
        if (heaters.length == 1) {
            return Math.max(Math.abs(heaters[0] - houses[0]), Math.abs(heaters[0] - houses[houses.length - 1]));
        }
        int result = 0, current = 0, index = 1, mid = (heaters[0] + heaters[1]) / 2;
        while (current < houses.length) {
            if (houses[current] <= mid) {
                result = Math.max(result, Math.abs(houses[current] - heaters[index - 1]));
                current++;
            } else {
                if (index + 1 < heaters.length) {
                    index++;
                    mid = (heaters[index - 1] + heaters[index]) / 2;
                } else {
                    result = Math.max(result, Math.abs(houses[houses.length - 1] - heaters[index]));
                    result = Math.max(result, Math.abs(houses[current] - heaters[index]));
                    break;
                }
            }
        }
        return result;
    }

    public int findMaxForm(String[] strs, int m, int n) {
        if (strs == null || strs.length == 0 || (m == 0 && n == 0)) {
            return 0;
        }
        int[][][] count = new int[m + 1][n + 1][2];
        int k, t;
        for (int i = 0; i <= m; i++) {
            count[i][0][0] = 0;
        }
        for (int i = 0; i <= n; i++) {
            count[0][i][0] = 0;
        }
        for (int i = 0; i < strs.length; i++) {
            for (int j = 0; j <= m; j++) {
                for (k = 0; k <= n; k++) {
                    t = count(strs[i]);
                    if (j >= t && k >= strs[i].length() - t && 1 + count[j - t][k - strs[i].length() + t][0] > count[j][k][0]) {
                        count[j][k][1] = 1 + count[j - t][k - strs[i].length() + t][0];
                    } else {
                        count[j][k][1] = count[j][k][0];
                    }
                }
            }
            for (int j = 0; j <= m; j++) {
                for (k = 0; k <= n; k++) {
                    count[j][k][0] = count[j][k][1];
                    System.out.println(j + " " + k + " " + count[j][k][0]);
                }
            }
        }
        return count[m][n][0];
    }

    public int count(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                count++;
            }
        }
        return count;
    }

    public String licenseKeyFormatting(String S, int K) {
        StringBuilder stringBuilder = new StringBuilder();
        char c;
        for (int i = 0; i < S.length(); i++) {
            c = S.charAt(i);
            if (c - 'a' >= 0 && c - 'z' <= 0) {
                stringBuilder.append((char) (c - 32));
            } else if (c - 'A' >= 0 && c - 'Z' <= 0) {
                stringBuilder.append(c);
            } else if (c - '0' >= 0 && c - '9' <= 0) {
                stringBuilder.append(c);
            }
        }
        StringBuilder builder = new StringBuilder();
        int k = stringBuilder.length() % K, len = stringBuilder.length();
        for (int i = 0; i < k; i++) {
            builder.append(stringBuilder.charAt(i));
        }
        if (k != 0 && k < len) {
            builder.append('-');
        }
        for (int i = k; i < len; i++) {
            builder.append(stringBuilder.charAt(i));
            if (i + 1 != len && (i - k + 1) % K == 0) {
                builder.append('-');
            }
        }
        return builder.toString();
    }

    public int magicalString(int n) {
        if (n < 0) {
            return 0;
        }
        int[] num = new int[n + 5];
        num[0] = 2;
        num[1] = 1;
        int k = 1, index = 1;
        while (index <= n) {
            if (num[k] == 1) {
                if (num[index - 1] == 1) {
                    num[index++] = 2;
                } else {
                    num[index++] = 1;
                }
            } else {
                if (num[index - 1] == 1) {
                    num[index++] = 2;
                    num[index++] = 2;
                } else {
                    num[index++] = 1;
                    num[index++] = 1;
                }
            }
            k++;
        }
        k = 0;
        for (int i = 1; i <= n; i++) {
            if (num[i] == 1) {
                k++;
            }
            System.out.println(i + " " + k);
        }
        return k;
    }

    public int findComplement(int num) {
        if (num == 0) {
            return 0;
        }
        int step = 1, result = 0, k;
        while (num != 0) {
            k = num % 2;
            num = num >> 1;
            if (k == 0) {
                result += step;
            }
            step *= 2;
        }
        return result;
    }

    public boolean makesquare(int[] nums) {
        if (nums.length < 4) {
            return false;
        }
        int sum = 0, edge, k, num;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }
        if (sum % 4 != 0) {
            return false;
        }
        edge = sum / 4;
        List<Integer> list = new ArrayList<>();
        list.addAll(map.keySet());
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        k = 0;
        while (k < list.size()) {
            num = list.get(k);
            if (num > edge) {
                return false;
            } else if (!map.containsKey(num)) {
                k++;
            } else if (num == edge) {
                sum = map.get(edge);
                if (sum == 1) {
                    map.remove(edge);
                    k++;
                } else {
                    map.put(edge, sum - 1);
                }
            } else {
                sum = map.get(num);
                if (sum == 1) {
                    map.remove(num);
                    k++;
                } else {
                    map.put(num, sum - 1);
                }
                if (!dfs(map, list, k, edge - num)) {
                    System.out.println(k + "b");
                    return false;
                }
            }
        }
        return true;
    }

    public boolean dfs(Map<Integer, Integer> map, List<Integer> list, int pos, int num) {
        int k;
        for (int i = pos; i < list.size(); i++) {
            k = list.get(i);
            if (k > num || !map.containsKey(k)) {
                continue;
            } else if (k == num) {
                int temp = map.get(k);
                if (temp == 1) {
                    map.remove(k);
                } else {
                    map.put(num, temp - 1);
                }
                return true;
            } else {
                int temp = map.get(k);
                if (temp == 1) {
                    map.remove(k);
                } else {
                    map.put(k, temp - 1);
                }
                if (dfs(map, list, i, num - k)) {
                    return true;
                }
                if (temp == 1) {
                    map.put(k, 1);
                } else {
                    map.put(k, temp);
                }
            }
        }
        return false;
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int count = 0, max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                max++;
            } else {
                max = 0;
            }
            if (max > count) {
                count = max;
            }
        }
        return count;
    }

    public int[] findMode(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<Integer> list = new ArrayList<>();
        int[] array = new int[2];
        array[0] = 0;
        array[1] = 0;
        list.add(0);
        list.add(0);
        preVisited(root, array, list);
        int k = list.size();
        if (list.get(k - 1) < array[1]) {
            return new int[]{array[0]};
        } else if (list.get(k - 1) == array[1]) {
            list.add(array[0]);
            list.add(array[1]);
        }
        int[] result = new int[list.size() / 2];
        for (int i = 0; i < list.size(); i += 2) {
            result[i / 2] = list.get(i);
        }
        return result;
    }

    public void preVisited(TreeNode root, int[] array, List<Integer> list) {
        if (root == null) {
            return;
        }
        preVisited(root.left, array, list);
        if (root.val == array[0]) {
            array[1]++;
        } else {
            int k = list.get(list.size() - 1);
            if (k < array[1]) {
                list.clear();
                list.add(array[0]);
                list.add(array[1]);
            } else if (k == array[1]) {
                list.add(array[0]);
                list.add(array[1]);
            }
            array[0] = root.val;
            array[1] = 1;
        }
        preVisited(root.right, array, list);
    }

    public int[] constructRectangle(int area) {
        int[] array = new int[2];
        array[0] = 0;
        array[1] = 0;
        if (area == 0) {
            return array;
        }
        for (int i = 1; i * i <= area; i++) {
            if (area % i == 0) {
                array[0] = area / i;
                array[1] = i;
            }
        }
        return array;
    }

    public int[] nextGreaterElement(int[] findNums, int[] nums) {
        if (findNums == null) {
            return null;
        }
        int[] result = new int[findNums.length];
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            while (!stack.empty() && stack.peek() < num) {
                map.put(stack.pop(), num);
            }
            stack.push(num);
        }
        for (int i = 0; i < findNums.length; i++) {
            result[i] = map.getOrDefault(findNums[i], -1);
        }
        return result;
    }

    public static void main(String[] args) {
        Easy easy = new Easy();
//        System.out.println(easy.fi
        //  System.out.println(easy.addStrings("899","16"));ndNthDigit(189));
//        System.out.println(easy.toHex(0));
//        System.out.println(easy.longestPalindrome("ccccAAdd"));
//        TreeNode a = new TreeNode(10);
//        TreeNode b = new TreeNode(5);
//        TreeNode c = new TreeNode(13);
//        TreeNode d = new TreeNode(13);
//        TreeNode e = new TreeNode(5);
//        TreeNode f = new TreeNode(5);
//        TreeNode g = new TreeNode(13);
//        a.left = b;
//        b.left=e;
//        b.right=f;
//        a.right = c;
//        c.left=d;
//        c.right=g;
//        TreeNode d=new TreeNode(3);
//        TreeNode e=new TreeNode(2);
//        b.left=d;
//        b.right=e;
//        TreeNode f=new TreeNode(11);
//        c.right=f;
//        TreeNode g=new TreeNode(3);
//        TreeNode h=new TreeNode(-2);
//        d.left=g;
//        d.right=h;
//        TreeNode i=new TreeNode(1);
//        e.right=i;
//        System.out.println(easy.pathSum(a,8));
        //System.out.println(easy.findAnagrams("cbaebabacd","abc"));
        //System.out.println(easy.thirdMax(new int[]{2,2,3,1,4,3,1,0,5}));
//        System.out.println(easy.arrangeCoins(2147483647));
//        System.out.println(easy.findDisappearedNumbers(new int[]{4,3,2,7,8,2,3,1}));
//        System.out.println(easy.repeatedSubstringPattern("ababa"));
//        System.out.println(easy.minMoves2(new int[]{1, 2, 3, 5}));
//        System.out.println(easy.islandPerimeter(new int[][]{{1,1,1}}));
//        System.out.println(easy.find132pattern(new int[]{-2,1,2,-2,1,2}));
//        System.out.println(easy.fourSumCount(new int[]{1, 2}, new int[]{-2, -1}, new int[]{-1, 2}, new int[]{0, 2}));
//        System.out.println(easy.findRadius(new int[]{1, 2, 3}, new int[]{1, 2, 3}));
//        System.out.println(easy.findMaxForm(new String[]{"10", "0001", "111001", "1", "0"},5,3));
//        System.out.println(easy.licenseKeyFormatting("a",2));
//        System.out.println(easy.magicalString(2));
//        System.out.println(easy.findComplement(5));
//        System.out.println(easy.makesquare(new int[]{2,2,2,1,1}));
//        System.out.println(easy.findMaxConsecutiveOnes(new int[]{1, 1, 1, 1, 0, 1, 1, 1}));
//        System.out.println(Arrays.toString(easy.findMode(a)));
//        System.out.println(Arrays.toString(easy.constructRectangle(10000000)));
        System.out.println(Arrays.toString(easy.nextGreaterElement(new int[]{4, 1, 2}, new int[]{1, 2, 3, 4})));
    }

}
