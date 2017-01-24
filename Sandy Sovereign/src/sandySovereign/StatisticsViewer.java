package sandySovereign;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sandySovereign.Stockpile.Resource;

/**
 * This menu allows the user to see specifics on each of their resources.
 * @author Benjamin Morledge-Hampton
 * @version 1.0 1/24/2017
 */
public class StatisticsViewer extends Menu implements ActionListener{

	/**
	 * Sets up the statistics viewer with a single button.
	 * @param scaleFactor specifies the factor by which to scale the UI.
	 * @param manager specifies the manager that the menu will refer back to.
	 */
	public StatisticsViewer(double scaleFactor, Manager manager) {
		super(scaleFactor, manager);
		
		// The button to go back to the event viewer.
		addButton(50,700, "Back");
		
		repaint();
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		// Get the stockpile.  You'll need it!
		Stockpile s = manager.getStockpile();
		
		// Display headers.
		displayText(g, 275, 50, "Statistics", 40);
		
		displayText(g, 10, 100, "Resources:", 20);
		displayText(g, 100, 100, "Value/Max (+Income)", 20);
		
		displayText(g, 310, 100, "Status of the People:", 20);
		
		displayText(g, 510, 100, "Status of Defenses:", 20);
		
		displayText(g, 10, 500, "Special Resources:", 20);
		
		displayText(g, 260, 400, "Sand Castle Completion:", 20);
		
		displayText(g, 510, 400, "Next Level Requirements:", 20);
		
		// Display info on resources with income values.
		int i = 0;
		for (Resource r : Resource.values()) {
			// Get only the resources with fixed income.
			if (r != Resource.CLAMSHELLS && r != Resource.SANDDOLLARS && r != Resource.HAPPINESS && r != Resource.POPULATION &&
					r != Resource.MOAT && r != Resource.WALLS && r != Resource.PALISADES && r != Resource.LEVEL) {
				
				// Turn the information about the resource into a String.
				String resourceInfo = String.format("%s:    %d/%d (+%d)" , r.toString(),
						s.getResourceValue(r), s.getResourceMax(r), s.getResourceIncome(r));
				
				// Display that string in a nice looking spot.
				displayText(g, 10, 150 + i*50, resourceInfo, 15);
				
				i++;
				
			}
			
		}
		
		// Display info on the population.
		displayText(g, 310, 150, String.format("Population:    %d/%d (+%d)", s.getResourceValue(Resource.POPULATION), 
				s.getResourceMax(Resource.POPULATION), s.getResourceIncome(Resource.POPULATION)), 15);
		displayText(g, 310, 200, String.format("Happiness: %d%s", s.getResourceValue(Resource.HAPPINESS), "%"), 15);
		displayText(g, 310, 250, String.format("Food Consumption: %d", s.getResourceValue(Resource.POPULATION)/10), 15);
		
		// Display info on defenses.
		displayText(g, 510, 150, String.format("Walls:    %d/%d", 
				s.getResourceValue(Resource.WALLS), s.getResourceMax(Resource.WALLS)), 15);
		displayText(g, 510, 200, String.format("Palisades:    %d/%d", 
				s.getResourceValue(Resource.PALISADES), s.getResourceMax(Resource.PALISADES)), 15);
		displayText(g, 510, 250, String.format("Moat:    %d/%d", 
				s.getResourceValue(Resource.MOAT), s.getResourceMax(Resource.MOAT)), 15);
		
		// Display info on special resources.
		i = 0;
		if (s.getResourceValue(Resource.SANDDOLLARS) > 0) {
			displayText(g, 100, 550 + i*50, String.format("Sand Dollars: %d", s.getResourceValue(Resource.SANDDOLLARS)), 15);
			i++;
		}
		if (s.getResourceValue(Resource.CLAMSHELLS) > 0) 
			displayText(g, 100, 550 + i*50, String.format("Clam Shells: %d", s.getResourceValue(Resource.CLAMSHELLS)), 15);
	
		// Display info on the sand castle.
		displayText(g, 310, 500, String.format("%d%s", s.getSandCastleLevel()*20, "%"), 50);
		
		displayText(g, 510, 450, String.format("Population: %d", 40*s.getSandCastleLevel()+40), 15);
		displayText(g, 510, 500, String.format("Seaweed: %d", 40*s.getSandCastleLevel()+40), 15);
		displayText(g, 510, 550, String.format("Driftwood Chips: %d", 40*s.getSandCastleLevel()+40), 15);
		displayText(g, 510, 600, String.format("Seashells: %d", 40*s.getSandCastleLevel()+40), 15);
		displayText(g, 510, 650, String.format("Sand: %d", 40*s.getSandCastleLevel()+40), 15);
		displayText(g, 510, 700, String.format("Pebbles: %d", 40*s.getSandCastleLevel()+40), 15);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		manager.switchMenus(Manager.EVENT_VIEWER);
	}

}
