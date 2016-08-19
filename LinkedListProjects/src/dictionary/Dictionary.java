package dictionary;
/**
 * Dictionary interface
 */


/**
 * @author Course Instructors of Data Structures Made Easy: https://www.coursera.org/learn/data-structures-optimizing-performance
 *
 */
public interface Dictionary {
	public abstract boolean addWord(String word);

	public abstract boolean isWord(String s);
	
	public abstract int size();	
}
