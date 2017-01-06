package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MoreWater {
	public void sortColors(int[] nums) {
		int left = 0, current = 0, right = nums.length - 1;
		while (current <= right) {
			while (right >= current && nums[right] == 2) {
				right--;
			}
			if (current > right) {
				break;
			}
			if (nums[current] == 0) {
				if (current != left) {
					nums[current] = nums[left];
					nums[left] = 0;
				}
				current++;
				left++;
			} else if (nums[current] == 2) {
				nums[current] = nums[right];
				nums[right] = 2;
				right--;
			} else {
				current++;
			}
		}
	}

	public int search(int[] nums, int target) {
		int left = 0, right = 0, mid;
		int[] temp = new int[nums.length];
		temp[0] = 0;
		for (left = 1; left < nums.length; left++) {
			if (nums[left] != nums[right]) {
				nums[++right] = nums[left];
				temp[right] = left - right;
			}
		}
		if (nums[right] == nums[0]) {
			right--;
		}
		left = 0;
		while (left <= right) {
			mid = (left + right) / 2;
			if (nums[mid] == target) {
				return mid + temp[mid];
			} else if (nums[left] < nums[right]) {
				if (nums[mid] > target) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			} else {
				if (nums[mid] > target) {
					if (nums[left] > nums[mid]) {
						right = mid - 1;
					} else {
						if (nums[left] <= target) {
							right = mid - 1;
						} else {
							left = mid + 1;
						}
					}
				} else {
					if (nums[left] <= nums[mid]) {
						left = mid + 1;
					} else {
						if (nums[left] > target) {
							left = mid + 1;
						} else {
							right = mid - 1;
						}
					}
				}
			}
		}
		return -1;
	}

	public ListNode deleteDuplicates(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode left = head, right = head.next;
		while (right != null) {
			if (right.val != left.val) {
				left.next = right;
				left = right;
			}
			right = right.next;
		}
		left.next = null;
		return head;

	}

	public ListNode reverseBetween(ListNode head, int m, int n) {
		if (m == n) {
			return head;
		}
		ListNode before = null, current, temp1, temp2;
		int i;
		for (i = 0; i < m - 1; i++) {
			if (before == null) {
				before = head;
			} else {
				before = before.next;
			}
		}
		if (before == null) {
			temp1 = head;
			current = head.next;
		} else {
			temp1 = before.next;
			current = temp1.next;
		}
		while (i < n - 1) {
			temp2 = current.next;
			current.next = temp1;
			temp1 = current;
			current = temp2;
			i++;
		}
		if (before == null) {
			head.next = current;
			head = temp1;
		} else {
			before.next.next = current;
			before.next = temp1;
		}
		return head;
	}

	public ListNode deleteDuplicates2(ListNode head) {
		if (head == null) {
			return head;
		}
		ListNode left = head, right = head.next, current = null, res = null;
		while (right != null) {
			if (right.val != left.val) {
				if (right == left.next) {
					if (current != null) {
						current.next = left;
					} else {
						res = left;
					}
					current = left;
				}
				left = right;
			}
			right = right.next;
		}
		if (left.next == null) {
			if (current == null) {
				res = left;
				current = left;
			} else {
				current.next = left;
				current = current.next;
			}
		}
		if (current != null) {
			current.next = null;
		}
		return res;
	}

	public int removeDuplicates(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}
		int count = 1, left = 0;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] != nums[left]) {
				nums[++left] = nums[i];
				count = 1;
			} else {
				count++;
				if (count < 3) {
					nums[++left] = nums[i];
				}
			}
		}
		return left + 1;
	}

	public List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		visit(list, res, 1, k, n);
		return res;
	}

	public void visit(List<Integer> list, List<List<Integer>> res, int t, int k, int n) {
		if (list.size() == k) {
			List<Integer> temp = new ArrayList<Integer>();
			temp.addAll(list);
			res.add(temp);
			System.out.println(temp);
			return;
		}
		for (int i = t; i <= n && list.size() + n - t + 1 >= k; i++) {
			list.add(i);
			visit(list, res, i + 1, k, n);
			list.remove(list.size() - 1);
		}
	}

	public ListNode partition(ListNode head, int x) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode result = null, current = null, left = head, right = head.next;
		while (right != null) {
			ListNode temp = right.next;
			if (right.val < x) {
				left.next = right.next;
				if (result == null) {
					current = right;
					current.next = head;
					result = current;
				} else {
					right.next = current.next;
					current.next = right;
					current = right;
				}
			}
			right = temp;
		}
		return result;
	}

	public List<String> restoreIpAddresses(String s) {
		List<String> res = new ArrayList<String>();
		int a = 0, b, c, d;
		for (int i = 0; i < 3 && i < s.length() - 3; i++) {
			if (a == 0 && i != 0) {
				break;
			}
			a = a * 10 + s.charAt(i) - '0';
			if (s.length() - i - 1 > 9) {
				continue;
			}
			b = 0;
			if (a > 255) {
				break;
			}
			for (int j = 0; j < 3 && i + 1 + j < s.length() - 2; j++) {
				if (b == 0 && j != 0) {
					break;
				}
				b = b * 10 + s.charAt(i + j + 1) - '0';
				if (s.length() - i - j - 2 > 6) {
					continue;
				}
				c = 0;
				if (b > 255) {
					break;
				}
				for (int k = 0; k < 3 && i + 2 + j + k < s.length() - 1; k++) {
					if (c == 0 && k != 0) {
						break;
					}
					c = c * 10 + s.charAt(i + 1 + j + 1 + k) - '0';
					if (s.length() - i - j - k - 3 > 3) {
						continue;
					}
					d = 0;
					if (c > 255) {
						break;
					}
					int m;
					for (m = i + 3 + j + k; m < s.length(); m++) {
						if (d == 0 && m != i + 3 + j + k) {
							break;
						}
						d = d * 10 + s.charAt(m) - '0';
					}
					if (m == s.length() && d <= 255) {
						String temp = String.valueOf(a) + "." + String.valueOf(b) + "." + String.valueOf(c) + "."
								+ String.valueOf(d);
						res.add(temp);
					}
				}
			}
		}
		return res;
	}

	public boolean isValidBST(TreeNode root) {
		return isValid(root, -2147483648, 2147483647, 0, 0);
	}

	public boolean isValid(TreeNode root, int min, int max, int left, int right) {
		if (root != null) {
			if (root.val == -2147483648 && min == -2147483648 && left == 0) {
				if (root.val < max) {
					return isValid(root.left, min, root.val, left, right)
							&& isValid(root.right, root.val, max, 1, right);
				} else {
					return false;
				}
			} else if (root.val == 2147483647 && max == 2147483647 && right == 0) {
				if (root.val > min) {
					return isValid(root.left, min, root.val, left, 1)
							&& isValid(root.right, root.val, max, left, right);
				} else {
					return false;
				}
			}
			if (root.val > min && root.val < max) {
				return isValid(root.left, min, root.val, left, right)
						&& isValid(root.right, root.val, max, left, right);
			} else {
				return false;
			}
		}
		return true;
	}

	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		LinkedList<TreeNode> nodes = new LinkedList<TreeNode>();
		if (root != null) {
			nodes.offer(root);
			nodes.offer(null);
			res.add(new ArrayList<Integer>());
		}
		while (nodes.size() > 1) {
			TreeNode temp = nodes.poll();
			if (temp == null) {
				nodes.offer(null);
				res.add(new ArrayList<Integer>());
			} else {
				res.get(res.size() - 1).add(temp.val);
				if (temp.left != null) {
					nodes.offer(temp.left);
				}
				if (temp.right != null) {
					nodes.offer(temp.right);
				}
			}
		}
		for(int i=0;i<=(res.size()-1)/2&&res.size()>0;i++){
			List<Integer>temp=res.get(i);
			res.set(i, res.get(res.size()-1-i));
			res.set(res.size()-1-i, temp);
		}
		return res;
	}

	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		boolean flag = true;
		LinkedList<TreeNode> nodes = new LinkedList<TreeNode>();
		if (root != null) {
			nodes.offer(root);
			nodes.offer(null);
			res.add(new ArrayList<Integer>());
		}
		while (nodes.size() > 1) {
			TreeNode temp = nodes.poll();
			if (temp == null) {
				if (!flag) {
					List<Integer> list = res.get(res.size() - 1);
					for (int i = 0; i <= (list.size() - 1) / 2; i++) {
						int k = list.get(i);
						list.set(i, list.get(list.size() - 1 - i));
						list.set(list.size() - 1 - i, k);
					}
				}
				flag = !flag;
				nodes.offer(null);
				res.add(new ArrayList<Integer>());
			} else {
				res.get(res.size() - 1).add(temp.val);
				if (temp.left != null) {
					nodes.offer(temp.left);
				}
				if (temp.right != null) {
					nodes.offer(temp.right);
				}
			}
		}
		if (!flag) {
			List<Integer> list = res.get(res.size() - 1);
			for (int i = 0; i <= (list.size() - 1) / 2; i++) {
				int k = list.get(i);
				list.set(i, list.get(list.size() - 1 - i));
				list.set(list.size() - 1 - i, k);
			}
		}
		return res;
	}

	public boolean isSymmetric(TreeNode root) {
		if (root == null) {
			return true;
		}
		return visitTree(root.left, root.right);
	}

	public boolean visitTree(TreeNode left, TreeNode right) {
		if (left == null && right == null) {
			return true;
		} else if ((left == null && right != null) || (left != null && right == null)) {
			return false;
		} else {
			if (left.val != right.val) {
				return false;
			} else {
				return visitTree(left.left, right.right) && visitTree(left.right, right.left);
			}
		}
	}

	public static void main(String[] args) {
		MoreWater water = new MoreWater();
		String a = "19216811";
		System.out.println(water.restoreIpAddresses(a));

	}
}
