package sandySovereign;

import java.util.Map;

import sandySovereign.Stockpile.Resource;

import java.util.EnumMap;

/**
 * A structure that can be purchased by the player to increase resource income and max.
 * @author Benjamin Morledge-Hampton
 * @version 1.0 1/22/2017
 */
public class Building {

	// Whether or not the building has been purchased.
	private boolean purchased;
	
	// The level of the sand castle required to purchase the building.
	private int sandCastleRequirement;
	
	// The cost of building as well as the effects.
	private Map<Stockpile.Resource,Integer> cost;
	private Map<Stockpile.Resource,Integer> incomeUpgrades;
	private Map<Stockpile.Resource,Integer> maxUpgrades;
	
	// The name of the building, its cost, and a short description of its effects.
	private String name;
	private String costString;
	private String description;
	
	/**
	 * Constructs a building with the given name and description.
	 * @param name specifies the name of the building.
	 * @param description specifies the description of the building's effects.
	 * @param sandCastleRequirement specifies the sand castle level required to build the building.
	 */
	Building(String name, String description, int sandCastleRequirement) {
		
		// Set the Strings.
		this.name = name;
		this.description = description;
		costString = "Cost:\n";
		
		// Set the sand castle requirement.
		this.sandCastleRequirement = sandCastleRequirement;
		
		// Initialize EnumMaps.
		cost = new EnumMap<Stockpile.Resource,Integer>(Stockpile.Resource.class);
		incomeUpgrades = new EnumMap<Stockpile.Resource,Integer>(Stockpile.Resource.class);
		maxUpgrades = new EnumMap<Stockpile.Resource,Integer>(Stockpile.Resource.class);
		
	}
	
