class Steak extends Food {

    /**
     * A string representation of the type of food.
     */
    public static final String TYPE = "Steak";

    /**
     * An integer representation of the cost of the food.
     */
    public static final int COST = 50;

    /**
     * An integer representation of the hunger level the food gives.
     */
    public static final int HUNGER_LEVEL = 100;

    /**
     * Steak Constructor
     */
    Steak() {
        super(TYPE, COST, HUNGER_LEVEL);
    }
}

class Salad extends Food {

    /**
     * A string representation of the type of food.
     */
    public static final String TYPE = "Salad";

    /**
     * An integer representation of the cost of the food.
     */
    public static final int COST = 30;

    /**
     * An integer representation of the hunger level the food gives.
     */
    public static final int HUNGER_LEVEL = 50;

    /**
     * Salad Constructor
     */
    Salad() {
        super(TYPE, COST, HUNGER_LEVEL);
    }
}

class Apple extends Food {

    /**
     * A string representation of the type of food.
     */
    public static final String TYPE = "Apple";

    /**
     * An integer representation of the cost of the food.
     */
    public static final int COST = 10;

    /**
     * An integer representation of the hunger level the food gives.
     */
    public static final int HUNGER_LEVEL = 5;

    /**
     * Apple Constructor
     */
    Apple() {
        super(TYPE, COST, HUNGER_LEVEL);
    }
}

class Noodles extends Food {

    /**
     * A string representation of the type of food.
     */
    public static final String TYPE = "Noodles";

    /**
     * An integer representation of the cost of the food.
     */
    public static final int COST = 10;

    /**
     * An integer representation of the hunger level the food gives.
     */
    public static final int HUNGER_LEVEL = 20;

    /**
     * Noodles Constructor
     */
    Noodles() {
        super(TYPE, COST, HUNGER_LEVEL);
    }
}

class Soup extends Food {

    /**
     * A string representation of the type of food.
     */
    public static final String TYPE = "Soup";

    /**
     * An integer representation of the cost of the food.
     */
    public static final int COST = 20;

    /**
     * An integer representation of the hunger level the food gives.
     */
    public static final int HUNGER_LEVEL = 10;

    /**
     * Soup Constructor
     */
    Soup() {
        super(TYPE, COST, HUNGER_LEVEL);
    }
}

class Pasta extends Food {

    /**
     * A string representation of the type of food.
     */
    public static final String TYPE = "Pasta";

    /**
     * An integer representation of the cost of the food.
     */
    public static final int COST = 40;

    /**
     * An integer representation of the hunger level the food gives.
     */
    public static final int HUNGER_LEVEL = 80;

    /**
     * Pasta Constructor
     */
    Pasta() {
        super(TYPE, COST, HUNGER_LEVEL);
    }
}


public class Food {
	private String type;
	private int cost;
    private int hungerLevel;
	
	private int count;
	
	Food(String type, int cost, int hunger) {
		this.type = type;
		this.cost = cost;
        this.count = 0;
        this.hungerLevel = hunger;
	}

    Food() {}

	public String toString() {
		String ret = this.type + " (" + count + ")\n";
		return ret;
	}
	
	public String getType() {
		return type;
	}
	
	public int getCost() {
		return cost;
	}
	
	public void incrementItemCount() {
		count += 1;
	}

    public void decrementItemCount() {
        count -= 1;
    }
	
	public int getCount() {
		return count;
	}

    public int getHungerLevel() {
        return hungerLevel;
    }

    public boolean exists() {
        if (count > 0) {
            return true;
        }
        return false;
    }
}
