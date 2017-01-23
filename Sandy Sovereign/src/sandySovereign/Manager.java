package sandySovereign;

import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * A class which manages the communication between model, view and controller.
 * @author Benjamin Morledge-Hampton
 * @version 1.0 1/21/2017
 */
public class Manager extends JFrame{

	// An array for all of the different menus as well as constants to access the indices.
	Menu[] menus;
	static final int MAIN_MENU = 0;
	static final int EVENT_VIEWER = 1;
	static final int STATISTICS_VIEWER = 2;
	static final int BUILDER = 3;
	static final int GAME_OVER = 4;
	
	// A value to keep track of the current menu type.
	int currentMenu;
	
	// A counter to keep track of the number of turns that have passed.
	private int currentTurn;
	
	// The degree by which the UI needs to be scaled.
	double scaleFactor;
	
	// Keeps track of whether or not the sand castle builder event has been given in the current cycle of ten turns.
	boolean sandCastleBuilderGiven;
	
	// Tells the Game Over screen whether the user has won or lost.
	boolean won;
	
	// The current event being used.
	private Event currentEvent;
	
	// The event manager which selects events.
	private EventManager eventManager;
	
	// The stockpile, where all resources are stored.
	Stockpile stockpile;
	
	// The buildings to be purchased throughout the game.
	Building[] buildings;
	
	/**
	 * Constructor that initializes values such as the menus and stockpile.
	 * Also sets up the JFrame.
	 */
	Manager() {
		
		// Start setting up the JFrame.
		super("Sandy Sovereign");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Find a scale factor for the UI.
		scaleFactor = Toolkit.getDefaultToolkit().getScreenSize().getWidth()/1920;
		
		// Continue setting up JFrame.
		setSize((int)(800*scaleFactor),(int)(800*scaleFactor));
		setVisible(true);
		
		// Initialize everything!
		menus = new Menu[5];// TODO add the 5 menus to this array.
		
		add(menus[MAIN_MENU]);
		
		eventManager = new EventManager("");
		stockpile = new Stockpile();
		currentTurn = 1;
		
		buildings = Building.initializeBuildings();
		
	}
	
	/**
	 * Returns and sets the next event for the user to confront.
	 * @return the next event for the user to confront.
	 */
	public Event getNextEvent() {
		
		// Reset the sand castle builder boolean if this set of turn turns is just beginning.
		if (currentTurn % 10 == 1) sandCastleBuilderGiven = false;
		
		// Using the current turn number, determine what type of event needs to occur.
		if (currentTurn % 10 == 0) 
			currentEvent = eventManager.retrieveDisaster(stockpile, currentTurn);
		else if ((currentTurn % 10) % 3 == 0)
			currentEvent = eventManager.retrieveConstructionEvent(stockpile);
		else 
			currentEvent = eventManager.retrieveRandomEvent(stockpile.getSandCastleLevel());
		
		// Return the newly set event.
		return currentEvent;
		
	}
	
	/**
	 * Switch the menu that is being displayed to the user.
	 * @param newMenu specifies the menu to switch to.
	 * @return either a sandCastleBuilder event or null.
	 */
	public void switchMenus(int newMenu) {
		remove(menus[currentMenu]);
		add(menus[newMenu]);
	}
	
	/**
	 * Get the results of the current event based on the user's decision.
	 * @param choice specifies the option chosen by the user.
	 * @return the sandCastleBuilderEvent if appropriate.
	 */
	public Event concludeEvent(int choice) {
		
		// Activate the results of the event.
		currentEvent.activateResult(choice);
		currentEvent = null;
		
		// Check to see if it's time for the sandCastleBuilder event.
		if (currentTurn % 10 == 0 && !sandCastleBuilderGiven) {
			currentEvent = eventManager.retrieveSandCastleBuilderEvent(stockpile.getSandCastleLevel());
			sandCastleBuilderGiven = true;
		}
		else currentTurn++;
		
		// Return the current event. (Important if it is the sandCastleBuilder.)
		return currentEvent;
		
	}
	
	/**
	 * Check for victory or loss.
	 */
	private void checkForGameOver() {
		
		// Check for victory.
		if (stockpile.getSandCastleLevel() == 5) {
			won = true;
			switchMenus(GAME_OVER);
		}
		// Check for loss.
		else if (stockpile.getResourceValue(Stockpile.Resource.POPULATION) == 0) {
			won = false;
			switchMenus(GAME_OVER);
		}
		
	}
	
	/**
	 * Returns whether the user won or not.
	 * @return whether the user won or not.
	 */
	public boolean hasWon() {return won;}
	
}
