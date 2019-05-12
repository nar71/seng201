public class Planet {
	private static final Planet[] ALL_PLANETS = {
		new Mars(),
		new Venus(),
		new Earth(),
		new Mercury()
	};
	
    private String name;
    
    Planet(String name) {
    	this.name = name;
    }
    
    Planet() {}

    public String getName() {
    	return name;
    }
    
    public Planet[] getAll() {
    	return ALL_PLANETS;
    }

    public Planet getByName(String name) {
        for (int i = 0; i < ALL_PLANETS.length; i++) {
            if (name.equals(ALL_PLANETS[i].getName())) {
                return ALL_PLANETS[i];
            }
        }
        return new Planet();
    }

    public String toString() {
        return "Planet: " + this.getName();
    }
}