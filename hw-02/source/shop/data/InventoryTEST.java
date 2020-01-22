package shop.data;

import junit.framework.Assert;
import junit.framework.TestCase;

// TODO:  complete the tests
public class InventoryTEST extends TestCase {
  public InventoryTEST(String name) {
    super(name);
  }

  // TODO 
  
  InventorySet s = new InventorySet();
  final VideoObj v1 = new VideoObj( "A", 2000, "B" );
  final VideoObj v1copy = new VideoObj( "A", 2000, "B" );
  final VideoObj v2 = new VideoObj( "B", 2000, "B" );
  
  public void testSize() {
    // TODO  
      Assert.assertEquals( 0, s.size() );
      s.addNumOwned(v1,  1); Assert.assertEquals( 1, s.size() );
      s.addNumOwned(v1,  2); Assert.assertEquals( 1, s.size() );
      s.addNumOwned(v2,  1); Assert.assertEquals( 2, s.size() );
      s.addNumOwned(v2, -1); Assert.assertEquals( 1, s.size() );
      s.addNumOwned(v1, -3); Assert.assertEquals( 0, s.size() );
      try { s.addNumOwned(v1, -3); } catch ( IllegalArgumentException e ) {}
      Assert.assertEquals( 0, s.size() );
  }

  public void testAddNumOwned() {
    // TODO  
  }

  public void testCheckOutCheckIn() {
    // TODO  
  }

  public void testClear() {
    // TODO
	  s.addNumOwned(v1, 2); Assert.assertEquals( 1, s.size() );
      s.addNumOwned(v2, 2); Assert.assertEquals( 2, s.size() );
      s.clear();            Assert.assertEquals( 0, s.size() );
      try { s.checkOut(v2);       Assert.fail(); } catch ( IllegalArgumentException e ) {}
  }

  public void testGet() {
    // TODO
	  s.addNumOwned(v1, 1);
	  Record r1 = s.get(v1);
	  Record r2 = s.get(v1);
	  Assert.assertFalse( r1.equals(r2) );
	  Assert.assertTrue( r1 != r2 );
  }

  public void testIterator1() {
    // TODO  
  }
  public void testIterator2() {
    // TODO  
  }

}
