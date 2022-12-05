package assignment3;

// Java implementation of the approach
import java.util.*;

// Node of the binary tree
class TreeNode
{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x)
    {
        val = x;
    }
}

// Iterator for BST
class BSTIterator{

    // Stack to store the nodes
// of BST
    Stack<TreeNode> s;

    // Constructor for the class
    public BSTIterator(TreeNode root)
    {

        // Initializing stack
        s = new Stack<>();
        TreeNode curr = root;

        while (curr != null)
        {
            s.push(curr);
            curr = curr.left;
        }
    }

    // Function to return
// current element iterator
// is pointing to
    TreeNode curr()
    {
        return s.peek();
    }

    // Function to iterate to next
// element of BST
    public void next()
    {
        TreeNode temp = s.peek().right;
        s.pop();

        while (temp != null)
        {
            s.push(temp);
            temp = temp.left;
        }
    }

    // Function to check if
// stack is empty
    public boolean isEnd()
    {
        return !s.isEmpty();
    }

    // Function to iterator to every element
// using iterator
    void iterate()
    {
        while (isEnd())
        {
            System.out.print(curr().val + " ");
            next();
        }
    }
}

class BinaryTree{

    TreeNode root;

    // Driver code
    public static void main(String args[])
    {

        // Let us construct a tree shown in
        // the above figure
        BinaryTree tree = new BinaryTree();
        tree.root = new TreeNode(5);
        tree.root.left = new TreeNode(3);
        tree.root.right = new TreeNode(7);
        tree.root.left.left = new TreeNode(2);
        tree.root.left.right = new TreeNode(4);
        tree.root.right.left = new TreeNode(6);
        tree.root.right.right = new TreeNode(8);

        // Iterator to BST
        BSTIterator it = new BSTIterator(tree.root);

        // Function to test iterator
        it.iterate();
    }
}

// This code is contributed by nobody_cares
