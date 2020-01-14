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
	try {
		new VideoObj("X", 2000,null);
		Assert.fail();
	} catch (IllegalArgumentException e) {}
	try {
		new VideoObj("X", 2000,"");
		Assert.fail();
	} catch (IllegalArgumentException e) {}
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
	VideoObj obj1 = new VideoObj("A",1900,"B");
	VideoObj obj2 = new VideoObj("A",1900,"B");
	VideoObj obj3 = new VideoObj("B",2000,"C");
	Assert.assertTrue(obj1.equals(obj2));
	Assert.assertFalse(obj1.equals(obj3));
  }

  public void testCompareTo() { 
    // TODO
	  
	VideoObj obj1 = new VideoObj("A",1900,"B");
	VideoObj obj2 = new VideoObj("A",1900,"B");
	VideoObj obj3 = new VideoObj("B",1900,"B");
	VideoObj obj4 = new VideoObj("A",2000,"B");
	VideoObj obj5 = new VideoObj("A",2000,"C");
	
	
	Assert.assertTrue(obj1.compareTo(obj2) == 0);
	Assert.assertTrue(obj1.compareTo(obj3) == -1);
	Assert.assertTrue(obj1.compareTo(obj4) == -1);
	Assert.assertTrue(obj1.compareTo(obj5) == -1);
	Assert.assertTrue(obj3.compareTo(obj1) == 1);
	Assert.assertTrue(obj4.compareTo(obj1) == 1);
	Assert.assertTrue(obj5.compareTo(obj1) == 1);
  }

  public void testToString() { 
    // TODO 
	VideoObj obj1 = new VideoObj("Title",2020,"Director");
	Assert.assertTrue((obj1.toString()).equals("Title (2020) : Director"));
	Assert.assertFalse((obj1.toString()).equals("titl (2021) : directo"));
  }
}
