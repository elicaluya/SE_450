import junit.framework.Assert;
import junit.framework.TestCase;
import java.util.*;
import java.util.function.Predicate;
import java.util.Comparator;


public class fpTest extends TestCase{
	fp f = new fp();
	
	
	// #1 - test map function using hashCode()
	public void testMap() {
		List<String> list1 = new ArrayList();
		list1.add("Mary");
		list1.add("Isla");
		list1.add("Sam");
		
		List returnList = fp.map(list1, s -> s.hashCode());
		
		List list2 = new ArrayList();
		for (Object o : list1) {
			list2.add(o.hashCode());
		}
		
		Assert.assertTrue(list2.equals(returnList));
		Assert.assertFalse(list1.equals(returnList));
		Assert.assertTrue(returnList.size() == list1.size());
	}
	
	
	
	// #2 - test foldLeft function with integers and strings
	public void testFoldLeft() {
		List<Integer> list1 = Arrays.asList(1,2,3);
		List<String> list2 = Arrays.asList("one","two","three");
		List<Integer> list3 = Arrays.asList(1,2,3,4,5,6);
		List<String> list4 = Arrays.asList("one","two","three","four","five","six");
		List<Integer> list5 = Arrays.asList();
		List<String> list6 = Arrays.asList();
		
		int result1 = fp.foldLeft(0, list1, (x,y) -> x + y);
		Assert.assertTrue(result1 == 6);
		
		String s1 = fp.foldLeft("", list2, (x,y)-> x + y);
		Assert.assertTrue(s1.equals("onetwothree"));
		
		int result2 = fp.foldLeft(0, list3, (x,y) -> x + y);
		Assert.assertTrue(result2 == 21);
		
		String s2 = fp.foldLeft("", list4, (x,y)-> x + y);
		Assert.assertTrue(s2.equals("onetwothreefourfivesix"));
		
		int result3 = fp.foldLeft(0, list5, (x,y) -> x + y);
		Assert.assertTrue(result3 == 0);
		
		String s3 = fp.foldLeft("", list6, (x,y)-> x + y);
		Assert.assertTrue(s3.equals(""));
	}
	
	
	// #3 - test foldRight on Integers and Strings
	public void testFoldRight() {
		List<Integer> list1 = Arrays.asList(1,2,3);
		List<String> list2 = Arrays.asList("one","two","three");
		List<String> list3 = Arrays.asList("one","two","three","four","five","six");
		
		int result = fp.foldLeft(0, list1, (x,y) -> y + x);
		Assert.assertTrue(result == 6);
		
		String s = fp.foldLeft("", list2, (x,y)-> y + x);
		Assert.assertTrue(s.equals("threetwoone"));
		
		String s2 = fp.foldLeft("", list3, (x,y)-> y + x);
		Assert.assertTrue(s2.equals("sixfivefourthreetwoone"));
	}
	
	
	//#4 test filter function with Person object and with integers
	public void testFilter() {
		List<Person> personList = new ArrayList();
		Person joe = new Person(75000,"Joe");
		Person peter = new Person(100000,"Peter");
		Person glenn = new Person(200000,"Glenn");
		Person cleveland = new Person(150000,"Cleveland");
		personList.add(joe);
		personList.add(peter);
		personList.add(glenn);
		personList.add(cleveland);
		
		List<Integer> list1 = Arrays.asList(1,2,3,4,5);
		List<Integer> list2 = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		
		Predicate<Person> salary = n -> n.salary > 100000;
		Predicate<Integer> greaterThanTwo = n -> n > 2;
		Predicate<Integer> even = n -> n %2 == 0;
		
		Iterable<Person> personResult = fp.filter(personList, salary);
		for (Person p : personResult) {
			Assert.assertTrue(p.name.equals("Glenn") || p.name.equals("Cleveland"));
		}
		
		
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
	
	
	// #5 and #6 test minVal function to get the minimum and maximum from a list
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
	
	
	// #7 and #8 test minPos function to get the minimum position of an Integer and String list
	public void testMinPos() {
		List<Integer> list1 = Arrays.asList(3,7,2,8,5,6);
		int result1 = fp.minPos(list1);
		Assert.assertTrue(result1 == 2);
		
		List<Integer> list2 = Arrays.asList(9,7,6,8,5,1);
		int result2 = fp.minPos(list2);
		Assert.assertTrue(result2 == 5);
		
		List<Integer> list3 = Arrays.asList(1,7,2,8,5,9);
		int result3 = fp.minPos(list3);
		Assert.assertTrue(result3 == 0);
		
	
		List<String> list4 = Arrays.asList("A","B","C","D");
		int result4 = fp.minPos(list4);
		Assert.assertTrue(result4 == 0);
		
		List<String> list5 = Arrays.asList("E","B","C","D","A");
		int result5 = fp.minPos(list5);
		Assert.assertTrue(result5 == 4);
		
		List<String> list6 = Arrays.asList("E","B","A","D","C");
		int result6 = fp.minPos(list6);
		Assert.assertTrue(result6 == 2);
	}
}
