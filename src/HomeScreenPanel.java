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

		this.cardLayout = new CardLayout();
		contentPanel.setLayout(cardLayout);
		
		// HOME PANEL
		addHomePanel();

		// Crew Status Panel
		addCrewStatusPanel();
		
		// Ship Status PANEL
		addShipStatusPanel();
		
		// SHOP PANEL...
        addSpaceOutPostPanel();

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

        JPanel homeBtnPanel = new JPanel();
        topPanel.add(homeBtnPanel);
        this.homeBtn = new JButton("Home");
        homeBtnPanel.add(homeBtn);

        JPanel dayLabelPanel = new JPanel();
        topPanel.add(dayLabelPanel);
        this.dayLabel = new JLabel("Day: " + environment.getCurrentDay() + "/" + environment.getNumDays());
        dayLabelPanel.add(dayLabel);

        JPanel currentPlanetPanel = new JPanel();
        topPanel.add(currentPlanetPanel);
        this.currentPlanetLabel = new JLabel("Current Planet: " + environment.getCurrentPlanet());
        currentPlanetPanel.add(currentPlanetLabel);

        JPanel peicesRequiredPanel = new JPanel();
        topPanel.add(peicesRequiredPanel);
        this.peicesRequiredLabel = new JLabel("Peices: " + spaceShip.getPeicesFound() + "/" + spaceShip.getPeicesRequired());
        peicesRequiredPanel.add(peicesRequiredLabel);
        
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
		
		JPanel crewStatusBtnPanel = new JPanel();
		homeSideBar.add(crewStatusBtnPanel);
		JButton crewDetailBtn = new JButton("View Crew Status");
		crewStatusBtnPanel.add(crewDetailBtn);
		
		JPanel shipStatusBtnPanel = new JPanel();
		homeSideBar.add(shipStatusBtnPanel);
		JButton shipBtn = new JButton("View Ship Status");
		shipStatusBtnPanel.add(shipBtn);
		
		JPanel inventoryBtnPanel = new JPanel();
		homeSideBar.add(inventoryBtnPanel);
		JButton inventoryBtn = new JButton("Inventory"); // Takes you to inventory of spaceoutpost
		inventoryBtnPanel.add(inventoryBtn);
		
		JPanel nextDayBtnPanel = new JPanel();
		homeSideBar.add(nextDayBtnPanel);
		JButton nextDayBtn = new JButton("Next Day");
		nextDayBtnPanel.add(nextDayBtn);

		JPanel homeContent = new JPanel();
		homePanel.add(homeContent);
		homeContent.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton exploreBtn = new JButton("Explore");
		homeContent.add(exploreBtn);
		JButton shopBtn = new JButton("Shop");
		homeContent.add(shopBtn);

		homeBtn.setEnabled(false);

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
				cardLayout.show(contentPanel, "SHOP");
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
		this.crewStatusPanel = new CrewPanel(environment);
        contentPanel.add(crewStatusPanel, CREW_STATUS);
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

    public void addExplorePanel() {
        this.explorePanel = new JPanel();
        contentPanel.add(explorePanel, EXPLORE);
    }

    public void addSpaceOutPostPanel() {
        this.spaceOutPostPanel = new SpaceOutPostPanel(spaceOutPost);
        contentPanel.add(spaceOutPostPanel, "SHOP");
    }
}
