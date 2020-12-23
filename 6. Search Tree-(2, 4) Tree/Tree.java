/*
* Name: 정재은
* Student ID #: 2017122063
*/

import java.lang.Math;
import java.util.Comparator;
/*
* Do NOT import additional packages/classes.
* If you (un)intentionally use packages/classes we did not provide,
* you will get 0.
*/

public class Tree<K> implements ITree<K> {
	private TreeNode<K> root = null;
	private int n; // size
	private Comparator<K> comp; // comparator
	private static final int MAX_KEYS = 3;
	private static final int MIN_KEYS = 1;

	public Tree(Comparator<K> comp) {
		/*
        * Constructor.
        *
        * Note that we will check the number of compare calls;
        * if the count is too low or too high (depending on cases),
        * you will fail the case.
        */
		n = 0;
		this.comp = comp;
	}

	private boolean isLeaf(TreeNode<K> node){
		return node.getChild(0).numKeys() == 0;
	}

	private boolean overflow(TreeNode<K> node){
		return node.numKeys() > MAX_KEYS;
	}

	private boolean underflow(TreeNode<K> node){
		return node.numKeys() < MIN_KEYS;
	}

	@Override
	public TreeNode<K> root() throws IllegalStateException {
		/*
        * Return the root node.
        * If there is no root, raise an IllegalStateException.
        */
        if(isEmpty())
        	throw new IllegalStateException("There is no root!");

        return root;
	}

	@Override
	public TreeNode<K> insert(K key) {
		/*
        * Insert the given key at the appropriate node
        * with correct position and return the node.
        * You need to handle the cases of 'overflow' and
        * perform a split operation.
        * Note that you do not need to consider the case of inserting
        * the key that is already in the tree. As stated in the
        * guideline, we will only insert the key that is not in the
        * tree.
        */
        if(isEmpty()){
        	root = new TreeNode<K>();
        	root.insertKey(0, key);
        	n++;

        	root.insertChild(0, new TreeNode<K>());
        	root.insertChild(1, new TreeNode<K>());

        	return root;
        }

        TreeNode<K> current = root;
        while(!isLeaf(current)){
        	int numKeys = current.numKeys();
        	int k = 0; // compiler
        	for(k=0;k<numKeys;k++){
        		K tmp = current.getKey(k);
        		int num = comp.compare(key, tmp);

        		// key가 더 작으면
        		if(num<0){
        			break;
        		}
        	}
        		
        	current = current.getChild(k);
        }

        // 실제 삽입연산이 일어남
        put(key, current);
        if(isLeaf(current)){
			current.insertChild(current.numChildren(), new TreeNode<K>());
		}

        while(overflow(current)){
        	current = split(current);
        }

        n++;

        return searchNode(current, key);
	}

	private void put(K key, TreeNode<K> node){
		int numKeys = node.numKeys();
        for(int k=0;k<numKeys;k++){
        	K tmp = node.getKey(k);
    		int num = comp.compare(key, tmp);

    		// key가 더 작으면
    		if(num<0){
    			node.insertKey(k, key);
    			return;
    		}
        }

        int k = numKeys;
    	node.insertKey(k, key);
	}

	private TreeNode<K> split(TreeNode<K> v){
		TreeNode<K> u = null;
		if(v==root){
			u = new TreeNode<K>();
			root = u;
			root.insertChild(0, v);
		} else{
			u = v.getParent();
		}

		TreeNode<K> v1 = new TreeNode<K>();
		TreeNode<K> v2 = new TreeNode<K>();

		v1.insertKey(0, v.getKey(0));
		v1.insertKey(1, v.getKey(1));
		v2.insertKey(0, v.getKey(3));

		for(int i=0;i<v.numChildren();i++){
			if(i<=2){
				v.getChild(i).setParent(v1);
				v1.insertChild(v1.numChildren(), v.getChild(i));
			} else{
				v.getChild(i).setParent(v2);
				v2.insertChild(v2.numChildren(), v.getChild(i));
			}
		}

		v1.setParent(u);
		v2.setParent(u);

		K goUp = v.getKey(2); 
		put(goUp, u);
		v.removeKey(2);

		int idx = 0;
		for(int i=0;i<u.numChildren();i++){
			if(u.getChild(i)==v){
				idx = i;
				break;
			}
		}

		u.removeChild(idx);
		u.insertChild(idx, v1);
		u.insertChild(idx+1, v2);

		return u;
	}

