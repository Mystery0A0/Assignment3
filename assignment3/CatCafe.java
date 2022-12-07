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
	public ArrayList<Cat> buildHallOfFame(int numOfCatsToHonor) { // NONONONONONNONONONO
		/*
		 * TODO: ADD YOUR CODE HERE
		 */
//		ArrayList<Cat> hallOfFame = new ArrayList<>();
//		ArrayList<CatNode> queue = new ArrayList<>();
//
//		queue.add(root); // enqueue the root
//
//
//		while (!queue.isEmpty() || hallOfFame.size()!= numOfCatsToHonor){
//			CatNode cur = queue.get(0);
//			queue.remove(0); // dequeue
//
//			hallOfFame.add(cur.catEmployee); // visit cur
//
//			if (cur.junior != null) {
//				if (cur.senior != null && cur.junior.catEmployee.getFurThickness() > cur.senior.catEmployee.getFurThickness()) {
//					queue.add(cur.junior); // enqueue the bigger child
//					queue.add(cur.senior); // enqueue the smaller child
//				}else if (cur.senior == null) {
//					queue.add(cur.junior);
//				}else{ // cur.junior.catEmployee.getFurThickness() < cur.senior.catEmployee.getFurThickness())
//					queue.add(cur.senior);
//					queue.add(cur.junior);
//				}
//			}else{ // if cur.junior == null
//				if (cur.senior != null){
//					queue.add(cur.senior);
//				}// both children are null
//			}
////			if (cur.senior != null){
////				if (cur.junior != null && cur.senior.catEmployee.getFurThickness() > cur.junior.catEmployee.getFurThickness()){
////					queue.add(cur.senior);
////				}
////			}
//
//		}

//******************************************************************************************
//		ArrayList<Cat> hallOfFame = new ArrayList<>();
//		ArrayList<Cat> allCats = new ArrayList<>();
//		CatCafeIterator it = new CatCafeIterator();
//
//		while (it.hasNext()){ // store all cats
//			allCats.add(it.next());
//		}
//		int furThickest = -1;
//
//		for (int i = 0; i < allCats.size() ; i++) { // find the thickest fur
//			if (allCats.get(i).getFurThickness() > furThickest){
//				furThickest = allCats.get(i).getFurThickness();
//			}
//		}
//
//		for (int i = furThickest; i <= 0 ; i--) {
//			for (int j = 0; j < allCats.size(); j++) {
//				if (allCats.get(j).getFurThickness() == i){
//					hallOfFame.add(allCats.get(j));
//				}
//				if (hallOfFame.size() == numOfCatsToHonor){
//					break;
//				}
//			}
//		}
//		return hallOfFame;
// *********************************************************************
		ArrayList<Cat> hallOfFame = new ArrayList<>();
		CatCafe copy = new CatCafe(this);

		while(copy.root!= null || hallOfFame.size() != numOfCatsToHonor){
			hallOfFame.add(root.catEmployee);
			copy.retire(root.catEmployee);
		}
		return hallOfFame;
	}

	// Returns the expected grooming cost the cafe has to incur in the next numDays days
	public double budgetGroomingExpenses(int numDays) {
		/*
		 * TODO: ADD YOUR CODE HERE
		 */
		ArrayList<Cat> allCats = new ArrayList<>();
		ArrayList<Cat> groomingCats = new ArrayList<>();
		double expense = 0;

		CatCafeIterator it = new CatCafeIterator();
// add all cats into an arraylist
		while (it.hasNext()){
			allCats.add(it.next());
		}
//add grooming cats into an arraylist
		for (Cat c:allCats) {
			if (c.getDaysToNextGrooming() <= numDays){
				groomingCats.add(c);
			}
		}
//add up the total cost of grooming cost
		for (Cat c:groomingCats) {
			expense = expense + c.getExpectedGroomingCost();
		}

		return expense;
	}

	// returns a list of list of Cats. 
	// The cats in the list at index 0 need be groomed in the next week. 
	// The cats in the list at index i need to be groomed in i weeks. 
	// Cats in each sublist are listed in from most senior to most junior. 
	public ArrayList<ArrayList<Cat>> getGroomingSchedule() {
		/*
		 * TODO: ADD YOUR CODE HERE
		 */
		ArrayList<ArrayList<Cat>> groomingSchedule = new ArrayList<>();
//		ArrayList<Cat> catsNeedGrooming = new ArrayList<>();
		ArrayList<Cat> allCats = new ArrayList<>();

		CatCafeIterator it = new CatCafeIterator();
// add all cats into an arraylist
		while (it.hasNext()){
			allCats.add(it.next());
		}
		int size = allCats.size();
		int maxDay = -1;

		for (int i = 0; i < size; i++) { //iterate through all cats in allCats
			if (allCats.get(i).getDaysToNextGrooming() > maxDay){
				maxDay = allCats.get(i).getDaysToNextGrooming(); // store the max day
				}
			}
		for (int nextDays = 0; nextDays <= maxDay; nextDays = nextDays+7 ) {
			ArrayList<Cat> catsNeedGrooming = new ArrayList<>(); // to create the new arraylist with next 7 days
			for (int i = 0; i < size; i++) {
				if (nextDays <= allCats.get(i).getDaysToNextGrooming() && allCats.get(i).getDaysToNextGrooming() < nextDays + 7) {
					catsNeedGrooming.add(allCats.get(i));
				}
			}
			groomingSchedule.add(catsNeedGrooming);
		}
		return groomingSchedule;
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
			if (root == null){
				root = new CatNode(c);
				return root;
			}
			root = add(root,c);
			return root;
//			//*******************************************************
//			if (c.compareTo(root.catEmployee)<0){
//				root = add(root,c);
//
//				// NEED TO FIX (ROTATION)
//				if (root.junior.catEmployee.getFurThickness() > root.catEmployee.getFurThickness()){
//					root = RightRotate(root);
//				}
//			}else{
//				root = add(root,c);
//				if (root.senior.catEmployee.getFurThickness() > root.catEmployee.getFurThickness()){
//					root = LeftRotate(root);
//				}
//			}
//			return root;
//			//********************************************************************

		}

		//helper
		private CatNode add(CatNode newRoot,Cat c){

			if (newRoot == null){
				return new CatNode(c);
			}

			if (c.compareTo(newRoot.catEmployee)<0) {
//			if (c.getMonthHired() > newRoot.catEmployee.getMonthHired()) {
				CatNode leftChild = add(newRoot.junior,c);
				newRoot.junior = leftChild;
				leftChild.parent = newRoot;

				if (newRoot.catEmployee.getFurThickness() < leftChild.catEmployee.getFurThickness()){
					return RightRotate(newRoot);
				}

			} else if (c.compareTo(newRoot.catEmployee)>0) {
//			} else if (c.getMonthHired() < newRoot.catEmployee.getMonthHired()) {
				CatNode rightChild = add(newRoot.senior,c);
				newRoot.senior = rightChild;
				rightChild.parent = newRoot;

				if (newRoot.catEmployee.getFurThickness() < rightChild.catEmployee.getFurThickness()){
					return LeftRotate(newRoot);
				}
			}
			return newRoot;
		}

