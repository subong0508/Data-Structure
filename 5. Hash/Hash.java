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

public final class Hash<K> implements IHash<K> {
    /*
    * Use some variables for your implementation.
    */
    private ArrayList<K> table;
    private IHashFunction<K> h;
    private IResizeFunction ex;
    private int tablesize;
    private int n; // size

    public Hash(IHashFunction<K> h, IResizeFunction ex, int tablesize) {
        /*
         * Constructor
         * This function is an initializer for this class.
         * Input:
         *  + IHashFunction<K> h:
         *      int h.hash(K key, int tablesize): returns an integral hash value of key.
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
         *  If the key is already in the hashtable, ignore the request.
         *  To decide whether two keys are equal,
         *  you must use _key.equals_ method.
         */
        // not null..
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
        int hashVal = h.hash(key, tablesize);
    	int cnt = 0;

    	while((cnt<tablesize) && !key.equals(table.get(hashVal))){
    		cnt++;
    		hashVal = (hashVal+1) % tablesize;
    	}

    	if(key.equals(table.get(hashVal))){
    		return hashVal;
    	}

    	// not found
    	return -1;
    }

    private void insert(K key){
    	int hashVal = h.hash(key, tablesize);
        
    	while(table.get(hashVal)!= null){
    		System.out.println("key: " + key + ", hashVal: " + hashVal);
    		hashVal = (hashVal+1) % tablesize;
    	}

    	System.out.println("key: " + key + " inserted to hashVal: " + hashVal);
    	table.set(hashVal, key);
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
        *  If the table does not have a key, silently ignore the request.
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
