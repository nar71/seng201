/**
 *
 *
 */
public class Barter extends CrewMember {
    
	/**
     * A string representation of the type of Crew Member.
     */
	private static final String TYPE = "Barter";

    /**
     * A string representation of the specialty of Crew Member.
     */
    private static final String SPECIALTY = "";

    /**
     * A string representation of the description of Crew Member.
     */
    private static final String DESCRIPTION = "DESCRIPTION BARTER";

    /**
     * An integer representation of the health of Crew Member.
     */
    private static final int HEALTH = 50;

    /**
     * Constructor for the instance of Barter
     * @param name The name of the Barter
     */
    Barter(String name) {
        super(name, TYPE, DESCRIPTION, HEALTH, SPECIALTY);
    }
}