/*
 * By : Mohammad Shoman
 */
public class Driver {
	
	public static void main(String[] args) {
		Tree tree = new Tree();
		
		tree.root = tree.insert(30, tree.root);
		tree.root = tree.insert(5, tree.root);
		tree.root = tree.insert(20, tree.root);
		tree.root = tree.insert(12, tree.root);
		tree.root = tree.insert(33, tree.root);
		tree.root = tree.insert(32, tree.root);
		tree.root = tree.insert(34, tree.root);
		tree.root = tree.insert(1, tree.root);
		tree.root = tree.insert(9, tree.root);
		tree.root = tree.insert(44, tree.root);
		tree.root = tree.insert(37, tree.root);
		tree.root = tree.insert(18, tree.root);
		tree.root = tree.insert(90, tree.root);

		

		System.out.println("inorder > ");
		tree.inorder(tree.root);
		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println("preorder > ");
		tree.preorder(tree.root);
		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println("postorder > ");
		tree.postorder(tree.root);
		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println("level order > ");
		tree.printLevelOrder();
		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println("is AVL  ? > ");
		System.out.print(tree.isBalanced(tree.root));
		System.out.println();
		System.out.println("--------------------------------------");
		tree.printAllPathes();
		System.out.println();
		System.out.println(tree.numOfLeaves(tree.root));
		System.out.println(tree.numOfNodes(tree.root));
		

	}

}
