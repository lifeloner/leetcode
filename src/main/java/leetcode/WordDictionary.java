package com.study.leetcode;

import java.util.*;

public class WordDictionary {
	private WordNode root;

	public WordDictionary() {
		root = new WordNode();
	}

	// Adds a word into the data structure.
	public void addWord(String word) {
		WordNode head = root;
		int index;
		for (int i = 0; i < word.length(); i++) {
			index = word.charAt(i) - 'a';
			if (head.nodes[index] == null) {
				head.nodes[index] = new WordNode();
				head.nodes[index].end = false;
			}
			head = head.nodes[index];
		}
		head.end = true;
	}

	// Returns if the word is in the data structure. A word could
	// contain the dot character '.' to represent any one letter.
	public boolean search(String word) {
		if (word == null) {
			return true;
		}
		return searchNode(word, 0, root, root.end);
	}

	public boolean searchNode(String word, int k, WordNode node, boolean end) {
		if (k == word.length()) {
			return end;
		}
		if (node == null) {
			return false;
		}
		if (word.charAt(k) == '.') {
			for (int j = 0; j < 26; j++) {
				if (node.nodes[j] != null && searchNode(word, k + 1, node.nodes[j], node.nodes[j].end) == true) {
					return true;
				}
			}
		} else {
			int index = word.charAt(k) - 'a';
			if (node.nodes[index] == null) {
				return false;
			}
			return searchNode(word, k + 1, node.nodes[index], node.nodes[index].end);
		}
		return false;
	}

	public String largestNumber(int[] nums) {
		if (nums.length == 0) {
			return null;
		}
		String result = "";
		String[] array = new String[nums.length];
		for (int i = 0; i < nums.length; i++) {
			array[i] = String.valueOf(nums[i]);
		}
		int k;
		for (int i = 0; i < array.length; i++) {
			k = i;
			for (int j = i + 1; j < array.length; j++) {
				if ((array[j] + array[k]).compareTo(array[k] + array[j]) > 0) {
					k = j;
				}

			}
			if (k != i) {
				String temp = array[i];
				array[i] = array[k];
				array[k] = temp;
			}
		}
		k = 0;
		while (k < array.length) {
			if (array[k].equals("0")) {
				k++;
			} else {
				break;
			}
		}
		while (k < array.length) {
			result += array[k];
			k++;
		}
		if (result.equals("")) {
			result = "0";
		}
		return result;
	}

