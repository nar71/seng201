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
		
		printDays(game, crew);
	}

    public static void printDays(GameEnvironment game, Crew crew) {
        // Now we let the game begin....
        ArrayList<String> actions = new ArrayList<String>();
        actions.add("Status of Crew Members");
        actions.add("Status of SpaceShip");
        actions.add("View Inventory");
        actions.add("Go to next day");
        
        // Randomize Planet objects ....
        Planet startPlanet = game.getCurrentPlanet();
        System.out.println("You are on " + startPlanet.getName());
        System.out.println("You are on day: " + game.getCurrentDay());

        System.out.println("You're actions for the day are:");
        int i = 1;
        for (String action: actions) {
            System.out.println(i + ": " + action);
            i++;
        }
        Scanner actionScanner = new Scanner(System.in);
        System.out.println("What would you like to do? Press number corrosponding");
        int actionTaken = actionScanner.nextInt();
        
        switch (actionTaken) {
            case 1:
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
                    // Display Inventory first....
                    SpaceOutPost spaceOutPost = game.getSpaceOutPost();
                    if (spaceOutPost.isInventoryEmpty()) {
                        System.out.println("You're inventory is empty...go buy something first ....");
                    } else {
                        System.out.println("Food or med? (1,2)?");
                        Scanner medOrFood = new Scanner(System.in);
                        String medOrFoodResp = medOrFood.next();
                        if (medOrFoodResp.equals("1")) {
                            // Food
                            System.out.println("What food would you like? ");
                            spaceOutPost.displayFoods();
                            Scanner foodS = new Scanner(System.in);
                            String foodType = foodS.next();
                            // Check if we have a food of that type.
                            if (spaceOutPost.foodExists(foodType)) {
                                Food food = spaceOutPost.getFoodByType(foodType);
                                if (food.getType() == "") {
                                    System.out.println("Invalid food");
                                } else {
                                    actionedMember.applyFood(food);
                                    System.out.println(actionedMember);
                                }
                            }
                        } else {
                            System.out.println("What meds would you like? ");
                            spaceOutPost.displayMedicalSupplies();
                            Scanner medS = new Scanner(System.in);
                            String medType = medS.next();
                            // Check if we have a food of that type.
                            if (spaceOutPost.medicalSupplyExists(medType)) {
                                MedicalSupply ms = spaceOutPost.getMedicalSupplyByType(medType);
                                if (ms.getType() == "") {
                                    System.out.println("Invalid Medical Supply"); 
                                } else {
                                    actionedMember.applyMedicalSupply(ms);
                                    System.out.println(actionedMember);
                                }
                            }
                        }
                    }
                }
          
                if (selectedCrewAction == 2) {
                    // apparently can sleep at any level so this has changed
                    if (actionedMember.canSleep()) {
                        actionedMember.sleep();
                        System.out.println(actionedMember.getName() + " is less tired (new tiredness level: "+actionedMember.getTiredness());
                    } else {
                        System.out.println(actionedMember.getName() + " is less tired (new tiredness level: 100");
                    }
                    actionedMember.removeAction();
                }
          
                if (selectedCrewAction == 3) {
                    // This needs to depend on some parameters
                    game.getSpaceShip().decreaseShieldLevel();
                    System.out.println("Space Ship shield health is now: " + game.getSpaceShip().getShieldHealth());
                    actionedMember.removeAction();
                }

                if (selectedCrewAction == 5) {
                	Planet planet = game.getPlanet();
                	Planet[] allPlanets = planet.getAll();
                	Scanner choosePlayer = new Scanner(System.in);
                	System.out.println("Choose another player that you would like to piolet the ship with?");
	                ArrayList<CrewMember> members3 = crew.getMembers();
	                for (CrewMember member: members3) {
	                	if (!actionedMember.getName().equals(member.getName())) {
	                    	System.out.println(member.getName()+ " "+ member.getType());
	                	}
	                }
	                String choosenPlayer = choosePlayer.next();
	                CrewMember chosenMember = crew.getMemberByName(choosenPlayer);
	                Scanner choosePlanet = new Scanner(System.in);
                	for (int d = 0; d < allPlanets.length; d++) {
                		if (!allPlanets[d].getName().equals(game.getCurrentPlanet().getName())) {
                			System.out.println(allPlanets[d]);
                		}
                	}
                	String chosenPlanet = choosePlanet.next();
                	Planet newPlanet = planet.getByName(chosenPlanet);

                	// Check if it can be actioned....

                	System.out.println("You have chosen planet: " + chosenPlanet);
                	game.changeCurrentPlanet(newPlanet);

                	actionedMember.removeAction();
                	chosenMember.removeAction();
                }

                printDays(game, crew);
            break;
            
            case 2:
                System.out.println(game.getSpaceShip());
                printDays(game, crew);
            break;
            
            case 3:
                // View Inventory
                SpaceOutPost spaceOutPost = game.getSpaceOutPost();
                System.out.println("You have $" + spaceOutPost.getCurrentMoney());
                spaceOutPost.displayInventory();
                
                Scanner foodsOrMeds = new Scanner(System.in);
                System.out.println("Do you want to buy foods or meds? (1,2) or n");
                String foodsOrMedsResp = foodsOrMeds.next();
                if (!foodsOrMedsResp.equals("n")) {
                    // Display all medical supplies to buy
                    if (foodsOrMedsResp.equals("1")) {
                         // Buy foods.
                        Scanner itemToBuy = new Scanner(System.in);
                        System.out.println("Type the name of item to buy");
                        spaceOutPost.displayFoods();
                        String itemToBuyResp = itemToBuy.next();
                        if (!itemToBuyResp.isEmpty()) {
                        	Food foodToBuy = spaceOutPost.getFoodByType(itemToBuyResp);
                        	if (spaceOutPost.canAffordItem(foodToBuy.getCost())) {
                        		spaceOutPost.purchaseFood(foodToBuy);
                        	} else {
                        		// GET MORE MONEY
                        	   
                            }
                        }
                        System.out.println("You now have $" + spaceOutPost.getCurrentMoney());
                        spaceOutPost.displayInventory();
                    } else {
                        Scanner itemToBuy = new Scanner(System.in);
                        System.out.println("Type the name of item to buy");
                        spaceOutPost.displayMedicalSupplies();
                        String itemBought = itemToBuy.next();
                        if (!itemBought.isEmpty()) {
                    		MedicalSupply ms = spaceOutPost.getMedicalSupplyByType(itemBought);
                    		if (spaceOutPost.canAffordItem(ms.getCost())) {
                    			spaceOutPost.purchaseMedicalSupply(ms);
                    		} else {

                    		}
                        }
                        // Now display inventory again...
                        System.out.println("You now have $" + spaceOutPost.getCurrentMoney());
                        spaceOutPost.displayInventory();
                    }
                }
                printDays(game, crew);
            break;
            
            case 4:
                if (!game.isValidCurrentDay()) {
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
                printDays(game, crew);
            break;
        }
    }
}













