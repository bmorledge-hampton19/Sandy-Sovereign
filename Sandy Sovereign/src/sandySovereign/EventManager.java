package sandySovereign;

import java.io.*;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

import sandySovereign.Stockpile.Resource;

/**
 * Manages the many events and prepares them with the changes caused by varying resources and the current turn.
 * @author Benjamin Morledge-Hampton
 * @version 1.0 1/23/2017
 */
public class EventManager {
	
	// Random events that are given to the player throughout the game.
	ArrayList<Event> randomEvents;
	
	// The disasters that befall the player.
	ArrayList<Event> disaster;
	
	// The fixed construction event that allows the player to reinforce defenses.
	Event construction;
	
	// The fixed sand castle event that gives the player the option to increase the level of their sand castle.
	Event sandCastleBuilder;
	
	/**
	 * Initialize the events from a text file.
	 * @param fileName specifies the name of the text file to be read from.
	 */
	EventManager(String fileName, Stockpile s) {
		
		// Initialize the ArrayLists.
		randomEvents = new ArrayList<Event>();
		disaster = new ArrayList<Event>();
		
		// Load in the events.
		loadEvents(fileName, s);
	
	}
	
	/*
	DESCRIPTION
	
	OPTION TEXT
	
	CREATE EVENT
	
	SUCCESS TEXT
	
	FAILURE TEXT
	
	INIT R AND C
	
	SUCCESS RESULT
	
	FAILURE RESULT
	
	CONDITIONAL
	
	END EVENT
	RANDOM EVENT
	 */
	
