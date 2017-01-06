package leetcode;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
	private int capacity;
	private Map<Integer, Node> map;
	private Node head;
	private Node tail;

	public LRUCache(int capacity) {
		this.capacity = capacity;
		map = new HashMap<Integer, Node>();
		head = null;
		tail = null;
	}

	public int get(int key) {
		if (map.containsKey(key)) {
			Node node = map.get(key);
			if (node != head) {
				node.pre.next = node.next;
				if (node.next != null) {
					node.next.pre = node.pre;
				}else{
					tail=node.pre;
				}
				node.next = head;
				head.pre = node;
				node.pre = null;
				head = node;
			}
			return head.val;
		}
		return -1;
	}

	public void set(int key, int value) {
		if (map.containsKey(key)) {
			Node node = map.get(key);
			if (node != head) {
				node.pre.next = node.next;
				if (node.next != null) {
					node.next.pre = node.pre;
				}else{
					tail=node.pre;
				}
				node.next = head;
				head.pre = node;
				node.pre = null;
				head = node;
			}
			node.val = value;
		} else {
			if (map.size() >= capacity) {
				map.remove(tail.key);
				if (tail.pre != null) {
					tail.pre.next = tail.next;
					Node temp = tail.pre;
					tail.pre = null;
					tail = temp;
				} else {
					head = null;
					tail = null;
				}
			}
			Node node = new Node(key, value, null, head);
			if (head == null) {
				head = node;
				tail = node;
			} else {
				head.pre = node;
				head = node;
			}
			map.put(key, node);
		}
	}

	public static void main(String[] args) {
		LRUCache lruCache = new LRUCache(2);
		lruCache.set(2, 1);
		lruCache.set(1, 1);
		System.out.println(lruCache.get(2));
		System.out.println();
		Node node = lruCache.head;
		while (node != null) {
			System.out.println(node.key);
			node = node.next;
		}
		lruCache.set(4, 1);
		System.out.println();
		 node = lruCache.head;
		while (node != null) {
			System.out.println(node.key);
			node = node.next;
		}
		System.out.println();
		System.out.println(lruCache.get(1));
		System.out.println();
		 node = lruCache.head;
		while (node != null) {
			System.out.println(node.key);
			node = node.next;
		}
		System.out.println(lruCache.get(2));
		
	}
}

class Node {
	int key;
	int val;
	Node pre;
	Node next;

	public Node(int key, int val, Node pre, Node next) {
		super();
		this.key = key;
		this.val = val;
		this.pre = pre;
		this.next = next;
	}

}