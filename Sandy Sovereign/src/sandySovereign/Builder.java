package sandySovereign;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This menu allows the user to purchase buildings to upgrade the stockpile.
 * @author Benjamin Morledge-Hampton
 * @version 1.0 1/24/2017
 */
public class Builder extends Menu implements ActionListener{

	// Keeps track of the tier being viewed.
	private int tier;

	// The buildings to be purchased throughout the game.
	private Building[] buildings;
	
	// Text to notify the user about the success of the purchase.
	private String notificationText;
	
	/**
	 * sets the tier to the default value, 0, adds buttons, and initializes the buildings.
	 * @param scaleFactor specifies the factor by which to scale the UI.
	 * @param manager specifies the manager that the menu will refer back to.
	 */
	public Builder(double scaleFactor, Manager manager) {
		super(scaleFactor, manager);
		
		// Initialize stuff.
		notificationText = "";
		tier = 0;
		buildings = Building.initializeBuildings();
		
		// Initialize Buttons.
		addButton(50, 50, "Back");
		addButton(50, 700, "Previous Tier");
		addButton(650, 700, "Next Tier");
		addButton(125, 350, "Purchase");
		addButton(500, 350, "Purchase");
		addButton(125, 650, "Purchase");
		addButton(500, 650, "Purchase");
		
		// Hide and disable buttons as necessary.
		buttons.get(1).setEnabled(false);
		buttons.get(2).setEnabled(false);
		
		for (int i = 3; i < 7; i++) buttons.get(i).setVisible(false);
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		// Display the title text and any notification text.
		displayText(g, 300, 50, String.format("Tier %d Builder", tier), 40);
		displayText(g, 325, 75, notificationText, 20);
		
		// Reset the notification text.
		notificationText = "";
		
		// Display buildings based on the current tier.
		if (tier == 0) {
			
			// First, check to see if the the button for the next tier should be enabled.
			if (manager.getStockpile().getSandCastleLevel()>0) 
				buttons.get(2).setEnabled(true);
			
			// Disable the previous tier button.
			buttons.get(1).setEnabled(false);
			
			// Display simple text.
			displayText(g, 200, 300, "No buildings available for this tier", 30);
			
		}
		
		else {
			
			buttons.get(1).setEnabled(true);
			
			for (int i = 1; i < 5; i++) {
				
				if (tier == i) {
				
					// First, check to see if the the button for the next tier should be enabled.
					if (manager.getStockpile().getSandCastleLevel()>i) 
						buttons.get(2).setEnabled(true);
					else buttons.get(2).setEnabled(false);
					
					// Now, figure out how to display all of the buildings' text.
					for (int z = 0; z < 4; z++) {
						
						// Display description, name, and cost for each building.
						displayText(g, 50 + 350*(z%2), 150 + (z/2)*300, buildings[z+(i-1)*4].getName(), 25);
						displayText(g, 50 + 350*(z%2), 185 + (z/2)*300, buildings[z+(i-1)*4].getDescription(), 15);
						displayText(g, 50 + 350*(z%2), 250 + (z/2)*300, buildings[z+(i-1)*4].getCostString(), 18);
						
						// Configure Buttons.
						if (buildings[z+(i-1)*4].isPuchased()) {
							buttons.get(z+3).setText("Purchased");
							buttons.get(z+3).setEnabled(false);
						}
						else {
							buttons.get(z+3).setText("Purchase");
							buttons.get(z+3).setEnabled(true);
						}
						
						
					}
					
				}
				
			}
			
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Go back with this button.
		if (e.getSource() == buttons.get(0))
			manager.switchMenus(Manager.EVENT_VIEWER);
		
		// Go back a tier with this button.
		else if (e.getSource() == buttons.get(1)) {
			
			tier--;
			
			// Get rid of the purchased buttons if rolling back to the "0th" tier.
			if (tier == 0) for (int i = 3; i < 7; i++) buttons.get(i).setVisible(false);
			
			repaint();
		}
		
		// Move up a tier with this button.
		else if (e.getSource() == buttons.get(2)) {
			tier++;
			
			// Bring back the purchased buttons if coming to the first tier.
			if (tier == 1) for (int i = 3; i < 7; i++) buttons.get(i).setVisible(true);
			
			repaint();
		}
		
		else {
			// Purchase buildings with these buttons.
			for (int i = 0; i < 4; i++) {
				
				if (e.getSource() == buttons.get(i+3)) {
					
					// Attempt the purchase.
					if (buildings[i+4*(tier-1)].attemptPurchase(manager.getStockpile()))
						notificationText = "Building purchased!";
					else notificationText = "Purchase Failed.\nNot enough resources...";
					
					repaint();
					
				}
				
			}
		}
			
	}

}
