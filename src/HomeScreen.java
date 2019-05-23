import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;

import java.time.LocalTime;

public class HomeScreen {

    /**
     * A string representation of the home panel, used for Card Layout.
     */
    private static final String HOME_PANEL_STRING = "HOME_PANEL";

    /**
     * A string representation of the crew status panel, used for Card Layout.
     */
    private static final String CREW_STATUS_PANEL_STRING = "CREW_STATUS_PANEL";

    /**
     * A string representation of the ship status panel, used for Card Layout.
     */
    private static final String SHIP_STATUS_PANEL_STRING = "SHIP_STATUS_PANEL";

    /**
     * A string representation of the inventory, used for Card Layout.
     */
    private static final String INVENTORY_PANEL_STRING = "INVENTORY_PANEL";

    /**
     * A string representation of the explore panel, used for Card Layout.
     */
    private static final String EXPLORE_PANEL_STRING = "EXPLORE_PANEL";

    /**
     * Main JFrame.
     */
    public JFrame window;

    /**
     * Main game manger
     */
	public Game game;

    /**
     * The main game, the Game Environment holds all the data as the user goes through the game.
     * It holds the crew members, space out post, space ship, medical supplies etc.
     */ 
    public GameEnvironment environment;

    /**
     * The Crew. Holds an array list of the crew members and can make these members do actions.
     */
    private Crew crew;

    /**
     * The space ship. Holds all data reguarding the ship.
     */
    private SpaceShip spaceShip;

    /**
     * The Space Out Post. Holds all medical supplies and foods.
     */
    private SpaceOutPost spaceOutPost;
	
    /*
     * The main content panel, which is used for every different card.
     */
	public JPanel contentPanel;

    /**
     * The main panel, in which the content panel is used on.
     */
	public JPanel mainPanel;

    /**
     * A panel component of content panel.
     */
	public CrewPanel crewStatusPanel;

    /**
     * A panel component of content panel.
     */
	public JPanel inventoryPanel;

    /**
     * A panel component of content panel.
     */
	public JPanel pilotPanel;

    /**
     * A panel component of content panel.
     */
	public JPanel shipStatusPanel;

    /**
     * A panel component of content panel.
     */
    public JPanel explorePanel;

    /**
     * A panel component of content panel.
     */
    public SpaceOutPostPanel spaceOutPostPanel;

    /**
     * A card layout used by content.
     */
	public CardLayout cardLayout;

    /**
     * A label component of the home card.
     */
	public JLabel lblCurrentDay;

    /**
     * A label component of the home card.
     */
	public JLabel lblCurrentPlanet;

    /**
     * A label component of the home card.
     */
    public JLabel lblPeicesRequired;

    /**
     * A home button. Shown everywhere in the game.
     */
	public JButton homeBtn;

	/**
	 * HomeScreen constructor
     * @param game The main game manager
	 */
	public HomeScreen(Game game) {
        this.game = game;
        this.environment = game.getGameEnvironment();
        this.crew = environment.getCrew();
        this.spaceOutPost = environment.getSpaceOutPost();
        this.spaceShip = environment.getSpaceShip();

		initialize();

		window.setVisible(true);
	}

    /**
     * Closes the home screen
     */
	public void finishedWindow() {
		window.dispose();
	}

    /**
     * Closes home screen and opens either a victory or defeat screen
     * @param isVictory Boolean for is victorious or not
     * @param message The error message.
     */
	public void closeWindow(boolean isVictory, String message) {
		game.closeHomeScreen(this, isVictory, message);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		window = new JFrame();
		window.setBounds(new Rectangle(0, 0, 1000, 810));
        window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.cardLayout = new CardLayout();
		this.mainPanel = new JPanel();
		this.contentPanel = new JPanel();

		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        contentPanel.setLayout(cardLayout);

        addTopPanel();

		mainPanel.add(contentPanel);

		addHomePanel();
		addCrewStatusPanel();
		addShipStatusPanel();
        addSpaceOutPostPanel();
        addExplorePanel();

		cardLayout.show(contentPanel, "HOME");
		
		window.getContentPane().setLayout(new BoxLayout(window.getContentPane(), BoxLayout.X_AXIS));
		window.getContentPane().add(mainPanel);
		window.setVisible(true);
	}