	private TreeNode<K> searchNode(TreeNode<K> current, K key){
		if(key==null || isEmpty()){
			return null;
		}

		while(current!=null){
			int numKeys = current.numKeys();
			int k = 0; // for compiler
			for(k=0;k<numKeys;k++){
				K tmp = current.getKey(k);
				int compare = comp.compare(key, tmp);
				if(compare == 0){
					return current;
				} else if(compare<0){ // key가 더 작으면
					break;
				}
			}

			if(isLeaf(current)){
				return null;
			}

			current = current.getChild(k);
		}

		return null;
	}

	@Override
	public void delete(K key) {
		/*
        * Find the node with a given key and delete the key.
        * You need to handle the cases of 'underflow' and
        * perform a fusion operation.
        * If there is no "key" in the tree, please ignore it.
        */
        // System.out.println("Trying to remove " + key);
        TreeNode<K> node = searchNode(root, key);

        if(node==null){
        	return;
        }

        int idx = 0;
        for(int i=0;i<node.numKeys();i++){
        	if(key.equals(node.getKey(i))){
        		idx = i;
        		break;
        	}
        }

        TreeNode<K> v = node;
        if(!isLeaf(node)){
        	// traversal... finding index of key first
        	v = node.getChild(idx+1);
        	while(!isLeaf(v)){
        		v = v.getChild(0);
        	}
        	// swap
        	K first = v.getKey(0);
        	v.removeKey(0);
        	put(key, v);
        	node.removeKey(idx);
        	put(first, node);
        	idx = 0;
        } 
 	
 		v.removeKey(idx);
 		if(isLeaf(v)){
 			v.removeChild(0);
 		}

 		if(v==root && underflow(v)){
 			root = null;
 			n--;
 			return;
 		}

 		while(underflow(v)){
 			v = fusion(v);

 			if(v==null)
 				break;
 		}

 		n--;
	}

	private TreeNode<K> fusion(TreeNode<K> v){
		// edge case
		TreeNode<K> u = v.getParent();
		if(u==null){
			root = v.getChild(0);
			root.setParent(null);
			return root;
		}

		int vIdx = 0;
		int wIdx = 0;
		for(int i=0;i<u.numChildren();i++){
			if(u.getChild(i)==v){
				vIdx = i;

				if((vIdx-1)>=0 && (vIdx+1)<u.numChildren()){
					if(u.getChild(vIdx-1).numKeys() < u.getChild(vIdx+1).numKeys()){
						wIdx = vIdx+1;
					} else{
						wIdx = vIdx-1;
					}
				} else if((vIdx-1)<0){
					wIdx = vIdx+1;
				} else{
					wIdx = vIdx-1;
				}

				break;
			}
		}

		TreeNode<K> w = u.getChild(wIdx);

		if(w.numKeys()!=1 && wIdx==(vIdx-1)){
			w.getChild(w.numChildren()-1).setParent(v);
			v.insertChild(0, w.getChild(w.numChildren()-1));
			w.removeChild(w.numChildren()-1);

			K goDown = u.getKey(wIdx);
			u.removeKey(wIdx);
			put(goDown, v);
			K goUp = w.getKey(w.numKeys()-1);
			w.removeKey(w.numKeys()-1);
			put(goUp, u);

			return null;
		} else if(w.numKeys()!=1 && wIdx==(vIdx+1)){
			w.getChild(0).setParent(v);
			v.insertChild(v.numChildren(), w.getChild(0));
			w.removeChild(0);

			K goDown = u.getKey(vIdx);
			u.removeKey(vIdx);
			put(goDown, v);
			K goUp = w.getKey(0);
			w.removeKey(0);
			put(goUp, u);

			return null;
		} 

		// w has only 1 key
		TreeNode<K> vPrime = new TreeNode<K>();
		vPrime.setParent(u);
		if(wIdx==(vIdx-1)){
			// merge
			for(int i=0;i<w.numKeys();i++)
				put(w.getKey(i), vPrime);
			for(int i=0;i<v.numKeys();i++)
				put(v.getKey(i), vPrime);
			for(int i=0;i<w.numChildren();i++){
				w.getChild(i).setParent(vPrime);
				vPrime.insertChild(vPrime.numChildren(), w.getChild(i));
			}
			for(int i=0;i<v.numChildren();i++){
				v.getChild(i).setParent(vPrime);
				vPrime.insertChild(vPrime.numChildren(), v.getChild(i));
			}

			u.removeChild(wIdx);
			u.removeChild(wIdx);
			u.insertChild(wIdx, vPrime);
			K goDown = u.getKey(wIdx);
			u.removeKey(wIdx);
			put(goDown, vPrime);
		} else{
			// merge
			for(int i=0;i<v.numKeys();i++)
				put(v.getKey(i), vPrime);
			for(int i=0;i<w.numKeys();i++)
				put(w.getKey(i), vPrime);
			for(int i=0;i<v.numChildren();i++){
				v.getChild(i).setParent(vPrime);
				vPrime.insertChild(vPrime.numChildren(), v.getChild(i));
			}
			for(int i=0;i<w.numChildren();i++){
				w.getChild(i).setParent(vPrime);
				vPrime.insertChild(vPrime.numChildren(), w.getChild(i));
			}

			u.removeChild(vIdx);
			u.removeChild(vIdx);
			u.insertChild(vIdx, vPrime);
			K goDown = u.getKey(vIdx);
			u.removeKey(vIdx);
			put(goDown, vPrime);
		}

		return u;
	}

