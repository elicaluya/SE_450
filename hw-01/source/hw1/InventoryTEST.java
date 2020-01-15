package hw1;
import junit.framework.Assert;
import junit.framework.TestCase;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;

// TODO:  complete the tests
public class InventoryTEST extends TestCase {
  public InventoryTEST(String name) {
    super(name);
  }

  // TODO  
  
  public void testSize() {
    // TODO
	  // new InventorySet should have size == 0
	  InventorySet set = new InventorySet();
	  Assert.assertTrue(set.size() == 0);
	  
	  // Add one VideoObj to the set so size == 1
	  set.addNumOwned(new VideoObj("A",2000,"B"), 1);
	  Assert.assertTrue(set.size() == 1);
	  
	  // Add a second VideoObj so size == 2
	  set.addNumOwned(new VideoObj("B",2000,"C"), 2);
	  Assert.assertTrue(set.size() == 2);
	  
	  // Add a third VideoObj so size == 3
	  set.addNumOwned(new VideoObj("C",2000,"D"), 3);
	  Assert.assertTrue(set.size() == 3);
	  
	  // Add a duplicate so size shouldn't change since record already exists
	  set.addNumOwned(new VideoObj("B",2000,"C"), 2);
	  Assert.assertTrue(set.size() == 3);
  }

  public void testAddNumOwned() {
    // TODO  
	  InventorySet set = new InventorySet();
	  VideoObj vid1 = new VideoObj("A",2000,"B");
	  
	  // Add 1 copy of vid1 so numOwned == 1
	  set.addNumOwned(vid1, 1);
	  Assert.assertTrue(set.get(vid1).numOwned == 1);
	  
	  // Add 3 more copies of vid1 so numOwned == 4
	  set.addNumOwned(vid1, 3);
	  Assert.assertTrue(set.get(vid1).numOwned == 4);
	  
	  // Remove 2 copies of vid1 so numOwned == 2
	  set.addNumOwned(vid1, -2);
	  Assert.assertTrue(set.get(vid1).numOwned == 2);
	  
	  // Remove the last copies of vid1 so it is removed from the inventory
	  // Since it was the only item in the inventory, size == 0
	  set.addNumOwned(vid1, -2);
	  Assert.assertTrue(set.size() == 0);
	  
	  VideoObj vid2 = new VideoObj("B",2001,"C");
	  set.addNumOwned(vid2, 2);
	  set.addNumOwned(vid1, 1);
	  
	  // When multiple entries in Inventory, you can get individual values
	  Assert.assertTrue(set.get(vid2).numOwned == 2);
	  Assert.assertTrue(set.get(vid1).numOwned == 1);
	  
	  // Test removing video from inventory while leaving one remaining
	  set.addNumOwned(vid2, -2);
	  Assert.assertTrue(set.size() == 1);
  }

  public void testCheckOutCheckIn() {
    // TODO  
	  InventorySet set = new InventorySet();
	  VideoObj vid = new VideoObj("A",2000,"B");
	  
	  // Test checking in and out on an empty Inventory
	  try {
		  set.checkOut(vid);
		  Assert.fail();
	  } catch (IllegalArgumentException e) {}
	  try {
		  set.checkIn(vid);
		  Assert.fail();
	  } catch (IllegalArgumentException e) {}
	  
	  // Add two copies of a video to the set
	  set.addNumOwned(vid, 2);
	  
	  // Test checking out a video that has not been checked out yet
	  try {
		  set.checkIn(vid);
		  Assert.fail();
	  } catch (IllegalArgumentException e) {}
	  
	  // Test that checking out a video increases the numOut and numRentals values of its Record
	  set.checkOut(vid);
	  Assert.assertTrue(set.get(vid).numOut == 1);
	  Assert.assertTrue(set.get(vid).numRentals == 1);
	  // Check out the second copy and the values should increase accordingly
	  set.checkOut(vid);
	  Assert.assertTrue(set.get(vid).numOut == 2);
	  Assert.assertTrue(set.get(vid).numRentals == 2);
	  
	  // Test checking out a video that has all copies already checked out
	  try {
		  set.checkOut(vid);
		  Assert.fail();
	  } catch (IllegalArgumentException e) {}
	  
	  // Check in one copy of the video and the numOut value decreases
	  set.checkIn(vid);
	  Assert.assertTrue(set.get(vid).numOut == 1);
	  // Check in the last copy of the video so numOut ==  0
	  set.checkIn(vid);
	  Assert.assertTrue(set.get(vid).numOut == 0);
	  
	  // Check out video again to test that the numRentals value increases everytime video is checked out
	  set.checkOut(vid);
	  Assert.assertTrue(set.get(vid).numOut == 1);
	  Assert.assertTrue(set.get(vid).numRentals == 3);
  }

