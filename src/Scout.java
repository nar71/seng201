public class Scout extends CrewMember {

    /**
     * A string representation of the type of Crew Member.
     */
    private static final String TYPE = "Scout";

    /**
     * A string representation of the specialty of Crew Member.
     */
    private static final String SPECIALTY = "";

    /**
     * A string representation of the description of Crew Member.
     */
    private static final String DESCRIPTION = "Scouts just cant wait to explore the wilderness of other planets";

    /**
     * An integer representation of the health of Crew Member.
     */
    private static final int HEALTH = 50;

    /**
     * An integer representation of the decrement of Crew Member.
     * How much tiredness, hunger and health degrade over time.
     */
    private static final int DECREMENT = 10;

    /**
     * An integer representation of how much health the crew member can restore to the space ship shield.
     */
    private static final int SHIELD_INCREMENT = 25;

    /**
     * Scout constructor
     * @param name A string for the name of the crew member
     */
    Scout(String name) {
        super(name, TYPE, DESCRIPTION, HEALTH, SPECIALTY, DECREMENT, SHIELD_INCREMENT);
    }
}