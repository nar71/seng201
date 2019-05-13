import java.util.*;
public class JavaTest {
    public static void main(String[] args) {
        Random random = new Random();
        int medOrFood = random.nextInt(2);
        System.out.println(medOrFood);
    }
}