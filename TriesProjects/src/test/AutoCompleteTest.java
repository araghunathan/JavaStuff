/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import autocompletion.AutoCompleteDictionaryTrie;

/**
 * @author Jhome
 *
 */
public class AutoCompleteTest {

	AutoCompleteDictionaryTrie emptyTrie;
	AutoCompleteDictionaryTrie trie;		

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		emptyTrie = new AutoCompleteDictionaryTrie();
		trie = new AutoCompleteDictionaryTrie();		
		String[] smallSetWords = {"what","who","why","when","where","how","hat"};
		for(String s : smallSetWords){
			trie.addWord(s);
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSize() {
		assertEquals("Testing empty dictionary size", emptyTrie.size(),0);		
		assertEquals("Testing small dictionary size",trie.size(), 7);
	}
	
	@Test
	public void testIsWord(){
		assertEquals("Testing isWord on small dictionary: when",trie.isWord("when"),true);
		assertEquals("Testing isWord on small dictionary: When",trie.isWord("When"),true);
		assertEquals("Testing isWord on small dictionary: which",trie.isWord("which"),false);
	}
	
	@Test
	public void testPredictCompletions(){
		List<String> completions = trie.predictCompletions("", 0);
		assertEquals(0, completions.size());
		
		completions = trie.predictCompletions("", 3);
		assertEquals(3, completions.size());
		
		completions = trie.predictCompletions("wh",9);
		assertNotEquals(9,completions.size());
		assertEquals(5,completions.size());
		
		completions = trie.predictCompletions("a", 4);
		assertEquals(0,completions.size());
	}
}
