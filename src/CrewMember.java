import java.util.ArrayList;

public class CrewMember {
    private static final CrewMember[] ALL_TYPES = {
        new Barter(""),
        new Mechanic(""),
        new Nerd(""),
        new Scout(""),
        new Tank("")
    };

    private String name;
    private String description;
    public String type;
    private int maximumHealth;
    private int currentHealth;
    private String specialty;
    private boolean isSick;
    private int actions;
    private int tiredness;
    private int hungerLevel;

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
    }

    public String[] getAllTypes() {
        String[] all = new String[ALL_TYPES.length];
        for (int i = 0; i < ALL_TYPES.length; i++) {
            all[i] = ALL_TYPES[i].getType();
        }
        return all;
    }

    public boolean canSleep() {
    	if (tiredness < 100 && tiredness > 0) {
    		return true;
    	}
    	return false;
    }
    
    public void sleep() {
    	if (tiredness < 100) {
    		if (tiredness > 90) {
    			tiredness = 100;
    		} else {
    			tiredness += 10;
    		}
    	}
    }

    public void applyFood(Food food) {
        hungerLevel -= food.getHungerLevel();
    }

    public void applyMedicalSupply(MedicalSupply medicalSupply) {
        currentHealth += medicalSupply.getHealth();
    }

    public String toString() {
    	String data = this.getName() + "\n";
    	data += "Type: " + this.getType() + "\n";
    	data += "Current Health: " + this.getCurrentHealth() + "\n";
    	data += "Max Health: " + this.getMaxHealth() + "\n";
        data += "Hunger Level: " + this.getHungerLevel() + "\n";
        data += "Tiredness: " + this.getHungerLevel() + "\n";
    	return data;
    }
    
    public void makeSick() {
    	isSick = true;
    }
    
    public void removeAction() {
    	if (actions > 0) {
    		actions -= 1;
    	}
    }
    
    public int getTiredness() {
    	return tiredness;
    }
    
    public int getActions() {
    	return actions;
    }
    
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public int getMaxHealth() {
        return maximumHealth;
    }

    public void setCurrentHealth(int health) {
        currentHealth = health;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public String getSpecialty() {
        return specialty;
    }

    public int getHungerLevel() {
        return hungerLevel;
    }
}