/**
 * 
 */
package driver;

import java.util.ArrayList;
import java.util.HashMap;

import mappings.Adjectives;
import mappings.SpicesInfo;


/**
 * @author Jhome
 *
 */
public class SpiceParser2 {
	
	private static SpiceParser2 sInstance = null;

	private HashMap<String,ArrayList<SpicesInfo>> userInfo;
	private HashMap<String,ArrayList<SpicesInfo>> spicesInfo;
	
	public static SpiceParser2 getInstance(){
	    if(sInstance == null){
	    	sInstance = new SpiceParser2(new HashMap<String,ArrayList<SpicesInfo>>(),
	    								new HashMap<String,ArrayList<SpicesInfo>>());
	    }
	    return sInstance;
	}
	
	public SpiceParser2(HashMap<String,ArrayList<SpicesInfo>> map,HashMap<String,ArrayList<SpicesInfo>> map2){
		this.userInfo = map;
		this.spicesInfo = map2;
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

		SpicesInfo info = new SpicesInfo();
		info.setRating(Integer.parseInt(values[values.length-1]));
		
		values = values[1].split(" ");
		info.setSpiceAttributes(values);
		
		Adjectives adjectives = new Adjectives();
		int currentPosition = parseAndPrint(adjectives,"Spices",values,0,Adjectives.SPICES);
		currentPosition = parseAndPrint(adjectives,"Adjective",values,currentPosition,Adjectives.ADJECTIVES);

		String country = Adjectives.COUNTRY_VALUES.get(values[values.length-1]);
		info.setCountry(country);
		System.out.println("Country:"+country);
	}
	
	private int parseAndPrint(Adjectives adjectives,String what, String[] values, int startPosition, String[] whichProperties){
		int curr = startPosition;
		for(int i = startPosition; i < values.length; i+= curr){
			boolean foundElement = adjectives.searchElement(whichProperties,values[i], 0, whichProperties.length);
			curr = 1;
			if(foundElement){
				String key = what.equals("Spices") ? values[i] : what;
				
				if(!spicesInfo.containsKey(key)){
					spicesInfo.put(key, new ArrayList<SpicesInfo>());
				}				
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
		String valueToPrint = what.equals("Spices") ? currValue + ":true" : what + ":" + currValue;
		System.out.println(valueToPrint);
	}
	
	public HashMap<String,ArrayList<SpicesInfo>> getUserInfo(){
		return userInfo;
	}
}
