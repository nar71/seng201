import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

public class CrewMemberTest {
    @Test
    final void testCrewMember() {
        CrewMember member = new CrewMember("Name", "Type", "Description", 40, "Specialty");
        
        // Getters and setters work
        assertEquals("Name", member.getName());
        assertEquals("Type", member.getType());
        assertEquals("Specialty", member.getSpecialty());
        assertEquals("Description", member.getDescription());
        assertEquals(40, member.getMaxHealth());
    }

    @Test
    final void testEat() {
        CrewMember member = new CrewMember("Name", "Type", "Description", 100, "Specialty");

        // Decrease current health
        member.setHungerLevel(50);
        Foof steak = new Steak();
        member.applyFood(steak);

        assertEquals(100, member.getHealthLevel());
    
        member.setHungerLevel(50);
        Food salad = new Salad();
        member.applyFood(salad);

        assetEquals(80, member.getHealthLevel());

        member.setHungerLevel(50);
        Food apple = new Apple();
        member.applyFood(apple);

        assetEquals(55, member.getHealthLevel());

        member.setHungerLevel(50);
        Food noodles = new Noodles();
        member.applyFood(noodles);

        assetEquals(70, member.getHealthLevel());

        member.setHungerLevel(50);
        Food soup = new Soup();
        member.applyFood(soup);

        assetEquals(60, member.getHealthLevel());        

        member.setHungerLevel(20);
        Food pasta = new Pasta();
        member.applyFood(pasta);

        assetEquals(100, member.getHealthLevel());
    }

    @Test
    final void testApplyMedical() {
        CrewMember member = new CrewMember("Name", "Type", "Description", 100, "Specialty");

        // Decrease current health
        member.setCurrentHealth(50);

        MedicalSupply firstAidKit = new FirstAidKit();
        member.applyMedicalSupply(firstAidKit);

        assertEquals(100, member.getCurrentHealth());
    }
}