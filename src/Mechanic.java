public class Mechanic extends CrewMember {
    public static final String TYPE = "Mechanic";

    public static final String SPECIALTY = "";

    public static final String DESCRIPTION = "DESCRIPTION MECHANIC";

    public static final int HEALTH = 0;

    Mechanic(String name) {
        super(name, TYPE, DESCRIPTION, HEALTH, SPECIALTY);
    }
}