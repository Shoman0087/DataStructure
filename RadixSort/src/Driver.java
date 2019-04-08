
public class Driver {
	static LinkedList<Integer>[] arr = new LinkedList[10];	
	public static void main(String[] args) {		
		for (int i = 0 ; i < arr.length ; i++)
			arr[i] = new LinkedList<>();	
		
		
		int[] arr2 = {20,6,2,127,3,12,55,44,82,1,47,333,222};
		
		arr2 = sort(arr2);				
		
		
		for (int i = 0 ; i < arr2.length ; i++)
			System.out.print(arr2[i] + " ");		
	}	
	
	
	static int[] sort(int[] arr2) {
		
		for (int i = 0 ; i < 3 ; i++) {
			
			
			for (int j = 0 ; j < arr2.length ; j++) 
					arr[getDigit(arr2[j], i)].add(arr2[j]);
			
			int c = 0;
			for (int k = 0 ; k < arr.length ; k++) 
				while (arr[k].getHead().getNext() != null) 					 
					arr2[c++] = arr[k].poll();				
		}
		return arr2;
	}	
	
	static int getDigit(int num , int index) {	
		for (int i = 0 ; i < index ; i++) 
			num = num / 10;		
		return num % 10;
	}

}
