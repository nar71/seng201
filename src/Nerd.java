public class Nerd extends CrewMember {

    /**
     * A string representation of the type of Crew Member.
     */
    public static final String TYPE = "Nerd";

    /**
     * A string representation of the specialty of Crew Member.
     */
    public static final String SPECIALTY = "";

    /**
     * A string representation of the description of Crew Member.
     */
    public static final String DESCRIPTION = "";

    /**
     * An integer representation of the health of Crew Member.
     */
    public static final int HEALTH = 0;

    /**
     * Nerd constructor
     * @param name A string for the name of the crew member
     */
    Nerd(String name) {
        super(name, TYPE, DESCRIPTION, HEALTH, SPECIALTY);
    }
}