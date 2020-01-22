package shop.data;

import shop.command.Command;

/**
 * Implementation of command to add or remove inventory.
 * @see Data
 */
final class CmdAdd implements Command {
  private InventorySet _inventory;
  private Video _video;
  private int _change;
  CmdAdd(InventorySet inventory, Video video, int change) {
    _inventory = inventory;
    _video = video;
    _change = change;
  }
  public boolean run() {
    // TODO  
	  try {
		  _inventory.addNumOwned(_video, _change);
		  return true;
	  } catch (ClassCastException e) {};
	  return false;
  }
}
