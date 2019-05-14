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
		/*JPanel topPanel = new JPanel();*/
		
		JPanel contentPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		
		/*JButton crewDetailBtn = new JButton("View Crew Status");
		JButton shipBtn = new JButton("View Ship Status");
		JButton inventoryBtn = new JButton("Inventory");
		JButton homeBtn = new JButton("Home");
		JButton nextDayBtn = new JButton("Next Day");
		topPanel.add(crewDetailBtn);
		topPanel.add(shipBtn);
		topPanel.add(inventoryBtn);
		topPanel.add(homeBtn);
		topPanel.add(nextDayBtn);*/
		
		//mainPanel.add(topPanel);
		mainPanel.add(contentPanel);

		CardLayout cardLayout = new CardLayout();
		contentPanel.setLayout(cardLayout);
		
		// HOME PANEL
		JPanel homePanel = new JPanel();
		JPanel homeSideBar = new JPanel();
		homePanel.add(homeSideBar);
		JPanel homeContent = new JPanel();
		homePanel.add(homeContent);
		JButton crewDetailBtn = new JButton("View Crew Status");
		JButton shipBtn = new JButton("View Ship Status");
		JButton inventoryBtn = new JButton("Inventory");
		JButton nextDayBtn = new JButton("Next Day");
		homeSideBar.add(crewDetailBtn);
		homeSideBar.add(shipBtn);
		homeSideBar.add(inventoryBtn);
		homeSideBar.add(nextDayBtn);
		JButton exploreBtn = new JButton("Explore");
		JButton shopBtn = new JButton("Shop");
		JButton rocketBtn = new JButton("Rocket");
		homeContent.add(exploreBtn);
		homeContent.add(shopBtn);
		homeContent.add(rocketBtn);
		contentPanel.add(homePanel, "HOME");
		
		// Crew Status Panel
		JPanel crewStatusPanel = new JPanel();
		ArrayList<CrewMember> members = game.getGameEnvironment().getCrew().getMembers();
		for (CrewMember member: members) {
			crewStatusPanel.add(new JLabel("Name: " + member.getName() + " Current Health: " + member.getCurrentHealth()));
		}
		contentPanel.add(crewStatusPanel, "CREW_STATUS");
		
		JButton homeBtn = new JButton("Home");
		crewStatusPanel.add(homeBtn);
		
		// Ship Status PANEL
		JPanel shipStatusPanel = new JPanel();
		SpaceShip spaceShip = game.getGameEnvironment().getSpaceShip();
		JLabel spaceShipName = new JLabel("Space Ship Name: " + spaceShip.getName());
		JLabel spaceShipHealth = new JLabel("Shield Health: " + spaceShip.getShieldHealth());
		JLabel peicesRequired = new JLabel("Peices Required: " + spaceShip.getPeicesRequired());
		
		shipStatusPanel.add(spaceShipName);
		shipStatusPanel.add(spaceShipHealth);
		shipStatusPanel.add(peicesRequired);
		
		contentPanel.add(shipStatusPanel, "SHIP_STATUS");
		
		// INVENTORY PANEL
		JPanel inventoryPanel = new JPanel();
		SpaceOutPost spaceOutPost = game.getGameEnvironment().getSpaceOutPost();
		for (MedicalSupply m : spaceOutPost.getMedicalSupplies()) {
			inventoryPanel.add(new JLabel(m.getType() + "(" + m.getCount() + ")"));
		}
		contentPanel.add(inventoryPanel, "INVENTORY");
		
		// SHOP PANEL...
		SpaceOutPostPanel shop = new SpaceOutPostPanel();
		contentPanel.add(shop, "SHOP");
		
		// SHOW DEFAULT PANEL
		cardLayout.show(contentPanel, "HOME");
		
		//homeBtn.setEnabled(false);
		
		// Action listeners
		homeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(contentPanel, "HOME");
			}
		});
		
		shopBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(contentPanel, "SHOP");
				//homeBtn.setEnabled(true);
			}
		});
		
		crewDetailBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(contentPanel, "CREW_STATUS");
				//homeBtn.setEnabled(true);
			}
		});
		
		shipBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(contentPanel, "SHIP_STATUS");
				//homeBtn.setEnabled(true);
			}
		});

		inventoryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(contentPanel, "INVENTORY");
				//homeBtn.setEnabled(true);
			}
		});
		
		window.getContentPane().add(mainPanel, BorderLayout.NORTH);
		window.setVisible(true);
	}

}
