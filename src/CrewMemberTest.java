import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

public class CrewMemberTest {
    @Test
    final void testCrewMember() {
        CrewMember member = new CrewMember("Name", "Type", "Description", 40, "Specialty", 0, 0);
        
        // Getters and setters work
        assertEquals("Name", member.getName());
        assertEquals("Type", member.getType());
        assertEquals("Specialty", member.getSpecialty());
        assertEquals("Description", member.getDescription());
        assertEquals(40, member.getMaxHealth());
    }

    @Test
    final void testEat() {
        CrewMember member = new CrewMember("Name", "Type", "Description", 100, "Specialty", 0, 0);

        // Decrease current health
        member.setHungerLevel(50);
        Food steak = new Steak();
        member.applyFood(steak);

        assertEquals(100, member.getHungerLevel());
    
        member.setHungerLevel(50);
        Food salad = new Salad();
        member.applyFood(salad);

        assertEquals(100, member.getHungerLevel());

        member.setHungerLevel(50);
        Food apple = new Apple();
        member.applyFood(apple);

        assertEquals(55, member.getHungerLevel());

        member.setHungerLevel(50);
        Food noodles = new Noodles();
        member.applyFood(noodles);

        assertEquals(70, member.getHungerLevel());

        member.setHungerLevel(50);
        Food soup = new Soup();
        member.applyFood(soup);

        assertEquals(60, member.getHungerLevel());        
        
        member.applyFood(noodles);
        assertEquals(80, member.getHungerLevel());
        
        member.setHungerLevel(20);
        Food pasta = new Pasta();
        member.applyFood(pasta);
        
        // Proves also that applyFood() wont increase hunger level over 100
        assertEquals(100, member.getHungerLevel());
        
        // Check applied foods.
        ArrayList<Food> expectedFoods = new ArrayList<Food>();
        expectedFoods.add(new Steak());
        expectedFoods.add(new Salad());
        expectedFoods.add(new Apple());
        expectedFoods.add(new Noodles());
        expectedFoods.add(new Soup());
        expectedFoods.add(new Pasta());
		ArrayList<Food> givenFoods = member.getAppliedFoods();
		
		assertTrue(givenFoods.equals(expectedFoods));
    }

    @Test
    final void testApplyMedical() {
        CrewMember member = new CrewMember("Name", "Type", "Description", 100, "Specialty", 0, 0);

        // Decrease current health
        member.setCurrentHealth(50);

        MedicalSupply firstAidKit = new FirstAidKit();
        member.applyMedicalSupply(firstAidKit);

        assertEquals(100, member.getCurrentHealth());
    }
    
    @Test
    final void testSleep() {
    	CrewMember member = new CrewMember("Name", "Type", "Description", 100, "Specialty", 0, 0);
    	
    	member.setTiredness(50);
    	member.sleep();
    	
    	assertEquals(100, member.getTiredness());
    }
}