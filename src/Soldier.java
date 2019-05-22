public class Soldier extends CrewMember {

    /**
     * A string representation of the type of Crew Member.
     */
    public static final String TYPE = "Soldier";

    /**
     * A string representation of the specialty of Crew Member.
     */
    public static final String SPECIALTY = "";

    /**
     * A string representation of the description of Crew Member.
     */
    public static final String DESCRIPTION = "A hardened soldier is ready to face any danger space throws at him";

    /**
     * An integer representation of the health of Crew Member.
     */
    public static final int HEALTH = 100;

    private static final int DECREMENT = 20;

    private static final int SHIELD_INCREMENT = 33;

    /**
     * Soldier constructor
     * @param name A string for the name of the crew member
     */
    Soldier(String name) {
        super(name, TYPE, DESCRIPTION, HEALTH, SPECIALTY, DECREMENT, SHIELD_INCREMENT);
    }
}