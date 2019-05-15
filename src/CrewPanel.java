import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
            }
        });

        pilotNewShipBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg1) {
                applyFoodBtn.setEnabled(true);
                crewDetailsBtn.setEnabled(true);
                pilotNewShipBtn.setEnabled(false);
                applyMedItemBtn.setEnabled(true);
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
            }
        });

        applyMedItemBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg3) {
                applyFoodBtn.setEnabled(true);
                crewDetailsBtn.setEnabled(true);
                pilotNewShipBtn.setEnabled(true);
                applyMedItemBtn.setEnabled(false);
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
        // Choose a member
        newFoodPanel.removeAll();

        JPanel memberSelect = new JPanel();
        ButtonGroup bgroup = new ButtonGroup();

        for (CrewMember member: crew.getMembers()) {
            JRadioButton radioBtn = new JRadioButton(member.getName());
            bgroup.add(radioBtn);
            memberSelect.add(radioBtn);
        }
        newFoodPanel.add(memberSelect);

        JPanel foodPanelOuter = new JPanel();
        foodPanelOuter.setLayout(new BoxLayout(foodPanelOuter, BoxLayout.Y_AXIS));
        for (Food f : spaceOutPost.getFoods()) {
            JPanel foodPanel = new JPanel();
            JLabel foodLabel = new JLabel(f.getType() + "(" + f.getCount() + ")");
            foodPanel.add(foodLabel);
            foodPanelOuter.add(foodPanel);
        }
        newFoodPanel.add(foodPanelOuter);
    }

    private void addNewMedicalItemPanel() {
        this.newMedicalItemPanel = new JPanel();
        content.add(newMedicalItemPanel, "APPLY_MEDICAL_ITEM");
    }
}