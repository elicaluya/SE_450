package shop.ui;

public interface UI {
  public void processMenu(UIFormMenu menu);
  public String[] processForm(UIFormMenu form);
  public void displayMessage(String message);
  public void displayError(String message);
}
