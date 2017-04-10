package leetcode;

import org.omg.PortableInterceptor.INACTIVE;

import java.time.temporal.Temporal;
import java.util.*;

/**
 * Created by fuyang on 16/9/14.
 */

public class Medium {

    public int superPow(int a, int[] b) {
        if (b == null || b.length == 0) {
            return 1;
        }
        int[] array = new int[10];
        array[0] = 1;
        a = a % 1337;
        for (int i = 1; i < 10; i++) {
            array[i] = array[i - 1] * a % 1337;
        }
        int result = 1, temp;
        for (int i = 0; i < b.length; i++) {
            result = result * result % 1337;
            temp = result;
            result = result * result % 1337;
            result = result * result % 1337;
            result = result * temp % 1337;
            result = result * array[b[i]] % 1337;
        }
        return result;
    }

    public int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        int begin = 0, end = numbers.length - 1;
        while (begin < end) {
            if (numbers[begin] + numbers[end] == target) {
                result[0] = begin + 1;
                result[1] = end + 1;
                return result;
            } else if (numbers[begin] + numbers[end] > target) {
                end--;
            } else {
                begin++;
            }
        }
        return result;
    }

    public int getMoneyAmount(int n) {
        if (n == 1) {
            return 0;
        }
        int[][] num = new int[n][n];
        for (int i = 0; i < n; i++) {
            num[i][i] = 0;
        }
        int max;
        for (int k = 1; k < n; k++) {
            for (int i = 0; i + k < n; i++) {
                num[i][i + k] = Math.min(i + 1 + num[i + 1][i + k], i + k + 1 + num[i][i + k - 1]);
                for (int j = 1; j < k; j++) {
                    max = Math.max(num[i][i + j - 1], num[i + j + 1][i + k]);
                    if (max + i + j + 1 < num[i][i + k]) {
                        num[i][i + k] = max + i + j + 1;
                    }
                }
            }
        }
        return num[0][n - 1];
    }

    public boolean isValidSerialization(String preorder) {
        if (preorder == null || preorder.length() == 0) {
            return true;
        }
        String[] strs = preorder.split(",");
        int result = 2;
        if (strs[0].equals("#")) {
            result = 0;
        }
        for (int i = 1; i < strs.length; i++) {
            if (result == 0) {
                return false;
            }
            if (strs[i].equals("#")) {
                result--;
            } else {
                result++;
            }
        }
        if (result == 0) {
            return true;
        }
        return false;
    }

    public int wiggleMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] pos = new int[nums.length];
        int[] neg = new int[nums.length];
        pos[0] = 1;
        neg[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] < nums[i]) {
                pos[i] = neg[i - 1] + 1;
                neg[i] = neg[i - 1];
            } else if (nums[i - 1] > nums[i]) {
                neg[i] = pos[i - 1] + 1;
                pos[i] = pos[i - 1];
            } else {
                neg[i] = neg[i - 1];
                pos[i] = pos[i - 1];
            }
        }
        return Math.max(pos[pos.length - 1], neg[neg.length - 1]);
    }

    // public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
//        List<int[]> result = new ArrayList<>();
//        if (k == 0 || nums1.length == 0 || nums2.length == 0 || nums1 == null || nums2 == null) {
//            return result;
//        }
//        boolean pos = true;
//        if (k > nums1.length * nums2.length) {
//            k = nums1.length * nums2.length;
//        }
//        if (nums1.length > nums2.length) {
//            int[] temp = nums1;
//            nums1 = nums2;
//            nums2 = temp;
//            pos = false;
//        }
//        int[] nums3 = new int[nums1.length];
//        int begin = 0, end = 0, min;
//        nums3[0] = 0;
//        for (int i = 0; i < k; i++) {
//            min = begin;
//            for (int j = begin + 1; j <= end; j++) {
//                if (nums1[min] + nums2[nums3[min]] > nums1[j] + nums2[nums3[j]]) {
//                    min = j;
//                }
//            }
//            int[] num = new int[2];
//            if (pos) {
//                num[0] = nums1[min];
//                num[1] = nums2[nums3[min]];
//            } else {
//                num[0] = nums2[nums3[min]];
//                num[1] = nums1[min];
//            }
//            result.add(num);
//            if (nums3[min] == 0 && end + 1 < nums3.length) {
//                nums3[++end] = 0;
//            }
//            if (++nums3[min] == nums2.length) {
//                begin++;
//            }
//        }
//        return result;
    // }

    // public List<String> findItinerary(String[][] tickets) {
