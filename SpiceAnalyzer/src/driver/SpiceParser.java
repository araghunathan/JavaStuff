/**
 * 
 */
package driver;

import java.util.ArrayList;
import java.util.Arrays;
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
	private HashMap<String,ArrayList<SpicesInfo>> spicesInfo;
	private HashMap<String,ArrayList<SpicesInfo>> countryInfo;
	
	public static SpiceParser getInstance(){
	    if(sInstance == null){
	    	sInstance = new SpiceParser(new HashMap<String,ArrayList<SpicesInfo>>(),
	    								new HashMap<String,ArrayList<SpicesInfo>>(),
	    								new HashMap<String,ArrayList<SpicesInfo>>());
	    }
	    return sInstance;
	}
	
	public SpiceParser(HashMap<String,ArrayList<SpicesInfo>> map,
					   HashMap<String,ArrayList<SpicesInfo>> map2,
					   HashMap<String,ArrayList<SpicesInfo>> map3){
		this.userInfo = map;
		this.spicesInfo = map2;
		this.countryInfo = map3;
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
		
		String country = Adjectives.COUNTRY_VALUES.get(values[values.length-1]);
		info.setCountry(country);

		if(!countryInfo.containsKey(country)){
			countryInfo.put(country, new ArrayList<SpicesInfo>());
		}
		
		Adjectives adjectives = new Adjectives();
		String[][] searchOptions = {Adjectives.SPICES,Adjectives.ADJECTIVES};
		int curr = 1;
		for(int i = 0; i < values.length-1; i+= curr){
			boolean foundElement = false;
			for(String[] searchOption : searchOptions){
				foundElement = adjectives.searchElement(searchOption,values[i], 0, searchOption.length);
				curr = 1;
				if(foundElement){
					info = insertAndPrintValues(searchOption,values[i],info);
				}
				else
				{
					String combinedValue = values[i] + " " + values[i+1];
					foundElement = adjectives.searchElement(searchOption,combinedValue, 0, searchOption.length);
					if(foundElement){
						info = insertAndPrintValues(searchOption,combinedValue,info);
					}
						curr = 2;
				}
				if(foundElement){
					break;
				}
			}
		}
		
		ArrayList<SpicesInfo> infoValues = userInfo.get(currentUser);
		infoValues.add(info);
		userInfo.put(currentUser,infoValues);
		
		infoValues = countryInfo.get(country);
		infoValues.add(info);
		countryInfo.put(country,infoValues);
		System.out.println("Country:"+country);
	}
		
	private SpicesInfo insertAndPrintValues(String[] searchOption, String currValue,SpicesInfo info){
		boolean equalsSpices = Arrays.equals(searchOption, Adjectives.SPICES);
		String valueToPrint = equalsSpices ? currValue + ":true" : "Adjective:" + currValue;
		if(!equalsSpices){
			info.setAdjective(currValue);						
		}
		if(!spicesInfo.containsKey(currValue)){
			spicesInfo.put(currValue, new ArrayList<SpicesInfo>());
		}
		ArrayList<SpicesInfo> infoValues = spicesInfo.get(currValue);
		infoValues.add(info);
		spicesInfo.put(currValue,infoValues);
		System.out.println(valueToPrint);
		return info;
	}
	
	public HashMap<String,ArrayList<SpicesInfo>> getUserInfo(){
		return userInfo;
	}
	
	public HashMap<String,ArrayList<SpicesInfo>> getSpicesInfo(){
		return spicesInfo;
	}
	
	public HashMap<String,ArrayList<SpicesInfo>> getCountryInfo(){
		return countryInfo;
	}
}
