package shop.ui;

import shop.ui.UIMenuAction;


public class UIFormMenuFactory <P,T> implements UIForm, UIMenu{
	private final String _heading;
	private UIPair[] PairArray = null;
	
	
	public UIFormMenuFactory (String heading, UIPair[] a) {
		_heading = heading;
		PairArray = a;
	}
	
	

	// Methods for UIForm
	public int sizeForm() {
		return PairArray.length;
	}
	public String getHeadingForm() {
		return _heading;
	}
	public Object getPromptForm(int i) {
		return PairArray[i].getP();
	}
	public Object getTestForm(int i) {
		return PairArray[i].getT();
	}
	public boolean checkInputForm(int i, String input, UIFormTest u) {
		if (null == PairArray[i])
		      return true;		
		return u.run(input);
	}
	

	
	// Methods for UIMenu
	public int sizeMenu() {
		return PairArray.length;
	}
	public String getHeadingMenu() {
		return _heading;
	}
	public Object getPromptMenu(int i) {
		return PairArray[i].getP();
	}
	public Object getActionMenu(int i) {
		return PairArray[i].getT();
	}
	public void runActionMenu(UIMenuAction u) {
		u.run();
	}
	
}
