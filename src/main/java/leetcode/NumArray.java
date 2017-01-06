package leetcode;

public class NumArray {
	private int[] sum;
	private int[] num;

	public NumArray(int[] nums) {
		sum = new int[nums.length + 1];
		num = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			update(i, nums[i]);
		}
	}

	void update(int i, int val) {
		int diff = val - num[i];
		num[i] = val;
		i++;
		while (i < sum.length) {
			sum[i] += diff;
			i += lowbit(i);
		}
	}

	public int lowbit(int n) {
		return n & (-n);
	}

	public int sumRange(int i, int j) {
		int a = 0, b = 0;
		j = j + 1;
		while (i > 0) {
			a += sum[i];
			i -= lowbit(i);
		}
		while (j > 0) {
			b += sum[j];
			j -= lowbit(j);
		}
		return b - a;
	}

	public void flatten(TreeNode root) {
          dfs(root);
	}
    public TreeNode dfs(TreeNode node){
    	if(node==null){
    		return null;
    	}
    	TreeNode left=dfs(node.left);
    	TreeNode right=dfs(node.right);
    	if(left==null&&right==null){
    		return node;
    	}
    	else if(left!=null){
    		TreeNode temp=node.right;
    		node.right=node.left;
    		node.left=null;
    		left.right=temp;
    		if(right!=null){
    			return right;
    		}
    		return left;
    	}
    	else{
    		return right;
    	}
    	
    }
    public void visit(TreeNode node){
    	if(node!=null){
    		System.out.println(node.val);
    		if(node.left!=null){
    		visit(node.left);
    		}
    		else{
    			System.out.println("left null");
    		}
    		if(node.right!=null){
    		visit(node.right);
    		}else{
    			System.out.println("right null");
    		}
    	}
    }
	public static void main(String[] args) {
		NumArray numArray = new NumArray(new int[] { 1, 3, 5 });
//		numArray.update(0, 10);
//		System.out.println(numArray.sumRange(0, 2));
		TreeNode a=new TreeNode(1);
		TreeNode b=new TreeNode(2);
		TreeNode c=new TreeNode(3);
		TreeNode d=new TreeNode(4);
		TreeNode e=new TreeNode(5);
		TreeNode f=new TreeNode(6);
		a.left=b;
		b.right=c;
		c.left=d;
		d.right=e;
		e.left=f;
		numArray.flatten(a);
		numArray.visit(a);
	}
}
