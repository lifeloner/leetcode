package start;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by yang on 2017/9/20.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        double[] datas = new double[n];
        for (int i = 0; i < n; i++) {
            datas[i] = Double.parseDouble(reader.readLine());
        }
        double b;
        int result = 0, t, m;
        for (int i = 0; i < n; i++) {
            b = datas[i] + 180.00000000;
            t = binarySearch(datas, b, i + 1);
            if (t < n) {
                m = t - i;
                result += m * (m - 1) / 2;
                t++;
                if (t < n && Double.compare(datas[t], b) == 0) {
                    t++;
                }
                if (t < n) {
                    m = n - t;
                    result += (m - 1) * m / 2;
                    if(i>0){
                        result+=i*m;
                    }
                }
            }
        }
        System.out.println(result);
    }

    public static int binarySearch(double[] data, double num, int begin) {
        if (begin == data.length) {
            return begin;
        }
        int end = data.length - 1, mid, k;
        while (begin <= end) {
            mid = begin + (end - begin) / 2;
            k = Double.compare(data[mid], num);
            if (k == 0) {
                return mid - 1;
            } else if (k > 0) {
                end = mid - 1;
            } else {
                begin = mid + 1;
            }
        }
        return end;
    }
}