	public int numDecodings(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		int current = 0, m = 1, n = 1;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '0') {
				if (i - 1 >= 0 && (s.charAt(i - 1) == '1' || s.charAt(i - 1) == '2')) {
					current = n;
				} else {
					current = 0;
				}
			} else {
				current = m;
				if (i - 1 >= 0 && (s.charAt(i - 1) == '1' || (s.charAt(i - 1) == '2' && s.charAt(i) < '7'))) {
					current += n;
				}
			}
			n = m;
			m = current;
			if (current == 0) {
				break;
			}
		}
		return current;
	}

	public void solve(char[][] board) {
		if (board == null || board.length == 0) {
			return;
		}
		int[][] state = new int[board.length][board[0].length];
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < state[0].length; i++) {
			if (state[0][i] == 0) {
				if (board[0][i] == 'O') {
					queue.add(0);
					queue.add(i);
					state[0][i] = 2;
				} else {
					state[0][i] = 1;
				}
			}
		}
		for (int i = 0; i < state.length; i++) {
			if (state[i][0] == 0) {
				if (board[i][0] == 'O') {
					queue.add(i);
					queue.add(0);
					state[i][0] = 2;
				} else {
					state[i][0] = 1;
				}
			}
		}
		for (int i = 0; i < state[0].length; i++) {
			if (state[state.length - 1][i] == 0) {
				if (board[state.length - 1][i] == 'O') {
					queue.add(state.length - 1);
					queue.add(i);
					state[state.length - 1][i] = 2;
				} else {
					state[state.length - 1][i] = 1;
				}
			}
		}
		for (int i = 0; i < state.length; i++) {
			if (state[i][state[0].length - 1] == 0) {
				if (board[i][state[0].length - 1] == 'O') {
					queue.add(i);
					queue.add(state[0].length - 1);
					state[i][state[0].length - 1] = 2;
				} else {
					state[i][state[0].length - 1] = 1;
				}
			}
		}
		int i, j;
		while (!queue.isEmpty()) {
			i = queue.poll();
			j = queue.poll();
			if (i - 1 >= 0 && state[i - 1][j] == 0 && board[i - 1][j] == 'O') {
				state[i - 1][j] = 2;
				queue.add(i - 1);
				queue.add(j);
			}
			if (i + 1 < board.length && state[i + 1][j] == 0 && board[i + 1][j] == 'O') {
				state[i + 1][j] = 2;
				queue.add(i + 1);
				queue.add(j);
			}
			if (j - 1 >= 0 && state[i][j - 1] == 0 && board[i][j - 1] == 'O') {
				state[i][j - 1] = 2;
				queue.add(i);
				queue.add(j - 1);
			}
			if (j + 1 < board[0].length && state[i][j + 1] == 0 && board[i][j + 1] == 'O') {
				state[i][j + 1] = 2;
				queue.add(i);
				queue.add(j + 1);
			}
		}
		for (i = 0; i < board.length; i++) {
			for (j = 0; j < board[i].length; j++) {
				if (state[i][j] != 2) {
					board[i][j] = 'X';
				}
			}
		}
	}

	public void dfs(char[][] board, int[][] state, int i, int j) {
		if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || state[i][j] > 0) {
			return;
		}
		if (board[i][j] == 'O') {
			state[i][j] = 2;
			if (i - 1 >= 0 && state[i - 1][j] == 0 && board[i - 1][j] == 'O') {
				dfs(board, state, i - 1, j);
			}
			if (i + 1 < board.length && state[i + 1][j] == 0 && board[i + 1][j] == 'O') {
				dfs(board, state, i + 1, j);
			}
			if (j - 1 >= 0 && state[i][j - 1] == 0 && board[i][j - 1] == 'O') {
				dfs(board, state, i, j - 1);
			}
			if (j + 1 < board[0].length && state[i][j + 1] == 0 && board[i][j + 1] == 'O') {
				dfs(board, state, i, j + 1);
			}
		} else {
			state[i][j] = 1;
		}
	}

	public String reverseWords(String s) {
		s = s.trim();
		String[] array = s.split(" ");
		String res = "";
		for (int i = array.length - 1; i >= 0; i--) {
			if (!array[i].equals("")) {
				res = res + array[i] + " ";
			}
		}
		return res.trim();
	}

	public int divide(int dividend, int divisor) {
		if (dividend == 0 || divisor == 0) {
			return 0;
		}
		int signal = 1;
		long a = dividend, b = divisor;
		if (a < 0) {
			signal = -signal;
			a = -a;
		}
		if (b < 0) {
			signal = -signal;
			b = -b;
		}
		long res = 0, temp = b, count = 1;
		while (a >= b) {
			temp = b;
			count = 1;
			while (a > temp) {
				temp = temp << 1;
				count = count << 1;
			}
			if (temp > a) {
				temp = temp >> 1;
				count = count >> 1;
			}
			res = res + count;
			a = a - temp;
		}
		if (signal == -1) {
			res = -res;
		}
		return res > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) res;

	}

	public String fractionToDecimal(int numerator, int denominator) {
		if (numerator == 0 || denominator == 0) {
			return "0";
		}
		int signal = 1;
		long a = numerator, b = denominator;
		if (numerator < 0) {
			a = -a;
			signal = -signal;
		}
		if (denominator < 0) {
			b = -b;
			signal = -signal;
		}
		StringBuilder builder = new StringBuilder();
		if (signal == -1) {
			builder.append("-");
		}
		long div = a / b;
		builder.append(div);
		a = a % b;
		if (a == 0) {
			return builder.toString();
		}
		builder.append('.');
		Map<Long, Integer> map = new HashMap<Long, Integer>();
		int index = 0;
		String temp = "";
		while (a != 0) {
			if (map.containsKey(a)) {
				break;
			} else {
				map.put(a, index);
				a = a * 10;
				temp = temp + a / b;
				a = a % b;
				index++;
			}
		}
		if (a == 0) {
			builder.append(temp);
			return builder.toString();
		}
		index = map.get(a);
		for (int i = 0; i < index; i++) {
			builder.append(temp.charAt(i));
		}
		builder.append("(");
		for (int i = index; i < temp.length(); i++) {
			builder.append(temp.charAt(i));
		}
		builder.append(")");
		return builder.toString();
	}

	public String numberToWords(int num) {
		String nums = String.valueOf(num);
		String res = "", temp;
		int count = 0, begin;
		int k = nums.length() - 1;
		while (k >= 0) {
			begin = k - 2 >= 0 ? k - 2 : 0;
			temp = read(Integer.parseInt(nums.substring(begin, k + 1)));
			if (!temp.equals("Zero")) {
				if (count == 0) {
					res = temp + res;
				} else if (count == 1) {
					res = temp + " Thousand " + res;
				} else if (count == 2) {
					res = temp + " Million " + res;
				} else {
					res = temp + " Billion " + res;
				}
			}
			k = begin - 1;
			count++;
		}
		if (res.equals("")) {
			return "Zero";
		}
		return res.trim();
	}

	public String read(int num) {
		if (num == 0) {
			return "Zero";
		}
		String a = read1(num % 10, 0);
		String b = read1(num / 10 % 10, 1);
		String c = read1(num / 100, 0);
		String res = "";
		if (!c.equals("Zero")) {
			res += c + " Hundred ";
		}
		if (!b.equals("Zero")) {
			if (b.equals("One")) {
				if (a.equals("Zero")) {
					res += "Ten";
				} else if (a.equals("One")) {
					res += "Eleven";
				} else if (a.equals("Two")) {
					res += "Twelve";
				} else if (a.equals("Three")) {
					res += "Thirteen";
				} else if (a.equals("Four")) {
					res += "Fourteen";
				} else if (a.equals("Five")) {
					res += "Fifteen";
				} else if (a.equals("Eight")) {
					res += "Eighteen";
				} else {
					res += (a + "teen");
				}
				return res;
			} else {
				res = res + b + " ";
			}
		}
		if (!a.equals("Zero")) {
			res += a;
		}
		return res.trim();
	}

	public String read1(int num, int type) {
		switch (num) {
		case 0:
			return "Zero";
		case 1:
			return "One";
		case 2:
			if (type == 1) {
				return "Twenty";
			}
			return "Two";
		case 3:
			if (type == 1) {
				return "Thirty";
			}
			return "Three";
		case 4:
			if (type == 1) {
				return "Forty";
			}
			return "Four";
		case 5:
			if (type == 1) {
				return "Fifty";
			}
			return "Five";
		case 6:
			if (type == 1) {
				return "Sixty";
			}
			return "Six";
		case 7:
			if (type == 1) {
				return "Seventy";
			}
			return "Seven";
		case 8:
			if (type == 1) {
				return "Eighty";
			}
			return "Eight";
		case 9:
			if (type == 1) {
				return "Ninety";
			}
			return "Nine";
		default:
			return "";
		}
	}

	public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
		if(beginWord.equals(endWord)){
			return 1;
		}
		wordList.add(endWord);
		Set<String>set=new HashSet<String>();
		int count = 1;
		String string=null;
		Queue<String> queue = new LinkedList<String>();
		queue.add(beginWord);
		queue.add(null);
		while (!queue.isEmpty()) {
			string = queue.poll();
			if(string==null){
				if(queue.isEmpty()){
					break;
				}
				queue.add(null);
				count++;
				continue;
			}
			set.add(string);
			Set<String>temp=compareString(string, wordList);
			for (String temps:temp) {
				if(!set.contains(temps)){
					if(temps.equals(endWord)){
						return count+1;
					}
					set.add(temps);
					queue.add(temps);
				}
			}
		}
		return 0;
	}

	public Set<String> compareString(String a, Set<String> wordList) {
		Set<String> set = new HashSet<String>();
		char[] array = a.toCharArray();
		for (int i = 0; i < a.length(); i++) {
			for (int j = 0; j < 26; j++) {
				char c = (char) (j + 'a');
				if (c != a.charAt(i)) {
					array[i] = c;
					String temp = new String(array);
					if (wordList.contains(temp)) {
						set.add(temp);
					}
				}
			}
			array[i]=a.charAt(i);
		}
		return set;
	}

	public static void main(String[] args) {
		WordDictionary wordDictionary = new WordDictionary();
		// wordDictionary.addWord("aba");
		// // wordDictionary.addWord("ab");
		// System.out.println(wordDictionary.search("aba"));
		// System.out.println(wordDictionary.largestNumber(new int[] { 0, 0 }));
		// wordDictionary.solve(new char[][] { { 'O', 'O', 'O', 'O' }, { 'O',
		// 'X', 'X', 'X' }, { 'X', 'X', 'O', 'X' },
		// { 'X', 'X', 'X', 'X' } });
		// System.out.println(wordDictionary.reverseWords(" a b "));
		// 0.0(102040816326530612244897959183673469387755)
		// 0.(0102040816326530612244897959183673469387755)
		// 0.0(102040816326530612244897959183673469387755)
		Set<String> set = new HashSet<String>();
		set.add("hot");
		set.add("dog");
		set.add("cog");
		set.add("pot");
		set.add("dot");
		System.out.println(wordDictionary.ladderLength("hot", "dog", set));
	}
}

class WordNode {
	public boolean end;
	public WordNode[] nodes;

	public WordNode() {
		end = true;
		nodes = new WordNode[26];
	}
}