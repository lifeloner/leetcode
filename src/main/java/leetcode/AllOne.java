package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by yang on 2016/11/5.
 */
public class AllOne {
    private Node head;
    private Node tail;
    private Map<String, Node> map;

    /**
     * Initialize your data structure here.
     */

    public AllOne() {
        head = null;
        tail = null;
        map = new HashMap<>();
    }

    /**
     * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
     */
    public void inc(String key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            Node next = node.next;
            if (next != null && next.value == node.value + 1) {
                next.count++;
            } else {
                next = new Node(node.value + 1, 1);
                if (node.next == null) {
                    tail = next;
                } else {
                    node.next.before = next;
                    next.next = node.next;
                }
                next.before = node;
                node.next = next;
            }
            next.set.add(key);
            map.put(key, next);
            node.set.remove(key);
            node.count--;
            if (node.count == 0) {
                if (node.before == null) {
                    head = next;
                } else {
                    node.before.next = next;
                }
                next.before=node.before;
            }
        } else {
            if (head != null && head.value == 1) {
                head.set.add(key);
                head.count++;
            } else {
                Node node = new Node(1, 1);
                node.set.add(key);
                if (head == null) {
                    tail = node;
                } else {
                    node.next = head;
                    head.before = node;
                }
                head = node;
            }
            map.put(key, head);
        }
    }

    /**
     * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
     */
    public void dec(String key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            Node before = node.before;
            if (node.value > 1) {
                if (before != null && before.value == node.value - 1) {
                    before.set.add(key);
                    before.count++;
                    map.put(key, before);
                } else {
                    Node nodes = new Node(node.value - 1, 1);
                    if (node.before == null) {
                        head = nodes;
                    } else {
                        node.before.next = nodes;
                        nodes.before = node.before;
                    }
                    nodes.set.add(key);
                    nodes.next = node;
                    node.before = nodes;
                    map.put(key, nodes);
                }
            } else {
                map.remove(key);
            }
            node.count--;
            node.set.remove(key);
            if (node.count == 0) {
                Node next = node.next;
                before=node.before;
                if (before == null) {
                    head = next;
                } else {
                    before.next = next;
                }
                if (next == null) {
                    tail = before;
                } else {
                    next.before = before;
                }
                node = null;
            }
        }
    }

    /**
     * Returns one of the keys with maximal value.
     */
    public String getMaxKey() {
        if (tail != null) {
            return tail.set.iterator().next();
        }
        return "";
    }

    /**
     * Returns one of the keys with Minimal value.
     */
    public String getMinKey() {
        if (head != null) {
            return head.set.iterator().next();
        }
        return "";
    }

    public static void main(String[] args) {
        AllOne one = new AllOne();
        one.inc("hello");
        one.inc("world");
        one.inc("world");
        one.dec("hello");
        Node node=one.tail;
        while (node != null) {
            System.out.println(node.set.size() + " " + node.value + " " + node.count);
            node = node.before;
        }
//        System.out.println(one.getMaxKey());
//        System.out.println(one.getMinKey());
    }

    class Node {
        private int value;
        private int count;
        private Node next;
        private Node before;
        private Set<String> set;

        public Node(int value, int count) {
            this.value = value;
            this.count = count;
            set = new HashSet<>();
        }
    }
}

