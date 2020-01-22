package shop.data;

import java.util.Map;


import java.util.HashMap;
import java.util.Comparator;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import shop.command.Command;

/**
 * Implementation of Inventory interface.
 * @see Data
 */
final class InventorySet implements Inventory {
  // Chose to use Map of Record, rather than RecordObj, because of
  // Java's broken generic types.  The story is too sad to retell, but
  // involves the fact that Iterable<? extends Record> is not a valid
  // type, and that Iterator<RecordObj> is not a subtype of
  // Iterator<Record>.
  //
  // Seems like the best approach for Java generics is to use the
  // external representation internally and downcast when necessary.
  private final Map<Video,Record> _data;

  InventorySet() {
    _data = new HashMap<Video,Record>();
  }

  public int size() {
    // TODO  
    return _data.size();
  }

  public Record get(Video v) {
    // TODO
	  Record r = _data.get(v);
	  // If video not found, return null
	  if (r == null) return null;
	  // Otherwise return a copy of the record
	  return new RecordObj(v,r.numOwned(),r.numOut(),r.numRentals());
  }

  public Iterator<Record> iterator() {
    return Collections.unmodifiableCollection(_data.values()).iterator();
  }

  public Iterator<Record> iterator(Comparator<Record> comparator) {
    // TODO  
	  ArrayList<Record> record_array = new ArrayList<Record>(_data.values());
	  Collections.sort(record_array, comparator);
	  return Collections.unmodifiableCollection(record_array).iterator();
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
   */
  void addNumOwned(Video video, int change) {
    // TODO 
	  if (video == null || change == 0) throw new IllegalArgumentException("Null video or change is zero");
	  
	  RecordObj r = (RecordObj) _data.get(video);
	  // Check to see if there is no Record for the video and if there is a positive change
	  if (r == null && change > 0) {
		  // Create a new Record object for the video and add it to the inventory
		  RecordObj record = new RecordObj(video,change,0,0);
		  _data.put(video,record);
	  }
	  
	  // if there is an existing record for the video
	  else if (r != null) {
		  // Store the change number 
		  int changeNum = r.numOwned + change;
		  // If the change doesn't deplete all of the copies owned in the inventory for that video,
		  // adjust the amount owned for that video
		  if (changeNum > 0)
			  r.numOwned = changeNum;
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
   */
  void checkOut(Video video) {
    // TODO
	  RecordObj r = (RecordObj)_data.get(video);
	  // If the video requested has no Record, throw IllegalArgumentException
	  if (r == null)
		  throw new IllegalArgumentException("No Record of Video in Inventory to check out");
	  // if all of the copies of the video are currently checked out, throw IllegalArgumentException
	  if (r.numOut == r.numOwned)
		  throw new IllegalArgumentException("All copies of video are currently checked out");
	  // Otherwise, increase the number of copies checked out and the total times it has been rented by 1 
	  else {
		  r.numOut++;
		  r.numRentals++;
	  }
  }
  
  /**
   * Check in a video.
   * @param video the video to be checked in.
   * @throws IllegalArgumentException if video has no record or numOut
   * non-positive.
   */
  void checkIn(Video video) {
    // TODO
	  RecordObj r = (RecordObj)_data.get(video);
	  // If the video requested has no Record, throw IllegalArgumentException
	  if (r == null)
		  throw new IllegalArgumentException("No Record of Video in Inventory to Check In");
	  // If there are no copies of the videos that are checked out, throw IllegalArgumentException
	  if (r.numOut == 0)
		  throw new IllegalArgumentException("Cannot Check In. No Copy of Video is Checked Out");
	  // Otherwise, decrease the number of copies that are checked out by 1
	  else {
		  r.numOut--;
	  }
  }
  
  /**
   * Remove all records from the inventory.
   */
  void clear() {
    // TODO 
	_data.clear();
  }

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


  /**
   * Implementation of Record interface.
   *
   * <p>This is a utility class for Inventory.  Fields are mutable and
   * package-private.</p>
   *
   * <p><b>Class Invariant:</b> No two instances may reference the same Video.</p>
   *
   * @see Record
   */
  private static final class RecordObj implements Record {
    Video video; // the video
    int numOwned;   // copies owned
    int numOut;     // copies currently rented
    int numRentals; // total times video has been rented
    
    RecordObj(Video video, int numOwned, int numOut, int numRentals) {
      this.video = video;
      this.numOwned = numOwned;
      this.numOut = numOut;
      this.numRentals = numRentals;
    }
    public Video video() {
      return video;
    }
    public int numOwned() {
      return numOwned;
    }
    public int numOut() {
      return numOut;
    }
    public int numRentals() {
      return numRentals;
    }
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
}
