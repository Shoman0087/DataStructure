package Q1;

public class Courser {
	
	Node[] list;
	
	public  void initialize(int size) {
		list = new Node[size];
		
		for (int i = 0 ; i < list.length ; i++) 
			list[i] = new Node(i + 1 , null);
		list[size - 1] = new Node(0 , null);
	}
	
	public int alloc() {
		if (list[0].next == 0) // Full
			return 0;
		
		int p = list[0].next;
		list[0].next = list[p].next;
		list[p].next = 0;
		return p;
	}
	
	public void free(int p) {
		list[p].next = list[0].next;
		list[0].next = p;
	}
	
	public Boolean isEmpty(int L) {
		return (list[L].next == 0);
	}
	
	public Boolean isLast(int p) {
		return (list[p].next == 0);
	}
	
	public int find(Object data , int L) {
		int p = list[L].next;
		
		while (p != 0) {
			if (list[p].data.equals(data))
				return p;
			p = list[p].next;
		}
		return 0;
	}
	public  boolean removeFirst(int L) {
		
		if (isEmpty(L))
			return false;
		
		int pos = list[L].next;
		list[L].next = list[pos].next;
		free(pos);
		
		return true;
		
	}
	
	public  boolean remove(int L, Object x) {
		
		if (isEmpty(L))
			return false;
		
		if (list[list[L].next].data.equals(x))
			return removeFirst(L);
		
		int prevCurrent = L;
		int current = list[L].next;
		
		while (current != 0) {
			
			if (list[current].data.equals(x)) {
				
				list[prevCurrent].next = list[current].next;
				free(current);
				
				return true;
			
			}
		
			prevCurrent = current;
			current = list[current].next;
		
		}
		
		return false;
	}
	public  void addFirst(int L, Object x) {
		
		int pos = alloc();
		
		if (pos != 0) { // Not full
			
			list[pos].data = x;
			list[pos].next = list[L].next;
			list[L].next = pos;
		
		}
		
	}
	
	public  void addLast(int L, Object x) {
		
		int pos = alloc();
		
		if (pos != 0) { // Not full
		
			while (list[L].next != 0)
				L = list[L].next;
		
			list[L].next = pos;
			list[pos].data = x;
			list[pos].next = 0;
		
		}
		
	} 
	public void insert(int L ,int index, Object data) {
		
		if (index == 0) {
			addLast(L, data);
			return ;
		}
		int p = alloc();
		if (p != 0) {
			int curr = L;
			for (int i = L ; i < index - 1   ; i = curr){
				if (curr == 0)
					return ;
				curr = list[curr].next;
			}
			list[p].data = data;
			list[p].next = list[curr].next;
			list[curr].next = p;
		
		}
			
		
	}
	
	public void push(int L ,Object o) {
		
		int p = alloc();
		
		if (p != 0) {
			
			while (list[L].next != 0) {
				L = list[L].next;
			}
			
			list[L].next = p;
			list[p].next = 0;
			list[p].data = o;
		}
	}
	
	
	public Object pop(int L) {
		
		if (list[L].next == 0)
			return null;
		while (list[list[L].next].next != 0) {
			L = list[L].next;
		}
		Object o = list[list[L].next].data;
		list[L].next = 0;
		return o;
	}
	
	
	public void swip(int L , Object key1 , Object key2) {
		
		if (list[L].next == 0)
			return ;
		int k1 = find(key1,L);
		int k2 = find(key2,L);
		int p1 = k1;
		//int p2 = list[k2].next;
		//Object d;
		
		
		while (k1 != k2) {
			insert(L, k2 , list[k1].data);
			p1 = list[k1].next;
			remove(L, list[k1].data);		
			k1 = p1;
		}
		
		
	}
	
	/*
	public String toString() {
		String st = "";
		int curr;
		for (int i = 0 ; i < list[0].next ; i++) {
			curr = i;
			if (list[curr].data == null) {
			while (curr != 0 && list[curr].data != null) {
				st += list[curr].data.toString() + " ";
				curr = list[curr].next;
			} 
			}
			st += "\n";
			
		}
		return st;
	}
	*/
}
	


