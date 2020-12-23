
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
        for(int i=0;i<1;i++)
        //     testHash(5, 10, 4, 6);
            testBounceHash(5, 6, 20, 6);
		//testBounceHash(5, 7, 6, 20);
        // IHash<Integer> h = new BounceHash<Integer>(new BounceHashFn1(), 
        //     new BounceResizeFn1(), 4);
        // h.put(13); //h_1(13)=1, h_2(13)=3
        // h.put(15); //h_1(15)=3,h_2(15)=3
        // h.put(11); //h_1(11)=3, h_2(11)=2
        // h.put(6); // h_1(6)=2, h_2(6)=1
        // System.out.println("final: " + h.show());
        // System.out.println("****** Fin ******");
	}

    public static void testHash(int tablesize, int put, int remove, int extra){
        System.out.println("***** Test Hash *****");
        IHash<Integer> h = new Hash<Integer>(new HashFn1(), new ResizeFn1(), tablesize);

        for(int i=0;i<put;i++){
            int r = (int)(Math.random()*extra);
            System.out.println("Trying to put " + (i+r));
            h.put(i+r);
            System.out.println("final: " + h.show());
            System.out.println("size: " + h.size());
            System.out.println("tablesize: " + h.tablesize() + "\n");
        }

        for(int j=0;j<remove;j++){
            try{
                int r = (int)(Math.random()*extra);
                System.out.println("Trying to remove " + r);
                h.remove(r);
            } catch(IllegalStateException e){
                System.out.println(e.getMessage());
            }
            System.out.println(h.show());
            System.out.println("size: " + h.size());
            System.out.println("tablesize: " + h.tablesize() + "\n");
        }
    }

    public static void testBounceHash(int tablesize, int put, int remove, int extra){
        System.out.println("***** Test BounceHash *****");
        IHash<Integer> h = new BounceHash<Integer>(new BounceHashFn1(), 
            new BounceResizeFn1(), tablesize);

        for(int i=0;i<put;i++){
            int r = (int)(Math.random()*extra);
            System.out.println("Trying to put " + (i+r));
            h.put(i+r);
            System.out.println("final: " + h.show());
            System.out.println("size: " + h.size());
            System.out.println("tablesize: " + h.tablesize() + "\n");
        }

        for(int j=0;j<remove;j++){
            try{
                int r = (int)(Math.random()*extra);
                System.out.println("Trying to remove " + r);
                h.remove(r);
                System.out.println(r+ " exists: " + h.exists(r));
            } catch(IllegalStateException e){
                System.out.println(e.getMessage());
            }
            System.out.println(h.show());
            System.out.println("size: " + h.size());
            System.out.println("tablesize: " + h.tablesize() + "\n");
        }
    }

    public static void printLine(){
        for(int i=0;i<50;i++)
            System.out.print("=");
        System.out.println();
    }
}

class Custom{
    int x;

    public Custom(int x){
        this.x = x;
    }

    public boolean equals(Custom other){
        return this.x == other.x;
    }
}

class HashFn1 implements IHashFunction<Integer> {
    public int hash(Integer i, int length) {
        return (i % length);
    }
}

class ResizeFn1 implements IResizeFunction {
    public boolean checkResize(int tablesize, int numItems) {
        if (numItems > tablesize * 0.8) {
            return true;
        }
        return false;
    }

    public int extendTable(int tablesize) {
        return tablesize * 2;
    }
}

class BounceHashFn1 implements IBounceHashFunction<Integer> {
    int offset = 0;

    public int hash1(Integer i, int length) {
        return ((i + offset) % length);
    }

    public int hash2(Integer i, int length) {
        return (((i + offset) / length) % length);
    }

    public void changeHashFn(int tablesize) {
        offset++;
    }
}

class BounceResizeFn1 implements IResizeFunction {
    public boolean checkResize(int tablesize, int numItems) {
        if (numItems > tablesize * 0.8) {
            return true;
        }
        return false;
    }

    public int extendTable(int tablesize) {
        return tablesize * 2;
    }
}
