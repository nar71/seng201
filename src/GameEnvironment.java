import java.util.Random;
import java.time.LocalTime;
import java.util.Collections;
import java.util.ArrayList;
import java.lang.Math;

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
    
    public boolean isValidNumDays(int day) {
    	if (day >= 3 && day <= 10) {
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
    	//double randomNumber = random.nextDouble();
    	double randomNumber = 0.50;
        int retVal;
    	if (randomNumber < 0.33) {
    		// Alien Pirate
    		retVal = 1;
            alienPirates();
    	} else if (randomNumber >= 0.33 && randomNumber < 0.66) {
    		// Space Plague
    		retVal = 2;
    		spacePlague();
    	} else {
    		// Asteroid BELT
    		retVal = 3;
    		asteroidBelt();
    	}
    	return retVal;
    }

    private void alienPirates() {
        if (!spaceOutPost.isInventoryEmpty()) {
            ArrayList<MedicalSupply> allMedicalSupplies = spaceOutPost.getMedicalSupplies();
            ArrayList<MedicalSupply> medicalSuppliesOwned = new ArrayList<MedicalSupply>();
            for (MedicalSupply m: allMedicalSupplies) {
                if (m.exists()) {
                    medicalSuppliesOwned.add(m);
                }
            }

            ArrayList<Food> allFoods = spaceOutPost.getFoods();
            ArrayList<Food> allFoodsOwned = new ArrayList<Food>();
            for (Food f: allFoods) {
                if (f.exists()) {
                    allFoodsOwned.add(f);
                }
            }

            Random random = new Random();
            int medOrFood = random.nextInt(2);
            if (medOrFood == 1) {
                // Medical Supply.
                int itemIndex = random.nextInt(medicalSuppliesOwned.size());
                MedicalSupply medToRemove = medicalSuppliesOwned.get(itemIndex);
                spaceOutPost.removeMedicalSupply(medToRemove);
            } else {
                // OR 0 -> We have a food
                int itemIndex = random.nextInt(allFoodsOwned.size());
                Food foodToRemove = allFoodsOwned.get(itemIndex);
                spaceOutPost.removeFood(foodToRemove);
            }
        }
    }

    private void spacePlague() {
        // Crew members become sick -> we need to implement more than one member becoming sick...
        Random random = new Random();

        ArrayList<CrewMember> allNonInfectedMembers = crew.getAllNonSickMembers();

        // Determine how many members to make sick
        int randInt = Math.round((allNonInfectedMembers.size() / 2));
        int count = random.nextInt(randInt);
        if (count == 0) {
            count = 1;
        }

        for (int i = 0; i < count; i++) {
            ArrayList<CrewMember> allNonInfectedMembersNew = crew.getAllNonSickMembers();
            int itemIndex = random.nextInt(allNonInfectedMembersNew.size());
            CrewMember infectedMember = allNonInfectedMembersNew.get(itemIndex);

            crew.makeMemberSick(infectedMember);
            System.out.println(infectedMember);
        }
    }

    private void asteroidBelt() {
        // Decrease damage to the ship..
        spaceShip.decreaseShieldLevel();
    }
    
    public void doToNextDay() {
        currentDay++;
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
