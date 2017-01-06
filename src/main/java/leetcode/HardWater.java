package leetcode;

import java.util.*;

public class HardWater {
	public int findMin(int[] nums) {
		return min(0, nums.length - 1, nums);
	}

	public int min(int left, int right, int[] nums) {
		if (left == right) {
			return nums[left];
		}
		int mid = (left + right) / 2, a, b;
		if (nums[left] > nums[mid]) {
			return min(left, mid, nums);

		} else if (nums[left] == nums[mid]) {
			a = nums[mid];
			b = nums[mid];
			if (mid - 1 >= left) {
				a = min(left, mid - 1, nums);
			}
			if (mid + 1 <= right) {
				b = min(mid + 1, right, nums);
			}
			return Math.min(a, b);
		} else {
			a = nums[left];
			b = min(mid, right, nums);
			return Math.min(a, b);
		}

	}

	public int trap(int[] height) {
		int sum = 0, left = 0, right = height.length - 1, sumTemp = 0, i = 0, j = right;
		while (left <= right) {
			while (left <= right && height[left] >= height[right]) {
				if (height[right] < height[j]) {
					sumTemp += (height[j] - height[right]);
				} else {
					j = right;
				}
				right--;
			}
			if (left > right) {
				break;
			}
			sum = sum + sumTemp;
			j = right;
			sumTemp = 0;
			while (left <= right && height[left] < height[right]) {
				if (height[left] < height[i]) {
					sumTemp += (height[i] - height[left]);
				} else {
					i = left;
				}
				left++;
			}
			if (left > right) {
				break;
			}
			sum = sum + sumTemp;
			i = left;
			sumTemp = 0;
		}
		sum = sum + sumTemp;
		return sum;
	}

