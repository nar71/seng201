import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;

public class CrewPanel extends JPanel {
    private HomeScreen screen;

    private GameEnvironment environment;

    private Crew crew;;

    private SpaceOutPost spaceOutPost;

    private CardLayout cardLayout;

    private JPanel content;

    private JPanel sideBar;

    private JPanel crewDetailsPanel;

    private JPanel repairShieldsPanel;

    private JPanel newFoodPanel;
    
    private JPanel newMedicalItemPanel;

    private JPanel newPlanetPanel;

    private JPanel crewMemberSleepPanel;

    private JButton crewDetailsBtn;

    private JButton crewMemberSleepBtn;

    private JButton repairShipBtn;

    private JButton pilotNewShipBtn;

    private JButton applyFoodBtn;

    private JButton applyMedItemBtn;

    private int numberOfCheckboxesChecked;

    private static final String CREW_STATUS_PANEL_STRING = "CREW_STATUS_PANEL";

    private static final String REPAIR_SHIP_PANEL_STRING = "REPAIR_SHIP_PANEL";

    private static final String NEW_PLANET_PANEL_STRING = "PILOT_NEW_PLANET_PANEL";

    private static final String CREW_SLEEP_PANEL_STRING = "CREW_SLEEP";

    private static final String CREW_APPLY_FOOD_PANEL_STRING = "APPLY_FOOD";

    private static final String CREW_APPLY_MEDICAL_PANEL_STRING = "APPLY_MEDICAL";

    CrewPanel(HomeScreen screen) {
        super();

        this.screen = screen;
        this.environment = screen.environment;
        this.crew = environment.getCrew();
        this.spaceOutPost = environment.getSpaceOutPost();

        init();
    }

    private void init() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        addSideBar();

        this.content = new JPanel();
        add(content);
        
        this.cardLayout = new CardLayout();
        content.setLayout(cardLayout);

        addCrewDetailsPanel();
        refreshCrewDetailsPanel();
        addNewFoodPanel();
        addNewMedicalItemPanel();
        addNewPlanetPanel();
        addCrewMemberSleepPanel();
        addRepairShipPanel();

        cardLayout.show(content, CREW_STATUS_PANEL_STRING);

