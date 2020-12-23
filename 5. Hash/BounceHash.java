/*
* Name: 정재은
* Student ID#: 2017122063
*/

import java.lang.Math;
import java.util.List;
import java.util.ArrayList;
/*
* Do NOT use any external packages/classes.
* If you (un)intentionally use them we did not provide,
* you will get 0.
* Also do NOT use auto-import function on IDEs.
* If the import statements change, you will also get 0.
*/

public final class BounceHash<K> implements IHash<K> {
    /*
    * Use some variables for your implementation.
    */
    private ArrayList<K> table;
    private IBounceHashFunction<K> h;
    private IResizeFunction ex;
    private int tablesize;
    private int n; // size

    public BounceHash(IBounceHashFunction<K> h, IResizeFunction ex, int tablesize) {
        /*
         * Constructor
         * This function is an initializer for this class.
         * Input:
         *  + IBounceHashFunction<K> h:
         *      int h.hash1(K key, int tablesize): returns an integral hash value of key with h_1.
         *      int h.hash2(K key, int tablesize): returns an integral hash value of key with h_2.
         *      void h.changeHashFn(int tablesize): change the hash functions for rehashing.
         *  + IResizeFunction ex:
         *      boolean ex.checkResize(int tablesize, int numItems): returns 'true' if the table must be extended for containing 'numItems' items. Otherwise, returns 'false'.
         *      int ex.extendTable(int tablesize): returns new tablesize for extended table.
         *  + tablesize: the initial table size of the hash table.
        */
        table = new ArrayList<K>(tablesize);
        // init
        for(int i=0;i<tablesize;i++)
            table.add(null);
        this.h = h;
        this.ex = ex;
        this.tablesize = tablesize;
        this.n = 0;
    }

    @Override
    public void put(K key) {
        /**
         * Input:
         * + key: A key to be added 
         * 
         * Job:
         *  Add the key into the hashtable.
         *  If the table must be extended, extend the table and retry adding the key.
         *  If the insertion results in a cycle, 
         *  If the key is already in the hashtable, ignore the request.
         *  To decide whether two keys are equal,
         *  you must use _key.equals_ method.
         */
        if(key == null)
        	return;

        if(search(key) == -1){
            System.out.println("Putting " + key);
            insert(key);
            n++;
            if(ex.checkResize(tablesize, n)){
                // resize
                resize();
            }

        } else{
            System.out.println("Already exists!");
        }
    }

    private int search(K key){
    	/*
        * Precondition: key must not be null.
        */
        int hashVal1 = h.hash1(key, tablesize);
        int hashVal2 = h.hash2(key, tablesize);
        int hashVal = hashVal1;
        boolean found = false;
        int cnt = 0;

        if(key.equals(table.get(hashVal1))){
            hashVal = hashVal1;
            found = true;
        } else if(key.equals(table.get(hashVal2))){
            hashVal = hashVal2;
            found = true;
        }

        /*
        linear search
        while(!found && (cnt<tablesize)){
        	System.out.println("hihihihihihihihihihi");
            hashVal = (hashVal+1)%tablesize;
            cnt++;

            if(key.equals(table.get(hashVal)))
                found = true;
        }
        */

        if(found){
            return hashVal;
        } 

        return -1;
    }

    private void insert(K x){
        boolean cycle = false;
        K xOrigin = x;
        int hOrigin = h.hash1(x, tablesize);
        int hashVal = h.hash1(x, tablesize);

        while(true){
            if(table.get(hashVal)==null){
                System.out.println(x + " inserted into " + hashVal);
                table.set(hashVal, x);
                System.out.println(show());
                return;
            }

            K y = table.get(hashVal);
            System.out.println(x + " inserted into " + hashVal);
            table.set(hashVal, x);
            if(h.hash1(y, tablesize) == hashVal){
                x = y;
                hashVal = h.hash2(x, tablesize);
            } else{
                x = y;
                hashVal = h.hash1(x, tablesize);
            }

            System.out.println(show());
            if(xOrigin.equals(x) && hOrigin == hashVal){
                cycle = true;
                break;
            }
        }

        // Cycle Condition
        if(cycle){
            System.out.println("Cycle... x: " + x + ", x_hashVal: " + hashVal);
            h.changeHashFn(tablesize);
            System.out.println("Change hash function...");
            rehash();
            insert(x);
        } 
    }

    private void rehash(){
        System.out.println("Rehashing...");
        // prepare
        int newTableSize = tablesize;
        ArrayList<K> newTable = new ArrayList<K>(newTableSize);
        for(int i=0;i<newTableSize;i++)
            newTable.add(i, null);

        ArrayList<K> tmpTable = table;
        int tmpSize = tablesize;
        n = 0;

        tablesize = newTableSize;
        table = newTable;

        for(int i=0;i<tmpSize;i++){
			put(tmpTable.get(i));
        }
    }

    private void resize(){
        System.out.println("Resizing...");
        // prepare
        int newTableSize = ex.extendTable(tablesize);
        ArrayList<K> newTable = new ArrayList<K>(newTableSize);
        for(int i=0;i<newTableSize;i++)
            newTable.add(i, null);

        ArrayList<K> tmpTable = table;
        int tmpSize = tablesize;
        n = 0;

        tablesize = newTableSize;
        table = newTable;

        for(int i=0;i<tmpSize;i++){
            put(tmpTable.get(i));
        }

        System.out.println("Resizing is done!");
    }

    @Override
    public void remove(K key) throws IllegalStateException {
        /*
        * Input:
        *  + key: A key to be removed
        *
        * Job:
        *  Delete the key from the hash table.
        *  If the table does not have key, silently ignore the request.
        *  To decide whether two keys are equal,
        *  you must use _key.equals_ method.
        */
        if(key==null)
            throw new IllegalStateException("There is no such value!");

        int idx = search(key);
        if(idx == -1)
            throw new IllegalStateException("There is no such value!");

        System.out.println("Removing " + key + " at idx: " + idx);
        table.set(idx, null);
        n--;
    }

    @Override
    public boolean exists(K key) {
        /*
        * Input:
        *  + key: A key to be checked
        *
        * Job:
        *  Return true if the key is in the table; false otherwise.
        *  To decide whether two keys are equal,
        *  you must use _key.equals_ method.
        */
        if(key==null)
        	return false;

        return search(key)!=-1;
    }

    @Override
    public int size() {
        /*
        * Job:
        *  Return the number of items in the hashtable.
        */
        return n;
    }

    @Override
    public int tablesize() {
        /*
        * Job:
        *  Return the size of current hashtable.
        */
        return tablesize;
    }

    @Override
    public List<K> show() {
        /*
        * Job:
        *  Return the items in the hashtable.
        *  The list index must be the bucket index of the item.
        *  If a bucket has no item, assign null.
        *  Note that you can use ArrayList.
        */
        ArrayList<K> tmp = new ArrayList<K>(tablesize);
        for(int i=0;i<tablesize;i++){
            tmp.add(table.get(i));
        }

        return tmp;
    }

}
