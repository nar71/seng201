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

public class HomeScreenPanel {

	private JFrame window;
	private Game game;
	public JPanel contentPanel;
	public CardLayout cardLayout;
	/**
	 * Create the application.
	 */
	public HomeScreenPanel(Game game) {
		this.game = game;
		initialize();
		window.setVisible(true);
	}

	/*public void finishedWindow() {
		window.dispose();
	}

	public void closeWindow() {
		game.closeHomeScreen(this);
	}*/

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setBounds(new Rectangle(0, 0, 880, 610));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		JPanel topTopPanel = new JPanel();
		JPanel topPanel = new JPanel();
		
		JPanel contentPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		mainPanel.add(panel);
		
		JLabel title = new JLabel("Game Name");
		panel.add(title);
		JButton homeBtn = new JButton("Home");
		JLabel days = new JLabel("Day: " + game.getGameEnvironment().getCurrentDay() + "/" + game.getGameEnvironment().getNumDays());
		JLabel currPlanet = new JLabel("Current Planet: " + game.getGameEnvironment().getCurrentPlanet());
		topPanel.add(homeBtn);
		topPanel.add(days);
		topPanel.add(currPlanet);
		mainPanel.add(topPanel);
		mainPanel.add(contentPanel);

		CardLayout cardLayout = new CardLayout();
		contentPanel.setLayout(cardLayout);
		
