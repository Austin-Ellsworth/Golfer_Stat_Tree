// File: TreeBag.java 

// The implementation of most methods in this file is left as a student
// exercise from Section 9.5 of "Data Structures and Other Objects Using Java"


/******************************************************************************
* This class is a homework assignment;
* An <CODE>TreeBag</CODE> is a collection of int numbers.
*
* <dl><dt><b>Limitations:</b> <dd>
*   Beyond <CODE>Integer.MAX_VALUE</CODE> elements, <CODE>countOccurrences</CODE>,
*   and <CODE>size</CODE> are wrong. 
*
* <dt><b>Note:</b><dd>
*   This file contains only blank implementations ("stubs")
*   because this is a Programming Project for my students.
*
* @version
*   Jan 24, 2016
******************************************************************************/
public class TreeBag<E extends Comparable> implements Cloneable
{
   // The Term E extends Comparable is letting the compiler know that any type
   // used to instantiate E must implement Comparable. i. e. that means that whatever
   // type E is must have a compareTo method so that elements can be compared against one another
   // This is required becuase we are doing comparisons in our methods


   // Invariant of the TreeBag class:
   //   1. The elements in the bag are stored in a binary search tree.
   //   2. The instance variable root is a reference to the root of the
   //      binary search tree (or null for an empty tree).
   private BTNode<E> root;   


   public BTNode<E> getRoot() {
	return root;
}

/**
   * Insert a new element into this bag.
   * @param <CODE>element</CODE>
   *   the new element that is being inserted
   * <dt><b>Postcondition:</b><dd>
   *   A new copy of the element has been added to this bag.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory a new BTNode.
   **/
@SuppressWarnings("unchecked")
public void add(E element)
   {      
	  BTNode<E> cursor = root;
	  boolean end = false;
	  
      if(root == null) {
    	  root = new BTNode<E>(element, null, null);
      }
      
      else {
    	  while(!end) {
    		  if(element.compareTo(cursor.getData()) <= 0) {
    			  if(cursor.getLeft() == null) {
    				  cursor.setLeft(new BTNode<E>(element, null, null));
    				  end = true;
    			  } else {
    				  cursor = cursor.getLeft();
    			  }
    		  } else {
    			  if(cursor.getRight() == null) {
    				  cursor.setRight(new BTNode<E>(element, null, null));
    				  end = true;
    			  } else {
    				  cursor = cursor.getRight();
    			  }
    		  }
    	  } // end while loop
      }
   } //end add

   /**
   * Retrieve location of a specified element from this bag.
   * @param <CODE>target</CODE>
   *   the element to locate in the bag
   * @return 
   *  the return value is a reference to the found element in the tree
   * <dt><b>Postcondition:</b><dd>
   *   If <CODE>target</CODE> was found in the bag, then method returns
   *   a reference to a comparable element. If the target was not found then
   *   the method returns null.
   *   The bag remains unchanged.
   **/
   @SuppressWarnings("unchecked")
public E retrieve(E target)
   {
      if (root == null) {
    	  return null;
      } else {
    	  BTNode<E> cursor = root;
    	  boolean end = false;
    	  
    	  while(cursor != null && !end) {
    		  if(target.compareTo(cursor.getData()) > 0)
    			  cursor = cursor.getRight();
    		  else if(target.compareTo(cursor.getData()) < 0)
    			  cursor = cursor.getLeft();
    		  else if(target.compareTo(cursor.getData()) == 0) {
    			  end = true;
    			  return cursor.getData();
    		  }
    	  }
    	  return null;
      }
   }

   
   /**
   * Remove one copy of a specified element from this bag.
   * @param <CODE>target</CODE>
   *   the element to remove from the bag
   * <dt><b>Postcondition:</b><dd>
   *   If <CODE>target</CODE> was found in the bag, then one copy of
   *   <CODE>target</CODE> has been removed and the method returns true. 
   *   Otherwise the bag remains unchanged and the method returns false. 
   **/
   @SuppressWarnings("unchecked")
public boolean remove(E target)
   {
	  BTNode<E> parent = null; //parent of cursor
      BTNode<E> cursor = root;
      boolean end = false;
      
      while(cursor != null && !end) {//to find target if it exists in tree
    	  if(target.compareTo(cursor.getData()) == 0) {
    		  end = true;
    	  } else if (target.compareTo(cursor.getData()) < 0) {
    		  parent = cursor;
    		  cursor = cursor.getLeft();
    	  } else if (target.compareTo(cursor.getData()) > 0) {
    		  parent = cursor;
    		  cursor = cursor.getRight();
    	  }
      }
      if(cursor == null) { //if there is no target in tree
    	  return false;
      }
      else if(cursor == root && cursor.getLeft() == null && cursor.getRight() == null) {//root with no children
    	  root = null;
      } else if(cursor == root && cursor.getLeft() == null) {//root with no left child
    	  root = root.getRight();
      } else if(cursor == root && cursor.getRight() == null) {//root with no right child
    	  root = root.getLeft();
      } else if(cursor.getLeft() == null && cursor.getRight() == null) {
    	  System.out.println("This ran");
    	  if(cursor == parent.getLeft()) {
    		  parent.removeLeftmost();
    	  } else if(cursor == parent.getRight()) {
    		  parent.removeRightmost();
    	  }
      } else if(cursor.getLeft() == null) {//cursor with no left child
    	  if(cursor == parent.getLeft()) {
    		  parent.setLeft(cursor.getRight());
    	  } else if(cursor == cursor.getRight()) {
    		  parent.setRight(cursor.getRight());
    	  }
      } else if(cursor.getRight() == null) {//cursor with no right child
    	  if(cursor == parent.getLeft()) {
    		  parent.setLeft(cursor.getLeft());
    	  } else if(cursor == parent.getRight()) {
    		  parent.setRight(cursor.getLeft());
    	  }
      } else { //cursor has right and left children
    	  cursor.setData(cursor.getLeft().getRightmostData());
    	  cursor.setLeft(cursor.getLeft().removeRightmost());
      }
      
      return true;
      
   }
   
