package leetcode;

public class LeetCode_19 {

	public ListNode removeNthFromEnd(ListNode head, int n) {

		ListNode current = head, before;
		int t = 1, pos = 0;
		while (current != null) {
			pos++;
			current = current.next;
		}
		pos = pos - n + 1;
		before = current = head;
		while (t != pos && current != null) {
			t++;
			before = current;
			current = current.next;
		}
		if (current != head) {
			before.next = current.next;
		} else {
			head = current.next;
		}
		return head;
	}
    public static int f(int x,int y){
    	if(x==0&&y==1){
    		return 1;
    	}
    	else if(x==0&&y==2){
    		return 1;
    	}
//    	else if(x==1&&y==0){
//    		return 2;
//    	}
    	else if(x==1&&y==2){
    		return 1;
    	}
    	else{
    		return 0;
    	}
    }
    public static   int g(int x){
    	return 0;
    }
	public static void main(String[] args) {
		// LeetCode_19 x = new LeetCode_19();
		// ListNode first=new ListNode(1);
		// ListNode second=new ListNode(2);
		// ListNode third=new ListNode(3);
		// ListNode four=new ListNode(4);
		// ListNode five=new ListNode(5);
		// first.next=second;
		// second.next=third;
		// third.next=four;
		// four.next=five;
		// first=x.removeNthFromEnd(first, 5);
		// while(first!=null){
		// System.out.println(first.val);
		// first=first.next;
		// }
		// ListNode a = new ListNode(1);
		// ListNode b = new ListNode(2);
		// ListNode[] alist = new ListNode[] { a, b };
		// List<ListNode> blist = Arrays.asList(alist);
		// ListNode c = blist.get(0);
		// blist.set(0, blist.get(1));
		// blist.set(1, c);
		// // ListNode b = new ListNode(2);
		// System.out.println(blist.get(0).val + "" + blist.get(1).val);
		// System.out.println(alist[0].val + "" + alist[1].val);
//		ListNode node=new ListNode(1);
//		List<ListNode> ab = new ArrayList<ListNode>();
//		ab.add(node);
//		node.val=6;
//		System.out.println(ab.get(0).val);
//		ListNode b=new ListNode();
//		Class<?> c=null;
//		c = ListNode.class;
//		try {
//			ListNode a= (ListNode) c.newInstance();
//			System.out.println(a.val);
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		List<String>list=new ArrayList<String>();
//		String str=new String("123");
//		list.add(str);
//		str=new String("456");
//		System.out.println(list.toString());
//		System.out.println(str);
//		for(int i=0;i<=2;i++){
//			for(int j=0;j<=2;j++){
//				for(int k=0;k<=2;k++){
//					if(f(f(i,f(j,k)),f(f(i,j),f(i,k)))!=0){
//						System.out.println(i+" "+j+" "+k);
//					}
//				}
//			}
//		}
//		for(int i=0;i<=2;i++){
//			for(int j=0;j<=2;j++){
//				if(f(i,f(j, i))!=0){
//					System.out.println(i+" "+j);
//				}
//			}
//		}
//		for(int i=0;i<=2;i++){
//			for(int j=0;j<=2;j++){
//				if(f(f(i, j),f(g(j), g(i)))!=0){
//					System.out.println(i+" "+j);
//				}
//			}
//		}
	}

}

class ListNode {
	int val;
	ListNode next;
public ListNode() {
	// TODO Auto-generated constructor stub
}
	ListNode(int x) {
		val = x;
	}
}