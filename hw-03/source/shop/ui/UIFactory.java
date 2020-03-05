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
  	
  	@Override
  	public void processMenu(UIMenu menu) {
  		if (typeText) {
  			_out.println(menu.getHeadingMenu());
  		    _out.println("Enter choice by number:");

  		    for (int i = 1; i < menu.sizeMenu(); i++) {
  		      _out.println("  " + i + ". " + menu.getPromptMenu(i));
  		    }

  		    String response = getResponse();
  		    int selection;
  		    try {
  		      selection = Integer.parseInt(response, 10);
  		      if ((selection < 0) || (selection >= menu.sizeMenu()))
  		        selection = 0;
  		    } catch (NumberFormatException e) {
  		      selection = 0;
  		    }
  		  menu.runActionMenu((UIMenuAction) menu.getActionMenu(selection));
  		}
  		
  		else {
  			StringBuffer b = new StringBuffer();
  		    b.append(menu.getHeadingMenu());
  		    b.append("\n");
  		    b.append("Enter choice by number:");
  		    b.append("\n");

  		    for (int i = 1; i < menu.sizeMenu(); i++) {
  		    	b.append("  " + i + ". " + menu.getPromptMenu(i));
  		    	b.append("\n");
  		    }

  		    String response = JOptionPane.showInputDialog(b.toString());
  		    int selection;
  		    try {
  		    	selection = Integer.parseInt(response, 10);
  		    	if ((selection < 0) || (selection >= menu.sizeMenu()))
  		    		selection = 0;
  		    } catch (NumberFormatException e) {
  		    	selection = 0;
  		    }

  		    
  		    menu.runActionMenu((UIMenuAction) menu.getActionMenu(selection));
  		}
	
  	}

  	@Override
  	public String[] processForm(UIForm form) {
  		String[] s = new String [form.sizeForm()];
		int i = 0;
  		
  		if (typeText) {
  			while (i < form.sizeForm()) {
  				_out.print(form.getPromptForm(i));
  				_out.flush();
  				String r = getResponse();
  				if (form.checkInputForm(i, r, (UIFormTest) form.getTestForm(i))) {
  					s[i] = r;
  					i++;
  				}
  				else {
  					displayError("Error processing form input");
  				}
  			}
  		}
  		
  		else {
  			while (i < form.sizeForm()) {
  				String r = JOptionPane.showInputDialog(form.getPromptForm(i));
  				if (r == null)
  					r = "";
  				if (form.checkInputForm(i, r, (UIFormTest) form.getTestForm(i))) {
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
