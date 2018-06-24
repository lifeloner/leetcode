package work;

/**
 * Created by fuyang on 2018/6/24.
 */
public class Easy {

    public static int minSwap(int[] A, int[] B) {
        int swap = 1, notSwap = 0, tempSwap, tempNotSwap;
        for (int i = 1; i < A.length; i++) {
            tempSwap = -1;
            tempNotSwap = -1;
            if (A[i] > A[i - 1] && B[i] > B[i - 1]) {
                if (tempNotSwap == -1 || tempNotSwap > notSwap) {
                    tempNotSwap = notSwap;
                }
                if (tempSwap == -1 || tempSwap < swap + 1) {
                    tempSwap = swap + 1;
                }
            }
            if (A[i] > B[i - 1] && B[i] > A[i-1]) {
                if(tempNotSwap==-1||swap<tempNotSwap){
                    tempNotSwap=swap;
                }
                if(tempSwap==-1||notSwap+1<tempSwap){
                    tempSwap=notSwap+1;
                }
            }
            swap=tempSwap;
            notSwap=tempNotSwap;
        }
        return Math.min(swap,notSwap);
    }

    public static void main(String[] args) {
        System.out.println(minSwap(new int[]{1,2,5,5},new int[]{2,3,4,7}));
    }
}
