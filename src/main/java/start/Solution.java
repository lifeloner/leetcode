
import java.util.*;

public class Solution {

    static Map<Integer, List<Integer>> func = new HashMap<Integer, List<Integer>>();
    static Stack<Integer> stack = new Stack<Integer>();
    static boolean[] vis = null;

    static int flag = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String nk = in.nextLine();
        String[] nandk = nk.split(" ");

        int n = Integer.parseInt(nandk[0]);
        int k = Integer.parseInt(nandk[1]);

        for (int i = 1; i <= n; i++) {
            func.put(i, new ArrayList<Integer>());
            String str = in.nextLine();
            String[] ss = str.split(" ");
            for (String each : ss) {
                func.get(i).add(Integer.parseInt(each));
            }
        }

        vis = new boolean[n + 1];
        for (int i = 1; i <= n; i ++) vis[i] = false;

        dfs(k);

        if (flag == -1)
            System.out.println("E");
        else if (flag == 0)
            System.out.println("R");

    }

    static void dfs(int k) {
        if (flag != 0) return;
        if (vis[k]) {
            flag = -1;
            return;
        }

        vis[k] = true;
        stack.push(k);
        List<Integer> stats = func.get(k);

        for (Integer each : stats) {

            if (each == -1) {
                while (stack.size() > 1)
                    System.out.print(stack.pop() + " ");
                System.out.println(stack.pop());

                flag = -2;
                return;
            }
            else if (each == 0) {
                break;
            } else {
                dfs(each);
                if (flag != 0)
                    return;
            }

        }

        vis[k] = false;
        stack.pop();
    }


}