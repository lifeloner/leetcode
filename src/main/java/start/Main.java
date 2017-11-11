package start;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by yang on 2017/9/20.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int m,n;
        String line=scanner.nextLine();
        String[]data=line.split(" ");
        m=Integer.parseInt(data[0]);
        n=Integer.parseInt(data[1]);
        List<List<Integer>>list=new ArrayList<>();
        boolean[]flag=new boolean[m+1];
        for(int i=0;i<m;i++){
            line=scanner.nextLine();
            data=line.split(" ");
            List<Integer>temp=new ArrayList<>(data.length);
            for(int j=0;j<data.length;j++){
                temp.add(Integer.parseInt(data[j]));
            }
            list.add(temp);
        }
        List<Integer>result=new ArrayList<>();
        result.add(n);
        flag[n]=true;
        m=dfs(n,result,list,flag);
        if(m==0){
            System.out.println("R");
        }
        else if(m==-1){
            for(int i=result.size()-1;i>0;i--){
                System.out.print(result.get(i)+" ");
            }
            System.out.println(result.get(0));
        }
        else {
            System.out.println("E");
        }
    }

    public static int dfs(int n, List<Integer>result, List<List<Integer>>list,boolean[]flag){
        int m,t;
        for(int i=0;i<list.get(n-1).size();i++){
            m=list.get(n-1).get(i);
            if(m==-1){
                return -1;
            }
            if(m==0){
                return 0;
            }
            result.add(m);
            if(flag[m]){
                return 1;
            }
            flag[m]=true;
            t=dfs(m,result,list,flag);
            if(t!=0){
                return t;
            }
            flag[m]=false;
        }
        return 0;
    }

    public static void mainss(String[] args) {
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
