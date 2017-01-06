package leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Created by fuyang on 16/9/14.
 */
public class NestedIterator implements Iterator<Integer> {

    private Stack<NestedInteger> stack;

    private Stack<Integer> index;

    private List<NestedInteger> list;

    private int current;

    public static class NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.

        private String number;

        public NestedInteger(String number) {
            this.number = number;
        }

        public boolean isInteger() {
            for (int i = 0; i < number.length(); i++) {
                if (!Character.isDigit(number.charAt(i))) {
                    return false;
                }
            }
            return true;
        }

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger() {
            return Integer.parseInt(number);
        }

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList() {
            List<NestedInteger> result = new ArrayList<>();
            String[] strings = number.split(",");
            for (int i = 0; i < strings.length; i++) {
                result.add(new NestedInteger(strings[i]));
            }
            return result;
        }
    }

    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        index = new Stack<>();
        list = nestedList;
        current = 0;
    }

    @Override
    public boolean hasNext() {
        NestedInteger nestedInteger = null;
        if (!stack.empty()) {
            nestedInteger = stack.pop();
            int pos = index.pop();
            while (!nestedInteger.isInteger()) {
                List<NestedInteger> lists = nestedInteger.getList();
                if (pos < lists.size()) {
                    NestedInteger integers = lists.get(pos);
                    if (++pos < lists.size()) {
                        stack.push(nestedInteger);
                        index.push(pos);
                    }
                    stack.push(integers);
                    index.push(0);
                }
                if (stack.empty()) {
                    break;
                }
                nestedInteger = stack.pop();
                pos = index.pop();
            }
            if (nestedInteger.isInteger()) {
                stack.push(nestedInteger);
                index.push(pos);
                return true;
            }
        }
        if(current<list.size()) {
            stack.push(list.get(current++));
            index.push(0);
            return hasNext();
        }
        return false;
    }

    @Override
    public Integer next() {
        index.pop();
        return stack.pop().getInteger();
    }

    public static void main(String[] args) {
        List<NestedInteger> list = new ArrayList<>();
        NestedInteger one = new NestedIterator.NestedInteger("1");
        NestedInteger two = new NestedIterator.NestedInteger("2,10,11");
        NestedInteger three = new NestedIterator.NestedInteger("3,4,5");
        list.add(one);
        list.add(three);
        list.add(two);
        NestedIterator nestedIterator = new NestedIterator(list);
        while (nestedIterator.hasNext()) {
            System.out.println(nestedIterator.next());
        }
    }
}