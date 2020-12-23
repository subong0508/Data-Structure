import java.util.*;

/*
* Since this file is not a part of submission,
* you can use any class/packages.
*/

public class Main
{
    public static void main(String[] args){
       // test1();
       // test2();
       // test3();
       // test4();
       // testRandom(10, 10);

       int lim = (int)(Math.pow(2, 21));;
       for(int i=2;i<=lim;i*=2){
            testTime(i, i);
       }
    }

    public static void test1(){
        System.out.println("****** test1 *****");
        Tree<Integer> tree = new Tree<>(Comparator.<Integer>naturalOrder());
        
        int[] insertArr = {1, 2, 3, 4};
        int[] deleteArr = {4, 3, 2, 1};
        
        for(int e: insertArr){
            tree.displayNode(tree.insert(e), "insert node");
            System.out.println("size: " + tree.size());
            System.out.println("height: " + tree.height());
            System.out.println("isEmpty: " + tree.isEmpty());
            System.out.println("search for " + e + ": " + tree.search(e));
            tree.depthOrder();
            tree.inOrder();
            System.out.println();
        }

        System.out.println("hey! " + tree.root().numChildren());

        for(int e: deleteArr){
            tree.delete(e);
            System.out.println("size: " + tree.size());
            System.out.println("height: " + tree.height());
            System.out.println("isEmpty: " + tree.isEmpty());
            System.out.println("search for " + e + ": " + tree.search(e));
            tree.depthOrder();
            tree.inOrder();
            System.out.println();
        }
    }

    public static void test2(){
        System.out.println("****** test2 *****");
        Tree<Integer> tree = new Tree<>(Comparator.<Integer>naturalOrder());

        int[] insertArr = {10, 15, 24, 2, 8, 12, 18, 27, 32};
        int[] deleteArr = {10, 2, 18, 24, 27, 15, 8, 32, 12};
        
        for(int e: insertArr){
            tree.displayNode(tree.insert(e), "insert node");
            System.out.println("size: " + tree.size());
            System.out.println("height: " + tree.height());
            System.out.println("isEmpty: " + tree.isEmpty());
            System.out.println("search for " + e + ": " + tree.search(e));
            tree.depthOrder();
            tree.inOrder();
            System.out.println();
        }

        for(int e: deleteArr){
            tree.delete(e);
            System.out.println("size: " + tree.size());
            System.out.println("height: " + tree.height());
            System.out.println("isEmpty: " + tree.isEmpty());
            System.out.println("search for " + e + ": " + tree.search(e));
            tree.depthOrder();
            tree.inOrder();
            System.out.println();
        }
    }

    public static void test3(){
        System.out.println("****** test3 *****");
        Tree<Integer> tree = new Tree<>(Comparator.<Integer>naturalOrder());
        
        int[] insertArr = {4, 3, 2, 1, 12, 11, 10, 9, 5, 6, 7, 8};;
        int[] deleteArr = {1, 2, 3, 4, 8, 7, 6, 5, 9, 10, 11, 12};

        for(int e: insertArr){
            tree.displayNode(tree.insert(e), "insert node");
            System.out.println("size: " + tree.size());
            System.out.println("height: " + tree.height());
            System.out.println("isEmpty: " + tree.isEmpty());
            System.out.println("search for " + e + ": " + tree.search(e));
            tree.depthOrder();
            tree.inOrder();
            System.out.println();
        }

        for(int e: deleteArr){
            tree.delete(e);
            System.out.println("size: " + tree.size());
            System.out.println("height: " + tree.height());
            System.out.println("isEmpty: " + tree.isEmpty());
            System.out.println("search for " + e + ": " + tree.search(e));
            tree.depthOrder();
            tree.inOrder();
            System.out.println();
        }
    }

    public static void test4(){
        System.out.println("****** test4 *****");
        Tree<Integer> tree = new Tree<>(Comparator.<Integer>naturalOrder());
        
        int[] insertArr = new int[20];
        for(int i=0;i<20;i++)
            insertArr[i] = i+1;
        int[] deleteArr = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 11, 12, 13, 14,
            15, 16, 17, 18, 19, 20};

        for(int e: insertArr){
            tree.displayNode(tree.insert(e), "insert node");
            System.out.println("size: " + tree.size());
            System.out.println("height: " + tree.height());
            System.out.println("isEmpty: " + tree.isEmpty());
            System.out.println("search for " + e + ": " + tree.search(e));
            tree.depthOrder();
            tree.inOrder();
            System.out.println();
        }

        for(int e: deleteArr){
            tree.delete(e);
            System.out.println("size: " + tree.size());
            System.out.println("height: " + tree.height());
            System.out.println("isEmpty: " + tree.isEmpty());
            System.out.println("search for " + e + ": " + tree.search(e));
            tree.depthOrder();
            tree.inOrder();
            System.out.println();
        }
    }

    public static void testRandom(int num, int remove){
        System.out.println("****** testRandom *****");
        Tree<Integer> tree = new Tree<>(Comparator.<Integer>naturalOrder());
        
        int[] arr = new int[num];
        for(int i=0;i<num;i++)
            arr[i] = i+1;

        for(int i=0;i<num;i++){
            int j = (int)(Math.random()*num);
            int k = (int)(Math.random()*num);

            int tmp = arr[j];
            arr[j] = arr[k];
            arr[k] = tmp;
        }

        for(int e: arr){
            System.out.println("Inserting " + e);
            tree.displayNode(tree.insert(e), "insert node");
            System.out.println("size: " + tree.size());
            System.out.println("height: " + tree.height());
            System.out.println("isEmpty: " + tree.isEmpty());
            if(!tree.search(e)){
                System.out.println("bye bye...");
                System.exit(0);
            }
            System.out.println("search for " + e + ": " + tree.search(e));
            tree.depthOrder();
            tree.inOrder();
            System.out.println();
        }

        for(int i=0;i<num;i++){
            int j = (int)(Math.random()*num);
            int k = (int)(Math.random()*num);

            int tmp = arr[j];
            arr[j] = arr[k];
            arr[k] = tmp;
        }

        int j = 0;
        System.out.println("Now removing...");
        for(int i=0;i<num;i++){
            if(j>remove)
                return;
            int e = arr[i];
            tree.delete(e);
            if(tree.search(e)){
                System.out.println("bye bye...");
                System.exit(0);
            }
            System.out.println("Deleting " + e);
            System.out.println("size: " + tree.size());
            System.out.println("isEmpty: " + tree.isEmpty());
            System.out.println("search for " + e + ": " + tree.search(e));
            tree.depthOrder();
            tree.inOrder();
            System.out.println();
            j++;
        }

        System.out.println("Fin!");
    }

    public static void testTime(int num, int remove){
        System.out.println("****** testTime *****");
        long start = System.currentTimeMillis();

        Tree<Integer> tree = new Tree<>(Comparator.<Integer>naturalOrder());
        
        int[] arr = new int[num];
        for(int i=0;i<num;i++)
            arr[i] = i+1;

        for(int e: arr){
            tree.insert(e);
        }

        for(int e: arr){
            tree.delete(e);
        }

        int log = (int)(Math.log10(num)/Math.log10(2));
        long end = System.currentTimeMillis();
            System.out.printf("size: %d, time: %d%n", log, end-start);
    }
}
