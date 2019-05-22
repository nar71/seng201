import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CrewTest {

	@Test
	void addRemoveMembers() {
		Crew crew = new Crew("", 3);
		
		crew.addCrewMember("Barter", "mybarter");
		CrewMember barter = crew.getMembers().get(0);
		
		assertEquals("mybarter", barter.getName());
		assertTrue(barter instanceof Barter);
		
		crew.addCrewMember("Medic", "mymedic");
		CrewMember medic = crew.getMembers().get(1);
		
		assertEquals("mymedic", medic.getName());
		assertTrue(medic instanceof Medic);
	}
	
	

}