	/**
	 * Initializes all of the game's buildings
	 */
	static Building[] initializeBuildings() {
		
		// The array that the buildings will be put into.
		Building[] buildings = new Building[16];
		
		// Tier 1 buildings (1st Income upgrades.)
		buildings[0] = new Building("Beach Comber's Hut", 
				"Trains sand people to comb the beach for driftwood,\nquality sand, and pebbles.", 1);
		buildings[0].addToCost(Resource.DRIFTWOODCHIPS, 20);
		buildings[0].addToCost(Resource.SAND, 20);
		buildings[0].addToCost(Resource.PEBBLES, 20);
		buildings[0].addToCost(Resource.FOOD, 15);
		buildings[0].addToIncomeUpgrades(Resource.DRIFTWOODCHIPS, 2);
		buildings[0].addToIncomeUpgrades(Resource.SAND, 2);
		buildings[0].addToIncomeUpgrades(Resource.PEBBLES, 2);
		
		buildings[1] = new Building("Sea Diver's Hut",
				"Trains sand people to dive into the ocean to retrieve\nseaweed and seashells.", 1);
		buildings[1].addToCost(Resource.SEAWEED, 20);
		buildings[1].addToCost(Resource.SEASHELLS, 20);
		buildings[1].addToCost(Resource.FOOD, 15);
		buildings[1].addToIncomeUpgrades(Resource.SEAWEED, 2);
		buildings[1].addToIncomeUpgrades(Resource.SEASHELLS, 2);
		
		buildings[2] = new Building("Hunter Gatherer Hut",
				"Trains sand people to bring a steady flow of food\ninto the city to support further growth.", 1);
		buildings[2].addToCost(Resource.PEBBLES, 20);
		buildings[2].addToCost(Resource.SAND, 20);
		buildings[2].addToCost(Resource.SEASHELLS, 20);
		buildings[2].addToIncomeUpgrades(Resource.FOOD, 2);
		
		buildings[3] = new Building("Flashy Flag",
				"Attracts sand people from around the beach to your\ncivilization.", 1);
		buildings[3].addToCost(Resource.DRIFTWOODCHIPS, 20);
		buildings[3].addToCost(Resource.SEAWEED, 20);
		buildings[3].addToCost(Resource.FOOD, 10);
		buildings[3].addToIncomeUpgrades(Resource.POPULATION, 2);
		
		// Tier 2 buildings (1st Max upgrades.)
		buildings[4] = new Building("Storage house",
				"Extra storage for all of the building materials.", 2);
		buildings[4].addToCost(Resource.DRIFTWOODCHIPS, 40);
		buildings[4].addToCost(Resource.PEBBLES, 40);
		buildings[4].addToCost(Resource.FOOD, 20);
		buildings[4].addToMaxUpgrades(Resource.DRIFTWOODCHIPS, 100);
		buildings[4].addToMaxUpgrades(Resource.SAND, 100);
		buildings[4].addToMaxUpgrades(Resource.PEBBLES, 100);
		buildings[4].addToMaxUpgrades(Resource.SEAWEED, 100);
		buildings[4].addToMaxUpgrades(Resource.SEASHELLS, 100);
		
		buildings[5] = new Building("Engineering Guild House",
				"Extra durability for defenses.", 2);
		buildings[5].addToCost(Resource.PEBBLES, 40);
		buildings[5].addToCost(Resource.SEAWEED, 40);
		buildings[5].addToCost(Resource.FOOD, 20);
		buildings[5].addToMaxUpgrades(Resource.WALLS, 100);
		buildings[5].addToMaxUpgrades(Resource.PALISADES, 100);
		buildings[5].addToMaxUpgrades(Resource.MOAT, 100);
		
		buildings[6] = new Building("Food Storage",
				"Extra storage for foodstuffs.", 2);
		buildings[6].addToCost(Resource.SAND, 40);
		buildings[6].addToCost(Resource.SEASHELLS, 40);
		buildings[6].addToCost(Resource.FOOD, 20);
		buildings[6].addToMaxUpgrades(Resource.FOOD, 100);
		
		buildings[7] = new Building("Housing increase",
				"Increases population cap", 2);
		buildings[7].addToCost(Resource.DRIFTWOODCHIPS, 40);
		buildings[7].addToCost(Resource.SEASHELLS, 40);
		buildings[7].addToCost(Resource.FOOD, 20);
		buildings[7].addToMaxUpgrades(Resource.POPULATION, 100);
		
		// Tier 3 buildings (2nd Income upgrades.)
		buildings[8] = new Building("Beach Comber's School", 
				"Teaches sand people to adeptly comb the beach for\ndriftwood, quality sand, and pebbles.", 3);
		buildings[8].addToCost(Resource.DRIFTWOODCHIPS, 50);
		buildings[8].addToCost(Resource.SAND, 50);
		buildings[8].addToCost(Resource.PEBBLES, 50);
		buildings[8].addToCost(Resource.FOOD, 30);
		buildings[8].addToIncomeUpgrades(Resource.DRIFTWOODCHIPS, 5);
		buildings[8].addToIncomeUpgrades(Resource.SAND, 5);
		buildings[8].addToIncomeUpgrades(Resource.PEBBLES, 5);
		
		buildings[9] = new Building("Sea Diver's School", 
				"Teaches sand people to adeptly dive into the ocean\n to retrieve seaweed and seashells", 3);
		buildings[9].addToCost(Resource.SEAWEED, 50);
		buildings[9].addToCost(Resource.SEASHELLS, 50);
		buildings[9].addToCost(Resource.FOOD, 30);
		buildings[9].addToIncomeUpgrades(Resource.SEAWEED, 5);
		buildings[9].addToIncomeUpgrades(Resource.SEASHELLS, 5);
		
		buildings[10] = new Building ("The Order of Crab Hunters.", 
				"Skilled hunters who bring home the bacon\n(er... Crab meat?)", 3);
		buildings[10].addToCost(Resource.PEBBLES, 50);
		buildings[10].addToCost(Resource.SAND, 50);
		buildings[10].addToCost(Resource.SEASHELLS, 50);
		buildings[10].addToIncomeUpgrades(Resource.FOOD, 5);
		
		buildings[11] = new Building ("Spectular Monument", 
				"Impress local sand people nations to join your cause!", 3);
		buildings[11].addToCost(Resource.DRIFTWOODCHIPS, 50);
		buildings[11].addToCost(Resource.SEAWEED, 50);
		buildings[11].addToCost(Resource.FOOD, 25);
		buildings[11].addToIncomeUpgrades(Resource.POPULATION, 5);
		
		// Tier 4 buildings (2nd Max upgrades.)
		buildings[12] = new Building("Storage Units", 
				"Increases storage for building materials even further!", 4);
		buildings[12].addToCost(Resource.DRIFTWOODCHIPS, 80);
		buildings[12].addToCost(Resource.PEBBLES, 80);
		buildings[12].addToCost(Resource.FOOD, 40);
		buildings[12].addToMaxUpgrades(Resource.DRIFTWOODCHIPS, 100);
		buildings[12].addToMaxUpgrades(Resource.SAND, 100);
		buildings[12].addToMaxUpgrades(Resource.PEBBLES, 100);
		buildings[12].addToMaxUpgrades(Resource.SEAWEED, 100);
		buildings[12].addToMaxUpgrades(Resource.SEASHELLS, 100);
		
		buildings[13] = new Building("Engineering University", 
				"Advances engineering techniques for better defenses.", 4);
		buildings[13].addToCost(Resource.PEBBLES, 80);
		buildings[13].addToCost(Resource.SEAWEED, 80);
		buildings[13].addToCost(Resource.FOOD, 40);
		buildings[13].addToMaxUpgrades(Resource.WALLS, 100);
		buildings[13].addToMaxUpgrades(Resource.PALISADES, 100);
		buildings[13].addToMaxUpgrades(Resource.MOAT, 100);
		
		buildings[14] = new Building("High-Capacity Food Storage", 
				"Even more space for long-term food storage.", 4);
		buildings[14].addToCost(Resource.SAND, 80);
		buildings[14].addToCost(Resource.SEASHELLS, 80);
		buildings[14].addToCost(Resource.FOOD, 40);
		buildings[14].addToMaxUpgrades(Resource.FOOD, 100);
		
		buildings[15] = new Building("Sand-Apartments",
				"Extra housing for your growing population!", 4);
		buildings[15].addToCost(Resource.DRIFTWOODCHIPS, 80);
		buildings[15].addToCost(Resource.SEASHELLS, 80);
		buildings[15].addToCost(Resource.FOOD, 40);
		buildings[15].addToMaxUpgrades(Resource.POPULATION, 100);
		
		// Return the prepared buildings.
		return buildings;
		
	}
	
