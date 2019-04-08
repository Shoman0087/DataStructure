package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HashTable {
	
	HashEntry[] table;
	int size = 0;
	
	public HashTable(int length) {
		// TODO Auto-generated constructor stub
		int s = findNextPrime(2 * length);
		table = new HashEntry[s];
	}
	
	public void add(City data) {
		
		if (size >= (table.length/2) )
			rehash();
		int key = hashKey(data.getName()) ;
		int c = 1;
		while (table[key] != null) {
			System.out.println(c);
			if (table[key].getData().getName().equals(data.getName()))
				return ;
			key += Math.pow(c++, 2);
			key %= table.length;
		}
		table[key] = new HashEntry(key, data);
		size++;
		
	}
	
	public void delete(City data) {
		int key = find(data);
		
		if (key == -1)
			return ;
		table[key] = null;
		int c = 1;
		key = (int) (key + Math.pow(2, c++));
		while (table[key] != null) {
			City temp = table[key].getData();
			table[key] = null;
			add(temp);
			key += Math.pow(c++, 2);
		}
	}
	
	public void delete(String data) {
		int key = find(data);
		System.out.println(key);
		if (key == -1)
			return ;
		
		table[key] = null;
		int c = 1;
		size--;
		key = (int) (key + Math.pow(2, c++));
		while (table[key] != null) {
			City temp = table[key].getData();
			table[key] = null;
			add(temp);
			key += Math.pow(c++, 2);
		}
	}
	
	public int find(City data) {
		int key = hashKey(data.getName()) % table.length;
		int c = 1;
		while (!table[key].getData().equals(data)) {
			if (table[key] == null)
				return -1;
			key += Math.pow(c++, 2);
		}
		return key;
	}
	
	public int find(String data) {
		int key = hashKey(data) % table.length;
		int c = 0;
		//System.out.println(key);
		while (table[key] != null && !table[key].getData().getName().equals(data)) {
			key += Math.pow(c++, 2);
			if (table[key] == null)
				return -1;
		}
		return key;
	}
	
	
	public int hashKey(String s) {
		int hash = 0;
		int g = 7;
		for (int i = 0 ; i < s.length() ; i++) {
			hash = ((hash << 3) + hash + s.charAt(i)) % table.length;
		}
		return hash;
		
	}
	
	public void rehash() {
		HashEntry[] temp = new HashEntry[findNextPrime(2 * table.length)];
		HashEntry[] temp2 = table;
		table = temp;
		for (int i = 0 ; i < temp2.length ; i++) {
			if (temp2[i] != null)
				add(temp2[i].getData());
		}
	}
	
	public City get(String data) {
		int key = find(data);
		
		if (key != -1)
			return table[key].getData();
		else
			return null;
	}
	
	public HashEntry get2(String data) {
		int key = find(data);
		
		if (key != -1)
			return table[key];
		else
			return null;
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
	
	public ObservableList<City> readHash() {
		ObservableList<City> list  = FXCollections.observableArrayList();
		
		for (int i = 0 ; i < table.length ; i++) {
			if (table[i] != null) 
				list.add(table[i].getData());
			else
				list.add(new City("null",0,0));
		}
		return list;
	}

}
