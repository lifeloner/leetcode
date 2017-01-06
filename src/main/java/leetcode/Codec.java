package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class Codec {
	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		StringBuilder res = new StringBuilder();
		TreeNode temp;
		while (!queue.isEmpty()) {
			temp = queue.poll();
				if (temp != null) {
					res.append(temp.val+" ");
					queue.add(temp.left);
					queue.add(temp.right);
				}
				else{
					res.append("a ");
				}
		}
		return res.toString().trim();
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		 String[] str = data.split(" ");
         if(str[0].equals("a")){
			return null;
		}
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		TreeNode root = new TreeNode(Integer.parseInt(str[0]));
		queue.add(root);
		TreeNode temp, current;
		int k = 1;
		while (!queue.isEmpty()) {
			current = queue.poll();
			if (!str[k].equals("a")) {
				temp = new TreeNode(Integer.parseInt(str[k]));
				current.left = temp;
				queue.add(temp);
			} else {
				current.left = null;
			}
			k++;
			if (!str[k].equals("a")) {
				temp = new TreeNode(Integer.parseInt(str[k]));
				current.right = temp;
				queue.add(temp);
			} else {
				current.right = null;
			}
			k++;
		}
		return root;
	}

	public static void main(String[] args) {
		TreeNode a = new TreeNode(1);
//		TreeNode b = new TreeNode(2);
//		TreeNode c = new TreeNode(3);
//		TreeNode d = new TreeNode(4);
//		TreeNode e = new TreeNode(5);
//		a.left = b;
//		a.right = c;
//		c.left = d;
//		c.right = e;
		Codec codec = new Codec();
		 System.out.println(codec.serialize(null));
		a = codec.deserialize(codec.serialize(null));
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(a);
		while (!queue.isEmpty()) {
			TreeNode temp = queue.poll();
			if (temp != null) {
				System.out.println(temp.val);
				queue.add(temp.left);
				queue.add(temp.right);
			} else {
				System.out.println("null");
			}
		}
	}
}
