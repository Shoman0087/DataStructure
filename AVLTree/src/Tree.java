import javax.sound.sampled.Port;

public class Tree {
	Node root;
	
	public Tree() {
		// TODO Auto-generated constructor stub
		root = null;
	}
	
	public Node getRoot() {
		return root;
	}
	
	public Node insert(Integer data,Node currentRoot) {
		if (currentRoot == null) {
			currentRoot = new Node(data);			
		}
		else {
			if (currentRoot.getData() > data) {
				
				currentRoot.left = insert(data,currentRoot.left);		
			}
			else {
				currentRoot.right = insert(data,currentRoot.right);
			}
		
		}

		return balance(currentRoot);
	}
	
	 void printLevelOrder()
	    {
	        int h = height(root);
	        int i;
	        for (i=1; i<=h; i++) {
	            printGivenLevel(root, i);
	            System.out.println();
	        }
	    }
	 
	 void printGivenLevel (Node root ,int level)
	    {
	        if (root == null)
	            return;
	        if (level == 1)
	            System.out.print(root.data + " ");
	        else if (level > 1)
	        {
	            printGivenLevel(root.left, level-1);
	            printGivenLevel(root.right, level-1);
	        }
	    }
	
	public int height(Node root) {
		if (root == null)
			return 0;
		else 
			return Math.max(height(root.left), height(root.right)) + 1;
	}
	
	public Node balance(Node root) {
		if (root == null)
			return root;
		
		if (height(root.left) - height(root.right) > 1) {
			if (height(root.left.left) >= height(root.left.right) )
				root = singleRotateRight(root);
			else 
				root = doubleRotateRight(root);
		} else if (height(root.right) - height(root.left) > 1) {
		//	System.out.println(root.data + " asasa");
			if (height(root.right.right) >= height(root.right.left)) {
				root = singleRotateLeft(root);
			}
			else {
				root = doubleRotateLeft(root); 
			}
		}
			
		return root;
		
	}
	
	public Node singleRotateRight(Node root) {
		Node k2 = root.left;
		root.left = k2.right;
		k2.right = root;
		return k2;
		
	}
	
	public Node doubleRotateLeft(Node root) {
		root.right = singleRotateRight(root.right);
		return singleRotateLeft(root);
	}
	
	public Node singleRotateLeft(Node root) {
		Node k2 = root.right;
		root.right = k2.left;
		k2.left = root;
		return k2;
		
	}
	
	public Node doubleRotateRight(Node root) {
		root.left = singleRotateLeft(root.left);
		return singleRotateRight(root);
	}
	
	public void inorder(Node curr) {
		if (curr == null)
			return ;
		inorder(curr.left);
		System.out.print(curr.getData().toString() + "   ");
		inorder(curr.right);
	}
	
	public void preorder(Node curr) {
		if (curr == null)
			return ;
		System.out.print(curr.getData().toString() + "   ");
		preorder(curr.left);
		preorder(curr.right);
	}
	
	public void postorder(Node curr) {
		if (curr == null)
			return ;
		postorder(curr.left);
		postorder(curr.right);
		System.out.print(curr.getData().toString() + "   ");
	}
	
	public  boolean isBalanced(Node root){
		if(root==null)
			return true;
		int heightdifference = height(root.left) - height(root.right);
		if(Math.abs(heightdifference)>1){
			return false;
		}else{
			return isBalanced(root.left) && isBalanced(root.right);
		}
	}
	
	public Node find(Integer data,Node curr) {
		if (curr == null)
			return curr;
		else {
			if (data.compareTo(curr.getData()) > 0)
				return find(data,curr.right);
			else if (data.compareTo(curr.getData()) < 0)
				return find(data,curr.left);
			else 
				return curr;
		}
	}
	
	public Node findMin(Node curr) {
		if (curr.left == null)
			return curr;
		else 
			return findMin(curr.left);
	}
	
	public Node findMax(Node curr) {
		if (curr.right == null)
			return curr;
		else 
			return findMin(curr.right);
	}
	
	public Node delete(Integer data,Node curr) {
		if (curr == null)
			return null;
		if (data.compareTo(curr.getData()) > 0)
			curr.right = delete(data,curr.right);
		else if (data.compareTo(curr.getData()) < 0)
			curr.left = delete(data,curr.left);
		else if (curr.left != null && curr.right != null) {
			curr.setData(findMin(curr.right).getData());		
			curr.right = delete(curr.getData(),curr.right);
		} else 
			curr = (curr.left == null) ? curr.right : curr.left;
		return balance(curr);
	}
	
	public int numOfLeaves(Node root) {
		if (root == null)
			return 0;
		if (root.left == null && root.right == null)
			return 1;
		return numOfLeaves(root.left) + numOfLeaves(root.right);
	}
	
	public int numOfNodes(Node root) {
		if (root == null)
			return 0;

		return 1 + numOfNodes(root.left) + numOfNodes(root.right);
	}
	
	
	public void printAllPathes() {
		int[] arr = new int[20];
		printAllPathes(root,arr,0);
	}
	
	public void  printAllPathes(Node root,int path[],int pathLen) {
		
		if (root == null)
			return ;
		path[pathLen] = root.data;
		pathLen++;
		
		if (root.left == null && root.right == null) {
			printArray(path,pathLen);
		} else {
			printAllPathes(root.left , path,pathLen);
			printAllPathes(root.right , path,pathLen);
		}
		
		
	}
	
	
	
	public int numOfLeavsAtLevel(int level,Node root) {
		if (root == null)
			return 0;
		if (level == 1)
			return 1;
		return numOfLeavsAtLevel(level-1, root.left) + numOfLeavsAtLevel(level-1, root.right);
		
	}
	
	public void printArray(int[] arr,int len) {
		for (int i = 0 ; i < len ; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

}
