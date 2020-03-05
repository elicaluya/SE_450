package shop.ui;

/**
 * @see UIFormBuilder
 */
public interface UIForm{
  public int sizeForm(); 
  public String getHeadingForm(); 
  public Object getPromptForm(int i);
  public Object getTestForm(int i);
  public boolean checkInputForm(int i, String input, UIFormTest u);
}
