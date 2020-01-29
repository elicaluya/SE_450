import junit.framework.Assert;
import junit.framework.TestCase;
import java.util.*;
import java.util.function.Predicate;
import java.util.Comparator;


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
		List<Integer> list3 = Arrays.asList(1,2,3,4,5,6);
		List<String> list4 = Arrays.asList("one","two","three","four","five","six");
		
		int result1 = fp.foldLeft(0, list1, (x,y) -> x + y);
		Assert.assertTrue(result1 == 6);
		
		String s1 = fp.foldLeft("", list2, (x,y)-> x + y);
		Assert.assertTrue(s1.equals("onetwothree"));
		
		int result2 = fp.foldLeft(0, list3, (x,y) -> x + y);
		Assert.assertTrue(result2 == 21);
		
		String s2 = fp.foldLeft("", list4, (x,y)-> x + y);
		Assert.assertTrue(s2.equals("onetwothreefourfivesix"));
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
		List<Integer> list2 = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		
		Predicate<Integer> greaterThanTwo = n -> n > 2;
		Predicate<Integer> even = n -> n %2 == 0;
		
		Iterable<Integer> result1 = fp.filter(list1, greaterThanTwo);
		for (Integer i : result1) {
			Assert.assertTrue(i > 2);
			Assert.assertFalse(i <= 2);
		}
		
		Iterable<Integer> result2 = fp.filter(list2, even);
		for (Integer i : result2) {
			Assert.assertTrue(i % 2 == 0);
			Assert.assertFalse(i % 2 != 0);
		}
	}
	
	
	
	public void testMinVal() {
		List<Integer> list1 = Arrays.asList(1,2,3,4,5);
		List<Integer> list2 = Arrays.asList(8,7,2,3,5,1);
		List<Integer> list3 = Arrays.asList(3,7,2,8,5,6);
		
		Comparator<Integer> min = new Comparator<Integer>() {
			public int compare(Integer x, Integer y) {
				return x < y ? -1 : 1;
			}
		};
		
		Comparator<Integer> max = new Comparator<Integer>() {
			public int compare(Integer x, Integer y) {
				return x > y ? -1 : 1;
			}
		};
		
		int resultMin1 = fp.minVal(list1, min);
		Assert.assertTrue(resultMin1 == 1);
		int resultMin2 = fp.minVal(list2, min);
		Assert.assertTrue(resultMin2 == 1);
		int resultMin3 = fp.minVal(list3, min);
		Assert.assertTrue(resultMin3 == 2);
		
		int resultMax1 = fp.minVal(list1, max);
		Assert.assertTrue(resultMax1 == 5);
		int resultMax2 = fp.minVal(list2, max);
		Assert.assertTrue(resultMax2 == 8);
		int resultMax3 = fp.minVal(list3, max);
		Assert.assertTrue(resultMax3 == 8);
	}
}
