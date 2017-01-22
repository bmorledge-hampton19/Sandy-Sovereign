package sandySovereign;

import java.util.Map;

/**
 * A class to handle a possible result from an event.
 * @author Benjamin Morledge-Hampton
 * @version 1.0 1/21/2017
 */
public class Result {

	// EnumMaps which keep track of how to alter stockpile values on either success or failure of the choice.
	private Map<Stockpile.Resource,Integer> success;
	private Map<Stockpile.Resource,Integer> failure;
	
	// The text to be displayed on either success of failure.
	private String successMessage;
	private String failureMessage;
	
	// The stockpile to be accessed.
	private Stockpile s;
	
	/**
	 * Constructor which initializes the Maps, messages, and Stockpile.
	 * @param successMessage specifies the success message to be set.
	 * @param failureMessage specifies the failure message to be set.
	 * @param s specifies the stockpile to be altered.
	 */
	Result(String successMessage, String failureMessage, Stockpile s) {
		
		// Initialize messages with given values.
		this.successMessage = successMessage;
		this.failureMessage = failureMessage;
		
		// Initialize Maps to default values.
		success = Stockpile.getResourceMap();
		failure = Stockpile.getResourceMap();
		
		// Initialize the Stockpile.
		this.s = s;
	}
	
	/**
	 * Determine the outcome of the choice and alter the stockpile accordingly.
	 * @param succeeded specifies whether or not the choice succeeded.
	 * @return the message describing the result.
	 */
	public String outcome(boolean succeeded) {
		
		// The message to be returned.
		String message;
		
		// Initialize the message with the appropriate stored message.
		if (succeeded) message = successMessage;
		else message = failureMessage;
		message += "\n";
		
		// Run through every resource value.
		for (Stockpile.Resource r : Stockpile.Resource.values()) {
			
			// Determine whether the success of failure map will be used.
			if (succeeded) {
				
				// Make sure the current resource value has been set.
				if (success.get(r) != null) {
					
					// Make the change.
					s.alterResourceValue(r, success.get(r));
					
					// Add to the message.
					message += addChangeToMessage(r,success.get(r));
					
				}
				
			}
			else {
				
				// Make sure the current resource value has been set.
				if (failure.get(r) != null) {
					
					// Make the change.
					s.alterResourceValue(r, failure.get(r));
					
					// Add to the message.
					message += addChangeToMessage(r,failure.get(r));
					
				}
				
			}
				
		}
		
		// Return the message.
		return message;
		
	}
	
	/**
	 * Adds a message detailing a change in resources.
	 * @param r specifies the resource being changed.
	 * @param value specifies how the resource is being changed.
	 * @return the message detailing the change.
	 */
	private String addChangeToMessage(Stockpile.Resource r, int value) {
		
		// The message should be different depending on whether or not the value is positive.
		if (value < 0) {
			
			// If the resource was depleted completely, let the user know.
			if (s.getResourceValue(r) == 0) {
				return String.format("%s reduced completely...\n", r.toString());
			}
			// Otherwise, tell the user by how much it was reduced.
			else return String.format("%s reduced by %d.\n", r.toString(), -value);
			
		}
		else return String.format("%s increased by %d.\n", r.toString(), value);
		
	}
	
	/**
	 * Adds another value to be altered in the stockpile on success.
	 * @param r specifies the resource to be modified.
	 * @param value specifies the amount to alter the resource by.
	 */
	public void addToSuccess(Stockpile.Resource r, int value) {success.put(r, value);}
	
	/**
	 * Adds another value to be altered in the stockpile on failure.
	 * @param r specifies the resource to be modified.
	 * @param value specifies the amount to alter the resource by.
	 */
	public void addToFailure(Stockpile.Resource r, int value) {failure.put(r, value);}
	
}
