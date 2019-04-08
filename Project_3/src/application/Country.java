package application;

public class Country {
	
	private String country;
	private LinkedList cities;
	private int numberOfCities = 0;
	private int numberOfAllTourists = 0;
	
	
	public Country() {
		// TODO Auto-generated constructor stub
	}
	
	public Country(String country) {
		this.country = country;
		cities = new LinkedList();
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public LinkedList getCities() {
		return cities;
	}

	public int getNumberOfAllTourists() {
		return numberOfAllTourists;
	}
	
	
	public int getNumberOfCities() {
		return numberOfCities;
	}

	public void setNumberOfCities(int numberOfCities) {
		this.numberOfCities = numberOfCities;
	}
	
	

	public void setNumberOfAllTourists(int numberOfAllTourists) {
		this.numberOfAllTourists = numberOfAllTourists;
	}

	public String toString() {
		return country + "  " + numberOfAllTourists;
	}
	
	

}
