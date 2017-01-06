package leetcode;

import java.util.*;

public class Water {
	public int lengthOfLastWord(String s) {
		int i, res = 0, j = 0;
		for (i = 0; i < s.length(); i++) {
			if (s.charAt(i) != ' ') {
				j++;
			} else {
				if (j > 0) {
					res = j;
				}
				j = 0;
			}
		}
		if (j > 0) {
			res = j;
		}
		return res;
	}

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		List<Integer> first = new ArrayList<Integer>();
		List<Integer> second = new ArrayList<Integer>();
		visit(first, root, p);
		visit(second, root, q);
		int len = 0;
		while (len < first.size() && len < second.size()) {
			if (first.get(len) != second.get(len)) {
				return root;
			}
			if (first.get(len) == 0) {
				root = root.left;
			} else {
				root = root.right;
			}
			len++;
		}
		return root;
	}

	public boolean visit(List<Integer> list, TreeNode root, TreeNode p) {
		if (root != null) {
			if (root == p) {
				return true;
			}
			list.add(0);
			if (visit(list, root.left, p)) {
				return true;
			}
			list.remove(list.size() - 1);
			list.add(1);
			if (visit(list, root.right, p)) {
				return true;
			}
			list.remove(list.size() - 1);
		}
		return false;
	}

	public int[] singleNumber(int[] nums) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			if (map.get(nums[i]) == null) {
				map.put(nums[i], 1);
			} else {
				map.remove(nums[i]);
			}
		}
		int[] res = new int[2];
		int j = 0;
		for (int i : map.keySet()) {
			res[j++] = i;
		}
		return res;
	}

	public boolean isPalindrome(ListNode head) {
		ListNode root = head;
		int count = 0;
		while (head != null) {
			count++;
			head = head.next;
		}
		if (count <= 1) {
			return true;
		}
		ListNode first = root;
		ListNode second = root.next;
		ListNode third = null;
		head = root;
		if (second != null) {
			third = second.next;
		}
		int i = 1;
		while (i <= count / 2) {
			root = root.next;
			i++;
		}
		while (second != root) {
			second.next = first;
			first = second;
			second = third;
			third = third.next;
		}
		second = first;
		if (count % 2 == 1) {
			root = root.next;
		}
		while (root != null) {
			if (root.val != second.val) {
				return false;
			}
			root = root.next;
			second = second.next;
		}
		return true;
	}

	public void deleteNode(ListNode node) {
		node.val = node.next.val;
		node.next = node.next.next;
	}

	public int lengthOfLIS(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}
		int max = 0;
		int[] num = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			num[i] = 1;
			for (int j = i - 1; j >= 0 && j + 1 > num[i] - 1; j--) {
				if (nums[i] > nums[j] && num[j] + 1 > num[i]) {
					num[i] = num[j] + 1;
				}
			}
			if (num[i] > max) {
				max = num[i];
			}
		}
		return max;
	}

	public int[] productExceptSelf(int[] nums) {
		int k = 1, count = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0) {
				count++;
			} else {
				k *= nums[i];
			}
		}
		for (int i = 0; i < nums.length; i++) {
			if (count > 1) {
				nums[i] = 0;
			} else {
				if (nums[i] == 0) {
					nums[i] = k;
				} else {
					if (count == 1) {
						nums[i] = 0;
					} else {
						nums[i] = k / nums[i];
					}
				}
			}
			System.out.println(nums[i]);
		}
		return nums;
	}

	public void HeapPermute(int[] nums, int n) {
		if (n == 1) {
			for (int i = 0; i < nums.length; i++) {
				System.out.print(nums[i]);
			}
			System.out.println();
		} else {
			for (int i = 0; i < n; i++) {
				HeapPermute(nums, n - 1);
				if (n == 4) {
					System.out.println(nums[0] + " " + nums[1] + " " + nums[2] + " " + nums[3]);
				}
				if (n % 2 == 1) {
					int temp = nums[0];
					nums[0] = nums[n - 1];
					nums[n - 1] = temp;
				} else {
					int temp = nums[i];
					nums[i] = nums[n - 1];
					nums[n - 1] = temp;
				}
			}
		}
	}

	public void HeapPermuteu(int[] nums, int n) {
		if (n == nums.length - 1) {
			for (int i = 0; i < nums.length; i++) {
				System.out.print(nums[i] + " ");
			}
			System.out.println();
		} else {
			for (int i = n; i < nums.length; i++) {
				int temp = nums[n];
				nums[n] = nums[i];
				nums[i] = temp;
				HeapPermuteu(nums, n + 1);
				temp = nums[n];
				nums[n] = nums[i];
				nums[i] = temp;
			}
		}
	}

	public int[][] generateMatrix(int n) {
		int[][] res = new int[n][n];
		int count = 1, i = 0, j = 0, right = 1, down = 0, left = 0, k, t = n - 1;
		while (count <= n * n) {
			k = 0;
			if (right == 1) {
				while (k <= t) {
					res[i][j++] = count++;
					k++;
				}
				right = 0;
				down = 1;
				j--;
			} else if (down == 1) {
				while (k < t) {
					res[++i][j] = count++;
					k++;
				}
				down = 0;
				left = 1;
			} else if (left == 1) {
				while (k < t) {
					res[i][--j] = count++;
					k++;
				}
				left = 0;
			} else {
				while (k < t - 1) {
					res[--i][j] = count++;
					k++;
				}
				j++;
				right = 1;
				t = t - 2;
			}
		}
		for (i = 0; i < res.length; i++) {
			for (j = 0; j < res[i].length; j++) {
				System.out.print(res[i][j] + " ");
			}
			System.out.println();
		}
		return res;
	}

	public String getPermutation(int n, int k) {

		String res = "";
		int sum, i;
		List<Integer> num = new ArrayList<Integer>();
		for (i = 0, k--; i < n; i++) {
			num.add(i + 1);
		}
		while (k > 0) {
			sum = cal(--n);
			i = k / sum;
			k = k % sum;
			res += num.get(i);
			num.remove(i);
		}
		if (!num.isEmpty()) {
			for (i = 0; i < num.size(); i++) {
				res += num.get(i);
			}
		}
		return res;
	}

	public int numSquares(int n) {
		int[] nums = new int[n + 1];
		nums[0] = 0;
		nums[1] = 1;
		for (int i = 2; i <= n; i++) {
			int min = 0;
			for (int j = 1; j * j <= i; j++) {
				if (1 + nums[i - j * j] < min || min == 0) {
					min = 1 + nums[i - j * j];
				}
			}
			nums[i] = min;
		}
		return nums[n];
	}

	public int cal(int n) {
		if (n < 1) {
			return 1;
		}
		int res = 1;
		while (n > 0) {
			res *= n;
			n--;
		}
		return res;
	}

	public int uniquePaths(int m, int n) {
		int res = 1, sum = m + n - 2, k = 1, t;
		m = m < n ? m - 1 : n - 1;
		List<Integer> list = new ArrayList<Integer>();
		for (int i = sum; i > sum - m; i--) {
			list.add(i);
		}
		while (m > 1) {
			k = m;
			for (int i = 0; i < list.size(); i++) {
				t = gcd(list.get(i), k);
				if (list.get(i) == t) {
					list.remove(i);
					i--;
				} else {
					list.set(i, list.get(i) / t);
				}
				if (k == t) {
					break;
				}
				k = k / t;
			}
			m--;
		}
		for (int i = 0; i < list.size(); i++) {
			res *= list.get(i);
		}
		return res;
	}

	public int gcd(int m, int n) {
		int t = m % n;
		while (t != 0) {
			m = n;
			n = t;
			t = m % n;
		}
		return n;
	}

	public void moveZeroes(int[] nums) {
		int len = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != 0) {
				if (len != i) {
					nums[len] = nums[i];
					nums[i] = 0;
				}
				len++;
			}
		}
	}

	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		if ((obstacleGrid.length == 0)) {
			return 0;
		}
		int[][] result = new int[obstacleGrid.length][obstacleGrid[0].length];
		result[0][0] = obstacleGrid[0][0] == 1 ? 0 : 1;
		for (int i = 1; i < obstacleGrid[0].length; i++) {
			if (obstacleGrid[0][i] == 1) {
				result[0][i] = 0;
			} else {
				result[0][i] = result[0][i - 1];
			}
		}
		for (int i = 1; i < obstacleGrid.length; i++) {
			for (int j = 0; j < obstacleGrid[i].length; j++) {
				if (obstacleGrid[i][j] == 1) {
					result[i][j] = 0;
				} else {
					result[i][j] = result[i - 1][j];
					if (j > 0) {
						result[i][j] += result[i][j - 1];
					}
				}
			}
		}
		return result[obstacleGrid.length - 1][obstacleGrid[0].length - 1];
	}

	public ListNode rotateRight(ListNode head, int k) {
		if (head == null) {
			return head;
		}
		ListNode last = head, current = head;
		int count = 0;
		while (last.next != null) {
			count++;
			last = last.next;
		}
		count++;
		last.next = head;
		k = count - k % count - 1;
		last = head;
		for (int i = 0; i < k; i++) {
			last = last.next;
		}
		current = last.next;
		last.next = null;
		return current;
	}

	public int minPathSum(int[][] grid) {
		if (grid.length == 0) {
			return 0;
		}
		for (int i = 1; i < grid[0].length; i++) {
			grid[0][i] += grid[0][i - 1];
		}
		for (int i = 1; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (j > 0 && grid[i][j - 1] < grid[i - 1][j]) {
					grid[i][j] += grid[i][j - 1];
				} else {
					grid[i][j] += grid[i - 1][j];
				}
			}
		}
		return grid[grid.length - 1][grid[0].length - 1];
	}

	public List<List<String>> groupAnagrams(String[] strs) {
		List<List<String>> res = new ArrayList<List<String>>();
		Arrays.sort(strs);
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		int i;
		for (i = 0; i < strs.length; i++) {
			char[] array = strs[i].toCharArray();
			Arrays.sort(array);
			String temp = new String(array);
			if (map.containsKey(temp)) {
				map.get(temp).add(strs[i]);
			} else {
				List<String> str = new ArrayList<String>();
				str.add(strs[i]);
				map.put(temp, str);
			}
		}
		for (Map.Entry<String, List<String>> entry : map.entrySet()) {
			res.add(entry.getValue());
		}
		return res;
	}

	public int[] plusOne(int[] digits) {
		int k = 0, temp;
		digits[digits.length - 1]++;
		for (int i = digits.length - 1; i >= 0; i--) {
			temp = (digits[i] + k) / 10;
			digits[i] = (digits[i] + k) % 10;
			k = temp;
		}
		if (k == 0) {
			return digits;
		}
		int[] res = new int[digits.length + 1];
		res[0] = k;
		for (int i = 0; i < digits.length; i++) {
			res[i + 1] = digits[i];
		}
		return res;
	}

	public int mySqrt(int x) {
		int i, k = 0;
		for (i = 1; i * i <= x && i * i > k; i++) {
			k = i * i;
		}
		return i - 1;
	}

	public int climbStairs(int n) {
		if (n == 1) {
			return 1;
		}
		if (n == 2) {
			return 2;
		}
		int a = 1, b = 2, temp;
		for (int i = 3; i <= n; i++) {
			temp = a + b;
			a = b;
			b = temp;
		}
		return b;
	}

	public int minimumTotal(List<List<Integer>> triangle) {
		for (int i = triangle.size() - 2; i >= 0; i--) {
			List<Integer> first = triangle.get(i);
			List<Integer> second = triangle.get(i + 1);
			for (int j = 0; j < first.size(); j++) {
				int temp = second.get(j) > second.get(j + 1) ? second.get(j + 1) : second.get(j);
				first.set(j, first.get(j) + temp);
			}
		}
		int min = triangle.get(0).get(0);
		for (int i = 1; i < triangle.get(0).size(); i++) {
			if (triangle.get(0).get(i) < min) {
				min = triangle.get(0).get(i);
			}
		}
		return min;
	}

	public int numTrees(int n) {
		int[][] array = new int[n + 1][n + 1];
		array[1][1] = 1;
		for (int i = 2; i <= n; i++) {
			for (int j = 1; j < i; j++) {
				for (int k = 1; k <= j + 1; k++) {
					array[i][k] += array[i - 1][j];
				}
			}
		}
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			sum += array[n][i];
		}
		return sum;
	}

	// public List<TreeNode> generateTrees(int n) {
	// TreeNode node=new TreeNode(1);
	// List<TreeNode>res=new ArrayList<TreeNode>();
	// res.add(node);
	// if(n==1){
	// return res;
	// }
	// List<TreeNode>result=new ArrayList<TreeNode>();
	// for(int i=2;i<=n;i++){
	// for(int j=0;j<res.size();j++){
	// TreeNode temp=res.get(j);
	//
	// }
	// }
	// }
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		Arrays.sort(nums);
		res.add(new ArrayList<Integer>());
		// for (int i = 0; i < nums.length; i++) {
		// for (int j = res.size() - 1; j >= 0; j--) {
		// List<Integer> temp = new ArrayList<Integer>();
		// temp.addAll(res.get(j));
		// temp.add(nums[i]);
		// res.add(temp);
		// }
		// }
		visit(new ArrayList<Integer>(), nums, 0, res);
		for (int i = 0; i < res.size(); i++) {
			System.out.println(res.get(i));
		}
		return res;
	}

	public void visit(List<Integer> list, int[] nums, int n, List<List<Integer>> res) {
		if (n == nums.length) {
			return;
		}
		int k = 0;
		for (int i = n; i < nums.length; i++) {
			if (k != 0 && k == nums[i]) {
				continue;
			}
			k = nums[i];
			list.add(nums[i]);
			List<Integer> temp = new ArrayList<Integer>();
			temp.addAll(list);
			res.add(temp);
			visit(list, nums, i + 1, res);
			list.remove(list.size() - 1);
		}
	}

	public int rob(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}
		int[] res = new int[nums.length];
		int max = nums[0];
		for (int i = 0; i < nums.length - 1; i++) {
			if (i - 3 >= 0) {
				res[i] = nums[i] + (res[i - 3] > res[i - 2] ? res[i - 3] : res[i - 2]);
			} else if (i - 2 >= 0) {
				res[i] = nums[i] + res[i - 2];
			} else {
				res[i] = nums[i];
			}
			if (res[i] > max) {
				max = res[i];
			}
		}
		for (int i = 1; i < nums.length; i++) {
			if (i - 3 >= 1) {
				res[i] = nums[i] + (res[i - 3] > res[i - 2] ? res[i - 3] : res[i - 2]);
			} else if (i - 2 >= 1) {
				res[i] = nums[i] + res[i - 2];
			} else {
				res[i] = nums[i];
			}
			if (res[i] > max) {
				max = res[i];
			}
		}
		return max;
	}

	public int minDistance(String word1, String word2) {
		if (word1.length() == 0) {
			return word2.length();
		}
		if (word2.length() == 0) {
			return word1.length();
		}
		int[][] res = new int[word1.length()][word2.length()];
		if (word1.charAt(0) == word2.charAt(0)) {
			res[0][0] = 0;
		} else {
			res[0][0] = 1;
		}
		for (int i = 1; i < word2.length(); i++) {
			if (word1.charAt(0) == word2.charAt(i)) {
				res[0][i] = i;
			} else {
				res[0][i] = res[0][i - 1] + 1;
			}
		}
		for (int i = 1; i < word1.length(); i++) {
			if (word1.charAt(i) == word2.charAt(0)) {
				res[i][0] = i;
			} else {
				res[i][0] = res[i - 1][0] + 1;
			}
		}
		for (int i = 1; i < word1.length(); i++) {
			for (int j = 1; j < word2.length(); j++) {
				if (word1.charAt(i) == word2.charAt(j)) {
					res[i][j] = res[i - 1][j - 1];
				} else {
					res[i][j] = min(res[i - 1][j - 1], res[i][j - 1], res[i - 1][j]) + 1;
				}
			}
		}
		return res[word1.length() - 1][word2.length() - 1];
	}

	public int min(int a, int b, int c) {
		if (a > b) {
			a = b;
		}
		if (a > c) {
			a = c;
		}
		return a;
	}

	public int maxProfit(int[] prices) {
		if (prices.length == 0) {
			return 0;
		}
		int res = 0, max = prices[prices.length - 1];
		for (int i = prices.length - 2; i >= 0; i--) {
			if (prices[i] > max) {
				max = prices[i];
			}
			if (max - prices[i] > res) {
				res = max - prices[i];
			}
		}
		return res;
	}

	public int jump(int[] nums) {
		if (nums.length <= 1) {
			return 0;
		}
		int currentMax = 0, max = 0, res = 0, i = 0;
		while (i <= currentMax) {
			if (i + nums[i] > max) {
				max = i + nums[i];
			}
			if (max >= nums.length - 1) {
				return res + 1;
			}
			if (i == currentMax) {
				res++;
				currentMax = max;
			}
			i++;
		}
		return -1;
	}

	public boolean isSameTree(TreeNode p, TreeNode q) {
		return visitTree(p, q);
	}

	private boolean visitTree(TreeNode p, TreeNode q) {
		if ((p == null && q != null) || (p != null && q == null)) {
			return false;
		} else if (p == null && q == null) {
			return true;
		} else {
			if (p.val != q.val) {
				return false;
			}
			if (visitTree(p.left, q.left) && visitTree(p.right, q.right)) {
				return true;
			}
			return false;
		}
	}

	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		preorderVisit(res, root);
		return res;
	}

	private void preorderVisit(List<Integer> res, TreeNode root) {
		// TODO Auto-generated method stub
		if (root != null) {
			res.add(root.val);
			preorderVisit(res, root.left);
			preorderVisit(res, root.right);
		}
	}

	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		inorderVisit(res, root);
		return res;
	}

	private void inorderVisit(List<Integer> res, TreeNode root) {
		// TODO Auto-generated method stub
		if (root != null) {
			preorderVisit(res, root.left);
			res.add(root.val);
			preorderVisit(res, root.right);
		}
	}

	public List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		postorderVisit(res, root);
		return res;
	}

	private void postorderVisit(List<Integer> res, TreeNode root) {
		// TODO Auto-generated method stub
		if (root != null) {
			postorderVisit(res, root.left);
			postorderVisit(res, root.right);
			res.add(root.val);
		}
	}

	public int sumNumbers(TreeNode root) {
		int[] res = new int[1];
		res[0] = 0;
		preorderVisitCal(root, res, 0);
		return res[0];
	}

	private void preorderVisitCal(TreeNode root, int[] res, int n) {
		// TODO Auto-generated method stub
		if (root != null) {
			n = n * 10 + root.val;
			if (root.left == null && root.right == null) {
				res[0] += n;
			}
			preorderVisitCal(root.left, res, n);
			preorderVisitCal(root.right, res, n);
		}
	}

	public boolean hasPathSum(TreeNode root, int sum) {
		if (root == null) {
			return false;
		}
		return preorderVisitCal2(root, 0, sum);
	}

	private boolean preorderVisitCal2(TreeNode root, int n, int sum) {
		// TODO Auto-generated method stub
		n += root.val;
		if (root.left != null && preorderVisitCal2(root.left, n, sum)) {
			return true;
		}
		if (root.right != null && preorderVisitCal2(root.right, n, sum)) {
			return true;
		}
		if (root.left == null && root.right == null && n == sum) {
			return true;
		}
		return false;
	}

	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		if (root != null) {
			preorderVisitCal3(root, 0, sum, list, res);
		}
		return res;
	}

	private void preorderVisitCal3(TreeNode root, int n, int sum, List<Integer> list, List<List<Integer>> res) {
		// TODO Auto-generated method stub
		n += root.val;
		list.add(root.val);
		if (root.left != null) {
			preorderVisitCal3(root.left, n, sum, list, res);
		}
		if (root.right != null) {
			preorderVisitCal3(root.right, n, sum, list, res);
		}
		if (root.left == null && root.right == null && n == sum) {
			List<Integer> temp = new ArrayList<Integer>();
			temp.addAll(list);
			res.add(temp);
		}
		list.remove(list.size() - 1);
	}

	public int minDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return minVisit(root);
	}

	private int minVisit(TreeNode root) {
		// TODO Auto-generated method stub
		if (root.left == null && root.right == null) {
			return 1;
		} else if (root.left == null) {
			return 1 + minVisit(root.right);
		} else if (root.right == null) {
			return 1 + minVisit(root.left);
		} else {
			return 1 + Math.min(minVisit(root.left), minVisit(root.right));
		}
	}

	public int ladderLength2(String beginWord, String endWord, Set<String> wordList) {
		if (beginWord.equals(endWord)) {
			return 0;
		}
		LinkedList<String> queue = new LinkedList<String>();
		int count = 1;
		queue.offer(beginWord);
		queue.offer(".");
		while (queue.size() > 1) {
			String str = queue.poll();
			if (str.equals(".")) {
				count++;
				queue.offer(".");
			} else {
				if (calLength(str, endWord) == 1) {
					return count + 1;
				}
				Iterator<String> it = wordList.iterator();
				while (it.hasNext()) {
					String temp = it.next();
					if (calLength(temp, str) == 1) {
						queue.offer(temp);
						it.remove();
					}
				}
			}
		}
		return 0;
	}

	public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
		if (beginWord.equals(endWord)) {
			return 2;
		}
		List<String> strs = new ArrayList<String>();
		strs.add(beginWord);
		strs.add(endWord);
		strs.addAll(wordList);
		int[][] map = new int[strs.size()][strs.size()];
		for (int i = 0; i < strs.size(); i++) {
			for (int j = i; j < strs.size(); j++) {
				map[i][j] = calLength(strs.get(i), strs.get(j));
				map[j][i] = map[i][j];
			}
		}
		int min, current;
		int[] num = new int[strs.size()];
		for (int i = 0; i < num.length; i++) {
			num[i] = i;
		}
		map[0][0] = 1;
		for (int i = 1; i < num.length; i++) {
			min = 0;
			current = i;
			for (int j = i; j < num.length; j++) {
				if (map[0][num[i - 1]] > 0 && map[num[i - 1]][num[j]] > 0) {
					if (map[0][num[j]] == 0 || map[0][num[i - 1]] + map[num[i - 1]][num[j]] < map[0][num[j]]) {
						map[0][num[j]] = map[0][num[i - 1]] + map[num[i - 1]][num[j]];
					}
				}
				if (map[0][num[j]] > 0 && (min == 0 || map[0][num[j]] < min)) {
					min = map[0][num[j]];
					current = j;
				}
			}
			if (num[current] == 1) {
				break;
			}
			if (current != i) {
				int temp = num[i];
				num[i] = num[current];
				num[current] = temp;
			}
		}
		if (map[0][1] == 0) {
			return 0;
		}
		return map[0][1] + 1;
	}

	public int calLength(String a, String b) {
		int count = 0;
		for (int i = 0; i < a.length() && count <= 1; i++) {
			if (a.charAt(i) != b.charAt(i)) {
				count++;
			}
		}
		if (count == 1) {
			return 1;
		}
		return 0;
	}

	public String getHint(String secret, String guess) {
		int a = 0, b = 0, len = 0, i, j;
		char[] sec = new char[secret.length()];
		char[] gue = new char[secret.length()];
		for (i = 0; i < sec.length; i++) {
			if (secret.charAt(i) == guess.charAt(i)) {
				a++;
			} else {
				sec[len] = secret.charAt(i);
				gue[len++] = guess.charAt(i);
			}
		}
		Arrays.sort(sec, 0, len);
		Arrays.sort(gue, 0, len);
		i = 0;
		j = 0;
		while (i < len && j < len) {
			if (sec[i] < gue[j]) {
				i++;
			} else if (sec[i] == gue[j]) {
				b++;
				i++;
				j++;
			} else {
				j++;
			}
		}
		return a + "A" + b + "B";
	}

	public boolean wordPattern(String pattern, String str) {
		String[] strs = str.split(" ");
		if (pattern.length() != strs.length) {
			return false;
		}
		int[] count = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		for (int i = 0; i < pattern.length(); i++) {
			if (count[pattern.charAt(i) - 'a'] == 0) {
				count[pattern.charAt(i) - 'a'] = i + 1;
			} else {
				if (!strs[count[pattern.charAt(i) - 'a'] - 1].equals(strs[i])) {
					return false;
				}
			}
		}
		for (int i = 0; i < pattern.length(); i++) {
			for (int j = i + 1; j < pattern.length(); j++) {
				if (strs[i].equals(strs[j]) && pattern.charAt(i) != pattern.charAt(j)) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean canWinNim(int n) {
		if (n % 4 == 0) {
			return false;
		}
		return true;
	}

	public void gameOfLife(int[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = getState(board, i, j);
			}
		}
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == -1) {
					board[i][j] = 1;
				}
				if (board[i][j] == 2) {
					board[i][j] = 0;
				}
			}
		}
	}

	public int getState(int[][] board, int x, int y) {
		int a = 0;
		if (x - 1 >= 0) {
			if (y - 1 >= 0) {
				if (board[x - 1][y - 1] >= 1) {
					a++;
				}
			}
			if (board[x - 1][y] >= 1) {
				a++;
			}
			if (y + 1 < board[0].length) {
				if (board[x - 1][y + 1] >= 1) {
					a++;
				}
			}
		}
		if (x + 1 < board.length) {
			if (y - 1 >= 0) {
				if (board[x + 1][y - 1] >= 1) {
					a++;
				}
			}
			if (board[x + 1][y] >= 1) {
				a++;
			}
			if (y + 1 < board[0].length) {
				if (board[x + 1][y + 1] >= 1) {
					a++;
				}
			}
		}
		if (y - 1 >= 0) {
			if (board[x][y - 1] >= 1) {
				a++;
			}
		}
		if (y + 1 < board[0].length) {
			if (board[x][y + 1] >= 1) {
				a++;
			}
		}
		if (board[x][y] == 0) {
			if (a == 3) {
				return -1;
			}
			return 0;
		} else {
			if (a == 2 || a == 3) {
				return 1;
			}
			return 2;
		}
	}

	public int findDuplicate(int[] nums) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			if (map.get(nums[i]) == null) {
				map.put(nums[i], 1);
			} else {
				return nums[i];
			}
		}
		return 0;
	}

	public int hIndex(int[] citations) {
		Arrays.sort(citations);
		for (int i = 0; i < citations.length; i++) {
			if (citations.length - i <= citations[i]) {
				return citations.length - i;
			}
		}
		return 0;
	}

	public int hIndex2(int[] citations) {
		Arrays.sort(citations);
		for (int i = 0; i < citations.length; i++) {
			if (citations.length - i <= citations[i]) {
				return citations.length - i;
			}
		}
		return 0;
	}

	public int nthUglyNumber(int n) {
		int[] res = new int[n];
		res[0] = 1;
		int num_2, num_3, num_5, index_2 = 0, index_3 = 0, index_5 = 0, len = 0, min;
		while (len < n - 1) {
			num_2 = res[index_2] * 2;
			num_3 = res[index_3] * 3;
			num_5 = res[index_5] * 5;
			if (num_2 <= num_3 && num_2 <= num_5) {
				min = num_2;
				index_2++;
			} else if (num_3 <= num_2 && num_3 <= num_5) {
				min = num_3;
				index_3++;
			} else {
				min = num_5;
				index_5++;
			}
			if (min != res[len]) {
				res[++len] = min;
			}
		}
		return res[len];
	}

	public int missingNumber(int[] nums) {
		int len = nums.length, sum1 = len, sum2 = 0;
		for (int i = 0; i < len; i++) {
			sum1 += i;
			sum2 += nums[i];
		}
		return sum1 - sum2;
	}

	public boolean isUgly(int num) {
		if (num == 0) {
			return false;
		}
		while (num % 2 == 0) {
			num = num / 2;
		}
		while (num % 3 == 0) {
			num = num / 3;
		}
		while (num % 5 == 0) {
			num = num / 5;
		}
		if (num == 1) {
			return true;
		}
		return false;
	}

	public List<String> binaryTreePaths(TreeNode root) {
		List<String> res = new ArrayList<String>();
		if (root == null) {
			return res;
		}
		String str = new String();
		preVisit(res, str, root);
		for (int i = 0; i < res.size(); i++) {
			System.out.println(res.get(i));
		}
		return res;
	}

	public void preVisit(List<String> res, String str, TreeNode root) {
		if (root != null) {
			if (str.equals("")) {
				str += root.val;
			} else {
				str += "->" + root.val;
			}
			if (root.left == null && root.right == null) {
				res.add(str);
			} else {
				preVisit(res, str, root.left);
				preVisit(res, str, root.right);
			}
		}
	}

	public boolean isAnagram(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}
		if (s.equals(t)) {
			int i;
			for (i = s.length() - 1; i >= 1; i--) {
				if (s.charAt(i) != s.charAt(i - 1)) {
					return false;
				}
			}
			if (i <= 0) {
				return true;
			}
			return false;
		}
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		Character c;
		for (int i = 0; i < s.length(); i++) {
			c = s.charAt(i);
			if (map.get(c) == null) {
				map.put(c, 1);
			} else {
				map.put(c, map.get(c) + 1);
			}
		}
		for (int i = 0; i < t.length(); i++) {
			c = t.charAt(i);
			if (map.get(c) == null) {
				return false;
			} else {
				int k = map.get(c);
				if (k == 0) {
					return false;
				}
				map.put(c, k - 1);
			}
		}
		for (Map.Entry<Character, Integer> entry : map.entrySet()) {
			if (entry.getValue() != 0) {
				return false;
			}
		}
		return true;
	}

	public int addDigits(int num) {
		int res = 0;
		while (num >= 10) {
			while (num > 0) {
				res += num % 10;
				num /= 10;
			}
			num = res;
			res = 0;
		}
		return num;
	}

	public boolean searchMatrix(int[][] matrix, int target) {
		int left, right, mid, t = 0;
		while (t < matrix.length) {
			left = 0;
			right = matrix[t].length - 1;
			if (matrix[t][left] <= target && matrix[t][right] >= target) {
				while (left <= right) {
					mid = (left + right) / 2;
					if (matrix[t][mid] == target) {
						return true;
					} else if (matrix[t][mid] > target) {
						right = mid - 1;
					} else {
						left = mid + 1;
					}
				}
			}
			if (matrix[t][0] > target) {
				return false;
			}
			t++;
		}
		return false;
	}

	public List<Integer> diffWaysToCompute(String input) {
		List<Integer> num = new ArrayList<Integer>();
		List<Character> operator = new ArrayList<Character>();
		int t = 0;
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) >= '0' && input.charAt(i) <= '9') {
				t = t * 10 + input.charAt(i) - '0';
			} else {
				num.add(t);
				t = 0;
				operator.add(input.charAt(i));
			}
		}
		num.add(t);
		List<Integer> result = new ArrayList<Integer>();
		diffWays(num, operator, result, 0);
		return result;
	}

	public void diffWays(List<Integer> num, List<Character> operator, List<Integer> res, int k) {
		if (operator.size() == 0) {
			res.add(num.get(0));
		} else {
			for (int i = k; i < operator.size(); i++) {
				List<Integer> nums = new ArrayList<Integer>();
				List<Character> operators = new ArrayList<Character>();
				nums.addAll(num);
				operators.addAll(operator);
				if (operator.get(i) == '+') {
					nums.set(i, nums.get(i) + nums.get(i + 1));
				} else if (operator.get(i) == '-') {
					nums.set(i, nums.get(i) - nums.get(i + 1));
				} else {
					nums.set(i, nums.get(i) * nums.get(i + 1));
				}
				operators.remove(i);
				nums.remove(i + 1);
				if (i - 1 < 0) {
					diffWays(nums, operators, res, i);
				} else {
					diffWays(nums, operators, res, i - 1);
				}
			}
		}
	}

	public int kthSmallest(TreeNode root, int k) {
		int[] res = new int[2];
		res[0] = 0;
		visit(root, k, res);
		return res[1];
	}

	public void visit(TreeNode root, int k, int[] res) {
		if (root != null) {
			visit(root.left, k, res);
			res[0]++;
			if (res[0] == k) {
				res[1] = root.val;
				return;
			}
			if (res[0] > k) {
				return;
			}
			visit(root.right, k, res);
		}
	}

	public TreeNode invertTree(TreeNode root) {
		TreeNode result = root;
		visit(root);
		return result;
	}

	public void visit(TreeNode root) {
		if (root != null) {
			visit(root.left);
			visit(root.right);
			TreeNode temp = root.left;
			root.left = root.right;
			root.right = temp;
		}
	}

	public List<List<Integer>> generate(int numRows) {
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		if (numRows == 0) {
			return list;
		}
		List<Integer> temp = new ArrayList<Integer>(), temps;
		temp.add(1);
		list.add(temp);
		for (int i = 1; i < numRows; i++) {
			temp = list.get(i - 1);
			temps = new ArrayList<>();
			temps.add(1);
			for (int j = 0; j < temp.size() - 1; j++) {
				temps.add(temp.get(j) + temp.get(j + 1));
			}
			temps.add(1);
			list.add(temps);
		}
		return list;
	}

	public List<Integer> getRow(int rowIndex) {
		List<Integer> result = new ArrayList<Integer>();
		result.add(1);
		for (int i = 1; i <= rowIndex; i++) {
			for (int j = i - 1; j > 0; j--) {
				result.set(j, result.get(j) + result.get(j - 1));
			}
			result.add(1);
		}
		return result;
	}

	public int calculate(String s) {
		List<Integer> num = new ArrayList<Integer>();
		List<Character> operator = new ArrayList<Character>();
		int k = -1, t, n;
		Character c;
		for (int i = 0; i < s.length(); i++) {
			c = s.charAt(i);
			if (c >= '0' && c <= '9') {
				if (k == -1) {
					k = 0;
				}
				k = k * 10 + c - '0';
			} else {
				if (k != -1) {
					num.add(k);
					k = -1;
				}
				if (c != ' ') {
					if (c != ')') {
						while (operator.size() > 0 && compare(operator.get(operator.size() - 1), c) == 0) {
							t = num.get(num.size() - 1);
							num.remove(num.size() - 1);
							n = num.get(num.size() - 1);
							num.remove(num.size() - 1);
							if (operator.get(operator.size() - 1) == '+') {
								num.add(t + n);
							} else if (operator.get(operator.size() - 1) == '-') {
								num.add(n - t);
							} else if (operator.get(operator.size() - 1) == '*') {
								num.add(n * t);
							} else {
								num.add(n / t);
							}
							operator.remove(operator.size() - 1);
						}
						operator.add(c);
					} else {
						while (operator.size() > 0 && operator.get(operator.size() - 1) != '(') {
							t = num.get(num.size() - 1);
							num.remove(num.size() - 1);
							n = num.get(num.size() - 1);
							num.remove(num.size() - 1);
							if (operator.get(operator.size() - 1) == '+') {
								num.add(t + n);
							} else if (operator.get(operator.size() - 1) == '-') {
								num.add(n - t);
							} else if (operator.get(operator.size() - 1) == '*') {
								num.add(n * t);
							} else {
								num.add(n / t);
							}
							operator.remove(operator.size() - 1);
						}
						operator.remove(operator.size() - 1);
					}
				}
			}
		}
		if (k != -1) {
			num.add(k);
		}
		while (operator.size() > 0) {
			t = num.get(num.size() - 1);
			num.remove(num.size() - 1);
			n = num.get(num.size() - 1);
			num.remove(num.size() - 1);
			if (operator.get(operator.size() - 1) == '+') {
				num.add(t + n);
			} else if (operator.get(operator.size() - 1) == '-') {
				num.add(n - t);
			} else if (operator.get(operator.size() - 1) == '*') {
				num.add(n * t);
			} else {
				num.add(n / t);
			}
			operator.remove(operator.size() - 1);
		}
		return num.get(0);
	}

	public int compare(Character a, Character b) {
		if (((b == '*' || b == '/') && (a == '+' || a == '-')) || a == '(' || b == '(') {
			return 1;
		}
		return 0;
	}

	public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
		int area = (C - A) * (D - B) + (G - E) * (H - F);
		A = A >= E ? A : E;
		B = B >= F ? B : F;
		C = C >= G ? G : C;
		D = D >= H ? H : D;
		if (A >= C || B >= D) {
			return area;
		}
		return area - (C - A) * (D - B);
	}

	public boolean containsNearbyDuplicate(int[] nums, int k,int t) {
		if(t<0){
			return false;
		}
		TreeSet<Long> tree = new TreeSet<Long>();
		for (int i = 0; i < nums.length; i++) {
			if (i>k) {
				tree.remove((long)nums[i-k-1]);
			} 
			Long left=(long)nums[i]-t; 
			Long right=(long)nums[i]+t; 
			if(tree.subSet(left, right+1).size()!=0){
				return true;
			}
			tree.add((long)nums[i]);
		}
		return false;
	}

	public static void main(String[] args) {
		Water water = new Water();
		System.out.println(water.containsNearbyDuplicate(new int[]{-1,-1}, 1, -1));
	}
}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}