public class Medic extends CrewMember {

    /**
     * A string representation of the type of Crew Member.
     */
    public static final String TYPE = "Medic";

    /**
     * A string representation of the specialty of Crew Member.
     */
    public static final String SPECIALTY = "Having a medic around is always helpful, especially when travelling the solar system";

    /**
     * A string representation of the description of Crew Member.
     */
    public static final String DESCRIPTION = "";

    /**
     * An integer representation of the health of Crew Member.
     */
    public static final int HEALTH = 75;

    private static final int DECREMENT = 25;

    private static final int SHIELD_INCREMENT = 33;

    /**
     * Medic constructor
     * @param name A string for the name of the crew member
     */
    Medic(String name) {
        super(name, TYPE, DESCRIPTION, HEALTH, SPECIALTY, DECREMENT, SHIELD_INCREMENT);
    }
}