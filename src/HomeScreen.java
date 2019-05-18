import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import java.time.LocalTime;

public class HomeScreen {

	private JFrame window;

	private Game game;

    private GameEnvironment environment;

    private Crew crew;

    private SpaceShip spaceShip;

    private SpaceOutPost spaceOutPost;
	
	public JPanel contentPanel;

	public JPanel mainPanel;
	
	public CrewPanel crewStatusPanel;

	public JPanel inventoryPanel;

	public JPanel pilotPanel;

	public JPanel shipStatusPanel;

	public JPanel hospitalPanel;

    public JPanel explorePanel;

    public SpaceOutPostPanel spaceOutPostPanel;

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

    private LocalTime previousTime;

	/**
	 * Create the application.
	 */
	public HomeScreen(Game game) {
		// Set up some variables
        this.game = game;
        this.environment = game.getGameEnvironment();
        this.crew = environment.getCrew();
        this.spaceOutPost = environment.getSpaceOutPost();
        this.spaceShip = environment.getSpaceShip();

        this.previousTime = environment.getTime();

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
		window.setBounds(new Rectangle(0, 0, 1000, 850));
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
        // Here we refresh time.
        /*LocalTime time = environment.getTime();
        if (previousTime.getMinutes() < time.getMinutes()) {
            previousTime = time;
            environment.goToNextDay();
        }*/

		refreshTopPanel();
		refreshShipStatusPanel();

        spaceOutPostPanel.refresh();
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
        title.setFont(new Font("Tahoma", Font.PLAIN, 15));
        titlePanel.add(title);

        JPanel topPanel = new JPanel();

        JPanel homeBtnPanel = new JPanel();
        topPanel.add(homeBtnPanel);
        this.homeBtn = new JButton("Home");
        homeBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
        homeBtnPanel.add(homeBtn);

        JPanel dayLabelPanel = new JPanel();
        topPanel.add(dayLabelPanel);
        this.dayLabel = new JLabel("Day: " + environment.getCurrentDay() + "/" + environment.getNumDays());
        dayLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        dayLabelPanel.add(dayLabel);

        JPanel currentPlanetPanel = new JPanel();
        topPanel.add(currentPlanetPanel);
        this.currentPlanetLabel = new JLabel("Current Planet: " + environment.getCurrentPlanet());
        currentPlanetLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        currentPlanetPanel.add(currentPlanetLabel);

        JPanel peicesRequiredPanel = new JPanel();
        topPanel.add(peicesRequiredPanel);
        this.peicesRequiredLabel = new JLabel("Peices: " + spaceShip.getPeicesFound() + "/" + spaceShip.getPeicesRequired());
        peicesRequiredLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
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
        crewDetailBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		crewStatusBtnPanel.add(crewDetailBtn);
		
		JPanel shipStatusBtnPanel = new JPanel();
		homeSideBar.add(shipStatusBtnPanel);
		JButton shipBtn = new JButton("View Ship Status");
        shipBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		shipStatusBtnPanel.add(shipBtn);
		
		JPanel inventoryBtnPanel = new JPanel();
		homeSideBar.add(inventoryBtnPanel);
		JButton inventoryBtn = new JButton("Inventory"); // Takes you to inventory of spaceoutpost
        inventoryBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		inventoryBtnPanel.add(inventoryBtn);
		
		JPanel nextDayBtnPanel = new JPanel();
		homeSideBar.add(nextDayBtnPanel);
		JButton nextDayBtn = new JButton("Next Day");
		nextDayBtnPanel.add(nextDayBtn);

		JPanel homeContent = new JPanel();
		homePanel.add(homeContent);
		homeContent.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton exploreBtn = new JButton("Explore");
        exploreBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		homeContent.add(exploreBtn);
		JButton shopBtn = new JButton("Shop");
        shopBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		homeContent.add(shopBtn);

		homeBtn.setEnabled(false);

		nextDayBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		if (environment.isValidCurrentDay()) {
					// Next day.....
					environment.goToNextDay();

					for (CrewMember member: crew.getMembers()) {
						member.decrementTiredness(10);
                        member.resetActions();
                        // check if is sick and remove tiredness
                        if (member.isSick()) {
                            member.decrementCurrentHealth();
                        }
                    }

					// Random event..
					int randomEvent = environment.determineRandomEvent();
		            if (randomEvent == 1) {
		            	JOptionPane.showMessageDialog(null, "Random Event: Alien Pirates (");
		            } else if (randomEvent == 2) {
		                JOptionPane.showMessageDialog(null, "Random Event: Space Plague");
                        // Check if member is dead.

		            } else {
		                JOptionPane.showMessageDialog(null, "Random Event: Asteroid Belt");
		            }
				} else {
					nextDayBtn.setEnabled(false);
				}
                
				refresh();

                spaceOutPostPanel.refresh();
                crewStatusPanel.refresh();
			}
		});

		shopBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(contentPanel, "SHOP");
				enableHomeButton();
                crewStatusPanel.refresh();
                spaceOutPostPanel.refresh();
            }
		});

		inventoryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(contentPanel, "SHOP");
                enableHomeButton();
                crewStatusPanel.refresh();
                spaceOutPostPanel.refresh();
			}
		});
		
		shipBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshShipStatusPanel();
				cardLayout.show(contentPanel, SHIP_STATUS);
				enableHomeButton();
                crewStatusPanel.refresh();
                spaceOutPostPanel.refresh();
			}
		});
		
		crewDetailBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                cardLayout.show(contentPanel, CREW_STATUS);
                crewStatusPanel.refresh();
                spaceOutPostPanel.refresh();
				enableHomeButton();
			}
		});

		homeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                refresh();
                crewStatusPanel.refresh();
                spaceOutPostPanel.refresh();
                cardLayout.show(contentPanel, HOME);
			    enableHomeButton();
            }
		});

        exploreBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                crewStatusPanel.refresh();
                spaceOutPostPanel.refresh();
                cardLayout.show(contentPanel, EXPLORE);
                enableHomeButton();
                refreshExplorePanel();
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
		shipStatusPanel.setLayout(null);

        JLabel spaceShipImageLabel = new JLabel("");
        spaceShipImageLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
        spaceShipImageLabel.setBounds(100, 50, 200, 200);
        spaceShipImageLabel.setIcon(new ImageIcon(getClass().getResource(spaceShip.getImagePath())));
        shipStatusPanel.add(spaceShipImageLabel);

        JLabel spaceShipName = new JLabel(spaceShip.getName());
        spaceShipName.setFont(new Font("Dialog", Font.BOLD, 18));
        spaceShipName.setHorizontalAlignment(SwingConstants.CENTER);
        spaceShipName.setBounds(100, 250, 200, 50);

		JLabel spaceShipHealth = new JLabel("Shield Health: " + spaceShip.getShieldHealth());
        spaceShipHealth.setBounds(350, 50, 250, 50);

		JLabel peicesRequired = new JLabel("Peices Required: " + spaceShip.getPeicesRequired());
        peicesRequired.setBounds(350, 80, 250, 50);

        JLabel partsFound = new JLabel("Peices found: " + spaceShip.getPeicesFound());
        partsFound.setBounds(350, 110, 250, 50);

		shipStatusPanel.add(spaceShipName);
		shipStatusPanel.add(spaceShipHealth);
		shipStatusPanel.add(peicesRequired);
		shipStatusPanel.add(partsFound);
    }

    public void addExplorePanel() {
        this.explorePanel = new JPanel();
        contentPanel.add(explorePanel, EXPLORE);
    }

    public void refreshExplorePanel() {
        // Get crew member select...
        explorePanel.removeAll();

        JLabel titleLabel = new JLabel("Choose a member and go explore: " + environment.getCurrentPlanet());
        explorePanel.add(titleLabel);

        ButtonGroup memberButtonGroup = new ButtonGroup();

        for (CrewMember member: crew.getMembers()) {
            JRadioButton memberRadio = new JRadioButton(member.getName());
            if (!member.hasActionsLeft()) {
                memberRadio.setEnabled(false);
            }
            memberRadio.putClientProperty("CrewMember", member);

            JLabel memberImageLabel = new JLabel("");
            memberImageLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
            memberImageLabel.setIcon(new ImageIcon(getClass().getResource(member.getIconPath())));

            memberButtonGroup.add(memberRadio);
            explorePanel.add(memberImageLabel);
            explorePanel.add(memberRadio);
        }


        JButton exploreBtn = new JButton("Go exploring!");
        explorePanel.add(exploreBtn);

        exploreBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JRadioButton selectedMemberRadio = Funcs.selectedButton(memberButtonGroup);
                CrewMember actionedMember = (CrewMember) selectedMemberRadio.getClientProperty("CrewMember");

                actionedMember.removeAction();

                String foundItem = environment.searchPlanetForParts();
                JOptionPane.showMessageDialog(null, foundItem);
            }
        });
    }

    public void addSpaceOutPostPanel() {
        this.spaceOutPostPanel = new SpaceOutPostPanel(spaceOutPost);
        contentPanel.add(spaceOutPostPanel, "SHOP");
    }
}
