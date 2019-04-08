/*
 * By : Mohammad Shoman
 */
public class Heap {
	Integer[] heap;
	int size = 0;
	
	public Heap() {
		heap = new Integer[20];
	}
	
	public Heap(int size) {
		heap = new Integer[size];
	}
	
	public int parent(int i) {
		if (i == 0)
			return 0;
		
		return (int) (Math.ceil(i / 2.0)) - 1;
	}
	
	public int leftChil(int i) {
		if (i * 2 + 1 >= size) {
			return i;
		}
		return i * 2 + 1;
	}
	
	public int rightChil(int i) {
		if (i * 2 + 2 >= size) {
			return i;
		}
		return i * 2 + 2;
	}
	
	public void buildMaxHeap() {
		for (int i = size/2 ; i >= 0 ; i--)
			maxHeapify(i);
		
	}
	
	public void insert(int num) {
		heap[size] = num;
		int current = size;
		while (heap[current] > heap[parent(current)]) {
			swap(current,parent(current));
			current = parent(current);
		}
		size++;
	}
	
	public void swap(int i , int j) {
		int temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
	}
	
	public void print()
    {
        for (int i = 0; i < size / 2; i++ )
        {
            System.out.print(" PARENT : " + heap[i] + " LEFT CHILD : " + heap[2*i + 1]
                  + " RIGHT CHILD :" + heap[2 * i  + 2]);
            System.out.println();
        }
    }
	
	public int delete() {
		int temp = heap[0];
		heap[0] = heap[--size];
		heap[size] = null;
		maxHeapify(0);
		return temp;
	}
	
	
	public void maxHeapify(int i) {
		if (!isLeaf(i)) {
			if (heap[i] < heap[leftChil(i)] || heap[i] < heap[rightChil(i)]) {
				if (heap[leftChil(i)] > heap[rightChil(i)]) {
					swap(i, leftChil(i));
					maxHeapify(leftChil(i));
				} else {
					swap(i,rightChil(i));
					maxHeapify(rightChil(i));
				}
			}
		}
	}
	
	
	public boolean isLeaf(int i) {
		if (i >= size / 2 && i < size) 
			return true;
		return false;
		
	}
	
	public void sort() {
		int temp = size;
		int t = size;
		for (int i = t - 1 ; i >= 1 ; i--) {		
			swap(i,0);
			size--;
			maxHeapify(0);
			
		}
		
		size = temp;
	}
	
	public void printHeap() {
		for (int i = 0 ; i < size ; i++) {
			System.out.print(heap[i] + "  ");
		}
	}
	

}
