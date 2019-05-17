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
    private String iconPath;

    CrewMember(String name, String type, String description, int health, String specialty, String iconPath) {
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
        this.iconPath = iconPath;
    }

    CrewMember() {}

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
    	//if (tiredness < 100) {
    	//	if (tiredness > 90) {
    	//		tiredness = 100;
    	//	} else {
    			tiredness += 10;
    	//	}
    	//}
    }

    public void applyFood(Food food) {
        hungerLevel -= food.getHungerLevel();
    }

    public void applyMedicalSupply(MedicalSupply medicalSupply) {
        currentHealth += medicalSupply.getHealth();
    }

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
    
    public void makeSick() {
    	isSick = true;
    }

    public boolean isSick() {
        return isSick;
    }
    
    public void removeAction() {
        if (actions == 1 || actions == 2) {
            actions = actions - 1;
            if (tiredness > 10) {
                tiredness -= 10;
            } else {
                tiredness = 0;
            }
        } else {
            actions = 0;
        }
    }

    public boolean hasActionsLeft() {
        if (actions == 1 || actions == 2) {
            return true;
        }
        return false;
    }
    
    public int getTiredness() {
    	return tiredness;
    }
    
    public void decrementTiredness(int decrement) {
        tiredness -= decrement;
    }

    public void incrementTiredness(int increment) {
        tiredness += increment;
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

    public String getIconPath() {
        return iconPath;
    }
}