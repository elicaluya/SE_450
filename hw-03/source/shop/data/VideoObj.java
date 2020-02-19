package shop.data;

/**
 * Implementation of Video interface.
 * @see Data
 */
final class VideoObj implements Video {
  private final String _title;
  private final int    _year;
  private final String _director;

  /**
   * Initialize all object attributes.
   * Title and director are "trimmed" to remove leading and final space.
   * @throws IllegalArgumentException if object invariant violated.
   */
  VideoObj(String title, int year, String director) {
    _title = title;
    _director = director;
    _year = year;
  }

  public String director() {
    // TODO  
    return _director;
  }

  public String title() {
    // TODO  
    return _title;
  }

  public int year() {
    // TODO  
    return _year;
  }

  public boolean equals(Object thatObject) {
    // TODO  
    if (thatObject != null ) {
		  if (this.hashCode() == thatObject.hashCode()) return true;
	  }
	
    return false;
  }

  public int hashCode() {
    // TODO  
	  int hcode = 17;
	  hcode = 37*hcode + _title.hashCode();
	  hcode = 37*hcode + _year;
	  hcode = 37*hcode + _director.hashCode();
	  return hcode;
  }

  public int compareTo(Object thatObject) {
    // TODO  
	// Cast the object from argument into a VideoObj
		VideoObj obj = (VideoObj) thatObject;
			  
		// First compare which title is greater
		if (_title.compareTo(obj._title) < 0)
			return -1;
		else if (_title.compareTo(obj._title) > 0)
			return 1;
			  
		// If the titles are the same then compare the years
		else if (_title.compareTo(obj._title) == 0) {
			if (_year < obj._year)
				return -1;
			else if (_year > obj._year)
				return 1;
				  
			// if the years are the same then compare the directors
			else if (_year == obj._year) {
				if (_director.compareTo(obj._director) < 0)
					return -1;
				else if (_director.compareTo(obj._director) > 0)
					return 1;
				// If all of the information is the same then the two VideoObj objects are equal and return 0
				else
					return 0;
			}	
		}
	    return -1;
  }

  public String toString() {
    // TODO  
	  return _title.trim() + " (" + Integer.toString(_year) + ") : " + _director.trim();
  }
}