//        List<String> result = new ArrayList<>();
//        if (tickets == null || tickets.length == 0) {
//            return result;
//        }
//        Map<String, Integer> index = new HashMap<>();
//        List<String>[] array = new List[tickets.length + 1];
//        boolean[][] visited = new boolean[tickets.length + 1][tickets.length + 1];
//        Comparator<String> comparator = new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return o1.compareTo(o2);
//            }
//        };
//        int k = 0, t;
//        for (int i = 0; i < tickets.length; i++) {
//            if (!index.containsKey(tickets[i][0])) {
//                index.put(tickets[i][0], k);
//                array[k++] = new ArrayList<>();
//
//            }
//            t = index.get(tickets[i][0]);
//            array[t].add(tickets[i][1]);
//            if (!index.containsKey(tickets[i][1])) {
//                index.put(tickets[i][1], k);
//                array[k++] = new ArrayList<>();
//            }
//        }
//        for (int i = 0; i < k; i++) {
//            Collections.sort(array[i], comparator);
//            System.out.println(array[i]);
//            for (int j = 0; j < array[i].size(); j++) {
//                visited[i][j] = false;
//            }
//        }
//        if (!index.containsKey("JFK")) {
//            return result;
//        }
//        result.add("JFK");
//        t = index.get("JFK");
//        dfs(result, array, index, visited, t, tickets.length + 1);
//        return result;
    // }

    public boolean dfs(List<String> result, List<String>[] array, Map<String, Integer> map, boolean[][] visited, int k, int n) {
        if (n == result.size()) {
            return true;
        }
        for (int i = 0; i < array[k].size(); i++) {
            if (!visited[k][i]) {
                visited[k][i] = true;
                result.add(array[k].get(i));
                if (dfs(result, array, map, visited, map.get(array[k].get(i)), n)) {
                    return true;
                }
                result.remove(result.size() - 1);
                visited[k][i] = false;
            }
        }
        return false;
    }

    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int t, s;
        int[][] array = new int[nums.length][nums.length];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j + i < nums.length; j++) {
                t = 0;
                for (int k = j; k <= i + j; k++) {
                    s = nums[k];
                    if (j - 1 >= 0) {
                        s *= nums[j - 1];
                    }
                    if (i + j + 1 < nums.length) {
                        s *= nums[i + j + 1];
                    }
                    if (k - 1 >= j) {
                        s += array[j][k - 1];
                    }
                    if (k + 1 <= i + j) {
                        s += array[k + 1][i + j];
                    }
                    if (t < s) {
                        t = s;
                    }
                }
                array[j][i + j] = t;
            }
        }
        return array[0][nums.length - 1];
    }

    public String removeKdigits(String num, int k) {
        if (k == 0 || num == null || num.length() == 0) {
            return num;
        }
        Stack<Character> stack = new Stack<>();
        int count = 0;
        char ch;
        for (int i = 0; i < num.length(); i++) {
            while (!stack.empty() && count < k) {
                ch = stack.peek();
                if (ch > num.charAt(i)) {
                    stack.pop();
                    if (++count == k) {
                        break;
                    }
                } else {
                    break;
                }
            }
            stack.push(num.charAt(i));
        }
        while (count < k) {
            stack.pop();
            count++;
        }
        StringBuilder result = new StringBuilder();
        while (!stack.empty()) {
            result.insert(0, stack.pop());
        }
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) != '0') {
                return result.substring(i);
            }
        }
        return "0";
    }

    public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0) {
            return people;
        }
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                }
                return o2[1] - o1[1];
            }
        });
        int[] count = new int[people.length + 1];
        for (int i = 0; i < count.length; i++) {
            count[i] = 0;
        }
        int k, t, sum;
        int[][] result = new int[people.length][];
        for (int i = 0; i < people.length; i++) {
            sum = 0;
            do {
                k = people[i][1] + sum + 1;
                sum = 0;
                t = k;
                while (k != 0) {
                    sum += count[k];
                    k -= k & (-k);
                }
                k = t;
            } while (k - sum <= people[i][1]);
            result[k - 1] = people[i];
            while (k < count.length) {
                count[k]++;
                k += k & (-k);
            }
        }
        return result;
    }

    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) {
            return true;
        }
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % 2 != 0) {
            return false;
        }
        sum /= 2;
        boolean[][] partition = new boolean[nums.length + 1][sum + 1];
        partition[nums.length][0] = true;
        for (int i = 1; i <= sum; i++) {
            partition[nums.length][i] = false;
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            for (int j = 0; j <= sum; j++) {
                partition[i][j] = partition[i + 1][j];
                if (j >= nums[i] && partition[i + 1][j - nums[i]]) {
                    partition[i][j] = true;
                }
            }
        }
        return partition[0][sum];
    }

