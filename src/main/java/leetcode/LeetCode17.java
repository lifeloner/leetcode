package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode17 {
	public static List<String> letterCombinations(String digits) {
		List<String> res = new ArrayList<>();
		int len = digits.length(), sum = pow(digits), t;
		String temp;
		for (int i = 0; i < sum; i++) {
			temp = "";
			t = i;
			for (int j = len - 1; j >= 0; j--) {
				if (digits.charAt(j) == '7' || digits.charAt(j) == '9') {
					temp = threeOfOne(digits.charAt(j), t % 4) + temp;
					t = t / 4;
				} else {

					temp = threeOfOne(digits.charAt(j), t % 3) + temp;
					t = t / 3;
				}
			}
			res.add(temp);
			System.out.println(temp);
		}
		return res;
	}

	public static int pow(String str) {
		if (str.length() == 0) {
			return 0;
		}
		int sum = 1;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '7' || str.charAt(i) == '9') {
				sum *= 4;
			} else {
				sum *= 3;
			}
		}
		return sum;
	}

	public static char threeOfOne(char c, int x) {
		if (c == '7') {
			if (x == 0) {
				return 'p';
			} else if (x == 1) {
				return 'q';
			} else if (x == 2) {
				return 'r';
			} else {
				return 's';
			}
		} else if (c == '9') {
			if (x == 0) {
				return 'w';
			} else if (x == 1) {
				return 'x';
			} else if (x == 2) {
				return 'y';
			} else {
				return 'z';
			}
		} else if (c == '2') {
			if (x == 0) {
				return 'a';
			} else if (x == 1) {
				return 'b';
			} else {
				return 'c';
			}
		} else if (c == '3') {
			if (x == 0) {
				return 'd';
			} else if (x == 1) {
				return 'e';
			} else {
				return 'f';
			}
		} else if (c == '4') {
			if (x == 0) {
				return 'g';
			} else if (x == 1) {
				return 'h';
			} else {
				return 'i';
			}
		} else if (c == '5') {
			if (x == 0) {
				return 'j';
			} else if (x == 1) {
				return 'k';
			} else {
				return 'l';
			}
		} else if (c == '6') {
			if (x == 0) {
				return 'm';
			} else if (x == 1) {
				return 'n';
			} else {
				return 'o';
			}
		} else {
			if (x == 0) {
				return 't';
			} else if (x == 1) {
				return 'u';
			} else {
				return 'v';
			}
		}
	}

	public static List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		int[] temp = new int[nums.length];
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < nums.length; i++) {
			temp[i] = 0;
		}
		Arrays.sort(nums);
		visitArray2(res, list, temp, nums, 1);
		return res;
	}

	public static void visitArray(List<List<Integer>> result, List<Integer> res, int[] symbol, int[] nums, int n) {
		if (n > nums.length) {
			List<Integer> temp = new ArrayList<Integer>();
			temp.addAll(res);
			result.add(temp);
		} else {
			for (int i = 0; i < symbol.length; i++) {
				if (symbol[i] == 0) {
					res.add(nums[i]);
					symbol[i] = 1;
					visitArray(result, res, symbol, nums, n + 1);
					symbol[i] = 0;
					res.remove(res.size() - 1);
				}
			}
		}
	}

	public static void visitArray2(List<List<Integer>> result, List<Integer> res, int[] symbol, int[] nums, int n) {
		if (n > nums.length) {
			List<Integer> temp = new ArrayList<Integer>();
			temp.addAll(res);
			result.add(temp);
		} else {
			int k = -1, t = 0;
			for (int i = 0; i < symbol.length; i++) {
				if (symbol[i] == 0 && (t == 0 || nums[i] != k)) {
					k = nums[i];
					t = 1;
					res.add(nums[i]);
					symbol[i] = 1;
					visitArray2(result, res, symbol, nums, n + 1);
					symbol[i] = 0;
					res.remove(res.size() - 1);
				}
			}
		}
	}

	public static void rotate(int[][] matrix) {
		// int i,i1,j,j1,k,temp;
		// for( i=0;i<matrix.length;i++){
		// for( j=i;j<matrix[i].length-i-1;j++){
		// i1=j;
		// j1=matrix.length-1-i;
		// while(i1!=i||j1!=j){
		// temp=matrix[i][j];
		// matrix[i][j]=matrix[i1][j1];
		// matrix[i1][j1]=temp;
		// k=i1;
		// i1=j1;
		// j1=matrix.length-1-k;
		// }
		// }
		// }
		int i, j;
		for (i = 0; i < matrix[0].length; i++) {
			for (j = 0; j < matrix.length; j++) {
				System.out.print(matrix[matrix.length - 1 - j][i] + " ");
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		// String a = new String("sb");
		// String b = a;
		// b = b + "a";
		// System.out.println(a + "--" + b);
		int[][] nums = { { 1, 2, 3, 4, 5 }, { 6, 7, 8, 9, 10 }, { 11, 12, 13, 14, 15 } };
		LeetCode17.rotate(nums);
	}
}
