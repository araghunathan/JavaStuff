package mappings;

import java.util.HashMap;

public final class Adjectives {
    
	public static String[] SPICES = 
				{		"Basil",
						"Black Pepper",
						"Brown Sugar",
						"Cayenne",
						"Chili Powder",
						"Cloves",
						"Coconut",
						"Coriander",
						"Cumin",
						"Oregano",
						"Paprika",
						"Red Pepper",
						"Rock Salt",
						"Turmeric"
				};
	
	public static String[] ADJECTIVES = 
				{		"Ay Caramba",
						"Caliente",
						"Cool",
						"Earthy",
						"Exotic",
						"Hot",
						"Majestic",
						"Wonderful",
				};
	
	public static HashMap<String,String> COUNTRY_VALUES = new HashMap<String,String>();
	static{
			COUNTRY_VALUES.put("Balinese", "Bali");
			COUNTRY_VALUES.put("Bolivian", "Bolivia");
			COUNTRY_VALUES.put("Brazilian", "Brazil");
			COUNTRY_VALUES.put("Costa Rican", "Costa Rica");
			COUNTRY_VALUES.put("Dominican", "Dominican Republic");
			COUNTRY_VALUES.put("Salvadorean", "El Salvador");
			COUNTRY_VALUES.put("Ethiopian", "Ethiopia");
			COUNTRY_VALUES.put("Guatemalan", "Guatemala");
			COUNTRY_VALUES.put("Indian", "India");
			COUNTRY_VALUES.put("Kenyan", "Kenya");
			COUNTRY_VALUES.put("Malian", "Mali");
			COUNTRY_VALUES.put("Mexican", "Mexico");
			COUNTRY_VALUES.put("Panamanian", "Panama");
			COUNTRY_VALUES.put("Peruvian", "Peru");
			COUNTRY_VALUES.put("Sumatran", "Sumatra");
	}
			
	/* this performs a binary search on a sorted array of Strings to find the element in question */
	public boolean searchElement(String[] which, String element, int low, int high){		
		if((high - low) <= 1)
		{
			if((which[low].equals(element)) || (high < which.length && which[high].equals(element))){
				return true;
			}
			return false;
		}
		int mid = (int)((low+high)/2);
		int comparison = which[mid].compareTo(element);
		if(comparison == 0){
			return true;
		}else if(comparison > 0){
			return searchElement(which,element,low,mid);			
		}else{
			return searchElement(which,element,mid,high);
		}
	}
}
