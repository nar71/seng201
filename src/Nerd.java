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
    public static final String DESCRIPTION = "Although he would much rather be play Runescape, but he can never turn down a space adventure.";

    /**
     * An integer representation of the health of Crew Member.
     */
    public static final int HEALTH = 60;

    private static final int DECREMENT = 15;

    private static final int SHIELD_INCREMENT = 50;

    /**
     * Nerd constructor
     * @param name A string for the name of the crew member
     */
    Nerd(String name) {
        super(name, TYPE, DESCRIPTION, HEALTH, SPECIALTY, DECREMENT, SHIELD_INCREMENT);
    }
}