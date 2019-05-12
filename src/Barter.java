public class Barter extends CrewMember {
    public static final String TYPE = "Barter";

    public static final String SPECIALTY = "";

    public static final String DESCRIPTION = "DESCRIPTION BARTER";

    public static final int HEALTH = 50;

    Barter(String name) {
        super(name, TYPE, DESCRIPTION, HEALTH, SPECIALTY);
    }
}