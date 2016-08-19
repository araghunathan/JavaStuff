package autocompletion;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that performs simple auto-completions from sample text input (e.g. spelling suggestions)
 * @author Archana Raghunathan (comments/code not written by me are pointed out in the various disclaimer sections)
 *
 */
public class AutoCompleteDictionaryTrie {

    private TrieNode root;
    private int size;
    
    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
		size = 0;
	}
	
	/** 
		//DISCLAIMER: The following set of comments was pre-written 
		//as part of the course Data Structures Made Easy (https://www.coursera.org/learn/data-structures-optimizing-performance)
		//I have implemented the actual code
	
	   Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should convert the 
	 * string to all lower case before you insert it. 
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes 
	 * into the trie, as described outlined in the videos for this week. It 
	 * should appropriately use existing nodes in the trie, only creating new 
	 * nodes when necessary. E.g. If the word "no" is already in the trie, 
	 * then adding the word "now" would add only one additional node 
	 * (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary.
	 */
	public boolean addWord(String word)
	{
		if(word.equals("")){
			return false;
		}
		word = word.toLowerCase();
		TrieNode next = root;
		for(int i = 0; i < word.length(); i++){			
			TrieNode curr = next.getChild(word.charAt(i));
			if(curr == null){
				curr = next.insert(word.charAt(i));
				if(curr != null && i == word.length()-1){
					curr.setEndsWord(true);
					size++;
					return true;
				}
			}
			else if(i == word.length()-1){
				if(!curr.endsWord()){
					size++;
					return true;
				}
			}
			
			next = curr;
		}
		return false; 
	}

	/** 
		//DISCLAIMER: The following set of comments was pre-written 
		//as part of the course Data Structures Made Easy (https://www.coursera.org/learn/data-structures-optimizing-performance)
		//I have implemented the actual code

	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    return size;
	}
	
	
	/** 
		//DISCLAIMER: The following set of comments was pre-written 
		//as part of the course Data Structures Made Easy (https://www.coursera.org/learn/data-structures-optimizing-performance)
		//I have implemented the actual code
		
	   Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	public boolean isWord(String s) 
	{
		if(s.equals("")){
			return false;
		}
		s = s.toLowerCase();
		return findWordInTrie(root, s, -1);
	}

	private boolean findWordInTrie(TrieNode node, String word, int currChar){
		if(node == null){
			return false;
		}
		if(currChar == word.length()-1){
			return node.endsWord();
		}
		currChar++;
		TrieNode curr = node.getChild(word.charAt(currChar));
		return findWordInTrie(curr,word,currChar);
	}

	private TrieNode findPrefixInTrie(TrieNode node, String word, int currChar){
		if(node == null){
			return null;
		}
		if(currChar == word.length()-1){
			return node;
		}
		currChar++;
		TrieNode curr = node.getChild(word.charAt(currChar));
		return findPrefixInTrie(curr,word,currChar);
	}

	/** 
		 //DISCLAIMER: The following set of comments was pre-written 
		//as part of the course Data Structures Made Easy (https://www.coursera.org/learn/data-structures-optimizing-performance)
		//I have implemented the actual code

     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions 
     * of the prefix string. All legal completions must be valid words in the 
     * dictionary. If the prefix itself is a valid word, it is included 
     * in the list of returned words. 
     * 
     * The list of completions must contain 
     * all of the shortest completions, but when there are ties, it may break 
     * them in any order. For example, if there the prefix string is "ste" and 
     * only the words "step", "stem", "stew", "steer" and "steep" are in the 
     * dictionary, when the user asks for 4 completions, the list must include 
     * "step", "stem" and "stew", but may include either the word 
     * "steer" or "steep".
     * 
     * If this string prefix is not in the trie, it returns an empty list.
     * 
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 List<String> completions = new LinkedList<String>();
    	 TrieNode prefixNode = findPrefixInTrie(root,prefix,-1);
    	 if(prefixNode == null){
    		 return completions;
    	 }
    	 Queue<TrieNode> linkedlist = new LinkedList<TrieNode>();
    	 linkedlist.add(prefixNode);
    	 return getCompletions(completions,linkedlist,0,numCompletions);
     }

	 //DISCLAIMER: The following set of comments was pre-written 
	 //as part of the course Data Structures Made Easy (https://www.coursera.org/learn/data-structures-optimizing-performance)
	 //I have implemented the actual code
	 
	 // This method should implement the following algorithm:
	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
	 //    empty list
	 // 2. Once the stem is found, perform a breadth first search to generate completions
	 //    using the following algorithm:
	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
	 //       of the list.
	 //    Create a list of completions to return (initially empty)
	 //    While the queue is not empty and you don't have enough completions:
	 //       remove the first Node from the queue
	 //       If it is a word, add it to the completions list
	 //       Add all of its child nodes to the back of the queue
	 // Return the list of completions    	 
    private List<String> getCompletions(List<String> completions, Queue<TrieNode> list, int completionSoFar, int numCompletions){
    	if(list.isEmpty() || completionSoFar == numCompletions){
    		return completions;
    	}
    	TrieNode head = list.remove();
    	if(head.endsWord()){
    		completions.add(head.getText());
    		completionSoFar++;
    	}
		Set<Character> nextCharacters = head.getValidNextCharacters();
		for(Character c : nextCharacters){
			list.add(head.getChild(c));
		}
    	return getCompletions(completions,list,completionSoFar,numCompletions);
    }
    
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** DISCLAIMER: The following set of comments and following function was pre-written 
	    as part of the course Data Structures Made Easy (https://www.coursera.org/learn/data-structures-optimizing-performance)
	    Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		} 		
 	}
 }