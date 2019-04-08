package application;

public class HashEntry {
	
	private int key;
	private City data;
	
	public HashEntry(int key, City data) {
		this.key = key;
		this.data = data;
	}

	public int getKey() {
		return key;
	}

	public City getData() {
		return data;
	}

	@Override
	public String toString() {
		return "HashEntry [key=" + key + ", data=" + data + "]";
	}
	
	

}
