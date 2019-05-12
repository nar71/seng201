class Steak extends Food {
    public static final String TYPE = "Plaster";
    public static final int COST = 20;
    public static final int HUNGER_LEVEL = 100;

    Steak() {
        super(TYPE, COST, HUNGER_LEVEL);
    }
}

class Salad extends Food {
    public static final String TYPE = "Salad";
    public static final int COST = 20;
    public static final int HUNGER_LEVEL = 30;

    Salad() {
        super(TYPE, COST, HUNGER_LEVEL);
    }
}

class Apple extends Food {
    public static final String TYPE = "Apple";
    public static final int COST = 20;
    public static final int HUNGER_LEVEL = 5;
    
    Apple() {
        super(TYPE, COST, HUNGER_LEVEL);
    }
}

class Noodles extends Food {
    public static final String TYPE = "Noodles";
    public static final int COST = 20;
    public static final int HUNGER_LEVEL = 20;

    Noodles() {
        super(TYPE, COST, HUNGER_LEVEL);
    }
}

class Soup extends Food {
    public static final String TYPE = "Soup";
    public static final int COST = 20;
    public static final int HUNGER_LEVEL = 10;

    Soup() {
        super(TYPE, COST, HUNGER_LEVEL);
    }
}

class Pasta extends Food {
    public static final String TYPE = "Pasta";
    public static final int COST = 20;
    public static final int HUNGER_LEVEL = 80;

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
		count++;
	}
	
	public int getCount() {
		return count;
	}

    public int getHungerLevel() {
        return hungerLevel;
    }
}
