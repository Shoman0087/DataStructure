package Q1;

public class Driver {
	public static void main(String[] args) {
		Courser list = new Courser();
		list.initialize(20);
		/*
		int L1 = list.alloc();
		
		list.addLast(L1, "mohammad");
		list.addLast(L1, "ahmad");;
		list.addLast(L1, "sami");;
		list.addLast(L1, "fadi");;
		list.addLast(L1, "wass");;
		int L2 = list.alloc();
		list.addLast(L2,  0);
		list.addLast(L2,  1);
		list.addLast(L2,  2);
		list.addLast(L2,  3);
		list.addLast(L2,  4);
		list.insert(L2, 9, 5);
		
		list.addLast(L1, "shom");
		list.addLast(L1, "shadow");
		
		for (int i = list.list[L1].next ; i != 0 ; i = list.list[i].next) 
			System.out.print(list.list[i].data.toString() + " > " + list.list[i].next + "   ");
		System.out.println("");
		System.out.println("################");
		for (int i = list.list[L2].next ; i != 0 ; i = list.list[i].next) 
			System.out.print(list.list[i].data.toString() + " > " + list.list[i].next + "   ");
		
		list.swip(L1, "ahmad", "shom");
		System.out.println();
		System.out.println();
		
		for (int i = list.list[L1].next ; i != 0 ; i = list.list[i].next) 
			System.out.print(list.list[i].data.toString() + " " + list.list[i].next + "   ");
			
			*/
		int L1 = list.alloc();
		
		list.push(L1, 0);
		list.push(L1, 1);
		list.push(L1, 2);
		list.push(L1, 3);
		list.push(L1, 4);
		list.push(L1, 5);
		for (int i = list.list[L1].next ; i != 0 ; i = list.list[i].next) 
			System.out.print(list.list[i].data.toString() + " > " + list.list[i].next + "   ");
		System.out.println("");
		
		list.pop(L1);
		list.pop(L1);
		
		for (int i = list.list[L1].next ; i != 0 ; i = list.list[i].next) 
			System.out.print(list.list[i].data.toString() + " > " + list.list[i].next + "   ");
	}

}
