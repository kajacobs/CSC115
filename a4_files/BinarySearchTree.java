/*
* Katherine Jacobs
* V00783178
* Last Modified Nov 22/18
* BinarySearchTree
* CSC115 Assignment #4
*/

import java.util.Iterator;


/**
 * BinarySearchTree is an ordered binary tree, where the element in each node
 * comes after all the elements in the left subtree rooted at that node
 * and before all the elements in the right subtree rooted at that node.
 * For this assignment, we can assume that there are no elements that are
 * identical in this tree.
 * A small modification will allow duplicates.
 */
public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> {

	// the root is inherited from BinaryTree.

	/**
	 * Create an empty BinarySearchTree.
	 */
	public BinarySearchTree() {
		super();
	}

	/**
	 * Creates a BinarySearchTree with a single item.
	 * @param item The single item in the tree.
	 */
	public BinarySearchTree(E item) {
		super(item);
	}

	/**
 	 * <b>This method is not allowed in a BinarySearchTree.</b>
	 * It's description from the subclass:<br>
	 * <br>
	 * {@inheritDoc}
	 * @throws UnsupportedOperationException if this method is invoked.
	 */
	public void attachLeftSubtree(BinaryTree<E> left) {
		throw new UnsupportedOperationException();
	}

	public void attachRightSubtree(BinaryTree<E> right) {
		throw new UnsupportedOperationException();
	}

	/*
	* Initial insert method called from the main methods
	*/
	public void insert(E item) {
		root = recursiveInsert(root, item);
	}

	/*
	* Private recursive insert method
	*/
	private TreeNode<E> recursiveInsert(TreeNode<E> root, E newItem){
		if (root == null){
			return new TreeNode<E>(newItem);
		}
		if (newItem.compareTo(root.item) < 0){
			root.left = recursiveInsert(root.left, newItem);
		} else {
			root.right = recursiveInsert(root.right, newItem);
		}
		return root;
	}

	public E retrieve(E item){
		TreeNode<E> temp = root;
		while (temp != null){
			if (item.compareTo(temp.item) == 0){
				return temp.item;
			} else if (item.compareTo(temp.item) < 0){
				temp = temp.left;
			} else if (item.compareTo(temp.item) > 0 ){
				temp = temp.right;
			}
		}
		return null;
	}

	/*
	* Initial delete method that is called from the main methods
	*/

	public E delete(E item) {
		root = recursiveDelete(root, item);
		return item;
	}

	/*
	* Private recursive method for deleting a TreeNode
	*/
	private TreeNode<E> recursiveDelete(TreeNode<E> root, E newItem){
		if (root == null){
			throw new InvalidExpressionException();
		}

		if (newItem.compareTo(root.item) < 0){
			root.left = recursiveDelete(root.left, newItem);
		} else if (newItem.compareTo(root.item) > 0){
			root.right = recursiveDelete(root.right, newItem);
		} else {
				if (root.left == null){
					return root.right;
				} else if (root.right == null){
					return root.left;
				} else {
					root.item = getItem(root.left);
					root.left = recursiveDelete(root.left, root.item);
				}
		}
		return root;
		}

		/*
		* Helper Method that finds right-most getItem
		*/
		private E getItem(TreeNode<E> root){
			while (root.right !=null){
				root = root.right;
			}
			return root.item;
		}

	/**
	 * Internal test harness.
	 * @param args Not used.
	 */
	public static void main(String[] args) {


		System.out.println("Testing Binary Search Tree With numbers");
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		Integer a1 = new Integer(1);
		tree.insert(a1);
		Integer a2 = new Integer(-10);
		tree.insert(a2);
		Integer a3 = new Integer(100);
		tree.insert(a3);
		Integer a4 = new Integer(50);
		tree.insert(a4);
		Integer a5 = new Integer(2);
		tree.insert(a5);
		Integer a6 = new Integer(-35);
		tree.insert(a6);
		Integer a7 = new Integer(5000);
		tree.insert(a7);
		System.out.println(tree.retrieve(1));
		System.out.println(tree.retrieve(-10));
		System.out.println(tree.delete(100));

		System.out.println("----Testing BinarySearchTree Insert & Constructor----");
		System.out.println();

		BinarySearchTree<String> tree1 = new BinarySearchTree<String>();
		if(tree1.root == null){
			System.out.println("BST Constructor: Pass");
		}

		String s1 = new String("optimal");
		String s2 = new String("needs");
		String s3 = new String("programmers");
		String s4 = new String("CSC115");

		tree1.insert(s1);
		tree1.insert(s2);
		tree1.insert(s3);
		tree1.insert(s4);

		if(tree1.root.equals("optimal")){
			System.out.println("BST Constructor: Pass");
		}

		BinarySearchTree<String> tree2 = new BinarySearchTree<String>();
		String s5 = new String("name");
		String s6 = new String("date");
		String s7 = new String("class");
		String s8 = new String("year");

		tree2.insert(s5);
		tree2.insert(s6);
		tree2.insert(s7);
		tree2.insert(s8);

		BinarySearchTree<String> tree3 = new BinarySearchTree<String>();
		String s9 = new String("number");
		String s10 = new String("letter");
		String s11= new String("symbol");
		String s12 = new String("object");

		tree3.insert(s9);
		tree3.insert(s10);
		tree3.insert(s11);
		tree3.insert(s12);

		BinarySearchTree<String> tree4 = new BinarySearchTree<String>();
		String s13 = new String("one");
		String s14 = new String("two");
		String s15 = new String("three");
		String s16 = new String("four");

		tree4.insert(s13);
		tree4.insert(s14);
		tree4.insert(s15);
		tree4.insert(s16);


		System.out.println("----Testing BinaryTree ----\n");

		BinaryTree<String> test1 = new BinaryTree<String>("Testing");
		BinaryTree<String> test2 = new BinaryTree<String>("Computers");
		BinaryTree<String> test3 = new BinaryTree<String>("Java");
		BinaryTree<String> test4 = new BinaryTree<String>("Y-Tho");
		BinaryTree<String> test5 = new BinaryTree<String>("Am I done yet?");

		if (test1.root.item.equals("Testing")){
		System.out.println("BinaryTree Constructor: Pass\n");
		}

		System.out.println("----Testing BinaryTree attachRightSubtree----\n");
		test1.attachRightSubtree(test2);
		test2.attachRightSubtree(test3);
		test3.attachRightSubtree(test4);
		test4.attachRightSubtree(test5);

		Iterator<String> bintree = test1.preorderIterator();
		System.out.println("printing out the contents of the tree in pre-order\n");
		while(bintree.hasNext()){
			System.out.print(bintree.next()+" ");
		}
		System.out.println();

		System.out.println("\n----Testing BinaryTree Height Method----");
		int height = test1.height();
		System.out.println(height);

		/*
		* tested height by using drawable tree to confirm height.
		*
		*DrawableBTree<String> tt = new DrawableBTree<String>(test1);
		*tt.showFrame();
		*/

	//	System.out.println("\n----End of BinaryTree Tests----\n");

		System.out.println("----Testing BinaryTreeIterator----\n");


		Iterator<String> y1 = tree1.preorderIterator();
		System.out.println("--Printing out the contents of the tree1 in pre-order--");
		while(y1.hasNext()){
			System.out.print(y1.next()+" ");
		}
			System.out.println();

		System.out.println("\n----Testing InorderIterators----\n");

		Iterator<String> t1 = tree1.inorderIterator();
		System.out.println("--Printing out the contents of the tree1 in order--\n");
		while(t1.hasNext()){
			System.out.print(t1.next()+" ");
		}
		System.out.println();

		Iterator<String> t2 = tree2.inorderIterator();
		System.out.println("--Printing out the contents of the tree2 in order:--\n");
		while (t2.hasNext()) {
			System.out.print(t2.next()+" ");
		}
		System.out.print("\n");

		Iterator<String> t3 = tree3.inorderIterator();
		System.out.println("--Printing out the contents of the tree3 in order:--\n");
		while (t3.hasNext()) {
			System.out.print(t3.next()+" ");
		}
		System.out.print("\n");

		Iterator<String> t4 = tree4.inorderIterator();
		System.out.println("--Printing out the contents of the tree in order:--\n");
		while (t4.hasNext()) {
			System.out.print(t4.next()+" ");
		}
		System.out.print("\n");

		System.out.println("\n----Testing PostorderIterators----\n");
		System.out.println();

		Iterator<String> n1 = tree1.postorderIterator();
		System.out.println("printing out the contents of the tree1 in post-order:\n");
		while (n1.hasNext()){
			System.out.print(n1.next()+" ");
		}
		System.out.print("\n");

		Iterator<String> n2 = tree2.postorderIterator();
		System.out.println("--Printing out the contents of the tree2 in post-order:--\n");
		while (n2.hasNext()){
			System.out.print(n2.next()+" ");
		}
		System.out.print("\n");

		Iterator<String> n3 = tree3.postorderIterator();
		System.out.println("--Printing out the contents of the tree3 in post-order:--\n");
		while (n3.hasNext()){
			System.out.print(n3.next()+" ");

		}
		System.out.print("\n");

		Iterator<String> n4 = tree4.postorderIterator();
		System.out.println("printing out the contents of the tree4 in post-order:\n");
		while (n4.hasNext()){
			System.out.print(n4.next()+" ");
		}
		System.out.print("\n");

		System.out.println("\n---Testing BST Retrieve---");

		String test = tree1.retrieve("needs");
		if (test != null && !test.equals("")) {
			System.out.println("retrieving the node that contains "+s2);
			if (test.equals(s2)) {
				System.out.println("Confirmed");
			} else {
				System.out.println("retrieve returns the wrong item");
			}
		} else {
			System.out.println("retrieve returns nothing when it should not");
		}

		System.out.println("--Testing BST attachRightSubtree---");
		try{
		tree1.attachRightSubtree(test1);
	} catch(Exception e){
		System.out.println("You cannot attach a Right subtree");
	}

	System.out.println();
	System.out.println("---Testing Delete ---");
	tree1.delete(s4);
	Iterator<String> p1 = tree1.inorderIterator();
	System.out.println("--Printing out the contents of the tree1 in order after delete:--\n");
	while (p1.hasNext()) {
		System.out.print(p1.next()+" ");
	}
	System.out.print("\n");

	tree2.delete(s6);
	Iterator<String> p2 = tree2.inorderIterator();
	System.out.println("--Printing out the contents of the tree2 in order after delete:--\n");
	while (p2.hasNext()) {
		System.out.print(p2.next()+" ");
	}
	System.out.print("\n");

	tree3.delete(s10);
	Iterator<String> p3 = tree3.inorderIterator();
	System.out.println("--Printing out the contents of the tree3 in order after delete:--\n");
	while (p3.hasNext()) {
		System.out.print(p3.next()+" ");
	}
	System.out.print("\n");

	tree4.delete(s15);
	Iterator<String> p4 = tree4.inorderIterator();
	System.out.println("--Printing out the contents of the tree4 in order after delete:--\n");
	while (p4.hasNext()) {
		System.out.print(p4.next()+" ");
	}
	System.out.print("\n");


		DrawableBTree<String> dbt = new DrawableBTree<String>(tree1);
		dbt.showFrame();
	} // end of main method
} // end of BinarySearchTree