//    public List<int[]> pacificAtlantic(int[][] matrix) {
//        List<int[]> list = new ArrayList<>();
//        if (matrix == null || matrix.length == 0) {
//            return list;
//        }
//        Queue<int[]> queue = new ArrayDeque<>();
//        int row = matrix.length, cloumn = matrix[0].length;
//        for (int i = 0; i < row; i++) {
//            queue.add(new int[]{i, 0});
//        }
//        for (int i = 1; i < cloumn; i++) {
//            queue.add(new int[]{0, i});
//        }
//        int[] array;
//        boolean[][] pacific = new boolean[row][cloumn];
//        boolean[][] atlantic = new boolean[row][cloumn];
//        while (!queue.isEmpty()) {
//            array = queue.poll();
//            pacific[array[0]][array[1]] = true;
//            if (array[0] > 0 && !pacific[array[0] - 1][array[1]] && matrix[array[0] - 1][array[1]] >= matrix[array[0]][array[1]]) {
//                queue.add(new int[]{array[0] - 1, array[1]});
//            }
//            if (array[0] + 1 < row && !pacific[array[0] + 1][array[1]] && matrix[array[0] + 1][array[1]] >= matrix[array[0]][array[1]]) {
//                queue.add(new int[]{array[0] + 1, array[1]});
//            }
//            if (array[1] > 0 && !pacific[array[0]][array[1] - 1] && matrix[array[0]][array[1] - 1] >= matrix[array[0]][array[1]]) {
//                queue.add(new int[]{array[0], array[1] - 1});
//            }
//            if (array[1] + 1 < cloumn && !pacific[array[0]][array[1] + 1] && matrix[array[0]][array[1] + 1] >= matrix[array[0]][array[1]]) {
//                queue.add(new int[]{array[0], array[1] + 1});
//            }
//        }
//        for (int i = 0; i < row; i++) {
//            queue.add(new int[]{i, cloumn - 1});
//        }
//        for (int i = 0; i < cloumn - 1; i++) {
//            queue.add(new int[]{row - 1, i});
//        }
//        while (!queue.isEmpty()) {
//            array = queue.poll();
//            atlantic[array[0]][array[1]] = true;
//            if (array[0] > 0 && !atlantic[array[0] - 1][array[1]] && matrix[array[0] - 1][array[1]] >= matrix[array[0]][array[1]]) {
//                queue.add(new int[]{array[0] - 1, array[1]});
//            }
//            if (array[0] + 1 < row && !atlantic[array[0] + 1][array[1]] && matrix[array[0] + 1][array[1]] >= matrix[array[0]][array[1]]) {
//                queue.add(new int[]{array[0] + 1, array[1]});
//            }
//            if (array[1] > 0 && !atlantic[array[0]][array[1] - 1] && matrix[array[0]][array[1] - 1] >= matrix[array[0]][array[1]]) {
//                queue.add(new int[]{array[0], array[1] - 1});
//            }
//            if (array[1] + 1 < cloumn && !atlantic[array[0]][array[1] + 1] && matrix[array[0]][array[1] + 1] >= matrix[array[0]][array[1]]) {
//                queue.add(new int[]{array[0], array[1] + 1});
//            }
//        }
//        for (int i = 0; i < row; i++) {
//            for (int j = 0; j < cloumn; j++) {
//                if (pacific[i][j] && atlantic[i][j]) {
//                    list.add(new int[]{i, j});
//                    System.out.println(i + " " + j);
//                }
//            }
//        }
//        return list;
//    }

    public String originalDigits(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        int[] res = new int[10];
        char c;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            switch (c) {
                case 'z':
                    res[0]++;
                    break;
                case 'o':
                    res[1]++;
                    break;
                case 'w':
                    res[2]++;
                    break;
                case 'h':
                    res[3]++;
                    break;
                case 'u':
                    res[4]++;
                    break;
                case 'f':
                    res[5]++;
                    break;
                case 'x':
                    res[6]++;
                    break;
                case 'v':
                    res[7]++;
                    break;
                case 'g':
                    res[8]++;
                    break;
                case 'i':
                    res[9]++;
                    break;
                default:
                    break;
            }
        }
        res[5] -= res[4];
        res[7] -= res[5];
        res[3] -= res[8];
        res[1] -= res[0] + res[2] + res[4];
        res[9] -= res[5] + res[6] + res[8];
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < res[i]; j++) {
                stringBuilder.append(i);
            }
        }
        return stringBuilder.toString();
    }

    public int characterReplacement(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int max = 0, result = 0, left = 0;
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'A']++;
            max = Math.max(max, count[s.charAt(i) - 'A']);
            if (i - left + 1 - max > k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }
            result = Math.max(result, i - left + 1);
        }
        return result;
    }

    public int findMaximumXOR(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Node root = new Node();
        for (int i = 0; i < nums.length; i++) {
            buildTree(nums[i], root);
        }
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, findMax(nums[i], root));

        }
        return max;
    }

    public int findMax(int num, Node root) {
        int max = 0;
        List<Integer> list = new ArrayList<>();
        while (num != 0) {
            list.add(num - (num >> 1 << 1));
            num = num >> 1;
        }
        int k = 31 - list.size();
        while (k > 0) {
            if (root.left == null) {
                root.left = new Node();
            }
            root = root.left;
            k--;
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            k = list.get(i);
            max = max << 1;
            if (root == null) {
                continue;
            }
            if (k == 0) {
                if (root.right != null) {
                    max += 1;
                    root = root.right;
                } else {
                    root = root.left;
                }
            } else {
                if (root.left != null) {
                    max += 1;
                    root = root.left;
                } else {
                    root = root.right;
                }
            }
        }
        return max;
    }


    public void buildTree(int num, Node root) {
        List<Integer> list = new ArrayList<>();
        while (num != 0) {
            list.add(num - (num >> 1 << 1));
            num = num >> 1;
        }
        int k = 31 - list.size();
        while (k > 0) {
            if (root.left == null) {
                root.left = new Node();
            }
            root = root.left;
            k--;
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            k = list.get(i);
            if (k == 0) {
                if (root.left == null) {
                    root.left = new Node();
                }
                root = root.left;
            } else {
                if (root.right == null) {
                    root.right = new Node();
                }
                root = root.right;
            }
        }
    }

    public int[] findRightInterval1(Interval[] intervals) {
        if (intervals == null) {
            return null;
        }
        int[] result = new int[intervals.length];
        if (intervals.length == 0) {
            return result;
        }
        Interval[] nums = new Interval[intervals.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = new Interval(intervals[i].start, i);
        }
        Arrays.sort(nums, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        for (int i = 0; i < intervals.length; i++) {
            result[i] = search(nums, intervals[i].end);
        }
        return result;
    }

    public int search(Interval[] intervals, int num) {
        int begin = 0, end = intervals.length - 1, mid, result = -1;
        while (begin <= end) {
            mid = (begin + end) >> 1;
            if (intervals[mid].start == num) {
                return intervals[mid].end;
            } else if (intervals[mid].start < num) {
                begin = mid + 1;
            } else {
                end = mid - 1;
                result = intervals[mid].end;
            }
        }
        return result;
    }

    public int[] findRightInterval(Interval[] intervals) {
        if (intervals == null) {
            return null;
        }
        int[] result = new int[intervals.length];
        if (intervals.length == 0) {
            return result;
        }
        TreeNode root = new TreeNode(intervals[0].start, 0);
        for (int i = 1; i < intervals.length; i++) {
            add(root, intervals[i], i);
        }
        for (int i = 0; i < intervals.length; i++) {
            result[i] = search(root, intervals[i]);
        }
        return result;
    }

    public int search(TreeNode root, Interval interval) {
        int result = -1;
        while (root != null) {
            if (interval.end > root.value) {
                root = root.right;
            } else if (interval.end < root.value) {
                root = root.left;
                result = root.index;
            } else {
                return root.index;
            }
        }
        if (root == null || root.right == null) {
            return result;
        }
        root = root.right;
        result = root.index;
        while (root != null) {
            result = root.index;
            root = root.left;
        }
        return result;
    }

    public void add(TreeNode root, Interval interval, int index) {
        while (root != null) {
            if (interval.start < root.value) {
                if (root.left == null) {
                    TreeNode node = new TreeNode(interval.start, index);
                    root.left = node;
                    return;
                } else {
                    root = root.left;
                }
            } else {
                if (root.right == null) {
                    TreeNode node = new TreeNode(interval.start, index);
                    root.right = node;
                    return;
                } else {
                    root = root.right;
                }
            }
        }
    }

    public int eraseOverlapIntervals(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        int[] num = new int[intervals.length];
        int max, result = 1;
        num[num.length - 1] = 1;
        for (int i = num.length - 2; i >= 0; i--) {
            max = 0;
            for (int j = i + 1; j < num.length; j++) {
                if (intervals[i].end <= intervals[j].start && num[j] > max) {
                    max = num[j];
                }
            }
            num[i] = max + 1;
            if (num[i] > result) {
                result = num[i];
            }
        }
        return intervals.length - result;
    }

    public int lastRemaining(int n) {
        if (n == 1) {
            return 1;
        }
        int len = 0, result = 1;
        int[] bits = new int[33];
        while (n != 1) {
            bits[++len] = n;
            n = n >> 1;
        }
        for (int i = len; i >= 1; i--) {
            result *= 2;
            if (i % 2 == 0 && bits[i] % 2 == 0) {
                result--;
            }
        }
        return result;
    }

    public int minMutation(String start, String end, String[] bank) {
        if (start == null || end == null || bank == null || bank.length == 0) {
            return -1;
        }
        int index = 0, result = 0, len;
        Queue<String> queue = new ArrayDeque<>();
        queue.add(start);
        String string;
        while (!queue.isEmpty()) {
            len = queue.size();
            while (len-- > 0) {
                string = queue.poll();
                if (string.equals(end)) {
                    return result;
                }
                for (int i = index; i < bank.length; i++) {
                    if (compareString(string, bank[i])) {
                        queue.add(bank[i]);
                        bank[i] = bank[index++];
                    }
                }
            }
            result++;
        }
        return -1;
    }

    public boolean compareString(String a, String b) {
        int count = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                count++;
                if (count > 1) {
                    return false;
                }
            }
        }
        return count == 1 ? true : false;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode first = l1, second = l2, head = null, current, temp;
        int len1 = 0, len2 = 0, k;
        while (first != null) {
            len1++;
            first = first.next;
        }
        while (second != null) {
            len2++;
            second = second.next;
        }
        if (len1 >= len2) {
            first = l1;
            second = l2;
        } else {
            first = l2;
            second = l1;
            k = len1;
            len1 = len2;
            len2 = k;
        }
        while (len1 > len2) {
            current = new ListNode(first.val);
            current.next = head;
            head = current;
            first = first.next;
            len1--;
        }
        while (len1-- > 0) {
            current = new ListNode(first.val + second.val);
            current.next = head;
            head = current;
            first = first.next;
            second = second.next;
        }
        k = head.val / 10;
        head.val = head.val % 10;
        current = head.next;
        head.next = null;
        while (current != null) {
            len1 = k + current.val;
            k = len1 / 10;
            current.val = len1 % 10;
            temp = current.next;
            current.next = head;
            head = current;
            current = temp;
        }
        if (k != 0) {
            current = new ListNode(k);
            current.next = head;
            head = current;
        }
        return head;
    }

    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> list = new ArrayList<>();
        if (nums == null && nums.length == 0) {
            return list;
        }
        int k;
        for (int i = 0; i < nums.length; i++) {
            k = nums[i];
            if (k < 0) {
                k = -k;
            }
            if (nums[k - 1] > 0) {
                nums[k - 1] *= -1;
            } else {
                list.add(k);
            }
        }
        return list;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode node = root, parent = null;
        while (node != null) {
            if (node.value < key) {
                parent = node;
                node = node.right;
            } else if (node.value == key) {
                TreeNode left = node.left, right = node.right;
                if (parent == null) {
                    if (left == null) {
                        return right;
                    } else if (right == null) {
                        return left;
                    } else {
                        parent = right;
                        while (right.left != null) {
                            right = right.left;
                        }
                        right.left = left;
                        return parent;
                    }

                }
                if (right == null) {
                    if (parent.left == node) {
                        parent.left = left;
                    } else {
                        parent.right = left;
                    }
                } else {
                    if (parent.left == node) {
                        parent.left = right;
                    } else {
                        parent.right = right;
                    }
                    while (right.left != null) {
                        right = right.left;
                    }
                    right.left = left;
                }
                return root;
            } else {
                parent = node;
                node = node.left;
            }
        }
        return root;
    }

    public String frequencySort(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        Map<Character, Integer> map = new HashMap<>();
        char c;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).getValue(); j++) {
                builder.append(list.get(i).getKey());
            }
        }
        return builder.toString();
    }

    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        if (minutesToTest == 0) {
            return 0;
        }
        int count = minutesToTest / minutesToDie + 1;
        int result = (int) (Math.ceil(Math.log(buckets) / Math.log(count)));
        return result;
    }


    public boolean circularArrayLoop(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int slow, fast, len = nums.length, k, t;
        boolean forward;
        for (int i = 0; i < len; i++) {
            if (nums[i] != 0) {
                forward = nums[i] > 0 ? true : false;
                slow = ((i + nums[i]) % len + len) % len;
                if ((nums[slow] > 0 && forward) || (nums[slow] < 0 && !forward)) {
                    fast = ((slow + nums[slow]) % len + len) % len;
                } else {
                    nums[i] = 0;
                    continue;
                }
                if (fast == slow) {
                    nums[i] = 0;
                    continue;
                }
                while (fast != slow) {
                    if ((nums[fast] > 0 && forward) || (nums[fast] < 0 && !forward)) {
                        fast = ((fast + nums[fast]) % len + len) % len;
                        if ((nums[fast] > 0 && forward) || (nums[fast] < 0 && !forward)) {
                            fast = ((fast + nums[fast]) % len + len) % len;
                            slow = ((slow + nums[slow]) % len + len) % len;
                        } else {
                            t = slow;
                            slow = i;
                            while (slow != fast) {
                                k = ((slow + nums[slow]) % len + len) % len;
                                nums[slow] = 0;
                                slow = k;
                            }
                            slow = t;
                            break;
                        }
                    } else {
                        t = slow;
                        slow = i;
                        while (slow != fast) {
                            k = ((slow + nums[slow]) % len + len) % len;
                            nums[slow] = 0;
                            slow = k;
                        }
                        slow = t;
                        break;
                    }
                }
                if (fast == slow) {
                    return true;
                }
            }
        }
        return false;
    }

    public int numberOfBoomerangs(int[][] points) {
        if (points == null || points.length < 3) {
            return 0;
        }
        int result = 0, num;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    continue;
                }
                num = distance(points[i], points[j]);
                if (map.containsKey(num)) {
                    map.put(num, map.get(num) + 1);
                } else {
                    map.put(num, 1);
                }
            }
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                result += entry.getValue() * (entry.getValue() - 1);
            }
            map.clear();
        }
        return result;
    }

    public int distance(int[] a, int[] b) {
        return (a[0] - b[0]) * (a[0] - b[0]) + (a[1] - b[1]) * (a[1] - b[1]);
    }

    public int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });
        int left = points[0][0], right = points[0][1], result = 0;
        for (int i = 1; i < points.length; i++) {
            if (points[i][1] <= right) {
                right = points[i][1];
                left = points[i][0];
            } else if (points[i][0] <= right) {
                left = points[i][0];
            } else {
                result++;
                left = points[i][0];
                right = points[i][1];
            }
        }
        return ++result;
    }