  public void testClear() {
    // TODO  
	  // Comparing sizes after clearing an InventorySet
	  InventorySet set = new InventorySet();
	  Assert.assertTrue(set.size() == 0);
	  set.addNumOwned(new VideoObj("A",2000,"B"), 1);
	  Assert.assertTrue(set.size() == 1);

	  // After clear() is called, the size of the inventory should be 0
	  set.clear();
	  Assert.assertTrue(set.size() == 0);
  }

  public void testGet() {
    // TODO  
	  // Create new InventorySet and add 1 VideoObj to it
	  InventorySet set = new InventorySet();
	  VideoObj vid1 = new VideoObj("A",2000,"B");
	  set.addNumOwned(vid1, 1);
	  
	  // Sample Records to test against
	  Record record1 = new Record(vid1,1,0,0);
	  Record record2 = new Record(vid1,2,0,0);
	  
	  // Test that the record returned from get() is the same as the correct sample record
	  Assert.assertTrue(set.get(vid1).toString().equals(record1.toString()));
	  // Test that the record returned from get() doesn't match the incorrect sample record
	  Assert.assertFalse(set.get(vid1).toString().equals(record2.toString()));
	  
	  // Test get() method with multiple entries in the InventorySet
	  VideoObj vid2 = new VideoObj("B",2001,"C");
	  set.addNumOwned(vid2, 3);
	  Record record3 = new Record(vid2,3,0,0);
	  Record record4 = new Record(vid2,4,0,0);
	  Assert.assertTrue(set.get(vid2).toString().equals(record3.toString()));
	  Assert.assertFalse(set.get(vid2).toString().equals(record4.toString()));
  }

  public void testToCollection() {
    // Be sure to test that changing records in the returned
    // collection does not change the original records in the
    // inventory.  ToCollection should return a COPY of the records,
    // not the records themselves.
    // TODO 
	  InventorySet set = new InventorySet();
	  VideoObj vid1 = new VideoObj("A",2000,"B");
	  VideoObj vid2 = new VideoObj("B",2001,"C");
	  VideoObj vid3 = new VideoObj("C",2002,"D");
	  
	  // Add two VideoObj to InventorySet
	  set.addNumOwned(vid1, 1);
	  set.addNumOwned(vid2, 2);
	  
	  // Sample records to test against
	  Record rec1 = new Record(vid1,1,0,0);
	  Record rec2 = new Record(vid2,2,0,0);
	  Record rec3 = new Record(vid3,3,0,0);

	  // Test that collection returned from toCollection() method returns the correct data
	  Iterator<Record> it = set.toCollection().iterator();
	  // Iterate through collection to make sure they match the correct sample Records
	  Assert.assertTrue(it.next().toString().equals(rec1.toString()));
	  Assert.assertTrue(it.next().toString().equals(rec2.toString()));
	  // Test that there are only two entries in the collection from the InventorySet
	  Assert.assertFalse(it.hasNext());
	  
	  // Add to the collection and test that it will not affect the collection of the inventory since a copy of the records are returned.
	  set.toCollection().add(rec3);
	  Assert.assertFalse(it.hasNext());  
  }
}
