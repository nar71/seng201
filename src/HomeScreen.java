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

	private JFrame window;

	public Game game;

    public GameEnvironment environment;

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

	public JLabel lblCurrentDay;

	public JLabel lblCurrentPlanet;

    public JLabel lblPeicesRequired;

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

	public void finishedWindow() {
		window.dispose();
	}

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

	public void refresh() {
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
        //topPanel.add(homeBtn);
        homeBtnPanel.add(homeBtn);

        JPanel dayLabelPanel = new JPanel();
        topPanel.add(dayLabelPanel);
        this.lblCurrentDay = new JLabel("");
        lblCurrentDay.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblCurrentDay.setBounds(10, 60, 150, 20);
        lblCurrentDay.setHorizontalAlignment(SwingConstants.CENTER);
        //topPanel.add(dayLabel);
        dayLabelPanel.add(lblCurrentDay);

        JPanel currentPlanetPanel = new JPanel();
        topPanel.add(currentPlanetPanel);
        this.lblCurrentPlanet = new JLabel("");
        lblCurrentPlanet.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblCurrentPlanet.setBounds(10, 90, 150, 20);
        lblCurrentPlanet.setHorizontalAlignment(SwingConstants.CENTER);
        //topPanel.add(currentPlanetLabel);
        currentPlanetPanel.add(lblCurrentPlanet);

        JPanel peicesRequiredPanel = new JPanel();
        topPanel.add(peicesRequiredPanel);
        this.lblPeicesRequired = new JLabel("");
        lblPeicesRequired.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblPeicesRequired.setBounds(10, 120, 150, 20);
        lblPeicesRequired.setHorizontalAlignment(SwingConstants.CENTER);
        //topPanel.add(peicesRequiredLabel);
        peicesRequiredPanel.add(lblPeicesRequired);
        
        refreshTopPanel();
    }

	public void refreshTopPanel() {
		lblCurrentDay.setText("Day: " +  environment.getCurrentDay() + "/" + environment.getNumDays());
		lblCurrentPlanet.setText("Current Planet: " + environment.getCurrentPlanet().getName());
        lblPeicesRequired.setText("Peices: " + spaceShip.getPeicesFound() + "/" + spaceShip.getPeicesRequired());
	}

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
        inventoryBtn.setBounds(60, 142, 165, 30);
		homeContent.add(inventoryBtn);

        JButton exploreBtn = new JButton("Explore");
        exploreBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
        exploreBtn.setBounds(509, 67, 165, 40);
        homeContent.add(exploreBtn);
        
        JButton shopBtn = new JButton("Shop");
        shopBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
        shopBtn.setBounds(350, 67, 165, 40);
        homeContent.add(shopBtn);

        JButton nextDayBtn = new JButton("Next Day");
        nextDayBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
        nextDayBtn.setBounds(509, 107, 165, 40);
        if (environment.getCurrentDay() == environment.getNumDays()) {
            nextDayBtn.setEnabled(false);
        }

        homeContent.add(nextDayBtn);
        
        JButton pilotNewPlanetBtn = new JButton("New Planet");
        pilotNewPlanetBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pilotNewPlanetBtn.setBounds(350, 107, 165, 40);
        homeContent.add(pilotNewPlanetBtn);

		homeBtn.setEnabled(false);

		nextDayBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		if (environment.isValidCurrentDay()) {
					environment.goToNextDay();

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
		                JOptionPane.showMessageDialog(null, "Random Event: Asteroid Belt");
                        if (spaceShip.getShieldHealth() <= 0) {
                            closeWindow(false, "Your spaceship ran out of shield health");
                        }
		            }
				} else {
					nextDayBtn.setEnabled(false);
				}

                if (environment.getCurrentDay() == environment.getNumDays()) {
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
			    disableHomeButton();
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

        pilotNewPlanetBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, CREW_STATUS);
                crewStatusPanel.refresh();
                // We want to show pilot to new planet card
                crewStatusPanel.showPilotNewPlanetCard();
                spaceOutPostPanel.refresh();
                enableHomeButton();
            }
        });

		contentPanel.add(homePanel, HOME);
	}

	public void addCrewStatusPanel() {
		this.crewStatusPanel = new CrewPanel(this);
        contentPanel.add(crewStatusPanel, CREW_STATUS);
	}

    public void addShipStatusPanel() {
		this.shipStatusPanel = new JPanel();
		contentPanel.add(shipStatusPanel, "SHIP_STATUS");
    }

    public void refreshShipStatusPanel() {
    	shipStatusPanel.removeAll();
		shipStatusPanel.setLayout(null);

        JLabel lblSpaceShipImage = new JLabel("");
        lblSpaceShipImage.setBorder(new LineBorder(new Color(0, 0, 0)));
        lblSpaceShipImage.setBounds(100, 50, 200, 200);
        lblSpaceShipImage.setIcon(new ImageIcon(getClass().getResource(spaceShip.getImagePath())));
        shipStatusPanel.add(lblSpaceShipImage);

        JLabel lblSpaceShipName = new JLabel(spaceShip.getName());
        lblSpaceShipName.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblSpaceShipName.setHorizontalAlignment(SwingConstants.CENTER);
        lblSpaceShipName.setBounds(100, 250, 200, 50);

		JLabel lblSpaceShipHealth = new JLabel("Shield Health: " + spaceShip.getShieldHealth());
        lblSpaceShipHealth.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSpaceShipHealth.setBounds(350, 50, 250, 50);

		JLabel lblPeicesRequired = new JLabel("Peices Required: " + spaceShip.getPeicesRequired());
        lblPeicesRequired.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPeicesRequired.setBounds(350, 80, 250, 50);

        JLabel lblPartsFound = new JLabel("Peices found: " + spaceShip.getPeicesFound());
        lblPartsFound.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblPartsFound.setBounds(350, 110, 250, 50);

		shipStatusPanel.add(lblSpaceShipName);
		shipStatusPanel.add(lblSpaceShipHealth);
		shipStatusPanel.add(lblPeicesRequired);
		shipStatusPanel.add(lblPartsFound);
    }

    public void addExplorePanel() {
        this.explorePanel = new JPanel();
        explorePanel.setLayout(new BoxLayout(explorePanel, BoxLayout.Y_AXIS));
        contentPanel.add(explorePanel, EXPLORE);
    }

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

    public void addSpaceOutPostPanel() {
        this.spaceOutPostPanel = new SpaceOutPostPanel(spaceOutPost);
        contentPanel.add(spaceOutPostPanel, "SHOP");
    }
}
