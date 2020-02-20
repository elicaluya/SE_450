package shop.command;
import java.util.Stack;

final class CommandHistoryObj implements CommandHistory {
  Stack<UndoableCommand> _undoStack = new Stack<UndoableCommand>();
  Stack<UndoableCommand> _redoStack = new Stack<UndoableCommand>();
  RerunnableCommand _undoCmd = new RerunnableCommand () {
      public boolean run () {
        boolean result = !_undoStack.empty();
        if (result) {
          // Undo
          // TODO 
        	UndoableCommand c = _undoStack.pop();
        	c.undo();
        	_redoStack.push(c);
        }
        return result;
      }
    };
  RerunnableCommand _redoCmd = new RerunnableCommand () {
      public boolean run () {
        boolean result = !_redoStack.empty();
        if (result) {
          // Redo
          // TODO 
        	UndoableCommand c = _redoStack.pop();
        	c.redo();
        	_undoStack.push(c);
        }
        return result;
      }
    };

  public void add(UndoableCommand cmd) {
    // TODO
	  _undoStack.push(cmd);
	  _redoStack.clear();
  }
  
  public RerunnableCommand getUndo() {
    return _undoCmd;
  }
  
  public RerunnableCommand getRedo() {
    return _redoCmd;
  }
  
  // For testing
  UndoableCommand topUndoCommand() {
    if (_undoStack.empty())
      return null;
    else
      return _undoStack.peek();
  }
  // For testing
  UndoableCommand topRedoCommand() {
    if (_redoStack.empty())
      return null;
    else
      return _redoStack.peek();
  }
}
