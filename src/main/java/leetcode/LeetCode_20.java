package com.study.leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_20 {
	public boolean isValid(String s) {

		List<Character> chars = new ArrayList<Character>();
		for (int i = 0; i < s.length(); i++) {
			switch (s.charAt(i)) {
			case ')':
				if (chars.size() == 0 || chars.get(chars.size() - 1) != '(') {
					return false;
				}
				chars.remove(chars.size() - 1);
				break;
			case ']':
				if (chars.size() == 0 || chars.get(chars.size() - 1) != '[') {
					return false;
				}
				chars.remove(chars.size() - 1);
				break;
			case '}':
				if (chars.size() == 0 || chars.get(chars.size() - 1) != '{') {
					return false;
				}
				chars.remove(chars.size() - 1);
				break;
			default:
				chars.add(s.charAt(i));
				break;
			}
		}
		if (chars.isEmpty()) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		LeetCode_20 ltcode = new LeetCode_20();
//		String str;
//		Scanner scanner = new Scanner(System.in);
//		while (!(str = scanner.next()).equals("not")) {
//			System.out.println(ltcode.isValid(str));
//		}
//		scanner.close();
		List<Stu>sam=new ArrayList<Stu>();
		sam.add(ltcode.new Stu());
		Stu s=sam.get(0);
		s.k();
		System.out.println(sam.get(0).getnum());
	}
	class Stu{
		private int num;
		public Stu() {
			// TODO Auto-generated constructor stub
			num=10;
		}
		public int getnum(){
			return num;
		}
		public void k(){
			num=1;
		}
	}
}
