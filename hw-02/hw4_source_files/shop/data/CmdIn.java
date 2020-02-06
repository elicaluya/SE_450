package shop.data;

import shop.command.Command;

/**
 * Implementation of command to check in a video.
 * @see Data
 */
final class CmdIn implements Command {
  private InventorySet _inventory;
  private Video _video;
  CmdIn(InventorySet inventory, Video video) {
    _inventory = inventory;
    _video = video;
  }
  public boolean run() {
    // TODO  
	  try {
		  _inventory.checkIn(_video);
		  return true;
	  } catch (ClassCastException e) {};
	  return false;
  }
}