   /**
   * Displays the entire tree of Node elements in a order specified
   * by the elements compareTo method
   * 
   * @param 
   *   none
   * <dt><b>Postcondition:</b><dd>
   *   Outputs all elements in the tree to Screen.
   *   Does not change the structure 
   **/
   public void display()
   {
      if(root == null)
    	  System.out.println("The tree is empty.");
      else
    	  root.inorderPrint();
      
   } 
     
   /**
   * Displays the entire tree of Node elements using the
   * built in print method of BTNode
   * which displays the entire tree in tree format
   * 
   * @param 
   *   none
   * <dt><b>Postcondition:</b><dd>
   *   Outputs all elements in the tree to Screen.
   *   Does not change the structure 
   **/   
   public void displayAsTree() {
      if (root == null)
         throw new IllegalArgumentException("The tree is empty");
      root.print(0);
   }
      
   
   
   /**
   * Generate a copy of this bag.
   * @param - none
   * @return
   *   The return value is a copy of this bag. Subsequent changes to the
   *   copy will not affect the original, nor vice versa. Note that the return
   *   value must be type cast to an <CODE>TreeBag</CODE> before it can be used.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for creating the clone.
   **/ 
   public TreeBag<E> clone( )
   {  // Clone an IntTreeBag object.
      // Student will replace this return statement with their own code:
      return null; 
   } 

   /**
   * Accessor method to count the number of occurrences of a particular element
   * in this bag.
   * @param <CODE>target</CODE>
   *   the element that needs to be counted
   * @return
   *   the number of times that <CODE>target</CODE> occurs in this bag
   **/
   public int countOccurrences(E target)
   {
      // Student will replace this return statement with their own code:
      return 0;
   }
   
       
   /**
   * Determine the number of elements in this bag.
   * @param - none
   * @return
   *   the number of elements in this bag
   **/                           
   public int size( )
   {
      return BTNode.treeSize(root);
   }




   /**
   * Add the contents of another bag to this bag.
   * @param <CODE>addend</CODE>
   *   a bag whose contents will be added to this bag
   * <dt><b>Precondition:</b><dd>
   *   The parameter, <CODE>addend</CODE>, is not null.
   * <dt><b>Postcondition:</b><dd>
   *   The elements from <CODE>addend</CODE> have been added to this bag.
   * @exception IllegalArgumentException
   *   Indicates that <CODE>addend</CODE> is null.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory to increase the size of the bag.
   **/
   public void addAll(TreeBag<E> addend)
   {
      // Implemented by student.
   }
   
   /**
   * Create a new bag that contains all the elements from two other bags.
   * @param <CODE>b1</CODE>
   *   the first of two bags
   * @param <CODE>b2</CODE>
   *   the second of two bags
   * <dt><b>Precondition:</b><dd>
   *   Neither b1 nor b2 is null.
   * @return
   *   the union of b1 and b2
   * @exception IllegalArgumentException
   *   Indicates that one of the arguments is null.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for the new bag.
   **/   
   @SuppressWarnings("rawtypes")
public static TreeBag union(TreeBag b1, TreeBag b2)
   {
      // Student will replace this return statement with their own code:
      return null;
   }
      
}