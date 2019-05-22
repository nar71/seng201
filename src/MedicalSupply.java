class Plaster extends MedicalSupply {
    public static final String TYPE = "Plaster";

    public static final int HEALTH = 10;
    
    public static final int COST = 10;

    Plaster() {
        super(TYPE, HEALTH, COST);
    }
}

class Bandage extends MedicalSupply {
    public static final String TYPE = "Bandage";

    public static final int HEALTH = 25;
    
    public static final int COST = 25;

    Bandage() {
        super(TYPE, HEALTH, COST);
    }
}

class FirstAidKit extends MedicalSupply {
    public static final String TYPE = "First Aid Kit";

    public static final int HEALTH = 50;
    
    public static final int COST = 50;

    FirstAidKit() {
        super(TYPE, HEALTH, COST);
    }
}

class SpacePlagueCure extends MedicalSupply {
    public static final String TYPE = "Space Plague Cure";

    public static final int HEALTH = 0;
    
    public static final int COST = 50;

    SpacePlagueCure() {
        super(TYPE, HEALTH, COST);
        isSpacePlagueCure = true;
    }
}

public class MedicalSupply {
	private String type;
	private int health;
	private int cost;
	
	private int countOfItems;
    protected boolean isSpacePlagueCure;

	MedicalSupply(String type, int health, int cost) {
		this.type = type;
		this.cost = cost;
		this.health = health;
        this.countOfItems = 0;
        this.isSpacePlagueCure = false;
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

    public boolean isSpacePlagueCure() {
        return isSpacePlagueCure;
    }
}
