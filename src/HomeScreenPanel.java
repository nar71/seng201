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

    private GameEnvironment environment;

    private Crew crew;

    private SpaceShip spaceShip;

    private SpaceOutPost spaceOutPost;
	
	public JPanel contentPanel;

	public JPanel mainPanel;
	
	public JPanel crewStatusPanel;

	public JPanel inventoryPanel;

	public JPanel pilotPanel;

	public JPanel shipStatusPanel;

	public JPanel hospitalPanel;

    public JPanel explorePanel;

    public JPanel spaceOutPostPanel;

	public CardLayout cardLayout;

	public JLabel dayLabel;

	public JLabel currentPlanetLabel;

    public JLabel peicesRequiredLabel;

	public JButton homeBtn;

	private static final String HOME = "HOME";

	private static final String CREW_STATUS = "CREW_STATUS";

	private static final String SHIP_STATUS = "SHIP_STATUS";

	private static final String INVENTORY = "INVENTORY";

	private static final String HOSPITAL = "HOSPITAL";

    private static final String EXPLORE = "EXPLORE";

	/**
	 * Create the application.
	 */
	public HomeScreenPanel(Game game) {
		// Set up some variables
        this.game = game;
        this.environment = game.getGameEnvironment();
        this.crew = environment.getCrew();
        this.spaceOutPost = environment.getSpaceOutPost();
        this.spaceShip = environment.getSpaceShip();

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
	public void initialize() {
		window = new JFrame();
		window.setBounds(new Rectangle(0, 0, 880, 610));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.mainPanel = new JPanel();
		this.contentPanel = new JPanel();

		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        addTopPanel();

		mainPanel.add(contentPanel);

		// TOP PANEL
		//addTopPanel();

		this.cardLayout = new CardLayout();
		contentPanel.setLayout(cardLayout);
		
		// HOME PANEL
		addHomePanel();

		// Crew Status Panel
		addCrewStatusPanel();
		
		// Ship Status PANEL
		addShipStatusPanel();

		// PILOT TO NEW SHIP
		addPilotToNewPlanetPanel();
		
		// INVENTORY PANEL
		addInventoryPanel();
		
		// SHOP PANEL...
        addSpaceOutPostPanel();

		// A PANEL TO Apply items to members
		addHospitalPanel();

        // Explore Panel
        addExplorePanel();

		// SHOW DEFAULT PANEL
		cardLayout.show(contentPanel, "HOME");
		
		// Action listeners
		window.getContentPane().setLayout(new BoxLayout(window.getContentPane(), BoxLayout.X_AXIS));
		window.getContentPane().add(mainPanel);
		window.setVisible(true);
	}

	public void refresh() {
		refreshTopPanel();
		refreshCrewStatusPanel();
		refreshShipStatusPanel();
	}

    private void enableHomeButton() {
        homeBtn.setEnabled(true);
    }

    private void disableHomeButton() {
        homeBtn.setEnabled(false);
    }

	public void addTopPanel() {
        JPanel titlePanel = new JPanel();
        mainPanel.add(titlePanel);
        
        JLabel title = new JLabel("Game Name");
        titlePanel.add(title);

        JPanel topPanel = new JPanel();

        this.homeBtn = new JButton("Home");
        this.dayLabel = new JLabel("Day: " + environment.getCurrentDay() + "/" + environment.getNumDays());
        this.currentPlanetLabel = new JLabel("Current Planet: " + environment.getCurrentPlanet());
        this.peicesRequiredLabel = new JLabel("Peices: " + spaceShip.getPeicesFound() + "/" + spaceShip.getPeicesRequired());
 
        topPanel.add(homeBtn);
        topPanel.add(dayLabel);
        topPanel.add(currentPlanetLabel);
        topPanel.add(peicesRequiredLabel);
        
        mainPanel.add(topPanel);
    }

	public void refreshTopPanel() {
		dayLabel.setText("Day: " +  environment.getCurrentDay() + "/" + environment.getNumDays());
		currentPlanetLabel.setText("Current Planet: " + environment.getCurrentPlanet());
        peicesRequiredLabel.setText("Peices: " + spaceShip.getPeicesFound() + "/" + spaceShip.getPeicesRequired());
	}

	public void addHomePanel() {
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
		JButton hospitalBtn = new JButton("Hospital");
		homeContent.add(hospitalBtn);

		homeBtn.setEnabled(false);

		hospitalBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(contentPanel, "HOSPITAL");
			}
		});

		nextDayBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		if (environment.isValidCurrentDay()) {
					// Next day.....
					environment.goToNextDay();
					// Set an alert
					// Tiredness must increase
					for (CrewMember m: crew.getMembers()) {
						m.incrementTiredness(10);
					}

					// Random event..
					int randomEvent = environment.determineRandomEvent();
		            if (randomEvent == 1) {
		            	JOptionPane.showMessageDialog(null, "Random Event: Alien Pirates (");
		            } else if (randomEvent == 2) {
		                JOptionPane.showMessageDialog(null, "Random Event: Space Plague");
		            } else {
		                JOptionPane.showMessageDialog(null, "Random Event: Asteroid Belt");
		            }
				} else {
					nextDayBtn.setEnabled(false);
				}
				refresh();
			}
		});

		shopBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(contentPanel, "SHOP");
				enableHomeButton();
			}
		});

		inventoryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshInventoryPanel();
				cardLayout.show(contentPanel, INVENTORY);
				enableHomeButton();
			}
		});
		
		shipBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshShipStatusPanel();
				cardLayout.show(contentPanel, SHIP_STATUS);
				enableHomeButton();
			}
		});
		
		crewDetailBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshCrewStatusPanel();
				cardLayout.show(contentPanel, CREW_STATUS);
				enableHomeButton();
			}
		});

		homeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                refresh();
                cardLayout.show(contentPanel, HOME);
			    enableHomeButton();
            }
		});

        exploreBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                cardLayout.show(contentPanel, EXPLORE);
                enableHomeButton();
            }
        });

		contentPanel.add(homePanel, HOME);
	}

	public void addCrewStatusPanel() {
		this.crewStatusPanel = new JPanel();
		contentPanel.add(crewStatusPanel, CREW_STATUS);
	}

	public void refreshCrewStatusPanel() {
		crewStatusPanel.removeAll();

        for (CrewMember member: game.getGameEnvironment().getCrew().getMembers()) {
            JPanel panelMember = new JPanel();
            panelMember.setLayout(new BoxLayout(panelMember, BoxLayout.Y_AXIS));
            crewStatusPanel.add(panelMember);

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
            panelMember.add(panel8M);
            
            JPanel buttonSleep = new JPanel();
            JButton btnSleep = new JButton("Sleep");
            buttonSleep.add(btnSleep);
            panelMember.add(buttonSleep);
        }
    }

    public void addShipStatusPanel() {
		this.shipStatusPanel = new JPanel();
		contentPanel.add(shipStatusPanel, "SHIP_STATUS");
    }

    public void refreshShipStatusPanel() {
    	shipStatusPanel.removeAll();

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
    }

    public void addPilotToNewPlanetPanel() {
		this.pilotPanel = new JPanel();
		contentPanel.add(pilotPanel, "PILOT");
    }


    public void refreshPilotToNewPlanetPanel() {
    	pilotPanel.removeAll();

        Planet planet = environment.getPlanet();
        Planet[] allPlanets = planet.getAll();

        ButtonGroup bgroup = new ButtonGroup();

        for (int d = 0; d < allPlanets.length; d++) {
            if (!allPlanets[d].getName().equals(environment.getCurrentPlanet().getName())) {
                JRadioButton radioBtn = new JRadioButton(allPlanets[d].getName());
                bgroup.add(radioBtn);
                pilotPanel.add(radioBtn);
            }
        }

        ButtonGroup bgroup2 = new ButtonGroup();
        for (CrewMember m: crew.getMembers()) {
        	JRadioButton radioBtn2 = new JRadioButton(m.getName());
        	bgroup2.add(radioBtn2);
        	pilotPanel.add(radioBtn2);
        }
    }

    public void addInventoryPanel() {
		this.inventoryPanel = new JPanel();
		inventoryPanel.setLayout(new GridLayout(1, 0, 0, 0));
		contentPanel.add(inventoryPanel, "INVENTORY");
    }

    public void refreshInventoryPanel() {
    	inventoryPanel.removeAll();

        JPanel coinsPanel = new JPanel();
        JLabel coinsLabel = new JLabel("Current coins: " + spaceOutPost.getCurrentMoney());
        coinsPanel.add(coinsLabel);
        inventoryPanel.add(coinsPanel);

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
    }

    public void addHospitalPanel() {
		this.hospitalPanel = new JPanel();
		contentPanel.add(hospitalPanel, "HOSPITAL");
    }

    public void refreshHospitalPanel() {
    	hospitalPanel.removeAll();

		JPanel medPanelMain2 = new JPanel();
		medPanelMain2.setLayout(new BoxLayout(medPanelMain2, BoxLayout.X_AXIS));

		medPanelMain2.add(new JLabel("What item would you like to apply?"));
		ButtonGroup hospitalJGroup = new ButtonGroup();

		for (MedicalSupply m : spaceOutPost.getMedicalSupplies()) {
			JPanel medPanel = new JPanel();
			JRadioButton medRadio = new JRadioButton(m.getType());
			if (!m.exists()) {
				medRadio.setEnabled(false);
			}
			hospitalJGroup.add(medRadio);
			medPanel.add(medRadio);
			medPanelMain2.add(medPanel);
		}
		hospitalPanel.add(medPanelMain2);
    }

    public void addExplorePanel() {
        this.explorePanel = new JPanel();
        contentPanel.add(explorePanel, EXPLORE);
    }

    public void addSpaceOutPostPanel() {
        this.spaceOutPostPanel = new SpaceOutPostPanel(spaceOutPost);
        contentPanel.add(spaceOutPostPanel, "SHOP");
    }
}
