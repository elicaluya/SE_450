package shop.ui;

/**
 * @see UIMenuBuilder
 */
public interface UIMenu {
  public int sizeMenu(); 
  public String getHeadingMenu(); 
  public Object getPromptMenu(int i);
  public Object getActionMenu(int i);
  public void runActionMenu(UIMenuAction u);
}
