package shop.ui;

import java.util.ArrayList;
import java.util.List;


public class UIFormMenuBuilderFactory <P,T> implements UIFormMenuBuilder {
	private final ArrayList<UIPair> _menu;
	
	public UIFormMenuBuilderFactory() {
	    _menu = new ArrayList();
	}

	@Override
	public UIFormMenu toUIFormMenu(String heading) {
		if (null == heading)
	    	throw new IllegalArgumentException();
	    if (_menu.size() < 1)
	    	throw new IllegalStateException();
	    UIPair[] array = new UIPair[_menu.size()];
	    for (int i = 0; i < _menu.size(); i++)
	    	array[i] = (_menu.get(i));
	    return new UIFormMenuFactory(heading, array);
	}

	@Override
	public void add(Object p, Object t) {
		if (t == null)
			throw new IllegalArgumentException();
		_menu.add(new UIPairFactory(p,t));
	}

}
