public class Image {

    /**
     * A string representation for the space image path.
     */
    public static final String SPACESHIP_IMAGE_PATH = "images/spaceship.jpg";

    /**
     * A string representation for the shop image path.
     */
    public static final String SHOP_IMAGE_PATH = "images/store.jpg";

    /**
     * A string representation for the explore image path.
     */
    public static final String EXPLORE_IMAGE_PATH = "images/explore.jpg";

    /**
     * A string representation for the next day image path.
     */
    public static final String NEXT_DAY_IMAGE_PATH = "images/next_day.jpg";

    /**
     * A string representation for the new planet image path.
     */
    public static final String NEW_PLANET_IMAGE_PATH = "images/travel.jpg";

    /**
     * Finds path based on the name of a crew member.
     * @param member The crew member in which to find the image.
     * @return string The image path.
     */
    public static String getCrewMemberImagePath(CrewMember member) {
        return String.format("images/%s.jpg", member.getType().toLowerCase());
    }

    /**
     * Finds path based on the name of a planet.
     * @param planet The planet in which to find the image.
     * @return string The image path.
     */
    public static String getPlanetImagePath(Planet planet) {
        return String.format("images/%s.png", planet.getName().toLowerCase());
    }

    /**
     * Finds path based on the name of a food.
     * @param food The food in which to find the image.
     * @return string The image path.
     */
    public static String getFoodImagePath(Food food) {
        return String.format("images/%s.png", food.getType().toLowerCase());
    }

    /**
     * Finds path based on the name of a medical supply.
     * @param medicalSupply The medical supply in which to find the image.
     * @return string The image path.
     */
    public static String getMedicalSupplyImagePath(MedicalSupply medicalSupply) {
        return String.format("images/%s.jpg", medicalSupply.getType().toLowerCase());
    }
}