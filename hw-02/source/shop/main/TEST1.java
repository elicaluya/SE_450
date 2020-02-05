package shop.main;

import junit.framework.Assert;
import junit.framework.TestCase;
import shop.command.Command;
import shop.data.Data;
import shop.data.Record;
import shop.data.Video;
import shop.data.Inventory;
import java.util.Iterator;

// TODO:
// write an integration test that tests the data classes.
// add in some videos, check out, check in, delete videos, etc.
// check that errors are reported when necessary.
// check that things are going as expected.
public class TEST1 extends TestCase {
  private Inventory _inventory = Data.newInventory();
  public TEST1(String name) {
    super(name);
  }
  private void expect(Video v, String s) {
    Assert.assertEquals(s,_inventory.get(v).toString());
  }
  private void expect(Record r, String s) {
    Assert.assertEquals(s,r.toString());
  }
  public void test1() {
    Command clearCmd = Data.newClearCmd(_inventory);
    clearCmd.run();
    
    Video v1 = Data.newVideo("Title1", 2000, "Director1");
    Assert.assertEquals(0, _inventory.size());
    Assert.assertTrue(Data.newAddCmd(_inventory, v1, 5).run());
    Assert.assertEquals(1, _inventory.size());
    Assert.assertTrue(Data.newAddCmd(_inventory, v1, 5).run());
    Assert.assertEquals(1, _inventory.size());
    // System.out.println(_inventory.get(v1));
    expect(v1,"Title1 (2000) : Director1 [10,0,0]");
    
    // TODO  
    // Create new Video object
    Video v2 = Data.newVideo("A", 2001, "B");
    Assert.assertTrue(Data.newAddCmd(_inventory, v2, 3).run());
    Assert.assertEquals(_inventory.size(), 2);
    Assert.assertEquals(_inventory.get(v2).numOwned(), 3);
    expect(v2,"A (2001) : B [3,0,0]");
    
    // Check out v1 and v2
    Assert.assertTrue(Data.newOutCmd(_inventory, v2).run());
    Assert.assertTrue(Data.newOutCmd(_inventory, v1).run());
    Assert.assertEquals(_inventory.get(v2).numOut(), 1);
    Assert.assertEquals(_inventory.get(v1).numOut(), 1);
    Assert.assertEquals(_inventory.get(v2).numRentals(), 1);
    Assert.assertEquals(_inventory.get(v1).numRentals(), 1);
    expect(v2,"A (2001) : B [3,1,1]");
    expect(v1,"Title1 (2000) : Director1 [10,1,1]");
    
    // Check out v2 again
    Assert.assertTrue(Data.newOutCmd(_inventory, v2).run());
    Assert.assertEquals(_inventory.get(v2).numOut(), 2);
    Assert.assertEquals(_inventory.get(v2).numRentals(), 2);
    expect(v2,"A (2001) : B [3,2,2]");

    
    // Add more v2 to the inventory
    Assert.assertTrue(Data.newAddCmd(_inventory, v2, 3).run());
    Assert.assertEquals(_inventory.get(v2).numOwned(), 6);
    expect(v2,"A (2001) : B [6,2,2]");

    // Check in all v2 back into the inventory
    Assert.assertTrue(Data.newInCmd(_inventory, v2).run());
    Assert.assertEquals(_inventory.get(v2).numOut(), 1);
    expect(v2,"A (2001) : B [6,1,2]");
    Assert.assertTrue(Data.newInCmd(_inventory, v2).run());
    Assert.assertEquals(_inventory.get(v2).numOut(), 0);
    expect(v2,"A (2001) : B [6,0,2]");
    
    // To test number of rentals counter
    Assert.assertTrue(Data.newOutCmd(_inventory, v2).run());
    Assert.assertEquals(_inventory.get(v2).numOut(), 1);
    expect(v2,"A (2001) : B [6,1,3]");
    
    // Create new Video object v3 and add it to inventory
    Video v3 = Data.newVideo("C", 2002, "D");
    Assert.assertTrue(Data.newAddCmd(_inventory, v3, 7).run());
    Assert.assertEquals(_inventory.size(), 3);
    Assert.assertEquals(_inventory.get(v3).numOwned(), 7);
    expect(v3,"C (2002) : D [7,0,0]");
    
    
    // Create iterator based on amount owned
    Iterator<Record> it1 = _inventory.iterator(new java.util.Comparator<Record>() {
        public int compare (Record r1, Record r2) {
        	return r2.numOwned() - r1.numOwned();
        }
    });
    
    expect(it1.next() ,"Title1 (2000) : Director1 [10,1,1]");
    expect(it1.next(), "C (2002) : D [7,0,0]");
    expect(it1.next() ,"A (2001) : B [6,1,3]");
    Assert.assertFalse(it1.hasNext());
    
    
 // Create iterator based on number of rentals
    Iterator<Record> it2 = _inventory.iterator(new java.util.Comparator<Record>() {
        public int compare (Record r1, Record r2) {
        	return r2.numRentals() - r1.numRentals();
        }
    });
    
    expect(it2.next() ,"A (2001) : B [6,1,3]");
    expect(it2.next() ,"Title1 (2000) : Director1 [10,1,1]");
    expect(it2.next(), "C (2002) : D [7,0,0]");
    Assert.assertFalse(it2.hasNext());
  }
}
