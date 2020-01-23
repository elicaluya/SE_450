import junit.framework.Assert;
import junit.framework.TestCase;
import java.util.*;
import java.util.Collections;

/**
 * <b>Linked List class with methods to manipulate the linked list that contains Nodes
 * */
public class MyLinked extends TestCase{
    static class Node {
        public Node() { }
        public double item;
        public Node next;
    }

    int N;
    Node first;
    
    /**
     * <b>Constructor for MyLinked takes no parameters and initializes first Node
     * */
    public MyLinked () {
        first = null;
        N = 0;
        assert checkInvariants ();
    }


    /**
     * <p><b>Method to check the invariants for the MyLinked class. Returns true if all invariants are not violated</b></p>
     * 
     * <p><b>Invariant: </b> n is not equal to zero and first node is not null</p>
     * <p><b>Invariant: </b> no Node in the linked list is equal to null</p>
     * <p><b>Invariant: </b> only the next value of the last Node is pointing to null</p>
     * @param None
     * */
    private boolean checkInvariants() {
        assert((N != 0) || (first == null));
        Node x = first;
        for (int i = 0; i < N; i++) {
            if (x==null) {
                return false;
            }
            x = x.next;
        }
        assert(x == null);
        return true;
    }

    /**
     * <p><b>Returns true if Linked list is empty
     * @param None
     * */
    public boolean isEmpty () { return first == null; }
    /**
     * <b>Returns the size of the linked list
     * @param None
     * */
    public int size () { return N; }
    /**
     * <b>Adds new Node to the Linked list
     * @param double to add to linked list
     * */
    public void add (double item) {
        Node newfirst = new Node ();
        newfirst.item = item;
        newfirst.next = first;
        first = newfirst;
        N++;
    }

    /**
     * <p><b>Returns true if able to contents of linked list to an array list. mainly used for asserts
     * @param ArrayList<node> l to fill with contents of linked list
     * @param Node n linked list to go into array list
     * */
    boolean copy(ArrayList<Node> l, Node n) {
    	while (n != null) {
    		l.add(n);
    		n = n.next;
    	}
    	return true;
    }

    
    /**
     * <p><b>Method for deleting a specific node in the linked list. Nothing happens if empty linked list or node is not in linked list.</b></p>
     * <p><b>Invariant: </b> linked list size is changed when item is removed</p>
     * @param int k integer to be deleted
     * */
    public void delete (int k) {
        if (k < 0 || k >= N) throw new IllegalArgumentException ();
        
        Node curr = first;
        
        // ArrayList for asserts
        ArrayList<Node> list1 = new ArrayList<Node>();
        ArrayList<Node> list2 = new ArrayList<Node>();
        copy(list1,curr);
        // Store initial size of list
        int init_size = list1.size();
        
        // If we want to delete the first element in the linked list
        if (k == 0) {
        	first = first.next;
        	copy(list2,first);
        	assert(list1.size() != list2.size());	// Check that list changes when item is removed
        }
        
        // If we want to delete the last item in the linked list
        else if (k == N-1) {
        	while (curr.next.next != null) {
        		curr = curr.next;
        		assert(curr.next != null);
        	}
        	curr.next = null;
        	copy(list2,curr);
        	assert(list1.size() != list2.size());	// Check that list changes when item is removed
        }
        
        // If we want to delete an item at any point in the linked list
        else {
        	for (int i = 0; i < k-1; i++) {
        		curr = curr.next;
        	}
        	assert(curr.next.next != null && curr.next != null);
        	curr.next = curr.next.next;
        	copy(list2,curr);
        	assert(list1.size() != list2.size());	// Check that list changes when item is removed
        }
        
        // Reduce the number of elements in linked list
        N--;
        
        assert checkInvariants ();
    }
    

