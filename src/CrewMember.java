import java.util.ArrayList;

public class CrewMember {

    /**
     * A CrewMember array that holds an empty instance of every type Crew Member
     */
    private static final CrewMember[] ALL_TYPES = {
        new Barter(""),
        new Mechanic(""),
        new Nerd(""),
        new Scout(""),
        new Tank("")
    };

    /**
     * A String representation for the name of the crew member
     */
    private String name;

    /**
     * A String representation for the description of the crew member
     */
    private String description;
    
    /**
     * A String representation for the type of the crew member
     */
    public String type;
    
    /**
     * An integer representation for the maximum health of a crew member
     */
    private int maximumHealth;

    /**
     * An integer representation for the current health of a crew member
     */
    private int currentHealth;

    /**
     * A string representation for the specialty of a crew member
     */
    private String specialty;

    /**
     * A boolean that determines if a crew member is sick or not
     */
    private boolean isSick;

    /**
     * An integer representation for the actions of a crew member
     */
    private int actions;

    /**
     * An integer representation for the tiredness of a crew member
     */
    private int tiredness;

    /**
     * An integer representation for the hunger level of a crew member
     */
    private int hungerLevel;

    /**
     * Crew Member constructor
     * @param name A string for the name of the crew member
     * @param type A string that determines the type of the crew member
     * @param description A string for the description of crew member
     * @param health An integer for the maximum health a crew member can have
     * @param specialty A string representation for the specialty of the crew member 
     */
    CrewMember(String name, String type, String description, int health, String specialty) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.maximumHealth = health;
        this.specialty = specialty;
        this.currentHealth = health;
        this.actions = 2;
        this.tiredness = 100;
        this.hungerLevel = 100;
        this.isSick = false;
    }

    /**
     * Blank constructor for Crew Member
     */
    CrewMember() {}

    /**
     * Gets all the types of crew members
     * @return allTypes A string array that contains all the types of crew members
     */
    public String[] getAllTypes() {
        String[] allTypes = new String[ALL_TYPES.length];
        for (int i = 0; i < ALL_TYPES.length; i++) {
            allTypes[i] = ALL_TYPES[i].getType();
        }
        return allTypes;
    }

    /**
     * Determines if a crew member can sleep or not
     * @return boolean true if member can sleep, false if not
     */
    public boolean canSleep() {
    	if (tiredness < 100 && tiredness > 0) {
    		return true;
    	}
    	return false;
    }
    
    /**
     * Makes a crew member sleep, which increases tiredness 
     */
    public void sleep() {
    	if (tiredness < 100) {
    		if (tiredness > 90) {
    			tiredness = 100;
    		} else {
    			tiredness += 10;
    		}
    	}
    }

    /**
     * Applies a food to the crew member, which makes them less tired
     * @param food An instance of Food
     */
    public void applyFood(Food food) {
        hungerLevel += food.getHungerLevel();
        if (hungerLevel > 100) {
            hungerLevel = 100;
        }
    }

    /**
     * Applies a medical supply 
     * @param medicalSupply An instance of MedicalSupply
     */
    public void applyMedicalSupply(MedicalSupply medicalSupply) {
        currentHealth += medicalSupply.getHealth();
        if (currentHealth > maximumHealth) {
            currentHealth = maximumHealth;
        }
    }

    /**
     * Makes crew member sick by setting isSick equal to true
     */
    public void makeSick() {
    	isSick = true;
    }

    /*
     * Cures crew member sick by setting isSick equal to false
     */
    public void cure() {
        isSick = false;
    }

    /**
     * Returns isSick
     * @return isSick A boolean that determines if a crew member is sick or not
     */
    public boolean isSick() {
        return isSick;
    }
    
    /**
     * Removes an action from a crew member
     */
    public void removeAction() {
        if (actions == 1 || actions == 2) {
            actions = actions - 1;
        } else {
            actions = 0;
        }
    }

    /**
     * Checks if a crew member has any actions left
     * @return boolean true if member has actions left, false if not
     */
    public boolean hasActionsLeft() {
        if (actions == 1 || actions == 2) {
            return true;
        }
        return false;
    }

    /**
     * Resets actions by setting it equal to 2
     */
    public void resetActions() {
        actions = 2;
    }
    
    /**
     * Gets tiredness level
     * @return tiredness An integer of the tiredness level
     */
    public int getTiredness() {
    	return tiredness;
    }
    
    /**
     * Decrements tiredness level by specified amount
     * @param decrement An integer to decrement the tiredness level by
     */
    public void decrementTiredness(int decrement) {
        tiredness -= decrement;
    }

    /**
     * Increments tiredness level by specified amount
     * @param increment An integer to increment the tiredness level by
     */
    public void incrementTiredness(int increment) {
        tiredness += increment;
    }

    /**
     * Sets tiredness level by specified amount
     * @param newTiredness An integer to set tiredness to
     */
    public void setTiredness(int newTiredness) {
        tiredness = newTiredness;
    }

    /**
     * Gets actions of a crew member
     * @return actions An integer of the actions left
     */
    public int getActions() {
    	return actions;
    }
    
    /**
     * Gets name of crew member
     * @return name A string representation of the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets type of crew member
     * @return type A string representation of the type
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the description of a crew member
     * @return description A string representation of the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the maximum health level
     * @return maximumHealth An integer of the maximum health of a crew member
     */
    public int getMaxHealth() {
        return maximumHealth;
    }

    /**
     * Gets the current health level of a crew member
     * @return currentHealth An integer of the current health
     */
    public int getCurrentHealth() {
        return currentHealth;
    }

    /**
     * Sets the current health to specified amount
     * @param health An integer to set the current health to
     */
    public void setCurrentHealth(int health) {
        currentHealth = health;
    }

    /** 
     * Decrements the current health level by 10 and checks if members dead (meaning current health < 0)
     * @return boolean
     */
    public boolean decrementCurrentHealthAndCheckIfDead() {
        currentHealth -= 10;
        if (currentHealth < 0) {
            return true;
        }
        return false;
    }

    /**
     * Gets the specialty of the crew member
     * @return specialty A string representation of the specialty
     */
    public String getSpecialty() {
        return specialty;
    }

    /**
     * Gets the hunger level of a crew member
     * @return hungerLevel An integer representation of the current hunger level
     */
    public int getHungerLevel() {
        return hungerLevel;
    }

    /** 
     * toString method which displays all the crew data
     * @return String data All the data of the crew member
     */
    public String toString() {
        String isSickStr = "no";
        if (isSick()) {
            isSickStr = "yes";
        }

        String data = this.getName() + "\n";
        data += "Type: " + this.getType() + "\n";
        data += "Current Health: " + this.getCurrentHealth() + "\n";
        data += "Max Health: " + this.getMaxHealth() + "\n";
        data += "Hunger Level: " + this.getHungerLevel() + "\n";
        data += "Tiredness: " + this.getHungerLevel() + "\n";
        data += "Sick: " + isSickStr + "\n";
        return data;
    }
}