//    public String serialize(TreeNode root) {
//        if (root == null) {
//            return null;
//        }
//        StringBuilder builder = new StringBuilder();
//        Stack<TreeNode> stack = new Stack<>();
//        stack.add(root);
//        serialize(stack, builder);
//        return builder.toString();
//    }

    public void serialize(Stack<TreeNode> stack, StringBuilder builder) {
        if (!stack.empty()) {
            TreeNode node = stack.pop();
            builder.append(node.value + ",");
            if (node.left != null) {
                stack.add(node.left);
                serialize(stack, builder);
            }
            if (node.right != null) {
                stack.add(node.right);
                serialize(stack, builder);
            }
        }
    }

    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        String[] array = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(array[0]), 0), node, temp;
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        int num;
        for (int i = 1; i < array.length; i++) {
            if (!stack.empty()) {
                node = stack.peek();
                num = Integer.parseInt(array[i]);
                if (num < node.value) {
                    temp = new TreeNode(num, 0);
                    node.left = temp;
                    stack.add(temp);
                    System.out.println("left " + num);
                } else {
                    node = stack.pop();
                    temp = node;
                    while (!stack.empty() && num > node.value) {
                        temp = node;
                        node = stack.pop();
                    }
                    if (num > node.value) {
                        System.out.println("big " + node.value);
                        temp = new TreeNode(num, 0);
                        node.right = temp;
                        stack.add(temp);
                    } else {
                        stack.add(node);
                        System.out.println("less " + temp.value);
                        node = new TreeNode(num, 0);
                        temp.right = node;
                        stack.add(node);
                    }
                }
            }
        }
        return root;
    }

    public int totalHammingDistance(int[] nums) {
        int result = 0, a, b, num = 0;
        while (true) {
            a = 0;
            b = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] % 2 == 0) {
                    a++;
                } else {
                    b++;
                }
                nums[i] = nums[i] >> 1;
                if (nums[i] > num) {
                    num = nums[i];
                }
            }
            result += a * b;
            if (num == 0) {
                break;
            }
            num = 0;
        }
        return result;
    }

    public int findPoisonedDuration(int[] timeSeries, int duration) {
        if (timeSeries == null || timeSeries.length == 0) {
            return 0;
        }
        int result = 0;
        for (int i = 1; i < timeSeries.length; i++) {
            if (timeSeries[i] - timeSeries[i - 1] >= duration) {
                result += duration;
            } else {
                result += timeSeries[i] - timeSeries[i - 1];
            }
        }
        return result + duration;
    }

    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (S > sum) {
            return 0;
        }
        int[][] array = new int[nums.length][2 * sum + 1];
        for (int i = 0; i < 2 * sum + 1; i++) {
            array[0][i] = 0;
        }
        if (nums[0] == 0) {
            array[0][sum] = 2;
        } else {
            array[0][nums[0] + sum] = 1;
            array[0][sum - nums[0]] = 1;
        }
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < 2 * sum + 1; j++) {
                array[i][j] = 0;
                if (j + nums[i] < 2 * sum + 1) {
                    array[i][j] += array[i - 1][j + nums[i]];
                }
                if (j >= nums[i]) {
                    array[i][j] += array[i - 1][j - nums[i]];
                }
            }
        }
        return array[nums.length - 1][S + sum];
    }

    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        Set<Integer> set = new HashSet<>();
        Set<Integer> sets;
        List<Integer> num = new ArrayList<>(), temp, temps, numss = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                continue;
            }
            set.add(nums[i]);
            sets = new HashSet<>();
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[i] || sets.contains(nums[j])) {
                    continue;
                }
                temp = new ArrayList<>();
                temp.add(nums[i]);
                temp.add(nums[j]);
                result.add(temp);
                num.add(result.size() - 1);
                num.add(j);
                sets.add(nums[j]);
            }
        }
        int index, k;
        while (num.size() > 0) {
            for (int i = 0; i < num.size(); i += 2) {
                temp = result.get(num.get(i));
                index = num.get(i + 1);
                k = temp.get(temp.size() - 1);
                set = new HashSet<>();
                for (int j = index + 1; j < nums.length; j++) {
                    if (nums[j] < k || set.contains(nums[j])) {
                        continue;
                    }
                    set.add(nums[j]);
                    temps = new ArrayList<>();
                    temps.addAll(temp);
                    temps.add(nums[j]);
                    result.add(temps);
                    numss.add(result.size() - 1);
                    numss.add(j);
                }
            }
            num = numss;
            numss = new ArrayList<>();
        }
        return result;
    }

    public int[] nextGreaterElements(int[] nums) {
        if (nums == null) {
            return null;
        }
        int[] result = new int[nums.length];
        Map<Integer, Integer> index = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<Integer> list;
        for (int k = 0; k < 2; k++) {
            for (int i = 0; i < nums.length; i++) {
                while (!stack.empty() && stack.peek() < nums[i]) {
                    if (!map.containsKey(stack.peek())) {
                        list = new ArrayList<>();
                        list.add(nums[i]);
                        map.put(stack.pop(), list);
                    } else {
                        map.get(stack.pop()).add(nums[i]);
                    }
                }
                if (k == 0) {
                    stack.push(nums[i]);
                } else {
                    if (index.containsKey(nums[i])) {
                        list = map.getOrDefault(nums[i], null);
                        if (list != null) {
                            result[i] = list.get(index.get(nums[i]));
                            index.put(nums[i], index.get(nums[i]) + 1);
                        } else {
                            result[i] = -1;
                        }
                    } else {
                        list = map.getOrDefault(nums[i], null);
                        if (list != null) {
                            result[i] = list.get(0);
                            index.put(nums[i], 1);
                        } else {
                            result[i] = -1;
                        }
                    }
                }
            }
        }
        return result;
    }

    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return null;
        }
        int m = matrix.length, n = matrix[0].length, index = 0, a = 0, b = 0, c = -1, d = 1;
        int[] result = new int[m * n];
        while (index < m * n) {
            result[index++] = matrix[a][b];
            if (b + d >= n) {
                a++;
                c = 1;
                d = -1;
            } else if (a + c < 0) {
                b++;
                c = 1;
                d = -1;
            } else if (a + c >= m) {
                b++;
                c = -1;
                d = 1;
            } else if (b + d < 0) {
                a++;
                c = -1;
                d = 1;
            } else {
                a += c;
                b += d;
            }
        }
        return result;
    }

    public int findBottomLeftValue(TreeNode root) {
        int[] num = postVisited(root, true);
        return num[1];
    }

    public int[] postVisited(TreeNode root, boolean isLeft) {
        if (root == null) {
            return new int[]{0, 0};
        }
        int[] left = postVisited(root.left, true);
        int[] right = postVisited(root.right, false);
        if (left[0] < right[0]) {
            left[0] = right[0];
            left[1] = right[1];
        }
        if (left[0] == 0) {
            left[1] = root.value;
        }
        left[0]++;
        return left;
    }

    public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        Map<Integer, Integer> count = new HashMap<>();
        afterVisited(count, root);
        List<Integer> list = new ArrayList<>();
        int max = 0;
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            if (entry.getValue() > max) {
                list.clear();
                list.add(entry.getKey());
                max = entry.getValue();
            } else if (entry.getValue() == max) {
                list.add(entry.getKey());
            }
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    public int afterVisited(Map<Integer, Integer> count, TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = afterVisited(count, root.left);
        int right = afterVisited(count, root.right);
        count.put(left + right + root.value, count.getOrDefault(left + right + root.value, 0) + 1);
        return left + right + root.value;
    }

    public List<Integer> largestValuess(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int size, max;
        while (!queue.isEmpty()) {
            size = queue.size();
            max = Integer.MIN_VALUE;
            while (size > 0) {
                root = queue.poll();
                if (root.value > max) {
                    max = root.value;
                }
                if (root.left != null) {
                    queue.add(root.left);
                }
                if (root.right != null) {
                    queue.add(root.right);
                }
                size--;
            }
            result.add(max);
        }
        return result;
    }

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preVisited(result, 0, root);
        return result;
    }

    public void preVisited(List<Integer> list, int depth, TreeNode root) {
        if (root == null) {
            return;
        }
        if (list.size() <= depth) {
            list.add(root.value);
        }
        if (list.get(depth) < root.value) {
            list.set(depth, root.value);
        }
        preVisited(list, depth + 1, root.left);
        preVisited(list, depth + 1, root.right);
    }

    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        int[][] palindrome = new int[s.length()][s.length()];
        for (int i = 0; i < palindrome.length; i++) {
            palindrome[i][i] = 1;
        }
        for (int i = 1; i < s.length(); i++) {
            for (int j = 0; i + j < s.length(); j++) {
                if (s.charAt(i + j) == s.charAt(j)) {
                    if (j + 1 <= i + j - 1) {
                        palindrome[j][i + j] = palindrome[j + 1][i + j - 1] + 2;
                    } else {
                        palindrome[j][i + j] = 2;
                    }
                } else {
                    palindrome[j][i + j] = palindrome[j][i + j - 1];
                    if (palindrome[j + 1][i + j] > palindrome[j][i + j]) {
                        palindrome[j][i + j] = palindrome[j + 1][i + j];
                    }
                }
                System.out.println(j + "\t" + (i + j) + "\t" + palindrome[j][i + j]);
            }
        }
        return palindrome[0][s.length() - 1];
    }

    public boolean checkSubarraySums(int[] nums, int k) {
        if (nums.length < 2) {
            return false;
        }
        if (k == 0) {
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == 0) {
                    count++;
                } else {
                    count = 0;
                }
                if (count >= 2) {
                    return true;
                }
            }
            return false;
        }
        Map<Integer, Integer> index = new HashMap<>();
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = (sum + nums[i]) % k;
            if (sum == 0 && i > 0) {
                return true;
            }
            if (index.containsKey(sum)) {
                if (i - index.get(sum) >= 1) {
                    return true;
                }
            } else {
                index.put(sum, i);
            }
        }
        return false;
    }

    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums.length < 2) {
            return false;
        }
        if (k == 0) {
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == 0) {
                    count++;
                } else {
                    count = 0;
                }
                if (count >= 2) {
                    return true;
                }
            }
            return false;
        }
        int[][] num = new int[2][nums.length];
        for (int i = 0; i < nums.length; i++) {
            num[0][i] = nums[i] % k;
        }
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; i + j < nums.length; j++) {
                if ((num[0][i + j - 1] + nums[i + j]) % k == 0) {
                    return true;
                }
                num[1][i + j] = (num[0][i + j - 1] + nums[i + j]) % k;
            }
            int[] temp = num[0];
            num[0] = num[1];
            num[1] = temp;
        }
        return false;
    }

    public int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length + 1][amount + 1];
        for (int i = 0; i <= coins.length; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= amount; i++) {
            dp[0][i] = 0;
        }
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= coins[i - 1]) {
                    dp[i][j] += dp[i][j - coins[i - 1]];
                }
            }
        }
        return dp[coins.length][amount];
    }

    public String findLongestWord(String s, List<String> d) {
        if (d == null) {
            return null;
        }
        String result = "";
        List<List<Integer>> index = new ArrayList<>(26);
        for (int i = 0; i < 26; i++) {
            index.add(new ArrayList<>());
        }
        int k;
        for (int i = 0; i < s.length(); i++) {
            k = s.charAt(i) - 'a';
            index.get(k).add(i);
        }
        for (int i = 0; i < d.size(); i++) {
            if (d.get(i).length() > s.length()) {
                continue;
            }
            if ((d.get(i).length() > result.length() || (d.get(i).length() == result.length() && d.get(i).compareTo(result) < 0))) {
                if (compareString(index, d.get(i))) {
                    result = d.get(i);
                }
            }
        }
        return result;
    }


    public boolean compareString(List<List<Integer>> index, String string) {
        int[] key = new int[26];
        for (int i = 0; i < 26; i++) {
            key[i] = 0;
        }
        int k, current = -1, m;
        for (int i = 0; i < string.length(); i++) {
            k = string.charAt(i) - 'a';
            if (index.get(k).size() <= key[k]) {
                return false;
            }
            m = index.get(k).get(key[k]);
            while (m <= current) {
                key[k]++;
                if (key[k] >= index.get(k).size()) {
                    return false;
                }
                m = index.get(k).get(key[k]);
            }
            current = m;
            key[k]++;
        }
        return true;
    }

    public boolean compareStrs(String one, String two) {
        int m = 0, n = 0;
        while (n < two.length()) {
            while (m < one.length() && one.charAt(m) != two.charAt(n)) {
                m++;
            }
            if (m == one.length()) {
                return false;
            }
            m++;
            n++;
        }
        return true;
    }

    public boolean compareStr(String one, String two) {
        if (one.length() < two.length()) {
            return true;
        }
        if (one.length() > two.length()) {
            return false;
        }
        return one.compareTo(two) <= 0 ? false : true;

    }

    public int findMaxLength(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int[] big = new int[nums.length + 1];
        int[] small = new int[nums.length + 1];
        int sum = 0, result = 0, t;
        for (int i = 0; i < nums.length; i++) {
            big[i + 1] = nums.length + 1;
            small[i + 1] = nums.length + 1;
            sum += nums[i];
            t = 2 * sum - i - 1;
            if (t == 0) {
                if (i + 1 > result) {
                    result = i + 1;
                }
            } else if (t > 0) {
                if (i < big[t]) {
                    big[t] = i;
                }
                if (i - big[t] > result) {
                    result = i - big[t];
                }
            } else {
                t = -t;
                if (small[t] > i) {
                    small[t] = i;
                }
                if (i - small[t] > result) {
                    result = i - small[t];
                }
            }
        }
        return result;
    }

    public void permutation(int k, int[] num, int[] result) {
        if (k == num.length) {
            result[0]++;
        }
        for (int i = k; i < num.length; i++) {
            if (num[i] % (k + 1) == 0 || (k + 1) % num[i] == 0) {
                int temp = num[k];
                num[k] = num[i];
                num[i] = temp;
                permutation(k + 1, num, result);
                temp = num[k];
                num[k] = num[i];
                num[i] = temp;
            }
        }
    }

    public int countArrangement(int N) {
        if (N <= 0) {
            return 0;
        }
        int[] num = new int[N], result = new int[1];
        result[0] = 0;
        for (int i = 0; i < N; i++) {
            num[i] = i + 1;
        }
        permutation(0, num, result);
        return result[0];
    }

    public int longestSubstring(String s, int k) {
        if (s == null || s.length() < k) {
            return 0;
        }
        int[][] count = new int[s.length() + 1][26];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < 26; j++) {
                count[i + 1][j] = count[i][j];
            }
            count[i + 1][s.charAt(i) - 'a']++;
        }
        int t = 0, max = 0, m;
        boolean flag;
        while (t < s.length()) {
            m = t;
            for (int i = s.length() - 1; i - t + 1 >= k; i--) {
                flag = true;
                for (int j = 0; j < 26; j++) {
                    if (count[i + 1][j] - count[t][j] < k && count[i + 1][j] - count[t][j] > 0) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    if (i - t + 1 > max) {
                        max = i - t + 1;
                    }
                    m = i;
                    break;
                }
            }
            t = m + 1;
        }
        return max;
    }

    public List<List<Integer>> updateMatrix(List<List<Integer>> matrix) {
        if (matrix == null) {
            return null;
        }
        List<List<Integer>> result = new ArrayList<>();
        int row = matrix.size(), colmn = 0, len;
        if (row == 0) {
            return result;
        }
        colmn = matrix.get(0).size();
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[row][colmn];
        int[][] array = new int[row][colmn];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < colmn; j++) {
                if (matrix.get(i).get(j) == 0) {
                    queue.add(i);
                    queue.add(j);
                    array[i][j] = 0;
                    visited[i][j] = true;
                }
            }
        }
        while (!queue.isEmpty()) {
            len = queue.size();
            while (len > 0) {
                int i = queue.poll(), j = queue.poll();
                if (i - 1 >= 0 && !visited[i - 1][j]) {
                    queue.add(i - 1);
                    queue.add(j);
                    array[i - 1][j] = array[i][j] + 1;
                    visited[i - 1][j] = true;
                }
                if (i + 1 < row && !visited[i + 1][j]) {
                    queue.add(i + 1);
                    queue.add(j);
                    array[i + 1][j] = array[i][j] + 1;
                    visited[i + 1][j] = true;
                }
                if (j - 1 >= 0 && !visited[i][j - 1]) {
                    queue.add(i);
                    queue.add(j - 1);
                    array[i][j - 1] = array[i][j] + 1;
                    visited[i][j - 1] = true;
                }
                if (j + 1 < colmn && !visited[i][j + 1]) {
                    queue.add(i);
                    queue.add(j + 1);
                    array[i][j + 1] = array[i][j] + 1;
                    visited[i][j + 1] = true;
                }
                len-=2;
            }
        }
        for (int i = 0; i < row; i++) {
            List<Integer> list = new ArrayList<>(colmn);
            for (int j = 0; j < colmn; j++) {
                list.add(array[i][j]);
            }
            result.add(list);
        }
        return result;
    }

    public static void main(String[] args) {
        Medium medium = new Medium();
        //  System.out.println(Arrays.toString(medium.twoSum(new int []{0,0,11,15},0)));
//        System.out.println(medium.wiggleMaxLength(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
        // System.out.println(medium.kSmallestPairs(new int[]{},new int[]{},3));
        //  System.out.println(medium.findItinerary(new String[][]{{"MUC", "LHR"}, {"JFK", "MUC"}, {"SFO", "SJC"}, {"LHR", "SFO"}}));
//        System.out.println(medium.canPartition(new int[]{1,2,3,6}));
//        System.out.println(medium.pacificAtlantic(new int[][]{{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}}));
//        System.out.println(medium.originalDigits("fviefuro"));
        //System.out.println(medium.characterReplacement("AABABBA",3));
//        System.out.println(medium.findMaximumXOR(new int[]{3, 10, 5, 25, 2, 8}));
//        Interval[] intervals = new Interval[3];
//        intervals[0] = new Interval(1, 2);
//        intervals[1] = new Interval(2, 3);
//        intervals[2] = new Interval(3, 4);
//        intervals[3]=new Interval(1,3);
//        System.out.println(Arrays.toString(medium.findRightInterval1(intervals)));
//        System.out.println(medium.eraseOverlapIntervals(intervals));
//        for(int i =1;i<=10;i++) {
//            System.out.println(i+"\t"+medium.lastRemaining(i));
//        }
//        System.out.println(medium.minMutation("AAAAACCC","AACCCCCC",new String[]{"AAAACCCC", "AAACCCCC", "AACCCCCC"}));
//        ListNode one=new ListNode(9);
//        ListNode two=new ListNode(9);
//        ListNode three=new ListNode(9);
//        one.next=two;
//        two.next=three;
//        ListNode four=new ListNode(9);
//        ListNode five=new ListNode(9);
//        ListNode six=new ListNode(9);
//        four.next=five;
//        five.next=six;
//        one=medium.addTwoNumbers(one,four);
//        while(one!=null){
//            System.out.println(one.val);
//            one=one.next;
//        }
//        System.out.println(medium.findDuplicates(new int[]{4,3,2,7,8,2,3,1}));
//        TreeNode root = new TreeNode(1, 1);
        //  TreeNode a1 = new TreeNode(2, 1);
        // TreeNode a2 = new TreeNode(3, 1);
        //TreeNode a3 = new TreeNode(4, 1);
//        TreeNode a4 = new TreeNode(5, 1);
//        TreeNode b1 = new TreeNode(6, 1);
        // TreeNode b2 = new TreeNode(7, 1);
//        TreeNode b3 = new TreeNode(6, 1);
//        TreeNode b4 = new TreeNode(8, 1);
//        TreeNode b5 = new TreeNode(10, 1);
//        root.left = null;
//        root.right = b1;
        //   a1.left = a3;
//        a2.left = a4;
        // a2.right = b1;
//        root.left = a1;
//        root.right = b2;
        // a4.left = b2;
//        b2.right = b4;
//        b4.right = b5;
//        System.out.println(medium.deleteNode(root,1).value);
//        System.out.println(medium.frequencySort("Aabb"));
//        System.out.println(medium.poorPigs(1000,15,60));
//        System.out.println(medium.circularArrayLoop(new int[]{-2,1,-1,-2,-2}));
//        System.out.println(medium.numberOfBoomerangs(new int[][]{{0,0},{1,0},{2,0}}));
//        System.out.println(medium.findMinArrowShots(new int[][]{{1,2}, {2,3}, {2,4}, {3,5}}));
//        String string=medium.serialize(root);
//        System.out.println(string);
//        root=medium.deserialize(string);
//        Queue<TreeNode>queue=new ArrayDeque<>();
//        queue.add(root);
//        while(!queue.isEmpty()){
//            root=queue.poll();
//            System.out.println(root.value);
//            if(root.left!=null){
//                queue.add(root.left);
//            }
//            if(root.right!=null){
//                queue.add(root.right);
//            }
//        }
//        System.out.println(medium.totalHammingDistance(new int[]{4,14,2}));
//        System.out.println(medium.findPoisonedDuration(new int[]{1,2},2));
//        System.out.println(medium.findTargetSumWays(new int[]{0,0,0,0,0,0,0,0,1},1));
//        System.out.println(medium.findSubsequences(new int[]{4,6,7,7}));
//        System.out.println(Arrays.toString(medium.nextGreaterElements(new int[]{100, 100,100})));
//        System.out.println(Arrays.toString(medium.findDiagonalOrder(new int[][]{{1,2,3,4},{4,5,6},{7,8,9}})));
//        System.out.println(Arrays.toString(medium.findFrequentTreeSum(root)));
//        System.out.println(medium.findBottomLeftValue(root));
//        System.out.println(medium.largestValuess(root));
//        System.out.println(medium.longestPalindromeSubseq("bbbab"));
//        System.out.println(medium.checkSubarraySums(new int[]{1, 2, 1}, 2));
//        System.out.println(medium.change(0,new int[]{}));
//        List<String> list = new ArrayList<String>() {{
//            add("ale");
//            add("appla");
//            add("appea");
//        }};
//        System.out.println(medium.findLongestWord("abpcplea", list));
//        System.out.println(medium.findMaxLength(new int[]{0, 1, 0, 0, 1}));
//        System.out.println(medium.countArrangement(6));
//        System.out.println(medium.longestSubstring("aaabb", 3));
        List<List<Integer>>list=new ArrayList<>();
        list.add(new ArrayList<Integer>(){{
            add(0);
            add(0);
            add(0);
        }});
        list.add(new ArrayList<Integer>(){{
            add(0);
            add(1);
            add(0);
        }});
        list.add(new ArrayList<Integer>(){{
            add(1);
            add(1);
            add(1);
        }});
        System.out.println(medium.updateMatrix(list));
    }

    class Node {
        private Node left;
        private Node right;
    }

    public static class TreeNode {
        int value;
        int index;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    public static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

}