    /**
     * <p><b>Method for reversing the linked list. Nothing happens if linked list is empty or there is only one Node.</b></p>
     * <p><b>Invariant: </b> when reversed list is reversed back, it will be the same as the original list</p>
     * @param None
     * */
    public void reverse () {
    	Node prev = null , curr = first;
    	
    	// ArrayList for asserts
    	ArrayList<Node> list = new ArrayList<Node>();
    	copy(list,curr);

    	// Reverse the linked list
    	while (curr != null) {
    		Node tmp = curr.next;
    		curr.next = prev;
    		prev = curr;
    		curr = tmp;
    	}
    	
    	// Check to see if the reversed list will be the same as the list as before
    	ArrayList<Node> rev_list = new ArrayList<Node>();
		copy(rev_list,prev);
		Collections.reverse(rev_list);
		assert(rev_list.equals(list));
		assert(list.equals(list));
    	
		
		// Set the linked list to the reverse linked list
    	first = prev;
        assert checkInvariants ();
    }

    /**
     * <b>Method for removing all occurrences of a node if it is in the linked list.</b>
     * <p><b>Invariant: </b> No node will have the item that was given in the parameter in the linked list</p>
     * <p><b>Invariant: </b> When removed, the linked list will have a lesser size than before</p>
     * 
     * @param double item. Item to be removed from linked list
     * */ 
    public void remove (double item) {
    	if (N > 0) {
        	Node tmp = new Node();
        	tmp.next = first;
        	Node curr = tmp;
        	
        	// ArrayList for asserts
        	ArrayList<Node> list1 = new ArrayList<Node>();
        	copy(list1,curr);
        
        	// Loop through linked list and remove node if its value matches the item
            while (curr.next != null) {
            	// If the item matches the value fo the next node
            	if (item == curr.next.item) {
            		curr.next = curr.next.next;
            		N--;
            		
            		// Check to make sure that the list has changed when an item has been removed
            		ArrayList<Node> list2 = new ArrayList<Node>();
            		copy(list2,curr);
            		assert(!list1.equals(list2));
            		assert(list1.size() != (list2.size()));
            	}
            	// move onto the next node
            	else	
            		curr = curr.next;
            }
            
            // Make sure that all occurrences of the item have been removed from the linked list
            ArrayList<Node> list = new ArrayList<Node>();
            copy(list,tmp.next);
            assert(list.indexOf(item) < 0);
            
            
            // Set the linked list to the newly edited linked list
            first = tmp.next;
        }
    	
        assert checkInvariants ();
    }

// TODO: CONVERT THE FOLLOWING TO JUNIT TESTS

//    private static void testMax () {
//        MyLinked b = new MyLinked ();
//        print ("empty", b, b.max());
//        b.add (-1);
//        print ("singleton", b, b.max());
//        b.add (-2);
//        b.add (-3);
//        b.add (-4);
//        print ("at end", b, b.max());
//        b.add (5);
//        print ("at beginning", b, b.max());
//        b.add (3);
//        b.add (2);
//        b.add (4);
//        print ("in the middle", b, b.max());
//    }
//    private static void testMaxRecursive () {
//        MyLinked b = new MyLinked ();
//        print ("empty", b, b.maxRecursive());
//        b.add (-1);
//        print ("singleton", b, b.maxRecursive());
//        b.add (-2);
//        b.add (-3);
//        b.add (-4);
//        print ("at end", b, b.maxRecursive());
//        b.add (5);
//        print ("at beginning", b, b.maxRecursive());
//        b.add (3);
//        b.add (2);
//        b.add (4);
//        print ("in the middle", b, b.maxRecursive());
//    }
    
    
    public static void testDelete () {
        MyLinked b = new MyLinked ();
        b.add (1);
        Assert.assertTrue(b.first.item == 1);
        b.delete (0);
        Assert.assertTrue(b.isEmpty());
        for (double i = 1; i < 13; i++) {
            b.add (i);
        }
        // Create a linked list of 12 nodes
        Assert.assertTrue(b.first.item == 12);
        // Delete first item in linked list
        b.delete (0);
        // First item deleted so we have a new first node
        Assert.assertTrue(b.first.item == 11);
        // Delete last item in linked list
        b.delete (10);
        // Index to the last element and make sure that the last element is 2 since the last element was deleted
        Node tmp = b.first;
        for (int i = 0; i < 9; i++)
        	tmp = tmp.next;
        Assert.assertTrue(tmp.item == 2);
        // Delete item in the middle of linked list
        b.delete (4);
        // Index to node that was previously pointing to deleted node and checking it is pointing to the next node
        tmp = b.first;
        for (int i = 0; i < 3; i++)
        	tmp = tmp.next;
        Assert.assertTrue(tmp.next.item == 6);
    }
    
