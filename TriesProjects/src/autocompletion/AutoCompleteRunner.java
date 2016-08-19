package autocompletion;

import test.AutoCompleteTest;

public class AutoCompleteRunner {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		AutoCompleteTest test = new AutoCompleteTest();
		test.setUp();
		test.testSize();
		test.testIsWord();
		test.testPredictCompletions();
		test.tearDown();
	}

}
