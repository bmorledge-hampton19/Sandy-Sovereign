package sandySovereign;

import java.util.EnumMap;
import java.util.Map;

import sandySovereign.Stockpile.Resource;

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
		success = new EnumMap<Stockpile.Resource,Integer>(Stockpile.Resource.class);
		failure = new EnumMap<Stockpile.Resource,Integer>(Stockpile.Resource.class);
		
		// Initialize the Stockpile.
		this.s = s;
	}
	
	/**
	 * Returns a Result object with the same success and failure messages, and scaled success and failure values.
	 * @param scaleFactor specifies the multiplicative factor with which to scale up values.
	 * @return the copied and scaled object.
	 */
	public Result clone(double scaleFactor) {
		
		// Create a new Result object to clone to.
		Result clonedResult = new Result(successMessage, failureMessage, s);
		
		// Copy over the success and failure values scaled up. 
		// (Happiness, Sand Dollars, and Clam Shells don't scale.)
		for (Stockpile.Resource r : Stockpile.Resource.values()) {
			
			if (success.get(r) != null) {
				clonedResult.success.put(r, (int)(success.get(r)*scaleFactor));
				if (r == Resource.HAPPINESS || r == Resource.SANDDOLLARS || r == Resource.CLAMSHELLS)
					clonedResult.success.put(r, success.get(r));
			}
			
			if (failure.get(r) != null) {
				clonedResult.failure.put(r, (int)(failure.get(r)*scaleFactor));
				if (r == Resource.HAPPINESS || r == Resource.SANDDOLLARS || r == Resource.CLAMSHELLS)
					clonedResult.failure.put(r, failure.get(r));
			}
		}
		
		// Return the cloned object.
		return clonedResult;
		
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
					
					if (r != Stockpile.Resource.LEVEL)
						// Make the change.
						s.alterResourceValue(r, success.get(r));
					else 
						// Increase the level of the sand castle!
						s.increaseSandCastleLevel();
					
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
			if (s.getResourceValue(r) == 0)
				return String.format("%s reduced completely...\n", r.toString());
				
			// Otherwise, tell the user by how much it was reduced.
			else return String.format("%s reduced by %d.\n", r.toString(), -value);
			
		}
		else {
			
			// If the resource was capped at the max value, let the user know.
			if (r != Resource.LEVEL && s.getResourceValue(r) == s.getResourceMax(r))
				return String.format("%s increased to maximum!\n", r.toString());
			
			// Otherwise, tell the user by how much it was increased.
			else return String.format("%s increased by %d.\n", r.toString(), value);
			
		}
		
	}
	
	/**
	 * Adds another value to be altered in the stockpile on success.
	 * @param r specifies the resource to be modified.
	 * @param value specifies the amount to alter the resource by.
	 */
	public void addToSuccess(Stockpile.Resource r, int value) {
		success.put(r, value);
		if (value==0) success.remove(r);
	}
	
	/**
	 * Adds another value to be altered in the stockpile on failure.
	 * @param r specifies the resource to be modified.
	 * @param value specifies the amount to alter the resource by.
	 */
	public void addToFailure(Stockpile.Resource r, int value) {
		failure.put(r, value);
		if (value==0) failure.remove(r);
	}
	
}
