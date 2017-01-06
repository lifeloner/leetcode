package leetcode;

import java.util.Scanner;

public class LeetCode_21 {

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

		ListNode head = null, current = null;
		while (l1 != null && l2 != null) {
			if (l1.val <= l2.val) {
				ListNode temp = new ListNode(l1.val);
				if (current == null) {
					current = temp;
					head = current;
				} else {
					current.next = temp;
					current = temp;
				}
				l1 = l1.next;
			} else {
				ListNode temp = new ListNode(l2.val);
				if (current == null) {
					current = temp;
					head = current;
				} else {
					current.next = temp;
					current = temp;
				}
				l2 = l2.next;
			}
		}
		while (l1 != null) {
			ListNode temp = new ListNode(l1.val);
			if (current == null) {
				current = temp;
				head = current;
			} else {
				current.next = temp;
				current = temp;
			}
			l1 = l1.next;
		}
		while (l2 != null) {
			ListNode temp = new ListNode(l2.val);
			if (current == null) {
				current = temp;
				head = current;
			} else {
				current.next = temp;
				current = temp;
			}
			l2 = l2.next;
		}
		return head;
	}

	public static void main(String[] args) {
		LeetCode_21 lt=new LeetCode_21();
		ListNode l1 = null,a=null,b=null, l2 = null;
		int x;
		Scanner scanner = new Scanner(System.in);
		while ((x = scanner.nextInt()) != -10000) {
			ListNode tmp = new ListNode(x);
			if (l1 == null) {
                   l1=tmp;
                   a=l1;
			}
			else{
				l1.next=tmp;
				l1=tmp;
			}
		}
		while ((x = scanner.nextInt()) != -10000) {
			ListNode temp = new ListNode(x);
			if (l2 == null) {
                   l2=temp;
                   b=l2;
			}
			else{
				l2.next=temp;
				l2=temp;
			}
		}
		l1=lt.mergeTwoLists(a, b);
		while(l1!=null){
			System.out.print(l1.val);
			l1=l1.next;
		}
		scanner.close();
	}
}
