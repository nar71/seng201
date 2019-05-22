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
    private static final String DESCRIPTION = "This barter loves nothing more than talking to shop keepers from any planet";

    /**
     * An integer representation of the health of Crew Member.
     */
    private static final int HEALTH = 50;

    private static final int DECREMENT = 10;

    private static final int SHIELD_INCREMENT = 25;

    /**
     * Constructor for the instance of Barter
     * @param name The name of the Barter
     */
    Barter(String name) {
        super(name, TYPE, DESCRIPTION, HEALTH, SPECIALTY, DECREMENT, SHIELD_INCREMENT);
    }
}