package shop.data;

import shop.command.Command;

/**
 * Implementation of command to clear the inventory.
 * @see Data
 */
final class CmdClear implements Command {
  private InventorySet _inventory;
  CmdClear(InventorySet inventory) {
    _inventory = inventory;
  }
  public boolean run() {
    try {
      _inventory.clear();
      return true;
    } catch (ClassCastException e) {
      return false;
    }
  }
}
