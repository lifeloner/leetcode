package leetcode;

public class Trie {
	private TrieNode root;
    private TrieNode end;
    
	public Trie() {
		root = new TrieNode();
		end=root;
	}

	// Inserts a word into the trie.
	public void insert(String word) {
        TrieNode node=new TrieNode(word);
        end.setNext(node);
        end=node;
	}

	// Returns if the word is in the trie.
	public boolean search(String word) {
      TrieNode current=root.getNext();
      while(current!=null){
    	  if(current.getString().equals(word)){
    		  return true;
    	  }
    	  current=current.getNext();
      }
      return false;
	}

	// Returns if there is any word in the trie
	// that starts with the given prefix.
	public boolean startsWith(String prefix) {
		TrieNode current=root.getNext();
	      while(current!=null){
	    	  if(current.getString().startsWith(prefix)){
	    		  return true;
	    	  }
	    	  current=current.getNext();
	      }
	      return false;
	}
}

class TrieNode {
	// Initialize your data structure here.
	private  String word;
	private TrieNode next;
	public TrieNode() {
        next=null;
	}
	public TrieNode(String word) {
		super();
		this.word = word;
		next=null;
	}
	public void setNext(TrieNode next){
		this.next=next;
	}
	public TrieNode getNext(){
		return next;
	}
	public String getString(){
		return word;
	}
}