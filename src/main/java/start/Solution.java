package start;

import java.util.*;

/**
 * @author fuyang
 * @version $Id: Solution.java, v 0.1 2019年01月07日 2:00 PM fuyang Exp $
 */
public class Solution {

    // 数组最长连续之和
    public static double findMaxAverage(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k > nums.length || k <= 0) {
            return 0;
        }
        int maxSum = 0, currentSum;
        for (int i = 0; i < k; i++) {
            maxSum += nums[i];
        }
        currentSum = maxSum;
        for (int i = k; i < nums.length; i++) {
            currentSum = currentSum + nums[i] - nums[i - k];
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
        }
        return 1.0 * maxSum / k;
    }

    public static int maximumSwap(int num) {
        if (num == 0) {
            return 0;
        }
        List<Integer> dights = new ArrayList<>();
        int k;
        while (num != 0) {
            k = num % 10;
            dights.add(k);
            num = num / 10;
        }
        int index = dights.size() - 1;
        for (int i = dights.size() - 2; i >= 0; i--) {
            if (dights.get(i + 1) < dights.get(i)) {
                index = i;
                break;
            }
        }
        int max = dights.get(index);
        k = index;
        for (int i = index - 1; i >= 0; i--) {
            if (dights.get(i) >= max) {
                k = i;
                max = dights.get(i);
            }
        }
        for (int i = index + 1; i < dights.size(); i++) {
            if (dights.get(i) >= dights.get(k)) {
                index = i - 1;
                break;
            } else {
                index = i;
            }
        }
        if (index > k) {
            int temp = dights.get(index);
            dights.set(index, dights.get(k));
            dights.set(k, temp);
        }
        num = 0;
        for (int i = dights.size() - 1; i >= 0; i--) {
            num = 10 * num + dights.get(i);
        }
        return num;
    }

    // 二分查找
    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> result = new ArrayList<>();
        if (arr == null || arr.length == 0 || k <= 0) {
            return result;
        }
        int begin = 0, end = arr.length - 1, mid = 0, index = 0;
        while (begin <= end) {
            mid = (begin + end) / 2;
            if (arr[mid] == x) {
                break;
            } else if (arr[mid] < x) {
                begin = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        if (arr[mid] == x) {
            begin = end = mid;
        } else if (mid > 0 && x - arr[mid - 1] <= arr[mid] - x) {
            begin = end = mid - 1;
        } else {
            begin = end = mid;
        }
        while (!((begin == 0 && end == arr.length - 1) || end - begin + 1 == k)) {
            if (begin == 0 && end == arr.length - 1) {
                break;
            } else if (begin == 0) {
                end++;
            } else if (end == arr.length - 1) {
                begin--;
            } else {
                if (x - arr[begin - 1] <= arr[end + 1] - x) {
                    begin--;
                } else {
                    end++;
                }
            }
        }
        for (int i = begin; i <= end; i++) {
            result.add(arr[i]);
        }
        return result;
    }

    // 最长单调子序列
    public static int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int maxCount = 0, maxNum = 0, max, count;
        int[][] arr = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            max = 1;
            count = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    if (arr[j][0] + 1 > max) {
                        max = arr[j][0] + 1;
                        count = arr[j][1];
                    } else if (arr[j][0] + 1 == max) {
                        count += arr[j][1];
                    }
                }
            }
            arr[i][0] = max;
            arr[i][1] = count;
            if (max > maxCount) {
                maxCount = max;
                maxNum = count;
            } else if (max == maxCount) {
                maxNum += count;
            }
        }
        return maxNum;
    }

    // 最短路径
    public static int cutOffTree(List<List<Integer>> forest) {
        if (forest == null || forest.size() == 0) {
            return 0;
        }
        List<int[]> indexHeight = new ArrayList<>();
        for (int i = 0; i < forest.size(); i++) {
            for (int j = 0; j < forest.get(i).size(); j++) {
                if (forest.get(i).get(j) > 1) {
                    int[] temp = new int[3];
                    temp[0] = i;
                    temp[1] = j;
                    temp[2] = forest.get(i).get(j);
                    indexHeight.add(temp);
                }
            }
        }
        if (indexHeight.size() == 0) {
            return 0;
        }
        Collections.sort(indexHeight, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        int steps = distance(forest, 0, 0, indexHeight.get(0)[0], indexHeight.get(0)[1]);
        if (steps == -1) {
            return -1;
        }
        int step;
        for (int i = 0; i < indexHeight.size() - 1; i++) {
            step = distance(forest, indexHeight.get(i)[0], indexHeight.get(i)[1], indexHeight.get(i + 1)[0], indexHeight.get(i + 1)[1]);
            if (step == -1) {
                return -1;
            }
            steps += step;
        }
        return steps;
    }

    private static int distance(List<List<Integer>> forest, int startPointX, int startPointY, int stopPointX, int stopPointY) {
        if (startPointX == stopPointX && startPointY == stopPointY) {
            return 0;
        }
        int m = forest.size(), n = forest.get(0).size();
        boolean[][] flag = new boolean[m][n];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {startPointX, startPointY});
        int size = queue.size(), x, y;
        int[] pos;
        int step = 0;
        flag[startPointX][startPointY] = true;
        while (size > 0) {
            step++;
            while (size > 0) {
                pos = queue.poll();
                x = pos[0];
                y = pos[1];
                if (x - 1 >= 0 && !flag[x - 1][y] && forest.get(x - 1).get(y) >= 1) {
                    if (x - 1 == stopPointX && y == stopPointY) {
                        return step;
                    }
                    queue.add(new int[] {x - 1, y});
                    flag[x - 1][y] = true;
                }
                if (x + 1 < m && !flag[x + 1][y] && forest.get(x + 1).get(y) >= 1) {
                    if (x + 1 == stopPointX && y == stopPointY) {
                        return step;
                    }
                    queue.add(new int[] {x + 1, y});
                    flag[x + 1][y] = true;
                }
                if (y - 1 >= 0 && !flag[x][y - 1] && forest.get(x).get(y - 1) >= 1) {
                    if (x == stopPointX && y - 1 == stopPointY) {
                        return step;
                    }
                    queue.add(new int[] {x, y - 1});
                    flag[x][y - 1] = true;
                }
                if (y + 1 < n && !flag[x][y + 1] && forest.get(x).get(y + 1) >= 1) {
                    if (x == stopPointX && y + 1 == stopPointY) {
                        return step;
                    }
                    queue.add(new int[] {x, y + 1});
                    flag[x][y + 1] = true;
                }
                size -= 1;
            }
            size = queue.size();
        }
        return -1;
    }

    // 括号匹配
    public static boolean checkValidString(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        Stack<Integer> stack = new Stack<>();
        char lastChar;
        int num;
        for (char c : s.toCharArray()) {
            if (stack.isEmpty() || stack.peek() > 0) {
                lastChar = '*';
            } else {
                lastChar = '(';
            }
            if (c == '(') {
                stack.push(-1);
            } else if (c == '*') {
                if (lastChar == '*' && !stack.isEmpty()) {
                    stack.push(stack.pop() + 1);
                } else {
                    stack.push(1);
                }
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                num = stack.pop();
                if (num > 0) {
                    if (stack.isEmpty()) {
                        num = num - 1;
                        if (num > 0) {
                            stack.push(num);
                        }
                    } else {
                        stack.pop();
                        if (!stack.isEmpty() && stack.peek() > 0) {
                            stack.push(stack.pop() + num);
                        } else {
                            stack.push(num);
                        }
                    }
                }
            }
        }
        int count = 0;
        while (!stack.isEmpty()) {
            num = stack.pop();
            if (num == -1) {
                if (count == 0) {
                    return false;
                }
                count--;
            } else {
                count += num;
            }
        }
        return true;
    }

    // 树路径最长
    public static int longestUnivaluePath(TreeNode root) {
        int[] result = new int[1];
        longestPath(root, result);
        return result[0];
    }

    public static int longestPath(TreeNode root, int[] max) {
        if (root == null) {
            return 0;
        }
        int left = 0, right = 0, num = 0, length = 0;
        if (root.left != null) {
            left = longestPath(root.left, max);
            if (root.val == root.left.val) {
                num = num + left + 1;
                length = num;
            }
        }
        if (root.right != null) {
            right = longestPath(root.right, max);
            if (root.val == root.right.val) {
                num = num + right + 1;
                if (right + 1 > length) {
                    length = right + 1;
                }
            }
        }
        if (num > max[0]) {
            max[0] = num;
        }
        return length;
    }

    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int begin = 0, end = 0, num = nums[0], total = 0;
        while (end < nums.length) {
            if (num < k) {
                while (end + 1 < nums.length && k / num >= nums[end + 1] && nums[end + 1] * num < k) {
                    end++;
                    num *= nums[end];
                }
                total = total + end - begin + 1;
            }
            num = num / nums[begin];
            begin++;
            if (begin > end) {
                end++;
                if (end < nums.length) {
                    num = num * nums[end];
                }
            }
            System.out.println("         " + begin + " " + end + " " + num);
        }
        return total;
    }

    // 优先队列
    public static int smallestDistancePair(int[] nums, int k) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        Arrays.sort(nums);
        Queue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });
        int[] distance = new int[nums.length - 1];
        for (int i = 1; i < nums.length; i++) {
            int[] distances = new int[2];
            distances[0] = nums[i] - nums[i - 1];
            distance[i - 1] = distances[0];
            distances[1] = i - 1;
            queue.add(distances);
        }
        int count = 0;
        int[] distances;
        while (!queue.isEmpty()) {
            distances = queue.poll();
            if (++count == k) {
                return distances[0];
            }
            if (distances[1] + 1 < distance.length) {
                queue.add(new int[] {distances[0] + distance[distances[1] + 1], distances[1] + 1});
            }
        }
        return -1;
    }

    public static int[] asteroidCollision(int[] asteroids) {
        if (asteroids == null || asteroids.length == 0) {
            return new int[0];
        }
        List<Integer> list = new ArrayList<>();
        int len = 0;
        boolean flag;
        for (int i = 0; i < asteroids.length; i++) {
            if (list.size() == 0 || asteroids[i] > 0) {
                list.add(asteroids[i]);
                len++;
            } else {
                flag = true;
                while (len > 0 && list.get(len - 1) > 0) {
                    if (-asteroids[i] == list.get(len - 1)) {
                        list.remove(len - 1);
                        len--;
                        flag = false;
                        break;
                    } else if (-asteroids[i] > list.get(len - 1)) {
                        list.remove(len - 1);
                        len--;
                    } else {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    list.add(asteroids[i]);
                    len++;
                }
            }
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    public static int reachNumber(int target) {
        if (target == 0) {
            return 0;
        }
        if (target < 0) {
            target = -target;
        }
        int step = 0, sum = 0;
        while (sum < target) {
            step++;
            sum += step;
        }
        while ((sum - target) % 2 != 0) {
            step++;
            sum += step;
        }
        return step;
    }

    // 单源最短路径
    public int networkDelayTime(int[][] times, int N, int K) {
        int[][] distances = new int[N][N];
        boolean[][] flag = new boolean[N][N];
        int max = 0, minNeighbor = K, distance = Integer.MAX_VALUE, index;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                distances[i][j] = -1;
            }
        }
        for (int i = 0; i < times.length; i++) {
            distances[times[i][0] - 1][times[i][1] - 1] = times[i][2];
            if (times[i][0] == K && times[i][2] < distance) {
                distance = times[i][2];
                minNeighbor = times[i][1];
            }
        }
        flag[K - 1][K - 1] = true;
        while (minNeighbor != K) {
            index = K;
            distance = Integer.MAX_VALUE;
            flag[K - 1][minNeighbor - 1] = true;
            for (int i = 0; i < N; i++) {
                if (!flag[K - 1][i] && distances[minNeighbor - 1][i] >= 0 && (distances[K - 1][i] == -1
                        || distances[K - 1][i] > distances[K - 1][minNeighbor - 1] + distances[minNeighbor - 1][i])) {
                    distances[K - 1][i] = distances[K - 1][minNeighbor - 1] + distances[minNeighbor - 1][i];
                }
                if (!flag[K - 1][i] && distances[K - 1][i] >= 0 && distances[K - 1][i] < distance) {
                    distance = distances[K - 1][i];
                    index = i + 1;
                }
            }
            minNeighbor = index;
        }
        for (int i = 0; i < N; i++) {
            if (!flag[K - 1][i]) {
                return -1;
            }
            if (distances[K - 1][i] > max) {
                max = distances[K - 1][i];
            }
        }
        return max;
    }

    // 区间
    public static int intersectionSizeTwo(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) {
                    return o1[0] - o2[0];
                }
                return o1[1] - o2[1];
            }
        });
        int count = 2, max = intervals[0][intervals[0].length - 1], min = max - 1;
        int[] interval;
        for (int i = 1; i < intervals.length; i++) {
            interval = intervals[i];
            if (interval[0] <= min) {
                continue;
            } else if (interval[0] > max) {
                max = interval[interval.length - 1];
                min = max - 1;
                count += 2;
            } else {
                min = max;
                max = interval[interval.length - 1];
                if (min == max) {
                    min = max - 1;
                }
                count++;
            }
        }
        return count;
    }

    //  two point
    public static int maxChunksToSorted(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return 1;
        }
        int[] maxs = new int[arr.length];
        int[] mins = new int[arr.length];
        int count = 1, max = arr[0], min = arr[arr.length - 1];
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
            maxs[i] = max;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] < min) {
                min = arr[i];
            }
            mins[i] = min;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            if (maxs[i] <= mins[i + 1]) {
                count++;
            }
        }
        return count;
    }

    // 简单DP
    public static int orderOfLargestPlusSign(int N, int[][] mines) {
        if (N <= 0) {
            return 0;
        }
        int[][] count = new int[N][N];
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                count[i][j] = Math.min(i + 1, N - i);
                count[i][j] = Math.min(count[i][j], j + 1);
                count[i][j] = Math.min(count[i][j], N - j);
            }
        }
        if (mines != null && mines.length > 0) {
            for (int i = 0; i < mines.length; i++) {
                for (int j = 0; j < N; j++) {
                    if (count[mines[i][0]][j] > Math.abs(mines[i][1] - j)) {
                        count[mines[i][0]][j] = Math.abs(mines[i][1] - j);
                    }
                }
                for (int j = 0; j < N; j++) {
                    if (count[j][mines[i][1]] > Math.abs(mines[i][0] - j)) {
                        count[j][mines[i][1]] = Math.abs(mines[i][0] - j);
                    }
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (count[i][j] > max) {
                    max = count[i][j];
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] arrays = new int[][]
                {{16, 18}, {11, 18}, {15, 23}, {1, 16}, {10, 16}, {6, 19}, {18, 20}, {7, 19}, {10, 11}, {11, 23}, {6, 7}, {23, 25},
                        {1, 3},
                        {7, 12}, {1, 13}, {23, 25}, {10, 22}, {23, 25}, {0, 19}, {0, 13}, {7, 12}, {14, 19}, {8, 17}, {7, 23}, {4, 24}};
        System.out.println(intersectionSizeTwo(arrays));

    }

    public class TreeNode {
        int      val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { val = x; }
    }
}