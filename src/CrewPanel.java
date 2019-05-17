import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

public class CrewPanel extends JPanel {
    private GameEnvironment environment;

    private Crew crew;;

    private SpaceOutPost spaceOutPost;

    private CardLayout cardLayout;

    private JPanel content;

    private JPanel sideBar;

    private JPanel crewDetailsPanel;

    private JPanel newFoodPanel;
    
    private JPanel newMedicalItemPanel;

    private JPanel newPlanetPanel;

    private JPanel crewMemberSleepPanel;

    private JButton crewDetailsBtn;

    private JButton crewMemberSleepBtn;

    private JButton pilotNewShipBtn;

    private JButton applyFoodBtn;

    private JButton applyMedItemBtn;

    private ArrayList<JCheckBox> selectedMembers = new ArrayList<JCheckBox>();

    private int numberOfCheckboxesChecked;

    CrewPanel(GameEnvironment game) {
        super();

        this.environment = game;
        this.crew = game.getCrew();
        this.spaceOutPost = environment.getSpaceOutPost();

        JPanel sideBar = new JPanel();
        
        JPanel panel_1 = new JPanel();
        sideBar.add(panel_1);

        this.crewDetailsBtn = new JButton("Crew Details");
        panel_1.add(crewDetailsBtn);

        JPanel crewMemberSleepPanel = new JPanel();
        sideBar.add(crewMemberSleepPanel);

        this.crewMemberSleepBtn = new JButton("Sleep");
        crewMemberSleepBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        crewMemberSleepPanel.add(crewMemberSleepBtn);

        JPanel panel_2 = new JPanel();
        sideBar.add(panel_2);
        this.pilotNewShipBtn = new JButton("Pilot To New Planet");
        pilotNewShipBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        panel_2.add(pilotNewShipBtn);
        
        JPanel panel_3 = new JPanel();
        sideBar.add(panel_3);
        this.applyFoodBtn = new JButton("Apply Food");
        applyFoodBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        panel_3.add(applyFoodBtn);

        JPanel panel_4 = new JPanel();
        sideBar.add(panel_4);
        this.applyMedItemBtn = new JButton("Apply Medical Item");
        applyMedItemBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        panel_4.add(applyMedItemBtn);

        this.content = new JPanel();
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        
        add(sideBar);
        add(content);
        
        this.cardLayout = new CardLayout();
        content.setLayout(cardLayout);
        sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));

        // Show crew details first
        addCrewDetailsPanel();
        refreshCrewDetailsPanel();

        // Add new food
        addNewFoodPanel();

        addNewMedicalItemPanel();

        addNewPlanetPanel();

        addCrewMemberSleepPanel();

        cardLayout.show(content, "CREW_DETAILS");

        // Action listeners
        crewDetailsBtn.setEnabled(false);
        crewDetailsBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                refreshCrewDetailsPanel();
                cardLayout.show(content, "CREW_DETAILS");
                applyFoodBtn.setEnabled(true);
                crewDetailsBtn.setEnabled(false);
                pilotNewShipBtn.setEnabled(true);
                applyMedItemBtn.setEnabled(true);
                crewMemberSleepBtn.setEnabled(true);
            }
        });

        crewMemberSleepBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshCrewMemberSleepPanel();
                cardLayout.show(content, "SLEEP");
                applyFoodBtn.setEnabled(true);
                crewDetailsBtn.setEnabled(true);
                pilotNewShipBtn.setEnabled(true);
                applyMedItemBtn.setEnabled(true);
                crewMemberSleepBtn.setEnabled(false);
            }
        });

        pilotNewShipBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg1) {
                refreshNewPlanetPanel();
                cardLayout.show(content, "PILOT");
                applyFoodBtn.setEnabled(true);
                crewDetailsBtn.setEnabled(true);
                pilotNewShipBtn.setEnabled(false);
                applyMedItemBtn.setEnabled(true);
                crewMemberSleepBtn.setEnabled(true);
            }
        });

        applyFoodBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg2) {
                applyFoodBtn.setEnabled(false);
                crewDetailsBtn.setEnabled(true);
                pilotNewShipBtn.setEnabled(true);
                applyMedItemBtn.setEnabled(true);
                refreshNewFoodPanel();
                cardLayout.show(content, "APPLY_FOOD");
                crewMemberSleepBtn.setEnabled(true);
            }
        });

        applyMedItemBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg3) {
                applyFoodBtn.setEnabled(true);
                crewDetailsBtn.setEnabled(true);
                pilotNewShipBtn.setEnabled(true);
                applyMedItemBtn.setEnabled(false);
                crewMemberSleepBtn.setEnabled(true);
                refreshNewMedicalItemPanel();
                cardLayout.show(content, "APPLY_MEDICAL_ITEM");
            }
        });
    }

    public void resetHome() {
        cardLayout.show(content, "CREW_DETAILS");        
        applyFoodBtn.setEnabled(true);
        crewDetailsBtn.setEnabled(false);
        pilotNewShipBtn.setEnabled(true);
        applyMedItemBtn.setEnabled(true);
        crewMemberSleepBtn.setEnabled(true);
    }

    public void refresh() {
        resetHome();
        refreshCrewDetailsPanel();
        refreshNewFoodPanel();
        refreshNewMedicalItemPanel();
        refreshNewPlanetPanel();
        refreshCrewMemberSleepPanel();
    }

    private void addCrewDetailsPanel() {
        this.crewDetailsPanel = new JPanel();
        content.add(crewDetailsPanel, "CREW_DETAILS");
        crewDetailsPanel.setLayout(new GridLayout(0, 3, 0, 0));  
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
            memberImageLabel.setBounds(30, 13, 150, 150);
            memberImageLabel.setIcon(new ImageIcon(getClass().getResource(member.getIconPath())));
            memberPanel.add(memberImageLabel);
            
            JLabel memberNameLabel = new JLabel(member.getName());
            memberNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
            memberNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
            memberNameLabel.setBounds(10, 172, 190, 25);
            memberPanel.add(memberNameLabel);

            JLabel memberTypeLabel = new JLabel(String.format("%s", member.getType()));
            memberTypeLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
            memberTypeLabel.setHorizontalAlignment(SwingConstants.CENTER);
            memberTypeLabel.setBounds(10, 195, 190, 25);
            memberPanel.add(memberTypeLabel);
            
            JLabel memberDescLabel = new JLabel("Description:" + member.getDescription());
            memberDescLabel.setHorizontalAlignment(SwingConstants.LEFT);
            memberDescLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
            memberDescLabel.setBounds(10, 220, 190, 24);
            memberPanel.add(memberDescLabel);
            
            JLabel memberHealthLabel = new JLabel("Current health:" + member.getCurrentHealth());
            memberHealthLabel.setHorizontalAlignment(SwingConstants.LEFT);
            memberHealthLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
            memberHealthLabel.setBounds(10, 240, 377, 24);
            memberPanel.add(memberHealthLabel);

            JLabel memberCurHealthLabel = new JLabel("Max health:" + member.getMaxHealth());
            memberCurHealthLabel.setHorizontalAlignment(SwingConstants.LEFT);
            memberCurHealthLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
            memberCurHealthLabel.setBounds(10, 260, 377, 24);
            memberPanel.add(memberCurHealthLabel);

            JLabel memberHungerLabel = new JLabel("Hunger level:" + member.getHungerLevel());
            memberHungerLabel.setHorizontalAlignment(SwingConstants.LEFT);
            memberHungerLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
            memberHungerLabel.setBounds(10, 280, 377, 24);
            memberPanel.add(memberHungerLabel);

            JLabel memberTirednessLabel = new JLabel("Tiredness:" + member.getTiredness());
            memberTirednessLabel.setHorizontalAlignment(SwingConstants.LEFT);
            memberTirednessLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
            memberTirednessLabel.setBounds(10, 300, 377, 24);
            memberPanel.add(memberTirednessLabel);

            JLabel memberSpecialtyLabel = new JLabel("Specialty:" + member.getSpecialty());
            memberSpecialtyLabel.setHorizontalAlignment(SwingConstants.LEFT);
            memberSpecialtyLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
            memberSpecialtyLabel.setBounds(10, 320, 377, 24);
            memberPanel.add(memberSpecialtyLabel);

            JLabel actionsLeftLabel = new JLabel("Actions left:" + member.getActions());
            actionsLeftLabel.setHorizontalAlignment(SwingConstants.LEFT);
            actionsLeftLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
            actionsLeftLabel.setBounds(10, 340, 377, 24);
            memberPanel.add(actionsLeftLabel);
        }
    }


    private void addNewFoodPanel() {
        this.newFoodPanel = new JPanel();
        content.add(newFoodPanel, "APPLY_FOOD");
    }

    private void refreshNewFoodPanel() {
        newFoodPanel.removeAll();

        JPanel memberBoxPanel = new JPanel();
        memberBoxPanel.setLayout(new BoxLayout(memberBoxPanel, BoxLayout.Y_AXIS));
        newFoodPanel.add(memberBoxPanel);
        ButtonGroup memberButtonGroup = new ButtonGroup();
        for (CrewMember member: crew.getMembers()) {
            JPanel membersPanel = new JPanel();
            JRadioButton memberRadioBtn = new JRadioButton(member.getName());
            if (!member.hasActionsLeft()) {
                memberRadioBtn.setEnabled(false);
            }
            memberRadioBtn.putClientProperty("CrewMember", member);
            memberButtonGroup.add(memberRadioBtn);
            membersPanel.add(memberRadioBtn);
            memberBoxPanel.add(membersPanel);
        }

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
                JRadioButton selectedMemberRadio = Funcs.selectedButton(memberButtonGroup);
                JRadioButton selectedFoodRadio = Funcs.selectedButton(foodButtonGroup);

                CrewMember member = (CrewMember) selectedMemberRadio.getClientProperty("CrewMember");
                Food food = (Food) selectedFoodRadio.getClientProperty("Food");

                member.applyFood(food);
                spaceOutPost.removeFood(food);
                member.removeAction();
            }
        });
    }

    private void addNewMedicalItemPanel() {
        this.newMedicalItemPanel = new JPanel();
        content.add(newMedicalItemPanel, "APPLY_MEDICAL_ITEM");
    }

    public void refreshNewMedicalItemPanel() {
        newMedicalItemPanel.removeAll();

        JPanel memberBoxPanel = new JPanel();
        memberBoxPanel.setLayout(new BoxLayout(memberBoxPanel, BoxLayout.Y_AXIS));
        newMedicalItemPanel.add(memberBoxPanel);
        ButtonGroup memberButtonGroup = new ButtonGroup();
        for (CrewMember member: crew.getMembers()) {
            JPanel membersPanel = new JPanel();
            JRadioButton memberRadioBtn = new JRadioButton(member.getName());
            if (!member.hasActionsLeft()) {
                memberRadioBtn.setEnabled(false);
            }
            memberRadioBtn.putClientProperty("CrewMember", member);
            memberButtonGroup.add(memberRadioBtn);
            membersPanel.add(memberRadioBtn);
            memberBoxPanel.add(membersPanel);
        }

        JPanel medicalBoxPanel = new JPanel();
        medicalBoxPanel.setLayout(new BoxLayout(medicalBoxPanel, BoxLayout.Y_AXIS));
        newMedicalItemPanel.add(medicalBoxPanel);
        ButtonGroup medicalRadioGroup = new ButtonGroup();
        for (MedicalSupply medicalItem : spaceOutPost.getMedicalSupplies()) {
            JPanel medicalPanel = new JPanel();
            JRadioButton medicalRadioBtn = new JRadioButton(medicalItem.getType() + "(" + medicalItem.getCount() + ")");
            medicalRadioBtn.putClientProperty("MedicalSupply", medicalItem);
            if (!medicalItem.exists()) {
                medicalRadioBtn.setEnabled(false);
            }
            medicalRadioGroup.add(medicalRadioBtn);
            medicalPanel.add(medicalRadioBtn);
            medicalBoxPanel.add(medicalPanel);
        }

        JButton applyMedicalItemBtn = new JButton("Apply Food Item");
        newMedicalItemPanel.add(applyMedicalItemBtn);

        applyMedicalItemBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JRadioButton selectedMemberRadio = Funcs.selectedButton(memberButtonGroup);
                JRadioButton selectedMedicalSupplyRadio = Funcs.selectedButton(medicalRadioGroup);

                CrewMember member = (CrewMember) selectedMemberRadio.getClientProperty("CrewMember");
                MedicalSupply medicalSupply = (MedicalSupply) selectedMedicalSupplyRadio.getClientProperty("MedicalSupply");

                member.applyMedicalSupply(medicalSupply);
                spaceOutPost.removeMedicalSupply(medicalSupply);
                member.removeAction();
            }
        });
    }

    private void addNewPlanetPanel() {
        this.newPlanetPanel = new JPanel();
        content.add(newPlanetPanel, "PILOT");
    }

    private void refreshNewPlanetPanel() {
        newPlanetPanel.removeAll();

        Planet planet = environment.getPlanet();
        Planet[] allPlanets = planet.getAll();

        ButtonGroup planetButtonGroup = new ButtonGroup();

        JPanel planetsPanel = new JPanel();
        planetsPanel.setLayout(new BoxLayout(planetsPanel, BoxLayout.Y_AXIS));
        newPlanetPanel.add(planetsPanel);

        for (int i = 0; i < allPlanets.length; i++) {
            if (!allPlanets[i].getName().equals(environment.getCurrentPlanet().getName())) {
                JLabel planetImageLabel = new JLabel("");
                planetImageLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
                //memberImageLabel.setBounds(30, 13, 150, 150);
                planetImageLabel.setIcon(new ImageIcon(getClass().getResource(allPlanets[i].getIconPath())));
                JRadioButton planetRadioBtn = new JRadioButton(allPlanets[i].getName());
                planetRadioBtn.putClientProperty("Planet", allPlanets[i]);
                planetButtonGroup.add(planetRadioBtn);
                planetsPanel.add(planetRadioBtn);
                planetsPanel.add(planetImageLabel);
            }
        }

        JPanel membersPanelSplit = new JPanel();
        membersPanelSplit.setLayout(new BoxLayout(membersPanelSplit, BoxLayout.Y_AXIS));
        newPlanetPanel.add(membersPanelSplit);

        JPanel membersPanelOne = new JPanel();
        membersPanelOne.setLayout(new BoxLayout(membersPanelOne, BoxLayout.X_AXIS));
        membersPanelSplit.add(membersPanelOne);

        membersPanelOne.add(new JLabel("Member 1:"));
        ButtonGroup membersBtnGroupOne = new ButtonGroup();

        for (CrewMember member: crew.getMembers()) {
            JRadioButton memberRadioOne = new JRadioButton(member.getName());
            memberRadioOne.putClientProperty("CrewMember", member);
            if (!member.hasActionsLeft()) {
                memberRadioOne.setEnabled(false);
            }

            JLabel memberOneImageLabel = new JLabel("");
            memberOneImageLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
            memberOneImageLabel.setIcon(new ImageIcon(getClass().getResource(member.getIconPath())));

            membersBtnGroupOne.add(memberRadioOne);
            membersPanelOne.add(memberRadioOne);
            membersPanelOne.add(memberOneImageLabel);
        }

        JPanel membersPanelTwo = new JPanel();
        membersPanelTwo.setLayout(new BoxLayout(membersPanelTwo, BoxLayout.X_AXIS));
        membersPanelSplit.add(membersPanelTwo);

        membersPanelTwo.add(new JLabel("Member 2:"));
        ButtonGroup membersBtnGroupTwo = new ButtonGroup();

        for (CrewMember member: crew.getMembers()) {
            JRadioButton memberRadioTwo = new JRadioButton(member.getName());
            memberRadioTwo.putClientProperty("CrewMember", member);
            if (!member.hasActionsLeft()) {
                memberRadioTwo.setEnabled(false);
            }

            JLabel memberTwoImageLabel = new JLabel("");
            memberTwoImageLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
            memberTwoImageLabel.setIcon(new ImageIcon(getClass().getResource(member.getIconPath())));

            membersBtnGroupTwo.add(memberRadioTwo);
            membersPanelTwo.add(memberRadioTwo);
            membersPanelTwo.add(memberTwoImageLabel);
        }

        JButton doActionBtn = new JButton("Sleep");
        newPlanetPanel.add(doActionBtn);

        // Action Listenernes
        doActionBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JRadioButton selectedMemberRadioOne = Funcs.selectedButton(membersBtnGroupOne);
                JRadioButton selectedMemberRadioTwo = Funcs.selectedButton(membersBtnGroupTwo);

                CrewMember selectedMemberOne = (CrewMember) selectedMemberRadioOne.getClientProperty("CrewMember");
                CrewMember selectedMemberTwo = (CrewMember) selectedMemberRadioTwo.getClientProperty("CrewMember");
                if (selectedMemberOne.getName() == selectedMemberTwo.getName()) {
                    JOptionPane.showMessageDialog(null, "You cannot select two members of the same");
                } else {
                    selectedMemberOne.removeAction();
                    selectedMemberTwo.removeAction();

                    // Now move to new planet
                    JRadioButton selectedPlanetRadio = Funcs.selectedButton(planetButtonGroup);
                    Planet planet = (Planet) selectedPlanetRadio.getClientProperty("Planet");

                    // Change current planet
                    environment.changeCurrentPlanet(planet);
                 
                    if (!selectedMemberOne.hasActionsLeft()) {
                        selectedMemberRadioOne.setEnabled(false);
                    }

                    if (!selectedMemberTwo.hasActionsLeft()) {
                        selectedMemberRadioTwo.setEnabled(false);
                    }

                    JOptionPane.showMessageDialog(null, "You have successfully pioleted to: " + planet.getName());
                }
            }
        });
    }

    public void addCrewMemberSleepPanel() {
        this.crewMemberSleepPanel = new JPanel();
        content.add(crewMemberSleepPanel, "SLEEP");
    }

    public void refreshCrewMemberSleepPanel() {
        this.crewMemberSleepPanel.removeAll();

        // Get crew members and create a JBUTTON
        JPanel membersPanel = new JPanel();
        membersPanel.setLayout(new BoxLayout(membersPanel, BoxLayout.Y_AXIS));
        crewMemberSleepPanel.add(membersPanel);

        ButtonGroup memberButtonGroup = new ButtonGroup();
        for (CrewMember member: crew.getMembers()) {
            JRadioButton memberRadioBtn = new JRadioButton(member.getName());
            memberRadioBtn.putClientProperty("CrewMember", member);
            if (!member.hasActionsLeft()) {
                memberRadioBtn.setEnabled(false);
            }

            JLabel memeberImageLabel = new JLabel("");
            memeberImageLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
            memeberImageLabel.setIcon(new ImageIcon(getClass().getResource(member.getIconPath())));

            memberButtonGroup.add(memberRadioBtn);
            membersPanel.add(memberRadioBtn);
            membersPanel.add(memeberImageLabel);
        }

        JButton sleepBtn = new JButton("Sleep");
        crewMemberSleepPanel.add(sleepBtn);
        sleepBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JRadioButton selectedMemberRadio = Funcs.selectedButton(memberButtonGroup);

                CrewMember member = (CrewMember) selectedMemberRadio.getClientProperty("CrewMember");
                String responseString = "Crew member has no actions left";
                if (member.hasActionsLeft()) {
                    member.sleep();
                    member.removeAction();
                    responseString = "Crew member's tiredness has decreased by 10 points";
                
                    if (!member.hasActionsLeft()) {
                        selectedMemberRadio.setEnabled(false);
                    }
                }

                JOptionPane.showMessageDialog(null, responseString);
            }
        });
    }

    public JPanel getSelectMemberPanel() {
        JPanel memberPanel = new JPanel();
        ButtonGroup memberBtnGroup = new ButtonGroup();

        for (CrewMember member: crew.getMembers()) {
            JRadioButton memberRadio = new JRadioButton(member.getName());
            memberRadio.putClientProperty("CrewMember", member);
            if (!member.hasActionsLeft()) {
                memberRadio.setEnabled(false);
            }

            JLabel memberOneImageLabel = new JLabel("");
            memberOneImageLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
            memberOneImageLabel.setIcon(new ImageIcon(getClass().getResource(member.getIconPath())));

            memberBtnGroup.add(memberRadio);
            memberPanel.add(memberRadio);
            memberPanel.add(memberOneImageLabel);
        }

        return memberPanel;
    }    
}