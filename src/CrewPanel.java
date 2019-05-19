import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

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
        crewDetailsPanel.setLayout(new GridLayout(0, 2, 0, 0));  
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
            
            JLabel memberDescLabel = new JLabel("Description:" + member.getDescription());
            memberDescLabel.setHorizontalAlignment(SwingConstants.LEFT);
            memberDescLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
            memberDescLabel.setBounds(10, 190, 200, 24);
            memberPanel.add(memberDescLabel);

            JLabel memberSpecialtyLabel = new JLabel("Specialty:" + member.getSpecialty());
            memberSpecialtyLabel.setHorizontalAlignment(SwingConstants.LEFT);
            memberSpecialtyLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
            memberSpecialtyLabel.setBounds(10, 210, 190, 24);
            memberPanel.add(memberSpecialtyLabel);
            
            JLabel memberHealthLabel = new JLabel("Health:" + member.getCurrentHealth() + "/" + member.getMaxHealth());
            memberHealthLabel.setHorizontalAlignment(SwingConstants.LEFT);
            memberHealthLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
            memberHealthLabel.setBounds(10, 230, 190, 24);
            memberPanel.add(memberHealthLabel);

            JLabel memberHungerLabel = new JLabel("Hunger level:" + member.getHungerLevel());
            memberHungerLabel.setHorizontalAlignment(SwingConstants.LEFT);
            memberHungerLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
            memberHungerLabel.setBounds(10, 250, 190, 24);
            memberPanel.add(memberHungerLabel);

            JLabel memberTirednessLabel = new JLabel("Tiredness:" + member.getTiredness());
            memberTirednessLabel.setHorizontalAlignment(SwingConstants.LEFT);
            memberTirednessLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
            memberTirednessLabel.setBounds(10, 270, 190, 24);
            memberPanel.add(memberTirednessLabel);

            JLabel actionsLeftLabel = new JLabel("Actions left:" + member.getActions());
            actionsLeftLabel.setHorizontalAlignment(SwingConstants.LEFT);
            actionsLeftLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
            actionsLeftLabel.setBounds(10, 290, 190, 24);
            memberPanel.add(actionsLeftLabel);

            String isSickString = "no";
            if (member.isSick()) {
                isSickString = "yes";
            }
            JLabel isSickLabel = new JLabel("Is sick: " + isSickString);
            isSickLabel.setHorizontalAlignment(SwingConstants.LEFT);
            isSickLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
            isSickLabel.setBounds(10, 310, 190, 24);
            memberPanel.add(isSickLabel);
        }
    }


    private void addNewFoodPanel() {
        this.newFoodPanel = new JPanel();
        content.add(newFoodPanel, CREW_APPLY_FOOD_PANEL_STRING);
    }

    private void refreshNewFoodPanel() {
        newFoodPanel.removeAll();

        ButtonGroup selectedMemberBtnGroup = new ButtonGroup();
        JPanel memberPanel = getCrewSelectPanel(selectedMemberBtnGroup);
        memberPanel.setLayout(new BoxLayout(memberPanel, BoxLayout.Y_AXIS));
        newFoodPanel.add(memberPanel);

        JPanel foodBoxPanel = new JPanel();
        foodBoxPanel.setLayout(new BoxLayout(foodBoxPanel, BoxLayout.Y_AXIS));
        newFoodPanel.add(foodBoxPanel);

        ButtonGroup foodButtonGroup = new ButtonGroup();

        for (Food food : spaceOutPost.getFoods()) {
            JPanel foodPanel = new JPanel();
            JRadioButton foodRadioBtn = new JRadioButton(food.getType() + "(" + food.getCount() + ")");
            foodRadioBtn.putClientProperty("Food", food);
            if (!food.exists()) {
                foodRadioBtn.setEnabled(false);
            }
            foodButtonGroup.add(foodRadioBtn);
            foodPanel.add(foodRadioBtn);
            foodBoxPanel.add(foodPanel);
        }

        JButton applyFoodBtn = new JButton("Apply Food Item");
        newFoodPanel.add(applyFoodBtn);

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
        content.add(newMedicalItemPanel, CREW_APPLY_MEDICAL_PANEL_STRING);
    }

    public void refreshNewMedicalItemPanel() {
        newMedicalItemPanel.removeAll();

        ButtonGroup selectedMemberBtnGroup = new ButtonGroup();
        JPanel memberPanel = getCrewSelectPanel(selectedMemberBtnGroup);
        memberPanel.setLayout(new BoxLayout(memberPanel, BoxLayout.Y_AXIS));
        newMedicalItemPanel.add(memberPanel);

        JPanel medicalBoxPanel = new JPanel();
        medicalBoxPanel.setLayout(new BoxLayout(medicalBoxPanel, BoxLayout.Y_AXIS));
        newMedicalItemPanel.add(medicalBoxPanel);
        ButtonGroup medicalRadioGroup = new ButtonGroup();
        for (MedicalSupply medicalSupply : spaceOutPost.getMedicalSupplies()) {
            JRadioButton medicalRadioBtn = new JRadioButton(medicalSupply.getType() + "(" + medicalSupply.getCount() + ")");
            medicalRadioBtn.putClientProperty("MedicalSupply", medicalSupply);
            if (!medicalSupply.exists()) {
                medicalRadioBtn.setEnabled(false);
            }

            JLabel medicalSupplyImageLabel = new JLabel("");
            medicalSupplyImageLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
            medicalSupplyImageLabel.setBounds(30, 13, 120, 120);
            medicalSupplyImageLabel.setIcon(Funcs.getScaledIcon(Image.getMedicalSupplyImagePath(medicalSupply), 100, 100));

            medicalRadioGroup.add(medicalRadioBtn);
            medicalBoxPanel.add(medicalRadioBtn);
            medicalBoxPanel.add(medicalSupplyImageLabel);
        }

        JButton applyMedicalItemBtn = new JButton("Apply Medical Item");
        newMedicalItemPanel.add(applyMedicalItemBtn);

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
        content.add(newPlanetPanel, NEW_PLANET_PANEL_STRING);
    }

    private void refreshNewPlanetPanel() {
        newPlanetPanel.removeAll();

        ButtonGroup planetButtonGroup = new ButtonGroup();
        JPanel planetsPanel = getSelectPlanetPanel(planetButtonGroup);
        planetsPanel.setLayout(new BoxLayout(planetsPanel, BoxLayout.Y_AXIS));
        newPlanetPanel.add(planetsPanel);

        JPanel membersPanelSplit = new JPanel();
        membersPanelSplit.setLayout(new BoxLayout(membersPanelSplit, BoxLayout.Y_AXIS));
        newPlanetPanel.add(membersPanelSplit);

        ButtonGroup membersBtnGroupOne = new ButtonGroup();
        JPanel membersPanelOne = getCrewSelectPanel(membersBtnGroupOne);
        membersPanelOne.add(new JLabel("Member 1:"));
        membersPanelOne.setLayout(new BoxLayout(membersPanelOne, BoxLayout.X_AXIS));
        membersPanelSplit.add(membersPanelOne);

        ButtonGroup membersBtnGroupTwo = new ButtonGroup();
        JPanel membersPanelTwo = getCrewSelectPanel(membersBtnGroupTwo);
        membersPanelTwo.add(new JLabel("Member 2:"));
        membersPanelTwo.setLayout(new BoxLayout(membersPanelTwo, BoxLayout.X_AXIS));
        membersPanelSplit.add(membersPanelTwo);

        JButton doActionBtn = new JButton("Go");
        newPlanetPanel.add(doActionBtn);

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

                        planetsPanel.removeAll();
                        refreshPlanetsOntoPanel(planetButtonGroup, planetsPanel);

                        membersPanelOne.removeAll();
                        refreshCrewOntoPanel(membersBtnGroupOne, membersPanelOne);
                        membersPanelOne.add(new JLabel("Member 1:"));

                        membersPanelTwo.removeAll();
                        refreshCrewOntoPanel(membersBtnGroupTwo, membersPanelTwo);
                        membersPanelTwo.add(new JLabel("Member 2:"));
                        
                        JOptionPane.showMessageDialog(null, "You have successfully pioleted to: " + planet.getName());
                    }
                }
            }
        });
    }

    public void addCrewMemberSleepPanel() {
        this.crewMemberSleepPanel = new JPanel();
        content.add(crewMemberSleepPanel, CREW_SLEEP_PANEL_STRING);
    }

    public void refreshCrewMemberSleepPanel() {
        this.crewMemberSleepPanel.removeAll();

        ButtonGroup selectedMemberBtnGroup = new ButtonGroup();
        JPanel memberPanel = getCrewSelectPanel(selectedMemberBtnGroup);

        memberPanel.setLayout(new BoxLayout(memberPanel, BoxLayout.Y_AXIS));
        crewMemberSleepPanel.add(memberPanel);

        JButton sleepBtn = new JButton("Sleep");
        crewMemberSleepPanel.add(sleepBtn);
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

                    JOptionPane.showMessageDialog(null, responseString);
                }
            }
        });
    }

    public ImageIcon getScaledMemberIcon(CrewMember member) {
        return Funcs.getScaledIcon(Image.getCrewMemberImagePath(member), 100, 100);
    } 

    public void addRepairShipPanel() {
        this.repairShieldsPanel = new JPanel();
        content.add(repairShieldsPanel, REPAIR_SHIP_PANEL_STRING);
    }

    public void refreshRepairShipPanel() {
        repairShieldsPanel.removeAll();

        ButtonGroup selectedMemberBtnGroup = new ButtonGroup();
        JPanel memberPanel = getCrewSelectPanel(selectedMemberBtnGroup);

        repairShieldsPanel.add(memberPanel);

        JButton repairBtn = new JButton("Repair Ship");
        repairShieldsPanel.add(repairBtn);

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

    private void fillMemberPanelAndButtonGroup(CrewMember member, ButtonGroup buttonGroup, JPanel panel) {
        JRadioButton memberRadio = new JRadioButton(member.getName());
        memberRadio.putClientProperty("CrewMember", member);
        if (!member.hasActionsLeft()) {
            memberRadio.setEnabled(false);
        }

        JLabel memberImageLabel = new JLabel("");
        memberImageLabel.setBorder(new LineBorder(new Color(0, 0, 0)));

        // Rescale what we already have
        ImageIcon smallerImg = getScaledMemberIcon(member);
        memberImageLabel.setIcon(smallerImg);

        buttonGroup.add(memberRadio);
        panel.add(memberRadio);
        panel.add(memberImageLabel);
    }

    private JPanel getCrewSelectPanel(ButtonGroup buttonGroup) {
        JPanel memberPanel = new JPanel();
        for (CrewMember member: crew.getMembers()) {
            fillMemberPanelAndButtonGroup(member, buttonGroup, memberPanel);
        }

        return memberPanel;
    }

    private void refreshCrewOntoPanel(ButtonGroup buttonGroup, JPanel panel) {
        panel.removeAll();
        for (CrewMember member: crew.getMembers()) {
            fillMemberPanelAndButtonGroup(member, buttonGroup, panel);
        }
    }
    
    private void fillPlanetPanelAndButtonGroup(Planet planet, ButtonGroup buttonGroup, JPanel panel) {
        JLabel planetImageLabel = new JLabel("");
        planetImageLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
        planetImageLabel.setIcon(new ImageIcon(Image.getPlanetImagePath(planet)));
        JRadioButton planetRadioBtn = new JRadioButton(planet.getName() + "    ");
        planetRadioBtn.putClientProperty("Planet", planet);
        buttonGroup.add(planetRadioBtn);
        panel.add(planetRadioBtn);
        panel.add(planetImageLabel);
    }

    private JPanel getSelectPlanetPanel(ButtonGroup buttonGroup) {
        Planet planet = environment.getPlanet();
        Planet[] allPlanets = planet.getAll();

        JPanel planetsPanel = new JPanel();
        for (int i = 0; i < allPlanets.length; i++) {
            if (!allPlanets[i].getName().equals(environment.getCurrentPlanet().getName())) {
                fillPlanetPanelAndButtonGroup(allPlanets[i], buttonGroup, planetsPanel);
            }
        }
        return planetsPanel;
    }

    private void refreshPlanetsOntoPanel(ButtonGroup buttonGroup, JPanel planetsPanel) {
        planetsPanel.removeAll();
        Planet planet = environment.getPlanet();
        Planet[] allPlanets = planet.getAll();
        for (int i = 0; i < allPlanets.length; i++) {
            if (!allPlanets[i].getName().equals(environment.getCurrentPlanet().getName())) {
                fillPlanetPanelAndButtonGroup(allPlanets[i], buttonGroup, planetsPanel);
            }
        }
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