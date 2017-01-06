package com.study.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode_4Sum {

	public List<List<Integer>> fourSum(int[] nums, int target) {
		Arrays.sort(nums);
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		for (int i = 0; i < nums.length - 3; i++) {
			if (4 * nums[i] > target) {
				break;
			}
			if (i >= 1 && nums[i] == nums[i - 1]) {
				continue;
			}
			List<List<Integer>> temp = threeSum(nums, i + 1, target - nums[i]);
			for (int j = 0; j < temp.size(); j++) {
				List<Integer> temps = temp.get(j);
				temps.add(0, nums[i]);
				res.add(temps);
			}
		}
		return res;
	}

	public List<List<Integer>> threeSum(int[] nums, int k, int target) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		for (int i = k; i < nums.length - 2; i++) {
			if (3 * nums[i] > target) {
				break;
			}
			if (i > k && nums[i] == nums[i - 1]) {
				continue;
			}
			List<List<Integer>> temp = twoSum(nums, i + 1, target - nums[i]);
			for (int j = 0; j < temp.size(); j++) {
				List<Integer> temps = temp.get(j);
				temps.add(0, nums[i]);
				res.add(temps);
			}
		}
		return res;
	}

	public List<List<Integer>> twoSum(int[] nums, int k, int target) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		int left = k, right = nums.length - 1, m, n;
		while (left < right && 2 * nums[left] <= target && 2 * nums[right] >= target) {
			m = nums[left];
			n = nums[right];
			if (m + n == target) {
				if (left > k && m == nums[left - 1]) {
					left++;
				} else {
					List<Integer> temp = new ArrayList<Integer>();
					temp.add(m);
					temp.add(n);
					res.add(temp);
					left++;
				}
			} else if (m + n > target) {
				right--;
			} else {
				left++;
			}
		}
		return res;
	}

	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		Arrays.sort(candidates);
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> res = new ArrayList<Integer>();
		for (int i = 0; i < candidates.length; i++) {
			if (i == 0 || candidates[i] != candidates[i - 1]) {
				res.add(candidates[i]);
			}
		}
		List<Integer>temp=new ArrayList<Integer>();
		calculateSum(result, temp, res, 0, target);
		for(int i=0;i<result.size();i++){
			System.out.println(result.get(i).toString());
		}
		return result;
	}

	public void calculateSum(List<List<Integer>> results,List<Integer> list, List<Integer>array, int n, int target) {
       for(int i=n;i<array.size();i++){
    	   if(array.get(i)>target){
    		   break;
    	   }
    	   else  if(array.get(i)==target){
    		   list.add(array.get(i));
    		   List<Integer>temp=new ArrayList<Integer>();
    		   temp.addAll(list);
    		   results.add(temp);
    		   list.remove(list.size()-1);
    		   break;
    	   }
    	   else{
    		      list.add(array.get(i));
    		      calculateSum(results, list, array, i, target-array.get(i));
    		      list.remove(list.size()-1);
    	   }
       }
	}

	public static void main(String[] args) {
		LeetCode_4Sum sum = new LeetCode_4Sum();
		int[] nums = {2,3,6,7};
		// int []nums={0,0,1,9};
		// List<List<Integer>> res = sum.fourSum(nums, 10);
		// for (int i = 0; i < res.size(); i++) {
		// System.out.print(res.get(i).get(0));
		// System.out.print(res.get(i).get(1));
		// System.out.print(res.get(i).get(2));
		// System.out.println(res.get(i).get(3));

		// }
		List<List<Integer>> result =new ArrayList<List<Integer>>();
		result=sum.combinationSum(nums, 7);
		System.out.println(result.size());
		
		
	}
}
