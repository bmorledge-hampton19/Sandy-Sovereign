package sandySovereign;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;

/**
 * An abstract class for all of the menus used in Sandy Sovereign
 * @author Benjamin Morledge-Hampton
 * @version 1.0 1/23/2017
 */
public abstract class Menu extends JComponent implements ActionListener{

	// The factor by which to scale the UI.
	double scaleFactor;
	
	// Buttons in the UI.
	ArrayList<JButton> buttons;
	
	// The manager that the menu will refer back to.
	Manager manager;
	
	/**
	 * Constructor which sets the scale factor and manager, initializes buttons, and sets the layout to null.
	 * @param scaleFactor specifies the factor by which to scale the UI.
	 * @param manager specifies the manager that the menu will refer back to.
	 */
	Menu(double scaleFactor, Manager manager) {
		this.setLayout(null);
		this.manager = manager;
		this.scaleFactor = scaleFactor;
		buttons = new ArrayList<JButton>();
	}

	/**
	 * Prints text to the screen.
	 * @param g specifies the Graphics object to draw the text.
	 * @param x specifies the x position of the text.
	 * @param y specifies the y position of the text.
	 * @param text specifies the text to be displayed.
	 * @param fontSize specifies the size of the text.
	 */
	public void displayText(Graphics g, int x, int y, String text, int fontSize) {
		
		// An array of all of the individual lines of text.
		String[] lines = text.split("\n");
		
		// Set the font size.
		g.setFont(new Font("TimesRoman", Font.PLAIN, (int)(fontSize*scaleFactor)));
		
		// Display the lines of text one at a time taking into account the font size and scale factor.
		for (int i = 0; i < lines.length; i++) {
			g.drawString(lines[i], (int)(x*scaleFactor), (int)(y*scaleFactor+i*fontSize*scaleFactor));
		}
		
	}
	
	/**
	 * Adds a button to the buttons array list.
	 * @param x specifies the x coordinate for the button.
	 * @param y specifies the y coordinate for the button.
	 * @param text specifies the text to be displayed on the button.
	 */
	public void addButton(int x, int y, String text) {
		
		// The new button to be added to the array list.
		JButton newButton = new JButton(text);
		
		// Set the buttons bounds based on the given x and y values.
		newButton.setBounds((int)(x*scaleFactor), (int)(y*scaleFactor), 
				(int)(newButton.getPreferredSize().width*scaleFactor), (int)(newButton.getPreferredSize().height*scaleFactor));
		
		// Scale the text on the button.
		newButton.setFont(new Font("TimesRoman", Font.BOLD, (int)(12*scaleFactor)));
		
		// Add the button to the Menu.
		add(newButton);
		newButton.addActionListener(this);
		
		// Add the new button to the array list!
		buttons.add(newButton);
		
	}
	
	/**
	 * Moves a button to a new location.
	 * @param button specifies the button to be moved.
	 * @param x specifies the new x position.
	 * @param y specifies the new y position.
	 */
	public void moveButton(JButton button, int x, int y) {
		button.setBounds((int)(x*scaleFactor), (int)(y*scaleFactor), 
				(int)(button.getPreferredSize().width*scaleFactor), (int)(button.getPreferredSize().height*scaleFactor));
	}

	
}
