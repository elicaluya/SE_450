package shop.ui;

import shop.ui.UIMenuAction;


public class UIFormMenuFactory <P,T> implements UIFormMenu{
	private final String _heading;
	private UIPair[] PairArray = null;
	
	
	public UIFormMenuFactory (String heading, UIPair[] a) {
		_heading = heading;
		PairArray = a;
	}

	@Override
	public int size() {
		return PairArray.length;
	}

	@Override
	public String getHeading() {
		return _heading;
	}

	@Override
	public Object getP(int i) {
		return PairArray[i].getP();
	}

	@Override
	public Object getT(int i) {
		return PairArray[i].getT();
	}

	@Override
	public void runAction(UIMenuAction u) {
		u.run();
	}

	@Override
	public boolean checkInput(int i, String input, UIFormTest u) {
		if (null == PairArray[i])
		      return true;		
		return u.run(input);
	}
	
}
