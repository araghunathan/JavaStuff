package dictionary;


import java.util.Iterator;
import java.util.LinkedList;

/**
 * Implementation of a simple dictionary using a linked list
 * Note: class and function names here are from Coursera's Data Structures Made Easy (https://www.coursera.org/learn/data-structures-optimizing-performance)
 * Otherwise, I have done the actual implementation in this class 
 * Author: Archana Raghunathan
 */
 
public class DictionaryLL implements Dictionary 
{
	private LinkedList<String> dict;

	public DictionaryLL(){
		dict = new LinkedList<String>();
	}

    /**
     * @param word 
     * @return true if word doesn't already exist in dictionary; false otherwise */
    public boolean addWord(String word) {
    	word = word.toLowerCase();
    	
    	for(int i = 0; i < dict.size(); i++){
    		if(dict.get(i).equals(word)){
    			return false;
    		}
    	}
    	dict.add(word);
        return true;
    }


    public boolean removeWord(String word){
    	word = word.toLowerCase();
    	return dict.remove(word);
    }

    public void printWords(){
    	Iterator<String> itor = dict.iterator();
    	while(itor.hasNext()){
    		System.out.println(itor.next());
    	}
    }
    /** Return how many words are in the dictionary */
    public int size()
    {
        return dict.size();
    }

    /** checks if word exists in this dictionary */
	public boolean isWord(String s) {
        return dict.contains(s.toLowerCase());
    }

    
}
