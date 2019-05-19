public class Image {
    public static final String SPACESHIP_IMAGE_PATH = "images/spaceship.jpg";



    public static String getCrewMemberImagePath(CrewMember member) {
        return String.format("images/%s.jpg", member.getType().toLowerCase());
    }

    public static String getPlanetImagePath(Planet planet) {
        return String.format("images/%s.png", planet.getName().toLowerCase());
    }

    public static String getFoodImagePath(Food food) {
        return String.format("images/%s.png", food.getType());
    }

    public static String getMedicalSupplyImagePath(MedicalSupply medicalSupply) {
        return String.format("images/%s.jpg", medicalSupply.getType().toLowerCase());
    }
}