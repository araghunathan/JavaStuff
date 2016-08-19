/**
 * 
 */
package driver;

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
public class SpiceDriver {

	static int numEntries = 0;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Yes we start");
		parseContents();
		summarizeContents();
	}
	
	private static void parseContents(){
		try {
			File file = new File("src/data/spice_ratings_sample.txt");
			BufferedReader bf = new BufferedReader(new FileReader(file.getPath()));
			SpiceParser parser = SpiceParser.getInstance();
			for(String line = bf.readLine(); line != null; line = bf.readLine())
			{
				String[] values = line.split("\t");
				System.out.println("Current user: " + values[0]);
				parser.parseProperties(values);
				System.out.println("User rating: " + values[values.length-1]);
				System.out.println("-----------------------------------");
				numEntries++;
			}
		} catch (FileNotFoundException e) {
			System.err.println("Over");
			e.printStackTrace();
		} catch(IOException e){
			System.err.println("Over");
			e.printStackTrace();
		}
	}
	
	private static void summarizeContents(){
		HashMap<String,ArrayList<SpicesInfo>> userInfo = SpiceParser.getInstance().getUserInfo();
		System.out.println("Total people: " + userInfo.size());
		System.out.println("Total spice types: " + numEntries);
		HashMap<String,ArrayList<SpicesInfo>> spicesInfo = SpiceParser.getInstance().getSpicesInfo();		
		
		System.out.println("Spices");
		
		for(String s : Adjectives.SPICES){
			ArrayList<SpicesInfo> info = spicesInfo.get(s);
			int size = info != null ? info.size() : 0;
			System.out.println(s);
			System.out.println("\tTrue:" + size);
			System.out.println("\tFalse:" + (numEntries-size));
		}
		
		System.out.println("Adjectives");
		for(String s : Adjectives.ADJECTIVES){
			ArrayList<SpicesInfo> info = spicesInfo.get(s);			
			int size = info != null ? info.size() : 0;
			System.out.println("\t"+s);
			System.out.println("\t\tTrue:" + size);
			System.out.println("\t\tFalse:" + (numEntries-size));			
		}
		
		HashMap<String,ArrayList<SpicesInfo>> countryInfo = SpiceParser.getInstance().getCountryInfo();		
		
		System.out.println("Countries");
		for(String s : Adjectives.COUNTRY_VALUES.values()){
			ArrayList<SpicesInfo> info = countryInfo.get(s);			
			int size = info != null ? info.size() : 0;
			System.out.println("\t"+s+":"+size);
		}
	}

}
