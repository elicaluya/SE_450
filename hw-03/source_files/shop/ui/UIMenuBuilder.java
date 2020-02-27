package shop.ui;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for creating UI Menu and adding UIMenu pair with prompt and action
 */
public class UIMenuBuilder {
  private final List _menu;
  public UIMenuBuilder() {
    _menu = new ArrayList();
  }
  
  /**
   * Create new UIMenu with the heading from the param
   * @throws IllegalArgumentException if heading is null or size of List _menu is less than 1
   * @param String heading
   */
  public UIMenu toUIMenu(String heading) {
    if (null == heading)
      throw new IllegalArgumentException();
    if (_menu.size() <= 1)
      throw new IllegalStateException();
    UIMenu.Pair[] array = new UIMenu.Pair[_menu.size()];
    for (int i = 0; i < _menu.size(); i++)
      array[i] = (UIMenu.Pair) (_menu.get(i));
    return new UIMenu(heading, array);
  }
  
  /**
   * Method to add a new UIMenu pair with prompt and action from param
   * @param String prompt
   * @param UIMenuAction action
   */
  public void add(String prompt, UIMenuAction action) {
    if (null == action)
      throw new IllegalArgumentException();
    _menu.add(new UIMenu.Pair(prompt, action));
  }
}
