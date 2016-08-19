package mappings;

public final class SpicesInfo {
	String[] spiceAttributes;
	String adjective;
	String country;
	int rating;
	public SpicesInfo(){
		
	}
	
	public SpicesInfo(String[] spiceAttributes, String adjective, String country, int rating){
		this.spiceAttributes = spiceAttributes;
		this.adjective = adjective;
		this.country = country;
		this.rating = rating;
	}

	public void setSpiceAttributes(String[] spiceAttributes){
		this.spiceAttributes = spiceAttributes;
	}

	public String[] getSpiceAttributes(){
		return this.spiceAttributes;
	}

	public void setAdjective(String adjective){
		this.adjective = adjective;
	}

	public String getAdjective(){
		return this.adjective;
	}
	
	public void setCountry(String country){
		this.country = country;
	}
	
	public String getCountry(){
		return this.country;
	}

	public void setRating(int rating){
		this.rating = rating;
	}

	public int getRating(){
		return rating;
	}
}
