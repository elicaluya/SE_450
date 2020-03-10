package shop.ui;

public interface UIFormMenu {
	public int size(); 
	public String getHeading(); 
	public Object getP(int i);
	public Object getT(int i);
	public void runAction(UIMenuAction u);
	public boolean checkInput(int i, String input, UIFormTest u);
}
