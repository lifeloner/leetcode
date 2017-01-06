package leetcode;

import java.util.*;

/**
 * Created by yang on 2016/9/3.
 */
public class Leetcode342 {

    public String reverseString(String s) {
        if(s==null||s.length()==0){
            return s;
        }
        StringBuilder stringBuilder=new StringBuilder();
        for(int i=s.length()-1;i>=0;i--){
            stringBuilder.append(s.charAt(i));
        }
        return stringBuilder.toString();
    }

    public boolean isPowerOfFour(int num) {
       int nums=(int)Math.sqrt(num);
        if(num!=0&&(nums*nums==num)&&(nums&nums-1)==0){
            return true;
        }
        return false;
    }
    public int integerBreak(int n) {
       if(n<4){
           return n-1;
       }
       int res=3;
        for(int i=4;i<=n;i++){
            if(i%3==0){
                res=res/2*3;
            }else if(i%3==1) {
                res = res / 3 * 4;
            }else{
                res=res/2*3;
            }
            System.out.println(i+" "+res);
        }
        return res;
    }

    public String reverseVowels(String s) {
         if(s==null||s.length()==0){
             return s;
         }
         StringBuilder pre=new StringBuilder();
         StringBuilder after=new StringBuilder();
        int begin=0,end=s.length()-1;
        while(begin<=end){
            while(begin<=end&&Character.toLowerCase(s.charAt(begin))!='a'&&Character.toLowerCase(s.charAt(begin))!='e'&&Character.toLowerCase(s.charAt(begin))!='i'&&Character.toLowerCase(s.charAt(begin))!='o'&&Character.toLowerCase(s.charAt(begin))!='u'){
                pre.append(s.charAt(begin));
                begin++;
            }
            while(begin<=end&&Character.toLowerCase(s.charAt(end))!='a'&&Character.toLowerCase(s.charAt(end))!='e'&&Character.toLowerCase(s.charAt(end))!='i'&&Character.toLowerCase(s.charAt(end))!='o'&&Character.toLowerCase(s.charAt(end))!='u'){
                after.append(s.charAt(end));
                end--;
            }
            if(begin<end){
                  pre.append(s.charAt(end));
                  after.append(s.charAt(begin));
                begin++;
                end--;
            }else if(begin==end){
                pre.append(s.charAt(begin));
                break;
            }
            else {
                break;
            }
        }
        return pre.append(after.reverse()).toString();
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        Map<Integer,Integer>map=new HashMap<>();
       List<Integer>list=new ArrayList<>();
        int k;
         for (int m:nums1){
             k=0;
            if(map.containsKey(m)){
              k=map.get(m);
            }
            map.put(m,++k);
         }
         for (int m:nums2){
             if(map.containsKey(m)){
                 list.add(m);
                 k=map.get(m);
                 if(k==1){
                     map.remove(m);
                 }else{
                     map.put(m ,--k);
                 }
             }
         }
         int [] res=new int[list.size()];
        int index=0;
       for(int m:list){
           res[index++]=m;
           System.out.println(m);
       }
        return res;
    }
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer,Integer>map=new HashMap<>();
        for(int m:nums){
            if(map.containsKey(m)){
                map.put(m,map.get(m)+1);
            }else{
                map.put(m,1);
            }
        }
        List<Map.Entry<Integer,Integer>>list=new ArrayList<>();
        list.addAll(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue()-o1.getValue();
            }
        });
        List<Integer>res=new ArrayList<>();
        for(int i=0;i<k;i++){
            res.add(list.get(i).getKey());
        }
        return res;
    }
    public static void main(String[] args) {
        Leetcode342 leetcode=new Leetcode342();
        System.out.println(leetcode.topKFrequent(new int[]{1,1,1,2,3,2},2));
    }
}