		// HOME PANEL
		JPanel homePanel = new JPanel();
		homePanel.setLayout(new BoxLayout(homePanel, BoxLayout.X_AXIS));
		JPanel homeSideBar = new JPanel();
		homePanel.add(homeSideBar);
		homeSideBar.setLayout(new BoxLayout(homeSideBar, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		homeSideBar.add(panel_1);
		JButton crewDetailBtn = new JButton("View Crew Status");
		panel_1.add(crewDetailBtn);
		
		JPanel panel_2 = new JPanel();
		homeSideBar.add(panel_2);
		JButton shipBtn = new JButton("View Ship Status");
		panel_2.add(shipBtn);
		
		JPanel panel_3 = new JPanel();
		homeSideBar.add(panel_3);
		JButton inventoryBtn = new JButton("Inventory");
		panel_3.add(inventoryBtn);
		
		JPanel panel_4 = new JPanel();
		homeSideBar.add(panel_4);
		JButton nextDayBtn = new JButton("Next Day");
		panel_4.add(nextDayBtn);

		JPanel homeContent = new JPanel();
		homePanel.add(homeContent);
		homeContent.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JButton rocketBtn = new JButton("New Planet");
		homeContent.add(rocketBtn);
		JButton exploreBtn = new JButton("Explore");
		homeContent.add(exploreBtn);
		JButton shopBtn = new JButton("Shop");
		homeContent.add(shopBtn);
		
		contentPanel.add(homePanel, "HOME");

		// Crew Status Panel
		CrewStatusPanel crewStatusPanel = new CrewStatusPanel(game);
		contentPanel.add(crewStatusPanel, "CREW_STATUS");
		
		// Ship Status PANEL
		JPanel shipStatusPanel = new JPanel();
		SpaceShip spaceShip = game.getGameEnvironment().getSpaceShip();
		shipStatusPanel.setLayout(new BoxLayout(shipStatusPanel, BoxLayout.Y_AXIS));
		JLabel spaceShipName = new JLabel("Space Ship Name: " + spaceShip.getName());
		JLabel spaceShipHealth = new JLabel("Shield Health: " + spaceShip.getShieldHealth());
		JLabel peicesRequired = new JLabel("Peices Required: " + spaceShip.getPeicesRequired());
		JLabel partsFound = new JLabel("Peices found: " + spaceShip.getPeicesFound());
			
		JButton pioletNewPlanetBtn = new JButton("Piolet ship to new planet");
		shipStatusPanel.add(pioletNewPlanetBtn);

		pioletNewPlanetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(contentPanel, "PILOT");
			}
		});

		shipStatusPanel.add(spaceShipName);
		shipStatusPanel.add(spaceShipHealth);
		shipStatusPanel.add(peicesRequired);
		shipStatusPanel.add(partsFound);
		
		contentPanel.add(shipStatusPanel, "SHIP_STATUS");


		// PILOT TO NEW SHIP
		JPanel pilotPanel = new JPanel();
        Planet planet = game.getGameEnvironment().getPlanet();
        Planet[] allPlanets = planet.getAll();

        ButtonGroup bgroup = new ButtonGroup();

        for (int d = 0; d < allPlanets.length; d++) {
            if (!allPlanets[d].getName().equals(game.getGameEnvironment().getCurrentPlanet().getName())) {
                JRadioButton radioBtn = new JRadioButton(allPlanets[d].getName());
                bgroup.add(radioBtn);
                pilotPanel.add(radioBtn);
            }
        }

        ButtonGroup bgroup2 = new ButtonGroup();
        for (CrewMember m: game.getGameEnvironment().getCrew().getMembers()) {
        	JRadioButton radioBtn2 = new JRadioButton(m.getName());
        	bgroup2.add(radioBtn2);
        	pilotPanel.add(radioBtn2);
        }

		contentPanel.add(pilotPanel, "PILOT");

		
		// INVENTORY PANEL
		JPanel inventoryPanel = new JPanel();
		inventoryPanel.setLayout(new GridLayout(1, 0, 0, 0));
		SpaceOutPost spaceOutPost = game.getGameEnvironment().getSpaceOutPost();
		JPanel medPanelMain = new JPanel();
		medPanelMain.setLayout(new BoxLayout(medPanelMain, BoxLayout.Y_AXIS));
		for (MedicalSupply m : spaceOutPost.getMedicalSupplies()) {
			JPanel medPanel = new JPanel();
			JLabel medLabel = new JLabel(m.getType() + "(" + m.getCount() + ")");
			medPanel.add(medLabel);
			medPanelMain.add(medPanel);
		}
		inventoryPanel.add(medPanelMain);

		JPanel foodPanelMain = new JPanel();
		foodPanelMain.setLayout(new BoxLayout(foodPanelMain, BoxLayout.Y_AXIS));
		for (Food f : spaceOutPost.getFoods()) {
			JPanel foodPanel = new JPanel();
			JLabel foodLabel = new JLabel(f.getType() + "(" + f.getCount() + ")");
			foodPanel.add(foodLabel);
			foodPanelMain.add(foodPanel);
		}
		inventoryPanel.add(foodPanelMain);
		contentPanel.add(inventoryPanel, "INVENTORY");
		
		// SHOP PANEL...
		SpaceOutPostPanel shop = new SpaceOutPostPanel();
		contentPanel.add(shop, "SHOP");
		
		// SHOW DEFAULT PANEL
		cardLayout.show(contentPanel, "HOME");
		
		//homeBtn.setEnabled(false);
		
		// Action listeners
		nextDayBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		if (game.getGameEnvironment().isValidCurrentDay()) {
					// Next day.....
					game.getGameEnvironment().goToNextDay();
					// Set an alert
					// Tiredness must increase
					for (CrewMember m: game.getGameEnvironment().getCrew().getMembers()) {
						m.incrementTiredness(10);
					}

					// Random event..
					int randomEvent = game.getGameEnvironment().determineRandomEvent();
		            if (randomEvent == 1) {
		            	JOptionPane.showMessageDialog(null, "Random Event: Alien Pirates (");
		            } else if (randomEvent == 2) {
		                JOptionPane.showMessageDialog(null, "Random Event: Space Plague");
		            } else {
		                JOptionPane.showMessageDialog(null, "Random Event: Asteroid Belt");
		            }

					days.setText("Day: " + game.getGameEnvironment().getCurrentDay() + "/" + game.getGameEnvironment().getNumDays());
				} else {
					nextDayBtn.setEnabled(false);
				}
				//rewStatusPanel.refresh();
			}
		});

		shopBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(contentPanel, "SHOP");
				homeBtn.setEnabled(true);
			}
		});

		inventoryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(contentPanel, "INVENTORY");
				homeBtn.setEnabled(true);
			}
		});
		
		shipBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(contentPanel, "SHIP_STATUS");
				homeBtn.setEnabled(true);
			}
		});
		
		crewDetailBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(contentPanel, "CREW_STATUS");
				homeBtn.setEnabled(true);
			}
		});

		homeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(contentPanel, "HOME");
			}
		});

		window.getContentPane().setLayout(new BoxLayout(window.getContentPane(), BoxLayout.X_AXIS));
		window.getContentPane().add(mainPanel);
		window.setVisible(true);
	}
}
