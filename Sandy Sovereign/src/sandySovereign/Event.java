package sandySovereign;

/**
 * A class to keep track of the many events and choices presented to the player.
 * @author Benjamin Morledge-Hampton
 * @version 1.0 1/21/2017
 */
public class Event {

	// An array of all of the possible outcomes to the event and their respective conditionals.
	private Result[] results;
	private Conditional[] conditionals;
	
	// An array of the text displayed for each option.
	private String[] optionText;
	
	// The Description for the event.
	private String description;
	
	// The number of options available to the user for the event.
	private int numberOfOptions;
	
	/**
	 * Creates an event with the specified number of options, description text, and option text.
	 * @param description specifies the explanation for the event.
	 * @param optionText specifies the descriptions for the choices.
	 */
	Event(String description, String[] optionText) {
		
		// Do this first.
		numberOfOptions = optionText.length;		
		
		// Allocate space for arrays.
		results = new Result[numberOfOptions];
		conditionals = new Conditional[numberOfOptions];
		this.optionText = new String[numberOfOptions];
		
		// Initialize all of the given values.
		this.description = description;
		for (int i = 0; i < numberOfOptions; i++)
			this.optionText[i] = optionText[i];
		
	}
	
	/**
	 * Clone the event while scaling up the values within each result and conditional.
	 * @param scaleFactor specifies the multiplicative factor by which to scale values.
	 * @return the cloned and scaled event.
	 */
	public Event Clone(double scaleFactor) {
		
		// Construct a new event with this events constructor parameters.
		Event clonedEvent = new Event(description, optionText);
	
		// Copy over the results and conditionals, modifying their values based on the scaleFactor.
		for (int i = 0; i < results.length; i++)
			clonedEvent.results[i] = results[i].clone(scaleFactor);
		
		for (int i = 0; i < conditionals.length; i++)
			clonedEvent.conditionals[i] = conditionals[i].clone(scaleFactor);
	
		// Return the cloned event.
		return clonedEvent;
		
	}
	
	/**
	 * Initializes all of the results and conditionals.
	 * @param s specifies the stockpile to be used by the results and conditionals.
	 * @param successMessages specifies the success messages for the results.
	 * @param failureMessages specifies the failure messages for the results.
	 */
	public void initializeResultsAndConditionals(Stockpile s, String[] successMessages, String[] failureMessages) {
		for (int i = 0; i < numberOfOptions; i++) {
			
			// Initialize the conditionals using the given stockpile.
			conditionals[i] = new Conditional(s);
			
			// Initialize the results with the given given stockpile and messages.
			results[i] = new Result(successMessages[i], failureMessages[i], s);
			
		}
	}
	
	/**
	 * Add a success alteration to the indicated result object.
	 * @param optionNumber specifies which result to alter.
	 * @param r specifies the resource to be altered.
	 * @param value specifies by how much to alter the resource.
	 */
	public void addSuccess(int optionNumber, Stockpile.Resource r, int value) {
		// Add to the indicated result's success occurrences.
		results[optionNumber].addToSuccess(r, value);
	}
	
	/**
	 * Add a failure alteration to the indicated result object.
	 * @param optionNumber specifies which result to alter.
	 * @param r specifies the resource to be altered.
	 * @param value specifies by how much to alter the resource.
	 */
	public void addFailure(int optionNumber, Stockpile.Resource r, int value) {
		// Add to the indicated result's failure occurrences.
		results[optionNumber].addToFailure(r, value);
	}
	
	/**
	 * Add a requirement to the indicated conditional object.
	 * @param optionNumber specifies which conditional to alter.
	 * @param r specifies the resource to be checked.
	 * @param values specifies the minimum required amount of that resource.
	 */
	public void addConditional(int optionNumber, Stockpile.Resource r, int value) {
		// Add to the indicated conditional's requirements.
		conditionals[optionNumber].addCondition(r, value);
	}
	
	/**
	 * Concludes the event by activating the results of a chosen option.
	 * @param optionNumber specifies the chosen option to conclude the event with.
	 * @return the message string provided by the result's outcome function.
	 */
	public String activateResult(int optionNumber) {
		return results[optionNumber].outcome(conditionals[optionNumber].checkCondition());
	}
	
	/**
	 * Returns the description for the event.
	 * @return the description for the event.
	 */
	public String getDescription() {return description;}
	
	/**
	 * Returns the descriptive text for the given option.
	 * @param index specifies which option to receive text from.
	 * @return the description text for the given option.
	 */
	public String getOptionText(int index) {return optionText[index];}
	
	/**
	 * returns the number of options in the event.
	 * @return the number of options in the event.
	 */
	public int getNumberOfOptions() {return numberOfOptions;}
	
}
