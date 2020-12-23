import java.util.Arrays;

public class Main {
	public static int cnt = 0;

	public static void main(String[] args) {
        IHeap heap = new Heap(null);
        for(int i=0;i<10;i++)
            heap.insert(i);
        for(int i=0;i<10;i++)
            System.out.println(heap.removeMax());
		// heapCheck(1, 2, 2);
		// System.out.println("***** Heap Time Check *****");
  //       for(int i=17;i<=25;i++)
  //           heapTimeCheck(i);

  //       // carsCheck(7, 20);
  //       System.out.println("***** Cars Time Check *****");
  //       for(int i=15;i<=25;i++)
  //           carsTimeCheck((int)(Math.pow(2, i)), (int)(Math.pow(2, 15)));

	}

    public static void heapTimeCheck(int n){
        System.out.println("*** Time Check ***");
        long time1 = 0, time2 = 0, time3, time4;
        IHeap heap = new Heap(new Integer[] {});
        int len = (int)(Math.pow(2, n));
        Integer[] arr = new Integer[len];
        for(int j=0;j<len;j++){
            int num = j;
            arr[j] = num;
        }
        time1 = System.currentTimeMillis();
        heap = new Heap(arr);
        time2 = System.currentTimeMillis();
        //System.out.println("heap size: " + heap.size());
        System.out.printf("construct: len=%d, time=%d%n", n, time2-time1);
        for(int j=0;j<(int)(Math.pow(2, 15));j++)
            heap.insert(heap.max()+1);
        time3 = System.currentTimeMillis();
        //System.out.println("heap size: " + heap.size());
        System.out.printf("insert: len=%d, time=%d%n", n, time3-time2);
        for(int j=0;j<(int)(Math.pow(2, 15));j++){
            heap.removeMax();
        }
        //System.out.println("heap size: " + heap.size());
        time4 = System.currentTimeMillis();
        System.out.printf("removeMax: len=%d, time=%d%n", n, time4-time3);
        System.out.println();        
    }

    public static void carsTimeCheck(int n, int k){
        System.out.println("*** Time Check ***");
        long time1 = System.currentTimeMillis();
        Cars car = new Cars(k);
        for(int i=1;i<=n;i++)
            car.carDistance(i);
        int[] arr = car.getCandidates();
        long time2 = System.currentTimeMillis();
        k = (int)(Math.log10(k) / Math.log10(2));
        System.out.printf("n: %d, logk: %d, time: %d%n", n, k, time2-time1);
        System.out.println();
    }

    public static void heapCheck(int n, int insert, int remove){
        System.out.println("*** Heap Check ***");
        Integer[] arr = new Integer[n];
        for(int i=0;i<n;i++){
        	int num = i;
        	System.out.printf("arr[%d]: %d%n", i, num);
            arr[i] = num;
        }
        IHeap heap = new Heap(null);
        System.out.printf("isEmpty: %s, size: %d%n", heap.isEmpty(), heap.size());
        heap = new Heap(arr);
        System.out.println("Heapified array: " + Arrays.toString(arr));
        System.out.printf("isEmpty: %s, size: %d%n", heap.isEmpty(), heap.size());

        printLine();
        traversal(heap.getRoot());

        printLine();
        // insert
        for(int i=0;i<insert;i++){
            int num = (n+i);
            System.out.println("inserting... " + num);
            heap.insert(num);
        }
        traversal(heap.getRoot());

        printLine();
        // removeMax, max
        for(int i=0;i<remove;i++){
            System.out.printf("max: %d, removeMax: %d%n", heap.max(), heap.removeMax());
        }

        if(!heap.isEmpty())
        	traversal(heap.getRoot());
        System.out.println();
    }

    public static void traversal(Node current){
        while(current != null){
            System.out.printf("current: %d%n", current.getData());
            double d = Math.random();
            if(d <= 0.6){
                current = current.getLChild();
                System.out.println("current -> current.left");
            }
            else{
                current = current.getRChild();
                System.out.println("current -> current.right");
            }
        }
        System.out.println("now current is null.");
    }

    public static void carsCheck(int k, int n){
        Cars cars = new Cars(k);
        for(int i=1;i<n;i+=2){
            int num = i;
            System.out.println("inserting... " + num);
            cars.carDistance(num);
            System.out.println("size: " + cars.heap.size());
        }

        int[] arr = cars.getCandidates();
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));

        for(int i=2;i<=n;i+=2){
            int num = i;
            System.out.println("inserting... " + num);
            cars.carDistance(num);
            System.out.println("size: " + cars.heap.size());
        }
        arr = cars.getCandidates();
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void printLine(){
        for(int i=0;i<50;i++)
            System.out.print("=");
        System.out.println();
    }
}