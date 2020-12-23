public class Main {
	static final int RANGE = 10;

	public static void main(String[] args) {
		/*
		Array Test
		*/
		Array array = new Array();

		// insert
		inspectInsert(array, 1);
		printArray(array);
		printLine();

		// search
		inspectSearch(array);
		printLine();

		// delete
		inspectDelete(array);
		printArray(array);
		printLine();

		// sort, unsort
		inspectSort(array);
		printLine();

		// atIndex
		inspectAtIndex(array);
		printLine();

		/*
		Set Test
		*/
		Set set1 = new Set();
		Set set2 = new Set();

		// insert
		for(int i=0;i<30;i++){
			int tmp1 = (int)(Math.random() * 40);
			int tmp2 = (int)(Math.random() * 40);
			set1.insert(tmp1);
			set2.insert(tmp2);
		}

		System.out.println("Set1 ");
		printSet(set1);
		
		System.out.println("Set2 ");
		printSet(set2);

		// System.out.println("Union ");
		// set1.union(set2);
		// printSet(set1);

		System.out.println("Intersection ");
		set1.intersection(set2);
		printSet(set1);

		// System.out.println("Subtraction ");
		// set1.subtraction(set2);
		// printSet(set1);


	}

	public static void inspectInsert(Array array, int flag){
		if(flag == 0){
			System.out.println("Array is empty? : " + array.isEmpty());

			int arrayLength = (int)(Math.random() * 10 + 10);
			for(int i=0;i<arrayLength+5;i++){
				int tmp = (int)(Math.random() * RANGE + 1);
				array.insert(tmp);
			}

			System.out.println("Array size: " + array.size());
			System.out.println("Array is empty? : " + array.isEmpty());
			System.out.println("Array size: " + array.size());
			System.out.println("Array is sorted? : " + array.isSorted());
		} else{
			System.out.println("Array is sorted? : " + array.isSorted());

			int arrayLength = (int)(Math.random() * 10 + 10);
			for(int i=0;i<arrayLength+5;i++){
				int tmp = (int)(Math.random() * RANGE + 1);
				array.insert(tmp);
			}

			array.sort();
			System.out.println("Sorted.");
			for(int i=0;i<5;i++){
				int tmp = (int)(Math.random() * RANGE + 1);
				array.insert(tmp);
			}
			
			System.out.println("Array size: " + array.size());
			System.out.println("Array is empty? : " + array.isEmpty());
			System.out.println("Array size: " + array.size());
			System.out.println("Array is sorted? : " + array.isSorted());
		}
	}

	public static void inspectSearch(Array array){
		for(int i=0;i<10;i++){
			int searchVal = (int)(Math.random() * RANGE + 1);
			try{
				System.out.println("searchVal: " + searchVal);
				int foundIdx = array.search(searchVal);
				System.out.println("foundIdx: " + foundIdx);
			} catch(IllegalStateException e){
				System.out.println("Invalid value.");
			}
		}
	}

	public static void inspectDelete(Array array)
	{
		System.out.println("Before deleting, size: " + array.size());
		int deleteNum = (int)(Math.random() * 3 + 2);
		int deleteCnt = 0;
		System.out.println("deleteNum : " + deleteNum);

		for(int i=0;i<deleteNum;i++){
			int deleteVal = (int)(Math.random() * RANGE + 1);
			System.out.println("deleteVal : " + deleteVal);
			try{
				array.delete(deleteVal);
				deleteCnt++;
			} catch(IllegalStateException e){
				printArray(array);
			}
		}

		System.out.println("Delete Count: " + deleteCnt);
		System.out.println("After deleting, size: " + array.size());
	}

	public static void inspectSort(Array array){
		System.out.println("Array is sorted? : " + array.isSorted());
		printArray(array);

		array.unsort();

		System.out.println("Array is sorted? : " + array.isSorted());
		printArray(array);
	}

	public static void inspectAtIndex(Array array){
		System.out.printf("array size: %d\n", array.size());
		for(int i=0;i<array.size()+5;i++)
			try{
				System.out.printf("%dth index: %d\n", i, array.atIndex(i));
			} catch(IndexOutOfBoundsException e){
				System.out.printf("invalid index: %d, array size: %d\n", i, array.size());
			}
	}

	public static void printArray(Array array){
		int j=0;
		for(int i=0;i<array.size();i++){
			j++;
			System.out.printf("%3d", array.atIndex(i));
			if(j % 10 == 0)
				System.out.println();
		}

		System.out.println();
	}

	public static void printLine(){
		for(int i=0;i<50;i++){
			System.out.print("=");
		}
		System.out.println();
	}

	public static void printSet(Set set){
		int i=0;
		for(int e : set.show()){
			System.out.printf("%3d", e);
			i++;
			if(i % 5 == 0)
				System.out.println();
		}
		System.out.println();
	}
}