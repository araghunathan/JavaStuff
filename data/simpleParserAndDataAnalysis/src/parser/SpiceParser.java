/**
 * 
 */
package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import mappings.Adjectives;
import mappings.SpicesInfo;


/**
 * @author Jhome
 *
 */
public class SpiceParser {
	
	private static SpiceParser sInstance = null;

	private HashMap<String,ArrayList<SpicesInfo>> userInfo;
	
	public static SpiceParser getInstance(){
	    if(sInstance == null){
	    	sInstance = new SpiceParser(new HashMap<String,ArrayList<SpicesInfo>>());
	    }
	    return sInstance;
	}
	
	public SpiceParser(HashMap<String,ArrayList<SpicesInfo>> map){
		this.userInfo = map;
	}
	
	public void parseProperties(String[] values){
		if(values == null || values.length < 3){
			System.err.println("Not enough arguments to parse " + values.length);
			throw new IllegalArgumentException();
		}
		String currentUser = values[0];
		if(!userInfo.containsKey(currentUser)){
			userInfo.put(currentUser, new ArrayList<SpicesInfo>());
		}
		values = values[1].split(" ");
		Adjectives adjectives = new Adjectives();
		int currentPosition = parseAndPrint(adjectives,"Spices",values,0,Adjectives.SPICES);
		currentPosition = parseAndPrint(adjectives,"Adjective",values,currentPosition,Adjectives.ADJECTIVES);
		System.out.println("Country:"+Adjectives.COUNTRY_VALUES.get(values[values.length-1]));
	}
	
	private int parseAndPrint(Adjectives adjectives,String what, String[] values, int startPosition, String[] whichProperties){
		int curr = startPosition;
		for(int i = startPosition; i < values.length; i+= curr){
			boolean foundElement = adjectives.searchElement(whichProperties,values[i], 0, whichProperties.length);
			curr = 1;
			if(foundElement){
				printValue(what,values[i]);
			}
			else
			{
				if(i < values.length-1){
					String combinedValue = values[i] + " " + values[i+1];
					foundElement = adjectives.searchElement(whichProperties,combinedValue, 0, whichProperties.length);
					if(foundElement){
						printValue(what,combinedValue);
					}
					curr = 2;
				}
			}
			if(!foundElement){
				curr = i;
				break;
			}
		}
		return curr;
	}
	
	private void printValue(String what, String currValue)
	{
		if(what.equals("Spices")){
			System.out.println(currValue + ":true");
		}
		else
		{
			System.out.println(what + ":" + currValue);
		}
	}
	
	public HashMap<String,ArrayList<SpicesInfo>> getUserInfo(){
		return userInfo;
	}
}
