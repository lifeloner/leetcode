package leetcode;

import java.util.Arrays;

/**
 * Created by yang on 2016/10/14.
 */
public class Permutate {
    public static int total = 0;
    public static void swap(String[] str, int i, int j)
    {
        String temp = new String();
        temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }
    public static void arrange (String[] str, int st, int len)
    {
        if (st == len - 1)
        {
            total++;
            if(total==500){
                System.out.println(Arrays.toString(str));
            }
        }
        else
        {
            for (int i = st; i < len; i ++)
            {
                swap(str, st, i);
                arrange(str, st + 1, len);
                swap(str, st, i);
            }
        }

    }
    public static void main(String[] args) {
        String str[] = {"a","b","c","d","e","f"};
        arrange(str, 0, str.length);
    }
}
