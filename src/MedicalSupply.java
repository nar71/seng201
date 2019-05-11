class Plaster extends MedicalSupply {
    public static final String TYPE = "Plaster";

    public static final String DESCRIPTION = "";

    public static final int HEALTH = 10;

    Plaster() {
        super(TYPE, DESCRIPTION, HEALTH);
    }
}

class Bandage extends MedicalSupply {
    public static final String TYPE = "Bandage";

    public static final String DESCRIPTION = "";

    public static final int HEALTH = 30;

    Bandage() {
        super(TYPE, DESCRIPTION, HEALTH);
    }
}

class FirstAidKit extends MedicalSupply {
    public static final String TYPE = "First Aid Kit";

    public static final String DESCRIPTION = "";

    public static final int HEALTH = 30;

    FirstAidKit() {
        super(TYPE, DESCRIPTION, HEALTH);
    }
}

public class MedicalSupply {
	private String type;
	private String description;
	private int health;
	
	private int countOfItems;
	
	MedicalSupply(String type, String description, int health) {
		this.type = type;
		this.description = description;
		this.health = health;
	}
	
	public void incrementItemCount() {
		countOfItems += 1;
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
}
