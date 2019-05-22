public class Mechanic extends CrewMember {

    /**
     * A string representation of the type of Crew Member.
     */
    public static final String TYPE = "Mechanic";

    /**
     * A string representation of the specialty of Crew Member.
     */
    public static final String SPECIALTY = "";

    /**
     * A string representation of the description of Crew Member.
     */
    public static final String DESCRIPTION = "If its broken he can fix it";

    /**
     * An integer representation of the max health of Crew Member.
     */
    public static final int HEALTH = 100;

    private static final int DECREMENT = 30;

    private static final int SHIELD_INCREMENT = 50;

    /**
     * Mechanic constructor
     * @param name A string for the name of the crew member
     */
    Mechanic(String name) {
        super(name, TYPE, DESCRIPTION, HEALTH, SPECIALTY, DECREMENT, SHIELD_INCREMENT);
    }
}