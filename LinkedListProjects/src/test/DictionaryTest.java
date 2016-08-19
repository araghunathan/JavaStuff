/**
 * 
 */
package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dictionary.DictionaryLL;

/**
 * @author Jhome
 *
 */
public class DictionaryTest {

	DictionaryLL emptyDictionary;
	DictionaryLL simpleDictionary;
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
		emptyDictionary = new DictionaryLL();
		simpleDictionary = new DictionaryLL();
		String[] words = {"helicopter","firefighter","police","instructor","dog","sheep","lamb","engineer"};
		for(String s : words){
			simpleDictionary.addWord(s);
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
		assertEquals("Test size for empty dictionary",0,emptyDictionary.size());
		assertNotEquals("Test size for empty dictionary",1,emptyDictionary.size());

		assertNotEquals("Test size for simple dictionary",0,simpleDictionary.size());
		assertEquals("Test size for simple dictionary",8,simpleDictionary.size());
		//this word already exists so it won't be added again, so size should not change

		simpleDictionary.addWord("helicopter");
		assertEquals("Test size for simple dictionary",8,simpleDictionary.size());
		simpleDictionary.addWord("smartcar");
		assertEquals("Test size for simple dictionary",9,simpleDictionary.size());

	}

	@Test
	public void testIsWord() {
		assertEquals("Test isWord for helicopter in simple dictionary",true,simpleDictionary.isWord("helicopter"));
		assertEquals("Test isWord for machine in simple dictionary",false,simpleDictionary.isWord("machine"));
		
		simpleDictionary.removeWord("helicopter");
		simpleDictionary.printWords();
		assertEquals("Test isWord for helicopter in simple dictionary",false,simpleDictionary.isWord("helicopter"));	
	}

}
