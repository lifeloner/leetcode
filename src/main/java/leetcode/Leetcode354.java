package com.study.leetcode;

import java.util.*;

/**
 * Created by yang on 2016/9/6.
 */
public class Leetcode354 {

    public char findTheDifference(String s, String t) {
       int[]count=new int[26];
        for(int i=0;i<s.length();i++){
         count[s.charAt(i)-'a']++;
        }
        for(int i=0;i<t.length();i++){
            if(count[t.charAt(i)-'a']==0){
                return t.charAt(i);
            }
            count[t.charAt(i)-'a']--;
        }
        return ' ';
    }

    public int firstUniqChar(String s) {
         if(s==null||s.length()==0){
             return -1;
         }
         Set<Integer>set=new HashSet<>();
         Map<Character,Integer>map=new HashMap<>();
         for(int i=0;i<s.length();i++){
             if(map.containsKey(s.charAt(i))){
                 set.remove(map.get(s.charAt(i)));
             }else{
                 map.put(s.charAt(i),i);
                 set.add(i);
             }
         }
         int min=-1;
         for(int i:set){
             if(min==-1||min>i) {
                  min=i;
             }
         }
         return min;
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        if(magazine==null){
            return false;
        }
        int []count=new int[26];
        for(int i=0;i<magazine.length();i++){
            count[magazine.charAt(i)-'a']++;
        }
        for(int i=0;i<ransomNote.length();i++){
            if(count[ransomNote.charAt(i)-'a']<=0){
                return false;
            }
            count[ransomNote.charAt(i)-'a']--;
        }
        return true;
    }

