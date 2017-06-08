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
                for (int i = 0; i < list.size() - 1; i++) {
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

    public static int nextGreaterElement(int n) {
        int k = n % 10, m = 0;
        n = n / 10;
        if (n == 0) {
            return -1;
        }
        List<Integer> list = new ArrayList<>();
        list.add(k);
        while (n != 0 && (m = n % 10) >= k) {
            list.add(m);
            k = m;
            n = n / 10;
        }
        if (n == 0) {
            return -1;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) > m) {
                n = n / 10 * 10 + list.get(i);
                list.set(i, m);
                break;
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (n > 214748364 || n == 214748364 && list.get(i) > 7) {
                return -1;
            }
            n = n * 10 + list.get(i);
        }
        return n;
    }

    public static String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        StringBuilder stringBuilder = new StringBuilder(s.length());
        List<Character> list = new ArrayList<>();
        char c;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (c == ' ') {
                for (int j = list.size() - 1; j >= 0; j--) {
                    stringBuilder.append(list.get(j));
                }
                stringBuilder.append(c);
                list.clear();
            } else {
                list.add(c);
            }
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            stringBuilder.append(list.get(i));
        }
        return stringBuilder.toString();
    }

    public static int singleNonDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        for (int i = 1; i < nums.length; i++) {
            nums[0] ^= nums[i];
        }
        return nums[0];
    }

    public static int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0, count = 0, m;
        for (int num : nums) {
            sum += num;
            m = map.getOrDefault(sum - k, 0);
            if (sum == k) {
                count++;
            }
            count += m;
            m = map.getOrDefault(sum, 0);
            map.put(sum, ++m);
        }
        return count;
    }

    public static int arrayPairSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i += 2) {
            sum += nums[i];
        }
        return sum;
    }

    public static int findTilt(TreeNode root) {
        int[] num = new int[]{0};
        postOrder(root, num);
        return num[0];
    }

    public static int postOrder(TreeNode node, int[] num) {
        if (node == null) {
            return 0;
        }
        int left = postOrder(node.left, num), right = postOrder(node.right, num);
        num[0] += Math.abs(left - right);
        return left + right + node.val;
    }

    public static int[][] matrixReshape(int[][] nums, int r, int c) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        int row = nums.length, column = nums[0].length;
        if (row * column < r * c) {
            return nums;
        }
        int[][] result = new int[r][c];
        int a = 0, b = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (b == column) {
                    b = 0;
                    a++;
                }
                result[i][j] = nums[a][b++];
            }
        }
        return result;
    }

    public static boolean check(int[] num) {
        for (int i = 0; i < num.length; i++) {
            if (num[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s2 == null || s2.length() < s1.length()) {
            return false;
        }
        int[] count = new int[26];
        for (int i = 0; i < 26; i++) {
            count[i] = 0;
        }
        for (int i = 0; i < s1.length(); i++) {
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
        }
        if (check(count)) {
            return true;
        }
        int len = s1.length();
        for (int i = len; i < s2.length(); i++) {
            count[s2.charAt(i - len) - 'a']++;
            count[s2.charAt(i) - 'a']--;
            if (check(count)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSameTree(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        if (s.val != t.val) {
            return false;
        }
        return isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }

    public static boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        if (s.val == t.val && isSameTree(s, t)) {
            return true;
        }
        return isSubtree(s.left,t)||isSubtree(s.right,t);
    }

    public static int distributeCandies(int[] candies) {
        if(candies==null||candies.length==0){
            return 0;
        }
        Set<Integer>set=new HashSet<>();
        for(int candy:candies) {
            set.add(candy);
        }
        return Math.min(set.size(),candies.length/2);
    }

    public static int findUnsortedSubarray(int[] nums) {
        if(nums==null||nums.length==0){
            return 0;
        }
        int end=0,begin=nums.length-1,max=nums[end],min=nums[begin],len=nums.length-1;
        for(int i=0;i<nums.length;i++){
            if(max<=nums[i]){
                max=nums[i];
            }else{
                end=i;
            }
            if(min>=nums[len-i]){
                min=nums[len-i];
            }else {
                begin=len-i;
            }
        }
        if(begin>=end){
            return 0;
        }
        return end-begin+1;

    }

    public static String[] findRestaurant(String[] list1, String[] list2) {
        List<String>list=new ArrayList<>();
        String[]result;
        if(list1==null||list2==null||list1.length==0||list2.length==0){
            result=new String[0];
            return list.toArray(result);
        }
        Map<String,Integer>mapIndex=new HashMap<>();
        for(int i=0;i<list1.length;i++){
            mapIndex.put(list1[i],i);
        }
        int sum=Integer.MAX_VALUE,index;
        for(int i=0;i<list2.length;i++){
           index=mapIndex.getOrDefault(list2[i],-1);
            if(index>=0){
                if(index+i==sum){
                    list.add(list2[i]);
                }
                else if(index+i<sum){
                    list.clear();
                    list.add(list2[i]);
                    sum=index+i;
                }
            }
        }
        result=new String[list.size()];
        return list.toArray(result);
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
//        System.out.println(reverseWords("Let's take LeetCode contest   aaa"));
//        System.out.println(nextGreaterElement(123));
//        System.out.println(singleNonDuplicate(new int[]{1,1,2}));
//        System.out.println(subarraySum(new int[]{-1, -1, -1}, -1));
//        System.out.println(findUnsortedSubarray(new int[]{6,5,4}));
        System.out.println(Arrays.toString(findRestaurant(new String[]{"Shogun", "Tapioca Express", "Burger King", "KFC"},new String[]{"Tapioca Express", "Shogun", "Burger King"})));
    }


    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
