package simpleExercises;

import java.util.HashMap;

public class SimpleExercises {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello");
		String testNonRepeatString = "abcbbaaaijklmnti";
		String test2 = "dctrcx";
		String test3 = "gopalsanthanaraman";
		String test4 = "archanaaaaaaaaaraghunathan";
		String largestNonRepeatingVal = "";
		int lStart = 0, lEnd = 0;
		int currStart = 0, currEnd = 0;
		
		final String stringToUse = test4;
		for(int i = 0; i < stringToUse.length(); i++){
			if(largestNonRepeatingVal.indexOf(stringToUse.charAt(i)) == -1){
				largestNonRepeatingVal = largestNonRepeatingVal + stringToUse.charAt(i);
				currEnd = i;
				//lEnd = i;
			}else{
				if((lEnd - lStart) < (currEnd - currStart)){//{0,2}
					lEnd = currEnd;
					lStart = currStart;
					largestNonRepeatingVal = "";
				}
				currStart = i+1;
				currEnd = i+1;
//				
				System.out.println("LStart: " + lStart + ", LEnd: " + lEnd);
				System.out.println("CurrStart: " + currStart + ", CurrEnd: " + currEnd);
				System.out.println("Value of i: " + i);
			}
		}
		
		System.out.println("Longest non-repeating substring is: " + stringToUse.substring(lStart,lEnd+1));
/*		HashMap<Character,Integer> lettersMap = new HashMap<Character,Integer>();
		boolean isNotRepeating = true;
		final String stringToUse = test2;
		for(int i = 0; i < stringToUse.length() && isNotRepeating; i++){
			if(!lettersMap.containsKey(stringToUse.charAt(i))){
				lettersMap.put(stringToUse.charAt(i), 1);
			}else{
				isNotRepeating = false;
			}
		}
		
			System.out.println("The keys are: " + lettersMap.keySet());
*/		
	}

}
