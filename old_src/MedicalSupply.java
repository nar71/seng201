class Plaster extends MedicalSupply {
    public static final String TYPE = "Plaster";

    public static final String DESCRIPTION = "";

    public static final int HEALTH = 10;
    
    public static final int COST = 10;

    Plaster() {
        super(TYPE, DESCRIPTION, HEALTH, COST);
    }
}

class Bandage extends MedicalSupply {
    public static final String TYPE = "Bandage";

    public static final String DESCRIPTION = "";

    public static final int HEALTH = 30;
    
    public static final int COST = 15;

    Bandage() {
        super(TYPE, DESCRIPTION, HEALTH, COST);
    }
}

class FirstAidKit extends MedicalSupply {
    public static final String TYPE = "First Aid Kit";

    public static final String DESCRIPTION = "";

    public static final int HEALTH = 30;
    
    public static final int COST = 50;

    FirstAidKit() {
        super(TYPE, DESCRIPTION, HEALTH, COST);
    }
}

public class MedicalSupply {
	private String type;
	private String description;
	private int health;
	private int cost;
	
	private int countOfItems;
	
	MedicalSupply(String type, String description, int health, int cost) {
		this.type = type;
		this.description = description;
		this.cost = cost;
		this.health = health;
        this.countOfItems = 0;
	}

    MedicalSupply() {}
	
	public void incrementItemCount() {
		countOfItems += 1;
	}

    public void decrementItemCount() {
        countOfItems -= 1;
    }
	
	public String toString() {
		String ret = this.type + " (" + countOfItems + ")\n";
		return ret;
	}
	
	public String getType() {
		return type;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getCount() {
		return countOfItems;
	}
	
	public int getCost() {
		return cost;
	}

    public boolean exists() {
        if (countOfItems > 0) {
            return true;
        }
        return false;
    }
}
