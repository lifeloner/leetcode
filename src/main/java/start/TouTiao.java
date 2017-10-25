package start;

import java.util.*;

/**
 * Created by yang on 2017/9/10.
 */
public class TouTiao {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        int k = scanner.nextInt();
        List<List<Integer>> index = new ArrayList<>(26);
        for (int i = 0; i < 26; i++) {
            index.add(new ArrayList<>());
        }
        for (int i = 0; i < s.length(); i++) {
            index.get(s.charAt(i) - 'a').add(i);
        }
        List<Integer> list;
        int result = 0, t, left, right, count, a, b, c, d;
        for (int i = 0; i < 26; i++) {
            list = index.get(i);
            if (list.size() > 0) {
                for (int j = 0; j < list.size(); j++) {
                    t = list.get(j);
                    left = j - 1;
                    right = j + 1;
                    count = k;
                    a = 0;
                    b = 0;
                    while (count > 0) {
                        c = Integer.MAX_VALUE;
                        d = Integer.MAX_VALUE;
                        if (left >= 0) {
                            c = t - list.get(left) - 1 - a;
                        }
                        if (right < list.size()) {
                            d = list.get(right) - t - 1 - b;
                        }
                        if (c == Integer.MAX_VALUE && d == Integer.MAX_VALUE) {
                            break;
                        }
                        if (c < d) {
                            if(c<=count) {
                                a++;
                                left--;
                                count-=c;
                            }else {
                                break;
                            }
                        } else {
                            if (d <= count) {
                                b++;
                                right++;
                                count-=d;
                            } else {
                                break;
                            }
                        }
                    }
                    if (right - left - 1 > result) {
                        result = right - left - 1;
                    }
                }
            }
        }
        System.out.println(result);
//        int n=scanner.nextInt();
//        Map<Integer,List<Integer>>map=new HashMap<>();
//        int k,a,b,c;
//        for(int i=0;i<n;i++){
//            k=scanner.nextInt();
//            if(map.containsKey(k)){
//                map.get(k).add(i+1);
//            }else {
//                List<Integer>index=new ArrayList<>();
//                index.add(i+1);
//                map.put(k,index);
//            }
//        }
//        k=scanner.nextInt();
//        List<Integer>result=new ArrayList<>(k),temp;
//        for(int i=0;i<k;i++){
//            a=scanner.nextInt();
//            b=scanner.nextInt();
//            c=scanner.nextInt();
//            temp=map.get(c);
//            if(temp==null){
//                result.add(0);
//                continue;
//            }
//            a=minBinarySearch(temp,a);
//            b=maxBinarySearch(temp,b);
//            if(b-a+1>0){
//                result.add(b-a+1);
//            }else {
//                result.add(0);
//            }
//        }
//        System.out.println(result);
    }

    public static int minBinarySearch(List<Integer> list, int k) {
        int left = 0, right = list.size() - 1, mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (list.get(mid) == k) {
                return mid;
            } else if (list.get(mid) < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static int maxBinarySearch(List<Integer> list, int k) {
        int left = 0, right = list.size() - 1, mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (list.get(mid) == k) {
                return mid;
            } else if (list.get(mid) < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }
}
