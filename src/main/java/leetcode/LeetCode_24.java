package leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_24 {
	public ListNode swapPairs(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode prenode = null, current = head, nextnode = null, result = head.next;
		while (current != null && current.next != null) {
			nextnode = current.next;
			current.next = nextnode.next;
			nextnode.next = current;
			if (prenode != null) {
				prenode.next = nextnode;
			}
			prenode = current;
			current = current.next;
		}
		return result;
	}

	public int removeDuplicates(int[] nums) {
		int i = 1, j, k, res = nums.length;
		while (i < res) {
			j = i;
			while (i < res && nums[i] == nums[i - 1]) {
				i++;
			}
			if (i > j) {
				k = j;
				while (i < res) {
					nums[j++] = nums[i++];
				}
				res = j;
				i = k;
			}
			i++;
		}
		return res;
	}

	public int removeElement(int[] nums, int val) {
		int i, j = 0;
		for (i = 0; i < nums.length; i++) {
			if (nums[i] != val) {
				nums[j++] = nums[i];
			}
		}
		return j;
	}

	private List<String> result = new ArrayList<String>();

	public String countAndSay(int n) {
		if(result.size()==0){
			result.add("1");
		}
		if (n < result.size()) {
			return result.get(n - 1);
		}
		int i, j, k;
		String temp, str;
		for (i = result.size() - 1; i < n; i++) {
			temp = result.get(i);
			str = "";
			for (j = 1, k = 1; j < temp.length(); j++) {
				if (temp.charAt(j) == temp.charAt(j - 1)) {
					k++;
				} else {
					str = str + k + temp.charAt(j - 1);
					k = 1;
				}
			}
			str = str + k + temp.charAt(j - 1);
			result.add(str);
		}
		return result.get(n - 1);
	}

	public static void main(String[] args) {
		LeetCode_24 lt = new LeetCode_24();
		// int n;
		// ListNode head = null, first = null;
		// Scanner scanner = new Scanner(System.in);
		// n = scanner.nextInt();
		// scanner.close();
		// int i = 1;
		// while (i <= n) {
		// ListNode node = new ListNode(i);
		// if (head == null) {
		// first = head = node;
		// } else {
		// head.next = node;
		// head = head.next;
		// }
		// i++;
		// }
		// first = lt.swapPairs(first);
		// while (first != null) {
		// System.out.println(first.val);
		// first = first.next;
		// }

		// int[] nums = new int[] { 1, 1, 2, 2, 2, 3, 3, 6, 7, 8, 2 };
		// System.out.println(lt.removeDuplicates(nums));
		for (int i = 1; i < 10; i++) {
			System.out.println(lt.countAndSay(i));
		}
	}
}
