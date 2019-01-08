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

    public static void main(String[] args) {
        System.out.println(numSubarrayProductLessThanK(new int[] {10, 9, 10, 4, 3, 8, 3, 3, 6, 2, 10, 10, 9, 3}, 19));
    }

    public class TreeNode {
        int      val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { val = x; }
    }
}
