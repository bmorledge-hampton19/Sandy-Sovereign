package sandySovereign;

import java.util.EnumMap;
import java.util.Map;

/**
 * Keeps track of all of the player's resources.
 * @author Benjamin Morledge-Hampton
 * @version 1.0 1/21/2017
 */
public class Stockpile {

	// The building materials available to the player.
	private int seaweed;
	private int seashells;
	private int sand;
	private int driftwoodChips;
	private int pebbles;
	
	// The status of the sand people.
	private int population;
	private int happiness;
	private int food;
	
	// Special items.
	private int sandDollars;
	private int clamShells;
	
	// The enumerator for all of the resources.
	public enum Resource {
		SEAWEED("Seaweed"), SEASHELLS("Seashells"), SAND("Sand"), DRIFTWOODCHIPS("Driftwood Chips"), PEBBLES("Pebbles"),
		POPULATION("Population"), HAPPINESS("Happiness"), FOOD("Food"),
		SANDDOLLARS("Sand Dollars"), CLAMSHELLS("Clam Shells");
		
		// Add some stuff to allow conversion to Strings.
		private String resourceName;
		private Resource(String resourceName) {
			this.resourceName = resourceName;
		}
		public String toString() {return resourceName;}
		
		
	}
	
	/**
	 * Returns an EnumMap with keys for all of the Resource enumerators and default values "null".
	 * @return the default Resource EnumMap.
	 */
	static public Map<Resource,Integer> getResourceMap() {
		
		// The EnumMap to be returned.
		Map<Resource,Integer> resources = new EnumMap<Resource,Integer>(Resource.class);
		
		// Add each resource to the EnumMap with the default value, "null".
		for (Resource r : Resource.values())
			resources.put(r, null);
		
		// Return the EnumMap
		return resources;
		
	}
	
	/**
	 * Gets a resourceValue based on the enumerator value passed to the function.
	 * @param resource specifies the resource to retrieve the value from.
	 * @return the retrieved value for the specified resource.
	 */
	public int getResourceValue(Resource resource) {
		
		// The value to be returned.
		int returnThis = 0;
		
		// Use the enumerator value to determine what value needs to be passed back.
		switch (resource) {
		
		case CLAMSHELLS:
			returnThis = clamShells;
			break;
		case DRIFTWOODCHIPS:
			returnThis = driftwoodChips;
			break;
		case FOOD:
			returnThis = food;
			break;
		case HAPPINESS:
			returnThis = happiness;
			break;
		case PEBBLES:
			returnThis = pebbles;
			break;
		case POPULATION:
			returnThis = population;
			break;
		case SAND:
			returnThis = sand;
			break;
		case SANDDOLLARS:
			returnThis = sandDollars;
			break;
		case SEASHELLS:
			returnThis = seashells;
			break;
		case SEAWEED:
			returnThis = seaweed;
			break;
			
		}
		
		// Return the specified value.
		return returnThis;
		
	}
	
	/**
	 * Add a given value to a resource determined by the passed enumerator value.
	 * @param resource specifies the resource to be altered.
	 * @param alter specifies the amount to add to the resource value. (Can be negative.)
	 */
	public void alterResourceValue(Resource resource, int alter) {
		
		
		// Use the enumerator value to determine which resource needs to have its value set.
		// Also adjust for invalid values.
		switch (resource) {
		
		case CLAMSHELLS:
			clamShells += alter;
			if (clamShells < 0) clamShells = 0;
			break;
			
		case DRIFTWOODCHIPS:
			driftwoodChips += alter;
			if (driftwoodChips < 0) driftwoodChips = 0;
			break;
			
		case FOOD:
			food += alter;
			if (food < 0) food = 0;
			if (food > 1000) food = 1000;
			break;
			
		case HAPPINESS:
			happiness += alter;
			if (happiness < 0) happiness = 0;
			if (happiness > 100) happiness = 100;
			break;
			
		case PEBBLES:
			pebbles += alter;
			if (pebbles < 0) pebbles = 0;
			break;
			
		case POPULATION:
			population += alter;
			if (population < 0) population = 0;
			break;
			
		case SAND:
			sand += alter;
			if (sand < 0) sand = 0;
			break;
			
		case SANDDOLLARS:
			sandDollars += alter;
			if (sandDollars < 0) sandDollars = 0;
			break;
			
		case SEASHELLS:
			seashells += alter;
			if (seashells < 0) seashells = 0;
			break;
			
		case SEAWEED:
			seaweed += alter;
			if (seaweed < 0) seaweed = 0;
			break;
			
		}
		
	}
	
	
	
}
