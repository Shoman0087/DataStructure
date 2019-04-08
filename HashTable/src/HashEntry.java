
public class HashEntry {
	
	private int key;
	private int data;
	
	public HashEntry(int key, int data) {
		this.key = key;
		this.data = data;
	}

	public int getKey() {
		return key;
	}

	public int getData() {
		return data;
	}

	@Override
	public String toString() {
		return "HashEntry [key=" + key + ", data=" + data + "]";
	}
	
	

}
