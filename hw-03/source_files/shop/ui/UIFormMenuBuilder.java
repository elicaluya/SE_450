package shop.ui;

public interface UIFormMenuBuilder <P,T> {
	public UIFormMenu toUIFormMenu(String heading); 
	public void add(P p, T t);
}
