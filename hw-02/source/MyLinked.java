import junit.framework.Assert;
import junit.framework.TestCase;

public class MyLinked extends TestCase{
    static class Node {
        public Node() { }
        public double item;
        public Node next;
    }

    int N;
    Node first;
    
    public MyLinked () {
        first = null;
        N = 0;
        assert checkInvariants ();
    }


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

    public boolean isEmpty () { return first == null; }
    public int size () { return N; }
    public void add (double item) {
        Node newfirst = new Node ();
        newfirst.item = item;
        newfirst.next = first;
        first = newfirst;
        N++;
    }


    // delete the kth element
    public void delete (int k) {
        if (k < 0 || k >= N) throw new IllegalArgumentException ();
        
        Node curr = first;
        
        // If we want to delete the first element in the linked list
        if (k == 0) first = first.next;
        
        // If we want to delete the last item in the linked list
        else if (k == N-1) {
        	while (curr.next.next != null) {
        		curr = curr.next;
        	}
        	curr.next = null;
        }
        
        // If we want to delete an item at any point in the linked list
        else {
        	for (int i = 0; i < k-1; i++) {
        		curr = curr.next;
        	}
        	curr.next = curr.next.next;
        }
        
        // Reduce the number of elements in linked list
        N--;
        
        assert checkInvariants ();
    }

    // reverse the list "in place"... without creating any new nodes
    public void reverse () {
    
        assert checkInvariants ();
    }

    // remove 
    public void remove (double item) {
        if (N > 0) {
        	Node tmp = new Node();
        	tmp.next = first;
        	
        	Node curr = tmp;
        
            while (curr.next != null) {
            	if (item == curr.next.item) {
            		curr.next = curr.next.next;
            		N--;
            	}
            	else	
            		curr = curr.next;
            }
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
        b.reverse ();
        print ("reverse empty", b);
        b.add (1);
        print ("singleton", b);
        b.reverse ();
        print ("reverse singleton", b);
        b.add (2);
        print ("two", b);
        b.reverse ();
        print ("reverse two", b);
        b.reverse ();
        print ("reverse again", b);
        for (double i = 3; i < 7; i++) {
            b.add (i);
            b.add (i);
        }
        print ("bigger list", b);
        b.reverse ();
        print ("reversed", b);
    }
    
    public static void testRemove () {
        MyLinked b = new MyLinked ();
        b.remove (4);
        Assert.assertTrue(b.first == null);
        b.add (1);
        b.remove (4);
        Assert.assertTrue(b.size() == 1);
        b.remove (1);
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
        Assert.assertTrue(b.size() == 28);
        b.remove (9);
        Assert.assertTrue(b.size() == 28); // does nothing
        b.remove (3);
        Assert.assertTrue(b.size() == 21);
        b.remove (1);
        Assert.assertTrue(b.size() == 14);
        b.remove (4);
        Assert.assertTrue(b.size() == 7);
        Assert.assertTrue(b.first.item == 2);
        b.remove (2);
        Assert.assertTrue(b.isEmpty()); // should be empty
        Assert.assertTrue(b.first == null);
    }

}

































