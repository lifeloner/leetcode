package com.study.leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_22 {
	public List<String> generateParenthesis(int n) {
		List<String> result = new ArrayList<String>();
		String str="";
		formResult(result, str, 0, 0, n);
		return result;
	}
	public void formResult(List<String>strs,String s,int left,int right,int n){
		if(right==n){
			strs.add(s);
		}
		else if(left==n){
			s+=')';
			formResult(strs, s, left, right+1, n);
		}
		else if(right==left){
			s+='(';
			formResult(strs, s, left+1, right, n);
		}
		else{
			formResult(strs, s+'(', left+1, right, n);
			formResult(strs, s+')', left, right+1, n);
		}
	}
	public static void main(String[] args) {
		int n=8;
		LeetCode_22 lt=new LeetCode_22();
		lt.generateParenthesis(n);
 	}
}
