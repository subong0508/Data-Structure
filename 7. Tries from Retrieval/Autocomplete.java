
/*
* Name: 정재은
* Student ID #: 2017122063
*/
import java.lang.Math;
/*
* Do NOT import additional packages/classes.
* If you (un)intentionally use packages/classes we did not provide,
* you will get 0.
*/

public class Autocomplete implements IAutocomplete {
	private Trie t;
	private String[] dict;
	private int n;

	public Autocomplete() {
		/*
		* Constructor.
		*
		* Note that we will check the number of compare calls;
		* if the count is too low or too high (depending on cases),
		* you will fail the case.
		*/
		t = new Trie();
		n = 0;
	}

	@Override
	public void construct(String[] s) {
		dict = new String[s.length];

		if(n!=0){
			t = new Trie();
			n = 0;
		}

		for(String str: s){
			if(!t.exists(str)){
				t.insert(str);
				dict[n++] = str;
			}
		}
	}

	@Override
	public String autocompletedWord(String s) {
		String str = "";
		Node<String> current = t.root();
		int length = s.length();

		// not constructed
		if(n==0){
			return "";
		}

		for(int i=0;i<length;i++){
			String key = s.substring(i, i+1);
			int idx = -1;
			boolean found = false;

			while(!found){
				idx = t.getPlace(current, key);

				if(idx!=-1 && key.equals(current.getKey(idx))){
					found = true;
					str += current.getKey(idx);
					current = current.getChild(idx);
				} else{
					str += current.getKey(0);
					current = current.getChild(0);
				}
			}
		}

		while(current.numKeys()==1){
			str += current.getKey(0);
			current = current.getChild(0);

			if(current.getLastChar()){
				break;
			}
		}

		return str;
	}

	@Override
	public float average() {
		if(n==0){
			return 0;
		}
		
		float cnt = 0;

		for(int i=0;i<n;i++){
			String str = dict[i];
			cnt += count(str);
		}
		cnt = cnt/n;
		cnt = Math.round(100.0f*cnt)/100.0f;

		return cnt;
	}

	private int count(String s){
		int res = 1;
		int length = s.length();
		Node<String> current = t.root();

		for(int i=0;i<length;i++){
			String key = s.substring(i, i+1);
			int idx = t.getPlace(current, key);
			current = current.getChild(idx);
		}

		while(true){
			// root
			if(current.getParent()==t.root()){
				break;
			} 

			if(current.getParent().numChildren()>1){
				res++;
			} else if(current.getParent().getLastChar()){
				res++;
			}

			current = current.getParent();
		}

		return res;
	}
}