import java.util.ArrayList;

public class SpaceOutPost {
	private static final MedicalSupply[] ALL_MEDICAL_SUPPLIES = {
		new Bandage(),
		new FirstAidKit(),
		new Plaster()
	};
	
	private ArrayList<MedicalSupply> currentMedicalSupplies;
	
	private int currentMoney;
	
	SpaceOutPost() {
		currentMoney = 100;
		initMedicalSupplies();
	}
	
	public ArrayList<MedicalSupply> getMedicalSupplies() {
		ArrayList<MedicalSupply> all = new ArrayList<MedicalSupply>();
		for (var i = 0; i < ALL_MEDICAL_SUPPLIES.length; i++) {
			all.add(ALL_MEDICAL_SUPPLIES[i]);
		}
		return all;
	}
	
	private void initMedicalSupplies() {
		ArrayList<MedicalSupply> currentMedicalSupplies = new ArrayList<MedicalSupply>();
		for (var i = 0; i < ALL_MEDICAL_SUPPLIES.length; i++) {
			currentMedicalSupplies.add(ALL_MEDICAL_SUPPLIES[i]);
		}
	}
	
	public void purchaseMedicalSupply(String type) {
		for (MedicalSupply m: currentMedicalSupplies) {
			if (type.equals(m.getType())) {
				m.incrementItemCount();
				currentMoney -= m.getCost();
			}
		}
	}
	
	public int getCurrentMoney() {
		return currentMoney;
	}
}