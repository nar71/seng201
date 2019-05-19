public class Mechanic extends CrewMember {
    public static final String TYPE = "Mechanic";

    public static final String SPECIALTY = "";

    public static final String DESCRIPTION = "DESCRIPTION MECHANIC";

    public static final int HEALTH = 0;

    public static final String ICON_PATH = "images/mechanic.jpg";

    Mechanic(String name) {
        super(name, TYPE, DESCRIPTION, HEALTH, SPECIALTY, ICON_PATH);
    }
}