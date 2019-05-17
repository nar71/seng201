import java.util.*;

public class CommandLine {
    private GameEnvironment game;
    private Crew crew;

    private Scanner scanner;

    CommandLine() {
        this.scanner = new Scanner(System.in);
        setUp();
        printDays();
    }

    public static void main(String[] args) {
        new CommandLine();
    }

    public void setUp() {
        System.out.println("What would you like to name you're team?");
        String teamName = scanner.next();
        
        System.out.println("What would you like to name you're spaceship?");
        String spaceShipName = scanner.next();
        
        int numDays = 0;
        boolean isValidNumDays = false;
        while (!isValidNumDays) {
            System.out.println("How many days would you like to play for?");
            // 3-10
            int numDaysIn = scanner.nextInt();
            if (numDaysIn >= 3 && numDaysIn <= 10) {
                isValidNumDays = true;
                numDays = numDaysIn;
            } else {
                System.out.println("Invalid number of days must be between 3 and 10 inclusive");
            }
        }

        int numMembers = 0;
        boolean isValidNumMembers = false;
        while (!isValidNumMembers) {
            System.out.println("How many members would you like to be in you're crew?");
            int numMembersIn = scanner.nextInt();
            
            Crew c = new Crew(teamName, numMembersIn);
            if (!c.isValidNumMembers()) {
                System.out.println("Invalid number of members must be between 2 and 4 inclusive");
            } else {
                numMembers = numMembersIn;
                isValidNumMembers = true;
            }
        }
        
        // Inititalize Game & CREW
        this.game = new GameEnvironment(teamName, numMembers, numDays, spaceShipName);
        this.crew = game.getCrew();

        CrewMember crewMember = new CrewMember();

        int i = 1;
        boolean allMembersAdded = false;
        while (!allMembersAdded) {
            if (i >= numMembers) {
                allMembersAdded = true;
            }

            System.out.println("What type would you like?");
            
            int j = 1;
            for (String type: crewMember.getAllTypes()) {
                System.out.println(j+" "+type);
                j++;
            }

            String type = scanner.next();
            System.out.println("What would you like to name it?");
            String name = scanner.next();
            
            crew.addCrewMember(type, name);
            i++;
        }
        
        System.out.println("Congrats you have made a team, here they are: ");
        for (CrewMember member: crew.getMembers()) {
            System.out.println(member.getName()+ " "+ member.getType());
        }
        
        System.out.println("You're spaceship is named: "+spaceShipName + " and you require: "+game.getSpaceShip().getPeicesRequired()+" peices in order to win the game");
        System.out.println("Awesome, you can now begin the game");
    }

    public void printDays() {
        // Randomize Planet objects ....
        Planet startPlanet = game.getCurrentPlanet();
        System.out.println("You are on " + startPlanet.getName());
        System.out.println("You are on day: " + game.getCurrentDay());

        // First we check if all parts are found if so we wonn...
        if (game.getSpaceShip().allPartsFound()) {
            System.out.println("You have won the game -> all parts found..");
            System.exit(0);
        }

        // Now we let the game begin....
        ArrayList<String> actions = new ArrayList<String>();
        actions.add("Status of Crew Members");
        actions.add("Status of SpaceShip");
        actions.add("View Inventory");
        actions.add("Go to next day");

        System.out.println("You're actions for the day are:");
        int i = 1;
        for (String action: actions) {
            System.out.println(i + ": " + action);
            i++;
        }

        System.out.println("What would you like to do? Press number corrosponding");

        int actionTaken = scanner.nextInt();
        switch (actionTaken) {
            case 1:
                // Action is Crew Details
                crewAction();
                printDays();
            break;
            
            case 2:
                // Leaving this here for now as small
                System.out.println(game.getSpaceShip());
                printDays();
            break;
            
            case 3:
                // Case 3 is outpost
                spaceOutPostAction();
                printDays();
            break;
            
            case 4:
                nextDayAction();
                printDays();
            break;
        }
    }

