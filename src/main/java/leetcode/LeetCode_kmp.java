package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode_kmp {
	int strStr(String haystack, String needle) {
		int[] next = new int[needle.length()];
		if (needle.length() == 0) {
			return 0;
		}
		getNext(needle, next);
		for (int i = 0, j = 0; i < haystack.length(); i++) {
			while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
				j = next[j - 1];
			}
			if (haystack.charAt(i) == needle.charAt(j)) {
				j++;
			}
			if (j == needle.length()) {
				return i - j + 1;
			}
		}
		return -1;
	}

	void getNext(String s, int[] next) {
		int i = 1, j = 0;
		next[0] = 0;
		while (i < s.length()) {
			while (j > 0 && s.charAt(i) != s.charAt(j)) {
				j = next[j - 1];
			}
			if (s.charAt(i) == s.charAt(j)) {
				j++;
			}
			next[i] = j;
			i++;
		}
	}

	public void nextPermutation(int[] nums) {
		int i = nums.length - 2, j, temp;
		while (i >= 0 && nums[i] >= nums[i + 1]) {
			i--;
		}
		if (i < 0) {
			i = 0;
			j = nums.length - 1;
			while (i < j) {
				temp = nums[i];
				nums[i] = nums[j];
				nums[j] = temp;
				i++;
				j--;
			}
			return;
		}
		j = nums.length - 1;
		while (nums[j] <= nums[i]) {
			j--;
		}
		temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
		j = nums.length - 1;
		i += 1;
		while (i < j) {
			temp = nums[i];
			nums[i] = nums[j];
			nums[j] = temp;
			i++;
			j--;
		}
	}

	public int[] searchRange(int[] nums, int target) {
		int left = 0, right = nums.length - 1, mid = 0;
		while (left <= right) {
			mid = (left + right) / 2;
			if (nums[mid] == target) {
				break;
			} else if (nums[mid] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		if (left > right) {
			return new int[] { -1, -1 };
		}
		left = mid;
		while (left >= 0 && nums[left] == target) {
			left--;
		}
		right = mid;
		while (right < nums.length && nums[right] == target) {
			right++;
		}
		return new int[] { left + 1, right - 1 };
	}

	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		Arrays.sort(candidates);
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> temp = new ArrayList<Integer>();
		calculateSum(result, temp, candidates, 0, target);
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).toString());
		}
		return result;
	}

	public void calculateSum(List<List<Integer>> results, List<Integer> list, int[] array, int n, int target) {
		for (int i = n; i < array.length; i++) {
			if (i > n && array[i] == array[i - 1]) {
				continue;
			}
			if (array[i] > target) {
				break;
			} else if (array[i] == target) {
				list.add(array[i]);
				List<Integer> temp = new ArrayList<Integer>();
				temp.addAll(list);
				results.add(temp);
				list.remove(list.size() - 1);
				break;
			} else {
				list.add(array[i]);
				calculateSum(results, list, array, i + 1, target - array[i]);
				list.remove(list.size() - 1);
			}
		}
	}

	public String multiply(String num1, String num2) {
		String result="";
		List<Integer>list=new ArrayList<Integer>();
		for(int i=0;i<num1.length();i++){
			for(int j=0;j<num2.length();j++){
				if(i+j>=list.size()){
					list.add((num1.charAt(i)-'0')*(num2.charAt(j)-'0'));
				}
				else{
					list.set(i+j,list.get(i+j)+(num1.charAt(i)-'0')*(num2.charAt(j)-'0'));
				}
			}
		}
		int k=0;
		for(int i=list.size()-1;i>=0;i--){
			k=list.get(i)+k;
			result=k%10+result;
			k=k/10;
		}
		while(k>0){
			result=k%10+result;
			k=k/10;
		}
		while(k<result.length()-1&&result.charAt(k)=='0'){
			k++;
		}
		return result.substring(k,result.length());
	}

	public static void main(String[] args) {
		LeetCode_kmp kmp = new LeetCode_kmp();

		// String a = "", b = "";
		// Scanner scanner = new Scanner(System.in);
		// a = scanner.next();
		// b = scanner.next();
		// System.out.println(kmp.strStr(a, b));
		// scanner.close();
		// int[] nums = new int[] { 3, 2, 1, 0 };
		// kmp.nextPermutation(nums);
		// int i = 0;
		// while (i < nums.length) {
		// System.out.println(nums[i]);
		// i++;
		// }
		//int[] nums = new int[] { 10, 2, 2, 7, 6, 1, 5, 4 };
		System.out.println(kmp.multiply("123","0"));
	}
}
