import junit.framework.Assert;
import junit.framework.TestCase;
import java.util.*;


public class AssertExp1TEST extends TestCase{

	
	public void testMinPosition() {
		AssertExp1 ae = new AssertExp1();
		
		double[] array1 = new double[] {1,2,3,4,5};
		assertTrue(ae.minPosition(array1) == 0);
		
		double[] array2 = new double[] {1,2,0,4,5};
		assertTrue(ae.minPosition(array2) == 2);
		
		double[] array3 = new double[] {1,2,3,4,0};
		assertTrue(ae.minPosition(array3) == 4);
	}
	
	
	public void testNumUnique() {
		AssertExp1 ae = new AssertExp1();

		double[] array1 = new double[] {1,2,3,4,5};
		assertTrue(ae.numUnique(array1) == 5);
		
		double[] array2 = new double[] {};
		assertTrue(ae.numUnique(array2) == 0);
		
		double[] array3 = new double[] {1,1,2,2,2,3,3,4,5,5};
		assertTrue(ae.numUnique(array3) == 5);
		
		double[] array4 = new double[] {1,1,1,1};
		assertTrue(ae.numUnique(array4) == 1);
		
		double[] array5 = new double[] {1};
		assertTrue(ae.numUnique(array5) == 1);
	}
	
	
	public void testRemoveDups() {
		AssertExp1 ae = new AssertExp1();

		double[] a1 = new double[] {1,2,3,4,5};
		double[] a2 = new double[] {1,1,2,2,3,3,3,4,4,4,5,5};
		assertTrue(Arrays.equals(ae.removeDuplicates(a2), a1));
		
		double[] a3 = new double[] {};
		assertTrue(Arrays.equals(ae.removeDuplicates(a3), a3));
		
		double[] a4 = new double[] {1};
		assertTrue(Arrays.equals(ae.removeDuplicates(a4), a4));
	}
}