	@Override
	public boolean search(K key) {
		/*
		* Find a node with a given key and return true if you can find it.
		* Return false if you cannot.
		*/
		return searchNode(root, key)!=null;
	}

	@Override
	public int size() {
		/*
		* Return the number of keys in the tree.
		*/
		return n;
	}
	
	@Override
	public boolean isEmpty() {
		/*
		* Return whether the tree is empty or not.
		*/
		return root==null;
	}

	public void displayNode(TreeNode<K> node, String name){
		System.out.println("Display " + name);
		System.out.print("[");
		for(int i=0;i<node.numKeys();i++){
			System.out.print(node.getKey(i));
			if(i<node.numKeys()-1)
				System.out.print(", ");
		}
		System.out.println("]");
	}

	public void inOrder(){
		if(isEmpty()){
			System.out.println("inOrder: Empty Tree");
			return;
		}

		TreeNode<K> seq = new TreeNode<K>();
		inHelp(root, seq);

		displayNode(seq, "inOrder");
	}

	private void inHelp(TreeNode<K> node, TreeNode<K> seq){
		if(isLeaf(node)){
			for(int i=0;i<node.numKeys();i++){
				seq.insertKey(seq.numKeys(), node.getKey(i));
			}
			return;
		}

		for(int i=0;i<node.numChildren();i++){
			inHelp(node.getChild(i), seq);
			if(i < node.numKeys()){
				seq.insertKey(seq.numKeys(), node.getKey(i));
			}
		}
	}

	public void depthOrder(){
		if(isEmpty()){
			System.out.println("depthOrder: Empty Tree");
			return;
		}

		TreeNode<K> seq = new TreeNode<K>();
		TreeNode<K> seqHelp = new TreeNode<K>();

		seqHelp.insertChild(0, root);
		while(seqHelp.numChildren()!=0){
			TreeNode<K> node = seqHelp.getChild(0);
			seqHelp.removeChild(0);
			if(node.numKeys()==0){
				seq.insertKey(seq.numKeys(), null);
			}
			for(int i=0;i<node.numKeys();i++){
				seq.insertKey(seq.numKeys(), node.getKey(i));
			}
			for(int i=0;i<node.numChildren();i++)
        		seqHelp.insertChild(seqHelp.numChildren(),
        			node.getChild(i));
		}

		displayNode(seq, "depthOrder");
	}

	public int height(){
		if(root==null)
			return 0;

		int h = 0;
		TreeNode<K> node = root;

		while(node.numChildren()>0){
			h++;
			node = node.getChild(0);
		}

		return h;
	}
}
