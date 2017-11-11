package campus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by yang on 2017/9/13.
 */
public class Tencent {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> coins = new ArrayList<>();
        int[] a = new int[n + 1];
        int[] b = new int[n + 1], temp;
        int k = 1, t;
        while (k <= n) {
            coins.add(k);
            k *= 2;
        }
        a[0] = 1;
        b[0] = 1;
        for (int i = 0; i < coins.size(); i++) {
            k=1;
            while (k <= n) {
                t = 0;
                t += a[k];
                if (k - coins.get(i) >= 0) {
                    t += a[k - coins.get(i)];
                }
                if (k - 2 * coins.get(i) >= 0) {
                    t += a[k - 2 * coins.get(i)];
                }
                b[k] = t;
                k++;
            }
            temp = a;
            a = b;
            b = temp;
        }
        System.out.println(a[n]);
    }
}
