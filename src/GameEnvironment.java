import java.util.Random;
import java.time.LocalTime;
import java.util.Collections;
import java.util.ArrayList;
import java.lang.Math;

public class GameEnvironment {

    /**
     * The crew which holds all the crew members and their data.
     */
    private Crew crew;

    /**
     * An integer representation of the number of days.
     */
    private int numDays;

    /**
     * The Space Ship. Holds all information regarding the spaceship.
     */
    private SpaceShip spaceShip;
    
    /**
     * The space out post which holds all the medical supplies and foods
     */
    private SpaceOutPost spaceOutPost;

    /**
     * A planet object for the curent planet.
     */
    private Planet currentPlanet;

    /**
     * The planet object.
     */
    private Planet planet;

    /**
     * An integer for the current day
     */
    private int currentDay;
    
    /**
     * An integer for the number of days specified by the player.
     */
    private int maxDays;

    /**
     * An integer of all the gold found, to help produce a score.
     */
    private int goldFound;
	
    /**
     * GameEnvironment constructor
     * @param teamName
     * @param numMembers
     * @param numDays
     * @param shipName
     */
    GameEnvironment(String teamName, int numMembers, int numDays, String shipName) {
    	this.crew = new Crew(teamName, numMembers);
    	this.spaceShip = new SpaceShip(shipName, numDays);
    	this.numDays = numDays;
    	this.spaceOutPost = new SpaceOutPost();
    	this.currentDay = 1;
        this.maxDays = numDays;
    	findStartingPlanet();
        this.planet = new Planet();
    }
    
    /**
     * Checks if current day is less than or equal to num days
     * @return boolean
     */
    public boolean isValidCurrentDay() {
        if (currentDay < maxDays) {
            return true;
        }
        return false;
    }
    
    /**
     * Finds the starting planet
     */
    private void findStartingPlanet() {
        // Randomized event to find starting planet
        Random rand = new Random();
        double randNum = rand.nextDouble();
        if (randNum < 0.16) {
            currentPlanet = new Earth();
        } else if (randNum >= 0.16 && randNum < 0.33) {
            currentPlanet = new Mars();
        } else if (randNum >= 0.33 && randNum < 0.49) {
            currentPlanet = new Mercury();
        } else if (randNum >= 0.49 && randNum < 0.66) {
            currentPlanet = new Venus();
        } else if (randNum >= 0.66 && randNum < 0.83) {
            currentPlanet = new Saturn();
        } else {
            currentPlanet = new Jupiter();
        }
    }

    /**
     * Checks if defeated - last day and no crew actions
     */
    public boolean isDefeated() {
        if (hasDaysLeft()) {
            return false;
        }
        
        boolean isDefeated = true;
        for (CrewMember member: crew.getMembers()) {
            if (member.hasActionsLeft()) {
                isDefeated = false;
            }
        }
        return isDefeated;
    }

