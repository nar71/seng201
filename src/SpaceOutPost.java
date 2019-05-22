import java.util.ArrayList;

public class SpaceOutPost {

    /**
     * An array list of current medical supplies the player has.
     */
	private ArrayList<MedicalSupply> currentMedicalSupplies = new ArrayList<MedicalSupply>();
	
    /**
     * An array list of current foods the player has.
     */
	private ArrayList<Food> currentFoods = new ArrayList<Food>();

	/**
	 * An integer representation of current money.
	 */
	private int currentMoney;
	
	/**
	 * SpaceOutPost constructor
	 */
	SpaceOutPost() {
		currentMoney = 100;
		initMedicalSupplies();
		initFoods();
	}
	
	/**
	 * Initiates current medical supplies array list with all medical supply objects
	 */
	private void initMedicalSupplies() {
		currentMedicalSupplies.add(new Bandage());
		currentMedicalSupplies.add(new FirstAidKit());
		currentMedicalSupplies.add(new Plaster());
		currentMedicalSupplies.add(new SpacePlagueCure());
	}
	
	/**
	 * Initiates current foods array list with all food objects
	 */
	private void initFoods() {
		currentFoods.add(new Steak());
		currentFoods.add(new Salad());
		currentFoods.add(new Apple());
		currentFoods.add(new Noodles());
		currentFoods.add(new Soup());
		currentFoods.add(new Pasta());
	}

	/**
	 * Checks if there are no foods present (if counter is zero)
	 * @return boolean True if foods False otherwise
	 */
	public boolean hasNoFoods() {
		boolean isEmpty = true;
		for (Food f: currentFoods) {
			if (f.getCount() > 0) {
				isEmpty = false;
			}
		}
		return isEmpty;
	}

	/**
	 * Checks if there are no medical supplies present (if counter is zero)
	 * @return boolean True if medical supplies False otherwise
	 */
	public boolean hasNoMedicalSupplies() {
		boolean isEmpty = true;
		for (MedicalSupply m: currentMedicalSupplies) {
			if (m.getCount() > 0) {
				isEmpty = false;
			}
		}
		return isEmpty;
	}

	/**
	 * Checks both hasNoMedicalSupplies and hasNoFoods to see if empty inventory
	 * @return boolean True if empty False otherwise
	 */
	public boolean isInventoryEmpty() {
		if (hasNoMedicalSupplies() && hasNoFoods()) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if has enough money to buy an item.
	 * @return boolean True if can afford item False otherwise
	 */
	public boolean canAffordItem(int cost) {
		if (currentMoney >= cost) {
			return true;
		}
		return false;
	}

	/**
	 * Purchases food, increments food count, decrements current money
	 */
	public void purchaseFood(Food food) {
		food.incrementItemCount();
		currentMoney -= food.getCost();
	}

	/**
	 * Decrements foods count.
	 */
	public void removeFood(Food food) {
		food.decrementItemCount();
	}

	/**
	 * Purchases medical supply, medical supply count, decrements current money
	 */
	public void purchaseMedicalSupply(MedicalSupply medicalSupply) {
		medicalSupply.incrementItemCount();
		currentMoney -= medicalSupply.getCost();
	}

	/**
	 * Decrements medical supplies count.
	 */
	public void removeMedicalSupply(MedicalSupply medicalSupply) {
		medicalSupply.decrementItemCount();
	}

	/**
	 * Checks if a medical supply exists
	 * @param type A string of the type of medical supply
	 * @return boolean True if exists False if not
	 */
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

	/**
	 * Checks if a food supply exists
	 * @param type A string of the type of food
	 * @return boolean True if exists False if not
	 */
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

	/** 
	 * Gets a food by a given type
	 * @param type A string of the type of food searched.
	 * @return the food instance found or an empty one of not.
	 */
	public Food getFoodByType(String type) {
		for (Food f: currentFoods) {
			if (type.equals(f.getType())) {
				return f;
			}
		}
		return new Food();
	}

	/** 
	 * Gets a medical supply by a given type
	 * @param type A string of the type of medical supply searched.
	 * @return the medical supply instance found or an empty one of not.
	 */
	public MedicalSupply getMedicalSupplyByType(String type) {
		for (MedicalSupply m: currentMedicalSupplies) {
			if (type.equals(m.getType())) {
				return m;
			}
		}
		return new MedicalSupply();
	}

	/**
	 * Prints all foods
	 */
	public void displayFoods() {
		System.out.println("Foods:");
		for (Food f : getFoods()) {
			System.out.println(f + " $" + f.getCost());
		}
	}

	/**
	 * Prints all medical supplies
	 */
	public void displayMedicalSupplies() {
		System.out.println("Medical Supplies:");
		for (MedicalSupply m : getMedicalSupplies()) {
			System.out.println(m + " $" + m.getCost());
		}
	}

	/**
	 * Prints both foods and medical supplies
	 */
	public void displayInventory() {
		displayFoods();
		displayMedicalSupplies();
	}

	/**
	 * Returns the medical supplies array list
	 * @return currentMedicalSupplies The array list of medical supplies
	 */
	public ArrayList<MedicalSupply> getMedicalSupplies() {
		return currentMedicalSupplies;
	}
	
	/**
	 * Returns the foods array list.
	 * @return currentFoods The array list of foods.
	 */
	public ArrayList<Food> getFoods() {
		return currentFoods;
	}
	
	/**
	 * Returns current money
	 * @return currentMoney An integer of current money
	 */
	public int getCurrentMoney() {
		return currentMoney;
	}

	/**
	 * Increments money by given amount
	 * @param amount Integer to increment by
	 */
	public void incrementMoney(int amount) {
		currentMoney += amount;
	}
}