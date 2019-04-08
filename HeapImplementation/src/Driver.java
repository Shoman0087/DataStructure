
public class Driver {
	public static void main(String[] args) {
		Heap heap = new Heap();
		
		heap.insert(5);
		heap.insert(3);
		heap.insert(17);
		heap.insert(10);
		heap.insert(84);
		heap.insert(19);
		heap.insert(6);
		heap.insert(22);
        heap.insert(9);
        heap.print();
        heap.delete();
        System.out.println();
        heap.print();
        
        heap.printHeap();
        System.out.println();
        heap.sort();
        
        heap.printHeap();
        heap.buildMaxHeap();
        System.out.println();
        heap.print();
	}

}
