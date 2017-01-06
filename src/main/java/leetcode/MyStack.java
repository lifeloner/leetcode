package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MyStack {
	// Push element x onto stack.
	private Queue<Integer> queue = new LinkedList<Integer>();

	public void push(int x) {
		List<Integer> list = new ArrayList<Integer>();
		while (!queue.isEmpty()) {
			list.add(queue.poll());
		}
		queue.offer(x);
		for (int i = 0; i < list.size(); i++) {
			queue.offer(list.get(i));
		}
	}

	// Removes the element on top of the stack.
	public void pop() {
		queue.poll();
	}

	// Get the top element.
	public int top() {
		return queue.peek();
	}

	// Return whether the stack is empty.
	public boolean empty() {
		return queue.isEmpty();
	}
}
