package assignment3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class CatCafe implements Iterable<Cat> {
	public CatNode root;

	public CatCafe() {
	}

	public CatCafe(CatNode dNode) {
		this.root = dNode;
	}

	// Constructor that makes a shallow copy of a CatCafe
	// New CatNode objects, but same Cat objects
	public CatCafe(CatCafe cafe) {
		/*
		 * TODO: ADD YOUR CODE HERE
		 */
		if (cafe.root == null) {
			this.root = null;
		}else{
			this.root = copyTree(cafe.root);
		}
	}
	//helper method
	private CatNode copyTree(CatNode root){
		if(root == null){
			return null;
		}else{
			CatNode rootCopy = new CatNode(root.catEmployee);
			rootCopy.parent = root.parent;
			rootCopy.junior = copyTree(root.junior);
			rootCopy.senior = copyTree(root.senior);
			return rootCopy;
		}
	}


	// add a cat to the cafe database
	public void hire(Cat c) {
		if (root == null) 
			root = new CatNode(c);
		else
			root = root.hire(c);
	}

	// removes a specific cat from the cafe database
	public void retire(Cat c) {
		if (root != null)
			root = root.retire(c);
	}

	// get the oldest hire in the cafe
	public Cat findMostSenior() {
		if (root == null)
			return null;

		return root.findMostSenior();
	}

	// get the newest hire in the cafe
	public Cat findMostJunior() {
		if (root == null)
			return null;

		return root.findMostJunior();
	}

	// returns a list of cats containing the top numOfCatsToHonor cats 
	// in the cafe with the thickest fur. Cats are sorted in descending 
	// order based on their fur thickness. 
	public ArrayList<Cat> buildHallOfFame(int numOfCatsToHonor) {
		/*
		 * TODO: ADD YOUR CODE HERE
		 */
		return null;
	}

	// Returns the expected grooming cost the cafe has to incur in the next numDays days
	public double budgetGroomingExpenses(int numDays) {
		/*
		 * TODO: ADD YOUR CODE HERE
		 */
		return 0;
	}

	// returns a list of list of Cats. 
	// The cats in the list at index 0 need be groomed in the next week. 
	// The cats in the list at index i need to be groomed in i weeks. 
	// Cats in each sublist are listed in from most senior to most junior. 
	public ArrayList<ArrayList<Cat>> getGroomingSchedule() {
		/*
		 * TODO: ADD YOUR CODE HERE
		 */
		return null;
	}


	public Iterator<Cat> iterator() {
		return new CatCafeIterator();
	}


	public class CatNode {
		public Cat catEmployee;
		public CatNode junior;
		public CatNode senior;
		public CatNode parent;

		public CatNode(Cat c) {
			this.catEmployee = c;
			this.junior = null;
			this.senior = null;
			this.parent = null;
		}

		// add the c to the tree rooted at this and returns the root of the resulting tree
		public CatNode hire (Cat c) {
			/*
			 * TODO: ADD YOUR CODE HERE
			 */
//			if (root == null){
//				root = new CatNode(c);
//			}
			if (c.compareTo(root.catEmployee)<0){
				root = add(root,c);

				if (root.junior.catEmployee.getFurThickness() > root.catEmployee.getFurThickness()){
					root = RightRotate(root);
				}
			}else{
				root = add(root,c);
				if (root.senior.catEmployee.getFurThickness() > root.catEmployee.getFurThickness()){
					root = LeftRotate(root);
				}
			}
			return root;
		}

		//helper
		private CatNode add(CatNode newRoot,Cat c){

			if (newRoot == null){
				return new CatNode(c);
			}

			if (c.compareTo(newRoot.catEmployee)<0) {
				CatNode leftChild = add(newRoot.junior,c);
				newRoot.junior = leftChild;
				leftChild.parent = newRoot;

			} else if (c.compareTo(newRoot.catEmployee)>0) {
				CatNode rightChild = add(newRoot.senior,c);
				newRoot.senior = rightChild;
				rightChild.parent = newRoot;
			}
			return newRoot;
		}

		private CatNode RightRotate(CatNode parent){
			CatNode leftChild = parent.junior;

			parent.junior = leftChild.senior; // B becomes left child of parent
			leftChild.senior = parent;

			return leftChild; // return left child
		}

		private CatNode LeftRotate(CatNode parent){
			CatNode rightChild = parent.senior;

			parent.senior = rightChild.junior; // A becomes right child of parent
			rightChild.senior = parent;

			return rightChild; // return right child
		}

		// remove c from the tree rooted at this and returns the root of the resulting tree
		public CatNode retire(Cat c) {
			/*
			 * TODO: ADD YOUR CODE HERE**
			 */
			return null;
		}

		// find the cat with highest seniority in the tree rooted at this
		public Cat findMostSenior(){
			/*
			 * TODO: ADD YOUR CODE HERE
			 */
			CatNode mostSenior = root;

			if (mostSenior == null){
				return null;
			}
			while(mostSenior.senior != null){
				mostSenior = mostSenior.senior;
			}
			return mostSenior.catEmployee;
		}

		// find the cat with lowest seniority in the tree rooted at this
		public Cat findMostJunior() {
			/*
			 * TODO: ADD YOUR CODE HERE
			 */
			CatNode mostJunior = root;

			if (mostJunior == null){
				return null;
			}
			while(mostJunior.junior != null){
				mostJunior = mostJunior.senior;
			}
			return mostJunior.catEmployee;
		}

		// Feel free to modify the toString() method if you'd like to see something else displayed.
		public String toString() {
			String result = this.catEmployee.toString() + "\n";
			if (this.junior != null) {
				result += "junior than " + this.catEmployee.toString() + " :\n";
				result += this.junior.toString();
			}
			if (this.senior != null) {
				result += "senior than " + this.catEmployee.toString() + " :\n";
				result += this.senior.toString();
			} /*
			if (this.parent != null) {
				result += "parent of " + this.catEmployee.toString() + " :\n";
				result += this.parent.catEmployee.toString() +"\n";
			}*/
			return result;
		}
	}


	private class CatCafeIterator implements Iterator<Cat> {
		// HERE YOU CAN ADD THE FIELDS YOU NEED

		ArrayList<CatNode> s; // the stack to store cat node

//		ArrayList<Cat> catStack; //the stack to store cat
		CatNode current;
		private CatCafeIterator() {
			/*
			 * TODO: ADD YOUR CODE HERE
			 */
			this.s = new ArrayList<>();

//			this.catStack = new ArrayList<>();

			this.current = root;

//			this.mostLeftNode(root); // to generate the stack
		}

		// helper method
//		private void mostLeftNode(CatNode cur){
//			while (cur != null){
//				this.s.add(cur); // push()
//				cur = cur.junior;
//			}
//		}

		public Cat next(){
			/*
			 * TODO: ADD YOUR CODE HERE
			 */

			// access the cats in ascending order of seniority

//			if (s.isEmpty()){
//				throw new NoSuchElementException("No more cats to iterate on");
//			}else{
//				CatNode currentMin = s.get(s.size() - 1); // current min is the top of the stack
//				s.remove(s.size()-1); // s.pop()
//
//				if (currentMin.senior != null){ // if current min has right subtree
//					this.mostLeftNode(currentMin.senior);
//				}
//				return currentMin.catEmployee;
//			}

			if (!hasNext()){
				throw new NoSuchElementException("No more cats to iterate on");
			}else{
				while (current != null){
					this.s.add(current); // push()
					current = current.junior;
				}
				current = s.get(s.size() -1 ); // pop()
				s.remove(s.size() -1);

				Cat nextCat = current.catEmployee;
				current = current.senior;
				return nextCat;
			}
//			if (!hasNext()){
//				throw new NoSuchElementException("No more cats to iterate on");
//			}else{
//				while (current != null){
//					this.catStack.add(current.catEmployee); // = push()
//					current = current.junior;
//				}
//				Cat nextCat = catStack.get(catStack.size() - 1); // = pop()
//				catStack.remove(catStack.size() - 1);
//
//				current = current.senior;
//				return nextCat;
//			}

		}

		public boolean hasNext(){
			/*
			 * TODO: ADD YOUR CODE HERE
			 */
			if (s.isEmpty() || current == null){ // if stack is empty -> no more cats to iterate
				return false;
			}
			return true;
		}
//			return !catStack.isEmpty() || current != null;
//		}
	}

	public static void main(String[] args) {
		Cat B = new Cat("Buttercup", 45, 53, 5, 85.0);
		Cat C = new Cat("Chessur", 8, 23, 2, 250.0);
		Cat J = new Cat("Jonesy", 0, 21, 12, 30.0);	
		Cat JJ = new Cat("JIJI", 156, 17, 1, 30.0);
		Cat JTO = new Cat("J. Thomas O'Malley", 21, 10, 9, 20.0);
		Cat MrB = new Cat("Mr. Bigglesworth", 71, 0, 31, 55.0);
		Cat MrsN = new Cat("Mrs. Norris", 100, 68, 15, 115.0);
		Cat T = new Cat("Toulouse", 180, 37, 14, 25.0);


		Cat BC = new Cat("Blofeld's cat", 6, 72, 18, 120.0);
		Cat L = new Cat("Lucifer", 10, 44, 20, 50.0);

	}

	// helper to print the tree -----------------------------------------------------------------------
	public void printTree(CatNode root)
	{
		//Thanks, GeeksForGeeks!:D
		printTree(root, 0);
		System.out.println("\n\n----------------------------------------------------------\n\n");

	}
	private void printTree(CatNode root, int spaceCount)
	{
		if(root==null)
			return;

		int spacing = spaceCount+20;

		printTree(root.senior, spacing);


		System.out.println();
		for(int index=0; index < spacing; index++)
			System.out.print(" ");
		System.out.println(root.catEmployee);

		printTree(root.junior, spacing);
	}

	// tester to check iterator
	private static void iteratorTest() {
		Cat B = new Cat("Buttercup", 45, 53, 5, 85.0);
		Cat C = new Cat("Chessur", 8, 23, 2, 250.0);
		Cat J = new Cat("Jonesy", 0, 21, 12, 30.0);
		Cat JJ = new Cat("JIJI", 156, 17, 1, 30.0);
		CatCafe Cafe = new CatCafe();
		Cafe.hire(B);Cafe.hire(C);Cafe.hire(J);Cafe.hire(JJ);
		Iterator itr = Cafe.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next().toString());
		}
		try {
			System.out.println(itr.next().toString());
		}
		catch (NoSuchElementException e) {
			System.out.println("No such element exists. The previous element has a next cat: "+itr.hasNext());
		}
	}

}


