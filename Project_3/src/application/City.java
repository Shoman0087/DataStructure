package application;

public class City {
	
	private String name;
	private int rank;
	private int numerOfTourists;
	
	public City() {
		// TODO Auto-generated constructor stub
		name = null;
		
	}

	public City(String name, int rank, int numerOfTourists) {
		super();
		this.name = name;
		this.rank = rank;
		this.numerOfTourists = numerOfTourists;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getNumerOfTourists() {
		return numerOfTourists;
	}

	public void setNumerOfTourists(int numerOfTourists) {
		this.numerOfTourists = numerOfTourists;
	}

	@Override
	public String toString() {
		return "City [name=" + name + ", rank=" + rank + ", numerOfTourists=" + numerOfTourists + "]";
	}
	

}
