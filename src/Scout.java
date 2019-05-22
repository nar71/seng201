public class Scout extends CrewMember {

    /**
     * A string representation of the type of Crew Member.
     */
    public static final String TYPE = "Scout";

    /**
     * A string representation of the specialty of Crew Member.
     */
    public static final String SPECIALTY = "";

    /**
     * A string representation of the description of Crew Member.
     */
    public static final String DESCRIPTION = "Scouts just cant wait to explore the wilderness of other planets";

    /**
     * An integer representation of the health of Crew Member.
     */
    public static final int HEALTH = 50;

    private static final int DECREMENT = 10;

    private static final int SHIELD_INCREMENT = 25;

    /**
     * Scout constructor
     * @param name A string for the name of the crew member
     */
    Scout(String name) {
        super(name, TYPE, DESCRIPTION, HEALTH, SPECIALTY, DECREMENT, SHIELD_INCREMENT);
    }
}