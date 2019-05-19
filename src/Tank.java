public class Tank extends CrewMember {
    public static final String TYPE = "Tank";

    public static final String SPECIALTY = "";

    public static final String DESCRIPTION = "";

    public static final int HEALTH = 0;

    public static final String ICON_PATH = "images/tank.png";

    Tank(String name) {
        super(name, TYPE, DESCRIPTION, HEALTH, SPECIALTY, ICON_PATH);
    }
}