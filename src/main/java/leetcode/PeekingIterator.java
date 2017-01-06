package leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PeekingIterator implements Iterator<Integer> {
	private int current;
	private List<Integer> list;

	public PeekingIterator(Iterator<Integer> iterator) {
		// initialize any member here.
		current = 0;
		list = new ArrayList<Integer>();
		while (iterator.hasNext()) {
			list.add(iterator.next());
		}
	}

	public Integer peek() {
		if (current < list.size()) {
			return list.get(current-1);
		}
		return null;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		if (current < list.size()) {
			return true;
		}
		return false;
	}

	@Override
	public Integer next() {
		// TODO Auto-generated method stub
		if (current < list.size()) {
			current++;
			return list.get(current-1);
		}
		return null;
	}

	public static void main(String[] args) {

	}
}
