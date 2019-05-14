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

public class HomeScreen {

	private JFrame window;
	private Game game;
	public JPanel contentPanel;
	public CardLayout cardLayout;
	/**
	 * Create the application.
	 */
	public HomeScreen(Game game) {
		this.game = game;
		initialize();
		window.setVisible(true);
	}

	public void finishedWindow() {
		window.dispose();
	}

	public void closeWindow() {
		game.closeHomeScreen(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setBounds(new Rectangle(0, 0, 880, 610));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		JPanel topPanel = new JPanel();
	
		JPanel contentPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		mainPanel.add(panel_1);
		
		JLabel lblGameName = new JLabel("Game Name");
		panel_1.add(lblGameName);
		
		JButton crewDetailBtn = new JButton("View Crew Status");
		JButton shipBtn = new JButton("View Ship Status");
		JButton inventoryBtn = new JButton("Inventory");
		topPanel.add(crewDetailBtn);
		topPanel.add(shipBtn);
		topPanel.add(inventoryBtn);
		
		mainPanel.add(topPanel);
		mainPanel.add(contentPanel);

		// HOME PANEL
		JPanel homePanel = new JPanel();
		JButton shopBtn = new JButton("Shop");
		JButton rocketBtn = new JButton("Rocket");
		JButton exploreBtn = new JButton("Explore");
		homePanel.add(shopBtn);
		homePanel.add(rocketBtn);
		homePanel.add(exploreBtn);
		contentPanel.add(homePanel, "HOME");
		
		
		
		// Action listeners
		shopBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		crewDetailBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				closeWindow();
			}
		});
		
		shipBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		inventoryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		window.getContentPane().add(mainPanel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		mainPanel.add(panel);
		
		JLabel lblDay = new JLabel("Day 1/4");
		panel.add(lblDay);
		
		JButton btnNextDay = new JButton("Next Day");
		panel.add(btnNextDay);
		window.setVisible(true);
	}

}
