import java.util.List;
import java.util.function.Function;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.ArrayList;
import java.util.Comparator;

public class fp {
	
static <U,V> List<V> map(Iterable<U> l, Function<U,V> f) {
	List<V> list = new ArrayList<V>();
	for (U u : l) {
		list.add(f.apply(u));
	}
	return list;
}


static <U,V> V foldLeft(V e, Iterable<U>l, BiFunction<V,U,V> f){
	for (U u : l) {
		e = f.apply(e, u);
	}
	return e;
}


static <U,V> V foldRight(V e, Iterable<U>l, BiFunction<U,V,V> f){
	for (U u : l) {
		e = f.apply(u, e);
	}
	return e;
}



static <U> Iterable<U> filter(Iterable<U> l, Predicate<U> p){
	List<U> list = new ArrayList<U>();
	
	for (U u : l) {
		if (p.test(u))
			list.add(u);
	}
	
	return list;
}

static <U> U minVal(Iterable<U> l, Comparator<U> c){
	// write using fold.  No other loops permitted. 
	return null;
}

static <U extends Comparable<U>> int minPos(Iterable<U> l){
	// write using fold.  No other loops permitted. 
	return 0;
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