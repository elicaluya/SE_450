
import java.util.*; // for comparing arrays in main() tests only

class AssertExp1 {

	/**
	 * <p>
	 * minValue returns the minimum value in an array of doubles. 
	 * <p>
	 * <p><b>Invariant: </b> array is nonempty and has no duplicates</p>
	 * <p><b>Invariant: </b> Must go through array exactly once</p>
	 * <p><b>Invariant: </b> Must not call any other functions</p>
	 * @param double[] array to determine the minimum value
	 */
	public static double minValue(double[] list) {
		double min = list[0];
		for (int i = 0; i < list.length; ++i) {
			if (list[i] < min) {
				min = list[i];
			}
		}
		return min;
	}

	/**
	 * <p>
	 * minPosition returns the position of the minimum value in an array of
	 * doubles. 
	 * </p>
	 * <p><b>Invariant: </b> array is nonempty and has no duplicates</p>
	 * <p><b>Invariant: </b> Must go through array exactly once</p>
	 * <p><b>Invariant: </b> Must not call any other functions</p>
	 * @param double[] array to determine the minimum position
	 * 
	 */
	public static int minPosition(double[] list) {
		 // TODO
		// Assert to check that parameter is not null and list is not empty
		assert(list != null && list.length != 0);
		
		// Set minimum position to the first element's index
		int minPos = 0;
		double minVal = list[0];
		
		// Loop through list and see if any value is smaller than the minimum value
		for (int i = 0; i < list.length; i++) {
			// If there is a smaller value, save its index
			if (list[i] < minVal) {
				minVal = list[i];
				minPos = i;
			}
		}
		
		return minPos;
	}


	/**
	 * <p>
	 * numUnique returns the number of unique values in an array of doubles.
	 * </p>
	 * 
	 * <p><b>Invariant: </b> Array is sorted, nonempty, and may contain duplicates</p>
	 * <p><b>Invariant: </b> Must go through array exactly once</p>
	 * <p><b>Invariant: </b> Must not call any other functions</p>
	 * @param double[] array to determine the number of unique numbers
	 * 
	 */
	public static int numUnique(double[] list) {
		// TODO
		// Check that the list is not null
		assert(list != null);
		
		// if the list is empty
		if (list.length == 0) return 0;
		
		// At this point the list is non empty
		assert(list.length >= 1);
		
		int count = 1;
		
		if (list.length > 1) {
			// Loop through list and if the next value is different than the last then increase the count 
			for (int i = 1; i < list.length; i++) {
				// Check that the list is sorted
				assert(list[i-1] <= list[i]);
				if (list[i] != list[i-1])
					count++;
			}
		}
		
		// make sure there is at least one unique number in a non empty list
		assert(count > 0);
		
		return count;	
	}

	/**
	 * <p>
	 * removeDuplicates returns the number of unique values in an array of
	 * doubles. You may assume that the list is sorted, as you did for
	 * numUnique.
	 * </p>
	 * <p><b>Invariant: </b> Array is sorted and may contain duplicates</p>
	 * <p><b>Invariant: </b> Must go through array exactly once after calling numUnique</p>
	 * <p><b>Invariant: </b> Must not call any other functions except numUnique</p>
	 * @param double[] array to remove duplicates from
	 */
	public static double[] removeDuplicates(double[] list) {
		//TODO
		// Check that list is not null
		assert(list != null);
		
		// if given an empty list return an empty list
		if (list.length == 0) return new double[0];
		
		// From this point on the list should be non empty
		assert(list.length >= 1);
		
		// Create an array of size of the number of unique elements in the list
		double[] array = new double[numUnique(list)];
		int j = 0;
		array[j] = list[0];
		
		if (list.length > 1) {
			// Loop through list and if an element is not in the array, add it. Otherwise keep indexing through 
			for (int i = 1; i < list.length; i++) {
				// Check that list is assorted
				assert(list[i-1] <= list[i]);
				if (list[i] != array[j]) {
					j++;
					array[j] = list[i];
				}
			}
		}
		
		// Make sure that the new array is fully filled with the unique numbers
		assert(!Arrays.asList(array).contains(null));
			
		return array;
	}

}