        addActionListeners();
    }

    public void showCrewStatusCard() {
        cardLayout.show(content, CREW_STATUS_PANEL_STRING);        
        applyFoodBtn.setEnabled(true);
        crewDetailsBtn.setEnabled(false);
        pilotNewShipBtn.setEnabled(true);
        applyMedItemBtn.setEnabled(true);
        crewMemberSleepBtn.setEnabled(true);
        repairShipBtn.setEnabled(true);
    }

    public void showPilotNewPlanetCard() {
        cardLayout.show(content, NEW_PLANET_PANEL_STRING);
        applyFoodBtn.setEnabled(true);
        crewDetailsBtn.setEnabled(true);
        pilotNewShipBtn.setEnabled(false);
        applyMedItemBtn.setEnabled(true);
        crewMemberSleepBtn.setEnabled(true);
        repairShipBtn.setEnabled(true);
    }

    public void refresh() {
        showCrewStatusCard();
        refreshCrewDetailsPanel();
        refreshNewFoodPanel();
        refreshNewMedicalItemPanel();
        refreshNewPlanetPanel();
        refreshCrewMemberSleepPanel();
        refreshRepairShipPanel();
    }

    public void addSideBar() {
        JPanel sideBar = new JPanel();
        sideBar.setBorder(new EmptyBorder(10, 10, 10, 10));
        JPanel crewDetailsBtnPanel = new JPanel();
        sideBar.add(crewDetailsBtnPanel);

        this.crewDetailsBtn = new JButton("Crew Details");
        crewDetailsBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        crewDetailsBtnPanel.add(crewDetailsBtn);

        JPanel crewMemberSleepPanel = new JPanel();
        sideBar.add(crewMemberSleepPanel);

        this.crewMemberSleepBtn = new JButton("Sleep");
        crewMemberSleepBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        crewMemberSleepPanel.add(crewMemberSleepBtn);

        JPanel pilotNewShipBtnPanel = new JPanel();
        sideBar.add(pilotNewShipBtnPanel);
        this.pilotNewShipBtn = new JButton("Pilot To New Planet");
        pilotNewShipBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        pilotNewShipBtnPanel.add(pilotNewShipBtn);

        JPanel repairShipPanel = new JPanel();
        sideBar.add(repairShipPanel);
        this.repairShipBtn = new JButton("Repair ship shields");
        repairShipBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        repairShipPanel.add(repairShipBtn);

        JPanel applyFoodBtnPanel = new JPanel();
        sideBar.add(applyFoodBtnPanel);
        this.applyFoodBtn = new JButton("Apply Food");
        applyFoodBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        applyFoodBtnPanel.add(applyFoodBtn);

        JPanel applyMedItemPanel = new JPanel();
        sideBar.add(applyMedItemPanel);
        this.applyMedItemBtn = new JButton("Apply Medical Item");
        applyMedItemBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        applyMedItemPanel.add(applyMedItemBtn);

        sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));
        add(sideBar);
    }

    public void addActionListeners() {
        crewDetailsBtn.setEnabled(false);
        crewDetailsBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                applyFoodBtn.setEnabled(true);
                crewDetailsBtn.setEnabled(false);
                pilotNewShipBtn.setEnabled(true);
                applyMedItemBtn.setEnabled(true);
                crewMemberSleepBtn.setEnabled(true);
                repairShipBtn.setEnabled(true);
                refreshCrewDetailsPanel();
                cardLayout.show(content, CREW_STATUS_PANEL_STRING);
            }
        });

        crewMemberSleepBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                applyFoodBtn.setEnabled(true);
                crewDetailsBtn.setEnabled(true);
                pilotNewShipBtn.setEnabled(true);
                applyMedItemBtn.setEnabled(true);
                crewMemberSleepBtn.setEnabled(false);
                repairShipBtn.setEnabled(true);
                refreshCrewMemberSleepPanel();
                cardLayout.show(content, CREW_SLEEP_PANEL_STRING);
            }
        });

        repairShipBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                applyFoodBtn.setEnabled(true);
                crewDetailsBtn.setEnabled(true);
                pilotNewShipBtn.setEnabled(true);
                applyMedItemBtn.setEnabled(true);
                crewMemberSleepBtn.setEnabled(true);
                repairShipBtn.setEnabled(false);
                cardLayout.show(content, REPAIR_SHIP_PANEL_STRING);
            }
        });

        pilotNewShipBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg1) {
                applyFoodBtn.setEnabled(true);
                crewDetailsBtn.setEnabled(true);
                pilotNewShipBtn.setEnabled(false);
                applyMedItemBtn.setEnabled(true);
                crewMemberSleepBtn.setEnabled(true);
                repairShipBtn.setEnabled(true);
                refreshNewPlanetPanel();
                cardLayout.show(content, NEW_PLANET_PANEL_STRING);
            }
        });

        applyFoodBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg2) {
                applyFoodBtn.setEnabled(false);
                crewDetailsBtn.setEnabled(true);
                pilotNewShipBtn.setEnabled(true);
                applyMedItemBtn.setEnabled(true);
                crewMemberSleepBtn.setEnabled(true);
                repairShipBtn.setEnabled(true);
                refreshNewFoodPanel();
                cardLayout.show(content, CREW_APPLY_FOOD_PANEL_STRING);
            }
        });

        applyMedItemBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg3) {
                applyFoodBtn.setEnabled(true);
                crewDetailsBtn.setEnabled(true);
                pilotNewShipBtn.setEnabled(true);
                applyMedItemBtn.setEnabled(false);
                crewMemberSleepBtn.setEnabled(true);
                repairShipBtn.setEnabled(true);
                refreshNewMedicalItemPanel();
                cardLayout.show(content, CREW_APPLY_MEDICAL_PANEL_STRING);
            }
        });
    }

    private void addCrewDetailsPanel() {
        this.crewDetailsPanel = new JPanel();
        content.add(crewDetailsPanel, CREW_STATUS_PANEL_STRING);
        crewDetailsPanel.setLayout(new GridLayout(0, 4, 0, 0));  
    }

    private void refreshCrewDetailsPanel() {
        crewDetailsPanel.removeAll();

        for (CrewMember member: crew.getMembers()) {
            JPanel memberPanel = new JPanel();
            memberPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
            memberPanel.setLayout(null);
            crewDetailsPanel.add(memberPanel);
            
            JLabel memberImageLabel = new JLabel("");
            memberImageLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
            memberImageLabel.setBounds(30, 13, 120, 120);
            memberImageLabel.setIcon(Funcs.getScaledIcon(Image.getCrewMemberImagePath(member), 120, 120));
            memberPanel.add(memberImageLabel);
            
            JLabel memberNameLabel = new JLabel(member.getName());
            memberNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
            memberNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
            memberNameLabel.setBounds(10, 150, 160, 25);
            memberPanel.add(memberNameLabel);

            JLabel memberTypeLabel = new JLabel(String.format("%s", member.getType()));
            memberTypeLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
            memberTypeLabel.setHorizontalAlignment(SwingConstants.CENTER);
            memberTypeLabel.setBounds(10, 170, 160, 25);
            memberPanel.add(memberTypeLabel);

            JLabel memberSpecialtyLabel = new JLabel("Specialty:" + member.getSpecialty());
            memberSpecialtyLabel.setHorizontalAlignment(SwingConstants.LEFT);
            memberSpecialtyLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
            memberSpecialtyLabel.setBounds(10, 200, 190, 24);
            memberPanel.add(memberSpecialtyLabel);
            
            JLabel memberHealthLabel = new JLabel("Health:" + member.getCurrentHealth() + "/" + member.getMaxHealth());
            memberHealthLabel.setHorizontalAlignment(SwingConstants.LEFT);
            memberHealthLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
            memberHealthLabel.setBounds(10, 230, 190, 24);
            memberPanel.add(memberHealthLabel);

            JLabel memberHungerLabel = new JLabel("Hunger level:" + member.getHungerLevel());
            memberHungerLabel.setHorizontalAlignment(SwingConstants.LEFT);
            memberHungerLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
            memberHungerLabel.setBounds(10, 260, 190, 24);
            memberPanel.add(memberHungerLabel);

            JLabel memberTirednessLabel = new JLabel("Tiredness:" + member.getTiredness());
            memberTirednessLabel.setHorizontalAlignment(SwingConstants.LEFT);
            memberTirednessLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
            memberTirednessLabel.setBounds(10, 290, 190, 24);
            memberPanel.add(memberTirednessLabel);

            JLabel actionsLeftLabel = new JLabel("Actions left:" + member.getActions());
            actionsLeftLabel.setHorizontalAlignment(SwingConstants.LEFT);
            actionsLeftLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
            actionsLeftLabel.setBounds(10, 320, 190, 24);
            memberPanel.add(actionsLeftLabel);

            String isSickString = "no";
            if (member.isSick()) {
                isSickString = "yes";
            }
            JLabel isSickLabel = new JLabel("Is sick: " + isSickString);
            isSickLabel.setHorizontalAlignment(SwingConstants.LEFT);
            isSickLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
            isSickLabel.setBounds(10, 350, 190, 24);

            JLabel memberDescLabel = new JLabel("Description:" + member.getDescription());
            memberDescLabel.setHorizontalAlignment(SwingConstants.LEFT);
            memberDescLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
            memberDescLabel.setBounds(10, 380, 200, 100);
            memberPanel.add(memberDescLabel);

            memberPanel.add(isSickLabel);
        }

        if ((4 - crew.getNumMembers()) > 0) {
            for (int i = 0; i < (4 - crew.getNumMembers()); i++) {
                JPanel others = new JPanel();
                others.setLayout(null);
                crewDetailsPanel.add(others);
            }
        }
    }


    private void addNewFoodPanel() {
        this.newFoodPanel = new JPanel();
        newFoodPanel.setLayout(new BoxLayout(newFoodPanel, BoxLayout.Y_AXIS));
        content.add(newFoodPanel, CREW_APPLY_FOOD_PANEL_STRING);
    }

    private void refreshNewFoodPanel() {
        newFoodPanel.removeAll();

        ButtonGroup selectedMemberBtnGroup = new ButtonGroup();
        JPanel membersPanel = getGridSelectMembers(selectedMemberBtnGroup, "hunger");
        newFoodPanel.add(membersPanel);

        JPanel foodBoxPanel = new JPanel(new GridLayout(1, 6));
        newFoodPanel.add(foodBoxPanel);

        ButtonGroup foodButtonGroup = new ButtonGroup();

        for (Food food : spaceOutPost.getFoods()) {
            JPanel foodPanel = new JPanel();
            foodPanel.setLayout(null);
            JRadioButton foodRadioBtn = new JRadioButton(food.getType() + "(" + food.getCount() + ")");
            foodRadioBtn.putClientProperty("Food", food);
            foodRadioBtn.setBounds(20, 130, 150, 23);
            if (!food.exists()) {
                foodRadioBtn.setEnabled(false);
            }

            JLabel memberImageLabel = new JLabel("");
            memberImageLabel.setBounds(22, 10, 100, 100);
            memberImageLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
            memberImageLabel.setIcon(Funcs.getScaledIcon(Image.getFoodImagePath(food), 100, 100));

            foodButtonGroup.add(foodRadioBtn);
            foodPanel.add(foodRadioBtn);
            foodPanel.add(memberImageLabel);
            foodBoxPanel.add(foodPanel);
        }

        JPanel applyFoodPanel = new JPanel();
        applyFoodPanel.setLayout(null);
        JButton applyFoodBtn = new JButton("Apply Food Item");
        applyFoodBtn.setBounds(600, 50, 150, 30);
        applyFoodPanel.add(applyFoodBtn);
        newFoodPanel.add(applyFoodPanel);

        applyFoodBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedMemberBtnGroup.getSelection() == null || foodButtonGroup.getSelection() == null) {
                    JOptionPane.showMessageDialog(null, "Please tick both check boxes");
                } else {
                    JRadioButton selectedMemberRadio = Funcs.selectedButton(selectedMemberBtnGroup);
                    JRadioButton selectedFoodRadio = Funcs.selectedButton(foodButtonGroup);

                    CrewMember member = (CrewMember) selectedMemberRadio.getClientProperty("CrewMember");
                    Food food = (Food) selectedFoodRadio.getClientProperty("Food");

                    member.applyFood(food);
                    spaceOutPost.removeFood(food);
                    
                    member.removeAction();
                    selectedMemberBtnGroup.clearSelection();
                    if (!member.hasActionsLeft()) {
                        selectedMemberRadio.setEnabled(false);
                        
                        if (isPlayerDefeated()) {
                        	screen.closeWindow(false, "You are out of actions");
                        }
                    }

                    foodButtonGroup.clearSelection();
                    if (!spaceOutPost.foodExists(food.getType())) {
                        selectedFoodRadio.setEnabled(false);
                    }
                    selectedFoodRadio.setText(food.getType() + "(" + food.getCount() + ")");

                    JOptionPane.showMessageDialog(null, food.getType() + " successfully applied");
                }
            }
        });
    }

    private void addNewMedicalItemPanel() {
        this.newMedicalItemPanel = new JPanel();
        newMedicalItemPanel.setLayout(new BoxLayout(newMedicalItemPanel, BoxLayout.Y_AXIS));
        content.add(newMedicalItemPanel, CREW_APPLY_MEDICAL_PANEL_STRING);
    }

    public void refreshNewMedicalItemPanel() {
        newMedicalItemPanel.removeAll();

        ButtonGroup selectedMemberBtnGroup = new ButtonGroup();
        JPanel membersPanel = getGridSelectMembers(selectedMemberBtnGroup, "health");
        newMedicalItemPanel.add(membersPanel);

        JPanel foodBoxPanel = new JPanel(new GridLayout(1, 6));
        //foodBoxPanel.setLayout(new BoxLayout(foodBoxPanel, BoxLayout.Y_AXIS));
        newMedicalItemPanel.add(foodBoxPanel);

        ButtonGroup medicalRadioGroup = new ButtonGroup();

        for (MedicalSupply food : spaceOutPost.getMedicalSupplies()) {
            JPanel foodPanel = new JPanel();
            foodPanel.setLayout(null);
            JRadioButton foodRadioBtn = new JRadioButton(food.getType() + "(" + food.getCount() + ")");
            foodRadioBtn.putClientProperty("Food", food);
            foodRadioBtn.setBounds(20, 130, 150, 23);
            if (!food.exists()) {
                foodRadioBtn.setEnabled(false);
            }

            JLabel memberImageLabel = new JLabel("");
            memberImageLabel.setBounds(22, 10, 100, 100);
            memberImageLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
            memberImageLabel.setIcon(Funcs.getScaledIcon(Image.getMedicalSupplyImagePath(food), 100, 100));

            medicalRadioGroup.add(foodRadioBtn);
            foodPanel.add(foodRadioBtn);
            foodPanel.add(memberImageLabel);
            foodBoxPanel.add(foodPanel);
        }

        JPanel applyFoodPanel = new JPanel();
        applyFoodPanel.setLayout(null);
        JButton applyMedicalItemBtn = new JButton("Apply Food Item");
        applyMedicalItemBtn.setBounds(600, 50, 150, 30);
        applyFoodPanel.add(applyMedicalItemBtn);
        newMedicalItemPanel.add(applyFoodPanel);

        applyMedicalItemBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedMemberBtnGroup.getSelection() == null || medicalRadioGroup.getSelection() == null) {
                     JOptionPane.showMessageDialog(null, "Please select both check boxes");
                } else {
                    JRadioButton selectedMemberRadio = Funcs.selectedButton(selectedMemberBtnGroup);
                    JRadioButton selectedMedicalSupplyRadio = Funcs.selectedButton(medicalRadioGroup);

                    CrewMember member = (CrewMember) selectedMemberRadio.getClientProperty("CrewMember");
                    MedicalSupply medicalSupply = (MedicalSupply) selectedMedicalSupplyRadio.getClientProperty("MedicalSupply");

                    member.applyMedicalSupply(medicalSupply);
                    spaceOutPost.removeMedicalSupply(medicalSupply);
                    
                    String responseString = medicalSupply.getType() + " successfully applied";

                    // Check if space plague cure..
                    if (member.isSick() && medicalSupply.isSpacePlagueCure()) {
                        member.cure();
                        responseString += " " + member.getName() + " cured"; 
                    }

                    member.removeAction();
                    selectedMemberBtnGroup.clearSelection();
                    if (!member.hasActionsLeft()) {
                        selectedMemberRadio.setEnabled(false);
                        
                        if (isPlayerDefeated()) {
                        	screen.closeWindow(false, "You are out of actions");
                        }
                    }

                    medicalRadioGroup.clearSelection();
                    if (!spaceOutPost.medicalSupplyExists(medicalSupply.getType())) {
                        selectedMedicalSupplyRadio.setEnabled(false);
                    }
                    selectedMedicalSupplyRadio.setText(medicalSupply.getType() + "(" + medicalSupply.getCount() + ")");

                    JOptionPane.showMessageDialog(null, responseString);
                }
            }
        });
    }

    private void addNewPlanetPanel() {
        this.newPlanetPanel = new JPanel();
        newPlanetPanel.setLayout(new BoxLayout(newPlanetPanel, BoxLayout.Y_AXIS));
        content.add(newPlanetPanel, NEW_PLANET_PANEL_STRING);
    }

    private void refreshNewPlanetPanel() {
        newPlanetPanel.removeAll();

        JPanel planets = new JPanel(new GridLayout(1,3));
        newPlanetPanel.add(planets);

        ButtonGroup planetButtonGroup = new ButtonGroup();

        Planet planet = environment.getPlanet();
        Planet[] allPlanets = planet.getAll();

        for (int i = 0; i < allPlanets.length; i++) {
            if (!allPlanets[i].getName().equals(environment.getCurrentPlanet().getName())) {
                JPanel insidePanel = fillPlanetPanelAndButtonGroup(allPlanets[i], planetButtonGroup);
                planets.add(insidePanel);
            }
        }

        ButtonGroup membersBtnGroupOne = new ButtonGroup();
        JPanel membersPanelOne = getGridSelectMembers(membersBtnGroupOne, "");
        newPlanetPanel.add(membersPanelOne);

        ButtonGroup membersBtnGroupTwo = new ButtonGroup();
        JPanel membersPanelTwo = getGridSelectMembers(membersBtnGroupTwo, "");
        newPlanetPanel.add(membersPanelTwo);

        JPanel goBtnPanel = new JPanel();
        JButton doActionBtn = new JButton("Go");
        goBtnPanel.add(doActionBtn);
        newPlanetPanel.add(goBtnPanel);

        // Action Listenernes
        doActionBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JRadioButton selectedMemberRadioOne = Funcs.selectedButton(membersBtnGroupOne);
                JRadioButton selectedMemberRadioTwo = Funcs.selectedButton(membersBtnGroupTwo);
                JRadioButton selectedPlanetRadio = Funcs.selectedButton(planetButtonGroup);
                if (membersBtnGroupOne.getSelection() == null || membersBtnGroupTwo.getSelection() == null || 
                    planetButtonGroup.getSelection() == null) {
                    JOptionPane.showMessageDialog(null, "Please select a planet and two crew members");
                } else {
                    Planet planet = (Planet) selectedPlanetRadio.getClientProperty("Planet");
                    CrewMember selectedMemberOne = (CrewMember) selectedMemberRadioOne.getClientProperty("CrewMember");
                    CrewMember selectedMemberTwo = (CrewMember) selectedMemberRadioTwo.getClientProperty("CrewMember");
                    if (selectedMemberOne.getName() == selectedMemberTwo.getName()) {
                        JOptionPane.showMessageDialog(null, "You cannot select two members of the same");
                    } else {
                        selectedMemberOne.removeAction();
                        selectedMemberTwo.removeAction();
                        if (!selectedMemberOne.hasActionsLeft() && !selectedMemberTwo.hasActionsLeft()) {
    	                    if (isPlayerDefeated()) {
    	                    	screen.closeWindow(false, "You are out of actions");
    	                    }
                    	}

                        environment.changeCurrentPlanet(planet);
                     
                        planetButtonGroup.clearSelection();
                        membersBtnGroupOne.clearSelection();
                        membersBtnGroupTwo.clearSelection();

                        if (!selectedMemberOne.hasActionsLeft()) {
                            selectedMemberRadioOne.setEnabled(false);
                        }
                        if (!selectedMemberTwo.hasActionsLeft()) {
                            selectedMemberRadioTwo.setEnabled(false);
                        }

                        screen.refreshTopPanel();

                        planets.removeAll();
                        refreshPlanetsOntoPanel(planetButtonGroup, planets);

                        membersPanelOne.removeAll();
                        refillCrewPanelAndButtonGroup(membersPanelOne, membersBtnGroupOne, "");

                        membersPanelTwo.removeAll();
                        refillCrewPanelAndButtonGroup(membersPanelTwo, membersBtnGroupTwo, "");

                        JOptionPane.showMessageDialog(null, "You have successfully pioleted to: " + planet.getName());
                    }
                }
            }
        });
    }

    public void addCrewMemberSleepPanel() {
        this.crewMemberSleepPanel = new JPanel();
        crewMemberSleepPanel.setLayout(new BoxLayout(crewMemberSleepPanel, BoxLayout.Y_AXIS));
        content.add(crewMemberSleepPanel, CREW_SLEEP_PANEL_STRING);
    }

    public void refreshCrewMemberSleepPanel() {
        this.crewMemberSleepPanel.removeAll();

        ButtonGroup selectedMemberBtnGroup = new ButtonGroup();
        JPanel membersPanel = getGridSelectMembers(selectedMemberBtnGroup, "tiredness");
        crewMemberSleepPanel.add(membersPanel);

        JPanel sleepBtnPanel = new JPanel();
        sleepBtnPanel.setLayout(null);
        crewMemberSleepPanel.add(sleepBtnPanel);
        JButton sleepBtn = getActionButton("Sleep");
        sleepBtnPanel.add(sleepBtn);

        sleepBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedMemberBtnGroup.getSelection() == null) {
                    JOptionPane.showMessageDialog(null, "Please select a crew member");
                } else {
                    JRadioButton selectedMemberRadio = Funcs.selectedButton(selectedMemberBtnGroup);
                    CrewMember member = (CrewMember) selectedMemberRadio.getClientProperty("CrewMember");
                    String responseString = "Crew member has no actions left";
                    if (member.hasActionsLeft()) {
                        if (!member.canSleep()) {
                            responseString = "Crew member not tired but has still slept";
                        } else {
                            member.sleep();
                            responseString = "Crew member's tiredness has decreased by 10 points";
                        }

                        member.removeAction();

                        selectedMemberBtnGroup.clearSelection();
                        if (!member.hasActionsLeft()) {
                            selectedMemberRadio.setEnabled(false);
                            
                            if (isPlayerDefeated()) {
                            	screen.closeWindow(false, "You are out of actions");
                            }
                        }
                    }

                    membersPanel.removeAll();
                    refillCrewPanelAndButtonGroup(membersPanel, selectedMemberBtnGroup, "tiredness");

                    JOptionPane.showMessageDialog(null, responseString);
                }
            }
        });
    }

    public void addRepairShipPanel() {
        this.repairShieldsPanel = new JPanel();
        repairShieldsPanel.setLayout(new BoxLayout(repairShieldsPanel, BoxLayout.Y_AXIS));
        content.add(repairShieldsPanel, REPAIR_SHIP_PANEL_STRING);
    }

    public void refreshRepairShipPanel() {
        repairShieldsPanel.removeAll();

        ButtonGroup selectedMemberBtnGroup = new ButtonGroup();
        JPanel membersPanel = getGridSelectMembers(selectedMemberBtnGroup, "");
        repairShieldsPanel.add(membersPanel);

        JPanel bottomPanel = new JPanel();
        repairShieldsPanel.add(bottomPanel);
        bottomPanel.setLayout(null);
        JButton repairBtn = getActionButton("Repair Ship");
        bottomPanel.add(repairBtn);

        JPanel sleepBtnPanel = new JPanel();
        sleepBtnPanel.setLayout(null);
        crewMemberSleepPanel.add(sleepBtnPanel);
        JButton sleepBtn = getActionButton("Sleep");
        sleepBtnPanel.add(sleepBtn);

        repairBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedMemberBtnGroup.getSelection() == null) {
                    JOptionPane.showMessageDialog(null, "Please select a crew member");
                } else {
                    JRadioButton selectedMemberRadio = Funcs.selectedButton(selectedMemberBtnGroup);
                    CrewMember member = (CrewMember) selectedMemberRadio.getClientProperty("CrewMember");

                    // Repair Shields
                    environment.getSpaceShip().incrementShieldLevel(10);
                    member.removeAction();

                    selectedMemberBtnGroup.clearSelection();
                    if (!member.hasActionsLeft()) {
                        selectedMemberRadio.setEnabled(false);
                        
                        if (isPlayerDefeated()) {
                        	screen.closeWindow(false, "You are out of actions");
                        }
                    }
                    
                    JOptionPane.showMessageDialog(null, "Space Ship shield health is now: " + environment.getSpaceShip().getShieldHealth());
                }
            }
        });
    }

    private void refreshPlanetsOntoPanel(ButtonGroup buttonGroup, JPanel planetsPanel) {
        planetsPanel.removeAll();
        Planet planet = environment.getPlanet();
        Planet[] allPlanets = planet.getAll();
        for (int i = 0; i < allPlanets.length; i++) {
            if (!allPlanets[i].getName().equals(environment.getCurrentPlanet().getName())) {
                JPanel insidePanel = fillPlanetPanelAndButtonGroup(allPlanets[i], buttonGroup);
                planetsPanel.add(insidePanel);
            }
        }
    }

    private JPanel fillPlanetPanelAndButtonGroup(Planet planet, ButtonGroup buttonGroup) {
        JPanel insidePanel = new JPanel();
        //memberPanel.setBorder(new LineBorder(new Color(119, 119, 119)));
        insidePanel.setLayout(null);

        JRadioButton planetRadioBtn = new JRadioButton(planet.getName() + "    ");
        planetRadioBtn.putClientProperty("Planet", planet);
        planetRadioBtn.setBounds(20, 140, 150, 23);

        JLabel planetImageLabel = new JLabel("");
        planetImageLabel.setBounds(22, 10, 120, 120);
        planetImageLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
        planetImageLabel.setIcon(Funcs.getScaledIcon(Image.getPlanetImagePath(planet), 120, 120));

        buttonGroup.add(planetRadioBtn);
        insidePanel.add(planetImageLabel);
        insidePanel.add(planetRadioBtn);
        return insidePanel;
    }

    public JPanel getGridSelectMembers(ButtonGroup buttonGroup, String labelToShow) {
        JPanel membersPanel = new JPanel(new GridLayout(1, 4));
        refillCrewPanelAndButtonGroup(membersPanel, buttonGroup, labelToShow);
        return membersPanel;
    }

    public void refillCrewPanelAndButtonGroup(JPanel panel, ButtonGroup btnGroup, String labelToShow) {
        panel.removeAll();
        for (CrewMember member: crew.getMembers()) {
            JPanel insidePanel = new JPanel();
            insidePanel.setLayout(null);

            JRadioButton memberRadio = new JRadioButton(member.getName());
            memberRadio.setBounds(20, 140, 150, 23);
            if (!member.hasActionsLeft()) {
                memberRadio.setEnabled(false);
            }

            JLabel memberImageLabel = new JLabel("");
            memberImageLabel.setBounds(22, 10, 120, 120);
            memberImageLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
            memberImageLabel.setIcon(Funcs.getScaledIcon(Image.getCrewMemberImagePath(member), 120, 120));

            if (labelToShow == "tiredness") {
                JLabel tirednessLabel = new JLabel("Tiredness: " + member.getTiredness());
                tirednessLabel.setBounds(22, 170, 150, 23);
                insidePanel.add(tirednessLabel);
            }
            if (labelToShow == "health") {
                JLabel healthLabel = new JLabel("Current Health: " + member.getCurrentHealth());
                healthLabel.setBounds(22, 170, 150, 23);
                insidePanel.add(healthLabel);
                if (spaceOutPost.hasNoMedicalSupplies()) {
                    memberRadio.setEnabled(false);
                }
            }
            if (labelToShow == "hunger") {
                JLabel hungerLabel = new JLabel("Hunger Level: " + member.getHungerLevel());
                hungerLabel.setBounds(22, 170, 150, 23);
                insidePanel.add(hungerLabel);
                if (spaceOutPost.hasNoFoods()) {
                    memberRadio.setEnabled(false);
                }
            }

            memberRadio.putClientProperty("CrewMember", member);
            btnGroup.add(memberRadio);
            insidePanel.add(memberImageLabel);
            insidePanel.add(memberRadio);
            panel.add(insidePanel);
        }

        if ((4 - crew.getNumMembers()) > 0) {
            for (int i = 0; i < (4 - crew.getNumMembers()); i++) {
                JPanel others = new JPanel();
                others.setLayout(null);
                panel.add(others);
            }
        }
    }

    private JButton getActionButton(String name) {
        JButton button = new JButton(name);
        button.setBounds(600, 50, 150, 30);
        return button;
    }

    public ImageIcon getScaledMemberIcon(CrewMember member) {
        return Funcs.getScaledIcon(Image.getCrewMemberImagePath(member), 100, 100);
    } 

    private boolean isPlayerDefeated() {
    	if (environment.hasDaysLeft()) {
    		return false;
    	}
    	
        boolean isDefeated = true;
        for (CrewMember m: crew.getMembers()) {
            if (m.hasActionsLeft()) {
            	isDefeated = false;
            }
        }
        return isDefeated;
    }
}