//		private CatNode RightRotate(CatNode newRoot){
//			CatNode oldParent = newRoot.parent;
//			newRoot.parent = oldParent.parent;
//			oldParent.parent = newRoot;
//
//			CatNode oldSenior = newRoot.senior;
//			newRoot.senior = oldParent;
//
//			if (oldSenior.catEmployee!= null){
//				oldParent.junior = oldSenior;
//				oldSenior.parent = oldParent;
//			}
//			return newRoot;
//		}


//		private CatNode LeftRotate(CatNode newRoot){
//			CatNode oldParent = newRoot.parent;
//			newRoot.parent = oldParent.parent;
//			oldParent.parent = newRoot;
//
//			CatNode oldJunior = newRoot.junior;
//			newRoot.junior = oldParent;
//
//			if (oldJunior.catEmployee!= null){
//				oldParent.senior = oldJunior;
//				oldJunior.parent = oldParent;
//			}
//			return newRoot;
//		}

		private CatNode RightRotate(CatNode parent){
			CatNode grandParent = parent.parent;
			CatNode leftChild = parent.junior;
			CatNode oldSenior = leftChild.senior;

			leftChild.senior = parent;
			parent.parent = leftChild;
			parent.junior = oldSenior;

			if(grandParent != null) {
				leftChild.parent = grandParent;
				if (grandParent.catEmployee.compareTo(parent.catEmployee) > 0) // grandparent has higher seniority
//				if(grandParent.catEmployee.getMonthHired() < parent.catEmployee.getMonthHired())
					grandParent.junior = leftChild;
				else // left child has higher seniority
					grandParent.senior = leftChild;
			}else{ // if grandparent is null (parent is root)
				leftChild.parent = null;
			}
			return leftChild;
		}

		private CatNode LeftRotate(CatNode parent) {
			CatNode grandParent = parent.parent;
			CatNode rightChild = parent.senior;
			CatNode oldJunior = rightChild.junior;

			rightChild.junior = parent;
			parent.parent = rightChild;
			parent.senior = oldJunior;

			if (grandParent != null) {
				rightChild.parent = grandParent;
				if (grandParent.catEmployee.compareTo(parent.catEmployee) > 0) // grandparent has higher seniority
//				if(grandParent.catEmployee.getMonthHired() < parent.catEmployee.getMonthHired())
					grandParent.junior = rightChild;
				else // left child has higher seniority
					grandParent.senior = rightChild;
			}else{ // if grandparent is null (parent is root)
				rightChild.parent = null;
			}
			return rightChild;
		}

		// remove c from the tree rooted at this and returns the root of the resulting tree
		public CatNode retire(Cat c) {
			/*
			 * TODO: ADD YOUR CODE HERE**
			 */

			root = remove(root,c); // step 1

//			if (root.catEmployee.getFurThickness() < root.senior.catEmployee.getFurThickness()){
//				root = LeftRotate(root);
//			}

			return root;
			// remove cat step (1) all passed but still need to fix heap property (2)
		}
		private CatNode remove(CatNode root,Cat c){
			if (root == null){
				return null;

			} else if (c.compareTo(root.catEmployee) < 0) {
				root.junior = remove(root.junior,c);
			} else if (c.compareTo(root.catEmployee) > 0) {
				root.senior = remove(root.senior,c);

				//Base cases
			} else if (root.junior == null) {
				root = root.senior;
			} else if (root.senior == null) {
				root = root.junior;
			}else {
				root.catEmployee = root.junior.findMostSenior(); // find the most senior cat in the left subtree and copy it to the root
				root.junior = remove(root.junior,root.catEmployee); // remove the duplicate seniorC from the left subtree
			}
			return root;
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
				mostJunior = mostJunior.junior;
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
//	private static void iteratorTest() {
//		Cat B = new Cat("Buttercup", 45, 53, 5, 85.0);
//		Cat C = new Cat("Chessur", 8, 23, 2, 250.0);
//		Cat J = new Cat("Jonesy", 0, 21, 12, 30.0);
//		Cat JJ = new Cat("JIJI", 156, 17, 1, 30.0);
//		CatCafe Cafe = new CatCafe();
//		Cafe.hire(B);Cafe.hire(C);Cafe.hire(J);Cafe.hire(JJ);
//		Iterator itr = Cafe.iterator();
//		while (itr.hasNext()) {
//			System.out.println(itr.next().toString());
//		}
//		try {
//			System.out.println(itr.next().toString());
//		}
//		catch (NoSuchElementException e) {
//			System.out.println("No such element exists. The previous element has a next cat: "+itr.hasNext());
//		}
//	}

}


