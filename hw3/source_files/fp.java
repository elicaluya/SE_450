import java.util.List;
import java.util.function.Function;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class fp {
	
	
/**
 * <p>
 * map takes and applies it to the contents of the list.
 * <p>
 * <p>
 * Returns a list with the functions applied to each element. 
 * <p>
 * @param Iterable<U> l: Iterable to apply function to
 * @param Function<U,V> f: The function to be applied to the contents in the Iterable
 */
static <U,V> List<V> map(Iterable<U> l, Function<U,V> f) {
	List<V> list = new ArrayList<V>();
	for (U u : l) {
		list.add(f.apply(u));
	}
	return list;
}


/**
 * <p>
 * foldLeft takes a list and reduces it to one item based on the BiFunction starting from the first element in the Iterable
 * <p>
 * <p>
 * Returns a single object after the list has been reduced by the BiFunction. 
 * <p>
 * @param V e: Starting element
 * @param Iterable<U> l: Iterable to be looped through and reduced
 * @param BiFunction<V,U,V> f: The function to be applied to the contents in the Iterable
 */
static <U,V> V foldLeft(V e, Iterable<U>l, BiFunction<V,U,V> f){
	Iterator<U> itr = l.iterator();
	if (!itr.hasNext()) return e;
	
	for (U u : l) {
		e = f.apply(e, u);
	}
	return e;
}


/**
 * <p>
 * foldRight takes a list and reduces it to one item based on the BiFunction starting from the last element in the Iterable
 * <p>
 * <p>
 * Returns a single object after the list has been reduced by the BiFunction. 
 * <p>
 * @param V e: Starting element
 * @param Iterable<U> l: Iterable to be looped through and reduced
 * @param BiFunction<V,U,V> f: The function to be applied to the contents in the Iterable
 */
static <U,V> V foldRight(V e, Iterable<U>l, BiFunction<U,V,V> f){
	Iterator<U> itr = l.iterator();
	if (!itr.hasNext()) return e;
	
	for (U u : l) {
		e = f.apply(u, e);
	}
	return e;
}


/**
 * <p>
 * filter returns items that fit the Predicate 
 * <p>
 * <p>
 * Returns a list with only the elements that satisfy the Predicate. 
 * <p>
 * @param Iterable<U> l: Iterable to be looped through and checked by Predicate
 * @param BPredicate<U> p: Predicate that every element will be checked against
 */
static <U> Iterable<U> filter(Iterable<U> l, Predicate<U> p){
	List<U> list = new ArrayList<U>();
	
	for (U u : l) {
		if (p.test(u))
			list.add(u);
	}
	
	return list;
}


/**
 * <p>
 * minVal returns the smallest value element in the Iterable using foldLeft and no other loops
 * <p>
 * <p>
 * Returns a single object which has the smallest value in the Iterable. 
 * <p>
 * @param Iterable<U> l: Iterable to be looped through folded for the minimum value
 * @param Comparator<U> c: Comparator used to compare the values of elements against each other to get the minimum value.
 */
static <U> U minVal(Iterable<U> l, Comparator<U> c){
	// write using fold.  No other loops permitted. 
	Iterator<U> itr = l.iterator();
	U first = itr.next();
	U result = foldLeft(first,l,(x,y) -> c.compare(x, y) < 0 ? x : y);
	
	return result;
}


/**
 * <p>
 * minPos returns the position of the minimum value in the Iterable using only foldLeft and no other loops.
 * <p>
 * <p>
 * <p>
 * @param Iterable<U> l: Iterable to be looped through folded for the minimum value
 * @param Comparator<U> c: Comparator used to compare the values of elements against each other to get the minimum value.
 */
static <U extends Comparable<U>> int minPos(Iterable<U> l){
	// write using fold.  No other loops permitted.
	Iterator<U> itr = l.iterator();
	U first = itr.next();
	List<Integer> minList = new ArrayList<Integer>();
	minList.add(0);
	
	
	BiFunction<U,U,U> bf = new BiFunction<U,U,U>(){
		int minimum = 0;
		int index = 0;
		U minVal = first;
		
		public U apply(U x, U y) {
			if (y.compareTo(x) < 0) {
				if (y.compareTo(minVal) < 0) {
					minVal = y;
					minimum = index;
					minList.add(minimum);
					index++;
					return y;
				}	
			} else {
				index++;
				return x;
			}
			return x;
		}
	};
	
	foldLeft(first,l,(x,y) -> bf.apply(x, y));
	
	return minList.get(minList.size()-1);
}

	public static void main(String[] args) {
		// (1) Use map to implement the following behavior (described in Python).  i.e given a List<T> create a List<Integer> of the hashes of the objects.
		// names = ['Mary', 'Isla', 'Sam']
		// for i in range(len(names)):
		       // names[i] = hash(names[i])
		
		// (2) Use foldleft to calculate the sum of a list of integers.
		// i.e write a method: int sum(List<Integer> l)
		
		// (3) Use foldRight to concatenate a list of strings i.e write a method
		// String s (List<String> l)
		
		// (4) consider an array of Persons. Use filter to 
		// print the names of the Persons whose salary is
		// greater than 100000
		
		// (5) Use minVal to calculate the minimum of a List of 
		//       Integers
		
        // (6) Use minVal to calculate the maximum of a List of 
		//       Integers
		
		// (7)  Use minPos to calculate the position of the
		// minimum in  a List of  Integers

		// (8)  Use minPos to calculate the position of the
		// minimum in  a List of  String
	}

}

class Person{
	final int salary;
	final String name;
	
	Person(int salary, String name){
		this.salary = salary;
		this.name = name;
	}
	
	int getSalary() { return salary; }
	String name() { return name;}
	
}