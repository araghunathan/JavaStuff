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

import mappings.SpicesInfo;

/**
 * @author Jhome
 *
 */
public class SpiceDriver {

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
			File file = new File("src/parser/spice_ratings_sample.txt");
			BufferedReader bf = new BufferedReader(new FileReader(file.getPath()));
			SpiceParser parser = SpiceParser.getInstance();
			for(String line = bf.readLine(); line != null; line = bf.readLine())
			{
				String[] values = line.split("\t");
				System.out.println("Current user: " + values[0]);
				parser.parseProperties(values);
				System.out.println("User rating: " + values[values.length-1]);
				System.out.println("-----------------------------------");
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
	}

}
