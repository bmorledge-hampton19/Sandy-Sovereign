package sandySovereign;

import java.util.*;

/**
 * A class to determine whether a requirement for an event has been fulfilled.
 * @author Benjamin Morledge-Hampton
 * @version 1.0 1/21/2017
 */
public class Conditional {

	// A hash map that keeps track of the requirements for each resource in the stockpile.
	private Map<Stockpile.Resource,Integer> required;
	
	// The stockpile to check requirements against.
	private Stockpile s;
	
	/**
	 * A simple default constructor which merely initializes the required Map and stockpile.
	 * @param s specifies the stockpile to be initialized with.
	 */
	Conditional(Stockpile s) {
		required = Stockpile.getResourceMap();
		this.s = s;
	}
	
	/**
	 * Add a requirement to the required map.
	 * @param resource specifies the resource value to be checked.
	 * @param value specifies the minimum required value.
	 */
	public void addCondition(Stockpile.Resource resource, int value) {
		// Update the value for the specified resource to the specified value.
		required.put(resource, value);
	}
	
	/**
	 * Checks to see if the requirements for the conditional are fulfilled.
	 * @param s specifies the stockpile to be checked against.
	 * @return whether or not the check succeeds.
	 */
	public boolean checkCondition() {
	
		// Get ready to check every resource via its corresponding enumerator.
		for (Stockpile.Resource r : Stockpile.Resource.values()) {
			// Check that a requirement has been set before progressing.
			if (required.get(r) != null) {
				
				// Ensure that the stockpile value is greater than or equal to the required minimum.
				// If it's not, the check fails.
				if (s.getResourceValue(r) < required.get(r)) return false;
				
			}			
		}
		
		// If no requirements were failed, the check succeeds!
		return true;
		
	}
	
}
