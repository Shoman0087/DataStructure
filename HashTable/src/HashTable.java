
public class HashTable {
	
	HashEntry[] table;
	int size = 0;
	
	public HashTable(int size) {
		// TODO Auto-generated constructor stub
		table = new HashEntry[findNextPrime(2 * size)];
	}
	
	public void add(int data) {
		if (size  >= table.length / 2)
			rehash();
		if (size != table.length-1) {
			int hash = H1(data);
			int i = 1;
			while (table[hash] != null)
				hash = (H1(data) + i++ * H2(data)) % table.length;
			table[hash] = new HashEntry(hash, data);
			size++;
		}
		
		
	}
	
	public void delete(int data) {
		int hash = find(data);
		if (hash == -1)
			return ;
		table[hash] = null;
		
		int c = 1;
		hash = (H1(data) + c++ * H2(data)) % table.length;
		while (table[hash] != null) {
			int temp = table[hash].getData();
			table[hash] = null;
			add(temp);
			hash = (H1(data) + c++ * H2(data)) % table.length;
		}
	}
	
	public int find(int data) {
		int hash = H1(data);
		int i = 1;
		while (table[hash].getData() != data) {
			if (table[hash] == null)
				return -1;
			hash = (H1(data) + i++ * H2(data)) % table.length;
		}
		return hash;
	}
	
	
	public void rehash() {
		HashEntry[] temp = new HashEntry[findNextPrime(2 * table.length)];
		HashEntry[] temp2 = table;
		table = temp;
		for (int i = 0 ; i < temp2.length ; i++) {
			if (temp2[i] != null)
				add(temp[i].getData());
		}
	}
	
	
	public int findNextPrime(int length) {

		while (true) {
			if (isPrime(length))
				return length;
			length++;
		}
		
	}
	
	public boolean isPrime(int num) {
		
		for (int i = 2 ; i < (num / 2) + 1 ; i++) {
			if (num % i == 0)
				return false;
		}
		return true;
	}

	
	public void printTable() {
		for (int i = 0 ; i < table.length ; i++) {
			System.out.println(table[i]);
		}
	}
	
	public int H1(int data) {
		return data % table.length;
	}
	
	public int H2(int data) {
		return 7 - (data % 7);
	}

}
