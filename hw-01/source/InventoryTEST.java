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
	  InventorySet set = new InventorySet();
	  Assert.assertTrue(set.size() == 0);
	  
	  set.addNumOwned(new VideoObj("A",2000,"B"), 1);
	  Assert.assertTrue(set.size() == 1);
	  
	  set.addNumOwned(new VideoObj("B",2000,"C"), 2);
	  Assert.assertTrue(set.size() == 2);
	  
	  set.addNumOwned(new VideoObj("C",2000,"D"), 3);
	  Assert.assertTrue(set.size() == 3);
  }

  public void testAddNumOwned() {
    // TODO  
	  InventorySet set = new InventorySet();
	  VideoObj vid1 = new VideoObj("A",2000,"B");
	  set.addNumOwned(vid1, 1);
	  Assert.assertTrue(set.get(vid1).numOwned == 1);
	  
	  set.addNumOwned(vid1, 3);
	  Assert.assertTrue(set.get(vid1).numOwned == 4);
	  
	  set.addNumOwned(vid1, -2);
	  Assert.assertTrue(set.get(vid1).numOwned == 2);
	  
	  set.addNumOwned(vid1, -2);
	  Assert.assertTrue(set.size() == 0);
	  
	  VideoObj vid2 = new VideoObj("B",2001,"C");
	  set.addNumOwned(vid2, 2);
	  set.addNumOwned(vid1, 1);
	  Assert.assertTrue(set.get(vid2).numOwned == 2);
	  Assert.assertTrue(set.get(vid1).numOwned == 1);
	  
	  set.addNumOwned(vid2, -2);
	  Assert.assertTrue(set.size() == 1);
  }

  public void testCheckOutCheckIn() {
    // TODO  
	  InventorySet set = new InventorySet();
	  VideoObj vid = new VideoObj("A",2000,"B");
	  
	  try {
		  set.checkOut(vid);
		  Assert.fail();
	  } catch (IllegalArgumentException e) {}
	  try {
		  set.checkIn(vid);
		  Assert.fail();
	  } catch (IllegalArgumentException e) {}
	  
	  set.addNumOwned(vid, 2);
	  
	  try {
		  set.checkIn(vid);
		  Assert.fail();
	  } catch (IllegalArgumentException e) {}
	  
	  set.checkOut(vid);
	  Assert.assertTrue(set.get(vid).numOut == 1);
	  Assert.assertTrue(set.get(vid).numRentals == 1);
	  set.checkOut(vid);
	  Assert.assertTrue(set.get(vid).numOut == 2);
	  Assert.assertTrue(set.get(vid).numRentals == 2);
	  
	  try {
		  set.checkOut(vid);
		  Assert.fail();
	  } catch (IllegalArgumentException e) {}
	  
	  set.checkIn(vid);
	  Assert.assertTrue(set.get(vid).numOut == 1);
	  set.checkIn(vid);
	  Assert.assertTrue(set.get(vid).numOut == 0);
	  
	  set.checkOut(vid);
	  Assert.assertTrue(set.get(vid).numOut == 1);
	  Assert.assertTrue(set.get(vid).numRentals == 3);
  }

  public void testClear() {
    // TODO  
	  InventorySet set = new InventorySet();
	  Assert.assertTrue(set.size() == 0);
	  set.addNumOwned(new VideoObj("A",2000,"B"), 1);
	  Assert.assertTrue(set.size() == 1);
	  set.clear();
	  Assert.assertTrue(set.size() == 0);
  }

  public void testGet() {
    // TODO  
	  InventorySet set = new InventorySet();
	  VideoObj vid1 = new VideoObj("A",2000,"B");
	  set.addNumOwned(vid1, 1);
	  Record record1 = new Record(vid1,1,0,0);
	  Record record2 = new Record(vid1,2,0,0);
	  Assert.assertTrue(set.get(vid1).toString().equals(record1.toString()));
	  Assert.assertFalse(set.get(vid1).toString().equals(record2.toString()));
	  
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
	  set.addNumOwned(vid1, 1);
	  set.addNumOwned(vid2, 2);
	  
	  Record rec1 = new Record(vid1,1,0,0);
	  Record rec2 = new Record(vid2,2,0,0);
	  Record rec3 = new Record(vid3,3,0,0);

	  
	  Iterator<Record> it = set.toCollection().iterator();
	  Assert.assertTrue(it.next().toString().equals(rec1.toString()));
	  Assert.assertTrue(it.next().toString().equals(rec2.toString()));
	  Assert.assertFalse(it.hasNext());
	  
	  set.toCollection().add(rec3);
	  Assert.assertFalse(it.hasNext());  
  }
}
