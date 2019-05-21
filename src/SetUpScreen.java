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
		window.setBounds(new Rectangle(200, 50, 1000, 810));
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(new BoxLayout(window.getContentPane(), BoxLayout.X_AXIS));
		
		cardLayout = new CardLayout();

		contentPanel = new JPanel();
		window.getContentPane().add(contentPanel);
		
		contentPanel.setLayout(cardLayout);

		addStartGamePanel();
		addGameDataPanel();
		addChooseCharacterPanel();
		addGameInformationPanel();

		cardLayout.show(contentPanel, "START_GAME_PANEL");
	}

	private JLabel getBigTitle() {
		JLabel lblGameTitle = new JLabel("SPACE TRAVELLERS");
		lblGameTitle.setFont(new Font("Ringbearer", Font.PLAIN, 70));
		lblGameTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameTitle.setBounds(10, 11, 1000, 150);
		return lblGameTitle;
	}

	public void addStartGamePanel() {
		JPanel startGamePanel = new JPanel();
		startGamePanel.setLayout(null);
		contentPanel.add(startGamePanel, "START_GAME_PANEL");

		startGamePanel.add(getBigTitle());

		JButton newGameBtn = new JButton("New Game");
		newGameBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		newGameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(contentPanel, "GAME_DATA_PANEL");
			}
		});
		newGameBtn.setBounds(425, 300, 150, 60);
		startGamePanel.add(newGameBtn);
		
		JButton exitBtn = new JButton("Exit");
		exitBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		exitBtn.setBounds(425, 380, 150, 60);
		startGamePanel.add(exitBtn);
	}

	public void addGameDataPanel() {
		JPanel gameDataPanel = new JPanel();
		gameDataPanel.setLayout(null);
		contentPanel.add(gameDataPanel, "GAME_DATA_PANEL");

		gameDataPanel.add(getBigTitle());
		
		JLabel lblTeamName = new JLabel("What do you want to call your team?");
		lblTeamName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTeamName.setBounds(160, 200, 300, 29);
		gameDataPanel.add(lblTeamName);

		JLabel lblTeamNameError = new JLabel("");
		lblTeamNameError.setBounds(80, 220, 276, 29);
		lblTeamNameError.setBounds(160, 220, 300, 29);
		lblTeamNameError.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTeamNameError.setForeground(Color.red);
		gameDataPanel.add(lblTeamNameError);
		
		JTextField teamNameTxt = new JTextField();
		teamNameTxt.setColumns(10);
		teamNameTxt.setBounds(520, 200, 200, 30);
		gameDataPanel.add(teamNameTxt);

		JLabel lblRocketShipName = new JLabel("What do you want to call your rocket?");
		lblRocketShipName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRocketShipName.setBounds(156, 259, 304, 29);
		gameDataPanel.add(lblRocketShipName);
		
		JLabel lblRocketShipNameError = new JLabel("");
		lblRocketShipNameError.setBounds(40, 280, 276, 29);
		lblRocketShipNameError.setBounds(156, 280, 304, 29);
		lblRocketShipNameError.setForeground(Color.red);
		lblRocketShipNameError.setFont(new Font("Tahoma", Font.PLAIN, 16));
		gameDataPanel.add(lblRocketShipNameError);

		JTextField rocketShipTxtField = new JTextField();
		rocketShipTxtField.setColumns(10);
		rocketShipTxtField.setBounds(520, 260, 200, 30);
		gameDataPanel.add(rocketShipTxtField);
		
		JLabel lblNumDays = new JLabel("How many days do you want to play?");
		lblNumDays.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNumDays.setBounds(160, 325, 300, 29);
		gameDataPanel.add(lblNumDays);

		JLabel lblNumDaysError = new JLabel("");
		lblNumDaysError.setBounds(40, 330, 276, 29);
		lblNumDaysError.setForeground(Color.red);
		gameDataPanel.add(lblNumDaysError);

		JSlider sliderNumDays = new JSlider();
		gameDataPanel.add(sliderNumDays);
		sliderNumDays.setSnapToTicks(true);
		sliderNumDays.setPaintLabels(true);
		sliderNumDays.setMinimum(3);
		sliderNumDays.setMaximum(10);
		sliderNumDays.setMajorTickSpacing(1);
		sliderNumDays.setBounds(520, 320, 264, 45);
		sliderNumDays.setValue(1);

		JLabel lblNumCharacters = new JLabel("How many characters do you want to play?");
		lblNumCharacters.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNumCharacters.setBounds(120, 400, 350, 29);
		gameDataPanel.add(lblNumCharacters);

		JLabel lblNumCharactersError = new JLabel("");
		lblNumCharactersError.setBounds(40, 410, 276, 29);
		lblNumCharactersError.setForeground(Color.red);
		gameDataPanel.add(lblNumCharactersError);

		JSlider sliderNumCharacters = new JSlider();
		sliderNumCharacters.setValue(2);
		sliderNumCharacters.setPaintLabels(true);
		sliderNumCharacters.setMajorTickSpacing(1);
		sliderNumCharacters.setMaximum(4);
		sliderNumCharacters.setMinimum(2);
		sliderNumCharacters.setSnapToTicks(true);
		sliderNumCharacters.setMinorTickSpacing(1);
		sliderNumCharacters.setBounds(520, 400, 264, 35);
		gameDataPanel.add(sliderNumCharacters);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.setBounds(781, 676, 130, 40);
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean hasErrors = false;
				String teamName = teamNameTxt.getText().trim();
				if (teamName.length() < 4 || teamName.length() > 8) {
					lblTeamNameError.setText("Must be between 4-8 characters long");
					hasErrors = true;
				}

				String rocketShipName = rocketShipTxtField.getText().trim();
				if (rocketShipName.length() < 4 || rocketShipName.length() > 8) {
					lblRocketShipNameError.setText("Must be between 4-8 characters long");
					hasErrors = true;
				}

				int numDays = sliderNumDays.getValue();
				if (!Funcs.isValidNumDays(numDays)) {
					lblNumDaysError.setText("Invalid number of days");
					hasErrors = true;
				}

				int numCharacters = sliderNumCharacters.getValue();
				if (!Funcs.isValidNumCharacters(numCharacters)) {
					lblNumCharactersError.setText("Invalid number of characters");
					hasErrors = true;
				}

				if (!hasErrors) {
					game.setGameEnvironment(new GameEnvironment(
							teamName,
							sliderNumCharacters.getValue(), 
							sliderNumDays.getValue(), 
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

		chooseCharacterPanel.add(getBigTitle());

		JLabel lblCharacterName = new JLabel("Character Name");
		lblCharacterName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCharacterName.setBounds(66, 200, 140, 27);
		chooseCharacterPanel.add(lblCharacterName);

		JLabel lblCharacterNameError = new JLabel("");
		lblCharacterNameError.setBounds(10, 220, 260, 27);
		lblCharacterNameError.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chooseCharacterPanel.add(lblCharacterNameError);
		lblCharacterNameError.setForeground(Color.red);
		
		JTextField characterNameTxt = new JTextField();
		characterNameTxt.setBounds(280, 200, 200, 30);
		chooseCharacterPanel.add(characterNameTxt);
		characterNameTxt.setColumns(10);
		
		JLabel lblCharacterType = new JLabel("Character Type");
		lblCharacterType.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCharacterType.setBounds(66, 260, 140, 27);
		chooseCharacterPanel.add(lblCharacterType);
		
		CrewMember crewMember = new CrewMember();
		String[] types = crewMember.getAllTypes();
		JComboBox type = new JComboBox(types);
		type.setBounds(280, 260, 200, 30);
		chooseCharacterPanel.add(type);
		
		JButton btnNext = new JButton("Add Member ");
		btnNext.setBounds(781, 676, 160, 40);
		chooseCharacterPanel.add(btnNext);
		
		// Barter is select box default value..
		Barter barter = new Barter("");
        JLabel lblMemberImage = new JLabel("");
        lblMemberImage.setBorder(new LineBorder(new Color(0, 0, 0)));
        lblMemberImage.setBounds(577, 200, 150, 150);
        lblMemberImage.setIcon(new ImageIcon(Image.getCrewMemberImagePath(barter)));
        chooseCharacterPanel.add(lblMemberImage);

		JLabel lblDescription = new JLabel(barter.getDescription());
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDescription.setBounds(577, 300, 300, 150);
		chooseCharacterPanel.add(lblDescription);

		JLabel lblHealth = new JLabel("Max Health: " + barter.getMaxHealth());
		lblHealth.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblHealth.setBounds(577, 340, 150, 150);
		chooseCharacterPanel.add(lblHealth);

		JLabel lblSpecialty = new JLabel("Specialty: " + barter.getSpecialty());
		lblSpecialty.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSpecialty.setBounds(577, 360, 150, 150);
		chooseCharacterPanel.add(lblSpecialty);

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
				lblDescription.setText(label);
				lblMemberImage.setIcon(Funcs.getScaledIcon(imagePath, 150,150));
				lblSpecialty.setText(specialtyLbl);
				lblHealth.setText(healthLbl);
			}
		});
		
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = characterNameTxt.getText().trim();
				if (name.length() < 4 || name.length() > 8) {
					lblCharacterNameError.setText("Must be between 4 and 8 characters");
				} else {
					lblCharacterNameError.setText("");
					numAddBtnPressed++;
					if (btnNext.getText() == "Continue") {
						cardLayout.show(contentPanel, "GAME_INFO_PANEL");
					}
					if (numAddBtnPressed == game.getGameEnvironment().getCrew().getNumMembers()) {
						type.setEnabled(false);
						characterNameTxt.setEditable(false);
						btnNext.setText("Continue");
					} else {
						String typeStr = String.valueOf(type.getSelectedItem());
						game.getGameEnvironment().getCrew().addCrewMember(typeStr, name);
						characterNameTxt.setText("");
						// Reset JCombo box
						type.setSelectedIndex(0);
						lblDescription.setText(barter.getDescription());
						lblHealth.setText("Max Health: " + barter.getMaxHealth());
						lblSpecialty.setText("Specialty: " + barter.getSpecialty());
					}
				}
			}
		});
	}

	public void addGameInformationPanel() {
		JPanel gameInfoPanel =  new JPanel();
		gameInfoPanel.setLayout(null);
		contentPanel.add(gameInfoPanel, "GAME_INFO_PANEL");

		gameInfoPanel.add(getBigTitle());

        JLabel lblWelcome = new JLabel("Welcome");
        lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblWelcome.setBounds(37, 180, 949, 57);
        gameInfoPanel.add(lblWelcome);

		JTextArea textArea = new JTextArea(
				 "You are stuck in our Solar System after having to perform an emergency landing on mercury which damaged your ship badly. " + 
				 "To win you must find enough transporter parts to be able to fix your spaceship. " + 
				 "Transporter parts can be found by exploring the planets, you can only find one transporter part per planet so you will have to travel to other planets to get all the parts. " + 
				 "Gold and medical supplies can also be found whilst exploring. " + 
				 "Use your gold at the shops to buy Food and medical supplies to keep your crew alive. " + 
				 "Each crew member will get two actions per day which can be used to Explore, Repair the Ships shield, sleep, or pilot the ship to another planet. " + 
				 "Good luck!", 6, 20);
		textArea.setSize(800, 225);
		textArea.setLocation(100, 250);
        textArea.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setOpaque(false);
        textArea.setEditable(false);

		gameInfoPanel.add(textArea);

		JButton startGameBtn = new JButton("Start Game");
		startGameBtn.setBounds(781, 676, 130, 40);
		gameInfoPanel.add(startGameBtn);

		startGameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
	}
}
