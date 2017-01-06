package leetcode;

import java.util.ArrayList;
import java.util.List;

public class TreeArray {
	private int[] num;

	public TreeArray(int[] nums) {
		num = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			num[i] = 0;
		}
		for (int i = 0; i < nums.length; i++) {
			update(i, nums[i]);
		}
	}

	public void update(int pos, int k) {
		while (pos < num.length) {
			num[pos] += k;
			pos += lowBit(pos + 1);
		}
	}

	public int get(int pos) {
		int sum = 0;
		while (pos >= 0) {
			sum += num[pos];
			pos -= lowBit(pos + 1);
		}
		return sum;
	}

	public int sumRange(int i, int j) {
		return get(j) - get(i - 1);
	}

	public int lowBit(int x) {
		return x & (-x);
	}

	public List<TreeNode> generateTrees(int n) {
		if (n == 0) {
			return new ArrayList<TreeNode>();
		}
		return generateTree(1, n);
	}

	public List<TreeNode> generateTree(int begin, int end) {
		List<TreeNode> list = new ArrayList<TreeNode>();
		if (begin > end) {
			list.add(null);
			return list;
		} else if (begin == end) {
			list.add(new TreeNode(begin));
			return list;
		}
		for (int i = begin; i <= end; i++) {
			List<TreeNode> left = generateTree(begin, i - 1);
			List<TreeNode> right = generateTree(i + 1, end);
			for (int j = 0; j < left.size(); j++) {
				for (int k = 0; k < right.size(); k++) {
					TreeNode root = new TreeNode(i);
					root.left = left.get(j);
					root.right = right.get(k);
					list.add(root);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {
		int[] nums = { -2, 0, 3, -5, 2, -1 };
		TreeArray treeArray = new TreeArray(nums);
		System.out.println(treeArray.sumRange(0, 5));
	}
}