	/**
	 * Load in the details for the events.
	 * @param fileName specifies the name of the text file to load in from.
	 */
	private void loadEvents(String fileName, Stockpile s) {
		
		// Load in the text file.
		try (FileInputStream is = new FileInputStream(new File(fileName))) {
			
			// Get to the buffered reader.
			InputStreamReader ir = new InputStreamReader(is);
			BufferedReader rdr = new BufferedReader(ir);
			
			// Keeps track of the line number for errors.
			int lineNumber = 0;
			
			// Read in lines until EOF
			String line;
			while ((line = rdr.readLine()) != null) {
				
				lineNumber++;
				
				// Remove excess whitespace.
				while (line != null && line.length() == 0) {
					line = rdr.readLine();
					lineNumber++;
				}
				
				// Make sure the end of the file hasn't been reached.
				if (line == null) break;
				
				// The event being created.
				Event event = null;
				
				// Values for the events.  These will be assigned for each event as the text file is parsed.
				String eventDescription = "";
				ArrayList<String> optionText = new ArrayList<String>();
				ArrayList<String> successText = new ArrayList<String>();
				ArrayList<String> failureText = new ArrayList<String>();
				
				// Read until the end of the event is specified, creating the event as you go.
				while (line != null && !line.contains("END EVENT")) {
					
					// Use headers to determine what data is being read.
					if (line.contains("DESCRIPTION")) {
						
						while( (line = rdr.readLine()).length() > 0) {
							lineNumber++;
							eventDescription += (line + "\n");
						}
					
						lineNumber++;
						
					}
					
					else if (line.contains("OPTION TEXT")) {
						
						// Keeps track of which option is having text added to it.
						int option = 0;
						// The string being built for the current option.
						String text = "";
						
						while( (line = rdr.readLine()).length() > 0) {
							lineNumber++;
							
							// This signals the start of text for the next option.
							if (line.contains("NEXT")) {
								optionText.add(text);
								option++;
								text = "";
							}
							else text += (line + "\n");
							
							
						}
						
						optionText.add(text);
						
						lineNumber++;
					
					}
					
					else if (line.contains("SUCCESS TEXT")) {
						
						// Keeps track of which option is having text added to it.
						int option = 0;
						// The string being built for the current option.
						String text = "";
						
						while( (line = rdr.readLine()).length() > 0) {
							lineNumber++;
							
							// This signals the start of text for the next option.
							if (line.contains("NEXT")) {
								successText.add(text);
								option++;
								text = "";
							}
							else text += (line + "\n");
							
							
						}
						
						successText.add(text);
						
						lineNumber++;
						
					}
					
					else if (line.contains("FAILURE TEXT")) {
						
						// Keeps track of which option is having text added to it.
						int option = 0;
						// The string being built for the current option.
						String text = "";
						
						while( (line = rdr.readLine()).length() > 0) {
							lineNumber++;
							
							// This signals the start of text for the next option.
							if (line.contains("NEXT")) {
								failureText.add(text);
								option++;
								text = "";
							}
							else text += (line + "\n");
							
							
						}
						
						failureText.add(text);
						
						lineNumber++;
						
					}
					
					else if (line.contains("SUCCESS RESULT")) {
						
						// Keeps track of which option is having the result added to it.
						int option = 0;
						
						while( (line = rdr.readLine()).length() > 0) {
							lineNumber ++;
							
							// Start adding to the next option.
							if (line.contains("NEXT")) {
								option++;
							}
							// Read in a result.
							else {
								String[] result = line.split(" ");
								event.addSuccess(option, Resource.valueOf(result[0]), Integer.parseInt(result[1]));
							}
							
							
						}
					
						lineNumber++;
						
					}
					
					else if (line.contains("FAILURE RESULT")) {
						
						// Keeps track of which option is having the result added to it.
						int option = 0;
						
						while( (line = rdr.readLine()).length() > 0) {
							lineNumber ++;
							
							// Start adding to the next option.
							if (line.contains("NEXT")) {
								option++;
							}
							// Read in a result.
							else {
								String[] result = line.split(" ");
								event.addFailure(option, Resource.valueOf(result[0]), Integer.parseInt(result[1]));
							}
							
						}
					
						lineNumber++;
						
					}
					
					else if (line.contains("CONDITIONAL")) {
						
						// Keeps track of which option is having the conditional added to it.
						int option = 0;
						
						while( (line = rdr.readLine()).length() > 0) {
							lineNumber ++;
							
							// Start adding to the next option.
							if (line.contains("NEXT")) {
								option++;
							}
							// Read in a result.
							else {
								String[] result = line.split(" ");
								event.addConditional(option, Resource.valueOf(result[0]), Integer.parseInt(result[1]));
							}
							
						}
					
						lineNumber++;
						
					}
					
					else if (line.contains("CREATE")) {
						event = new Event(eventDescription, (String[])optionText.toArray(new String[optionText.size()]));
						rdr.readLine();
						lineNumber++;
					}
					
					else if (line.contains("INIT")) {
						event.initializeResultsAndConditionals(s, (String[])successText.toArray(new String[optionText.size()]),
							   (String[])failureText.toArray(new String[optionText.size()]));
					    rdr.readLine();
					    lineNumber++;
					}
					   
					else System.out.printf("Error.  Cannot read line %d.\n", lineNumber);
					
					line = rdr.readLine();
					lineNumber++;
					
				}
				
				// Now, read the next line to determine which event has been created.
				line = rdr.readLine();
				lineNumber++;
				
				// Put the event where it belongs.
				if (line.contains("RANDOM EVENT")) randomEvents.add(event);
				else if (line.contains("DISASTER")) disaster.add(event);
				else if (line.contains("CONSTRUCTION")) construction = event;
				else if (line.contains("SAND CASTLE BUILDER")) sandCastleBuilder = event;
				else System.out.printf("Unkown header on line %d.\n", lineNumber);
				
			}
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	/**
	 * Get a random event scaled based on the sand castle's level.
	 * @param sandCastleLevel specifies the sand castle's level to scale based on.
	 * @return the scaled random event.
	 */
	public Event retrieveRandomEvent(int sandCastleLevel) {
		
		// Get a random number generator.
		Random RNG = new Random();
		
		// Return a random event scaled based on the sand castle's level.
		return randomEvents.get(RNG.nextInt(randomEvents.size())).Clone((double)(1 + sandCastleLevel*.375));
		
	}
	
	
	public Event retrieveDisaster(Stockpile s, int turnNumber) {
		
		// Get a random number generator and a random number.
		Random RNG = new Random();
		int disasterChoice = RNG.nextInt(3);
		
		// Based on the random number, set up a disaster!
		
		// High tide!
		if (disasterChoice == 0) return prepareHighTide(s,turnNumber);
		
		// Crab attack!
		else if (disasterChoice == 1) return prepareCrabAttack(s,turnNumber);
		
		// Careless Beach-goers!
		else return prepareCarelessBeachGoers(s,turnNumber);
				
	}
	
	/**
	 * Get the event for building up defenses.
	 * @param s specifies the stockpile to be accessed.
	 * @return the prepared construction event.
	 */
	public Event retrieveConstructionEvent(Stockpile s) {
		
		// Determine the limiting wall, moat, and palisades amount.
		int wallLimiter = s.getLimitingAmount(new Resource[]{Resource.PEBBLES,Resource.SAND,Resource.SEASHELLS,Resource.WALLS});
		int palisadeLimiter = s.getLimitingAmount(new Resource[]{Resource.DRIFTWOODCHIPS,Resource.SEAWEED,Resource.PALISADES});
		int moatLimiter = s.getLimitingAmount(new Resource[]{Resource.MOAT});
		
		// Set up the events to reduce resources and build up defense based on the determined limiting value.
		
		construction.addSuccess(0, Resource.MOAT, moatLimiter);
		construction.addSuccess(0, Resource.HAPPINESS, -10);
		
		construction.addSuccess(1, Resource.WALLS, wallLimiter);
		construction.addSuccess(1, Resource.SAND, -wallLimiter);
		construction.addSuccess(1, Resource.PEBBLES, -wallLimiter);
		construction.addSuccess(1, Resource.SEASHELLS, -wallLimiter);
		
		construction.addSuccess(2, Resource.PALISADES, palisadeLimiter);
		construction.addSuccess(2, Resource.DRIFTWOODCHIPS, -palisadeLimiter);
		construction.addSuccess(2, Resource.SEAWEED, -palisadeLimiter);
		
		// Return the prepared construction event.
		return construction;
		
	}
	
	public Event retrieveSandCastleBuilderEvent(int sandCastleLevel) {
		
		// Scale the event's values based on the current sand castle level.
		Event clonedSandCastleBuilder = sandCastleBuilder.Clone(sandCastleLevel+1);
		
		// Make sure to reset the level gain.
		clonedSandCastleBuilder.addSuccess(0, Resource.LEVEL, 1);
		
		// Return the prepared event.
		return clonedSandCastleBuilder;
		
	}
	
	/**
	 * Prepares a high tide event adjusted for current values.
	 * @param s specifies the stockpile to be accessed.
	 * @param turnNumber specifies the turn number which will affect the severity of the disaster.
	 * @return the prepared event.
	 */
	private Event prepareHighTide(Stockpile s, int turnNumber) {
		
		// Calculate the incoming flow of the tide.
		int disasterPower = (int)(70*(1+turnNumber*0.01));
		
		// Calculate the overflow from the moat with people's assistance.
		// A negative value indicates no overflow.
		int moatOverflow = disasterPower - s.getResourceValue(Resource.MOAT) - s.getPeopleEffectiveness();
		
		// Calculate the potential overflow from the walls into the city. (Oh no!)
		// A negative value indicates no overflow.
		int wallOverflow = moatOverflow * 2 - s.getResourceValue(Resource.WALLS);
		
		// Set up the two options, sending people out to bail out the moat(0) or having them stay inside the walls(1).
		//<3
		
		// If the moat doesn't overflow, only a few people die, and they are only minorly unhappy.
		disaster.get(0).addSuccess(0, Resource.MOAT, -(disasterPower-s.getPeopleEffectiveness()));
		disaster.get(0).addSuccess(0, Resource.POPULATION, -s.getTenPercentPopulation());
		disaster.get(0).addSuccess(0, Resource.HAPPINESS, -15);

		// If the moat overflows, more people die, the rest are unhappy, and the walls and possibly population take further damage.
		disaster.get(0).addFailure(0, Resource.MOAT, -1000);
		disaster.get(0).addFailure(0, Resource.HAPPINESS, -30);
		disaster.get(0).addFailure(0, Resource.WALLS,-(moatOverflow));
		if (wallOverflow > 0)
			disaster.get(0).addFailure(0, Resource.POPULATION, 
					-3*s.getTenPercentPopulation()-wallOverflow);
		else disaster.get(0).addFailure(0, Resource.POPULATION, -3*s.getTenPercentPopulation());
		
		// The choice fails if the people cannot keep the water from overflowing the moat.
		disaster.get(0).addConditional(0, Resource.MOAT, disasterPower-s.getPeopleEffectiveness());
		
		
		// Recalculate overflow values without people's assistance.
		moatOverflow += s.getPeopleEffectiveness();
		wallOverflow = moatOverflow * 2 - s.getResourceValue(Resource.WALLS);
		
		
		// If the moat is strong enough to hold, only it takes damage.
		disaster.get(0).addSuccess(1, Resource.MOAT, -disasterPower);
		
		// If the moat fails, the walls and potentially the people take damage.
		disaster.get(0).addFailure(1, Resource.MOAT, -1000);
		disaster.get(0).addFailure(1, Resource.WALLS, -moatOverflow);
		if (wallOverflow>0) {
			disaster.get(0).addFailure(1, Resource.POPULATION, -wallOverflow);
			disaster.get(0).addFailure(1, Resource.HAPPINESS, -20);
		}
		else {
			disaster.get(0).addFailure(1, Resource.POPULATION, 0);
			disaster.get(0).addFailure(1, Resource.HAPPINESS, 0);
		}
		
		// The choice fails if the player's moat is not dry enough to prevent the water from reaching the walls.
		disaster.get(0).addConditional(1, Resource.MOAT, disasterPower);
		
		// Return the prepared event.
		return disaster.get(0);
		
	}
	
	/**
	 * Prepares a crab attack event adjusted for current values.
	 * @param s specifies the stockpile to be accessed.
	 * @param turnNumber specifies the turn number which will affect the severity of the disaster.
	 * @return the prepared event.
	 */
	private Event prepareCrabAttack(Stockpile s, int turnNumber) {
		
		// Calculate the incoming strength of the angry crabs!
		int disasterPower = (int)(60*(1+turnNumber*0.01));
		
		// Calculate the potential overflow of the crabs attack if it breaches the walls even with the people's assistance.
		// A negative value indicates no overflow.
		int crabOverflow = disasterPower-s.getResourceValue(Resource.WALLS)-s.getPeopleEffectiveness();
		
		// Set up the two options: Having people go out to reinforce the wall, or keeping them inside the walls.
		
		// If the people are successful in reinforcing the wall, damage is mitigated at the cost of population and happiness.
		disaster.get(1).addSuccess(0, Resource.POPULATION, -s.getTenPercentPopulation());
		disaster.get(1).addSuccess(0, Resource.HAPPINESS, -15);
		disaster.get(1).addSuccess(0, Resource.WALLS, -(disasterPower-s.getPeopleEffectiveness()));
		
		// If the people fail, lots of people die, the walls are destroyed, and happiness drops drastically.
		disaster.get(1).addFailure(0, Resource.POPULATION, -3*s.getTenPercentPopulation()-crabOverflow);
		disaster.get(1).addFailure(0, Resource.HAPPINESS, -30);
		disaster.get(1).addFailure(0, Resource.WALLS, -1000);
		
		// Success is determined by the ability of the people to keep the crabs from breaching the walls.
		disaster.get(1).addConditional(0, Resource.WALLS, disasterPower-s.getPeopleEffectiveness());
		
		
		// Adjust overflow to be accurate without the people's assistance.
		crabOverflow += s.getPeopleEffectiveness();
		
		
		// If the walls hold against the attack, they are all that is damaged.
		disaster.get(1).addSuccess(1, Resource.WALLS, -disasterPower);
		
		// If the walls fail, people die and happiness is lost.
		disaster.get(1).addFailure(1, Resource.WALLS, -1000);
		disaster.get(1).addFailure(1, Resource.POPULATION, -crabOverflow);
		disaster.get(1).addFailure(1, Resource.HAPPINESS, -20);
		
		// Success is determined by the ability of the walls to hold up against the attack.
		disaster.get(1).addConditional(1, Resource.WALLS, disasterPower);
		
		// Return the prepared event.
		return disaster.get(1);
		
	}
	
	/**
	 * Prepares a careless beach-goer event adjusted for current values.
	 * @param s specifies the stockpile to be accessed.
	 * @param turnNumber specifies the turn number which will affect the severity of the disaster.
	 * @return the prepared event.
	 */
	private Event prepareCarelessBeachGoers(Stockpile s, int turnNumber) {
		
		// Calculate the incoming strength of the careless beach-goers.
		int disasterPower = (int)(50*(1+turnNumber*0.01));
		
		// Calculate the potential overflow from the beach-goers if the palisades fail even with the people's assistance.
		// A negative value indicates no overflow.
		int carelessOverflow = disasterPower-s.getResourceValue(Resource.PALISADES)-s.getPeopleEffectiveness();
		
		// Set up the two options: Having people try to get the attention of the beach-goers and having them hide in safety.
		
		// If the people are successful in making their presence known, damage is mitigated.
		disaster.get(2).addSuccess(0, Resource.POPULATION, -s.getTenPercentPopulation());
		disaster.get(2).addSuccess(0, Resource.HAPPINESS, -15);
		disaster.get(2).addSuccess(0, Resource.PALISADES, -(disasterPower-s.getPeopleEffectiveness()));
		
		// If the people are unsuccessful, many of them die, and the walls take damage as well.
		disaster.get(2).addFailure(0, Resource.POPULATION, -3*s.getTenPercentPopulation()-carelessOverflow);
		disaster.get(2).addFailure(0, Resource.HAPPINESS, -30);
		disaster.get(2).addFailure(0, Resource.PALISADES, -1000);
		disaster.get(2).addFailure(0, Resource.WALLS, -carelessOverflow);
		
		// Success is determined by whether or not the palisades hold up with the help of the people.
		disaster.get(2).addConditional(0, Resource.PALISADES, disasterPower-s.getPeopleEffectiveness());
		
		
		// Adjust the overflow value to discount the people's help.
		carelessOverflow += s.getPeopleEffectiveness();
		
		
		// If the palisades are sufficient to repel the beach-goers, they are all that takes damage.
		disaster.get(2).addSuccess(1, Resource.PALISADES, -disasterPower);
		
		// If the palisades fail to repel the beach-goers, happiness, population, and walls all take damage.
		disaster.get(2).addFailure(1, Resource.PALISADES, -1000);
		disaster.get(2).addFailure(1, Resource.HAPPINESS, -20);
		disaster.get(2).addFailure(1, Resource.POPULATION, -carelessOverflow);
		disaster.get(2).addFailure(1, Resource.WALLS, -carelessOverflow);
		
		// Success is determined by whether or not the palisades can fully repel the beach-goers.
		disaster.get(2).addConditional(1, Resource.PALISADES, disasterPower);
		
		
		// Return the prepared event.
		return disaster.get(2);
		
	}
	
}