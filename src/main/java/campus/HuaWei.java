package campus;

import java.util.*;

/**
 * Created by fuyang on 2017/8/16.
 */
public class HuaWei {

    public static int findMatch(String a, String b) {
        if (a == null || b == null || a.length() != b.length()) {
            return -1;
        } else {
            int k = 0, count = 0;
            for (int i = 0; i < a.length(); i++) {
                if (a.charAt(i) < '0' || a.charAt(i) > '9') {
                    return -1;
                }
                if (b.charAt(i) < '0' || b.charAt(i) > '9') {
                    return -1;
                }
                if (a.charAt(i) == b.charAt(i)) {
                    k++;
                } else {
                    if (k > count) {
                        count = k;
                    }
                    k = 0;
                }
            }
            if (k > count) {
                count = k;
            }
            return count;
        }
    }

    public static void dfs(int[] visited, List<List<Integer>> list, int k) {
        visited[k]++;
        if (visited[k] > 1) {
            return;
        }
        List<Integer> nums = list.get(k);
        for (int num : nums) {
            dfs(visited, list, num);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<List<Integer>> list = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        int count = 0;
        while (sc.hasNext()) {
            String a = sc.nextLine();
            if (a.equals("stop")) {
                break;
            }
            a = a.trim();
            if (a.charAt(a.length() - 1) == ',') {
                a = a.substring(0, a.length() - 1);
            }
            String[] str = a.split(",");
            str[0] = str[0].substring(1);
            str[1] = str[1].trim();
            str[1] = str[1].substring(0, str[1].length() - 1);
            if (!map.containsKey(str[0])) {
                map.put(str[0], count++);
                list.add(new ArrayList<>());
            }
            if (!map.containsKey(str[1])) {
                map.put(str[1], count++);
                list.add(new ArrayList<Integer>());
            }
            int m = map.get(str[0]);
            int n = map.get(str[1]);
            list.get(m).add(n);
        }
        boolean[] flag = new boolean[map.size()];
        for (int i = 0; i < map.size(); i++) {
            int[] visited = new int[map.size()];
            dfs(visited, list, i);
            if (visited[i] > 1) {
                flag[i] = true;
            }
        }
        int i = 1;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.print("{" + entry.getKey() + ", " + flag[entry.getValue()]);
            if(i++<map.size()){
                System.out.println("},");
            }else {
                System.out.println("}");
            }
        }
    }
}
