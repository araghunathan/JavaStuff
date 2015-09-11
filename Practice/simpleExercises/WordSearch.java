package simpleExercises;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WordSearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//https://oj.leetcode.com/problems/word-search/
		String board[][] = {{"ABCE"},
							{"SFCS"}, 
							{"ADEE"},
							{"GTHM"}};
		
		HashMap<Character, List<Point>> locations = new HashMap<Character,List<Point>>();
		for(int i = 0; i < board.length; i++){
			for (int j = 0; j < board[i][0].length(); j++){
				Character thisChar = board[i][0].charAt(j);
			
				if(!locations.containsKey(thisChar)){
					List<Point> point = new ArrayList<Point>();
					point.add(new Point(i,j));
					locations.put(thisChar, point);
				}else{
					List<Point> currPoint = locations.get(thisChar);
					currPoint.add(new Point(i,j));
					locations.put(thisChar, currPoint);
				}
/*				System.out.print("Current key: " + thisChar);
				System.out.println("Current key locations: " + locations.get(thisChar));
*/		
			}
//			System.out.println("");
		}
		
		//all letters of the word exist
		String testWord = "ABCCE";
		String testWord2 = "DAS";
		String testWord3 = "ABDE";

		for(int i = 0; i < testWord2.length(); i++){
			if(!locations.containsKey(testWord2.charAt(i))){
				System.out.println("The word " + testWord2 + " is not possible ");
			}
		}

		//is horizontally or vertically adjacent
		//index range:: >-1 && < board.length
		System.out.println("The first character is " + testWord2.charAt(0) + " and its locations are: " + locations.get(testWord2.charAt(0)));
		for(int i = 0; i < testWord2.length(); i++){
//			System.out.println("The current character is " + testWord.charAt(i) + " and its locations are: " + locations.get(testWord.charAt(i)));
		}
		

	}
	
}
