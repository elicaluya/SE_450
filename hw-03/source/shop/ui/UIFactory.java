package shop.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import javax.swing.JOptionPane;

public class UIFactory implements UI{
	static private BufferedReader _in;
	static private PrintStream _out;
  	static private Boolean typeText;
  
  	
  	public UIFactory (String s) {
		if (s.equals("pop")) {
  			typeText = false;
  		}
  		else if (s.equals("text")) {
  			typeText = true;
  			_in = new BufferedReader(new InputStreamReader(System.in));
  		    _out = System.out;
  		}
  	}

  
    private String getResponse() {
        String result;
        try {
        	result = _in.readLine();
        } catch (IOException e) {
        	throw new UIError(); // re-throw UIError
        }
        if (result == null) {
        	throw new UIError(); // input closed
        }
        return result;
     }
    
    
    public void runMenuAction(UIFormMenu menu,String response) {
    	int selection;
    	try {
    		selection = Integer.parseInt(response, 10);
    		if ((selection < 0) || (selection >= menu.size()))
    			selection = 0;
		    } catch (NumberFormatException e) {
		      selection = 0;
		    }
    	menu.runAction((UIMenuAction) menu.getT(selection));
    }
    
  	
  	@Override
  	public void processMenu(UIFormMenu menu) {
  		if (typeText) {
  			_out.println(menu.getHeading());
  		    _out.println("Enter choice by number:");

  		    for (int i = 1; i < menu.size(); i++) {
  		      _out.println("  " + i + ". " + menu.getP(i));
  		    }

  		    String response = getResponse();
  		  
  		    runMenuAction(menu,response);
  		}
  		
  		else {
  			StringBuffer b = new StringBuffer();
  		    b.append(menu.getHeading());
  		    b.append("\n");
  		    b.append("Enter choice by number:");
  		    b.append("\n");

  		    for (int i = 1; i < menu.size(); i++) {
  		    	b.append("  " + i + ". " + menu.getP(i));
  		    	b.append("\n");
  		    }

  		    String response = JOptionPane.showInputDialog(b.toString());
  		    runMenuAction(menu,response);
  		}
	
  	}

  	@Override
  	public String[] processForm(UIFormMenu form) {
  		String[] s = new String [form.size()];
		int i = 0;
  		
  		if (typeText) {
  			while (i < form.size()) {
  				_out.print(form.getP(i));
  				_out.flush();
  				String r = getResponse();
  				if (form.checkInput(i, r, (UIFormTest) form.getT(i))) {
  					s[i] = r;
  					i++;
  				}
  				else {
  					displayError("Error processing form input");
  				}
  			}
  		}
  		
  		else {
  			while (i < form.size()) {
  				String r = JOptionPane.showInputDialog(form.getP(i));
  				if (r == null)
  					r = "";
  				if (form.checkInput(i, r, (UIFormTest) form.getT(i))) {
  					s[i] = r;
  					i++;
  				}
  				else {
  					displayError("Error processing form");
  				}
  			}
  		}
  		
  		return s;
  	}

  	@Override
  	public void displayMessage(String message) {
  		if (typeText) {
  			_out.println(message);
  		}
  		else {
  			JOptionPane.showMessageDialog(null,message);
  		}
	
  	}

  	@Override
  	public void displayError(String message) {
  		if (typeText) {
  			_out.println(message);
  		}
  		else {
  			JOptionPane.showMessageDialog(null,message,"Error",JOptionPane.ERROR_MESSAGE);
  		}
	
  	}
}
