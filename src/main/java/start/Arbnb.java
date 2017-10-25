package start;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by yang on 2017/9/20.
 */
public class Arbnb {

    public static int[] meet(String[] wizards) {
        if (wizards == null || wizards.length == 0) {
            return new int[]{0};
        }
        int[][] distance = new int[10][10];
        String[] strs;
        int k, t, m;
        for (int i = 0; i < wizards.length; i++) {
            strs = wizards[i].split(" ");
            for (int j = 0; j < strs.length; j++) {
                k = Integer.parseInt(strs[j]);
                distance[i][k] = (i - k) * (i - k);
            }
        }
        boolean[] flag = new boolean[10];
        int[] path = new int[10];
        m = 0;
        while (true) {
            k = 0;
            t = Integer.MAX_VALUE;
            for (int i = 1; i < 10; i++) {
                if (!flag[i]) {
                    if (distance[m][i] > 0 && (distance[0][i] == 0 || distance[0][i] > distance[0][m] + distance[m][i])) {
                        distance[0][i] = distance[0][m] + distance[m][i];
                        path[i] = m;
                    }
                    if (distance[0][i] != 0 && distance[0][i] < t) {
                        t=distance[0][i];
                        k = i;
                    }
                }
            }
            if (k == 0) {
                return new int[]{0};
            } else if (k == 9) {
                break;
            } else {
                flag[k] = true;
                m = k;
            }
        }
        List<Integer> list = new ArrayList<>();
        list.add(9);
        while (k != 0) {
            k = path[k];
            list.add(k);
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(result.length - 1 - i);
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] wizards = new String[10];
        int i = 0;
        while (scanner.hasNext()) {
            wizards[i++] = scanner.nextLine();
        }
        System.out.println(Arrays.toString(meet(wizards)));
    }
}
