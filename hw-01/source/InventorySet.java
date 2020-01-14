import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;

// TODO:  complete the methods
/**
 * An Inventory implemented using a <code>HashMap&lt;Video,Record&gt;</code>.
 * Keys are Videos; Values are Records.
 *
 * @objecttype Mutable Collection of Records
 * @objectinvariant
 *   Every key and value in the map is non-<code>null</code>.
 * @objectinvariant
 *   Each value <code>r</code> is stored under key <code>r.video</code>.
 */
final class InventorySet {
  /** @invariant <code>_data != null</code> */
  private final Map<VideoObj,Record> _data;

  InventorySet() {
    _data = new HashMap<VideoObj,Record>();
  }

  /**
   * Return the number of Records.
   */
  public int size() {
    // TODO  
    return _data.size();
  }

  /**
   * Return a copy of the record for a given Video.
   */
  public Record get(VideoObj v) {
    // TODO  
	  return _data.get(v.hashCode());
  }

  /**
   * Return a copy of the records as a collection.
   * Neither the underlying collection, nor the actual records are returned.
   */
  public Collection toCollection() {
    // Recall that an ArrayList is a Collection.
    // TODO  
    return null;
  }

  /**
   * Add or remove copies of a video from the inventory.
   * If a video record is not already present (and change is
   * positive), a record is created. 
   * If a record is already present, <code>numOwned</code> is
   * modified using <code>change</code>.
   * If <code>change</code> brings the number of copies to be less
   * than one, the record is removed from the inventory.
   * @param video the video to be added.
   * @param change the number of copies to add (or remove if negative).
   * @throws IllegalArgumentException if video null or change is zero
   * @postcondition changes the record for the video
   */
  public void addNumOwned(VideoObj video, int change) {
    // TODO 
	  if (_data.get(video.hashCode()) == null && change > 0) {
		  Record record = new Record(video,change,0,0);
		  _data.put(video,record);
	  }
	  else if (_data.get(video.hashCode()) != null) {
		  int changeNum = _data.get(video.hashCode()).numOwned + change;
		  
		  if (changeNum > 0)
			  _data.get(video.hashCode()).numOwned = changeNum;
		  else 
			  _data.remove(video);
	  }		  
  }

  /**
   * Check out a video.
   * @param video the video to be checked out.
   * @throws IllegalArgumentException if video has no record or numOut
   * equals numOwned.
   * @postcondition changes the record for the video
   */
  public void checkOut(VideoObj video) {
    // TODO
	  if (_data.get(video.hashCode()) == null)
		  throw new IllegalArgumentException("No Record of Video in Inventory to check out");
	  if (_data.get(video.hashCode()).numOut == _data.get(video.hashCode()).numOwned)
		  throw new IllegalArgumentException("All copies of video are currently checked out");
	  else {
		  _data.get(video.hashCode()).numOut++;
		  _data.get(video.hashCode()).numRentals++;
	  }  
  }
  
  /**
   * Check in a video.
   * @param video the video to be checked in.
   * @throws IllegalArgumentException if video has no record or numOut
   * non-positive.
   * @postcondition changes the record for the video
   */
  public void checkIn(VideoObj video) {
    // TODO  
	  if (_data.get(video.hashCode()) == null)
		  throw new IllegalArgumentException("No Record of Video in Inventory to Check In");
	  if (_data.get(video.hashCode()).numOut == 0)
		  throw new IllegalArgumentException("Cannot Check In. No Copy of Video is Checked Out");
	  else {
		  _data.get(video.hashCode()).numOut--;
	  }
  }
  
  /**
   * Remove all records from the inventory.
   * @postcondition <code>size() == 0</code>
   */
  public void clear() {
    // TODO
	  _data.clear();
  }

  /**
   * Return the contents of the inventory as a string.
   */
  public String toString() {
    StringBuffer buffer = new StringBuffer();
    buffer.append("Database:\n");
    for (Record r : _data.values()) {
      buffer.append("  ");
      buffer.append(r);
      buffer.append("\n");
    }
    return buffer.toString();
  }
}
