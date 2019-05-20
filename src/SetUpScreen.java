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
		
		/**JLabel lblGameTitle = new JLabel("GAME NAME");
		lblGameTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblGameTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameTitle.setBounds(12, 43, 974, 58);
		startGamePanel.add(lblGameTitle);**/

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
		
		JLabel teamNameLabel = new JLabel("What do you want to call your team?");
		teamNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        teamNameLabel.setBounds(160, 200, 300, 29);
		gameDataPanel.add(teamNameLabel);

		JLabel teamNameErrorLabel = new JLabel("");
		teamNameErrorLabel.setBounds(80, 220, 276, 29);
		teamNameErrorLabel.setBounds(160, 220, 300, 29);
		teamNameErrorLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		teamNameErrorLabel.setForeground(Color.red);
		gameDataPanel.add(teamNameErrorLabel);
		
		JTextField teamNameTxt = new JTextField();
		teamNameTxt.setColumns(10);
		teamNameTxt.setBounds(520, 200, 200, 30);
		gameDataPanel.add(teamNameTxt);

		JLabel lblRocketShipName = new JLabel("What do you want to call your rocket?");
		lblRocketShipName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRocketShipName.setBounds(156, 259, 304, 29);
		gameDataPanel.add(lblRocketShipName);
		
		JLabel rocketShipNameErrorLabel = new JLabel("");
		rocketShipNameErrorLabel.setBounds(40, 280, 276, 29);
		rocketShipNameErrorLabel.setBounds(156, 280, 304, 29);
		rocketShipNameErrorLabel.setForeground(Color.red);
		rocketShipNameErrorLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		gameDataPanel.add(rocketShipNameErrorLabel);

		JTextField rocketShipTxtField = new JTextField();
		rocketShipTxtField.setColumns(10);
		rocketShipTxtField.setBounds(520, 260, 200, 30);
		gameDataPanel.add(rocketShipTxtField);
		
		JLabel lblNumDays = new JLabel("How many days do you want to play?");
		lblNumDays.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNumDays.setBounds(160, 325, 300, 29);
		gameDataPanel.add(lblNumDays);

		JLabel numDaysErrorLabel = new JLabel("");
		numDaysErrorLabel.setBounds(40, 330, 276, 29);
		numDaysErrorLabel.setForeground(Color.red);
		gameDataPanel.add(numDaysErrorLabel);

		JSlider sliderDaysAmount = new JSlider();
		gameDataPanel.add(sliderDaysAmount);
		sliderDaysAmount.setSnapToTicks(true);
		sliderDaysAmount.setPaintLabels(true);
		sliderDaysAmount.setMinimum(3);
		sliderDaysAmount.setMaximum(10);
		sliderDaysAmount.setMajorTickSpacing(1);
		sliderDaysAmount.setBounds(520, 320, 264, 45);
		sliderDaysAmount.setValue(1);

		JLabel lblNumCharacters = new JLabel("How many characters do you want to play?");
		lblNumCharacters.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNumCharacters.setBounds(120, 400, 350, 29);
		gameDataPanel.add(lblNumCharacters);

		JLabel numCharactersErrorLabel = new JLabel("");
		numCharactersErrorLabel.setBounds(40, 410, 276, 29);
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
		sliderCharactersAmount.setBounds(520, 400, 264, 35);
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

		chooseCharacterPanel.add(getBigTitle());

		JLabel lblCharacterName = new JLabel("Character Name");
		lblCharacterName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCharacterName.setBounds(66, 200, 140, 27);
		chooseCharacterPanel.add(lblCharacterName);

		JLabel lblCharacterNameError = new JLabel("");
		lblCharacterNameError.setBounds(66, 215, 126, 27);
		chooseCharacterPanel.add(lblCharacterNameError);
		lblCharacterNameError.setForeground(Color.red);
		
		JTextField characterNameTxt = new JTextField();
		characterNameTxt.setBounds(267, 200, 200, 30);
		chooseCharacterPanel.add(characterNameTxt);
		characterNameTxt.setColumns(10);
		
		JLabel lblCharacterType = new JLabel("Character Type");
		lblCharacterType.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCharacterType.setBounds(66, 240, 140, 27);
		chooseCharacterPanel.add(lblCharacterType);
		
		CrewMember crewMember = new CrewMember();
		String[] types = crewMember.getAllTypes();
		JComboBox type = new JComboBox(types);

		type.setBounds(267, 240, 200, 30);
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
        memberImageLabel.setBounds(577, 200, 150, 150);
        memberImageLabel.setIcon(new ImageIcon(Image.getCrewMemberImagePath(barter)));
        chooseCharacterPanel.add(memberImageLabel);

		JLabel descriptionLabel = new JLabel(barter.getDescription());
		descriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		descriptionLabel.setBounds(577, 300, 300, 150);
		chooseCharacterPanel.add(descriptionLabel);

		JLabel healthLbl = new JLabel("Max Health: " + barter.getMaxHealth());
		healthLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		healthLbl.setBounds(577, 340, 150, 150);
		chooseCharacterPanel.add(healthLbl);

		JLabel specialtyLbl = new JLabel("Specialty: " + barter.getSpecialty());
		specialtyLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		specialtyLbl.setBounds(577, 360, 150, 150);
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
