package start;

import java.util.Scanner;

/**
 * Created by yang on 2017/9/20.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str=scanner.nextLine();
        int n = Integer.parseInt(str);
        String[] strs = new String[n];
        for (int i = 0; i < n; i++) {
            strs[i] = scanner.nextLine().trim();
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                result += isValid(strs[i], strs[j]);
            }
        }
        scanner.close();
        System.out.println(result);
    }

    public static void mains(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m, n;
        String line = scanner.nextLine();
        String[] temp = line.split(" ");
        boolean flag = true;
        m = Integer.parseInt(temp[0]);
        n = Integer.parseInt(temp[1]);
        if (m <= 0 || n <= 0) {
            flag = false;
            System.out.println("input erro");
        } else {
            int[][] array = new int[m][n];
            for (int i = 0; i < m; i++) {
                line = scanner.nextLine();
                temp = line.split(" ");
                if (temp.length != n) {
                    flag = false;
                    continue;
                }
                try {
                    for (int j = 0; j < n; j++) {
                        array[i][j] = Integer.parseInt(temp[j]);
                        if (array[i][j] < 0) {
                            flag = false;
                            break;
                        }
                    }
                } catch (Exception e) {
                    flag = false;
                }
            }
            int t = scanner.nextInt();
            if (!flag) {
                System.out.println("input error");
                return;
            }
            int a = 0, b = n - 1;
            while (a >= 0 && a < m && b >= 0 && b < n) {
                if (array[a][b] == t) {
                    System.out.println(1);
                    return;
                } else if (array[a][b] < t) {
                    a++;
                } else {
                    b--;
                }
            }
            System.out.println(0);
        }
    }

    public static int isValid(String a, String b) {
        int t = 0;
        StringBuilder builder = new StringBuilder();
        builder.append(a);
        builder.append(b);
        String c=builder.toString();
        int m = 0, n = c.length() - 1;
        while (m <= n) {
            if (c.charAt(m) != c.charAt(n)) {
                break;
            }
            m++;
            n--;
        }
        if (m > n) {
            t++;
        }
        builder = new StringBuilder();
        builder.append(b);
        builder.append(a);
        c=builder.toString();
        m = 0;
        n = c.length() - 1;
        while (m <= n) {
            if (c.charAt(m) != c.charAt(n)) {
                break;
            }
            m++;
            n--;
        }
        if (m > n) {
            t++;
        }
        return t;
    }
}
