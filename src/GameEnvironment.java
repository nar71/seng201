import java.util.Random;
import java.time.LocalTime;

public class GameEnvironment {
	private Crew crew;
	private int numDays;
	private SpaceShip spaceShip;
	private SpaceOutPost spaceOutPost;
	private LocalTime time;
	
	private Planet currentPlanet;
    private Planet planet;
	
	private int maxGold = 100;
	private int currentDay;
    private int maxDays;
	
    GameEnvironment(String teamName, int numMembers, int numDays, String shipName) {
    	this.crew = new Crew(teamName, numMembers);
    	this.spaceShip = new SpaceShip(shipName, numDays);
    	this.numDays = numDays;
    	this.spaceOutPost = new SpaceOutPost();
    	this.time = LocalTime.now();
    	this.currentDay = 1;
        this.maxDays = numDays;
    	findStartingPlanet();
        this.planet = new Planet();
    }
    
    public boolean isValidDayAmount() {
    	if (maxDays >= 3 && maxDays <= 10) {
    		return true;
    	}
    	return false;
    }

    public boolean isValidCurrentDay() {
        if (currentDay <= maxDays) {
            return true;
        }
        return false;
    }
    
    private void findStartingPlanet() {
    	// Randomized event to find starting planet
    	Random rand = new Random();
    	double randNum = rand.nextDouble();
    	if (randNum < 0.25) {
    		currentPlanet = new Earth();
    	} else if (randNum >= 0.25 && randNum < 0.50) {
    		currentPlanet = new Mars();
    	} else if (randNum >= 0.50 && randNum < 0.75) {
    		currentPlanet = new Mercury();
    	} else {
    		currentPlanet = new Venus();
    	}
    }

    public void changeCurrentPlanet(Planet planet) {
        this.currentPlanet = planet;
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
        if (currentDay < maxDays) {
            currentDay++;
        }
    }
    
    public Planet getCurrentPlanet() {
    	return currentPlanet;
    }
    
    public Planet getPlanet() {
        return planet;
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