    /**
     * Refresh SpaceOutPost panel by showing the main inventory card and refreshing it.
     */
    private void refreshSpaceOutPostPanel() {
        spaceOutPostPanel.showInventoryCard();
    }

    /**
     * Refresh CrewStatusPanel panel by showing the main inventory card and refreshing it.
     */
    private void refreshCrewStatusPanel() {
        crewStatusPanel.showCrewStatusCard();
    }

    /**
     * Makes the home button clickable.
     */
    private void enableHomeButton() {
        homeBtn.setEnabled(true);
    }

    /**
     * Makes the home button un clickable.
     */
    private void disableHomeButton() {
        homeBtn.setEnabled(false);
    }

    /**
     * Adds the top panel to the main panel..
     */
	public void addTopPanel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.add(titlePanel);

        JLabel lblGameTitle = new JLabel("SPACE TRAVELLERS");
        lblGameTitle.setFont(new Font("Ringbearer", Font.PLAIN, 30));
        lblGameTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblGameTitle.setBounds(10, 30, 1000, 150);
        titlePanel.add(lblGameTitle);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        mainPanel.add(topPanel);

        JPanel homeBtnPanel = new JPanel();
        topPanel.add(homeBtnPanel);
        this.homeBtn = new JButton("Home");
        homeBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
        homeBtn.setBounds(10, 30, 150, 20);
        homeBtn.setHorizontalAlignment(SwingConstants.CENTER);
        homeBtnPanel.add(homeBtn);

        JPanel dayLabelPanel = new JPanel();
        topPanel.add(dayLabelPanel);
        this.lblCurrentDay = new JLabel("");
        lblCurrentDay.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblCurrentDay.setBounds(10, 60, 150, 20);
        lblCurrentDay.setHorizontalAlignment(SwingConstants.CENTER);
        dayLabelPanel.add(lblCurrentDay);

        JPanel currentPlanetPanel = new JPanel();
        topPanel.add(currentPlanetPanel);
        this.lblCurrentPlanet = new JLabel("");
        lblCurrentPlanet.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblCurrentPlanet.setBounds(10, 90, 150, 20);
        lblCurrentPlanet.setHorizontalAlignment(SwingConstants.CENTER);
        currentPlanetPanel.add(lblCurrentPlanet);

        JPanel peicesRequiredPanel = new JPanel();
        topPanel.add(peicesRequiredPanel);
        this.lblPeicesRequired = new JLabel("");
        lblPeicesRequired.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblPeicesRequired.setBounds(10, 120, 150, 20);
        lblPeicesRequired.setHorizontalAlignment(SwingConstants.CENTER);
        peicesRequiredPanel.add(lblPeicesRequired);
        
