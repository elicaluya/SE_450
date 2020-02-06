package shop.data;

import junit.framework.Assert;
import junit.framework.TestCase;

// TODO:  complete the tests
public class VideoTEST extends TestCase {
  public VideoTEST(String name) {
    super(name);
  }
  public void testEquals() { 
    // TODO
	  String title = "A";
	  int year = 2009;
	  String director = "Zebra";
	  VideoObj a = new VideoObj(title,year,director);
	  Assert.assertTrue( a.equals(a) );
	  Assert.assertTrue( a.equals( new VideoObj(title, year, director) ) );
	  Assert.assertTrue( a.equals( new VideoObj(new String(title), year, director) ) );
	  Assert.assertTrue( a.equals( new VideoObj(title, year, new String(director)) ) );
	  Assert.assertFalse( a.equals( new VideoObj(title+"1", year, director) ) );
	  Assert.assertFalse( a.equals( new VideoObj(title, year+1, director) ) );
	  Assert.assertFalse( a.equals( new VideoObj(title, year, director+"1") ) );
	  Assert.assertFalse( a.equals( new Object() ) );
	  Assert.assertFalse( a.equals( null ) );
  }

  public void testCompareTo() { 
    // TODO  
	  String title = "A", title2 = "B";
	  int year = 2009, year2 = 2010;
	  String director = "Zebra", director2 = "Zzz";
	  VideoObj a = new VideoObj(title,year,director);
	  VideoObj b = new VideoObj(title2,year,director);
	  Assert.assertTrue( a.compareTo(b) < 0 );
	  Assert.assertTrue( a.compareTo(b) == -b.compareTo(a) );
	  Assert.assertTrue( a.compareTo(a) == 0 );
	  
	  b = new VideoObj(title,year2,director);
	  Assert.assertTrue( a.compareTo(b) < 0 );
	  Assert.assertTrue( a.compareTo(b) == -b.compareTo(a) );
	  
	  b = new VideoObj(title,year,director2);
	  Assert.assertTrue( a.compareTo(b) < 0 );
	  Assert.assertTrue( a.compareTo(b) == -b.compareTo(a) );
	  
	  b = new VideoObj(title2,year2,director2);
	  Assert.assertTrue( a.compareTo(b) < 0 );
	  Assert.assertTrue( a.compareTo(b) == -b.compareTo(a) );
  }	

  public void testToString() { 
    // TODO  
	  String s = new VideoObj("A",2000,"B").toString();
	  Assert.assertEquals( s, "A (2000) : B" );
	  s = new VideoObj(" A ",2000," B ").toString();
	  Assert.assertEquals( s, "A (2000) : B" );
  }

  public void testHashCode() {
    Assert.assertEquals
      (-875826552,
       new VideoObj("None", 2009, "Zebra").hashCode());
    Assert.assertEquals
      (-1391078111,
       new VideoObj("Blah", 1954, "Cante").hashCode());
  }

}
