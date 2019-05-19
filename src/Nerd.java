public class Nerd extends CrewMember {
    public static final String TYPE = "Nerd";

    public static final String SPECIALTY = "";

    public static final String DESCRIPTION = "";

    public static final int HEALTH = 0;

    public static final String ICON_PATH = "images/nerd.png";

    Nerd(String name) {
        super(name, TYPE, DESCRIPTION, HEALTH, SPECIALTY, ICON_PATH);
    }
}