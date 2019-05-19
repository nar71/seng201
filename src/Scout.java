public class Scout extends CrewMember {
    public static final String TYPE = "Scout";

    public static final String SPECIALTY = "";

    public static final String DESCRIPTION = "";

    public static final int HEALTH = 0;

    public static final String ICON_PATH = "images/scout.jpg";

    Scout(String name) {
        super(name, TYPE, DESCRIPTION, HEALTH, SPECIALTY, ICON_PATH);
    }
}