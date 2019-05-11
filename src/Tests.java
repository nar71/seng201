import java.util.*;

public class Tests {
    public static void main(String[] args) {
    	Scanner s1 = new Scanner(System.in);
        System.out.println("What would you like to name you're team?");
        String teamName = s1.next();
        
        Scanner ss = new Scanner(System.in);
        System.out.println("What would you like to name you're spaceship?");
        String spaceShipName = s1.next();
        
        Scanner ds = new Scanner(System.in);
        System.out.println("How many days would you like to play for?");
        // 3-10
        int numDays = ds.nextInt();
        
        int numMembers = 0;
        boolean hasNoNums = false;
        while (!hasNoNums) {
	        Scanner s2 = new Scanner(System.in);
	        System.out.println("How many members would you like to be in you're crew?");
	        int numMembersIn = s2.nextInt();
	        
	        Crew crew1 = new Crew(teamName, numMembersIn);
	        if (!crew1.isValidNumMembers()) {
	        	System.out.println("Invalid number of members must be between 2 and 4 inclusive");
	        } else {
	        	numMembers = numMembersIn;
	        	hasNoNums = true;
	        }
        }
        
        // Inititalize Game
        GameEnvironment game = new GameEnvironment(teamName, numMembers, numDays, spaceShipName);
        
        Crew crew = game.getCrew();
        CrewMember cm = new CrewMember("","","",0,"");
        
        int j = 1;
        boolean isDone = false;
        while (!isDone) {
			if (j >= numMembers) {
				isDone = true;
			}
        	Scanner s3 = new Scanner(System.in);
			System.out.println("What type would you like?");
			int i = 1;
			for (String type:cm.getAllTypes()) {
				System.out.println(i+" "+type);
				i++;
			}
			String type = s3.next();
			
			Scanner s4 = new Scanner(System.in);
			System.out.println("What would you like to name it?");
			String name = s4.next();
			
			crew.addCrewMember(type, name);
			j++;
        }
        
        System.out.println("Congrats you have made a team, here they are: ");
		ArrayList<CrewMember> members = crew.getMembers();
		for (CrewMember member: members) {
			System.out.println(member.getName()+ " "+ member.getType());
		}
		
		System.out.println("You're spaceship is named: "+spaceShipName + " and you require: "+game.getSpaceShip().getPeicesRequired()+" peices in order to win the game");
		System.out.println("Awesome, you can now begin the game");
		
		// Now we let the game begin....
		ArrayList<String> actions = new ArrayList<String>();
		actions.add("Status of Crew Members");
		actions.add("Status of SpaceShip");
		actions.add("View Inventory");
		actions.add("Go to next day");
		
		// Randomize Planet objects ....
		Planet startPlanet = game.getStartingPlanet();
		System.out.println("You are on " + startPlanet.getName());
		
		System.out.println("You're actions for the day are:");
		int i = 1;
		for (String action: actions) {
			System.out.println(i + ": " + action);
			i++;
		}
		Scanner actionScanner = new Scanner(System.in);
		System.out.println("What would you like to do? Press number corrosponding");
		int actionTaken = actionScanner.nextInt();
		
		if (actionTaken == 1) {
			ArrayList<CrewMember> memberss = crew.getMembers();
			for (CrewMember member: memberss) {
				System.out.println(member);
			}
			
			// A crew member has a actions
			System.out.println("Select a crew member to complete an action (by name)");
			Scanner crewScanner = new Scanner(System.in);
			ArrayList<CrewMember> members2 = crew.getMembers();
			for (CrewMember member: members2) {
				System.out.println(member.getName()+ " "+ member.getType());
			}
			String name = crewScanner.next();
			CrewMember actionedMember = crew.getMemberByName(name);
			
			System.out.println("What would you like to do with this member? ");
			Scanner crewActionScanner = new Scanner(System.in);
			ArrayList<String> crewActions = new ArrayList<String>();
			crewActions.add("1) Eat food or use medical supply");
			crewActions.add("2) Sleep");
			crewActions.add("3) Repair the shields of the ship");
			crewActions.add("4) Search planet for spaceship parts");
			crewActions.add("5) Pilot ship to new planet");	
			
			for (String ca: crewActions) {
				System.out.println(ca);
			}
			
			int selectedCrewAction = crewActionScanner.nextInt();
			System.out.println(actionedMember);
			System.out.println(selectedCrewAction);			

			if (selectedCrewAction == 1) {
				
			}
			if (selectedCrewAction == 2) {
				if (actionedMember.canSleep()) {
					actionedMember.sleep();
					System.out.println(actionedMember.getName() + " is less tired (new tiredness level: "+actionedMember.getTiredness());
				} else {
					System.out.println("Member cannot sleep -> not tired at all");
				}
				actionedMember.removeAction();
			}
			if (selectedCrewAction == 3) {
				game.getSpaceShip().decreaseShieldLevel();
				System.out.println("Space Ship shield health is now: " + game.getSpaceShip().getShieldHealth());
				actionedMember.removeAction();
			}
		}
		
		if (actionTaken == 2) {
			System.out.println(game.getSpaceShip());
		}
		
		if (actionTaken == 3) {
			// View Inventory
			SpaceOutPost spaceOutPost = game.getSpaceOutPost();
			for (MedicalSupply m : spaceOutPost.getMedicalSupplies()) {
				System.out.println(m);
			}
			
			// From here we must be able to buy items now..
			Scanner buyScanner = new Scanner(System.in);
			System.out.println("Would you like to buy something? (y,n)");
			String buyScannerResp = buyScanner.next();
			if (buyScannerResp.equals("y")) {
				// Display all medical supplies to buy
				Scanner itemToBuy = new Scanner(System.in);
				System.out.println("Type the name of item to buy");
				for (MedicalSupply m : spaceOutPost.getMedicalSupplies()) {
					System.out.println(m);
				}
				String itemBought=itemToBuy.next();
				spaceOutPost.purchaseMedicalSupply(itemBought);
				
				// Now display inventory again...
				for (MedicalSupply m : spaceOutPost.getMedicalSupplies()) {
					System.out.println(m);
				}
			}
		}
		
		if (actionTaken == 4) {
			if (!game.isValidDayAmount()) {
				// Check if you have won/lost....
				System.out.println("You are finished....max days");
			} else {
				game.doToNextDay();
				System.out.println("You are now at day: " + game.getCurrentDay());
				// Random Event
				int randomEvent = game.determineRandomEvent();
				if (randomEvent == 1) {
					System.out.println("Alien Pirates");
				} else if (randomEvent == 2) {
					System.out.println("Space Plague");
				} else {
					System.out.println("Asteroid BELT");
				}
			}
		}
    }
}













