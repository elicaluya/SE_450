package shop.main;

import shop.ui.UI;
import shop.ui.UIMenuAction;
import shop.ui.UIError;
import shop.ui.UIFormTest;
import shop.ui.UIFormMenuBuilderFactory;
import shop.ui.UIFormMenu;

import shop.data.Data;
import shop.data.Inventory;
import shop.data.Video;
import shop.data.Record;
import shop.command.Command;
import java.util.Iterator;

class Control {
  private static final int EXITED = 0;
  private static final int EXIT = 1;
  private static final int START = 2;
  private static final int NUMSTATES = 10;
  private UIFormMenu[] _menus;
  private State _state;

  private UIFormMenu _getVideoForm;
  private UIFormTest _numberTest;
  private UIFormTest _stringTest;
    
  private Inventory _inventory;
  private UI _ui;
  
  enum State {
	  StartState,
	  ExitState,
	  ExitedState
  }
  
  enum Entry {
	  DEFAULT,
	  ADD,
	  CheckIn,
	  CheckOut,
	  PRINT,
	  CLEAR,
	  UNDO,
	  REDO,
	  TOP,
	  ExitEntry,
	  INIT,
	  YES,
	  NO
  }
  
  
  Control(Inventory inventory, UI ui) {
    _inventory = inventory;
    _ui = ui;

    _menus = new UIFormMenu[NUMSTATES];
    _state = State.StartState;
    addSTART(START);
    addEXIT(EXIT);
    
    UIFormTest yearTest = new UIFormTest() {
        public boolean run(String input) {
          try {
            int i = Integer.parseInt(input);
            return i > 1800 && i < 5000;
          } catch (NumberFormatException e) {
            return false;
          }
        }
      };
    _numberTest = new UIFormTest() {
        public boolean run(String input) {
          try {
            Integer.parseInt(input);
            return true;
          } catch (NumberFormatException e) {
            return false;
          }
        }
      };
    _stringTest = new UIFormTest() {
        public boolean run(String input) {
          return ! "".equals(input.trim());
        }
      };

    UIFormMenuBuilderFactory f = new UIFormMenuBuilderFactory();
    f.add("Title", _stringTest);
    f.add("Year", yearTest);
    f.add("Director", _stringTest);
    _getVideoForm = f.toUIFormMenu("Enter Video");
  }
  
  void run() {
    try {
      while (_state != State.ExitedState) {
    	  switch(_state) {
    	  	case StartState:
    	  		_ui.processMenu(_menus[START]);
    	  		break;
    	  	case ExitState:
    	  		_ui.processMenu(_menus[EXIT]);
    	  		break;
    	  }
      }
    } catch (UIError e) {
      _ui.displayError("UI closed");
    }
  }
  
  
  private UIMenuAction setAction(Entry e) {
	  UIMenuAction a = null;
	  
	  switch (e) {
	  	case DEFAULT:
	  		a = new UIMenuAction() {
	  	        public void run() {
	  	          _ui.displayError("doh!");
	  	        }
	  		};
	  	    break;
	  	case ADD:
	  		a = new UIMenuAction() {
	  	        public void run() {
	  	          String[] result1 = _ui.processForm(_getVideoForm);
	  	          Video v = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]);

	  	          UIFormMenuBuilderFactory f = new UIFormMenuBuilderFactory();
	  	          f.add("Number of copies to add/remove", _numberTest);
	  	          String[] result2 = _ui.processForm(f.toUIFormMenu(""));
	  	                                             
	  	          Command c = Data.newAddCmd(_inventory, v, Integer.parseInt(result2[0]));
	  	          if (! c.run()) {
	  	            _ui.displayError("Command failed");
	  	          }
	  	        }
	  		};
	  	      break; 
	  	case CheckIn:
	  		a = new UIMenuAction() {
	  	        public void run() {
	  	          // TODO  
	  	        	String[] result1 = _ui.processForm(_getVideoForm);
	  	        	Video v = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]);
	  	        	
