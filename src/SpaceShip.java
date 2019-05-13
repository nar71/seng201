import java.lang.Math;

public class SpaceShip {
	private String name;
	private int peicesRequired;
	private int shieldHealth;
	private int numDays;
	private int peicesFound;
	
	SpaceShip(String name, int numDays) {
		this.name = name;
		this.shieldHealth = 0;
		this.peicesFound = 0;
		this.numDays = numDays;
		calculatePeicesRequired();
	}
	
	private void calculatePeicesRequired() {
		int peicesRequired = Math.round((this.numDays * 2) / 3);
		System.out.println(this.numDays);
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
	
	public void decreaseShieldLevel() {
		// Needs to be scaled to current shield lvel
		if (shieldHealth > 0) {
			shieldHealth -= 10;
		}
	}
	
	public int getShieldHealth() {
		return shieldHealth;
	}
	
	public int getPeicesRequired() {
		return peicesRequired;
	}
}
