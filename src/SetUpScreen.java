/*import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Rectangle;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import java.awt.Font;*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

public class SetUpScreen {

	private JFrame window;
	
	private Game game;
	
	private int numAddBtnPressed = 0;
	
	private JTextField name;

	private CardLayout cardLayout;

	private JPanel contentPanel;

	/**
	 * Create the application.
	 */
	public SetUpScreen(Game game) {
		this.game = game;
		initialize();
		window.setVisible(true);
	}

	public void finishedWindow() {
		window.dispose();
	}

	public void closeWindow() {
		game.closeSetUpScreen(this);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setBounds(new Rectangle(0, 0, 900, 700));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(new BoxLayout(window.getContentPane(), BoxLayout.X_AXIS));
		
		cardLayout = new CardLayout();

		contentPanel = new JPanel();
		window.getContentPane().add(contentPanel);
		
		contentPanel.setLayout(cardLayout);

		addStartGamePanel();
		addGameDataPanel();
		addChooseCharacterPanel();

		cardLayout.show(contentPanel, "START_GAME_PANEL");
	}

	public void addStartGamePanel() {
		JPanel startGamePanel = new JPanel();
		startGamePanel.setLayout(null);
		contentPanel.add(startGamePanel, "START_GAME_PANEL");
		
		JLabel lblGameTitle = new JLabel("GAME NAME");
		lblGameTitle.setFont(new Font("Dialog", Font.BOLD, 24));
		lblGameTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameTitle.setBounds(12, 43, 874, 58);
		startGamePanel.add(lblGameTitle);
		
		JButton newGameBtn = new JButton("New Game");
		newGameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(contentPanel, "GAME_DATA_PANEL");
			}
		});
		newGameBtn.setBounds(386, 233, 114, 25);
		startGamePanel.add(newGameBtn);
		
		JButton exitBtn = new JButton("Exit");
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		exitBtn.setBounds(386, 290, 114, 25);
		startGamePanel.add(exitBtn);
	}

	public void addGameDataPanel() {
		JPanel gameDataPanel = new JPanel();
		gameDataPanel.setLayout(null);
		contentPanel.add(gameDataPanel, "GAME_DATA_PANEL");

		JLabel lblGameName = new JLabel("Game Name");
		lblGameName.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameName.setBounds(0, 0, 878, 42);
		gameDataPanel.add(lblGameName);
		
		JLabel lblHowManyCharacters = new JLabel("Team name");
		lblHowManyCharacters.setBounds(35, 80, 276, 29);
		gameDataPanel.add(lblHowManyCharacters);
		
		JLabel lblRocketShipName = new JLabel("Rocket ship name");
		lblRocketShipName.setBounds(37, 132, 276, 29);
		gameDataPanel.add(lblRocketShipName);
		
		JTextField rocketShipTxtField = new JTextField();
		rocketShipTxtField.setColumns(10);
		rocketShipTxtField.setBounds(444, 132, 176, 24);
		gameDataPanel.add(rocketShipTxtField);
		
		JTextField teamNameTxt = new JTextField();
		teamNameTxt.setColumns(10);
		teamNameTxt.setBounds(444, 85, 176, 24);
		gameDataPanel.add(teamNameTxt);
		
		JLabel lblNumDays = new JLabel("How many days do you want to play?");
		lblNumDays.setBounds(35, 184, 276, 29);
		gameDataPanel.add(lblNumDays);

		JLabel lblNumCharacters = new JLabel("How many characters do you want to play?");
		lblNumCharacters.setBounds(35, 242, 325, 29);
		gameDataPanel.add(lblNumCharacters);
		
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
						teamNameTxt.getText().trim(), 
						sliderCharactersAmount.getValue(), 
						sliderDaysAmount.getValue(), 
						rocketShipTxtField.getText()
				));
				cardLayout.show(contentPanel, "CHOOSE_CHARACTER_PANEL");
			}
		});
		gameDataPanel.add(btnContinue);
	}

	public void addChooseCharacterPanel() {
		JPanel chooseCharacterPanel = new JPanel();
		chooseCharacterPanel.setLayout(null);
		contentPanel.add(chooseCharacterPanel, "CHOOSE_CHARACTER_PANEL");

		JLabel lblGameName = new JLabel("Game Name");
		lblGameName.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameName.setBounds(0, 0, 878, 42);
		chooseCharacterPanel.add(lblGameName);

		JLabel lblCharacterName = new JLabel("Character Name:");
		lblCharacterName.setBounds(33, 100, 126, 27);
		chooseCharacterPanel.add(lblCharacterName);
		
		JTextField characterNameTxt = new JTextField();
		characterNameTxt.setBounds(233, 100, 143, 23);
		chooseCharacterPanel.add(characterNameTxt);
		characterNameTxt.setColumns(10);
		
		JLabel lblCharacterType = new JLabel("Character Type:");
		lblCharacterType.setBounds(33, 140, 126, 27);
		chooseCharacterPanel.add(lblCharacterType);
		
		CrewMember crewMember = new CrewMember();
		String[] types = crewMember.getAllTypes();
		JComboBox type = new JComboBox(types);

		type.setBounds(233, 140, 143, 24);
		chooseCharacterPanel.add(type);
		
		JButton btnNext = new JButton("Add");
		btnNext.setBounds(707, 527, 114, 25);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numAddBtnPressed++;
				if (btnNext.getText() == "Continue") {
					closeWindow();
				}
				if (numAddBtnPressed == game.getGameEnvironment().getCrew().getNumMembers()) {
					type.setEnabled(false);
					characterNameTxt.setEditable(false);
					btnNext.setText("Continue");
				} else {
					String typeStr = String.valueOf(type.getSelectedItem());
					game.getGameEnvironment().getCrew().addCrewMember(typeStr, characterNameTxt.getText());
					characterNameTxt.setText("");
				}
			}
		});

		chooseCharacterPanel.add(btnNext);
		
		// Barter is select box default value..
		Barter barter = new Barter("");
        JLabel memberImageLabel = new JLabel("");
        memberImageLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
        memberImageLabel.setBounds(531, 100, 150, 150);
        memberImageLabel.setIcon(new ImageIcon(getClass().getResource(barter.getIconPath())));
        chooseCharacterPanel.add(memberImageLabel);

		JLabel descriptionLabel = new JLabel(barter.getDescription());
		descriptionLabel.setBounds(531, 200, 300, 150);
		chooseCharacterPanel.add(descriptionLabel);

		JLabel healthLbl = new JLabel("Max Health: " + barter.getMaxHealth());
		healthLbl.setBounds(531, 240, 150, 150);
		chooseCharacterPanel.add(healthLbl);

		JLabel specialtyLbl = new JLabel("Specialty: " + barter.getSpecialty());
		specialtyLbl.setBounds(531, 260, 150, 150);
		chooseCharacterPanel.add(specialtyLbl);

		type.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Run a switch: and get the descriptions....update a local label to it...
				String label = "";
				String healthLbl = "";
				String specialtyLbl = "";
				String imagePath = "";
				switch ((String)  type.getSelectedItem()) {
		            case "Barter":
		                Barter bart = new Barter("");
		                label = bart.getDescription();
		                healthLbl = "Max health: " + bart.getMaxHealth();
		                specialtyLbl = "Specialty: " + bart.getSpecialty();
		                imagePath = bart.getIconPath();
		            break;
		            case "Mechanic":
		                Mechanic mech = new Mechanic("");
		                label = mech.getDescription();
		          		healthLbl = "Max health: " + mech.getMaxHealth();
		          		specialtyLbl = "Specialty: " + mech.getSpecialty();
		                imagePath = mech.getIconPath();
		            break;
		            case "Nerd":
		                Nerd nerd = new Nerd("");
		                label = nerd.getDescription();
		                healthLbl = "Max health: " + nerd.getMaxHealth();
		                specialtyLbl = "Specialty: " + nerd.getSpecialty();
		                imagePath = nerd.getIconPath();
		            break;
		            case "Scout":
		                Scout scout = new Scout("");
		                label = scout.getDescription();
		                healthLbl = "Max health: " + scout.getMaxHealth();
		                specialtyLbl = "Specialty: " + scout.getSpecialty();
		                imagePath = scout.getIconPath();
		            break;
		            case "Tank":
		                Tank tank = new Tank("");
		                label = tank.getDescription();
		                healthLbl = "Max health: " + tank.getMaxHealth();
		                specialtyLbl = "Specialty: " + tank.getSpecialty();
		                imagePath = tank.getIconPath();
		            break;
				}
				descriptionLabel.setText(label);
				memberImageLabel.setIcon(new ImageIcon(getClass().getResource(imagePath)));
			}
		});
	}
}
