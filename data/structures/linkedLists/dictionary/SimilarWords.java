/**
 * 
 */
package spelling;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Set;


/**
 * @author Archana Raghunathan, except for the portions stated in the disclaimers
 *
 */
public class SimilarWords { 
	Dictionary dict;

	public SimilarWords (Dictionary dict) 
	{
		this.dict = dict;
	}

	public List<String> distanceOne(String s, boolean wordsOnly )  {
		   List<String> retList = new ArrayList<String>();
		   insertions(s, retList, wordsOnly);
		   substitution(s, retList, wordsOnly);
		   deletions(s, retList, wordsOnly);
		   return retList;
	}

	
	/** 
	  *  DISCLAIMER: The following set of comments and code for substitution has already 
	  *	been pre-written by the course instructors of Coursera's Data Structures Made Easy 
	  *	(https://www.coursera.org/learn/data-structures-optimizing-performance)
	
	  * Add to the currentList Strings that are one character mutation away
	  * from the input string.  
	  * @param s The original String
	  * @param currentList is the list of words to append modified words 
	  * @param wordsOnly controls whether to return only words or any String
	  * @return
	  */
	public void substitution(String s, List<String> currentList, boolean wordsOnly) {
		// for each letter in the s and for all possible replacement characters
		for(int index = 0; index < s.length(); index++){
			for(int charCode = (int)'a'; charCode <= (int)'z'; charCode++) {
				// use StringBuffer for an easy interface to permuting the 
				// letters in the String
				StringBuffer sb = new StringBuffer(s);
				sb.setCharAt(index, (char)charCode);

				// if the item isn't in the list, isn't the original string, and
				// (if wordsOnly is true) is a real word, add to the list
				if(!currentList.contains(sb.toString()) && 
						(!wordsOnly||dict.isWord(sb.toString())) &&
						!s.equals(sb.toString())) {
					currentList.add(sb.toString());
				}
			}
		}
	}
	
	public void insertions(String s, List<String> currentList, boolean wordsOnly ) {
		for(int index = 0; index <= s.length(); index++){
			for(int charCode = (int)'a'; charCode <= (int)'z'; charCode++) {
				StringBuilder sb = new StringBuilder(s);
				sb.insert(index,(char)charCode);
				if(!currentList.contains(sb.toString()) && 
						(!wordsOnly||dict.isWord(sb.toString())) &&
						!s.equals(sb.toString())) {
					currentList.add(sb.toString());
				}
			}
		}
	}

	public void deletions(String s, List<String> currentList, boolean wordsOnly ) {
		for(int index = 0; index < s.length(); index++){
			StringBuffer sb = new StringBuffer(s);
			sb.deleteCharAt(index);
			if(!currentList.contains(sb.toString()) && 
			(!wordsOnly||dict.isWord(sb.toString())) &&
			!s.equals(sb.toString())) {
				currentList.add(sb.toString());
			}
		}
	}

	/**
	   DISCLAIMER: The following set of comments and code for suggestions has already 
 	   been pre-written by the course instructors of Coursera's Data Structures Made Easy 
 	  (https://www.coursera.org/learn/data-structures-optimizing-performance)
      
       I have implemented the actual getSuggestions logic, though. 
      */	   
	public List<String> suggestions(String word, int numSuggestions) {
		List<String> queue = new LinkedList<String>();     
		HashSet<String> visited = new HashSet<String>();   
														   
		List<String> retList = new LinkedList<String>();   
		 		
		queue.add(word);
		visited.add(word);
					
		return getSuggestions(retList,queue,visited,0,numSuggestions);
	}	

	private List<String> getSuggestions(List<String> retList, List<String> queue, HashSet<String> visited, int completionSoFar, int numCompletions)
	{
	   	if(queue.isEmpty() || completionSoFar >= numCompletions){
	   		return retList;
	   	}
	   	String currWord = queue.remove(0);
	   	List<String> suggestions = distanceOne(currWord,true);
	   	for(String s: suggestions){
	   		if(!visited.contains(s)){
	   			retList.add(s);
	   			queue.add(s);
	   			visited.add(s);
	   			completionSoFar++;
	   		}
	   	}
	   	return getSuggestions(retList,queue,visited,completionSoFar,numCompletions);
   }
}
