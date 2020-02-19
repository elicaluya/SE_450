package shop.data;

import shop.command.UndoableCommand;

/**
 * Implementation of command to check out a video.
 * @see Data
 */
final class CmdOut implements UndoableCommand {
  private InventorySet _inventory;
  private Record _oldvalue;
  private Video _video;
  CmdOut(InventorySet inventory, Video video) {
    _inventory = inventory;
    _video = video;
  }
  public boolean run() {
    // TODO  
	  try {
		  _inventory.checkOut(_video);
		  return true;
	  } catch (ClassCastException e) {};
	  return false;
  }
  public void undo() {
    // TODO  
  }
  public void redo() {
    // TODO  
  }
}
