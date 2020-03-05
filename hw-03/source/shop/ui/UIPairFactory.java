package shop.ui;

public class UIPairFactory <P,T> implements UIPair{
	private P p;
	private T t;
	
	
	UIPairFactory(P p, T t){
		this.p = p;
		this.t = t;
	}
	
	public P getP() {
		return this.p;
	}

	public T getT() {
		return this.t;
	}
}
