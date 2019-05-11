import java.util.ArrayList;

public class SpaceOutPost {
	private static final MedicalSupply[] ALL_MEDICAL_SUPPLIES = {
		new Bandage(),
		new FirstAidKit(),
		new Plaster()
	};
	
	private ArrayList<MedicalSupply> currentMedicalSupplies;
	
	SpaceOutPost() {
		// Inititate inventory
		ArrayList<MedicalSupply> currentMedicalSupplies = new ArrayList<MedicalSupply>();
	}
	
	public void addMedicalSupply(MedicalSupply medicalSupply) {
		currentMedicalSupplies.add(medicalSupply);
	}
	
	public ArrayList<MedicalSupply> getMedicalSupplies() {
		ArrayList<MedicalSupply> all = new ArrayList<MedicalSupply>();
		for (var i = 0; i < ALL_MEDICAL_SUPPLIES.length; i++) {
			all.add(ALL_MEDICAL_SUPPLIES[i]);
		}
		return all;
	}
}