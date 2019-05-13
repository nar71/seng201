import java.util.ArrayList;

public class SpaceOutPost {
	private ArrayList<MedicalSupply> currentMedicalSupplies = new ArrayList<MedicalSupply>();
	private ArrayList<Food> currentFoods = new ArrayList<Food>();

	private int currentMoney;
	
	SpaceOutPost() {
		currentMoney = 100;
		initMedicalSupplies();
		initFoods();
	}
	
	private void initMedicalSupplies() {
		currentMedicalSupplies.add(new Bandage());
		currentMedicalSupplies.add(new FirstAidKit());
		currentMedicalSupplies.add(new Plaster());
	}
	
	private void initFoods() {
		currentFoods.add(new Steak());
		currentFoods.add(new Salad());
		currentFoods.add(new Apple());
		currentFoods.add(new Noodles());
		currentFoods.add(new Soup());
		currentFoods.add(new Pasta());
	}
	
	public boolean isInventoryEmpty() {
		boolean isEmpty = true;
		for (MedicalSupply m: currentMedicalSupplies) {
			if (m.getCount() > 0) {
				isEmpty = false;
			}
		}
		
		for (Food f: currentFoods) {
			if (f.getCount() > 0) {
				isEmpty = false;
			}
		}
		return isEmpty;
	}

	public boolean canAffordItem(int cost) {
		if (currentMoney >= cost) {
			return true;
		}
		return false;
	}

	public void purchaseFood(Food food) {
		food.incrementItemCount();
		currentMoney -= food.getCost();
	}

	public void removeFood(Food food) {
		food.decrementItemCount();
	}

	public void purchaseMedicalSupply(MedicalSupply medicalSupply) {
		medicalSupply.incrementItemCount();
		currentMoney -= medicalSupply.getCost();
	}

	public void removeMedicalSupply(MedicalSupply medicalSupply) {
		medicalSupply.decrementItemCount();
	}

	public boolean medicalSupplyExists(String type) {
		boolean exists = false;
		for (MedicalSupply m: currentMedicalSupplies) {
			if (type.equals(m.getType())) {
				if (m.getCount() > 0) {
					exists = true;
				}
			}
		}
		return exists;
	}

	public boolean foodExists(String type) {
		boolean exists = false;
		for (Food f: currentFoods) {
			if (type.equals(f.getType())) {
				if (f.getCount() > 0) {
					exists = true;
				}
			}
		}
		return exists;
	}

	public Food getFoodByType(String type) {
		for (Food f: currentFoods) {
			if (type.equals(f.getType())) {
				return f;
			}
		}
		return new Food();
	}

	public MedicalSupply getMedicalSupplyByType(String type) {
		for (MedicalSupply m: currentMedicalSupplies) {
			if (type.equals(m.getType())) {
				return m;
			}
		}
		return new MedicalSupply();
	}

	public void displayFoods() {
		System.out.println("Foods:");
		for (Food f : getFoods()) {
			System.out.println(f + " $" + f.getCost());
		}
	}

	public void displayMedicalSupplies() {
		System.out.println("Medical Supplies:");
		for (MedicalSupply m : getMedicalSupplies()) {
			System.out.println(m + " $" + m.getCost());
		}
	}

	public void displayInventory() {
		displayFoods();
		displayMedicalSupplies();
	}

	public ArrayList<MedicalSupply> getMedicalSupplies() {
		return currentMedicalSupplies;
	}
	
	public ArrayList<Food> getFoods() {
		return currentFoods;
	}
	
	public int getCurrentMoney() {
		return currentMoney;
	}
}