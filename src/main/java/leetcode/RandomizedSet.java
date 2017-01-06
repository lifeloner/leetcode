package leetcode;

import java.util.*;

/**
 * Created by yang on 2016/9/17.
 */
public class RandomizedSet {
    private Map<Integer,List<Integer>>map;
    private List<Integer>list;
    private List<Integer>index;
    private Random random;
    public RandomizedSet() {
        map=new HashMap<>();
        list=new ArrayList<>();
        index=new ArrayList<>();
        random=new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        list.add(val);
        List<Integer>a;
        if(map.containsKey(val)){
            a=map.get(val);
            a.add(list.size()-1);
            index.add(a.size()-1);
            return false;
        }
        a=new ArrayList<>();
        a.add(list.size()-1);
        index.add(a.size()-1);
        map.put(val,a);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val)){
            return false;
        }
        List<Integer>a=map.get(val);
        if(a.size()==0){
            return false;
        }
        int pos=a.get(a.size()-1);
        int newVaule=list.get(list.size()-1);
        list.set(pos,newVaule);
        map.get(newVaule).set(index.get(index.size()-1),pos);
        index.set(pos,index.get(list.size()-1));
        a.remove(a.size()-1);
        list.remove(list.size()-1);
        index.remove(index.size()-1);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        if(list.size()==0){
            return -1;
        }
        int pos=random.nextInt(list.size());
        return list.get(pos);
    }

    public static void main(String[] args) {
        RandomizedSet randomizedSet=new RandomizedSet();
        randomizedSet.insert(3);
        randomizedSet.insert(-2);
        randomizedSet.insert(-2);
//        System.out.println(randomizedSet.list.size());
 randomizedSet.remove(-2);
 randomizedSet.remove(1);
//        System.out.println(randomizedSet.list.size());
//        randomizedSet.insert(1);
//        System.out.println(randomizedSet.list.size());
//        randomizedSet.insert(-3);
//        System.out.println(randomizedSet.list.size());
//        randomizedSet.insert(-2);
//        System.out.println(randomizedSet.list.size());
//        randomizedSet.remove(-2);
//        System.out.println(randomizedSet.list.size());
//        randomizedSet.remove(3);
//        System.out.println(randomizedSet.list.size());
//        randomizedSet.insert(-1);
//        System.out.println(randomizedSet.list.size());
//        randomizedSet.remove(-3);
//        System.out.println(randomizedSet.list.size());
//        randomizedSet.insert(1);
//        randomizedSet.insert(-2);
//        randomizedSet.insert(-2);
//        randomizedSet.insert(-2);
//        randomizedSet.insert(1);
//        randomizedSet.getRandom();
//        randomizedSet.insert(-2);
//        randomizedSet.remove(0);
//        randomizedSet.insert(-3);
//        randomizedSet.insert(1);
        System.out.println(randomizedSet.list);
        System.out.println(randomizedSet.getRandom());
    }
}