    public static void testReverse () {
        MyLinked b = new MyLinked ();
        b.reverse ();	// Reverse empty linked list
        Assert.assertTrue(b.isEmpty());	// Check linked list is empty
        b.add (1);
        Assert.assertTrue(b.size() == 1);	// Confirm size of linked list after adding 1 node
        Assert.assertTrue(b.first.item == 1);	
        b.reverse ();
        // Should return same first value since only one node when reversed
        Assert.assertTrue(b.first.item == 1);
        Assert.assertTrue(b.size() == 1);
        b.add (2);
        // Add a second Node and make sure there is more than one node in linked list
        Assert.assertTrue(b.size() == 2);
        Assert.assertTrue(b.first.item == 2);
        Assert.assertTrue(b.first.next.item == 1);
        b.reverse ();
        // Check that the order of nodes is reversed after the reverse method is called
        Assert.assertTrue(b.first.item == 1);
        Assert.assertTrue(b.first.next.item == 2);
        b.reverse ();
        // Check that the order is the same as original linked list after reversing again
        Assert.assertTrue(b.first.item == 2);
        Assert.assertTrue(b.first.next.item == 1);
        for (double i = 3; i < 7; i++) {
            b.add (i);
            b.add (i);
        }
        Assert.assertTrue(b.size() == 10);	// Check there are more items in linked list now
        b.reverse ();
        // Make sure that the correct nodes are in the right place after reversing
        Assert.assertTrue(b.first.item == 1);
        Assert.assertTrue(b.first.next.item == 2);
        Assert.assertTrue(b.first.next.next.item == 3);
        Assert.assertTrue(b.first.next.next.next.item == 3);
        Assert.assertTrue(b.first.next.next.next.next.item == 4);
        Assert.assertTrue(b.first.next.next.next.next.next.item == 4);
        Assert.assertTrue(b.first.next.next.next.next.next.next.item == 5);
        Assert.assertTrue(b.first.next.next.next.next.next.next.next.item == 5);
        Assert.assertTrue(b.first.next.next.next.next.next.next.next.next.item == 6);
        Assert.assertTrue(b.first.next.next.next.next.next.next.next.next.next.item == 6);
    }
    
    public static void testRemove () {
        MyLinked b = new MyLinked ();
        b.remove (4);
        Assert.assertTrue(b.first == null);	// Removing from empty list should do nothing
        b.add (1);
        b.remove (4);
        // Linked list should be unchanged after removing a nonexistent node
        Assert.assertTrue(b.size() == 1);
        b.remove (1);
        // Linked list is now empty after removing only node
        Assert.assertTrue(b.isEmpty());
        for (double i = 1; i < 5; i++) {
            b.add (i);
            b.add (i);
        }
        for (double i = 1; i < 5; i++) {
            b.add (i);
            b.add (i);
            b.add (i);
            b.add (i);
            b.add (i);
        }
        Assert.assertTrue(b.size() == 28);	// Linked list now has 28 nodes in it
        Assert.assertTrue(b.first.item == 4);
        b.remove (9);
        Assert.assertTrue(b.size() == 28); // does nothing
        b.remove (3);
        Assert.assertTrue(b.size() == 21);	// Remove all 3s
        Assert.assertTrue(b.first.item == 4);	// First node is unchanged
        b.remove (1);
        Assert.assertTrue(b.size() == 14); // Remove all 1s
        b.remove (4);
        Assert.assertTrue(b.size() == 7);	// remove all 4s
        Assert.assertTrue(b.first.item == 2);	// New first node
        b.remove (2);
        Assert.assertTrue(b.isEmpty()); // should be empty
        Assert.assertTrue(b.first == null);	// No first node
    }

}

