        refreshTopPanel();
    }

    /**
     * Refreshs the current day, planet and how many peices found.
     */
	public void refreshTopPanel() {
		lblCurrentDay.setText("Day: " +  environment.getCurrentDay() + "/" + environment.getNumDays());
		lblCurrentPlanet.setText("Current Planet: " + environment.getCurrentPlanet().getName());
        lblPeicesRequired.setText("Peices: " + spaceShip.getPeicesFound() + "/" + spaceShip.getPeicesRequired());
	}

    /**
     * Adds home panel to main content panel.
     */
	public void addHomePanel() {
		JPanel homePanel = new JPanel();
		homePanel.setLayout(new BoxLayout(homePanel, BoxLayout.X_AXIS));
		JPanel homeContent = new JPanel();
		homePanel.add(homeContent);

		homeContent.setLayout(null);

		JButton crewDetailBtn = new JButton("View Crew Status");
        crewDetailBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		crewDetailBtn.setBounds(60, 57, 165, 30);
		homeContent.add(crewDetailBtn);
		
		JButton shipBtn = new JButton("View Ship Status");
        shipBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
        shipBtn.setBounds(60, 99, 165, 30);
		homeContent.add(shipBtn);

		JButton inventoryBtn = new JButton("Inventory"); 
        inventoryBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
        inventoryBtn.setBounds(60, 142, 155, 30);
		homeContent.add(inventoryBtn);

        JLabel lblExploreImage = new JLabel("");
        lblExploreImage.setIcon(Funcs.getScaledIcon(Image.EXPLORE_IMAGE_PATH, 150, 150));
        lblExploreImage.setBorder(new LineBorder(new Color(0, 0, 0)));
        lblExploreImage.setBounds(700, 20, 150, 150);
        homeContent.add(lblExploreImage);

        JButton exploreBtn = new JButton("Explore");
        exploreBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
        exploreBtn.setBounds(700, 170, 150, 30);
        homeContent.add(exploreBtn);
        
        JLabel lblShopImage = new JLabel("");
        lblShopImage.setIcon(Funcs.getScaledIcon(Image.SHOP_IMAGE_PATH, 150, 150));
        lblShopImage.setBorder(new LineBorder(new Color(0, 0, 0)));
        lblShopImage.setBounds(350, 20, 150, 150);
        homeContent.add(lblShopImage);

        JButton shopBtn = new JButton("Shop");
        shopBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
        shopBtn.setBounds(350, 170, 150, 30);
        homeContent.add(shopBtn);

        JLabel lblNextDayImage = new JLabel("");
        lblNextDayImage.setIcon(Funcs.getScaledIcon(Image.NEXT_DAY_IMAGE_PATH, 150, 150));
        lblNextDayImage.setBorder(new LineBorder(new Color(0, 0, 0)));
        lblNextDayImage.setBounds(700, 250, 150, 150);
        homeContent.add(lblNextDayImage);

        JButton nextDayBtn = new JButton("Next Day");
        nextDayBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
        nextDayBtn.setBounds(700, 400, 150, 30);
        if (environment.getCurrentDay() == environment.getNumDays()) {
            nextDayBtn.setEnabled(false);
        }

        homeContent.add(nextDayBtn);
        
        JLabel lblNewPlanetImage = new JLabel("");
        lblNewPlanetImage.setIcon(Funcs.getScaledIcon(Image.NEW_PLANET_IMAGE_PATH, 150, 150));
        lblNewPlanetImage.setBorder(new LineBorder(new Color(0, 0, 0)));
        lblNewPlanetImage.setBounds(350, 250, 150, 150);
        homeContent.add(lblNewPlanetImage);

        JButton pilotNewPlanetBtn = new JButton("New Planet");
        pilotNewPlanetBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pilotNewPlanetBtn.setBounds(350, 400, 150, 30);
        homeContent.add(pilotNewPlanetBtn);

		homeBtn.setEnabled(false);

		nextDayBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		if (environment.isValidCurrentDay()) {
					environment.goToNextDay();

                    if (environment.getCurrentDay() == environment.getNumDays()) {
                        nextDayBtn.setEnabled(false);
                    }

					int randomEvent = environment.determineRandomEvent();
		            if (randomEvent == 1) {
		            	JOptionPane.showMessageDialog(null, "Random Event: Alien Pirates");
		            } else if (randomEvent == 2) {
		                JOptionPane.showMessageDialog(null, "Random Event: Space Plague");
                        // Game could potentially be over in this case if we have no members left...
                        if (!crew.hasMembers()) {
                        	closeWindow(false, "All members are out of health");
                        }
		            } else {
                        if (spaceShip.getShieldHealth() <= 0) {
                            //closeWindow(false, "Your spaceship ran out of shield health");
                            JOptionPane.showMessageDialog(null, "Random Event: Asteroid Belt (You cannot travel until you repair your ship!)");
                        } else {
                            JOptionPane.showMessageDialog(null, "Random Event: Asteroid Belt");
                        }
		            }
				} else {
					nextDayBtn.setEnabled(false);
				}
                
				refreshTopPanel();
			}
		});

		shopBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(contentPanel, INVENTORY_PANEL_STRING);
                refreshSpaceOutPostPanel();
				enableHomeButton();
            }
		});

		inventoryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(contentPanel, INVENTORY_PANEL_STRING);
                refreshSpaceOutPostPanel();
                enableHomeButton();
			}
		});
		
		shipBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshShipStatusPanel();
				cardLayout.show(contentPanel, SHIP_STATUS_PANEL_STRING);
				enableHomeButton();
			}
		});
		
		crewDetailBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                cardLayout.show(contentPanel, CREW_STATUS_PANEL_STRING);
                refreshCrewStatusPanel();
				enableHomeButton();
			}
		});

		homeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                refreshTopPanel();
                cardLayout.show(contentPanel, HOME_PANEL_STRING);
			    disableHomeButton();
            }
		});

        exploreBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                cardLayout.show(contentPanel, EXPLORE_PANEL_STRING);
                enableHomeButton();
                refreshExplorePanel();
            }
        });

        pilotNewPlanetBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, CREW_STATUS_PANEL_STRING);
                // We want to show pilot to new planet card
                crewStatusPanel.showPilotNewPlanetCard();
                enableHomeButton();
            }
        });

		contentPanel.add(homePanel, HOME_PANEL_STRING);
	}

    /**
     * Adds crew status panel to content panel.
     */
	public void addCrewStatusPanel() {
		this.crewStatusPanel = new CrewPanel(this);
        contentPanel.add(crewStatusPanel, CREW_STATUS_PANEL_STRING);
	}

    /**
     * Adds ship status panel to content panel.
     */
    public void addShipStatusPanel() {
		this.shipStatusPanel = new JPanel();
		contentPanel.add(shipStatusPanel, SHIP_STATUS_PANEL_STRING);
    }

    /**
     * Refreshs ship status panel with latest data.
     */
    public void refreshShipStatusPanel() {
    	shipStatusPanel.removeAll();
		shipStatusPanel.setLayout(null);

        if (!environment.getSpaceShip().canTravel()) {
            JLabel lblSpaceShipNotice = new JLabel("You cannot travel until you repair you're spaceship");
            lblSpaceShipNotice.setFont(new Font("Tahoma", Font.BOLD, 22));
            lblSpaceShipNotice.setHorizontalAlignment(SwingConstants.CENTER);
            lblSpaceShipNotice.setBounds(100, 50, 800, 50);
            shipStatusPanel.add(lblSpaceShipNotice);
        }

        JLabel lblSpaceShipImage = new JLabel("");
        lblSpaceShipImage.setBorder(new LineBorder(new Color(0, 0, 0)));
        lblSpaceShipImage.setBounds(100, 100, 200, 200);
        lblSpaceShipImage.setIcon(new ImageIcon(Image.SPACESHIP_IMAGE_PATH));
        shipStatusPanel.add(lblSpaceShipImage);

        JLabel lblSpaceShipName = new JLabel(spaceShip.getName());
        lblSpaceShipName.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblSpaceShipName.setHorizontalAlignment(SwingConstants.CENTER);
        lblSpaceShipName.setBounds(100, 300, 200, 50);

		JLabel lblSpaceShipHealth = new JLabel("Shield Health: " + spaceShip.getShieldHealth());
        lblSpaceShipHealth.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSpaceShipHealth.setBounds(350, 100, 250, 50);

		JLabel lblPeicesRequired = new JLabel("Peices Required: " + spaceShip.getPeicesRequired());
        lblPeicesRequired.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPeicesRequired.setBounds(350, 130, 250, 50);

        JLabel lblPartsFound = new JLabel("Peices found: " + spaceShip.getPeicesFound());
        lblPartsFound.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblPartsFound.setBounds(350, 160, 250, 50);

		shipStatusPanel.add(lblSpaceShipName);
		shipStatusPanel.add(lblSpaceShipHealth);
		shipStatusPanel.add(lblPeicesRequired);
		shipStatusPanel.add(lblPartsFound);
    }

    /**
     * Adds explore panel to content panel.
     */
    public void addExplorePanel() {
        this.explorePanel = new JPanel();
        explorePanel.setLayout(new BoxLayout(explorePanel, BoxLayout.Y_AXIS));
        contentPanel.add(explorePanel, EXPLORE_PANEL_STRING);
    }

    /**
     * Refreshs explore panel with latest data.
     */
    public void refreshExplorePanel() {
        explorePanel.removeAll();

        JPanel planetPanel = new JPanel();
        planetPanel.setLayout(null);
        explorePanel.add(planetPanel);

        JLabel lblChooseAMember = new JLabel("Choose a member and go explore " + environment.getCurrentPlanet().getName() + "!");
        lblChooseAMember.setBounds(0, 49, 585, 74);
        lblChooseAMember.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblChooseAMember.setHorizontalAlignment(SwingConstants.CENTER);
        planetPanel.add(lblChooseAMember);

        JLabel lblPlanetImage = new JLabel("");
        lblPlanetImage.setBorder(new LineBorder(new Color(0, 0, 0)));
        lblPlanetImage.setBounds(600, 20, 150, 150);
        lblPlanetImage.setIcon(new ImageIcon(Image.getPlanetImagePath(environment.getCurrentPlanet())));
        
        planetPanel.add(lblPlanetImage);

        JPanel crewMemberPanel = new JPanel();
        crewMemberPanel.setLayout(new GridLayout(0, 4, 0, 0));
        explorePanel.add(crewMemberPanel);

        ButtonGroup memberButtonGroup = new ButtonGroup();

        for (CrewMember member: crew.getMembers()) {
            JPanel panel = new JPanel();
            panel.setLayout(null);

            JRadioButton memberRadio = new JRadioButton(member.getName());
            memberRadio.setFont(new Font("Tahoma", Font.PLAIN, 16));
            memberRadio.setBounds(70, 155, 110, 23);
            if (!member.hasActionsLeft()) {
                memberRadio.setEnabled(false);
            }
            memberRadio.putClientProperty("CrewMember", member);

            JLabel lblMemberImage = new JLabel("");
            lblMemberImage.setBounds(60, 27, 120, 120);
            lblMemberImage.setBorder(new LineBorder(new Color(0, 0, 0)));
            lblMemberImage.setIcon(Funcs.getScaledIcon(Image.getCrewMemberImagePath(member), 120, 120));;

            memberButtonGroup.add(memberRadio);
            panel.add(lblMemberImage);
            panel.add(memberRadio);
            crewMemberPanel.add(panel);
        }

        JPanel exploreBtnPanel = new JPanel();
        JButton exploreBtn = new JButton("Go exploring!");
        exploreBtnPanel.add(exploreBtn);
        explorePanel.add(exploreBtnPanel);

        exploreBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JRadioButton selectedMemberRadio = Funcs.selectedButton(memberButtonGroup);
                CrewMember actionedMember = (CrewMember) selectedMemberRadio.getClientProperty("CrewMember");

                actionedMember.removeAction();
                memberButtonGroup.clearSelection();
                if (!actionedMember.hasActionsLeft()) {
                    selectedMemberRadio.setEnabled(false);
                }

                String foundItem = environment.searchPlanetForParts();
                boolean showMessageDialog = true;
                if (spaceShip.allPartsFound()) {
                    // Games over and won
                    showMessageDialog = false;
                    closeWindow(true, "");
                } else {
                    // All actions could be used here
                    if (environment.isDefeated()) {
                        showMessageDialog = false;
                        closeWindow(false, "Players are out of actions");
                    }
                }

                refreshTopPanel();

                if (showMessageDialog) {
                    JOptionPane.showMessageDialog(null, foundItem);
                }
            }
        });
    }

    /**
     * Adds space out post panel to content panel.
     */
    public void addSpaceOutPostPanel() {
        this.spaceOutPostPanel = new SpaceOutPostPanel(spaceOutPost);
        contentPanel.add(spaceOutPostPanel, INVENTORY_PANEL_STRING);
    }
}
