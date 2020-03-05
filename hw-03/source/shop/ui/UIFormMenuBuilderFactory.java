package shop.ui;

import java.util.ArrayList;
import java.util.List;


public class UIFormMenuBuilderFactory implements UIFormBuilder, UIMenuBuilder {
	private final ArrayList<UIPair> _menu;
	
	public UIFormMenuBuilderFactory() {
	    _menu = new ArrayList();
	}
	

	
	@Override
	public UIMenu toUIMenu(String heading) {
	    if (null == heading)
	    	throw new IllegalArgumentException();
	    if (_menu.size() <= 1)
	    	throw new IllegalStateException();
	    UIPair[] array = new UIPair[_menu.size()];
	    for (int i = 0; i < _menu.size(); i++)
	    	array[i] = (_menu.get(i));
	    return new UIFormMenuFactory(heading,array);
	}

	@Override
	public UIForm toUIForm(String heading) {
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
	public void addForm(String prompt, UIFormTest test) {
	    _menu.add(new UIPairFactory(prompt, test));
	}

	@Override
	public void addMenu(String prompt, UIMenuAction action) {
	    if (null == action)
	    	throw new IllegalArgumentException();
	    _menu.add(new UIPairFactory(prompt, action));
		
	}

}
