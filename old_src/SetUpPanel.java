import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Rectangle;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.Font;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class SetUpPanel extends JPanel {
    private GamePanel game;

    private CardLayout cardLayout;

    private JPanel contentPanel;

    private JTextField rocketShipName;
    
    private JTextField teamName;

    private JTextField textField_1;

    private JTextField name;

    private int charactersAddedCount;

    private static final String START_SCREEN_PANEL_STRING = "START_SCREEN";

    private static final String GAME_DATA_PANEL_STRING = "GAME_DATA_PANEL";

    private static final String CHOOSE_CHARACTER_PANEL_STRING = "CHOOSE_CHARACTER_PANEL";

    SetUpPanel(GamePanel game) {
        super();
        this.game = game;
        init();
    }

    public void init() {
        cardLayout = new CardLayout();
        
        contentPanel = new JPanel();
        contentPanel.setLayout(cardLayout);

        add(contentPanel);

        addStartGamePanel();
        addGameDataPanel();
        addChooseCharacterPanel();

        cardLayout.show(contentPanel, GAME_DATA_PANEL_STRING);
    }

    public void addStartGamePanel() {
        JPanel startGamePanel = new JPanel();
        this.contentPanel.add(startGamePanel, START_SCREEN_PANEL_STRING);
        JLabel gameNameLabel = new JLabel("Game Name");
        startGamePanel.add(gameNameLabel);
        
        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, SET_UP_PANEL_STRING);
            }
        });
        newGameButton.setBounds(386, 79, 114, 25);
        startGamePanel.add(newGameButton);
        
        JButton exitGameButton = new JButton("Exit");
        exitGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        exitGameButton.setBounds(386, 126, 114, 25);
        startGamePanel.add(exitGameButton);
    }

    public void addGameDataPanel() {
        JPanel gameDataPanel = new JPanel();
        contentPanel.add(gameDataPanel, GAME_DATA_PANEL_STRING);
        
        //gameDataPanel.setLayout(null);
        
        JLabel lblGameName = new JLabel("Game Name");
        lblGameName.setHorizontalAlignment(SwingConstants.CENTER);
        lblGameName.setBounds(0, 0, 878, 42);
        gameDataPanel.add(lblGameName);
        
        JLabel lblHowManyCharacters = new JLabel("Team name");
        lblHowManyCharacters.setBounds(35, 80, 276, 29);
        gameDataPanel.add(lblHowManyCharacters);
        
        JLabel label = new JLabel("Rocket ship name");
        label.setBounds(37, 132, 276, 29);
        gameDataPanel.add(label);
        
        rocketShipName = new JTextField();
        rocketShipName.setColumns(10);
        rocketShipName.setBounds(444, 132, 176, 24);
        gameDataPanel.add(rocketShipName);
        
        teamName = new JTextField();
        teamName.setColumns(10);
        teamName.setBounds(444, 85, 176, 24);
        gameDataPanel.add(teamName);
        
        JLabel label_1 = new JLabel("How many days do you want to play?");
        label_1.setBounds(35, 184, 276, 29);
        gameDataPanel.add(label_1);

        JLabel label_2 = new JLabel("How many characters do you want to play?");
        label_2.setBounds(35, 242, 325, 29);
        gameDataPanel.add(label_2);
        
        JSlider sliderDaysAmount = new JSlider();
        gameDataPanel.add(sliderDaysAmount);
        sliderDaysAmount.setSnapToTicks(true);
        sliderDaysAmount.setPaintLabels(true);
        sliderDaysAmount.setMinimum(3);
        sliderDaysAmount.setMaximum(10);
        sliderDaysAmount.setMajorTickSpacing(1);
        sliderDaysAmount.setBounds(444, 169, 264, 45);
        sliderDaysAmount.setValue(1);
        
        JSlider sliderCharactersAmount = new JSlider();
        sliderCharactersAmount.setValue(2);
        sliderCharactersAmount.setPaintLabels(true);
        sliderCharactersAmount.setMajorTickSpacing(1);
        sliderCharactersAmount.setMaximum(4);
        sliderCharactersAmount.setMinimum(2);
        sliderCharactersAmount.setSnapToTicks(true);
        sliderCharactersAmount.setMinorTickSpacing(1);
        sliderCharactersAmount.setBounds(444, 232, 264, 35);
        gameDataPanel.add(sliderCharactersAmount);
        
        JButton btnContinue = new JButton("Continue");
        btnContinue.setBounds(707, 527, 114, 25);
        btnContinue.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.setGameEnvironment(new GameEnvironment(
                        teamName.getText().trim(), 
                        sliderCharactersAmount.getValue(), 
                        sliderDaysAmount.getValue(), 
                        rocketShipName.getText()
                ));

                cardLayout.show(contentPanel, CHOOSE_CHARACTER_PANEL_STRING);
            }
        });
        gameDataPanel.add(btnContinue);
    }

    public void addChooseCharacterPanel() {
        JPanel panel = new JPanel();
        contentPanel.add(panel, CHOOSE_CHARACTER_PANEL_STRING);
        
        //panel.setLayout(null);
        
        JLabel lblGameName = new JLabel("Game Name");
        lblGameName.setHorizontalAlignment(SwingConstants.CENTER);
        lblGameName.setBounds(0, 0, 878, 27);
        panel.add(lblGameName);
        
        JLabel lblCharacterName = new JLabel("Character Name:");
        lblCharacterName.setBounds(33, 53, 126, 27);
        panel.add(lblCharacterName);
        
        name = new JTextField();
        name.setBounds(233, 55, 143, 23);
        panel.add(name);
        name.setColumns(10);
        
        JLabel lblCharacterType = new JLabel("Character Type:");
        lblCharacterType.setBounds(33, 110, 126, 27);
        panel.add(lblCharacterType);
        
        CrewMember cm = new CrewMember();
        String[] types = cm.getAllTypes();
        JComboBox type = new JComboBox(types);

        type.setBounds(233, 111, 143, 24);
        panel.add(type);
        
        JButton btnNext = new JButton("Add");
        btnNext.setBounds(688, 524, 114, 25);
        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                charactersAddedCount++;
                if (btnNext.getText() == "Continue") {
                    game.startGame();
                }
                if (charactersAddedCount == game.getGameEnvironment().getCrew().getNumMembers()) {
                    type.setEnabled(false);
                    name.setEditable(false);
                    btnNext.setText("Continue");
                } else {
                    String typeStr = String.valueOf(type.getSelectedItem());
                    game.getGameEnvironment().getCrew().addCrewMember(typeStr, name.getText());
                    name.setText("");
                }
            }
        });
        panel.add(btnNext);
        
        Barter bart = new Barter("");
        JLabel descriptionLabel = new JLabel(bart.getDescription());
        descriptionLabel.setBounds(489, 59, 340, 258);
        panel.add(descriptionLabel);

        type.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Run a switch: and get the descriptions....update a local label to it...
                String label = "";
                switch ((String)  type.getSelectedItem()) {
                    case "Barter":
                        Barter bart = new Barter("");
                        label = bart.getDescription();
                    break;
                    case "Mechanic":
                        Mechanic mech = new Mechanic("");
                        label = mech.getDescription();
                    break;
                    case "Nerd":
                        Nerd nerd = new Nerd("");
                        label = nerd.getDescription();
                    break;
                    case "Scout":
                        Scout scout = new Scout("");
                        label = scout.getDescription();
                    break;
                    case "Tank":
                        Tank tank = new Tank("");
                        label = tank.getDescription();
                    break;
                }
                descriptionLabel.setText(label);
            }
        });
    }
}