package shop.ui;

import java.util.ArrayList;
import java.util.List;


/**
 * Class for creating UI Form and adding UIForm pair with prompt and test
 */
public final class UIFormBuilder {
  private final List _menu;
  public UIFormBuilder() {
    _menu = new ArrayList();
  }
  
  /**
   * Create new UIForm with the heading from the param
   * @throws IllegalArgumenException if heading is null or the size of the List _menu is less than 1
   * @param String heading
   */
  public UIForm toUIForm(String heading) {
    if (null == heading)
      throw new IllegalArgumentException();
    if (_menu.size() < 1)
      throw new IllegalStateException();
    UIForm.Pair[] array = new UIForm.Pair[_menu.size()];
    for (int i = 0; i < _menu.size(); i++)
      array[i] = (UIForm.Pair) (_menu.get(i));
    return new UIForm(heading, array);
  }
  
  /**
   * Method to add a new UIForm pair with prompt and test from param
   * @param String prompt
   * @param UIFormTest test
   */
  public void add(String prompt, UIFormTest test) {
    _menu.add(new UIForm.Pair(prompt, test));
  }
}
