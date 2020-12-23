/*
* Name: 정재은
* Student ID #: 2017122063
*/

/*
* Do NOT import additional packages/classes.
* If you (un)intentionally use packages/classes we did not provide,
* you will get 0.
*/

public class Trie implements ITrie {
	private Node<String> root;
	private int n; // size
	private int tmp; // for dictionary method

	public Trie() {
		/*
		* Constructor.
		*
		* Note that we will check the number of compare calls;
		* if the count is too low or too high (depending on cases),
		* you will fail the case.
		*/
		root = new Node<String>();
		n = 0;
	}

	@Override
	public void insert(String s) {
		if(exists(s)){
			return;
		}

		int length = s.length();
		Node<String> current = root;
		for(int i=0;i<length;i++){
			String key = s.substring(i, i+1);
			Node<String> child = new Node<String>();
			if(i==length-1){
				child.setLastChar(true);
			} else{
				child.setLastChar(false);
			}

			int idx = getPlace(current, key);
			if(idx==-1){
				idx = current.numChildren();
				current.insertKey(idx, key);
				child.setParent(current);
				current.insertChild(idx, child);
			} else if(!key.equals(current.getKey(idx))){
				current.insertKey(idx, key);
				child.setParent(current);
				current.insertChild(idx, child);
			} else if(key.equals(current.getKey(idx))){
				if(i==length-1){
					current.getChild(idx).setLastChar(true);
				} 
			}

			current = current.getChild(idx);
		}

		n++;
	}

	public int getPlace(Node<String> node, String key){
		int numKeys = node.numKeys();
		for(int i=0;i<numKeys;i++){
			if(key.compareTo(node.getKey(i))<=0)
				return i;
		}

		// not found
		return -1;
	}

	@Override
	public boolean exists(String s) {
		int length = s.length();
		Node<String> current = root;

		for(int i=0;i<length;i++){
			String key = s.substring(i, i+1);
			int idx = getPlace(current, key);

			if(idx==-1 || !key.equals(current.getKey(idx))){
				return false;
			} 

			current = current.getChild(idx);
		}

		if(current.getLastChar()){
			return true;
		} 

		return false;
	}

	@Override
	public String[] dictionary() {
		String[] arr = new String[n];
		tmp = 0;
		dictHelp(root, "", arr);

		return arr;
	}

	private void dictHelp(Node<String> node, String str, String[] arr){
		if(node.getLastChar()){
			arr[tmp++] = str;
			if(node.numChildren()==0)
				return;
		}

		for(int i=0;i<node.numChildren();i++){
			dictHelp(node.getChild(i), str+node.getKey(i), arr);
		}
	}

	@Override
	public Node<String> root() {
		return root;
	}
}