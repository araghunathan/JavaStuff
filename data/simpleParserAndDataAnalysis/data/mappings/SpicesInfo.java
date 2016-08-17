package mappings;

public final class SpicesInfo {
	String[] spiceAttributes;
	String adjective;
	String country;
	public SpicesInfo(){
		
	}
	
	public SpicesInfo(String[] spiceAttributes, String adjective, String country){
		this.spiceAttributes = spiceAttributes;
		this.adjective = adjective;
		this.country = country;
	}
	
	public String[] getSpiceAttributes(){
		return this.spiceAttributes;
	}
	
	public String getAdjective(){
		return this.adjective;
	}
	
	public String getCountry(){
		return this.country;
	}
}
