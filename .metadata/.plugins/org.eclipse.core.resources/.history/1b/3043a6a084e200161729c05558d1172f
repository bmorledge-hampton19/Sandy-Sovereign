package sandySovereign;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOver extends Menu implements ActionListener{

	//The text shown on win or lose.
	String winText;
	String loseText;
	
	/**
	 * This menu pops up when the game ends and tells the user if they won or lost.
	 * @param scaleFactor
	 * @param manager
	 */
	public GameOver(double scaleFactor, Manager manager) {
		super(scaleFactor, manager);

		// Set up the descriptive text for winning or losing.
		winText = "Your kingdom is flourishing and your grand sand castle has brought\n\n";
		winText += "wealth, happiness, and recognition to your people who so deserve it!\n\n";
		winText += "Your name will go down in the history of the sand people as a legend!\n\n";
		winText += "You've done your duty, and you've done it well.\n\n\n";
		winText += "You are truly a grand, wise, noble, sandy sovereign.";
		
		loseText = "Your kingdom is in shambles from your poor luck and leadership...\n\n";
		loseText += "Certainly your people would look down on you with contempt,\n\n";
		loseText += "but sadly, no one is left to do so.  Only you remain...\n\n";
		loseText += "Perhaps another time will see you victorious, but not today...\n\n";
	
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		if (manager.hasWon()){
			// Display winning text.
			displayText(g, 300, 30, "YOU WON!!!", 40);
			displayText(g, 100, 200, winText, 20);
		}
		else {
			// Display losing text.
			displayText(g, 300, 30, "You lost...", 40);
			displayText(g, 100, 200, loseText, 20);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {}

}
