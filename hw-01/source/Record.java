/**
 * Utility class for Inventory.  Fields are mutable and package-private.
 * Does not override <code>equals</code> or <code>hashCode</code>.
 *
 * @objecttype Mutable Data Class
 */
final class Record {
  /**
   * The video.
   * @invariant <code>video != null</code>
   */
  VideoObj video;
  /**
   * The number of copies of the video that are in the inventory.
   * @invariant <code>numOwned > 0</code>
   */
  int numOwned;
  /**
   * The number of copies of the video that are currently checked out.
   * @invariant <code>numOut <= numOwned</code>
   */
  int numOut;
  /**
   * The total number of times this video has ever been checked out.
   * @invariant <code>numRentals >= numOut</code>
   */
  int numRentals;

  /**
   * Initialize all object attributes.
   */
  Record(VideoObj video, int numOwned, int numOut, int numRentals) {
    this.video = video;
    this.numOwned = numOwned;
    this.numOut = numOut;
    this.numRentals = numRentals;
  }
  /**
   * Return a shallow copy of this record.
   */
  public Record copy() {
    return new Record(video,numOwned,numOut,numRentals);
  }
  /**
   * Return a string representation of the object in the following format:
   * <code>"video [numOwned,numOut,numRentals]"</code>.
   */
  public String toString() {
    StringBuffer buffer = new StringBuffer();
    buffer.append(video);
    buffer.append(" [");
    buffer.append(numOwned);
    buffer.append(",");
    buffer.append(numOut);
    buffer.append(",");
    buffer.append(numRentals);
    buffer.append("]");
    return buffer.toString();
  }
}
