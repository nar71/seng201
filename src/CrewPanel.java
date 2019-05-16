import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;

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

    private ArrayList<JCheckBox> selectedMembers = new ArrayList<JCheckBox>();

    CrewPanel(GameEnvironment game) {
        super();

        this.environment = game;
        this.crew = game.getCrew();
        this.spaceOutPost = environment.getSpaceOutPost();

        JPanel sideBar = new JPanel();
        
        JPanel panel_1 = new JPanel();
        sideBar.add(panel_1);

        JButton crewDetailsBtn = new JButton("Crew Details");
        panel_1.add(crewDetailsBtn);

        JPanel crewMemberSleepPanel = new JPanel();
        sideBar.add(crewMemberSleepPanel);

        JButton crewMemberSleepBtn = new JButton("Sleep");
        crewMemberSleepPanel.add(crewMemberSleepBtn);

        JPanel panel_2 = new JPanel();
        sideBar.add(panel_2);
        JButton pilotNewShipBtn = new JButton("Pilot To New Planet");
        panel_2.add(pilotNewShipBtn);
        
        JPanel panel_3 = new JPanel();
        sideBar.add(panel_3);
        JButton applyFoodBtn = new JButton("Apply Food");
        panel_3.add(applyFoodBtn);

        JPanel panel_4 = new JPanel();
        sideBar.add(panel_4);
        JButton applyMedItemBtn = new JButton("Apply Medical Item");
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

    private void addCrewDetailsPanel() {
        this.crewDetailsPanel = new JPanel();
        content.add(crewDetailsPanel, "CREW_DETAILS");
    }

    private void refreshCrewDetailsPanel() {
        crewDetailsPanel.removeAll();

        for (CrewMember cm: crew.getMembers()) {
            System.out.println(cm);
        }

        for (CrewMember member: crew.getMembers()) {
            JPanel panelMember = new JPanel();
            panelMember.setLayout(new BoxLayout(panelMember, BoxLayout.Y_AXIS));
            crewDetailsPanel.add(panelMember);

            JPanel panel1M = new JPanel();
            JLabel label = new JLabel(member.getName());
            panel1M.add(label);
            panelMember.add(panel1M);
            
            JPanel panel2M = new JPanel();
            JLabel label2 = new JLabel("Max Health: " + member.getMaxHealth());
            panel2M.add(label2);
            panelMember.add(panel2M);
            
            JPanel panel3M = new JPanel();
            JLabel label3 = new JLabel("Current Health: " + member.getCurrentHealth());
            panel3M.add(label3);
            panelMember.add(panel3M);
            
            JPanel panel4M = new JPanel();
            JLabel label4 = new JLabel("Tiredness level: " + member.getTiredness());
            panel4M.add(label4);
            panelMember.add(panel4M);
            
            JPanel panel5M = new JPanel();
            JLabel label5 = new JLabel("Hunger level: " + member.getHungerLevel());
            panelMember.add(label5);
            panel5M.add(label5);
            panelMember.add(panel5M);
            
            JPanel panel6M = new JPanel();
            JLabel label6 = new JLabel("Speciality: " + member.getSpecialty());
            panel6M.add(label6);
            panelMember.add(panel6M);
            
            JPanel panel7M = new JPanel();
            JLabel label7 = new JLabel("Description: " + member.getDescription());
            panel7M.add(label7);
            panelMember.add(label7);
            
            String isSickStr = "no";
            if (member.isSick()) {
                isSickStr = "yes";
            }
            JPanel panel8M = new JPanel();
            JLabel label8 = new JLabel("Is sick: " + isSickStr);
            panel8M.add(label8);

            JPanel panel9M = new JPanel();
            panel9M.add(new JLabel("Actions: " + member.getActions()));
            
            panelMember.add(panel8M);
            panelMember.add(panel9M);
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
                JRadioButton planetRadioBtn = new JRadioButton(allPlanets[i].getName());
                planetRadioBtn.putClientProperty("Planet", allPlanets[i]);
                planetButtonGroup.add(planetRadioBtn);
                planetsPanel.add(planetRadioBtn);
            }
        }

        JPanel membersPanel = new JPanel();
        membersPanel.setLayout(new BoxLayout(membersPanel, BoxLayout.Y_AXIS));
        newPlanetPanel.add(membersPanel);

        for (CrewMember member: crew.getMembers()) {
            JCheckBox memberCheckBox = new JCheckBox(member.getName());
            memberCheckBox.putClientProperty("CrewMember", member);
            if (!member.hasActionsLeft()) {
                memberCheckBox.setEnabled(false);
            }
            selectedMembers.add(memberCheckBox);
            membersPanel.add(memberCheckBox);
        }

        JButton doActionBtn = new JButton("DO");
        newPlanetPanel.add(doActionBtn);

        // Action Listenernes
        doActionBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Loop through list:
                for (JCheckBox box: selectedMembers) {
                    if (box.isSelected()) {
                        CrewMember actionedMember = crew.getMemberByName(box.getText());
                        actionedMember.removeAction();
                    }
                }

                // Now move to new planet
                JRadioButton selectedPlanetRadio = Funcs.selectedButton(planetButtonGroup);
                Planet planet = (Planet) selectedPlanetRadio.getClientProperty("Planet");

                // Change current planet
                environment.changeCurrentPlanet(planet);
                
                JOptionPane.showMessageDialog(null, "You have successfully pioleted to: " + planet.getName());
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
            memberButtonGroup.add(memberRadioBtn);
            membersPanel.add(memberRadioBtn);
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
                }

                JOptionPane.showMessageDialog(null, responseString);
                refreshCrewMemberSleepPanel();
            }
        });
    }
}