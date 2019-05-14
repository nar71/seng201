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
		topPanel.add(homeBtn);
		topPanel.add(days);
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
		JPanel homeContent = new JPanel();
		homePanel.add(homeContent);
		homeContent.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JButton rocketBtn = new JButton("New Planet");
		homeContent.add(rocketBtn);
		JButton exploreBtn = new JButton("Explore");
		homeContent.add(exploreBtn);
		JButton shopBtn = new JButton("Shop");
		homeContent.add(shopBtn);
		
		shopBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(contentPanel, "SHOP");
				homeBtn.setEnabled(true);
			}
		});
		contentPanel.add(homePanel, "HOME");
		
		// Crew Status Panel
		JPanel crewStatusPanel = new JPanel();
		crewStatusPanel.setLayout(new GridLayout(1, 0, 0, 0));
		for (CrewMember member: game.getGameEnvironment().getCrew().getMembers()) {
			JPanel panelMember = new JPanel();
			panelMember.setLayout(new BoxLayout(panelMember, BoxLayout.Y_AXIS));
			
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
			
			crewStatusPanel.add(panelMember);
		}
		
		contentPanel.add(crewStatusPanel, "CREW_STATUS");
		
		// Ship Status PANEL
		JPanel shipStatusPanel = new JPanel();
		SpaceShip spaceShip = game.getGameEnvironment().getSpaceShip();
		shipStatusPanel.setLayout(new BoxLayout(shipStatusPanel, BoxLayout.Y_AXIS));
		JLabel spaceShipName = new JLabel("Space Ship Name: " + spaceShip.getName());
		JLabel spaceShipHealth = new JLabel("Shield Health: " + spaceShip.getShieldHealth());
		JLabel peicesRequired = new JLabel("Peices Required: " + spaceShip.getPeicesRequired());
		JLabel partsFound = new JLabel("Peices found: " + spaceShip.getPeicesFound());
		
		shipStatusPanel.add(spaceShipName);
		shipStatusPanel.add(spaceShipHealth);
		shipStatusPanel.add(peicesRequired);
		shipStatusPanel.add(partsFound);
		
		contentPanel.add(shipStatusPanel, "SHIP_STATUS");
		
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
