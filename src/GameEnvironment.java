import java.util.Random;
import java.time.LocalTime;

public class GameEnvironment {
	private Crew crew;
	private int numDays;
	private SpaceShip spaceShip;
	private SpaceOutPost spaceOutPost;
	private LocalTime time;
	
	private Planet startPlanet;
	
	private int maxGold = 100;
	private int currentDay;
	
    GameEnvironment(String teamName, int numMembers, int numDays, String shipName) {
    	this.crew = new Crew(teamName, numMembers);
    	this.spaceShip = new SpaceShip(shipName, numDays);
    	this.numDays = numDays;
    	this.spaceOutPost = new SpaceOutPost();
    	this.time = LocalTime.now();
    	this.currentDay = numDays;
    	findStartingPlanet();
    }
    
    public boolean isValidDayAmount() {
    	if (currentDay >= 3 && currentDay <= 10) {
    		return true;
    	}
    	return false;
    }
    
    private void findStartingPlanet() {
    	// Randomized event to find starting planet
    	Random rand = new Random();
    	double randNum = rand.nextDouble();
    	if (randNum < 0.25) {
    		startPlanet = new Earth();
    	} else if (randNum >= 0.25 && randNum < 0.50) {
    		startPlanet = new Mars();
    	} else if (randNum >= 0.50 && randNum < 0.75) {
    		startPlanet = new Mercury();
    	} else {
    		startPlanet = new Venus();
    	}
    }
    
    public int determineRandomEvent() {
    	Random random = new Random();
    	double randomNumber = random.nextDouble();
    	int retVal;
    	if (randomNumber < 0.33) {
    		// Alien Pirate
    		retVal = 1;
    		doRandomEvent("Pirate");
    	} else if (randomNumber >= 0.33 && randomNumber < 0.66) {
    		// Space Plague
    		retVal = 2;
    		doRandomEvent("Plague");
    	} else {
    		// Asteroid BELT
    		retVal = 3;
    		doRandomEvent("Asteroid");
    	}
    	return retVal;
    }
    
    public void doRandomEvent(String type) {
    	Random random = new Random();
    	if (type == "Pirate") {
    		// Randomly remove an item from inventory
    		// First check if any items in inventory
    		
    	} else if (type == "Plague") {

    	} else if (type == "Asteroid") {
    		// Decrease damage to the ship..
    		spaceShip.decreaseShieldLevel();
    	}
	}
    
    public void doToNextDay() {
    	if (currentDay > 10) {
    		// Error
    	} else {
    		currentDay++;
    	}
    }
    
    public Planet getStartingPlanet() {
    	return startPlanet;
    }
    
    public int getCurrentDay() {
    	return currentDay;
    }
    
    public void setSpaceShip(SpaceShip spaceShip) {
    	this.spaceShip = spaceShip;
    }
    
    public SpaceShip getSpaceShip() {
    	return spaceShip;
    }
    
    public SpaceOutPost getSpaceOutPost() {
    	return spaceOutPost;
    }
    
    public Crew getCrew() {
    	return this.crew;
    }
}
