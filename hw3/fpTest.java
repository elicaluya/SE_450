import junit.framework.Assert;
import junit.framework.TestCase;
import java.util.*;
import java.util.function.Predicate;


public class fpTest extends TestCase{
	fp f = new fp();
	
	
	public void testMap() {
		List list1 = new ArrayList();
		list1.add("one");
		list1.add("two");
		list1.add("three");
		
		List returnList = fp.map(list1, s -> s.hashCode());
		
		List list2 = new ArrayList();
		for (Object o : list1) {
			list2.add(o.hashCode());
		}
		
		Assert.assertTrue(list2.equals(returnList));
		Assert.assertFalse(list1.equals(returnList));
		Assert.assertTrue(returnList.size() == list1.size());
	}
	
	
	
	
	public void testFoldLeft() {
		List<Integer> list1 = Arrays.asList(1,2,3);
		List<String> list2 = Arrays.asList("one","two","three");
		
		int result = fp.foldLeft(0, list1, (x,y) -> x + y);
		Assert.assertTrue(result == 6);
		
		String s = fp.foldLeft("", list2, (x,y)-> x + y);
		Assert.assertTrue(s.equals("onetwothree"));
	}
	
	
	
	public void testFoldRight() {
		List<Integer> list1 = Arrays.asList(1,2,3);
		List<String> list2 = Arrays.asList("one","two","three");
		
		int result = fp.foldLeft(0, list1, (x,y) -> y + x);
		Assert.assertTrue(result == 6);
		
		String s = fp.foldLeft("", list2, (x,y)-> y + x);
		Assert.assertTrue(s.equals("threetwoone"));
	}
	
	
	
	public void testFilter() {
		List<Integer> list1 = Arrays.asList(1,2,3,4,5);
		
		Predicate<Integer> greaterThanTwo = n -> n > 2;
		
		Iterable<Integer> result = fp.filter(list1, greaterThanTwo);
		for (Integer i : result) {
			Assert.assertTrue(i > 2);
			Assert.assertFalse(i <= 2);
		}
	}
}
