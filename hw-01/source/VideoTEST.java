import junit.framework.Assert;
import junit.framework.TestCase;

// TODO:  complete the tests
public class VideoTEST extends TestCase {
  public VideoTEST(String name) {
    super(name);
  }
  public void testConstructorAndAttributes() {
    String title1 = "XX";
    String director1 = "XY";
    String title2 = " XX ";
    String director2 = " XY ";
    int year = 2002;

    VideoObj v1 = new VideoObj(title1, year, director1);
    Assert.assertSame(title1, v1.title());
    Assert.assertEquals(year, v1.year());
    Assert.assertSame(director1, v1.director());

    VideoObj v2 = new VideoObj(title2, year, director2);
    Assert.assertEquals(title1, v2.title());
    Assert.assertEquals(director1, v2.director());
  }

  public void testConstructorExceptionYear() {
    try {
      new VideoObj("X", 1800, "Y");
      Assert.fail();
    } catch (IllegalArgumentException e) { }
    try {
      new VideoObj("X", 5000, "Y");
      Assert.fail();
    } catch (IllegalArgumentException e) { }
    try {
      new VideoObj("X", 1801, "Y");
      new VideoObj("X", 4999, "Y");
    } catch (IllegalArgumentException e) {
      Assert.fail();
    }
  }

  public void testConstructorExceptionTitle() {
    try {
      new VideoObj(null, 2002, "Y");
      Assert.fail();
    } catch (IllegalArgumentException e) { }
    try {
      new VideoObj("", 2002, "Y");
      Assert.fail();
    } catch (IllegalArgumentException e) { }
    try {
      new VideoObj(" ", 2002, "Y");
      Assert.fail();
    } catch (IllegalArgumentException e) { }
  }

  public void testConstructorExceptionDirector() {
    // TODO  
	  // Test to see if giving a null value will throw IllegalArgumentException
	  try {
		  new VideoObj("X", 2000,null);
		  Assert.fail();
	  } catch (IllegalArgumentException e) {}
	  
	  // Test if giving an empty string will throw IllegalArgumentException
	  try {
		  new VideoObj("X", 2000,"");
		  Assert.fail();
	  } catch (IllegalArgumentException e) {}
	  
	  // Test if empty string is detected after trimming the leading and trailing white spaces
	  try {
		  new VideoObj("X", 2000," ");
		  Assert.fail();
	  } catch (IllegalArgumentException e) {}
  }

  public void testHashCode() {
    Assert.assertEquals
      (-875826552,
       new VideoObj("None", 2009, "Zebra").hashCode());
    Assert.assertEquals
      (-1391078111,
       new VideoObj("Blah", 1954, "Cante").hashCode());
  }

  public void testEquals() { 
    // TODO
	  // Create VideoObj objects to test equality
	  VideoObj obj1 = new VideoObj("A",1900,"B");
	  VideoObj obj2 = new VideoObj("A",1900,"B");
	  VideoObj obj3 = new VideoObj("B",2000,"C");
	  
	  // Test if two objects are equal
	  Assert.assertTrue(obj1.equals(obj2));
	  // Test if two objects are not equal
	  Assert.assertFalse(obj1.equals(obj3));
  }

  public void testCompareTo() { 
    // TODO
	  // Create VideoObj objects for testing comparisons to each other
	  VideoObj obj1 = new VideoObj("A",1900,"B");
	  VideoObj obj2 = new VideoObj("A",1900,"B");
	  VideoObj obj3 = new VideoObj("B",1900,"B");
	  VideoObj obj4 = new VideoObj("A",2000,"B");
	  VideoObj obj5 = new VideoObj("A",2000,"C");
	
	  // Test that two objects are equal using compareTo() method
	  Assert.assertTrue(obj1.compareTo(obj2) == 0);
	  // Test that obj1 is less than obj3 based on title
	  Assert.assertTrue(obj1.compareTo(obj3) == -1);
	  // Test that obj1 is less than obj4 based on year
	  Assert.assertTrue(obj1.compareTo(obj4) == -1);
	  // Test that obj1 is less than obj5 based on director
	  Assert.assertTrue(obj1.compareTo(obj5) == -1);
	  // Test that obj3 is greater than obj1 based on title
	  Assert.assertTrue(obj3.compareTo(obj1) == 1);
	  // Test that obj4 is greater than obj1 based on year
	  Assert.assertTrue(obj4.compareTo(obj1) == 1);
	  // Test that obj5 is greater than obj1 based on director
	  Assert.assertTrue(obj5.compareTo(obj1) == 1);
  }

  public void testToString() { 
    // TODO 
	  VideoObj obj1 = new VideoObj("Title",2020,"Director");
	  // Test that obj1 produces the correct string
	  Assert.assertTrue((obj1.toString()).equals("Title (2020) : Director"));
	  // Test against an incorrect string
	  Assert.assertFalse((obj1.toString()).equals("titl (2021) : directo"));
  }
}
