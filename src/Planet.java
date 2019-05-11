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
    
    public String getName() {
    	return name;
    }
    
    public Planet[] getAll() {
    	return ALL_PLANETS;
    }
}