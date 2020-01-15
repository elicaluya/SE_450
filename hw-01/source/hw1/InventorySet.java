package hw1;
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
	  // Use HashMap method size() to return the size of the inventory
	  return _data.size();
  }

  /**
   * Return a copy of the record for a given Video.
   */
  public Record get(VideoObj v) {
    // TODO  
	  // Use HashMap method get() to return the Record using the VideoObj v as the key
	  return _data.get(v);
  }

  /**
   * Return a copy of the records as a collection.
   * Neither the underlying collection, nor the actual records are returned.
   */
  public Collection toCollection() {
    // Recall that an ArrayList is a Collection.
    // TODO
	  // Create ArrayList of type Record to store the copies of Records from the InventorySet
	  ArrayList<Record> array = new ArrayList<Record>();
	  // Loop through the HashMap
	  for (VideoObj v : _data.keySet()) {
		  // Make a copy of the Record and add it to the array
		  Record r = _data.get(v);
		  array.add(r);
	  }
	  
	  // Return the array with the copies of the Record objects
	  return array;
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
	  // Check to see if there is no Record for the video and if there is a positive change
	  if (_data.get(video) == null && change > 0) {
		  // Create a new Record object for the video and add it to the inventory
		  Record record = new Record(video,change,0,0);
		  _data.put(video,record);
	  }
	  // if there is an existing record for the video
	  else if (_data.get(video) != null) {
		  // Store the change number 
		  int changeNum = _data.get(video).numOwned + change;
		  // If the change doesn't deplete all of the copies owned in the inventory for that video,
		  // adjust the amount owned for that video
		  if (changeNum > 0)
			  _data.get(video).numOwned = changeNum;
		  // If the stock is depleted then remove the video from the InventorySet
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
	  // If the video requested has no Record, throw IllegalArgumentException
	  if (_data.get(video) == null)
		  throw new IllegalArgumentException("No Record of Video in Inventory to check out");
	  // if all of the copies of the video are currently checked out, throw IllegalArgumentException
	  if (_data.get(video).numOut == _data.get(video).numOwned)
		  throw new IllegalArgumentException("All copies of video are currently checked out");
	  // Otherwise, increase the number of copies checked out and the total times it has been rented by 1 
	  else {
		  _data.get(video).numOut++;
		  _data.get(video).numRentals++;
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
	  // If the video requested has no Record, throw IllegalArgumentException
	  if (_data.get(video) == null)
		  throw new IllegalArgumentException("No Record of Video in Inventory to Check In");
	  // If there are no copies of the videos that are checked out, throw IllegalArgumentException
	  if (_data.get(video).numOut == 0)
		  throw new IllegalArgumentException("Cannot Check In. No Copy of Video is Checked Out");
	  // Otherwise, decrease the number of copies that are checked out by 1
	  else {
		  _data.get(video).numOut--;
	  }
  }
  
  /**
   * Remove all records from the inventory.
   * @postcondition <code>size() == 0</code>
   */
  public void clear() {
    // TODO
	  // Use HashMap method clear() to remove all the Records in the InventorySet
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