	/**
	 * Add a resource cost to the map of costs for the building.
	 * @param r specifies the resource.
	 * @param value specifies the amount of the resource required to purchase the building.
	 */
	public void addToCost(Stockpile.Resource r, int value) {
	
		// Add the cost to the cost map.
		cost.put(r, value);
		
		// Append to the cost String.
		costString += String.format("%d %s\n", value, r.toString());
		
	}
	
	/**
	 * Add an upgrade to resource income to apply when the building is purchased.
	 * @param r specifies the resource to upgrade income for.
	 * @param value specifies the amount to upgrade by.
	 */
	public void addToIncomeUpgrades(Stockpile.Resource r, int value) {incomeUpgrades.put(r, value);}
	
	/**
	 * Add an upgrade to resource capacity to apply when the building is purchased.
	 * @param r specifies the resource to upgrade maximum for.
	 * @param value specifies the amount to upgrade by.
	 */
	public void addToMaxUpgrades(Stockpile.Resource r, int value) {maxUpgrades.put(r, value);}
	
	/**
	 * Try to purchase the building.  If the purchase is successful, apply the building's upgrades.
	 * @param s specifies the stockpile to pull values from and upgrade to.
	 * @return whether or not the purchase was successful!
	 */
	public boolean attemptPurchase(Stockpile s) {
		
		// First, check to see if all of the necessary resources are available to cover the cost.
		for (Stockpile.Resource r : cost.keySet()) {		
			// Fail the attempted purchase if any resource in the stockpile falls short of the cost.
			if (s.getResourceValue(r) < cost.get(r)) return false;
		}
		
		// If all the checks succeeded, subtract the cost from the stockpile.
		for (Stockpile.Resource r : cost.keySet()) 
			s.alterResourceValue(r,-cost.get(r));
		
		// Now apply all of the upgrades for the building.
		
		// First the income upgrades.
		for (Stockpile.Resource r : incomeUpgrades.keySet())
			s.alterResourceIncome(r,incomeUpgrades.get(r));
		
		// Next the max upgrades.
		for (Stockpile.Resource r : maxUpgrades.keySet())
			s.alterResourceMax(r,maxUpgrades.get(r));
		
		// The purchase was successful and upgrades have been applied!
		return (purchased=true);
		
	}
	
	/**
	 * Returns whether or not the building has been purchased.
	 * @return whether or not the building has been purchased.
	 */
	public boolean isPuchased() {return purchased;}
	
	/**
	 * Returns the level of the sand castle required to purchase the building.
	 * @return the level of the sand castle required to purchase the building.
	 */
	public int getSandCastleRequirement() {return sandCastleRequirement;}
	
	/**
	 * Returns the name of the Building.
	 * @return the name of the Building.
	 */
	public String getName() {return name;}
	
	/**
	 * Returns the cost of the building as a string.
	 * @return the cost of the building as a string.
	 */
	public String getCostString() {return costString;}
	
	/**
	 * Returns the description of the building.
	 * @return the description of the building.
	 */
	public String getDescription() {return description;}
	
}
