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
                refreshRepairShipPanel();
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
            memberPanel.setBorder(new LineBorder(new Color(190, 190, 190)));
            memberPanel.setLayout(null);
            crewDetailsPanel.add(memberPanel);
            
            JLabel lblMemberImage = new JLabel("");
            lblMemberImage.setBorder(new LineBorder(new Color(0, 0, 0)));
            lblMemberImage.setBounds(30, 13, 120, 120);
            lblMemberImage.setIcon(Funcs.getScaledIcon(Image.getCrewMemberImagePath(member), 120, 120));
            memberPanel.add(lblMemberImage);
            
            JLabel lblMemberName = new JLabel(member.getName());
            lblMemberName.setHorizontalAlignment(SwingConstants.CENTER);
            lblMemberName.setFont(new Font("Tahoma", Font.PLAIN, 18));
            lblMemberName.setBounds(10, 150, 160, 25);
            memberPanel.add(lblMemberName);

            JLabel lblMemberType = new JLabel(String.format("%s", member.getType()));
            lblMemberType.setFont(new Font("Tahoma", Font.PLAIN, 18));
            lblMemberType.setHorizontalAlignment(SwingConstants.CENTER);
            lblMemberType.setBounds(10, 170, 160, 25);
            memberPanel.add(lblMemberType);

            JLabel lblMemberSpecialty = new JLabel("Specialty:" + member.getSpecialty());
            lblMemberSpecialty.setHorizontalAlignment(SwingConstants.LEFT);
            lblMemberSpecialty.setFont(new Font("Tahoma", Font.PLAIN, 15));
            lblMemberSpecialty.setBounds(10, 200, 190, 24);
            memberPanel.add(lblMemberSpecialty);
            
            JLabel lblMemberHealth = new JLabel("Health:" + member.getCurrentHealth() + "/" + member.getMaxHealth());
            lblMemberHealth.setHorizontalAlignment(SwingConstants.LEFT);
            lblMemberHealth.setFont(new Font("Tahoma", Font.PLAIN, 15));
            lblMemberHealth.setBounds(10, 230, 190, 24);
            memberPanel.add(lblMemberHealth);

            JLabel lblMemberHunger = new JLabel("Hunger level:" + member.getHungerLevel());
            lblMemberHunger.setHorizontalAlignment(SwingConstants.LEFT);
            lblMemberHunger.setFont(new Font("Tahoma", Font.PLAIN, 15));
            lblMemberHunger.setBounds(10, 260, 190, 24);
            memberPanel.add(lblMemberHunger);

            JLabel lblMemberTiredness = new JLabel("Tiredness:" + member.getTiredness());
            lblMemberTiredness.setHorizontalAlignment(SwingConstants.LEFT);
            lblMemberTiredness.setFont(new Font("Tahoma", Font.PLAIN, 15));
            lblMemberTiredness.setBounds(10, 290, 190, 24);
            memberPanel.add(lblMemberTiredness);

            JLabel lblActions = new JLabel("Actions left:" + member.getActions());
            lblActions.setHorizontalAlignment(SwingConstants.LEFT);
            lblActions.setFont(new Font("Tahoma", Font.PLAIN, 15));
            lblActions.setBounds(10, 320, 190, 24);
            memberPanel.add(lblActions);

            String isSickString = "no";
            if (member.isSick()) {
                isSickString = "yes";
            }
            JLabel lblSick = new JLabel("Is sick: " + isSickString);
            lblSick.setHorizontalAlignment(SwingConstants.LEFT);
            lblSick.setFont(new Font("Tahoma", Font.PLAIN, 15));
            lblSick.setBounds(10, 350, 190, 24);

            JLabel lblAppliedMeds = new JLabel("Applied Meds:");
            lblAppliedMeds.setHorizontalAlignment(SwingConstants.LEFT);
            lblAppliedMeds.setBounds(10, 370, 200, 100);
            lblAppliedMeds.setFont(new Font("Tahoma", Font.BOLD, 15));
            memberPanel.add(lblAppliedMeds);

            int ycord = 390;
            for (MedicalSupply medicalSupply: member.getAppliedMedicalSupplies()) {
                JLabel lblMedicalSupply = new JLabel(medicalSupply.getType());
                memberPanel.add(lblMedicalSupply);
                lblMedicalSupply.setBounds(20, ycord, 200, 100);
                ycord+=12;
            }

            JLabel lblAppliedFoods = new JLabel("Applied Foods:");
            lblAppliedFoods.setHorizontalAlignment(SwingConstants.LEFT);
            lblAppliedFoods.setBounds(10, 460, 200, 100);
            lblAppliedFoods.setFont(new Font("Tahoma", Font.BOLD, 15));
            memberPanel.add(lblAppliedFoods);

            ycord = 480;
            for (Food food: member.getAppliedFoods()) {
                JLabel lblFood = new JLabel(food.getType());
                memberPanel.add(lblFood);
                lblFood.setBounds(20, ycord, 200, 100);
                ycord+=12;
            }

            memberPanel.add(lblSick);
        }

        if ((4 - crew.getNumMembers()) > 0) {
            for (int i = 0; i < (4 - crew.getNumMembers()); i++) {
                JPanel others = new JPanel();
                others.setLayout(null);
                crewDetailsPanel.add(others);
            }
        }
    }

    public void addCrewMemberSleepPanel() {
        this.crewMemberSleepPanel = new JPanel();
        crewMemberSleepPanel.setLayout(new BoxLayout(crewMemberSleepPanel, BoxLayout.Y_AXIS));
        content.add(crewMemberSleepPanel, CREW_SLEEP_PANEL_STRING);
    }

    public void refreshCrewMemberSleepPanel() {
        crewMemberSleepPanel.removeAll();

        for (CrewMember member: crew.getMembers()) {
            JPanel panel = new JPanel();
            panel.setLayout(null);
            
            JLabel lblMember = new JLabel(member.getName() + " (Tiredness: " + member.getTiredness() + ")");
            lblMember.setBounds(80, 0, 250, 30);
            lblMember.setFont(new Font("Tahoma", Font.PLAIN, 16));
            panel.add(lblMember);

            JLabel lblMemberImage = new JLabel("");
            lblMemberImage.setIcon(Funcs.getScaledIcon(Image.getCrewMemberImagePath(member), 150, 150));
            lblMemberImage.setBorder(new LineBorder(new Color(0, 0, 0)));
            lblMemberImage.setBounds(83, 33, 150, 150);
            panel.add(lblMemberImage);
            
            JButton sleepBtn = new JButton("Sleep");
            sleepBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
            sleepBtn.setBounds(83, 194, 150, 30);
            if (!member.hasActionsLeft()) {
                sleepBtn.setEnabled(false);
            }

            sleepBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg5) {
                    if (member.getTiredness() < 100) {
                        crew.makeMemberSleep(member);
                        lblMember.setText(member.getName() + " (Tiredness: " + member.getTiredness() + ")");
                        if (!member.hasActionsLeft()) {
                            sleepBtn.setEnabled(false);
                        }
                    }
                }
            });
            panel.add(sleepBtn);
            crewMemberSleepPanel.add(panel);
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
            foodRadioBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
            foodRadioBtn.putClientProperty("Food", food);
            foodRadioBtn.setBounds(20, 130, 150, 23);
            if (!food.exists()) {
                foodRadioBtn.setEnabled(false);
            }

            JLabel lblFoodImage = new JLabel("");
            lblFoodImage.setBounds(22, 10, 100, 100);
            lblFoodImage.setBorder(new LineBorder(new Color(0, 0, 0)));
            lblFoodImage.setIcon(Funcs.getScaledIcon(Image.getFoodImagePath(food), 100, 100));

            foodButtonGroup.add(foodRadioBtn);
            foodPanel.add(foodRadioBtn);
            foodPanel.add(lblFoodImage);
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
                    boolean isDefeated = false;
                    if (!member.hasActionsLeft()) {
                        selectedMemberRadio.setEnabled(false);
                        
                        if (isPlayerDefeated()) {
                            isDefeated = true;
                        	screen.closeWindow(false, "You are out of actions");
                        }
                    }

                    foodButtonGroup.clearSelection();
                    if (!spaceOutPost.foodExists(food.getType())) {
                        selectedFoodRadio.setEnabled(false);
                    }
                    selectedFoodRadio.setText(food.getType() + "(" + food.getCount() + ")");

                    refillCrewPanelAndButtonGroup(membersPanel, selectedMemberBtnGroup, "hunger");

                    if (!isDefeated) {
                        JOptionPane.showMessageDialog(null, food.getType() + " successfully applied");
                    }
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

        JPanel medicalBoxPanel = new JPanel(new GridLayout(1, 6));
        newMedicalItemPanel.add(medicalBoxPanel);

        ButtonGroup medicalRadioGroup = new ButtonGroup();

        for (MedicalSupply medicalSupply : spaceOutPost.getMedicalSupplies()) {
            JPanel medicalSupplyPanel = new JPanel();
            medicalSupplyPanel.setLayout(null);
            JRadioButton medicalRadioBtn = new JRadioButton(medicalSupply.getType() + "(" + medicalSupply.getCount() + ")");
            medicalRadioBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
            medicalRadioBtn.putClientProperty("MedicalSupply", medicalSupply);
            medicalRadioBtn.setBounds(10, 130, 200, 23);
            if (!medicalSupply.exists()) {
                medicalRadioBtn.setEnabled(false);
            }

            JLabel lblMedicalSupplyImage = new JLabel("");
            lblMedicalSupplyImage.setBounds(22, 10, 100, 100);
            lblMedicalSupplyImage.setBorder(new LineBorder(new Color(0, 0, 0)));
            lblMedicalSupplyImage.setIcon(Funcs.getScaledIcon(Image.getMedicalSupplyImagePath(medicalSupply), 100, 100));

            medicalRadioGroup.add(medicalRadioBtn);
            medicalSupplyPanel.add(medicalRadioBtn);
            medicalSupplyPanel.add(lblMedicalSupplyImage);
            medicalBoxPanel.add(medicalSupplyPanel);
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
                    boolean isDefeated = false;
                    if (!member.hasActionsLeft()) {
                        selectedMemberRadio.setEnabled(false);
                        
                        if (isPlayerDefeated()) {
                        	screen.closeWindow(false, "You are out of actions");
                            isDefeated = true;
                        }
                    }

                    medicalRadioGroup.clearSelection();
                    if (!spaceOutPost.medicalSupplyExists(medicalSupply.getType())) {
                        selectedMedicalSupplyRadio.setEnabled(false);
                    }
                    selectedMedicalSupplyRadio.setText(medicalSupply.getType() + "(" + medicalSupply.getCount() + ")");

                    refillCrewPanelAndButtonGroup(membersPanel, selectedMemberBtnGroup, "health");

                    if (!isDefeated) {
                        JOptionPane.showMessageDialog(null, responseString);
                    }
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

        JPanel lblChoosePlanetPanel = new JPanel();
        newPlanetPanel.add(lblChoosePlanetPanel);
        lblChoosePlanetPanel.setLayout(null);
        
        JLabel lblChooseAPlanet = new JLabel("Choose a Planet ");
        lblChooseAPlanet.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblChooseAPlanet.setBounds(24, 12, 300, 15);
        lblChoosePlanetPanel.add(lblChooseAPlanet);

        JPanel planets = new JPanel(new GridLayout(1,3));
        planets.setPreferredSize(new Dimension(1000, 120));
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

        JPanel lblChooseMemberOnePanel = new JPanel();
        newPlanetPanel.add(lblChooseMemberOnePanel);
        lblChooseMemberOnePanel.setLayout(null);
        
        JLabel lblChooseMemberTwo = new JLabel("Choose Member One");
        lblChooseMemberTwo.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblChooseMemberTwo.setBounds(12, 12, 300, 15);
        lblChooseMemberOnePanel.add(lblChooseMemberTwo);

        ButtonGroup membersBtnGroupOne = new ButtonGroup();
        JPanel membersPanelOne = getGridSelectMembers(membersBtnGroupOne, "planet");
        membersPanelOne.setPreferredSize(new Dimension(1000, 120));
        newPlanetPanel.add(membersPanelOne);

        JPanel lblChooseMemberTwoPanel = new JPanel();
        newPlanetPanel.add(lblChooseMemberTwoPanel);
        lblChooseMemberTwoPanel.setLayout(null);
        
        JLabel lblChooseMemberOne = new JLabel("Choose Member Two");
        lblChooseMemberOne.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblChooseMemberOne.setBounds(12, 12, 300, 15);
        lblChooseMemberTwoPanel.add(lblChooseMemberOne);

        ButtonGroup membersBtnGroupTwo = new ButtonGroup();
        JPanel membersPanelTwo = getGridSelectMembers(membersBtnGroupTwo, "planet");
        membersPanelTwo.setPreferredSize(new Dimension(1000, 120));
        newPlanetPanel.add(membersPanelTwo);

        JPanel goBtnPanel = new JPanel();
        goBtnPanel.setPreferredSize(new Dimension(1000, 20));
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
                        boolean isDefeated = false;
                        if (!selectedMemberOne.hasActionsLeft() && !selectedMemberTwo.hasActionsLeft()) {
    	                    if (isPlayerDefeated()) {
                                isDefeated = true;
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
                        refillCrewPanelAndButtonGroup(membersPanelOne, membersBtnGroupOne, "planet");

                        membersPanelTwo.removeAll();
                        refillCrewPanelAndButtonGroup(membersPanelTwo, membersBtnGroupTwo, "planet");

                        if (!isDefeated) {
                            JOptionPane.showMessageDialog(null, "You have successfully pioleted to: " + planet.getName());
                        }
                    }
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

        JPanel lblShieldHealthPanel = new JPanel();
        repairShieldsPanel.add(lblShieldHealthPanel);
        lblShieldHealthPanel.setLayout(null);

        JLabel lblShieldHealth = new JLabel("Shield Health: " + environment.getSpaceShip().getShieldHealth());
        lblShieldHealth.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblShieldHealth.setBounds(20, 20, 300, 15);
        lblShieldHealthPanel.add(lblShieldHealth);

        JPanel lblChooseMemberPanel = new JPanel();
        repairShieldsPanel.add(lblChooseMemberPanel);
        lblChooseMemberPanel.setLayout(null);

        JLabel lblChooseMember = new JLabel("Choose Member to repair ship");
        lblChooseMember.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblChooseMember.setBounds(20, 20, 300, 15);
        lblChooseMemberPanel.add(lblChooseMember);

        ButtonGroup selectedMemberBtnGroup = new ButtonGroup();
        JPanel membersPanel = getGridSelectMembers(selectedMemberBtnGroup, "shield");
        membersPanel.setPreferredSize(new Dimension(1000, 300));
        repairShieldsPanel.add(membersPanel);

        JPanel bottomPanel = new JPanel();
        repairShieldsPanel.add(bottomPanel);
        bottomPanel.setLayout(null);
        bottomPanel.setPreferredSize(new Dimension(1000, 100));
        JButton repairBtn = getActionButton("Repair Ship");
        bottomPanel.add(repairBtn);

        repairBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedMemberBtnGroup.getSelection() == null) {
                    JOptionPane.showMessageDialog(null, "Please select a crew member");
                } else {
                    JRadioButton selectedMemberRadio = Funcs.selectedButton(selectedMemberBtnGroup);
                    CrewMember member = (CrewMember) selectedMemberRadio.getClientProperty("CrewMember");

                    // Repair Shields
                    //environment.getSpaceShip().incrementShieldLevel(10);
                    environment.getSpaceShip().repair(member);
                    member.removeAction();

                    lblShieldHealth.setText("Shield Health: " + environment.getSpaceShip().getShieldHealth());

                    selectedMemberBtnGroup.clearSelection();
                    boolean isDefeated = false;
                    if (!member.hasActionsLeft() || environment.getSpaceShip().isFullHealth()) {
                        selectedMemberRadio.setEnabled(false);
                        
                        if (isPlayerDefeated()) {
                            isDefeated = true;
                        	screen.closeWindow(false, "You are out of actions");
                        }
                    }

                    refillCrewPanelAndButtonGroup(membersPanel, selectedMemberBtnGroup, "shield");
                    
                    if (!isDefeated) {
                        JOptionPane.showMessageDialog(null, "Space Ship shield health is now: " + environment.getSpaceShip().getShieldHealth());
                    }
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
        planetRadioBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        planetRadioBtn.putClientProperty("Planet", planet);
        planetRadioBtn.setBounds(20, 140, 150, 23);

        if (!environment.getSpaceShip().canTravel()) {
            planetRadioBtn.setEnabled(false);
        }

        JLabel lblPlanetImage = new JLabel("");
        lblPlanetImage.setBounds(22, 10, 120, 120);
        lblPlanetImage.setBorder(new LineBorder(new Color(0, 0, 0)));
        lblPlanetImage.setIcon(Funcs.getScaledIcon(Image.getPlanetImagePath(planet), 120, 120));

        buttonGroup.add(planetRadioBtn);
        insidePanel.add(lblPlanetImage);
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
            memberRadio.setFont(new Font("Tahoma", Font.PLAIN, 16));
            memberRadio.setBounds(20, 140, 150, 23);
            memberRadio.putClientProperty("CrewMember", member);
            if (!member.hasActionsLeft()) {
                memberRadio.setEnabled(false);
            }

            JLabel lblMemberImage = new JLabel("");
            lblMemberImage.setBounds(22, 10, 120, 120);
            lblMemberImage.setBorder(new LineBorder(new Color(0, 0, 0)));
            lblMemberImage.setIcon(Funcs.getScaledIcon(Image.getCrewMemberImagePath(member), 120, 120));

            if (labelToShow == "health") {
                JLabel lblHealth = new JLabel("Current Health: " + member.getCurrentHealth());
                lblHealth.setBounds(22, 170, 150, 23);
                lblHealth.setFont(new Font("Tahoma", Font.PLAIN, 16));
                insidePanel.add(lblHealth);
                if (spaceOutPost.hasNoMedicalSupplies()) {
                    memberRadio.setEnabled(false);
                }
            } else if (labelToShow == "hunger") {
                JLabel lblHunger = new JLabel("Hunger Level: " + member.getHungerLevel());
                lblHunger.setBounds(22, 170, 150, 23);
                lblHunger.setFont(new Font("Tahoma", Font.PLAIN, 16));
                insidePanel.add(lblHunger);
                if (spaceOutPost.hasNoFoods()) {
                    memberRadio.setEnabled(false);
                }
            } else if (labelToShow == "shield") {
                if (environment.getSpaceShip().isFullHealth()) {
                    memberRadio.setEnabled(false);
                }
            } else if (labelToShow == "planet") {
                if (!environment.getSpaceShip().canTravel()) {
                    memberRadio.setEnabled(false);
                }
            }

            btnGroup.add(memberRadio);
            insidePanel.add(lblMemberImage);
            insidePanel.add(memberRadio);
            panel.add(insidePanel);
        }

        if ((4 - crew.getNumMembers()) > 0) {
            for (int i = 0; i < (4 - crew.getNumMembers()); i++) {
                JPanel otherMemberPanel = new JPanel();
                otherMemberPanel.setLayout(null);
                panel.add(otherMemberPanel);
            }
        }
    }

    private JButton getActionButton(String name) {
        JButton btn = new JButton(name);
        btn.setBounds(600, 50, 150, 30);
        return btn;
    }

    public ImageIcon getScaledMemberIcon(CrewMember member) {
        return Funcs.getScaledIcon(Image.getCrewMemberImagePath(member), 100, 100);
    } 

    private boolean isPlayerDefeated() {
        return environment.isDefeated();
    }
}