
public class Main {
	public static void main(String[] args) {
		// checkStack(100);
		// printLine();
		// checkLISP("[{}((()))]");
		// printLine();
		// checkCalc("3 5 + 4 2 + *");
		checkCafe();
		// printLine();
		checkTree(3, 41);
	}

	public static void checkStack(int num){
		IStack<Integer> stack = new Stack<>();
		System.out.println("*** Checking Stack ***");
		for(int i=1;i<=num;i++){
			System.out.println("Pushing " + i);
			stack.push(i);
		}
		System.out.println("Stack size: " + stack.size());

		for(int i=1;i<=num;i++){
			System.out.println("Popping " + stack.pop());
			System.out.println("Stack size: " + stack.size());
		}

		try{
			stack.pop();
		} catch(IllegalStateException e){
			System.out.println(e.getMessage());
		}

	}

	public static void checkLISP(String exp){
		ILISP lisp = new LISP();
		System.out.println("*** Checking LISP ***");
		System.out.println(exp + " : " + lisp.checkBracketBalance(exp));
	}

	public static void checkCalc(String exp){
		ICalc calc = new PostfixCalc();
		System.out.println("*** Checking PostfixCalc ***");
		System.out.println(exp + " = " + calc.evaluate(exp));		
	}

	public static void checkCafe(){
		System.out.println("*** Checking Cafe ***");
		ICafe cafe = new Cafe();
		// cafe.arrive(1, 0, 5);
		// cafe.arrive(2, 0, 1);
		// cafe.arrive(3, 4, 2);

		// cafe.serve();
		// cafe.serve();
		// cafe.serve();
		// System.out.println(cafe.stat()); // 1:2 -> 5:
		// cafe.arrive(1, 0, 10);
		// cafe.arrive(2, 0, 9);
		// cafe.serve();
		// cafe.arrive(3, 9, 8);
		// cafe.arrive(4, 10, 4); //
		// cafe.arrive(5, 10, 5); //
		// cafe.serve();
		// cafe.serve();
		// cafe.serve();
		// cafe.serve();

		// cafe.arrive(1, 0, 3);
		// cafe.arrive(2, 0, 2);
		// cafe.arrive(3, 0, 1);

		// cafe.arrive(4, 1, 3);
		// cafe.arrive(5, 1, 2);
		// cafe.arrive(6, 1, 1);

		// for(int i=0;i<6;i++){
		// 	cafe.serve();
		// }
		// cafe.stat();

  //       cafe.arrive(1, 0, 3);
  //       cafe.arrive(2, 1, 1);
  //       cafe.arrive(3, 1, 2);

  //       System.out.println("serving: " + cafe.serve());
  //       System.out.println("stat: " + cafe.stat());

  //       cafe.arrive(4, 2, 2);

  //       System.out.println("serving: " + cafe.serve());
  //       System.out.println("stat: " + cafe.stat());
		// System.out.println("serving: " + cafe.serve());
  //       System.out.println("stat: " + cafe.stat());
  //       System.out.println("serving: " + cafe.serve());
  //       System.out.println("stat: " + cafe.stat());

		cafe.arrive(1, 5, 3);
		cafe.arrive(2, 7, 1);
		System.out.println(cafe.serve());
		System.out.println(cafe.serve());
		System.out.println(cafe.stat());


	}

	public static void checkTree(int arity, int num){
		System.out.println("*** Checking Tree ***");
		ITree<Integer> tree = new Tree<>(arity);
		System.out.println("tree's arity: " + tree.arity());
		tree.add(2);
		tree.add(4);
		tree.add(3);
		tree.add(7);
		tree.add(10);
		tree.add(15);
		tree.add(13);
		tree.add(5);
		tree.add(1);
		System.out.println(tree.depthOrder());
		tree.detach(tree.root().getChild(0));
		tree.add(8);
		System.out.println(tree.depthOrder());
		System.out.println(tree.height());
  //       for(int i=1;i<=num;i++)
  //       	tree.add(i);
		// System.out.println(tree.preOrder());
		// System.out.println(tree.postOrder());
		// System.out.println("<Depthorder Traversal>");
		// System.out.println(tree.depthOrder());
		// System.out.println("tree's size: " + tree.size());
		// System.out.println("tree's height: " + tree.height());
		// tree.detach(tree.root().getChild(1));
  //       System.out.println("isEmpty: " + tree.isEmpty());
  //       System.out.println("tree's size: " + tree.size());
  //       System.out.println("tree's height: " + tree.height());

		// tree.add(2);
  //       tree.add(4);
  //       tree.add(3);
  //       System.out.println(tree.preOrder());
  //       tree.add(7);
  //       System.out.println(tree.postOrder());
  //       System.out.println("<Depthorder Traversal>");
  //       System.out.println(tree.depthOrder());
  //       System.out.println("size: " + tree.size());
  //       tree.add(10);
  //       tree.add(15);
  //       tree.add(16);
  //       tree.add(20);
  //       tree.add(21);
  //       tree.add(22);
  //       tree.add(23);
  //       tree.add(24);
  //       tree.add(25);
  //       System.out.println("size: " + tree.size());
  //       System.out.println("isEmpty: " + tree.isEmpty());

  //       TreeNode<Integer> root = tree.root();
  //       TreeNode<Integer> node = root.getChild(0);

  //       System.out.println("node: " + node.getValue());
  //       tree.detach(node);
  //       node = node.getChild(1);
  //       System.out.println("node: " + node.getValue());
        
  //       try{
  //       	root.getChild(1);
  //       } catch(IndexOutOfBoundsException e){
  //       	System.out.println(e.getMessage());
  //       }

  //       node = root.getChild(0);
  //       System.out.println("node: " + node.getValue());

  //       System.out.println(tree.depthOrder());
  //       System.out.println("tree's height: " + tree.height());

	}

	public static void printLine(){
		for(int i=0;i<50;i++)
			System.out.print("=");
		System.out.println();
	}
}