	public int longestConsecutive(int[] nums) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			if (!map.containsKey(nums[i])) {
				map.put(nums[i], 0);
			}
		}
		int result = 0, count;
		for (int i = 0; i < nums.length; i++) {
			if (map.get(nums[i]) == 0) {
				map.put(nums[i], 1);
				int k = nums[i];
				count = 1;
				while (map.containsKey(--k) && map.get(k) == 0) {
					count++;
					map.put(k, 1);
				}
				k = nums[i];
				while (map.containsKey(++k) && map.get(k) == 0) {
					count++;
					map.put(k, 1);
				}
				if (count > result) {
					result = count;
				}
			}
		}
		return result;
	}

	public ListNode reverseKGroup(ListNode head, int k) {
		if (k < 2) {
			return head;
		}
		ListNode first = head, second = null, third = null, start = null, begin = head, root = null;
		if (head == null) {
			return null;
		}
		int step = 1, state = 0;
		second = first.next;
		while (second != null) {
			third = second.next;
			second.next = first;
			first = second;
			second = third;
			step++;
			if (step == k) {
				if (state == 0) {
					root = first;
					state = 1;
				}
				if (start != null) {
					start.next = first;
					start = begin;
				} else {
					start = begin;
				}
				first = second;
				if (second != null) {
					second = second.next;
				}
				begin = first;
				step = 1;
			}
		}
		if (first != null) {
			begin.next = null;
			second = first.next;
			begin = first;
			while (second != null) {
				third = second.next;
				second.next = first;
				first = second;
				second = third;
			}
			begin.next = null;
		}
		if (root == null) {
			root = first;
			return root;
		}
		start.next = first;
		return root;
	}

	public int maxPathSum(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int[] max = new int[1];
		max[0] = root.val;
		dfs(root, max);
		return max[0];
	}

	public int dfs(TreeNode node, int[] max) {
		if (node == null) {
			return 0;
		}
		int left = dfs(node.left, max);
		int right = dfs(node.right, max);
		int k = node.val;
		if (left > 0) {
			k += left;
		}
		if (right > 0) {
			k += right;
		}
		if (max[0] < k) {
			max[0] = k;
		}
		if (left < right) {
			left = right;
		}
		if (left > 0) {
			return node.val + left;
		}
		return node.val;
	}

	public ListNode mergeKLists(ListNode[] lists) {
		List<Integer> list = new ArrayList<Integer>();
		ListNode node, head;
		for (int i = 0; i < lists.length; i++) {
			node = lists[i];
			while (node != null) {
				list.add(node.val);
				node = node.next;
			}
		}
		if (list.size() == 0) {
			return null;
		}
		Collections.sort(list);
		head = new ListNode(list.get(0));
		node = head;
		for (int i = 1; i < list.size(); i++) {
			node.next = new ListNode(list.get(i));
			node = node.next;
		}
		return head;
		// int len = 0;
		// for (int i = 0; i < lists.length; i++) {
		// if (lists[i] != null) {
		// lists[len++] = lists[i];
		// }
		// }
		// if (len == 0) {
		// return null;
		// }
		// ListNode head = null, current = null;
		// int position, k;
		// while (len > 0) {
		// position = -1;
		// k = 0;
		// for (int i = 0; i < len; i++) {
		// if (lists[i] != null) {
		// lists[k++] = lists[i];
		// if (position == -1 || (lists[i].val < lists[position].val)) {
		// position = k - 1;
		// }
		// }
		// }
		// len = k;
		// if(len<=0){
		// break;
		// }
		// if (head == null) {
		// head = lists[position];
		// current = head;
		// } else {
		// current.next = lists[position];
		// current = current.next;
		// }
		// lists[position] = lists[position].next;
		// }
		// return head;
	}

	public int longestValidParentheses(String s) {
		if (s.length() < 2) {
			return 0;
		}
		int[] count = new int[s.length()];
		int max = 0;
		count[0] = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ')') {
				if (i - 1 >= 0) {
					if (s.charAt(i - 1) == ')') {
						if (i - 2 * count[i - 1] - 1 >= 0 && s.charAt(i - 2 * count[i - 1] - 1) == '(') {
							count[i] = 1 + count[i - 1];
							if (i - 2 * count[i - 1] - 2 >= 0) {
								count[i] += count[i - 2 * count[i - 1] - 2];
							}
						}
					} else {
						count[i] = 1;
						if (i - 2 >= 0) {
							count[i] += count[i - 2];
						}
					}
				}
				if (count[i] > max) {
					max = count[i];
				}
			}
		}
		return max * 2;
	}

	public int candy(int[] ratings) {
		if (ratings.length == 0) {
			return 0;
		}
		int[] left = new int[ratings.length];
		int[] right = new int[ratings.length];
		left[0] = 1;
		for (int i = 1; i < ratings.length; i++) {
			if (ratings[i] > ratings[i - 1]) {
				left[i] = left[i - 1] + 1;
			} else {
				left[i] = 1;
			}
		}
		right[ratings.length - 1] = 1;
		for (int i = right.length - 2; i >= 0; i--) {
			if (ratings[i] > ratings[i + 1]) {
				right[i] = right[i + 1] + 1;
			} else {
				right[i] = 1;
			}
		}
		int sum = 0;
		for (int i = 0; i < ratings.length; i++) {
			sum += Integer.max(left[i], right[i]);
		}
		return sum;
	}

	public boolean isInterleave(String s1, String s2, String s3) {
		if (s1.length() + s2.length() != s3.length()) {
			return false;
		}
		if (s1.length() == 0) {
			return s2.equals(s3);
		}
		if (s2.length() == 0) {
			return s1.equals(s3);
		}
		boolean[][] array = new boolean[s1.length() + 1][s2.length() + 1];
		array[0][0] = true;
		for (int i = 1; i <= s1.length(); i++) {
			if (s1.charAt(i - 1) == s3.charAt(i - 1)) {
				array[i][0] = array[i - 1][0];
			}
		}
		for (int i = 1; i <= s2.length(); i++) {
			if (s2.charAt(i - 1) == s3.charAt(i - 1)) {
				array[0][i] = array[0][i - 1];
			}
		}
		for (int i = 1; i <= s1.length(); i++) {
			for (int j = 1; j <= s2.length(); j++) {
				if ((s1.charAt(i - 1) == s3.charAt(i + j - 1) && array[i - 1][j])
						|| (s2.charAt(j - 1) == s3.charAt(i + j - 1) && array[i][j - 1])) {
					array[i][j] = true;
				}
			}
		}
		return array[s1.length()][s2.length()];
	}

	public boolean isMatch(String s, String p) {
		boolean[][] array = new boolean[s.length() + 1][p.length() + 1];
		array[0][0] = true;
		for (int i = 0; i < p.length(); i++) {
			if (p.charAt(i) == '*') {
				array[0][i + 1] = array[0][i - 1];
			}
		}
		for (int i = 1; i <= s.length(); i++) {
			for (int j = 1; j <= p.length(); j++) {
				if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
					array[i][j] = array[i - 1][j - 1];
				} else if (p.charAt(j - 1) == '*') {
					if (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.') {
						if (array[i - 1][j] || array[i][j - 2]) {
							array[i][j] = true;
						}
					} else {
						array[i][j] = array[i][j - 2];
					}
				}
			}
		}
		return array[s.length()][p.length()];
	}

	public int minCut(String s) {
		if (s.length() < 2) {
			return 0;
		}
		boolean[][] array = new boolean[s.length()][s.length()];
		for (int len = 1; len <= s.length(); len++) {
			for (int i = 0; i + len - 1 < s.length(); i++) {
				if (s.charAt(i) == s.charAt(i + len - 1)) {
					if (len - 1 < 2) {
						array[i][i + len - 1] = true;
					} else {
						array[i][i + len - 1] = array[i + 1][i + len - 2];
					}
				}
			}
		}
		int[] result = new int[s.length()];
		int min;
		for (int i = 0; i < s.length(); i++) {
			min = Integer.MAX_VALUE;
			for (int j = 0; j <= i; j++) {
				if (array[j][i]) {
					if (j - 1 >= 0) {
						min = Integer.min(min, 1 + result[j - 1]);
					} else {
						min = 0;
					}
				}
			}
			result[i] = min;
		}
		return result[s.length() - 1];
	}

	public String minWindow(String s, String t) {
		if (s.length() < t.length()) {
			return "";
		}
		int start = 0, min = s.length(), begin = 0, end = 0, num = 0;
		int[] count = new int[256];
		for (int i = 0; i < t.length(); i++) {
			if (count[t.charAt(i)] == 0) {
				num++;
			}
			count[t.charAt(i)]++;
		}
		int[] counts = new int[256];
		char c = ' ';
		while (end < s.length() && num > 0) {
			c = s.charAt(end);
			counts[c]++;
			if (counts[c] == count[c]) {
				num--;
			}
			if (num == 0) {
				break;
			}
			end++;
		}
		if (num > 0) {
			return "";
		}
		min = end - begin + 1;
		while (end < s.length()) {
			while (begin <= end) {
				c = s.charAt(begin);
				counts[c]--;
				if (counts[c] < count[c]) {
					break;
				}
				begin++;
			}
			System.out.println(begin + " " + end);
			if (end - begin + 1 < min) {
				min = end - begin + 1;
				start = begin;
			}
			begin++;
			end++;
			while (end < s.length()) {
				counts[s.charAt(end)]++;
				if (s.charAt(end) == c) {
					break;
				}
				end++;
			}
			if (end < s.length() && end - begin + 1 < min) {
				min = end - begin + 1;
				start = begin;
			}
		}

		return s.substring(start, start + min);
	}

	public List<Integer> findSubstring(String s, String[] words) {
		List<Integer> res = new ArrayList<Integer>();
		if (words.length == 0) {
			for (int i = 0; i < words.length; i++) {
				res.add(i);
			}
		}
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < words.length; i++) {
			if (map.containsKey(words[i])) {
				map.put(words[i], map.get(words[i]) + 1);
			} else {
				map.put(words[i], 1);
			}
		}
		int len = words[0].length(), k, t = 0;
		String sub;
		for (int i = 0; i < s.length() - words.length * len + 1; i++) {
			sub = s.substring(i, i + len);
			if (map.containsKey(sub)) {
				Map<String, Integer> temp = new HashMap<String, Integer>();
				temp.put(sub, 1);
				k = i + len;
				t = 1;
				while (k + len <= s.length()) {
					sub = s.substring(k, k + len);
					if (!map.containsKey(sub)) {
						break;
					}
					if (temp.containsKey(sub)) {
						if (temp.get(sub) == map.get(sub)) {
							break;
						}
						temp.put(sub, temp.get(sub) + 1);
					} else {
						temp.put(sub, 1);
					}
					k = k + len;
					t++;
				}
				if (t == words.length) {
					res.add(i);
				}
			}
		}
		return res;
	}

	public List<String> wordBreak(String s, Set<String> wordDict) {
		// List<List<String>>result=new ArrayList<List<String>>();
		// List<String>temp,temps;
		// String string;
		// for(int i=0;i<s.length();i++){
		// temp=new ArrayList<String>();
		// result.add(temp);
		// string=s.substring(0, i+1);
		// if(wordDict.contains(string)){
		// temp.add(string+" ");
		// }
		// for(int j=1;j<i;j++){
		// string=s.substring(j, i+1);
		// if(wordDict.contains(string)){
		// temps=result.get(j-1);
		// for(int k=0;k<temps.size();k++){
		// temp.add(temps.get(k)+string+" ");
		// }
		// }
		// }
		// }
		// temp=new ArrayList<>();
		// for(int i=0;i<result.get(s.length()-1).size();i++){
		// temp.add(result.get(s.length()-1).get(i).trim());
		// }
		// return temp;
		List<String> res = new ArrayList<String>();
		if (s.length() == 0) {
			return res;
		}
		wordbreak(s, 0, wordDict, "", res);
		for (int i = 0; i < res.size(); i++) {
			res.set(i, res.get(i).trim());
		}
		return res;
	}

	public void wordbreak(String s, int k, Set<String> wordDict, String str, List<String> res) {
		if (k == s.length()) {
			res.add(str);
			return;
		}
		String temp;
		for (int i = k; i < s.length(); i++) {
			temp = s.substring(k, i + 1);
			if (wordDict.contains(temp)) {
				temp = str + temp + " ";
				wordbreak(s, i + 1, wordDict, temp, res);
			}
		}
	}

	public boolean isMatch1(String s, String p) {
		if (s == null || p == null) {
			return false;
		}
		boolean[][] res = new boolean[s.length() + 1][p.length() + 1];
		boolean[] array = new boolean[p.length() + 1];
		res[0][0] = true;
		for (int i = 0; i < p.length(); i++) {
			if (p.charAt(i) == '*') {
				res[0][i + 1] = res[0][i];
			}
			if (res[0][i + 1] == true) {
				array[i + 1] = true;
			}
		}
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < p.length(); j++) {
				if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
					res[i + 1][j + 1] = res[i][j];
				} else if (p.charAt(j) == '*') {
					if (array[j + 1] || res[i + 1][j])
						res[i + 1][j + 1] = true;
				}
				if (res[i + 1][j + 1] == true) {
					array[j + 1] = true;
				}
			}
		}
		return res[s.length()][p.length()];
	}

	public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
		List<List<String>> result = new ArrayList<List<String>>();
		if (beginWord == null) {
			result.add(null);
			return result;
		}
		if (beginWord.equals(endWord)) {
			List<String> list = new ArrayList<String>();
			list.add(beginWord);
			result.add(list);
			return result;
		}
		Queue<List<String>> queue = new LinkedList<List<String>>();
		Set<String> set = new HashSet<String>();
		List<String> list = new ArrayList<String>();
		set.add(beginWord);
		list.add(beginWord);
		queue.add(list);
		queue.add(null);
		boolean state = false;
		Set<String> visitLevel = new HashSet<String>();
		while (!queue.isEmpty()) {
			list = queue.poll();
			if (list == null) {
				if (queue.isEmpty() || state) {
					break;
				} else {
					queue.add(null);
					set.addAll(visitLevel);
					visitLevel.clear();
				}
			} else if (findOne(list, endWord, visitLevel, set, wordList, queue, result)) {
				state = true;
			}

		}
		return result;
	}

	public boolean findOne(List<String> list, String endWord, Set<String> visitLevel, Set<String> set,
			Set<String> wordList, Queue<List<String>> queue, List<List<String>> result) {
		char[] chars = list.get(list.size() - 1).toCharArray();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			for (int j = 0; j < 26; j++) {
				chars[i] = (char) ('a' + j);
				String temp = new String(chars);
				if (temp.equals(endWord)) {
					list.add(temp);
					result.add(list);
					return true;
				}
				if (wordList.contains(temp) && !set.contains(temp)) {
					visitLevel.add(temp);
					List<String> temps = new ArrayList<String>();
					temps.addAll(list);
					temps.add(temp);
					queue.add(temps);
				}
			}
			chars[i] = c;
		}
		return false;
	}

	public boolean isNumber(String s) {
		if (s == null || s.length() == 0) {
			return false;
		}
		s = s.trim();
		boolean flag = false;
		boolean e = false;
		boolean point = false;
		boolean res = false;
		char c;
		for (int i = 0; i < s.length(); i++) {
			c = s.charAt(i);
			if (c == '-') {
				if (flag || (point && !e) || res) {
					return false;
				}
				flag = true;
				res = false;
			} else if (c == '+') {
				if (flag || (point && !e) || res) {
					return false;
				}
				flag = true;
				res = false;
			} else if (c == 'e') {
				if (e || !res) {
					return false;
				}
				e = true;
				res = false;
				flag = false;
			} else if (c == '.') {
				if (e || point) {
					return false;
				}
				point = true;
			} else if (!Character.isDigit(c)) {
				return false;
			} else {
				res = true;
			}
		}
		return res;
	}

	public List<List<Integer>> palindromePairs(String[] words) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		String string;
		int left, right;
		for (int i = 0; i < words.length; i++) {
			for (int j = i + 1; j < words.length; j++) {
				string = words[i] + words[j];
				left = 0;
				right = string.length() - 1;
				while (left < right && string.charAt(left) == string.charAt(right)) {
					left++;
					right--;
				}
				if (left >= right) {
					List<Integer> list = new ArrayList<Integer>();
					list.add(i);
					list.add(j);
					result.add(list);
				}
				string = words[j] + words[i];
				left = 0;
				right = string.length() - 1;
				while (left < right && string.charAt(left) == string.charAt(right)) {
					left++;
					right--;
				}
				if (left >= right) {
					List<Integer> list = new ArrayList<Integer>();
					list.add(j);
					list.add(i);
					result.add(list);
				}
			}
		}
		return result;
	}

	public void solveSudoku(char[][] board) {
		if (solve(board, 0, 0)) {
			for (int k = 0; k < board.length; k++) {
				System.out.println(board[k]);
			}
		}
	}

	public boolean solve(char[][] board, int i, int j) {
		if (i == board.length) {
			return true;
		}
		if (j == board[i].length) {
			return solve(board, i + 1, 0);
		}
		if (board[i][j] != '.') {
			return solve(board, i, j + 1);
		}
		Set<Integer> set = new HashSet<Integer>();
		int left = 0, right = 0;
		while (left < board.length) {
			if (board[left][j] != '.') {
				set.add(board[left][j] - '0');
			}
			left++;
		}
		while (right < board[0].length) {
			if (board[i][right] != '.') {
				set.add(board[i][right] - '0');
			}
			right++;
		}
		left = i - i % 3;
		right = j - j % 3;
		for (int m = left; m < left + 3; m++) {
			for (int n = right; n < right + 3; n++) {
				if (board[m][n] != '.') {
					set.add(board[m][n] - '0');
				}
			}
		}
		for (int m = 1; m <= 9; m++) {
			if (!set.contains(m)) {
				board[i][j] = (char) (m + '0');
				if (solve(board, i, j + 1) == true) {
					return true;
				}
				board[i][j] = '.';
			}
		}
		return false;
	}

	public int[] countBits(int num) {
		int k = 0, t;
		int[] count = new int[num + 1];
		count[0] = 0;
		for (int i = 0; i < num; i++) {
			t = (i + 1) & (-i - 1);
			k = k + 1 - (int) (Math.log(t) / Math.log(2));
			count[i + 1] = k;
			System.out.println(k);
		}
		return count;
	}

	public int rob(TreeNode root) {
       return robber(root);
	}
    public int robber(TreeNode node){
    	if(node==null){
    		return 0;
    	}
    	int a=robber(node.left)+robber(node.right),b=node.val;
    	if(node.left!=null){
    		b+=robber(node.left.left);
    		b+=robber(node.left.right);
    	}
    	if(node.right!=null){
    		b+=robber(node.right.left);
    		b+=robber(node.right.right);
    	}
    	return Math.max(a, b);
    }
	public static void main(String[] args) {
		HardWater hardWater = new HardWater();
		// System.out.println(hardWater
		// .trap(new int[] { 6, 4, 2, 0, 3, 2, 0, 3, 1, 4, 5, 3, 2, 7, 5, 3, 0,
		// 1, 2, 1, 3, 4, 6, 8, 1, 3 }));
		// System.out.println(hardWater.longestConsecutive(new int[] { 100, 4,
		// 200, 1, 3, 2 }));
		// ListNode a = new ListNode(1);
		// ListNode b = new ListNode(2);
		// ListNode c = new ListNode(3);
		// ListNode d = new ListNode(4);
		// ListNode e = new ListNode(5);
		// ListNode f = new ListNode(6);
		// ListNode g=new ListNode(7);
		// TreeNode a = new TreeNode(0);
		// TreeNode b = new TreeNode(2);
		// TreeNode c = new TreeNode(-1);
		// a.next = c;
		// c.next = e;
		// b.next = d;
		// d.next = f;
		// c.next = d;
		// d.next = e;
		// e.next = f;
		// f.next=g;
		// a = hardWater.reverseKGroup(a, 6);
		// while (a != null) {
		// System.out.println(a.val);
		// a = a.next;
		// }
		// ListNode[] lists = new ListNode[6];
		// lists[0] = f;
		// lists[1] = e;
		// lists[2] = d;
		// lists[3] = c;
		// lists[4] = b;
		// lists[5] = a;
		// a = hardWater.mergeKLists(lists);
		// while (a != null) {
		// System.out.println(a.val);
		// a = a.next;
		// }
		// String aString="aaab";
		// System.out.println(aString.indexOf('c'));
		// int a=scanner.nextInt();
		// System.out.println(hardWater.minWindow("ADOBECODEBANC", "CODE"));
		// Set<String> set = new HashSet<String>();
		// set.add("red");
		// set.add("tax");
		// set.add("ted");
		// set.add("tex");
		// set.add("tad");
		// set.add("den");
		// set.add("rex");
		// set.add("pee");
		// set.add("catsand");
		// set.add("catsanddog");
		// System.out.println(hardWater.findLadders("red", "tax", set));
		// double a=011;
		// System.out.println(a);
		// char[][] board = { "53..7....".toCharArray(),
		// "6..195...".toCharArray(), ".98....6.".toCharArray(),
		// "8...6...3".toCharArray(), "4..8.3..1".toCharArray(),
		// "7...2...6".toCharArray(),
		// ".6....28.".toCharArray(), "...419..5".toCharArray(),
		// "....8..79".toCharArray() };
		// hardWater.solveSudoku(board);
		//hardWater.countBits(262144);
		TreeNode a=new TreeNode(3);
		TreeNode b=new TreeNode(4);
		TreeNode c=new TreeNode(5);
		TreeNode d=new TreeNode(1);
		TreeNode e=new TreeNode(3);
		TreeNode f=new TreeNode(1);
		a.left=b;
		a.right=c;
		b.left=d;
		c.left=e;
		c.right=f;
		System.out.println(hardWater.rob(a));
	}
}
