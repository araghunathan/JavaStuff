package dictionary;

import test.DictionaryTest;
import test.SimilarWordsTest;

public class DictionaryRunner {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		DictionaryTest test = new DictionaryTest();
		test.setUp();
		test.testSize();
		test.testIsWord();
		test.tearDown();
		
		SimilarWordsTest similarTest = new SimilarWordsTest();
		similarTest.testDistanceOne();
	}

}
