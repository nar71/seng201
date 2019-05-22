import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;

public class CrewPanel extends JPanel {

    /**
     * A string representation of the crew status panel, used for Card Layout.
     */
    private static final String CREW_STATUS_PANEL_STRING = "CREW_STATUS_PANEL";

    /**
     * A string representation of the repair ship panel, used for Card Layout.
     */
    private static final String REPAIR_SHIP_PANEL_STRING = "REPAIR_SHIP_PANEL";

    /**
     * A string representation of the new planet panel, used for Card Layout.
     */
    private static final String NEW_PLANET_PANEL_STRING = "PILOT_NEW_PLANET_PANEL";

    /**
     * A string representation of the crew sleep panel, used for Card Layout.
     */
    private static final String CREW_SLEEP_PANEL_STRING = "CREW_SLEEP";

    /**
     * A string representation of the apply food panel, used for Card Layout.
     */
    private static final String CREW_APPLY_FOOD_PANEL_STRING = "APPLY_FOOD";

    /**
     * A string representation of the apply medical supply panel, used for Card Layout.
     */
    private static final String CREW_APPLY_MEDICAL_PANEL_STRING = "APPLY_MEDICAL";

    /**
     * The Home Screen of the game (the actual game play). Used to produce the defeat/victory screens.
     */
    private HomeScreen screen;

    /**
     * The main game, the Game Environment holds all the data as the user goes through the game.
     * It holds the crew members, space out post, space ship, medical supplies etc.
     */ 
    private GameEnvironment environment;

    /**
     * The Crew. Holds an array list of the crew members and can make these members do actions.
     */
    private Crew crew;;

    /**
     * The Space Out Post. Holds all medical supplies and foods.
     */
    private SpaceOutPost spaceOutPost;

    /**
     * A card layout used by content.
     */
    private CardLayout cardLayout;

    /*
     * The main content panel, which is used for every different card.
     */
    private JPanel content;

    /**
     * The sidebar which holds all the buttons for crew actions.
     */
    private JPanel sideBar;

    /**
     * A panel component of content.
     */
    private JPanel crewDetailsPanel;

    /**
     * A panel component of content.
     */
    private JPanel repairShieldsPanel;

    /**
     * A panel component of content.
     */
    private JPanel newFoodPanel;

    /**
     * A panel component of content.
     */
    private JPanel newMedicalItemPanel;

    /**
     * A panel component of content.
     */
    private JPanel newPlanetPanel;

    /**
     * A panel component of content.
     */
    private JPanel crewMemberSleepPanel;

    /**
     * A button of sidebar.
     */
    private JButton crewDetailsBtn;

    /**
     * A button of sidebar.
     */
    private JButton crewMemberSleepBtn;

    /**
     * A button of sidebar.
     */
    private JButton repairShipBtn;

    /**
     * A button of sidebar.
     */
    private JButton pilotNewShipBtn;

    /**
     * A button of sidebar.
     */
    private JButton applyFoodBtn;

    /**
     * A button of sidebar.
     */
    private JButton applyMedItemBtn;

    /**
     * Crew Panel constructor
     * @param screen The main game play screen.
     */
    CrewPanel(HomeScreen screen) {
        super();

        this.screen = screen;
        this.environment = screen.environment;
        this.crew = environment.getCrew();
        this.spaceOutPost = environment.getSpaceOutPost();

        init();
    }

    /** 
     * Initialize the layout parameters and all all the panels.
     */
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

    /**
     * Shows the crew status card and makes all the other buttons disabled
     * Used in Home Screen for every time the View Crew Details event handler is used.
     */
    public void showCrewStatusCard() {
        cardLayout.show(content, CREW_STATUS_PANEL_STRING);
        refreshCrewDetailsPanel();     
        applyFoodBtn.setEnabled(true);
        crewDetailsBtn.setEnabled(false);
        pilotNewShipBtn.setEnabled(true);
        applyMedItemBtn.setEnabled(true);
        crewMemberSleepBtn.setEnabled(true);
        repairShipBtn.setEnabled(true);
    }