    public int binarySearch(List<Integer> list, int num) {
        int left = 0, right = list.size() - 1, mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (list.get(mid) == num) {
                return mid;
            } else if (list.get(mid) < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o2[1] - o1[1];
                }
                return o1[0] - o2[0];
            }
        });
        int result;
        List<Integer> index = new ArrayList<>();
        for (int i = 0; i < envelopes.length; i++) {
            result = binarySearch(index, envelopes[i][1]);
            if (result >= index.size()) {
                index.add(envelopes[i][1]);
            } else {
                index.set(result, envelopes[i][1]);
            }
        }
        return index.size();
    }

    public int countNumbersWithUniqueDigits(int n) {
        int sum = 0, base, k;
        for (int i = 1; i <= n; i++) {
            base = 9;
            k = 9;
            while (base >= 11 - i) {
                k *= base;
                base--;
            }
            sum += k;
        }
        return sum + 1;
    }

    public boolean isPerfectSquare(int num) {
        for (int i = 1; i * i > 0 && i * i <= num; i++) {
            if (i * i == num) {
                return true;
            }
        }
        return false;
    }

    public List<Integer> largestDivisibleSubset(int[] nums) {
        if (nums == null) {
            return null;
        }
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        int[][] index = new int[nums.length][2];
        int max = 0, k = -1;
        for (int i = 0; i < index.length; i++) {
            index[i][0] = 1;
            index[i][1] = -1;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && index[i][0] < index[j][0] + 1) {
                    index[i][0] = index[j][0] + 1;
                    index[i][1] = j;
                }
            }
            if (index[i][0] > max) {
                max = index[i][0];
                k = i;
            }
        }
        while (k >= 0) {
            list.add(nums[k]);
            k = index[k][1];
        }
        return list;
    }

    public int getSum(int a, int b) {
        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }
        int c = a ^ b, d = (a & b) << 1;
        return getSum(c, d);
    }

    public int guess(int n) {
        if (n == 1702766719) {
            return 0;
        }
        if (n < 1702766719) {
            return 1;
        }
        return -1;
    }

    public int guessNumber(int n) {
        int left = 1, right = n, mid, num;
        while (left <= right) {
            mid = left / 2 + right / 2;
            if (left % 2 == 1 && right % 2 == 1) {
                ++mid;
            }
            num = guess(mid);
            if (num == 0) {
                return mid;
            } else if (num == 1) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int[] count = new int[target + 1];
        count[0] = 1;
        for (int i = 1; i <= target; i++) {
            count[i] = 0;
            for (int j = 0; j < nums.length; j++) {
                if (i - nums[j] >= 0) {
                    count[i] += count[i - nums[j]];
                } else {
                    break;
                }
            }
        }
        return count[target];
    }

    public void heapAdjust(Node[] heap, int len, int index) {
        int min;
        while (index < len) {
            min = index;
            if (2 * index + 1 < len && heap[min].value > heap[2 * index + 1].value) {
                min = 2 * index + 1;
            }
            if (2 * index + 2 < len && heap[min].value > heap[2 * index + 2].value) {
                min = 2 * index + 2;
            }
            if (min != index) {
                Node temp = heap[index];
                heap[index] = heap[min];
                heap[min] = temp;
                index = min;
            } else {
                break;
            }
        }
    }

    public void heapInsert(Node[] heap, int index) {
        int parent;
        while (index > 0) {
            parent = (index - 1) / 2;
            if (heap[parent].value > heap[index].value) {
                Node temp = heap[index];
                heap[index] = heap[parent];
                heap[parent] = temp;
                index = parent;
            } else {
                break;
            }
        }
    }

    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        Node[] heap = new Node[matrix.length * matrix.length];
        int[][] visited = new int[matrix.length][matrix.length];
        int len = 0, i, j;
        heap[len++] = new Node(matrix[0][0], 0, 0);
        visited[0][0] = 1;
        Node result = null;
        while (k > 0) {
            result = heap[0];
            k--;
            if (k == 0) {
                break;
            }
            len--;
            if (len > 0) {
                heap[0] = heap[len];
                heapAdjust(heap, len, 0);
            }
            i = result.x;
            j = result.y;
            if (i + 1 < matrix.length && visited[i + 1][j] == 0) {
                heap[len++] = new Node(matrix[i + 1][j], i + 1, j);
                visited[i + 1][j] = 1;
                heapInsert(heap, len - 1);
            }
            if (j + 1 < matrix.length && visited[i][j + 1] == 0) {
                heap[len++] = new Node(matrix[i][j + 1], i, j + 1);
                visited[i][j + 1] = 1;
                heapInsert(heap, len - 1);
            }
        }
        return result.value;
    }

    public boolean isSubsequence(String s, String t) {
        if(s==null||t==null){
            return false;
        }
        boolean [][] isSub=new boolean[2][t.length()+1];
        boolean []sub=new boolean[s.length()+1];
        for(int i=0;i<t.length()+1;i++){
             isSub[0][i]=true;
        }
        sub[0]=true;
        for(int i=1;i<s.length()+1;i++){
             sub[i]=false;
        }
        for(int i=0;i<s.length();i++){
            for(int j=0;j<t.length();j++){
                if(s.charAt(i)==t.charAt(j)){
                    if(j==0){
                        isSub[1][j+1]=sub[i];
                    }else{
                        isSub[1][j+1]=isSub[0][j];
                    }
                }else{
                    if(j==0){
                        isSub[1][j+1]=sub[i+1];
                    }else {
                        isSub[1][j + 1] = isSub[1][j];
                    }
                }
            }
            boolean []temp=isSub[0];
            isSub[0]=isSub[1];
            isSub[1]=temp;
        }
        return isSub[0][t.length()];
    }

    public String decodeString(String s) {
        if(s==null||s.length()==0){
            return s;
        }
        Stack<Integer>num=new Stack<>();
        Stack<String>string=new Stack<>();
        char c;
        int k=0;
        StringBuilder str=new StringBuilder();
        for(int i=0;i<s.length();i++){
            c=s.charAt(i);
            if(c>='0'&&c<='9'){
                k=k*10+c-'0';
            }
            else if(c=='['){
                num.push(k);
                if(str.length()!=0){
                    string.push(str.toString());
                    str=new StringBuilder();
                }
                string.push("[");
                k=0;
            }else if(c==']'){
                  String temps=new String(str);
                  str=new StringBuilder();
                  String temp;
                  while(string.size()>0){
                     temp=string.pop();
                     if(temp.equals("[")){
                         break;
                     }
                     temps=temp+temps;
                  }
                  int count=num.pop();
                  StringBuilder builder=new StringBuilder();
                  for(int j=0;j<count;j++){
                      builder.append(temps);
                  }
                  string.push(builder.toString());
            }else{
                str.append(c);
            }
        }
        if(str.length()!=0){
            string.push(str.toString());
        }
        String result=new String();
        String temp;
        while(string.size()>0){
            temp=string.pop();
            result=temp+result;
        }
        return result;
    }

    public boolean isSubsequences(String s, String t){
        if(s==null||t==null){
            return false;
        }
        int index=0,len;
        for(len=0;len<s.length()&&index<t.length();len++){
            while(index<t.length()){
                if(t.charAt(index)==s.charAt(len)){
                    index++;
                    break;
                }
                index++;
            }
        }
        if(len==s.length()){
            return true;
        }
        return false;
    }

    public int lengthLongestPath(String input) {
        if(input==null||input.length()==0){
            return 0;
        }
        String[]strs=input.split("\n");
        int []count=new int[strs.length];
        int t,max=0,k;
        for(int i=0;i<strs.length;i++){
            t=strs[i].lastIndexOf("\t");
            k=strs[i].length()-t-1;
            if(t>=0){
                k+=count[t];
            }
            if(strs[i].contains(".")){
                if(k>max){
                    max=k;
                }
            }else{
                count[t+1]=k+1;
            }
        }
        return max;
    }

    public List<Integer> lexicalOrder(int n) {
        List<Integer>result=new ArrayList<>();
        addList(result,0,n);
        return result;
    }
    public void addList(List<Integer>list,int m,int n){
        if(m<=n){
            if(m>0) {
                list.add(m);
                addList(list,m*10,n);
            }
            if(m%10==0) {
                for (int i = 1; i < 10; i++) {
                    addList(list, m + i, n);
                }
            }
        }
    }
    private class Node {
        public int value;
        public int x;
        public int y;

        public Node(int value, int x, int y) {
            this.value = value;
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        Leetcode354 leetcode354 = new Leetcode354();
//        System.out.println(leetcode354.maxEnvelopes(new int[][]{{1,15},{7,18},{7,6},{7,100},{2,200},{17,30},{17,45},{3,5},{7,8},{3,6},{3,10},{7,20},{17,3},{17,45}}));
        //   System.out.println((1+2126753390)/2);
       // System.out.println(leetcode354.kthSmallest(new int[][]{{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}}, 20));
        //System.out.println(leetcode354.firstUniqChar("mmestrsbrlktgkorlcnihdfwopqcgwsmehcvhahqlpvakkxmfuprbrehquexugctesjgaxcvhoswemaamknuddvlaoniwvopunbfxhdsgocngkqwxuakptxsogwnktnauabdsibwgivlhciwgrisehtfkuvxrahhmssxsbejamccwvhwbduekibduisfttvdmntsxuouebcbflwaqifrroinnjoirvpnuxfkcheglbmbtmntlrkijiajlijyjgudebqrbdjpdcbbaxtpkgujkatriwkbvwvqelthwitiovbbevanwpkemuovsakjlfbjuuocqfkqcktbhonendvkxfetlbaunljqnwteguxhbipksscvieustdknrifesxvuvxhectkwrbotdbooiiqeiwdtgmaackmvgafdltcmmdavtdkrdarjxfflbgcbfupuwjdqjmqbrhugdhogtuwawsqhswdwsnvahkblmolwinlqjgrdxdacgtvwrekplmobhcanmjecrvntpadcohwnibujgiaexdmiakqejmtfmgqftfvccobddtmpbrewhqvtjdatdqhpjbpqxvpecjadwloicaxubmjfcsqxkvqjapqerhahoimwtxkmrnqxgpjtdssswdxsjgepdejdwdfdjboeoiigwbnxukdtxbuluoktuurqcrkkppvpbjgjkruifjosobgssbdhupjpsluehbvnthmiqigpoqjajrojbvgulvbxojcaqjxxcehmrwvkhxemcsliktubrnijolfosnkexzjeodwmigawhegfgtmoorousbmonhomtwkiwxmkknfpsxxcevxdkpualdarkfvqvdbjqbnodtcgcsvptjvbnqkfxiiwxifhmhpvwdjhubqdgojsnovxpmpuirvgvhwjkqepxdrvoaopatsfjoivrsmumgphmmbxwxxudsvjpfnqxjbpgxnurtjvfhflvvnaqmiorqmcbdurdqfbwcnljieibdasoluepximluxadrehmihsaohkxhiukepcjoldvqxhqqvduwvucigkmneknqdbxatfeomwboewjkxmotopfjtjknmkrvopiidtjdxumlirudmtqiqbvlgqsposvmixmuishmmgmncqcdngcvcqcoggndcdihkanmcevcklsmtudjkpervgvkmmbbkuculdcxiinfrlgsleqxcnwoqdeopqhrlfkumplolpgnkwbojqplwrisufidaupkihsguujbkai"));
//        System.out.println(leetcode354.lengthLongestPath("dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"));
        System.out.println(leetcode354.lexicalOrder(20));
    }
}
