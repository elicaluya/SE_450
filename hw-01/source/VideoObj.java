// TODO:  complete the methods
/**
 * Immutable Data Class for video objects.
 * Comprises a triple: title, year, director.
 *
 * @objecttype Immutable Data Class
 * @objectinvariant
 *   Title is non-null, no leading or final spaces, not empty string.
 * @objectinvariant
 *   Year is greater than 1800, less than 5000.
 * @objectinvariant
 *   Director is non-null, no leading or final spaces, not empty string.
 */
final class VideoObj implements Comparable {
  /** @invariant non-null, no leading or final spaces, not empty string */
  private final String _title;
  /** @invariant greater than 1800, less than 5000 */
  private final int    _year;
  /** @invariant non-null, no leading or final spaces, not empty string */
  private final String _director;

  /**
   * Initialize all object attributes.
   * Title and director are "trimmed" to remove leading and final space.
   * @throws IllegalArgumentException if any object invariant is violated.
   */
  VideoObj(String title, int year, String director) {
    // TODO  
	
	// throws IllegalArgumentException if the title or director string is null
	if (title == null || director == null)
	   	throw new IllegalArgumentException("title and director need to be non-null");
	
	// throws IllegalArgumentException if the tile or director is an empty string
	if (title.trim().isEmpty() || director.trim().isEmpty())
		throw new IllegalArgumentException("title and director must not be empty string");
	
	// throws IllegalArgumentException if the year is not in the correct range
	if (year <= 1800 || year >= 5000)
		throw new IllegalArgumentException("Year must be greater than 1800 and less than 5000");
	  
	
	// initialize the object variables while trimming the leading and final spaces for the title and director
	_title = title.trim();
    _year = year;
    _director = director.trim();
  }

  /**
   * Return the value of the attribute.
   */
  public String director() {
    // TODO  
    return _director;
  }

  /**
   * Return the value of the attribute.
   */
  public String title() {
    // TODO  
    return _title;
  }

  /**
   * Return the value of the attribute.
   */
  public int year() {
    // TODO  
    return _year;
  }

  /**
   * Compare the attributes of this object with those of thatObject.
   * @param thatObject the Object to be compared.
   * @return deep equality test between this and thatObject.
   */
  public boolean equals(Object thatObject) {
    // TODO
	if (this.compareTo(thatObject) == 0)
		return true;
	return false;
  }

  /**
   * Return a hash code value for this object using the algorithm from Bloch:
   * fields are added in the following order: title, year, director.
   */
  public int hashCode() {
    // TODO  
    int hcode = 17;
    hcode = 37*hcode + _title.hashCode();
    hcode = 37*hcode + _year;
    hcode = 37*hcode + _director.hashCode();
    return hcode;
  }

  /**
   * Compares the attributes of this object with those of thatObject, in
   * the following order: title, year, director.
   * @param thatObject the Object to be compared.
   * @return a negative integer, zero, or a positive integer as this
   *  object is less than, equal to, or greater thatObject.
   * @throws ClassCastException if thatObject has an incompatible type.
   */
  public int compareTo(Object thatObject) {
    // TODO
	VideoObj obj = (VideoObj) thatObject;
	if (_title.compareTo(obj._title) < 0)
		return -1;
	else if (_title.compareTo(obj._title) > 0)
		return 1;
	else if (_title.compareTo(obj._title) == 0) {
		if (_year < obj._year)
			return -1;
		else if (_year > obj._year)
			return 1;
		else if (_year == obj._year) {
			if (_director.compareTo(obj._director) < 0)
				return -1;
			else if (_director.compareTo(obj._director) > 0)
				return 1;
			else
				return 0;
		}
	}
    return -1;
  }

  /**
   * Return a string representation of the object in the following format:
   * <code>"title (year) : director"</code>.
   */
  public String toString() {
    // TODO  
    return _title + " (" + Integer.toString(_year) + ") : " + _director;
  }
}
