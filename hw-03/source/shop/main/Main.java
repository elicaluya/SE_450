package shop.main;

import shop.ui.UIFactory;
import shop.data.Data;

public class Main {
  private Main() {}
  public static void main(String[] args) {
	  Control control = null;
	  
	  if (args.length > 0) {
		  if (args[0].equals("pop") || args[0].equals("text"))
			  control = new Control(Data.newInventory(), new UIFactory(args[0]));
		  else {
			  System.out.println("Argument must be either 'pop' or 'text'");
			  System.out.println("Exiting...");
			  System.exit(1);
		  }
	  }
	  else {
		  System.out.println("No arguments Found! Argument must be either 'pop' or 'text'");
		  System.out.println("Exiting...");
		  System.exit(1);
	  }
	  
	  
	  control.run();
  }
}