	  	        	Command c = Data.newInCmd(_inventory, v);
	  	        	if (! c.run()) {
	  	                _ui.displayError("Command failed");
	  	            }
	  	        }
	  		};
	  		break;
	  	case CheckOut:
	  		a = new UIMenuAction() {
	  	        public void run() {
	  	          // TODO 
	  	        	String[] result1 = _ui.processForm(_getVideoForm);
	  	        	Video v = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]);
	  	        	
	  	        	Command c = Data.newOutCmd(_inventory, v);
	  	        	if (! c.run()) {
	  	                _ui.displayError("Command failed");
	  	            }
	  	        }
	  		};
	  		break;
	  	case PRINT:
	  		a = new UIMenuAction() {
	  	        public void run() {
	  	          _ui.displayMessage(_inventory.toString());
	  	        }
	  		};
	  		break;
	  	case CLEAR:
	  		a = new UIMenuAction() {
	  	        public void run() {
	  	          if (!Data.newClearCmd(_inventory).run()) {
	  	            _ui.displayError("Command failed");
	  	          }
	  	        }
	  		};
	  		break;
	  	case UNDO:
	  		a = new UIMenuAction() {
	  	        public void run() {
	  	          if (!Data.newUndoCmd(_inventory).run()) {
	  	            _ui.displayError("Command failed");
	  	          }
	  	        }
	  		};
	  		break;
	  	case REDO:
	  		a = new UIMenuAction() {
	  	        public void run() {
	  	          if (!Data.newRedoCmd(_inventory).run()) {
	  	            _ui.displayError("Command failed");
	  	          }
	  	        }
	  		};
	  		break;
	  	case TOP:
	  		a = new UIMenuAction() {
	  	        public void run() {
	  	          // TODO  
	  	        	Iterator<Record> itr = _inventory.iterator((r1,r2) -> r2.numRentals() - r1.numRentals());
	  	        	int count = 0;
	  	        	StringBuilder s = new StringBuilder();
	  	        	while (itr.hasNext() && count < 10) {
	  	        		Record r = itr.next();
	  	        		s.append(r.toString()+ "\n");
	  	        		count++;
	  	        	}
	  	        	_ui.displayMessage(s.toString());
	  	        }
	  		};
	  		break;
	  	case ExitEntry:
	  		a = new UIMenuAction() {
	  	        public void run() {
	  	          _state = State.ExitState;
	  	        }
	  		};
	  		break;  		
	  	case INIT:
	  		a = new UIMenuAction() {
	  	        public void run() {
	  	          Data.newAddCmd(_inventory, Data.newVideo("a", 2000, "m"), 1).run();
	  	          Data.newAddCmd(_inventory, Data.newVideo("b", 2000, "m"), 2).run();
	  	          Data.newAddCmd(_inventory, Data.newVideo("c", 2000, "m"), 3).run();
	  	          Data.newAddCmd(_inventory, Data.newVideo("d", 2000, "m"), 4).run();
	  	          Data.newAddCmd(_inventory, Data.newVideo("e", 2000, "m"), 5).run();
	  	          Data.newAddCmd(_inventory, Data.newVideo("f", 2000, "m"), 6).run();
	  	          Data.newAddCmd(_inventory, Data.newVideo("g", 2000, "m"), 7).run();
	  	          Data.newAddCmd(_inventory, Data.newVideo("h", 2000, "m"), 8).run();
	  	          Data.newAddCmd(_inventory, Data.newVideo("i", 2000, "m"), 9).run();
	  	          Data.newAddCmd(_inventory, Data.newVideo("j", 2000, "m"), 10).run();
	  	          Data.newAddCmd(_inventory, Data.newVideo("k", 2000, "m"), 11).run();
	  	          Data.newAddCmd(_inventory, Data.newVideo("l", 2000, "m"), 12).run();
	  	          Data.newAddCmd(_inventory, Data.newVideo("m", 2000, "m"), 13).run();
	  	          Data.newAddCmd(_inventory, Data.newVideo("n", 2000, "m"), 14).run();
	  	          Data.newAddCmd(_inventory, Data.newVideo("o", 2000, "m"), 15).run();
	  	          Data.newAddCmd(_inventory, Data.newVideo("p", 2000, "m"), 16).run();
	  	          Data.newAddCmd(_inventory, Data.newVideo("q", 2000, "m"), 17).run();
	  	          Data.newAddCmd(_inventory, Data.newVideo("r", 2000, "m"), 18).run();
	  	          Data.newAddCmd(_inventory, Data.newVideo("s", 2000, "m"), 19).run();
	  	          Data.newAddCmd(_inventory, Data.newVideo("t", 2000, "m"), 20).run();
	  	          Data.newAddCmd(_inventory, Data.newVideo("u", 2000, "m"), 21).run();
	  	          Data.newAddCmd(_inventory, Data.newVideo("v", 2000, "m"), 22).run();
	  	          Data.newAddCmd(_inventory, Data.newVideo("w", 2000, "m"), 23).run();
	  	          Data.newAddCmd(_inventory, Data.newVideo("x", 2000, "m"), 24).run();
	  	          Data.newAddCmd(_inventory, Data.newVideo("y", 2000, "m"), 25).run();
	  	          Data.newAddCmd(_inventory, Data.newVideo("z", 2000, "m"), 26).run();
	  	        }
	  		};
	  		break;
	  	
	  	case YES:
	  		a = new UIMenuAction() {
	        	public void run() {
	        		_state = State.ExitedState;
	        	}
	  		};
	  		break;
	  	
	  	case NO:
	  		a = new UIMenuAction() {
	        	public void run() {
	        		_state = State.StartState;
	        	}
	  		};
	  		break;
	  }
	  return a;
  }
  
  private void addSTART(int stateNum) {
	  UIFormMenuBuilderFactory m = new UIFormMenuBuilderFactory();
	  m.add("Default",setAction(Entry.DEFAULT));
	  m.add("Add/Remove copies of a video",setAction(Entry.ADD));
	  m.add("Check in a video",setAction(Entry.CheckIn));
	  m.add("Check out a video",setAction(Entry.CheckOut));
	  m.add("Print the inventory",setAction(Entry.PRINT));
	  m.add("Clear the inventory",setAction(Entry.CLEAR));
	  m.add("Undo",setAction(Entry.UNDO));
	  m.add("Redo",setAction(Entry.REDO));
	  m.add("Print top ten all time rentals in order",setAction(Entry.TOP));    
	  m.add("Exit",setAction(Entry.ExitEntry));
	  m.add("Initialize with bogus contents",setAction(Entry.INIT));
	  _menus[stateNum] = m.toUIFormMenu("Bob's Video");
  }
  
  private void addEXIT(int stateNum) {
	  UIFormMenuBuilderFactory m = new UIFormMenuBuilderFactory();
	  m.add("Default",setAction(Entry.DEFAULT));
	  m.add("Yes",setAction(Entry.YES));
	  m.add("No",setAction(Entry.NO));
	  _menus[stateNum] = m.toUIFormMenu("Are you sure you want to exit?");
  }
}
