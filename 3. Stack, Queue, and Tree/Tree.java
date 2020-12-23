/*
* Name: 정재은
* Student ID#: 2017122063
*/

/*
* Do NOT use any external packages/classes.
* If you (un)intentionally use them we did not provide,
* you will get 0.
* Also do NOT use auto-import function on IDEs.
* If the import statements change, you will also get 0.
*/

import java.util.List;
import java.util.ArrayList;

public final class Tree<E> implements ITree<E> {
	private int k; // max number of children
	private TreeNode<E> rootNode = null;
    private int n = 0; // # of nodes
    private int h = 0; // height

    public Tree(int arity) {
        /*
        * Input:
        *  + arity: max number of node's children. always positive.
        */
        k = arity;
    }

    @Override
    public TreeNode<E> root() throws IllegalStateException {
        /*
        * Return the root node.
        * If there is no root, raise an IllegalStateException.
        */
        if(rootNode == null)
        	throw new IllegalStateException("There is no root.");

        return rootNode;
    }

    @Override
    public int arity() {
        /*
        * Return the max number of children its node can have
        */
        return k;
    }

    private void getSize(){
        List<E> seq = depthOrder();
        n = seq.size();
    }

    @Override
    public int size() {
        /*
        * Return the number of nodes in this tree.
        */
        return n;
    }

    @Override
    public boolean isEmpty() {
        /*
        * Return true if this tree is empty.
        */
        return rootNode == null;
    }

    private void getHeight(TreeNode<E> currentNode, int res){
        if(currentNode == null)
            return;

        if(res > h)
            h = res;

        for(int i=0;i<currentNode.numChildren();i++){
            getHeight(currentNode.getChild(i), res+1);
        }
    }

    @Override
    public int height() throws IllegalStateException {
        /*
        * Return height of this tree.
        * If there are no nodes in this tree,
        * raise an IllegalStateException.
        */
        if(isEmpty())
        	throw new IllegalStateException("There are no nodes in this tree.");

        return h;
    }

    private TreeNode<E> getParentNode(TreeNode<E> currentNode, int level){
    	TreeNode<E> res = null;

    	if(level == h)
    		return null;

    	if(currentNode.numChildren() < k)
    		return currentNode;

    	for(int i=0;i<currentNode.numChildren();i++){
    		res = getParentNode(currentNode.getChild(i), level+1);
    		if(res != null)
    			break;
    	}

    	return res;
    }

    @Override
    public TreeNode<E> add(E item) {
        /*
        * Insert the given node at the *end* of this tree and
        * return THE inserted NODE.
        * *End* means that the leftmost possible slot of
        * smallest depth of this tree.
        */
        // System.out.println("Inserting... " + item);
        TreeNode<E> insertNode = new TreeNode<E>(k, item);
        TreeNode<E> parentNode;

        n++;
        // empty
        if(isEmpty()){
        	rootNode = insertNode;
        	return insertNode;
        } 

        // insert할 노드 찾으러감
        parentNode = getParentNode(rootNode,  0);
    	if(parentNode == null){
    		parentNode = rootNode;
    		for(int i=0;i<h;i++)
    			parentNode = parentNode.getChild(0);
            h++;
    	} 

        insertNode.setParent(parentNode);
       	parentNode.insertChild(parentNode.numChildren(), insertNode);

        return insertNode;
    }

    private TreeNode<E> search(TreeNode<E> currentNode, TreeNode<E> node){
        TreeNode<E> res= null;

        if(currentNode == node || currentNode == null)
    		return currentNode;

    	for(int i=0;i<currentNode.numChildren();i++){
    		res = search(currentNode.getChild(i), node);
    		if(res != null)
    			break;
    	}

    	return res;
    }

    @Override
    public void detach(TreeNode<E> node) throws IllegalArgumentException {
        /*
        * Detach the given node (and its descendants) from this tree.
        * if the node is not in this tree,
        * raise an IllegalArgumentException.
        */
        TreeNode<E> res = search(rootNode, node);

        if(res == null)
        	throw new IllegalArgumentException("Node doesn't exist.");

        if(res == rootNode){
        	rootNode = null;
        } else{
            TreeNode<E> par = res.getParent();
            int i;
            for(i=0;i<par.numChildren();i++){
                if(par.getChild(i) == res)
                    break;
            }

            try{
                par.removeChild(i);
            } catch(IndexOutOfBoundsException e){
                System.out.println("Unable to detach.");
                return;
            }
        }

        // update
        n = 0;
        h = 0;
        getSize();
        getHeight(rootNode, 0);
    }

    private void preHelp(TreeNode<E> node, List<E> seq){
    	seq.add(node.getValue());

    	for(int i=0;i<node.numChildren();i++){
    		preHelp(node.getChild(i), seq);
    	}
    }

    @Override
    public List<E> preOrder() {
        /*
        * Return the sequence of items visited by preorder traversal.
        * If there are no nodes, return an empty list, NOT NULL.
        */
        System.out.println("<Preorder Traversal>");
        List<E> seq = new ArrayList<>();

        if(isEmpty())
        	return seq;

        preHelp(rootNode, seq);

        return seq;
    }

    private void postHelp(TreeNode<E> node, List<E> seq){
    	for(int i=0;i<node.numChildren();i++){
    		postHelp(node.getChild(i), seq);
    	}

    	seq.add(node.getValue());
    }

    @Override
    public List<E> postOrder() {
        /*
        * Return the sequence of items visited by postorder traversal.
        * If there are no nodes, return an empty list, NOT NULL.
        */
        System.out.println("<Postorder Traversal>");
        List<E> seq = new ArrayList<>();

        if(isEmpty())
        	return seq;

        postHelp(rootNode, seq);

        return seq;
    }

    @Override
    public List<E> depthOrder() {
        /*
        * Return the sequence of items visited by depthorder traversal.
        * If there are no nodes, return an empty list, NOT NULL.
        */
        List<E> seq = new ArrayList<>();
        List<TreeNode<E>> helpSeq = new ArrayList<>();

        if(rootNode == null)
        	return seq;

        helpSeq.add(rootNode);
        while(!helpSeq.isEmpty()){
        	TreeNode<E> node = helpSeq.remove(0);
        	seq.add(node.getValue());
        	for(int i=0;i<node.numChildren();i++)
        		helpSeq.add(node.getChild(i));
        }

        return seq;
    }
}