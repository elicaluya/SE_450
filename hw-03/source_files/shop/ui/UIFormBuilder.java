package shop.ui;

import java.util.ArrayList;
import java.util.List;


/**
 * Class for creating UI Form and adding UIForm pair with prompt and test
 */
public interface UIFormBuilder {
  
  /**
   * Create new UIForm with the heading from the param
   * @throws IllegalArgumenException if heading is null or the size of the List _menu is less than 1
   * @param String heading
   */
  public UIForm toUIForm(String heading); 

  
  /**
   * Method to add a new UIForm pair with prompt and test from param
   * @param String prompt
   * @param UIFormTest test
   */
  public void addForm(String prompt, UIFormTest test);

}
