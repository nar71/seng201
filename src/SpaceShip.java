import java.lang.Math;

public class SpaceShip {
	private String name;
	private int peicesRequired;
	private int shieldHealth;
	private int numDays;
	private int peicesFound;

	private static final String IMAGE_PATH = "images/spaceship.jpg";
	
	SpaceShip(String name, int numDays) {
		this.name = name;
		this.shieldHealth = 100;
		this.peicesFound = 0;
		this.numDays = numDays;
		calculatePeicesRequired();
	}
	
	private void calculatePeicesRequired() {
		int peicesRequired = Math.round((this.numDays * 2) / 3);
		this.peicesRequired = peicesRequired;
	}
	
	public boolean allPartsFound() {
		if (peicesFound == peicesRequired) {
			return true;
		}
		return false;
	}

	public void addPeice() {
		peicesFound += 1;
	}

	public String toString() {
		String data = "Name: " + this.getName() + "\n";
		data += "Shield Health: " + this.getShieldHealth() + "\n";
		data += "Peices Required: " + this.getPeicesRequired();
		return data;
	}
	
	public String getName() {
		return name;
	}
	
	public void decreaseShieldLevel(int decrement) {
		shieldHealth -= decrement;
		if (shieldHealth < 0) {
			shieldHealth = 0;
		}
	}

	public void incrementShieldLevel(int increment) {
		shieldHealth += increment;
		if (shieldHealth > 100) {
			shieldHealth = 100;
		}
	}

	public void repair(CrewMember member) {
		incrementShieldLevel(member.getShieldIncrement());
	}
	
	public int getShieldHealth() {
		return shieldHealth;
	}
	
	public boolean isFullHealth() {
		if (shieldHealth >= 100) {
			return true;
		}
		return false;
	}

	public boolean canTravel() {
		if (shieldHealth <= 0) {
			return false;
		}
		return true;
	}

	public int getPeicesRequired() {
		return peicesRequired;
	}
	
	public int getPeicesFound() {
		return peicesFound;
	}

	public String getImagePath() {
		return IMAGE_PATH;
	}
}
