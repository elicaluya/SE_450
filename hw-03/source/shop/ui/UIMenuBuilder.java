package shop.ui;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for creating UI Menu and adding UIMenu pair with prompt and action
 */
public interface UIMenuBuilder {
  
  /**
   * Create new UIMenu with the heading from the param
   * @throws IllegalArgumentException if heading is null or size of List _menu is less than 1
   * @param String heading
   */
  public UIMenu toUIMenu(String heading); 

  
  /**
   * Method to add a new UIMenu pair with prompt and action from param
   * @param String prompt
   * @param UIMenuAction action
   */
  public void addMenu(String prompt, UIMenuAction action); 
}
