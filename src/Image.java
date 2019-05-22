public class Image {
    public static final String SPACESHIP_IMAGE_PATH = "images/spaceship.jpg";

    public static final String SHOP_IMAGE_PATH = "images/store.jpg";

    public static final String EXPLORE_IMAGE_PATH = "images/explore.jpg";

    public static final String NEXT_DAY_IMAGE_PATH = "images/next_day.jpg";

    public static final String NEW_PLANET_IMAGE_PATH = "images/travel.jpg";

    public static String getCrewMemberImagePath(CrewMember member) {
        return String.format("images/%s.jpg", member.getType().toLowerCase());
    }

    public static String getPlanetImagePath(Planet planet) {
        return String.format("images/%s.png", planet.getName().toLowerCase());
    }

    public static String getFoodImagePath(Food food) {
        return String.format("images/%s.png", food.getType().toLowerCase());
    }

    public static String getMedicalSupplyImagePath(MedicalSupply medicalSupply) {
        return String.format("images/%s.jpg", medicalSupply.getType().toLowerCase());
    }
}