    /**
     * Shows the pilot to new planet card and makes all the other buttons disabled
     * Used in Home Screen for every time the New Planet event handler is used.
     */
    public void showPilotNewPlanetCard() {
        cardLayout.show(content, NEW_PLANET_PANEL_STRING);
        refreshNewPlanetPanel();
        applyFoodBtn.setEnabled(true);
        crewDetailsBtn.setEnabled(true);
        pilotNewShipBtn.setEnabled(false);
        applyMedItemBtn.setEnabled(true);
        crewMemberSleepBtn.setEnabled(true);
        repairShipBtn.setEnabled(true);
    }

    /**
     * Creates sidebar panel and adds buttons onto it
     */
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

    /**
     * Adds action listeners for all buttons on the sidebar
     * On each handler, a new card is showed.
     */
    public void addActionListeners() {
        crewDetailsBtn.setEnabled(false);
        crewDetailsBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
            public void actionPerformed(ActionEvent e) {
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
            public void actionPerformed(ActionEvent e) {
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
            public void actionPerformed(ActionEvent e) {
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

    /**
     * Adds crew details panel to content panel.
     */
    private void addCrewDetailsPanel() {
        this.crewDetailsPanel = new JPanel();
        content.add(crewDetailsPanel, CREW_STATUS_PANEL_STRING);
        crewDetailsPanel.setLayout(new GridLayout(0, 4, 0, 0));  
    }

    /**
     * Refreshs crew details panel with the latest data.
     */
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

            JLabel lblDecrement = new JLabel("-" + member.getDecrement() + " per day");
            lblDecrement.setHorizontalAlignment(SwingConstants.LEFT);
            lblDecrement.setFont(new Font("Tahoma", Font.PLAIN, 15));
            lblDecrement.setBounds(10, 380, 190, 24);
            memberPanel.add(lblDecrement);

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

    /**
     * Adds crew member sleep panel to content panel.
     */
    public void addCrewMemberSleepPanel() {
        this.crewMemberSleepPanel = new JPanel();
        crewMemberSleepPanel.setLayout(new GridLayout(2, 2, 0, 0));
        content.add(crewMemberSleepPanel, CREW_SLEEP_PANEL_STRING);
    }

    /**
     * Refreshs crew details panel with the latest data.
     */
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

    /**
     * Adds apply food panel to content panel.
     */
    private void addNewFoodPanel() {
        this.newFoodPanel = new JPanel();
        newFoodPanel.setLayout(new BoxLayout(newFoodPanel, BoxLayout.Y_AXIS));
        content.add(newFoodPanel, CREW_APPLY_FOOD_PANEL_STRING);
    }

    /**
     * Refreshs apply food panel with the latest data.
     */
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

    /**
     * Adds apply medical supply to content panel.
     */
    private void addNewMedicalItemPanel() {
        this.newMedicalItemPanel = new JPanel();
        newMedicalItemPanel.setLayout(new BoxLayout(newMedicalItemPanel, BoxLayout.Y_AXIS));
        content.add(newMedicalItemPanel, CREW_APPLY_MEDICAL_PANEL_STRING);
    }

    /**
     * Refreshs apply medical supply panel with the latest data.
     */
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

    /**
     * Adds new planet panel to content panel.
     */
    private void addNewPlanetPanel() {
        this.newPlanetPanel = new JPanel();
        newPlanetPanel.setLayout(new BoxLayout(newPlanetPanel, BoxLayout.Y_AXIS));
        content.add(newPlanetPanel, NEW_PLANET_PANEL_STRING);
    }

    /**
     * Refreshs crew details panel with the latest data.
     */
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

    /**
     * Adds repair ship panel with the latest data.
     */
    public void addRepairShipPanel() {
        this.repairShieldsPanel = new JPanel();
        repairShieldsPanel.setLayout(new BoxLayout(repairShieldsPanel, BoxLayout.Y_AXIS));
        content.add(repairShieldsPanel, REPAIR_SHIP_PANEL_STRING);
    }

    /**
     * Refreshs crew details panel with the latest data.
     */
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
        JButton repairBtn = new JButton("Repair Ship");
        repairBtn.setBounds(600, 50, 150, 30);
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

    /**
     * Refreshs all planets onto given panel and button group.
     * @param buttonGroup A specified button group.
     * @param panel A specified panel.
     */
    private void refreshPlanetsOntoPanel(ButtonGroup buttonGroup, JPanel panel) {
        panel.removeAll();
        Planet planet = environment.getPlanet();
        Planet[] allPlanets = planet.getAll();
        for (int i = 0; i < allPlanets.length; i++) {
            if (!allPlanets[i].getName().equals(environment.getCurrentPlanet().getName())) {
                JPanel insidePanel = fillPlanetPanelAndButtonGroup(allPlanets[i], buttonGroup);
                panel.add(insidePanel);
            }
        }
    }

    /**
     * Helper method for filling a given panel up with planet data.
     * @param planet A planet object for which data is filled onto the panel.
     * @param buttonGroup A specified button group.
     * @return panel The filled panel with planet data.
     */
    private JPanel fillPlanetPanelAndButtonGroup(Planet planet, ButtonGroup buttonGroup) {
        JPanel panel = new JPanel();
        //memberPanel.setBorder(new LineBorder(new Color(119, 119, 119)));
        panel.setLayout(null);

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
        panel.add(lblPlanetImage);
        panel.add(planetRadioBtn);
        return panel;
    }

    /**
     * A helper method for getting the crew members in a grid layout with labels and image.
     * @param buttonGroup A desired button group.
     * @param labelToShow The label to show as used for the apply medical item, apply food, repair shield
     *                       and go to new planet actions.
     * @return panel The new filled panel.
     */
    public JPanel getGridSelectMembers(ButtonGroup buttonGroup, String labelToShow) {
        JPanel panel = new JPanel(new GridLayout(1, 4));
        refillCrewPanelAndButtonGroup(panel, buttonGroup, labelToShow);
        return panel;
    }

    /**
     * Helper method for filling a given panel up with crew member data.
     * @param panel The desired panel to fill.
     * @param buttonGroup A specified button group.
     * @param labelToShow The label wanted to show.
     */
    public void refillCrewPanelAndButtonGroup(JPanel panel, ButtonGroup buttonGroup, String labelToShow) {
        panel.removeAll();
        for (CrewMember member: crew.getMembers()) {
            JPanel memberPanel = new JPanel();
            memberPanel.setLayout(null);

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
                lblHealth.setBounds(22, 170, 200, 23);
                lblHealth.setFont(new Font("Tahoma", Font.PLAIN, 15));
                memberPanel.add(lblHealth);
                if (spaceOutPost.hasNoMedicalSupplies()) {
                    memberRadio.setEnabled(false);
                }
            } else if (labelToShow == "hunger") {
                JLabel lblHunger = new JLabel("Hunger Level: " + member.getHungerLevel());
                lblHunger.setBounds(22, 170, 200, 23);
                lblHunger.setFont(new Font("Tahoma", Font.PLAIN, 14));
                memberPanel.add(lblHunger);
                if (spaceOutPost.hasNoFoods()) {
                    memberRadio.setEnabled(false);
                }
            } else if (labelToShow == "shield") {
                JLabel lblShieldIncrement = new JLabel("+ " + member.getShieldIncrement() + " shield health");
                lblShieldIncrement.setBounds(22, 170, 150, 23);
                lblShieldIncrement.setFont(new Font("Tahoma", Font.PLAIN, 14));
                memberPanel.add(lblShieldIncrement);
                if (environment.getSpaceShip().isFullHealth()) {
                    memberRadio.setEnabled(false);
                }
            } else if (labelToShow == "planet") {
                if (!environment.getSpaceShip().canTravel()) {
                    memberRadio.setEnabled(false);
                }
            }

            buttonGroup.add(memberRadio);
            memberPanel.add(lblMemberImage);
            memberPanel.add(memberRadio);
            panel.add(memberPanel);
        }

        if ((4 - crew.getNumMembers()) > 0) {
            for (int i = 0; i < (4 - crew.getNumMembers()); i++) {
                JPanel otherMemberPanel = new JPanel();
                otherMemberPanel.setLayout(null);
                panel.add(otherMemberPanel);
            }
        }
    }

    /**
     * Checks if game is over - all actions used on the last day.
     * @return boolean True on defeat False on not
     */
    private boolean isPlayerDefeated() {
        return environment.isDefeated();
    }
}