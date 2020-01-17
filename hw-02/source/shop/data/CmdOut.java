package shop.data;

import shop.command.Command;

/**
 * Implementation of command to check out a video.
 * @see Data
 */
final class CmdOut implements Command {
  private InventorySet _inventory;
  private Video _video;
  CmdOut(InventorySet inventory, Video video) {
    _inventory = inventory;
    _video = video;
  }
  public boolean run() {
    // TODO  
    return false;
  }
}
