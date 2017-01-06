package leetcode;

import java.util.ArrayList;
import java.util.List;

public class NQueens {
	public List<List<String>> solveNQueens(int n) {
		int[] array = new int[n];
		List<List<String>> res = new ArrayList<List<String>>();
		for (int i = 0; i < n; i++) {
			array[i] = 0;
		}
		// setQueue(0, array, list, res);
		return res;
	}

	public void setQueue(int n, int[] array, List<Integer> list, int[] res) {
		if (n == array.length) {
			res[0]++;
			return;
		}
		for (int i = 0; i < array.length; i++) {
			if (array[i] == 0 && judge(i, list) == true) {
				list.add(i);
				array[i] = 1;
				setQueue(n + 1, array, list, res);
				array[i] = 0;
				list.remove(list.size() - 1);
			}
		}
	}

	public int totalNQueens(int n) {
		int[] result = { 0 };
		int[] array = new int[n];
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			array[i] = 0;
		}
		setQueue(0, array, list, result);
		return result[0];
	}

	public boolean judge(int k, List<Integer> list) {
		int n = list.size();
		for (int i = 0; i < n; i++) {
			if (n - i == k - list.get(i) || n - i + k - list.get(i) == 0) {
				return false;
			}
		}
		return true;
	}

	public int maxSubArray(int[] nums) {
		int current = 0, max = 0;
		if (nums.length > 0) {
			max = nums[0];
			current = max;
		}
		for (int i = 1; i < nums.length; i++) {
			if (current > 0) {
				current += nums[i];
			} else {
				current = nums[i];
			}
			if (current > max) {
				max = current;
			}
		}
		return max;
	}

	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> res = new ArrayList<Integer>();
		if (matrix.length == 0) {
			return res;
		}
		int rows = matrix.length, cloumns = matrix[0].length, i = 0, j = -1, k = 0, t;
		int count = rows * cloumns, left = 0, down = 0, right = 1, row = rows - 1, cloumn = cloumns - 1;
		while (k < count) {
			t = 0;
			if (right == 1) {
				while (t <= cloumn) {
					res.add(matrix[i][++j]);
					k++;
					t++;
				}
				right = 0;
				down = 1;
			} else if (down == 1) {
				while (t < row) {
					res.add(matrix[++i][j]);
					k++;
					t++;
				}
				down = 0;
				left = 1;
			} else if (left == 1) {
				while (t < cloumn) {
					res.add(matrix[i][--j]);
					k++;
					t++;
				}
				left = 0;
			} else {
				while (t < row - 1) {
					res.add(matrix[--i][j]);
					k++;
					t++;
				}
				right = 1;
				cloumn = cloumn - 2;
				row = row - 2;
			}
		}
		return res;
	}

	public boolean canJump(int[] nums) {
		if (nums.length <= 1) {
			return true;
		}
		if (nums.length > 1) {
			nums[nums.length - 1] = 1;
		}
		int i,max=nums.length-1;
		for (i = nums.length - 2; i >= 0; i--) {
			if(i+nums[i]<max){
				nums[i]=0;
			}
			else{
				nums[i]=1;
				max=i;
			}
		}
		if (nums[0] == 1) {
			return true;
		}
		return false;
	}
	public static void main(String[] args) {
		NQueens queens=new NQueens();
		
		int []array={3,2,1,0,4};
		System.out.println(queens.canJump(array));
	}
}
