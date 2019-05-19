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
		window.setBounds(new Rectangle(0, 0, 1000, 810));
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
		lblGameTitle.setBounds(12, 43, 974, 58);
		startGamePanel.add(lblGameTitle);
		
		JButton newGameBtn = new JButton("New Game");
		newGameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(contentPanel, "GAME_DATA_PANEL");
			}
		});
		newGameBtn.setBounds(425, 194, 150, 60);
		startGamePanel.add(newGameBtn);
		
		JButton exitBtn = new JButton("Exit");
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		exitBtn.setBounds(425, 259, 150, 60);
		startGamePanel.add(exitBtn);
	}

	public void addGameDataPanel() {
		JPanel gameDataPanel = new JPanel();
		gameDataPanel.setLayout(null);
		contentPanel.add(gameDataPanel, "GAME_DATA_PANEL");

		JLabel gameNameLabel = new JLabel("Game Name");
		gameNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gameNameLabel.setBounds(12, 0, 974, 69);
		gameDataPanel.add(gameNameLabel);
		
		JLabel teamNameLabel = new JLabel("Team name");
		teamNameLabel.setBounds(40, 90, 276, 29);
		gameDataPanel.add(teamNameLabel);

		JLabel teamNameErrorLabel = new JLabel("");
		teamNameErrorLabel.setBounds(40, 110, 276, 29);
		teamNameErrorLabel.setForeground(Color.red);
		gameDataPanel.add(teamNameErrorLabel);
		
		JTextField teamNameTxt = new JTextField();
		teamNameTxt.setColumns(10);
		teamNameTxt.setBounds(444, 90, 200, 30);
		gameDataPanel.add(teamNameTxt);

		JLabel lblRocketShipName = new JLabel("Rocket ship name");
		lblRocketShipName.setBounds(40, 140, 276, 29);
		gameDataPanel.add(lblRocketShipName);
		
		JLabel rocketShipNameErrorLabel = new JLabel("");
		rocketShipNameErrorLabel.setBounds(40, 160, 276, 29);
		rocketShipNameErrorLabel.setForeground(Color.red);
		gameDataPanel.add(rocketShipNameErrorLabel);

		JTextField rocketShipTxtField = new JTextField();
		rocketShipTxtField.setColumns(10);
		rocketShipTxtField.setBounds(444, 140, 200, 30);
		gameDataPanel.add(rocketShipTxtField);
		
		JLabel lblNumDays = new JLabel("How many days do you want to play?");
		lblNumDays.setBounds(40, 190, 276, 29);
		gameDataPanel.add(lblNumDays);

		JLabel numDaysErrorLabel = new JLabel("");
		numDaysErrorLabel.setBounds(40, 210, 276, 29);
		numDaysErrorLabel.setForeground(Color.red);
		gameDataPanel.add(numDaysErrorLabel);

		JSlider sliderDaysAmount = new JSlider();
		gameDataPanel.add(sliderDaysAmount);
		sliderDaysAmount.setSnapToTicks(true);
		sliderDaysAmount.setPaintLabels(true);
		sliderDaysAmount.setMinimum(3);
		sliderDaysAmount.setMaximum(10);
		sliderDaysAmount.setMajorTickSpacing(1);
		sliderDaysAmount.setBounds(444, 190, 264, 45);
		sliderDaysAmount.setValue(1);

		JLabel lblNumCharacters = new JLabel("How many characters do you want to play?");
		lblNumCharacters.setBounds(40, 240, 325, 29);
		gameDataPanel.add(lblNumCharacters);

		JLabel numCharactersErrorLabel = new JLabel("");
		numCharactersErrorLabel.setBounds(40, 260, 276, 29);
		numCharactersErrorLabel.setForeground(Color.red);
		gameDataPanel.add(numCharactersErrorLabel);

		JSlider sliderCharactersAmount = new JSlider();
		sliderCharactersAmount.setValue(2);
		sliderCharactersAmount.setPaintLabels(true);
		sliderCharactersAmount.setMajorTickSpacing(1);
		sliderCharactersAmount.setMaximum(4);
		sliderCharactersAmount.setMinimum(2);
		sliderCharactersAmount.setSnapToTicks(true);
		sliderCharactersAmount.setMinorTickSpacing(1);
		sliderCharactersAmount.setBounds(444, 240, 264, 35);
		gameDataPanel.add(sliderCharactersAmount);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.setBounds(781, 676, 130, 40);
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean hasErrors = false;
				String teamName = teamNameTxt.getText().trim();
				if (teamName.length() < 4 || teamName.length() > 8) {
					teamNameErrorLabel.setText("Must be between 4-8 characters long");
					hasErrors = true;
				}

				String rocketShipName = rocketShipTxtField.getText().trim();
				if (rocketShipName.length() < 4 || rocketShipName.length() > 8) {
					rocketShipNameErrorLabel.setText("Must be between 4-8 characters long");
					hasErrors = true;
				}

				int numDays = sliderDaysAmount.getValue();
				if (!Funcs.isValidNumDays(numDays)) {
					numDaysErrorLabel.setText("Invalid number of days");
					hasErrors = true;
				}

				int numCharacters = sliderCharactersAmount.getValue();
				if (!Funcs.isValidNumCharacters(numCharacters)) {
					numCharactersErrorLabel.setText("Invalid number of characters");
					hasErrors = true;
				}

				if (!hasErrors) {
					game.setGameEnvironment(new GameEnvironment(
							teamName,
							sliderCharactersAmount.getValue(), 
							sliderDaysAmount.getValue(), 
							rocketShipName
					));
					cardLayout.show(contentPanel, "CHOOSE_CHARACTER_PANEL");
				}
			}
		});
		gameDataPanel.add(btnContinue);
	}

	public void addChooseCharacterPanel() {
		JPanel chooseCharacterPanel =  new JPanel();
		chooseCharacterPanel.setLayout(null);
		contentPanel.add(chooseCharacterPanel, "CHOOSE_CHARACTER_PANEL");

		JLabel lblGameName = new JLabel("Game Name");
		lblGameName.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameName.setBounds(12, 0, 974, 69);
		chooseCharacterPanel.add(lblGameName);

		JLabel lblCharacterName = new JLabel("Character Name:");
		lblCharacterName.setBounds(66, 100, 126, 27);
		chooseCharacterPanel.add(lblCharacterName);

		JLabel lblCharacterNameError = new JLabel("");
		lblCharacterNameError.setBounds(66, 115, 126, 27);
		chooseCharacterPanel.add(lblCharacterNameError);
		lblCharacterNameError.setForeground(Color.red);
		
		JTextField characterNameTxt = new JTextField();
		characterNameTxt.setBounds(267, 102, 200, 30);
		chooseCharacterPanel.add(characterNameTxt);
		characterNameTxt.setColumns(10);
		
		JLabel lblCharacterType = new JLabel("Character Type:");
		lblCharacterType.setBounds(66, 140, 126, 27);
		chooseCharacterPanel.add(lblCharacterType);
		
		CrewMember crewMember = new CrewMember();
		String[] types = crewMember.getAllTypes();
		JComboBox type = new JComboBox(types);

		type.setBounds(267, 141, 200, 30);
		chooseCharacterPanel.add(type);
		
		JButton btnNext = new JButton("Add");
		btnNext.setBounds(781, 676, 130, 40);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = characterNameTxt.getText().trim();
				if (name.length() < 4 || name.length() > 8) {
					lblCharacterNameError.setText("Must be between 4 and 8 characters");
				} else {
					lblCharacterNameError.setText("");
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
						game.getGameEnvironment().getCrew().addCrewMember(typeStr, name);
						characterNameTxt.setText("");
					}
				}
			}
		});

		chooseCharacterPanel.add(btnNext);
		
		// Barter is select box default value..
		Barter barter = new Barter("");
        JLabel memberImageLabel = new JLabel("");
        memberImageLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
        memberImageLabel.setBounds(577, 98, 150, 150);
        memberImageLabel.setIcon(new ImageIcon(Image.getCrewMemberImagePath(barter)));
        chooseCharacterPanel.add(memberImageLabel);

		JLabel descriptionLabel = new JLabel(barter.getDescription());
		descriptionLabel.setBounds(577, 204, 300, 150);
		chooseCharacterPanel.add(descriptionLabel);

		JLabel healthLbl = new JLabel("Max Health: " + barter.getMaxHealth());
		healthLbl.setBounds(577, 240, 150, 150);
		chooseCharacterPanel.add(healthLbl);

		JLabel specialtyLbl = new JLabel("Specialty: " + barter.getSpecialty());
		specialtyLbl.setBounds(577, 260, 150, 150);
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
		                Barter barter = new Barter("");
		                label = barter.getDescription();
		                healthLbl = "Max health: " + barter.getMaxHealth();
		                specialtyLbl = "Specialty: " + barter.getSpecialty();
		                imagePath = Image.getCrewMemberImagePath(barter);
		            break;
		            case "Mechanic":
		                Mechanic mech = new Mechanic("");
		                label = mech.getDescription();
		          		healthLbl = "Max health: " + mech.getMaxHealth();
		          		specialtyLbl = "Specialty: " + mech.getSpecialty();
		                imagePath = Image.getCrewMemberImagePath(mech);
		            break;
		            case "Nerd":
		                Nerd nerd = new Nerd("");
		                label = nerd.getDescription();
		                healthLbl = "Max health: " + nerd.getMaxHealth();
		                specialtyLbl = "Specialty: " + nerd.getSpecialty();
		                imagePath = Image.getCrewMemberImagePath(nerd);
		            break;
		            case "Scout":
		                Scout scout = new Scout("");
		                label = scout.getDescription();
		                healthLbl = "Max health: " + scout.getMaxHealth();
		                specialtyLbl = "Specialty: " + scout.getSpecialty();
		                imagePath = Image.getCrewMemberImagePath(scout);
		            break;
		            case "Tank":
		                Tank tank = new Tank("");
		                label = tank.getDescription();
		                healthLbl = "Max health: " + tank.getMaxHealth();
		                specialtyLbl = "Specialty: " + tank.getSpecialty();
		                imagePath = Image.getCrewMemberImagePath(tank);
		            break;
				}
				descriptionLabel.setText(label);
				memberImageLabel.setIcon(Funcs.getScaledIcon(imagePath, 150,150));;
			}
		});
	}
}
