package shop.data;

import junit.framework.Assert;
import junit.framework.TestCase;
import java.util.*;

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
      Assert.assertEquals( 0, s.size() );
      s.addNumOwned(v1, 1);     Assert.assertEquals( v1, s.get(v1).video() );
      Assert.assertEquals( 1, s.get(v1).numOwned() );
      s.addNumOwned(v1, 2);     Assert.assertEquals( 3, s.get(v1).numOwned() );
      s.addNumOwned(v1, -1);    Assert.assertEquals( 2, s.get(v1).numOwned() );
      s.addNumOwned(v2, 1);     Assert.assertEquals( 2, s.get(v1).numOwned() );
      s.addNumOwned(v1copy, 1); Assert.assertEquals( 3, s.get(v1).numOwned() );
      Assert.assertEquals( 2, s.size() );
      s.addNumOwned(v1, -3);    
      Assert.assertEquals( 1, s.size() );
      try { s.addNumOwned(null, 1);   Assert.fail(); } catch ( IllegalArgumentException e ) {}
  }

  public void testCheckOutCheckIn() {
    // TODO 
	  try { s.checkOut(null);     Assert.fail(); } catch ( IllegalArgumentException e ) {}
	  try { s.checkIn(null);      Assert.fail(); } catch ( IllegalArgumentException e ) {}
	  		s.addNumOwned(v1, 2); Assert.assertTrue( s.get(v1).numOut() == 0 && s.get(v1).numRentals() == 0 );
	  		s.checkOut(v1);       Assert.assertTrue( s.get(v1).numOut() == 1 && s.get(v1).numRentals() == 1 );
	  		s.addNumOwned(v1,-1); Assert.assertTrue( s.get(v1).numOut() == 1 && s.get(v1).numRentals() == 1 );
	  		s.addNumOwned(v1, 1); Assert.assertTrue( s.get(v1).numOut() == 1 && s.get(v1).numRentals() == 1 );
	  		s.checkOut(v1);       Assert.assertTrue( s.get(v1).numOut() == 2 && s.get(v1).numRentals() == 2 );
	  try { s.checkOut(v1);       Assert.fail(); } catch ( IllegalArgumentException e ) {}
	  		s.checkIn(v1);        Assert.assertTrue( s.get(v1).numOut() == 1 && s.get(v1).numRentals() == 2 );
	  		s.checkIn(v1copy);    Assert.assertTrue( s.get(v1).numOut() == 0 && s.get(v1).numRentals() == 2 );
	  try { s.checkIn(v1);        Assert.fail(); } catch ( IllegalArgumentException e ) {}
	  try { s.checkOut(v2);       Assert.fail(); } catch ( IllegalArgumentException e ) {}
	  		s.checkOut(v1);       Assert.assertTrue( s.get(v1).numOut() == 1 && s.get(v1).numRentals() == 3 );
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
	  Assert.assertTrue( r1.equals(r2) );
	  Assert.assertTrue( r1 == r2 );
  }

  public void testIterator1() {
    // TODO 
	  Set<Video> expected = new HashSet<Video>();
	  InventorySet inv = new InventorySet();
	  Video v1 = new VideoObj("XX", 2004, "XX");
	  Video v2 = new VideoObj("XY", 2000, "XY");
	  inv.addNumOwned(v1,10);
	  inv.addNumOwned(v2,20);
	  expected.add(v1);
	  expected.add(v2);

	  Iterator<Record> i = inv.iterator();
	  try { i.remove(); fail(); } catch (UnsupportedOperationException e) { }
	  while(i.hasNext()) {
		  Record r = i.next();
		  assertTrue(expected.contains(r.video()));
		  expected.remove(r.video());
	  }
	  assertTrue(expected.isEmpty());
  }
  public void testIterator2() {
    // TODO
	  InventorySet inv = new InventorySet();
	  Video v1 = new VideoObj("XX", 2004, "XX");
	  Video v2 = new VideoObj("XY", 2000, "XY");
	  inv.addNumOwned(v1,10);
	  inv.addNumOwned(v2,20);
	  
	  List<Video> expected = new ArrayList<Video>();
	  expected.add(v2);
	  expected.add(v1);

	  {
		  Comparator<Record> c = (r1, r2) -> r1.video().year() - r2.video().year();
		  Iterator<Record> i = inv.iterator(c);
		  try { i.remove(); fail(); } catch (UnsupportedOperationException e) { }
		  assertSame(v2, i.next().video());
		  assertSame(v1, i.next().video());
		  assertFalse(i.hasNext());
	  }
	  {
		  Comparator<Record> c = (r1, r2) -> r2.video().year() - r1.video().year();
		  Iterator<Record> i = inv.iterator(c);
		  assertSame(v1, i.next().video());
		  assertSame(v2, i.next().video());
		  assertFalse(i.hasNext());
	  }
	  
	  Comparator<Record> c = new Comparator<Record>() {
	        public int compare(Record r1, Record r2) {
	          return r1.video().year() - r2.video().year();
	        }
	      };
	    Iterator<Record> i = inv.iterator(c);
	    try { i.remove(); Assert.fail(); }
	    catch (UnsupportedOperationException e) { }
	    Iterator j = expected.iterator();
	    while (i.hasNext()) {
	      Assert.assertSame(j.next(), i.next().video());
	      j.remove();
	    }
	    Assert.assertTrue(expected.isEmpty());
  }

}