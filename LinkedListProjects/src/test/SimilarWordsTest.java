/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dictionary.DictionaryLL;
import dictionary.SimilarWords;

/**
 * @author Archana Raghunathan
 *
 */
public class SimilarWordsTest {

	DictionaryLL emptyDictionary;
	DictionaryLL simpleDictionary;
	SimilarWords similarWords;
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
		String[] words = {"helicopter","firefighter","police","instructor","pig","wig","dog","fog","frog","sheep","lamb","engineer"};
		for(String s : words){
			simpleDictionary.addWord(s);
		}
		similarWords = new SimilarWords(simpleDictionary);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDistanceOne() {
		List<String> values = similarWords.distanceOne("fog", true);
		assertEquals("Values contains dog:testDistanceOne on fog",true,values.contains("dog"));
		assertEquals("Values contains frog:testDistanceOne on fog",true,values.contains("dog"));
	    assertNotNull(values);
	    
	    values = similarWords.distanceOne("nothing", true);
		assertEquals("Values don't contain anything for testDistanceOne on nothing",0,values.size());
	    
	    values = similarWords.distanceOne("nothing", false);
	}


}