    public void crewAction() {
        for (CrewMember member: crew.getMembers()) {
            System.out.println(member);
        }
        
        // A crew member has a actions
        System.out.println("Select a crew member to complete an action (by name)");
        for (CrewMember member: crew.getMembers()) {
            System.out.println(member.getName()+ " "+ member.getType());
        }
        String name = scanner.next();
        CrewMember actionedMember = crew.getMemberByName(name);
        
        System.out.println("What would you like to do with this member? ");

        ArrayList<String> crewActions = new ArrayList<String>();
        crewActions.add("1) Eat food or use medical supply");
        crewActions.add("2) Sleep");
        crewActions.add("3) Repair the shields of the ship");
        crewActions.add("4) Search planet for spaceship parts");
        crewActions.add("5) Pilot ship to new planet"); 
        
        for (String action: crewActions) {
            System.out.println(action);
        }
        
        int selectedCrewAction = scanner.nextInt();
        System.out.println(actionedMember);
        System.out.println(selectedCrewAction);

        if (actionedMember.hasActionsLeft()) {
            switch (selectedCrewAction) {
                case 1:
                    // Display Inventory first....
                    SpaceOutPost spaceOutPost = game.getSpaceOutPost();
                    if (spaceOutPost.isInventoryEmpty()) {
                        System.out.println("You're inventory is empty...go buy something first ....");
                    } else {
                        System.out.println("Food or med? (1,2)?");
                        String medOrFoodResp = scanner.next();
                        if (medOrFoodResp.equals("1")) {
                            // Food
                            System.out.println("What food would you like? ");
                            spaceOutPost.displayFoods();
                            String foodType = scanner.next();
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
                            String medType = scanner.next();
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
                    actionedMember.removeAction();
                    crewAction();
                break;
          
                case 2:
                    // apparently can sleep at any level so this has changed
                    if (actionedMember.canSleep()) {
                        actionedMember.sleep();
                        System.out.println(actionedMember.getName() + " is less tired (new tiredness level: "+actionedMember.getTiredness());
                    } else {
                        System.out.println(actionedMember.getName() + " is less tired (new tiredness level: 100");
                    }
                    actionedMember.removeAction();
                    //crewAction();
                break;
          
                case 3:
                    // This needs to depend on some parameters
                    game.getSpaceShip().increaseShieldLevel();
                    System.out.println("Space Ship shield health is now: " + game.getSpaceShip().getShieldHealth());
                    actionedMember.removeAction();
                    //crewAction();
                break;

                case 4:
                    // Search planet for ship parts...
                    // Do we have a random event function on the planet class....and on each sub class have diferent probability variables...
                    // Do we....
                    game.searchPlanetForParts();
                    actionedMember.removeAction();
                    crewAction();
                break;

                case 5:
                    Planet planet = game.getPlanet();
                    Planet[] allPlanets = planet.getAll();
                    System.out.println("Choose another player that you would like to piolet the ship with?");
                    for (CrewMember member: crew.getMembers()) {
                        if (!actionedMember.getName().equals(member.getName())) {
                            System.out.println(member.getName()+ " "+ member.getType());
                        }
                    }
                    String choosenPlayer = scanner.next();
                    CrewMember chosenMember = crew.getMemberByName(choosenPlayer);
                    for (int d = 0; d < allPlanets.length; d++) {
                        if (!allPlanets[d].getName().equals(game.getCurrentPlanet().getName())) {
                            System.out.println(allPlanets[d]);
                        }
                    }
                    String chosenPlanet = scanner.next();
                    Planet newPlanet = planet.getByName(chosenPlanet);

                    // Check if it can be actioned....

                    System.out.println("You have chosen planet: " + chosenPlanet);
                    game.changeCurrentPlanet(newPlanet);

                    actionedMember.removeAction();
                    chosenMember.removeAction();
                    //crewAction();
                break;
            }
        }
    }

    public void spaceOutPostAction() {
        // View Inventory
        SpaceOutPost spaceOutPost = game.getSpaceOutPost();

        boolean boughtEnough = false;
        while (!boughtEnough) {
            System.out.println("You have $" + spaceOutPost.getCurrentMoney());
            spaceOutPost.displayInventory();

            System.out.println("Do you want to buy foods or meds? (f, m) or exit (n)");
            String inventoryAction = scanner.next();
            if (!inventoryAction.equals("n")) {
                // Display all medical supplies to buy
                if (inventoryAction.equals("f")) {
                     // Buy foods.
                    System.out.println("Type the name of item to buy");
                    spaceOutPost.displayFoods();
                    String foodType = scanner.next();
                    if (!foodType.isEmpty()) {
                        Food foodToBuy = spaceOutPost.getFoodByType(foodType);
                        if (spaceOutPost.canAffordItem(foodToBuy.getCost())) {
                            spaceOutPost.purchaseFood(foodToBuy);
                        } else {
                            // GET MORE MONEY
                           
                        }
                    }
                    System.out.println("You now have $" + spaceOutPost.getCurrentMoney());
                    spaceOutPost.displayInventory();
                } else {
                    System.out.println("Type the name of item to buy");
                    spaceOutPost.displayMedicalSupplies();
                    String medicalSupplyType = scanner.next();
                    if (!medicalSupplyType.isEmpty()) {
                        /// run a check if exists
                        MedicalSupply ms = spaceOutPost.getMedicalSupplyByType(medicalSupplyType);
                        if (spaceOutPost.canAffordItem(ms.getCost())) {
                            spaceOutPost.purchaseMedicalSupply(ms);
                        } else {

                        }
                    } else {
                        System.out.println("Please enter a medical supply");
                    }
                    // Now display inventory again...
                    System.out.println("You now have $" + spaceOutPost.getCurrentMoney());
                    spaceOutPost.displayInventory();
                }
            } else {
                boughtEnough = true;
            }

            System.out.println("Would you like to buy anymore items? (y,n)");
            if (scanner.next().equals("n")) {
                boughtEnough = true;
            } 
        }
    }

    public void nextDayAction() {
        if (!game.isValidCurrentDay()) {
            // Check if you have won/lost....
            System.out.println("You are finished....max days");
            // exit...
            System.exit(0);
        } else {
            game.goToNextDay();
            System.out.println("You are now at day: " + game.getCurrentDay());
            // Random Event
            int randomEvent = game.determineRandomEvent();
            for (CrewMember m: game.getCrew().getMembers()) {
                m.incrementTiredness(10);
            }
            if (randomEvent == 1) {
                System.out.println("Alien Pirates");
                game.getSpaceOutPost().displayInventory();
            } else if (randomEvent == 2) {
                System.out.println("Space Plague");
            } else {
                System.out.println("Asteroid Belt");
            }
        }
    }
}