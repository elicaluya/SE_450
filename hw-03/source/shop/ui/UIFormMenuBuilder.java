package shop.ui;

public interface UIFormMenuBuilder {
	public UIFormMenu toUIFormMenu(String heading); 
	public void addMenu(String prompt, UIMenuAction action);
	public void addForm(String prompt, UIFormTest test);
}
