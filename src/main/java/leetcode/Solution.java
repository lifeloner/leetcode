package leetcode;

import java.util.*;

/**
 * Created by yang on 2016/9/10.
 */
public class Solution {
    private int []nums;
    private   Random random;
    private ListNode head;
    private int len;
    public Solution(int[] nums) {
       this.nums=nums;
        random=new Random();
    }
    public Solution(ListNode head) {
        this.head=head;
        random=new Random();
        len=0;
        while(head!=null){
            len++;
            head=head.next;
        }
    }

    /** Returns a random node's value. */
    public int getRandom() {
        int k=random.nextInt(len);
        ListNode listNode=head;
        for(int i=0;i<k;i++){
            listNode=listNode.next;
        }
        return listNode.val;
    }
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return nums;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int []array=Arrays.copyOf(nums,nums.length);
        int k,temp;
        for(int i=array.length;i>1;i--){
            k=random.nextInt(i);
            temp=array[i-1];
            array[i-1]=array[k];
            array[k]=temp;
        }
        return array;
    }

    public int pick(int target) {
        List<Integer>list=new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            if(nums[i]==target){
                list.add(i);
            }
        }
        if(list.size()>0){
            return list.get(random.nextInt(list.size()));
        }
        return -1;
    }

    public int minPath(int n){
        if(n==0){
            return 0;
        }
        Queue<Integer>queue=new LinkedList<>();
        queue.add(0);
        queue.add(null);
        int step=1;
        Integer num;
        while(!queue.isEmpty()){
            num=queue.poll();
            if(num==null){
                queue.add(null);
                step++;
                continue;
            }
            if(num==n){
                return step-1;
            }
            queue.add(num+step);
            queue.add(num-step);
        }
        return -1;
    }


    public static void main(String[] args) {
//        ListNode head = new ListNode(1);
//        head.next = new ListNode(2);
//        head.next.next = new ListNode(3);
        Solution solution = new Solution(new int[]{1,2,3,3,3});
       // System.out.println(solution.pick(3));
        for(int i=0;i<1000;i++) {
            System.out.println(i+"\t"+solution.minPath(i));
        }
    }
}
