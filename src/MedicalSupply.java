class Plaster extends MedicalSupply {

    /**
     * A string representation of the type of medical supply.
     */
    public static final String TYPE = "Plaster";

    /**
     * An integer representation of the health the medical supply gives.
     */
    public static final int HEALTH = 10;

    /**
     * An integer representation of the cost of the medical supply.
     */
    public static final int COST = 10;

    /**
     * Plaster constructor.
     */
    Plaster() {
        super(TYPE, HEALTH, COST);
    }
}

class Bandage extends MedicalSupply {

    /**
     * A string representation of the type of medical supply.
     */
    public static final String TYPE = "Bandage";

    /**
     * An integer representation of the health the medical supply gives.
     */
    public static final int HEALTH = 25;
    
    /**
     * An integer representation of the cost of the medical supply.
     */
    public static final int COST = 25;

    /**
     * Bandage constructor.
     */
    Bandage() {
        super(TYPE, HEALTH, COST);
    }
}

class FirstAidKit extends MedicalSupply {

    /**
     * A string representation of the type of medical supply.
     */
    public static final String TYPE = "First Aid Kit";

    /**
     * An integer representation of the health the medical supply gives.
     */
    public static final int HEALTH = 50;

    /**
     * An integer representation of the cost of the medical supply.
     */
    public static final int COST = 50;

    /**
     * FirstAidKit constructor.
     */
    FirstAidKit() {
        super(TYPE, HEALTH, COST);
    }
}

class SpacePlagueCure extends MedicalSupply {

    /**
     * A string representation of the type of medical supply.
     */
    public static final String TYPE = "Space Plague Cure";

    /**
     * An integer representation of the health the medical supply gives.
     */
    public static final int HEALTH = 0;
    
    /**
     * An integer representation of the cost of the medical supply.
     */
    public static final int COST = 50;

    /**
     * SpacePlagueCure constructor.
     */
    SpacePlagueCure() {
        super(TYPE, HEALTH, COST);
        isSpacePlagueCure = true;
    }
}

public class MedicalSupply {
    
    /**
     * An string representation of the type of medical supply.
     */
	private String type;
	
    /**
     * An integer representation of health the medical supply gives.
     */
    private int health;
	
    /**
     * An integer representation of the cost of the medical supply.
     */
    private int cost;

    /**
     * An integer representation of the count of the medical supply.
     */
	private int countOfItems;

    /**
     * A boolean to determine of medical supply cures space plague or not.
     */
    protected boolean isSpacePlagueCure;

    /**
     * MedicalSupply constructor
     */
	MedicalSupply(String type, int health, int cost) {
		this.type = type;
		this.cost = cost;
		this.health = health;
        this.countOfItems = 0;
        this.isSpacePlagueCure = false;
	}

    /**
     * Blank MedicalSupply constructor
     */
    MedicalSupply() {}

    /**
     * Increment count of items
     */
	public void incrementItemCount() {
		countOfItems += 1;
	}

    /**
     * Decrement count of items
     */
    public void decrementItemCount() {
        countOfItems -= 1;
    }

    /**
     * toString()
     * @return string
     */
	public String toString() {
		String ret = this.type + " (" + countOfItems + ")\n";
		return ret;
	}
	
    /**
     * Gets type of medical supply
     * @return type The type of medical supply.
     */
	public String getType() {
		return type;
	}

    /**
     * Gets health of medical supply
     * @return health The health of medical supply.
     */
	public int getHealth() {
		return health;
	}

    /**
     * Gets count of medical supply
     * @return countOfItems The count of medical supply.
     */
	public int getCount() {
		return countOfItems;
	}

    /**
     * Gets cost of medical supply
     * @return cost The cost of medical supply.
     */
	public int getCost() {
		return cost;
	}

    /**
     * Checks if item exists or not (count > 0)
     * @return boolean
     */
    public boolean exists() {
        if (countOfItems > 0) {
            return true;
        }
        return false;
    }

    /**
     * Checks if item is space plague cure or not
     * @return boolean
     */
    public boolean isSpacePlagueCure() {
        return isSpacePlagueCure;
    }
}
