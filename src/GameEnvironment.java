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
        if (currentDay < maxDays) {
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

    public String searchPlanetForParts() {
        Random random = new Random();

        String returnStr = "";

        int randomAction = random.nextInt(4);
        if (randomAction == 4) {
            returnStr = "You have found nothing";
        }

        if (randomAction == 0) {
            if (!spaceShip.allPartsFound() && !currentPlanet.transporterPartFound()) {
                spaceShip.addPeice();
                currentPlanet.foundTransporterPart();
                returnStr = "You have found a transporter part";
            }
        }

        if (randomAction == 1) {
            // Food.
            int foodIndex = random.nextInt(spaceOutPost.getFoods().size());
            Food foodFound = spaceOutPost.getFoods().get(foodIndex);
            foodFound.incrementItemCount();
            returnStr = "You have found food: " + foodFound.getType();
        }

        if (randomAction == 2) {
            // Medical supply
            int medicalSupplyIndex = random.nextInt(spaceOutPost.getMedicalSupplies().size());
            MedicalSupply medicalItemFound = spaceOutPost.getMedicalSupplies().get(medicalSupplyIndex);
            medicalItemFound.incrementItemCount();
            returnStr = "You have found a medical supply: " + medicalItemFound.getType();
        }

        if (randomAction == 3) {
            // Increment money randomly
            int randIncrement = random.nextInt(20);
            if (randIncrement == 0) {
                randIncrement = 1;
            }
            spaceOutPost.incrementMoney(randIncrement);
            returnStr = "You have found money: $" + randIncrement;
        }

        return returnStr;
    }
    
    public int determineRandomEvent() {
        Random random = new Random();
        double randomNumber = random.nextDouble();
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

    private String alienPirates() {
        if (!spaceOutPost.isInventoryEmpty()) {
            ArrayList<MedicalSupply> medicalSuppliesOwned = new ArrayList<MedicalSupply>();
            for (MedicalSupply m: spaceOutPost.getMedicalSupplies()) {
                if (m.exists()) {
                    medicalSuppliesOwned.add(m);
                }
            }

            ArrayList<Food> allFoodsOwned = new ArrayList<Food>();
            for (Food f: spaceOutPost.getFoods()) {
                if (f.exists()) {
                    allFoodsOwned.add(f);
                }
            }

            Random random = new Random();
            int medOrFood = random.nextInt(2);
            String ret = "";
            if (medOrFood == 1) {
                // Medical Supply.
                int itemIndex = random.nextInt(medicalSuppliesOwned.size());
                MedicalSupply medToRemove = medicalSuppliesOwned.get(itemIndex);
                spaceOutPost.removeMedicalSupply(medToRemove);
            
                ret = medToRemove.getType();
            } else {
                // OR 0 -> We have a food
                int itemIndex = random.nextInt(allFoodsOwned.size());
                Food foodToRemove = allFoodsOwned.get(itemIndex);
                spaceOutPost.removeFood(foodToRemove);

                ret = foodToRemove.getType();
            }
            return ret;
        } else {
            return "Inventory empty (couldnt pinch anything)";
        }
    }

    private String spacePlague() {
        // Crew members become sick -> we need to implement more than one member becoming sick...
        Random random = new Random();

        ArrayList<CrewMember> allNonInfectedMembers = crew.getAllNonSickMembers();

        String ret = "";
        if (allNonInfectedMembers.size() > 0) {
            // Determine how many members to make sick -> need an algo here..
            int randInt = Math.round((allNonInfectedMembers.size()));
            int count = random.nextInt(randInt);
            if (count == 0) {
                count = 1;
            }

            for (int i = 0; i < count; i++) {
                ArrayList<CrewMember> allNonInfectedMembersNew = crew.getAllNonSickMembers();
                int itemIndex = random.nextInt(allNonInfectedMembersNew.size());
                CrewMember infectedMember = allNonInfectedMembersNew.get(itemIndex);

                crew.makeMemberSick(infectedMember);
                ret += infectedMember.getName();
            }
        }
        return ret;
    }

    private String asteroidBelt() {
        // Decrease damage to the ship..
        int decrement = Math.round(spaceShip.getShieldHealth() / 2);
        spaceShip.decreaseShieldLevel(decrement);
        int shieldLevel = spaceShip.getShieldHealth();
        return "Shield Health: " + shieldLevel;
    }
    
    public void goToNextDay() {
        currentDay++;
    }
    
    public Planet getCurrentPlanet() {
    	return currentPlanet;
    }
    
    public Planet getPlanet() {
        return planet;
    }

    public void changeCurrentPlanet(Planet planet) {
        this.currentPlanet = planet;
    }

    public int getCurrentDay() {
    	return currentDay;
    }
    
    public int getNumDays() {
    	return numDays;
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

    public LocalTime getTime() {
        return time;
    }
}
