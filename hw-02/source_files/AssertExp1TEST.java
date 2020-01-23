import junit.framework.Assert;
import junit.framework.TestCase;
import java.util.*;


public class AssertExp1TEST extends TestCase{

	
	public void testMinPosition() {
		AssertExp1 ae = new AssertExp1();
		
		// if min position is the first position
		double[] array1 = new double[] {1,2,3,4,5};
		assertTrue(ae.minPosition(array1) == 0);
		
		// if min position is in the middle of the array
		double[] array2 = new double[] {1,2,0,4,5};
		assertTrue(ae.minPosition(array2) == 2);
		
		// If min positiion is at the end of array
		double[] array3 = new double[] {1,2,3,4,0};
		assertTrue(ae.minPosition(array3) == 4);
	}
	
	
	public void testNumUnique() {
		AssertExp1 ae = new AssertExp1();

		// If all numbers are unique
		double[] array1 = new double[] {1,2,3,4,5};
		assertTrue(ae.numUnique(array1) == 5);
		
		// If empty list
		double[] array2 = new double[] {};
		assertTrue(ae.numUnique(array2) == 0);
		
		// 5 unique numbers, each with different numbers of occurrences
		double[] array3 = new double[] {1,1,2,2,2,3,3,4,5,5};
		assertTrue(ae.numUnique(array3) == 5);
		
		// All the same number
		double[] array4 = new double[] {1,1,1,1};
		assertTrue(ae.numUnique(array4) == 1);
		
		// Just one number
		double[] array5 = new double[] {1};
		assertTrue(ae.numUnique(array5) == 1);
	}
	
	
	public void testRemoveDups() {
		AssertExp1 ae = new AssertExp1();

		// Remove all multiple copies of numbers
		double[] a1 = new double[] {1,2,3,4,5};
		double[] a2 = new double[] {1,1,2,2,3,3,3,4,4,4,5,5};
		assertTrue(Arrays.equals(ae.removeDuplicates(a2), a1));
		
		// Remove duplicates from empty array
		double[] a3 = new double[] {};
		assertTrue(Arrays.equals(ae.removeDuplicates(a3), a3));
		
		// Remove duplicates from array with one element
		double[] a4 = new double[] {1};
		assertTrue(Arrays.equals(ae.removeDuplicates(a4), a4));
		
		// Remove from array with all one number
		double[] a5 = new double[] {1};
		double[] a6 = new double[] {1,1,1,1,1};
		assertTrue(Arrays.equals(ae.removeDuplicates(a6), a5));
		
		// No change from array with no duplicates
		assertTrue(Arrays.equals(ae.removeDuplicates(a1), a1));
	}
}