    /**
     * Searchs planet for space parts
     * @return String the part found
     */
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
            } else {
                returnStr = "Go travel to another planet to find a transporter part";
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
            int randIncrement = random.nextInt(50);
            if (randIncrement == 0) {
                randIncrement = 1;
            }
            goldFound += randIncrement;
            spaceOutPost.incrementMoney(randIncrement);
            returnStr = "You have found money: $" + randIncrement;
        }

        return returnStr;
    }
    
    /**
     * Determines the random event to occur
     * @return randomEvent If 1 its alien pirates, if 2 its space plague and so on
     */
    public int determineRandomEvent() {
        Random random = new Random();
        double randomNumber = random.nextDouble();
        int randomEvent;
        if (randomNumber < 0.33) {
            // Alien Pirate
            randomEvent = 1;
            alienPirates();
        } else if (randomNumber >= 0.33 && randomNumber < 0.66) {
            // Space Plague
            randomEvent = 2;
            spacePlague();
        } else {
            // Asteroid BELT
            retVal = 3;
            asteroidBelt();
        }
        return randomEvent;
    }

    /**
     * Does the alien pirates random event
     * Steals somethhing from your inventory
     * @return type The medical or food item stolen type.
     */
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
            String type = "";
            if (medOrFood == 1) {
                // Medical Supply.
                int itemIndex = random.nextInt(medicalSuppliesOwned.size());
                MedicalSupply medToRemove = medicalSuppliesOwned.get(itemIndex);
                spaceOutPost.removeMedicalSupply(medToRemove);
            
                type = medToRemove.getType();
            } else {
                // OR 0 -> We have a food
                int itemIndex = random.nextInt(allFoodsOwned.size());
                Food foodToRemove = allFoodsOwned.get(itemIndex);
                spaceOutPost.removeFood(foodToRemove);

                type = foodToRemove.getType();
            }
            return type;
        } else {
            return "Inventory empty (couldnt pinch anything)";
        }
    }

    /**
     * Does the space plague random event
     * @return response string From what occured during space plague
     */
    private String spacePlague() {
        Random random = new Random();

        ArrayList<CrewMember> allNonInfectedMembers = crew.getAllNonSickMembers();

        String returnString = "";
        if (allNonInfectedMembers.size() > 0) {
            int randInt = Math.round((allNonInfectedMembers.size()) / 2);
            int count = random.nextInt(randInt);
            if (count == 0) {
                count = 1;
            }

            for (int i = 0; i < count; i++) {
                ArrayList<CrewMember> allNonInfectedMembersNew = crew.getAllNonSickMembers();
                int itemIndex = random.nextInt(allNonInfectedMembersNew.size());
                CrewMember infectedMember = allNonInfectedMembersNew.get(itemIndex);

                if (!infectedMember.isSick()) {
                    infectedMember.makeSick();
                    if (infectedMember.decrementCurrentHealthForSpacePlague()) {
                        crew.removeCrewMember(infectedMember);
                        returnString += "You have lost a crew member\n";
                    }
                }
                returnString += infectedMember.getName() + "\n";
            }
        }
        return returnString;
    }

    /**
     * Asteroid belt
     * Decreases shield health by 50
     * @return String the new shield health
     */
    private String asteroidBelt() {
        // Decrease damage to the ship..
        //int decrement = Math.round(spaceShip.getShieldHealth() / 2);
        spaceShip.decreaseShieldLevel(50);
        int shieldLevel = spaceShip.getShieldHealth();
        return "Shield Health: " + shieldLevel;
    }
    
    /**
     * Goes to next day if possible
     * Decreases members tiredness, hunger and health by decrement
     * Decreases member health more if they are sick
     * Removes member from array list if no health left.
     */
    public void goToNextDay() {
        currentDay++;
        for (CrewMember member: crew.getMembers()) {
            if (member.getTiredness() > member.getDecrement()) {
                member.decrementTiredness(member.getDecrement());
            } else {
                member.setTiredness(0);
            }

            if (member.getHungerLevel() > member.getDecrement()) {
                member.decrementHungerLevel(member.getDecrement());
            } else {
                member.setHungerLevel(0);
            }

            if (member.getCurrentHealth() > member.getDecrement()) {
                member.decrementCurrentHealth(member.getDecrement());
            } else {
                // Dead if we removed the increment..
                crew.removeCrewMember(member);
            }

            member.resetActions();
            if (member.isSick()) {   
                if (member.decrementCurrentHealthForSpacePlague()) {
                    crew.removeCrewMember(member);
                }
            }
        }
    }

    /**
     * Checks if there are days left
     * @return boolean
     */
    public boolean hasDaysLeft() {
        if (currentDay == numDays) {
            return false;
        }
        return true;
    }
    
    /**
     * Gets the current planet
     * @return currentPlanet
     */
    public Planet getCurrentPlanet() {
    	return currentPlanet;
    }
    
    /**
     * Gets planet object
     * @return planet
     */
    public Planet getPlanet() {
        return planet;
    }

    /**
     * Changes current planet
     * @param planet New planet to change to
     */
    public void changeCurrentPlanet(Planet planet) {
        this.currentPlanet = planet;
    }

    /**
     * Gets current day
     * @param currentDay The current day
     */
    public int getCurrentDay() {
    	return currentDay;
    }
    
    /**
     * Gets number of days
     * @param numDays The number of day
     */
    public int getNumDays() {
    	return numDays;
    }
    
    /**
     * Initalises space ship
     * @param spaceShip The space ship object.
     */
    public void setSpaceShip(SpaceShip spaceShip) {
    	this.spaceShip = spaceShip;
    }
    
    /**
     * Gets the space ship
     * @return spaceShip
     */
    public SpaceShip getSpaceShip() {
    	return spaceShip;
    }
    
    /**
     * Gets the space out post
     * @return spaceOutPost
     */
    public SpaceOutPost getSpaceOutPost() {
    	return spaceOutPost;
    }
    
    /**
     * Gets the crew
     * @return crew
     */
    public Crew getCrew() {
    	return this.crew;
    }

    /**
     * Returns gold found
     * @return goldFound
     */
    public int getGoldFound() {
        return goldFound;
    }
}
