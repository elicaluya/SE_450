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
	  
	  VideoObj vid = new VideoObj("A",2000,"B");
	  set.addNumOwned(vid, 1);
	
	  Assert.assertTrue(set.size() == 1);
  }

  public void testAddNumOwned() {
    // TODO  
  }

  public void testCheckOutCheckIn() {
    // TODO  
  }

  public void testClear() {
    // TODO  
	  InventorySet set = new InventorySet();
	  Assert.assertTrue(set.size() == 0);
	  VideoObj vid = new VideoObj("A",2000,"B");
	  set.addNumOwned(vid, 1);
	  Assert.assertTrue(set.size() == 1);
	  set.clear();
	  Assert.assertTrue(set.size() == 0);
  }

  public void testGet() {
    // TODO  
	  VideoObj vid = new VideoObj("A",2000,"B");
	  Record record = new Record(vid,20,10,300);
  }

  public void testToCollection() {
    // Be sure to test that changing records in the returned
    // collection does not change the original records in the
    // inventory.  ToCollection should return a COPY of the records,
    // not the records themselves.
    // TODO  
  }
}
