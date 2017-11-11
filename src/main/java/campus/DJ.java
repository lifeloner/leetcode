package campus;

import java.util.*;

/**
 * Created by yang on 2017/9/13.
 */
public class DJ {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), a, b, c;
        int[] childs;
        Map<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            a = scanner.nextInt();
            b = scanner.nextInt();
            c = scanner.nextInt();
            childs = map.get(b);
            if (childs == null) {
                childs = new int[2];
                childs[0] = a;
                map.put(b,childs);
            } else {
                childs[1] = a;
            }
        }
        n = scanner.nextInt();
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(n);
        n=1;
        while (!queue.isEmpty()) {
            a=queue.poll();
            childs=map.get(a);
            if(childs!=null){
                n++;
                queue.add(childs[0]);
                if(childs[1]!=0){
                    n++;
                    queue.add(childs[1]);
                }
            }
        }
        System.out.println(n);
    }
}
