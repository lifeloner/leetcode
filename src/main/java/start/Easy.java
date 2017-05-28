package start;

import java.util.*;

/**
 * Created by fuyang on 2017/5/28.
 */
public class Easy {

    public static boolean checkRecord(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        int a = 0, b = 0;
        char c;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (c == 'A') {
                a++;
                b = 0;
            } else if (c == 'L') {
                b++;
            } else {
                b = 0;
            }
            if (a > 1 || b > 2) {
                return false;
            }
        }
        return true;
    }

    public static String optimalDivision(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        builder.append(nums[0]);
        if (nums.length == 2) {
            builder.append("/");
            builder.append(nums[1]);
        }
        if (nums.length > 2) {
            builder.append("/(");
            for (int i = 1; i < nums.length - 1; i++) {
                builder.append(nums[i]);
                builder.append("/");
            }
            builder.append(nums[nums.length - 1]);
            builder.append(")");
        }
        return builder.toString();
    }

    public static int leastBricks(List<List<Integer>> wall) {
        if (wall == null || wall.size() == 0) {
            return 0;
        }
        Map<Integer, Integer> count = new HashMap<>();
        int sum, k;
        for (List<Integer> list : wall) {
            if (list != null && list.size() > 0) {
                sum = 0;
                for (int i=0;i<list.size()-1;i++) {
                    sum += list.get(i);
                    k = count.getOrDefault(sum, -0);
                    k++;
                    count.put(sum, k);
                }
            }
        }
        sum = wall.size();
        k = wall.size();
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            if (k - entry.getValue() < sum) {
                sum = k - entry.getValue();
            }
        }
        return sum;
    }

    public static void main(String[] args) {
//        System.out.println(checkRecord("LALL"));
//        System.out.println(optimalDivision(new int[]{100,10,1000,10}));
//        System.out.println(leastBricks(new ArrayList<List<Integer>>() {{
//            add(Arrays.asList(1,2,2,1));
//            add(Arrays.asList(3, 1, 2));
//            add(Arrays.asList(1, 3, 2));
//            add(Arrays.asList(2, 4));
//            add(Arrays.asList(3, 1, 2));
//            add(Arrays.asList(1, 3, 1, 1));
//        }}));
    }
}
