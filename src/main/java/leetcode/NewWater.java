package leetcode;

import java.util.*;

public class NewWater {
	public int longestIncreasingPath(int[][] matrix) {
		if (matrix.length == 0) {
			return 0;
		}
		int[][] max = new int[matrix.length][matrix[0].length];
		int result = 0, k;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				k = maxLength(i, j, matrix, max);
				if (k > result) {
					result = k;
				}
			}
		}
		return result;
	}

	public int titleToNumber(String s) {
		int num = 0;
		for (int i = 0; i < s.length(); i++) {
			num = num * 26 + s.charAt(i) - 'A' + 1;
		}
		return num;
	}

	public int majorityElement(int[] nums) {
		if (nums.length == 1) {
			return nums[0];
		}
		int left = 0, current = 1;
		while (current < nums.length) {
			if (nums[left] != nums[current]) {
				left--;
				if (left < 0) {
					nums[0] = nums[++current];
					left = 0;
				}
			} else {
				nums[++left] = nums[current];
			}
			current++;
		}
		return nums[0];
	}

	public int hammingWeight(int n) {
		int count = 0;
		// for(int i=0;i<32;i++){
		// if(((k<<i)&n)!=0){
		// count++;
		// }
		// }
		return count;
	}

	public ListNode reverseList(ListNode head) {
		if (head == null) {
			return head;
		}
		ListNode current = head.next, last;
		head.next = null;
		while (current != null) {
			last = current.next;
			current.next = head;
			head = current;
			current = last;
		}
		return head;
	}

	public int maxLength(int i, int j, int[][] matrix, int[][] max) {
		int a = 1, b = 1, c = 1, d = 1;
		if (i - 1 >= 0 && matrix[i - 1][j] < matrix[i][j]) {
			if (max[i - 1][j] == 0) {
				max[i - 1][j] = maxLength(i - 1, j, matrix, max);
			}
			a += max[i - 1][j];
		}
		if (i + 1 <= matrix.length - 1 && matrix[i + 1][j] < matrix[i][j]) {
			if (max[i + 1][j] == 0) {
				max[i + 1][j] = maxLength(i + 1, j, matrix, max);
			}
			b += max[i + 1][j];
		}
		if (j - 1 >= 0 && matrix[i][j - 1] < matrix[i][j]) {
			if (max[i][j - 1] == 0) {
				max[i][j - 1] = maxLength(i, j - 1, matrix, max);
			}
			c += max[i][j - 1];
		}
		if (j + 1 <= matrix[0].length - 1 && matrix[i][j + 1] < matrix[i][j]) {
			if (max[i][j + 1] == 0) {
				max[i][j + 1] = maxLength(i, j + 1, matrix, max);
			}
			d += max[i][j + 1];
		}
		a = a > b ? a : b;
		c = c > d ? c : d;
		a = a > c ? a : c;
		max[i][j] = a;
		return a;
	}

	public ListNode oddEvenList(ListNode head) {
		if (head == null) {
			return head;
		}
		ListNode second = head.next;
		if (second == null || second.next == null) {
			return head;
		}
		ListNode current1 = head, current2 = second;
		while (current2 != null && current2.next != null) {
			current1.next = current2.next;
			current1 = current1.next;
			current2.next = current1.next;
			current2 = current2.next;
		}
		current1.next = second;
		return head;
	}

	public boolean isPowerOfThree(int n) {
		double a = Math.log(n) / Math.log(3);
		return Math.abs(a - Math.rint(a)) <= 0.00000000000001;
	}

	public int coinChange(int[] coins, int amount) {
		int[] minCount = new int[amount + 1];
		minCount[0] = 0;
		int temp;
		for (int i = 1; i <= amount; i++) {
			temp = -1;
			for (int j = 0; j < coins.length; j++) {
				if (i - coins[j] >= 0 && minCount[i - coins[j]] >= 0
						&& (temp == -1 || temp > 1 + minCount[i - coins[j]])) {
					temp = 1 + minCount[i - coins[j]];
				}
			}
			minCount[i] = temp;
		}
		return minCount[amount];
	}

	public int bulbSwitch(int n) {
		for (int i = 1; i <= n; i++) {
			if (i * i + 2 * i >= n) {
				return i;
			}
		}
		return 0;
	}

	public int maxProduct(String[] words) {
		int len = 0, temp;
		for (int i = 0; i < words.length; i++) {
			for (int j = i + 1; j < words.length; j++) {
				temp = compare(words[i], words[j]);
				if (temp > len) {
					len = temp;
				}
			}
		}
		return len;
	}

	public int compare(String a, String b) {
		int[] temp = new int[26];
		for (int i = 0; i < a.length(); i++) {
			temp[a.charAt(i) - 'a'] = 1;
		}
		for (int j = 0; j < b.length(); j++) {
			if (temp[b.charAt(j) - 'a'] == 1) {
				return 0;
			}
		}
		return a.length() * b.length();
	}

	public String removeDuplicateLetters(String s) {
		int[] nums = new int[26];
		int[] current = new int[26];
		int[] result = new int[26];
		int count = 0;
		List<Integer> res = new ArrayList<Integer>();
		for (int i = 0; i < s.length(); i++) {
			nums[s.charAt(i) - 'a']++;
			if (nums[s.charAt(i) - 'a'] == 1) {
				count++;
			}
		}
		for (int i = 0; i < s.length(); i++) {
			current[s.charAt(i) - 'a']++;
			if (result[s.charAt(i) - 'a'] == 0) {
				for (int j = res.size() - 1; j >= 0; j--) {
					if (res.get(j) + 'a' > s.charAt(i) && nums[res.get(j)] - current[res.get(j)] > 0) {
						result[res.get(j)] = 0;
						res.remove(j);
					} else {
						break;
					}
				}
				res.add(s.charAt(i) - 'a');
				result[s.charAt(i) - 'a'] = 1;
			}
			System.out.println(res);
			if (res.size() == count) {
				break;
			}
		}
		String string = "";
		for (int i = 0; i < res.size(); i++) {
			string += (char) (res.get(i) + 'a');
		}
		return string;
	}

	public int singleNumber(int[] nums) {
		int result = 0;
		for (int i = 0; i < nums.length; i++) {
			result ^= nums[i];
		}
		return result;
	}

	public int nthSuperUglyNumber(int n, int[] primes) {
		int[] bound = new int[primes.length];
		int[] result = new int[n];
		result[0] = 1;
		for (int i = 0; i < bound.length; i++) {
			bound[i] = 0;
		}
		int min, k;
		for (int i = 1; i < n; i++) {
			min = primes[0] * result[bound[0]];
			k = 0;
			if (min <= result[i - 1]) {
				bound[0]++;
				min = primes[0] * result[bound[0]];
			}
			for (int j = 1; j < primes.length; j++) {
				if (primes[j] * result[bound[j]] <= result[i - 1]) {
					bound[j]++;
				}
				if (primes[j] * result[bound[j]] < min) {
					k = j;
					min = primes[j] * result[bound[j]];
				}
			}
			result[i] = primes[k] * result[bound[k]];
			bound[k]++;
		}
		return result[n - 1];
	}

	public int maxProfit(int[] prices) {
		if (prices.length < 2) {
			return 0;
		}
		int[] maxProfit = new int[prices.length];
		maxProfit[0] = 0;
		int temp = 0, k = 0;
		for (int i = 1; i < prices.length; i++) {
			temp = 0;
			maxProfit[i] = maxProfit[i - 1];
			for (int j = i - 1; j >= 0; j--) {
				if (prices[i] > prices[j]) {
					k = 0;
					if (j - 2 >= 0) {
						k = maxProfit[j - 2];
					}
					if (k + prices[i] - prices[j] > temp) {
						temp = k + prices[i] - prices[j];
					}
				}
			}
			if (temp > maxProfit[i - 1]) {
				maxProfit[i] = temp;
			}
		}
		return maxProfit[prices.length - 1];
	}

	public boolean isAdditiveNumber(String num) {
		String first = "", second = "", temp, sum;
		for (int i = 0; i < num.length() / 2; i++) {
			for (int j = i + 1; i <= num.length() - 1 - j && j - i <= num.length() - 1 - j; j++) {
				temp = num;
				first = num.substring(0, i + 1);
				second = num.substring(i + 1, j + 1);
				temp = num.substring(j + 1);
				if ((first.startsWith("0") && !first.equals("0")) || (second.startsWith("0") && !second.equals("0"))) {
					continue;
				}
				int f = first.length() - 1, s = second.length() - 1, k = 0;
				sum = "";
				while (f >= 0 && s >= 0) {
					sum = (first.charAt(f) - '0' + second.charAt(s) - '0' + k) % 10 + sum;
					k = (first.charAt(f) - '0' + second.charAt(s) - '0' + k) / 10;
					f--;
					s--;
				}
				while (f >= 0) {
					sum = (first.charAt(f) - '0' + k) % 10 + sum;
					k = (first.charAt(f) - '0' + k) / 10;
					f--;
				}
				while (s >= 0) {
					sum = (second.charAt(s) - '0' + k) % 10 + sum;
					k = (second.charAt(s) - '0' + k) / 10;
					s--;
				}
				if (k > 0) {
					sum = 1 + sum;
				}
				while (temp.startsWith(sum)) {
					first = second;
					second = sum;
					temp = temp.substring(sum.length());
					sum = "";
					f = first.length() - 1;
					s = second.length() - 1;
					k = 0;
					while (f >= 0 && s >= 0) {
						sum = (first.charAt(f) - '0' + second.charAt(s) - '0' + k) % 10 + sum;
						k = (first.charAt(f) - '0' + second.charAt(s) - '0' + k) / 10;
						f--;
						s--;
					}
					while (f >= 0) {
						sum = (first.charAt(f) - '0' + k) % 10 + sum;
						k = (first.charAt(f) - '0' + k) / 10;
						f--;
					}
					while (s >= 0) {
						sum = (second.charAt(s) - '0' + k) % 10 + sum;
						k = (second.charAt(s) - '0' + k) / 10;
						s--;
					}
					if (k > 0) {
						sum = 1 + sum;
					}
				}
				if (temp.length() == 0) {
					System.out.println(sum);
					return true;
				}
			}
		}
		return false;
	}

	public boolean isHappy(int n) {
		Set<Integer> set = new HashSet<Integer>();
		set.add(n);
		int sum = n, k;
		while (sum != 1) {
			n = sum;
			sum = 0;
			while (n != 0) {
				k = n % 10;
				n = n / 10;
				sum = sum + k * k;
			}
			if (set.contains(sum)) {
				return false;
			}
			set.add(sum);
		}
		return true;
	}

	public boolean isBalanced(TreeNode root) {
		if (root == null) {
			return true;
		}
		if (Math.abs(getNodeHeight(root.left) - getNodeHeight(root.right)) <= 1 && isBalanced(root.left)
				&& isBalanced(root.right)) {
			return true;
		}
		return false;
	}

	public int getNodeHeight(TreeNode node) {
		if (node == null) {
			return 0;
		} else {
			return 1 + Integer.max(getNodeHeight(node.left), getNodeHeight(node.right));
		}
	}

	public int trailingZeroes(int n) {
		int num = 0;
		while (n != 0) {
			num += n / 5;
			n = n / 5;
		}
		return num;
	}

	public ListNode removeElements(ListNode head, int val) {
		ListNode current = head, before = head;
		while (current != null) {
			if (current.val == val) {
				if (current == before) {
					before = current.next;
					head = before;
				} else {
					before.next = current.next;
				}
			} else {
				if (before != current) {
					before = before.next;
				}
			}
			current = current.next;
		}
		return head;
	}

	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		// if (headA == null || headB == null) {
		// return null;
		// }
		// ListNode ahead = headA;
		// while (ahead.next != null) {
		// ahead = ahead.next;
		// }
		// ahead.next=headB;
		// ListNode fast=headA,slow=headA,start=headA;
		// while(fast!=null&&fast.next!=null){
		// fast=fast.next.next;
		// slow=slow.next;
		// if(slow==fast){
		// break;
		// }
		// }
		// if(fast==slow&&fast!=null){
		// while(start!=slow){
		// start=start.next;
		// slow=slow.next;
		// }
		// }
		// ahead.next=null;
		// return start;
		int lenA = 0, lenB = 0;
		ListNode nodeA = headA, nodeB = headB;
		while (nodeA != null) {
			lenA++;
			nodeA = nodeA.next;
		}
		while (nodeB != null) {
			nodeB = nodeB.next;
			lenB++;
		}
		nodeA = headA;
		while (lenA > lenB) {
			nodeA = nodeA.next;
			lenA--;
		}
		nodeB = headB;
		while (lenA < lenB) {
			nodeB = nodeB.next;
			lenB--;
		}
		while (nodeA != null && nodeB != null && nodeA != nodeB) {
			nodeA = nodeA.next;
			nodeB = nodeB.next;
		}
		return nodeA;
	}

	public boolean isPalindrome(String s) {
		int left = 0, right = s.length() - 1;
		while (left < right) {
			if (!Character.isLetterOrDigit(s.charAt(left))) {
				left++;
				continue;
			}
			if (!Character.isLetterOrDigit(s.charAt(right))) {
				right--;
				continue;
			}
			if (Character.isLetter(s.charAt(left)) && Character.isLetter(s.charAt(right))
					&& Character.toLowerCase(s.charAt(left)) == Character.toLowerCase(s.charAt(right))) {
				left++;
				right--;
			} else if (s.charAt(left) == s.charAt(right)) {
				left++;
				right--;
			} else {
				return false;
			}
		}
		return true;
	}

	public int countPrimes(int n) {
		boolean[] prims = new boolean[n];
		for (int i = 2; i < n; i++) {
			prims[i] = true;
		}
		for (int i = 2; i * i < n; i++) {
			if (!prims[i]) {
				continue;
			}
			for (int j = i * i; j < n; j += i) {
				prims[j] = false;
			}
		}
		int count = 0;
		for (int i = 2; i < n; i++) {
			if (prims[i]) {
				++count;
			}
		}
		return count;
	}

	public boolean isIsomorphic(String s, String t) {
		Map<Character, Character> maps = new HashMap<Character, Character>();
		Map<Character, Character> mapt = new HashMap<Character, Character>();
		if (s.length() != t.length()) {
			return false;
		}
		for (int i = 0; i < s.length(); i++) {
			if (maps.get(s.charAt(i)) != null) {
				if (maps.get(s.charAt(i)) != t.charAt(i)) {
					return false;
				}
			} else {
				maps.put(s.charAt(i), t.charAt(i));
			}
			if (mapt.get(t.charAt(i)) != null) {
				if (mapt.get(t.charAt(i)) != s.charAt(i)) {
					return false;
				}
			} else {
				mapt.put(t.charAt(i), s.charAt(i));
			}
		}
		return true;
	}

	public boolean increasingTriplet(int[] nums) {
		if (nums.length < 3) {
			return false;
		}
		int min = nums[0], i = 1, max = 0;
		while (i < nums.length) {
			if (nums[i] <= min) {
				min = nums[i];
				i++;
			} else {
				max = nums[i];
				i++;
				break;
			}
		}
		while (i < nums.length) {
			if (nums[i] <= min) {
				min = nums[i];
			} else if (nums[i] < max) {
				max = nums[i];
			} else if (nums[i] > max) {
				System.out.println(min + " " + max + " " + nums[i]);
				return true;
			}
			i++;
		}
		return false;
	}

	public int findKthLargest(int[] nums, int k) {
		Arrays.sort(nums);
		return nums[nums.length - k];
	}

	public int canCompleteCircuit(int[] gas, int[] cost) {
		int pos = 0, sum = 0, current = 0;
		for (int i = 0; i < gas.length; i++) {
			sum += (gas[i] - cost[i]);
			current += (gas[i] - cost[i]);
			if (current < 0) {
				pos = i + 1;
				current = 0;
			}
		}
		if (sum < 0) {
			return -1;
		}
		return pos;
	}

	public int numIslands(char[][] grid) {
		if (grid.length == 0) {
			return 0;
		}
		int[][] visited = new int[grid.length][grid[0].length];
		int res = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (visited[i][j] == 0 && grid[i][j] == '1') {
					res++;
					dfs(i, j, grid, visited);
				}
			}
		}
		return res;
	}

	public void dfs(int i, int j, char[][] grid, int[][] visited) {
		if (i >= 0 && j >= 0 && i < grid.length && j < grid[i].length && visited[i][j] == 0) {
			visited[i][j] = 1;
			if (grid[i][j] == '1') {
				dfs(i - 1, j, grid, visited);
				dfs(i + 1, j, grid, visited);
				dfs(i, j - 1, grid, visited);
				dfs(i, j + 1, grid, visited);
			}
		}
	}

	public TreeNode buildTree(int[] inorder, int[] postorder) {
		return build(inorder, 0, postorder, 0, inorder.length - 1);
	}

	public TreeNode build(int[] inorder, int i, int[] postorder, int k, int j) {
		TreeNode left = null, current = null;
		while (i < inorder.length && k <= j) {
			current = new TreeNode(inorder[i]);
			if (inorder[i] == postorder[k]) {
				if (left != null) {
					current.left = left;
				}
				left = current;
				i++;
				k++;
			} else {
				int t = k + 1;
				for (t = k + 1; t <= j; t++) {
					if (inorder[i] == postorder[t]) {
						break;
					}
				}
				if (left != null) {
					current.left = left;
				}
				current.right = build(inorder, i + 1, postorder, k, t - 1);
				left = current;
				i = i + t - k + 1;
				k = t + 1;
			}
		}
		return current;
	}

	public void pre(TreeNode aNode) {
		if (aNode != null) {
			System.out.println(aNode.val);
			pre(aNode.left);
			pre(aNode.right);
		}
	}

	public TreeNode buildTree1(int[] preorder, int[] inorder) {
		return build1(preorder, 0, inorder, 0, inorder.length - 1);
	}

	public TreeNode build1(int[] preorder, int i, int[] inorder, int j, int k) {
		TreeNode current = null;
		if (i < preorder.length && j <= k) {
			current = new TreeNode(preorder[i]);
			int t = j;
			for (t = j; t <= k; t++) {
				if (preorder[i] == inorder[t]) {
					break;
				}
			}
			current.left = build1(preorder, i + 1, inorder, j, t - 1);
			current.right = build1(preorder, i + t - j + 1, inorder, t + 1, k);
		}
		return current;
	}

	public List<List<String>> partition(String s) {
		List<List<String>> res = new ArrayList<List<String>>();
		addList(s, 0, res, new ArrayList<String>());
		return res;
	}

	public void addList(String s, int begin, List<List<String>> lists, List<String> list) {
		if (begin == s.length()) {
			List<String> temp = new ArrayList<String>();
			temp.addAll(list);
			lists.add(temp);
		}
		for (int i = 0; i + begin < s.length(); i++) {
			int k = 0;
			while (begin + k < begin + i - k && s.charAt(begin + k) == s.charAt(begin + i - k)) {
				k++;
			}
			if (begin + k >= begin + i - k) {
				System.out.println(begin + " " + k);
				list.add(s.substring(begin, i + begin + 1));
				addList(s, begin + i + 1, lists, list);
				list.remove(list.size() - 1);
			}
		}
	}

	public int minSubArrayLen(int s, int[] nums) {
		int left = 0, right = 0, sum = 0, len = nums.length + 2;
		while (right < nums.length) {
			sum += nums[right];
			while (right < nums.length - 1 && sum < s) {
				right++;
				sum += nums[right];
			}
			while (sum >= s) {
				sum -= nums[left];
				left++;
			}
			if (left > 0) {
				sum += nums[--left];
			}
			if (sum >= s && len > right - left + 1) {
				len = right - left + 1;
			}
			right++;
		}
		return len < nums.length + 2 ? len : 0;
	}

	public boolean canFinish(int numCourses, int[][] prerequisites) {
		Queue<Integer> queue = new LinkedList<Integer>();
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		int counts = 0;
		int[] count = new int[numCourses];
		for (int i = 0; i < numCourses; i++) {
			count[i] = 0;
			list.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < prerequisites.length; i++) {
			count[prerequisites[i][0]]++;
			list.get(prerequisites[i][1]).add(prerequisites[i][0]);
		}
		for (int i = 0; i < numCourses; i++) {
			if (count[i] == 0) {
				queue.add(i);
			}
		}
		int k, m;
		while (!queue.isEmpty()) {
			k = queue.poll();
			counts++;
			for (int i = 0; i < list.get(k).size(); i++) {
				m = list.get(k).get(i);
				count[m]--;
				if (count[m] == 0) {
					queue.add(m);
				}
			}
		}
		if (counts == numCourses) {
			return true;
		}
		return false;
	}

	public List<Integer> majorityElement1(int[] nums) {
		List<Integer> res = new ArrayList<Integer>();
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			if (map.get(nums[i]) == null) {
				map.put(nums[i], 1);
			} else {
				map.put(nums[i], map.get(nums[i]) + 1);
			}
		}
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getValue() > nums.length / 3) {
				res.add(entry.getKey());
			}
		}
		return res;
	}

	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		UndirectedGraphNode root = null;
		Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
		Map<Integer, UndirectedGraphNode> map = new HashMap<Integer, UndirectedGraphNode>();
		UndirectedGraphNode temp, curNode;
		if (node != null) {
			queue.add(node);
			curNode = new UndirectedGraphNode(node.label);
			map.put(curNode.label, curNode);
			root = curNode;
		}
		while (!queue.isEmpty()) {
			temp = queue.poll();
			curNode = map.get(temp.label);
			for (UndirectedGraphNode a : temp.neighbors) {
				if (map.containsKey(a.label)) {
					curNode.neighbors.add(map.get(a.label));
				} else {
					queue.add(a);
					UndirectedGraphNode undirectedGraphNode = new UndirectedGraphNode(a.label);
					curNode.neighbors.add(undirectedGraphNode);
					map.put(a.label, undirectedGraphNode);
				}
			}
		}
		return root;
	}

	public boolean wordBreak(String s, Set<String> wordDict) {
		List<String> words = new ArrayList<String>();
		for (String temp : wordDict) {
			words.add(temp);
		}
		String sum = new String();
		return breaks(s, sum, words, 0);
	}

	public boolean wordBreak1(String s, Set<String> wordDict) {
		for (String str : wordDict) {
			if (s.startsWith(str)) {
				System.out.println(str + " " + s.substring(str.length()));
				if (wordBreak1(s.substring(str.length()), wordDict) == true) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean wordBreak2(String s, Set<String> wordDict) {
		if (s == null) {
			return false;
		}
		boolean[] array = new boolean[s.length()];
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < s.length(); i++) {
			array[i] = false;
			if (wordDict.contains(s.substring(0, i + 1))) {
				array[i] = true;
				list.add(i);
			} else {
				for (int j = list.size() - 1; j >= 0; j--) {
					if (wordDict.contains(s.substring(list.get(j) + 1, i + 1))) {
						array[i] = true;
						list.add(i);
						break;
					}
				}
			}
			System.out.println(i + " " + array[i]);
		}
		return array[array.length - 1];
	}

	public boolean breaks(String s, String sum, List<String> list, int n) {
		if (n == list.size() - 1) {
			sum = sum + list.get(list.size() - 1);
			if (sum.equals(s)) {
				return true;
			}
			return false;
		}
		String temp, temp1;
		for (int i = n; i < list.size(); i++) {
			temp1 = list.get(i);
			list.set(i, list.get(n));
			list.set(n, temp1);
			temp = sum + temp1;
			if (s.startsWith(temp)) {
				if (breaks(s, temp, list, n + 1) == true) {
					return true;
				}
			}
			list.set(n, list.get(i));
			list.set(i, temp1);
		}
		return false;
	}

	public int countNodes(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int l = getHeight(root.left), r = getHeight(root.right);
		if (l == r) {
			return (1 << l) + countNodes(root.right);
		}
		return (1 << r) + countNodes(root.left);
	}

	public int getHeight(TreeNode root) {
		int height = 0;
		while (root != null) {
			height++;
			root = root.left;
		}
		return height;
	}

	public ListNode sortList(ListNode head) {
		int len = 0;
		ListNode node = head;
		while (node != null) {
			len++;
			node = node.next;
		}
		if (len <= 1) {
			return head;
		}
		node = head;
		for (int i = 0; i < len / 2 - 1; i++) {
			node = node.next;
		}
		ListNode a = node.next;
		node.next = null;
		head = sortList(head);
		a = sortList(a);
		return merge(head, a);
	}

	public ListNode merge(ListNode first, ListNode second) {
		ListNode root = null, current = null;
		if (first.val > second.val) {
			root = second;
			second = second.next;
		} else {
			root = first;
			first = first.next;
		}
		current = root;
		while (first != null && second != null) {
			if (first.val > second.val) {
				current.next = second;
				second = second.next;
			} else {
				current.next = first;
				first = first.next;
			}
			current = current.next;
		}
		while (first != null) {
			current.next = first;
			first = first.next;
			current = current.next;
		}
		while (second != null) {
			current.next = second;
			second = second.next;
			current = current.next;
		}
		current.next = null;
		return root;
	}

	public List<String> findRepeatedDnaSequences(String s) {
		List<String> result = new ArrayList<String>();
		if (s.length() < 11) {
			return result;
		}
		Map<String, Integer> map = new HashMap<String, Integer>();
		String sub = new String();
		for (int i = 8; i < s.length(); i++) {
			sub = s.substring(i - 9, i + 1);
			if (map.containsKey(sub)) {
				if (map.get(sub) == 1) {
					map.put(sub, 2);
					result.add(sub);
				}
			} else {
				map.put(sub, 1);
			}
		}
		return result;
	}

	public int countDigitOne(int n) {
		if (n <= 0) {
			return 0;
		}
		int count = 0, m = n, left = 0, weight = 1, right = 0;
		while (m != 0) {
			left = m / 10;
			if (weight > 1) {
				right = n % weight;
			}
			if (m % 10 == 0) {
				count = count + left * weight;
			} else if (m % 10 == 1) {
				count = count + left * weight + right + 1;
			} else {
				count = count + (left + 1) * weight;
			}
			m = left;
			weight = weight * 10;
		}
		return count;
	}

	public int evalRPN(String[] tokens) {
		Stack<Integer> stack = new Stack<Integer>();
		int a, b;
		for (int i = 0; i < tokens.length; i++) {
			if (tokens[i].equals("+")) {
				a = stack.pop();
				b = stack.pop();
				stack.push(a + b);
			} else if (tokens[i].equals("-")) {
				a = stack.pop();
				b = stack.pop();
				stack.push(b - a);
			} else if (tokens[i].equals("*")) {
				a = stack.pop();
				b = stack.pop();
				stack.push(a * b);
			} else if (tokens[i].equals("/")) {
				a = stack.pop();
				b = stack.pop();
				stack.push(b / a);
			} else {
				stack.push(Integer.parseInt(tokens[i]));
			}
		}
		return stack.pop();
	}

	public int maximalSquare(char[][] matrix) {
		if (matrix.length == 0) {
			return 0;
		}
		int[][] num = new int[matrix.length][matrix[0].length];
		int max = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				num[i][j] = 0;
				if (matrix[i][j] == '1') {
					if (i - 1 >= 0 && j - 1 >= 0) {
						num[i][j] = Integer.min(num[i - 1][j], num[i][j - 1]);
						num[i][j] = Integer.min(num[i][j], num[i - 1][j - 1]);
					}
					num[i][j]++;
				}
				if (max < num[i][j]) {
					max = num[i][j];
				}
			}
		}
		return max * max;
	}

	public boolean exist(char[][] board, String word) {
		if (board == null) {
			return false;
		}
		if (word == null || word.length() == 0) {
			return true;
		}
		if (board.length == 0) {
			return false;
		}
		boolean[][] visited = new boolean[board.length][board[0].length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (dfs(board, visited, word, i, j, 0) == true) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean dfs(char[][] board, boolean[][] visitd, String word, int i, int j, int n) {
		if (n == word.length()) {
			return true;
		}
		if (i >= 0 && j >= 0 && i < board.length && j < board[i].length && visitd[i][j] == false
				&& board[i][j] == word.charAt(n)) {
			visitd[i][j] = true;
			if (dfs(board, visitd, word, i - 1, j, n + 1) == true) {
				return true;
			}
			if (dfs(board, visitd, word, i + 1, j, n + 1) == true) {
				return true;
			}
			if (dfs(board, visitd, word, i, j - 1, n + 1) == true) {
				return true;
			}
			if (dfs(board, visitd, word, i, j + 1, n + 1) == true) {
				return true;
			}
			visitd[i][j] = false;
		}
		return false;
	}

	public void reorderList(ListNode head) {
		if (head == null) {
			return;
		}
		int count = 0;
		ListNode first = head, second, third, four;
		while (first != null) {
			count++;
			first = first.next;
		}
		if (count % 2 == 1) {
			count = count / 2;
		} else {
			count = count / 2 - 1;
		}
		first = head;
		for (int i = 0; i < count; i++) {
			first = first.next;
		}
		second = first.next;
		first.next = null;
		if (second != null) {
			first = second;
			second = second.next;
			first.next = null;
		}
		while (second != null) {
			third = second.next;
			second.next = first;
			first = second;
			second = third;
		}
		second = first;
		first = head;
		third = first.next;
		four = second.next;
		while (second != null) {
			first.next = second;
			second.next = third;
			first = third;
			second = four;
			if (first != null) {
				third = first.next;
			}
			if (second != null) {
				four = second.next;
			}
		}
	}

	public int maxProduct(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}
		int max = nums[0], k = nums[0], m = nums[0], a, b;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] == 0) {
				k = 0;
				m = 0;
			} else {
				a = nums[i] * k;
				b = nums[i] * m;
				if (a >= b) {
					k = a;
					m = b;
				} else {
					k = b;
					m = a;
				}
				if (k < nums[i]) {
					k = nums[i];
				}
				if (m > nums[i]) {
					m = nums[i];
				}
			}
			if (k > max) {
				max = k;
			}
		}
		return max;
	}

	public String simplifyPath(String path) {
		String[] array = path.split("/");
		Stack<String> stack = new Stack<String>();
		for (String a : array) {
			if (a.equals("") || a.equals(".")) {
				continue;
			} else if (a.equals("..")) {
				if (!stack.empty()) {
					stack.pop();
				}
			} else {
				stack.push(a);
			}
		}
		String res = "";
		while (!stack.empty()) {
			res = "/" + stack.pop() + res;
		}
		if (res.equals("")) {
			res = "/";
		}
		return res;
	}

	public void wiggleSort(int[] nums) {
		Arrays.sort(nums);
		int[] res = Arrays.copyOf(nums, nums.length);
		int left = nums.length / 2, right = nums.length - 1;
		if (nums.length % 2 == 0) {
			left--;
		}
		int mid = left, k = 0;
		while (right > mid) {
			nums[k++] = res[left--];
			nums[k++] = res[right--];
		}
		if (left == 0) {
			nums[k] = res[0];
		}
		for (int num : nums) {
			System.out.println(num);
		}
	}

	public int[] findOrder(int numCourses, int[][] prerequisites) {
		int[] res = new int[numCourses];
		int[] count = new int[numCourses];
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		Queue<Integer>queue=new LinkedList<Integer>();
		for (int i = 0; i < numCourses; i++) {
			count[i] = 0;
			list.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < prerequisites.length; i++) {
			count[prerequisites[i][0]]++;
			list.get(prerequisites[i][1]).add(prerequisites[i][0]);
		}
		for(int i=0;i<count.length;i++){
			if(count[i]==0){
				queue.add(i);
			}
		}
		int num,index=0,temp;
		while(!queue.isEmpty()){
			num=queue.poll();
			res[index++]=num;
			for(int i=0;i<list.get(num).size();i++){
				temp=list.get(num).get(i);
				count[temp]--;
				if(count[temp]==0){
					queue.add(temp);
				}
			}
		}
		
		if(index!=numCourses){
			res=new int[0];
		}
		return res;
	}

	public static void main(String[] args) {
		NewWater newWater = new NewWater();
		System.out.println(newWater.findOrder(4, new int[][]{{1,0},{2,0},{3,1},{3,2}}));
//		newWater.wiggleSort(new int[] { 1, 3, 2, 2, 3, 1, 5 }){};
		// System.out.println(Integer.toBinaryString(8));
		// System.out.println(newWater.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
		// TreeNode a = new TreeNode(1);
		// TreeNode b = new TreeNode(2);
		// TreeNode c = new TreeNode(3);
		// a.left = b;
		// a.right = c;
		// System.out.println(newWater.countNodes(a));
		// Set<String> set = new HashSet<String>();
		// set.add("a");
		// set.add("aa");
		// set.add("aaa");
		// set.add("aaaa");
		// set.add("aaaaa");
		// set.add("aaaaaa");
		// set.add("aaaaaaa");
		// set.add("aaaaaaaa");
		// set.add("aaaaaaaaa");
		// set.add("aaaaaaaaaa");
		// System.out.println(newWater.wordBreak2(
		// "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
		// set));
		// int[][] nums = { { 3, 4, 5 }, { 3, 2, 6 }, { 2, 2, 1 }, };
		// System.out.println(newWater.longestIncreasingPath(nums));
		// ListNode a = new ListNode(1);
		// ListNode b = new ListNode(2);
		// ListNode c = new ListNode(3);
		// ListNode d = new ListNode(4);
		// ListNode e = new ListNode(5);
		// ListNode f = new ListNode(6);
		// ListNode g = new ListNode(7);
		// ListNode h = new ListNode(8);
		// a.next = b;
		// b.next = c;
		// // c.next = d;
		// // d.next = e;
		// // e.next = f;
		// // f.next = g;
		// newWater.reorderList(a);
		// // g.next = h;
		// // a = newWater.sortList(a);
		// while (a != null) {
		// System.out.println(a.val);
		// a = a.next;
		// }
		// a = newWater.reverseList(a);
		// while (a != null) {
		// System.out.println(a.val);
		// a = a.next;
		// }
		// for (int i = 1; i < 30; i++) {
		// System.out.println(newWater.maxProduct(new String[] { "a", "ab",
		// "abc", "d", "cd", "bcd", "abcd" }));
		// }
		// Set<Integer> set = new LinkedHashSet<Integer>();
		// set.add(6);
		// set.add(2);
		// set.add(3);
		// set.add(4);
		// // set.get(1);
		// System.out.println(set.contains(2));
		// int[] primes = { 2, 5, 7, 11, 13, 17, 23, 29, 43, 53 };
		// System.out.println(newWater.hammingWeight(2147483647 + 1));
		// for (int i = 1; i <= 100; i++) {
		// System.out.println(i + " " + newWater.isHappy(i));
		// }
		// TreeNode a = new TreeNode(1);
		// TreeNode b = new TreeNode(1);
		// TreeNode c = new TreeNode(1);
		// TreeNode d = new TreeNode(1);
		// TreeNode e = new TreeNode(1);
		// a.left = b;
		// b.right = c;
		// b.left = d;
		// d.right = e;
		// System.out.println(newWater.numIslands(
		// new char[][] { { 1, 1, 0, 0, 0 }, { 1, 1, 0, 0, 0 }, { 0, 0, 1, 0, 0
		// }, { 0, 0, 0, 1, 1 } }));
		// System.out.println(newWater.isIsomorphic("foo", "bar"));
		// System.out.println(newWater.isIsomorphic("paper", "title"));
		// System.out.println(newWater.isIsomorphic("ab", "aa"));
	}

}

class UndirectedGraphNode {
	int label;
	List<UndirectedGraphNode> neighbors;

	UndirectedGraphNode(int x) {
		label = x;
		neighbors = new ArrayList<UndirectedGraphNode>();
	}
};