package start;

import java.util.*;

/**
 * Created by yang on 2017/4/4.
 */
public class Medium {

    public static int findFather(int a,int []father){
        while(a!=father[a]){
            a=father[a];
        }
        return a;
    }

    public static int findCircleNums(int[][] M) {
        if(M==null||M.length==0){
            return 0;
        }
        Queue<Integer>queue=new ArrayDeque<>();
        boolean[]flag=new boolean[M.length];
        int sum=0,k;
        for(int i=0;i<M.length;i++){
            if(flag[i]){
              continue;
            }
            queue.add(i);
            flag[i]=true;
            while(!queue.isEmpty()){
                k=queue.poll();
                for(int j=0;j<M.length;j++){
                    if(j!=k&&M[k][j]==1&&!flag[j]){
                        queue.add(j);
                        flag[j]=true;
                    }
                }
            }
            sum++;
        }
        return sum;
    }

    public static int findCircleNum(int[][] M) {
        if(M==null||M.length==0){
            return 0;
        }
        int[]father=new int[M.length];
        for(int i=0;i<father.length;i++){
            father[i]=i;
        }
        int a,b;
        for(int i=0;i<M.length;i++){
            for(int j=i+1;j<M.length;j++){
                if(M[i][j]==1) {
                    a = findFather(i,father);
                    b=findFather(j,father);
                    father[a]=b;
                }
            }
        }
        boolean[]flag=new boolean[M.length];
        for(int i=0;i<M.length;i++){
            flag[findFather(i,father)]=true;
        }
        a=0;
        for(int i=0;i<flag.length;i++){
            if(flag[i]){
                a++;
            }
        }
        return a;
    }

    public static int findMinDifference(List<String> timePoints) {
        if(timePoints==null||timePoints.size()==0){
            return 0;
        }
        int[]nums=new int[timePoints.size()];
        String[]temp;
        for(int i=0;i<nums.length;i++){
            temp=timePoints.get(i).split(":");
            nums[i]=Integer.parseInt(temp[0])*60+Integer.parseInt(temp[1]);
        }
        Arrays.sort(nums);
        int num=23*60+59;
        for(int i=1;i<nums.length;i++){
            if(nums[i]-nums[i-1]<num){
                num=nums[i]-nums[i-1];
            }
        }
        if(24*60-nums[nums.length-1]+nums[0]<num){
            num=24*60-nums[nums.length-1]+nums[0];
        }
        return num;
    }

    public static void main(String[] args) {
//        System.out.println(findCircleNums(new int[][]{{1,1,0},{1,1,1},{0,1,1}}));
        System.out.println(findMinDifference(new ArrayList<String>(){{add("00:01");
        add("23:59");
        }}));
    }
}
