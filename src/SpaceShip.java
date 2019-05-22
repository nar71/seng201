import java.lang.Math;

public class SpaceShip {

	/**
	 * A string representation of the name of the space ship.
	 */
	private String name;

	/**
	 * An integer representation for the number of peices required.
	 */
	private int peicesRequired;
	
	/**
	 * An integer representation for shield health.
	 */
	private int shieldHealth;
	
	/**
	 * An integer representation for the number of days to play in the game.
	 */
	private int numDays;
	
	/**
	 * An integer representation for the number of peices found.
	 */
	private int peicesFound;
	
	/**
	 * SpaceShip constructor.
	 * @param name The name for the ship.
	 * @param numDays The number of days to play.)
	 */
	SpaceShip(String name, int numDays) {
		this.name = name;
		this.shieldHealth = 100;
		this.peicesFound = 0;
		this.numDays = numDays;
		calculatePeicesRequired();
	}
	
 	/**
 	 * Calculates peices required based on number of days.
 	 */
	private void calculatePeicesRequired() {
		int peicesRequired = Math.round((this.numDays * 2) / 3);
		this.peicesRequired = peicesRequired;
	}
	
	/**
	 * Checks if all parts are found
	 * @return boolean True if found false otherwise.
	 */
	public boolean allPartsFound() {
		if (peicesFound == peicesRequired) {
			return true;
		}
		return false;
	}

	/**
	 * Adds a peice to the peicesFound property.
	 */
	public void addPeice() {
		peicesFound += 1;
	}

	/**
	 * Checks if the space ship has enough health to travel.
	 * @return boolean true if can travel false if not.
	 */
	public boolean canTravel() {
		if (shieldHealth <= 0) {
			return false;
		}
		return true;
	}

	/**
	 * Repairs the ship given member property
	 * @param member A crew member object.
	 */
	public void repair(CrewMember member) {
		incrementShieldLevel(member.getShieldIncrement());
	}

	/**
	 * Decreases the shield level if < 0 then sets it to 0
	 * @param decrement The amount to decrement by
	 */
	public void decreaseShieldLevel(int decrement) {
		shieldHealth -= decrement;
		if (shieldHealth < 0) {
			shieldHealth = 0;
		}
	}

	/**
	 * Increases the shield level if > 100 then sets it to 100
	 * @param increment The amount to increment by
	 */
	public void incrementShieldLevel(int increment) {
		shieldHealth += increment;
		if (shieldHealth > 100) {
			shieldHealth = 100;
		}
	}

	/**
	 * Increases the shield level if > 100 then sets it to 100
	 * @param increment The amount to increment by
	 */
	public int getShieldHealth() {
		return shieldHealth;
	}

	/**
	 * Checks if shield health is full.
	 * @return boolean is shieldhealth full false otherwise.
	 */
	public boolean isFullHealth() {
		if (shieldHealth >= 100) {
			return true;
		}
		return false;
	}

	/**
	 * Gets peices required.
	 * @return peicesRequired The peices required to fix the ship.
	 */
	public int getPeicesRequired() {
		return peicesRequired;
	}

	/**
	 * Gets peices found.
	 * @return peicesFound The peices found.
	 */
	public int getPeicesFound() {
		return peicesFound;
	}

	/**
	 * Gets the name of the space ship.
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * toString property.
	 * @return string
	 */
	public String toString() {
		String data = "Name: " + this.getName() + "\n";
		data += "Shield Health: " + this.getShieldHealth() + "\n";
		data += "Peices Required: " + this.getPeicesRequired();
		return data;
	}
}
