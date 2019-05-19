import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.border.LineBorder;

public class WindowTests {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowTests window = new WindowTests();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WindowTests() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(new Rectangle(0, 0, 1000, 700));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		JPanel contentPanel = new JPanel();

		CardLayout cardLayout = new CardLayout();
		
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        contentPanel.setLayout(cardLayout);

		JPanel homePanel = new JPanel();
		contentPanel.add(homePanel, "HOME");

		homePanel.setLayout(new BoxLayout(homePanel, BoxLayout.X_AXIS));
		JPanel homeSideBar = new JPanel();
		homePanel.add(homeSideBar);
		
		//homeSideBar.setLayout(new BoxLayout(homeSideBar, BoxLayout.Y_AXIS));
		homeSideBar.setLayout(null);
		
		//JPanel crewStatusBtnPanel = new JPanel();
		//homeSideBar.add(crewStatusBtnPanel);
		JButton crewDetailBtn = new JButton("View Crew Status");
        crewDetailBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		crewDetailBtn.setBounds(26, 30, 165, 30);
		homeSideBar.add(crewDetailBtn);
		//crewStatusBtnPanel.add(crewDetailBtn);
		
		//JPanel shipStatusBtnPanel = new JPanel();
		//homeSideBar.add(shipStatusBtnPanel);
		JButton shipBtn = new JButton("View Ship Status");
        shipBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
        shipBtn.setBounds(26, 69, 165, 30);
		homeSideBar.add(shipBtn);
		//shipStatusBtnPanel.add(shipBtn);
		
		//JPanel inventoryBtnPanel = new JPanel();
		//homeSideBar.add(inventoryBtnPanel);
		JButton inventoryBtn = new JButton("Inventory"); // Takes you to inventory of spaceoutpost
        inventoryBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
        inventoryBtn.setBounds(26, 108, 165, 30);
		homeSideBar.add(inventoryBtn);
		//inventoryBtnPanel.add(inventoryBtn);
		
		JButton shopBtn = new JButton("Shop");
        shopBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
        shopBtn.setBounds(12, 67, 165, 30);
        homeSideBar.add(shopBtn);
        
		JButton exploreBtn = new JButton("Explore");
        exploreBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
        exploreBtn.setBounds(235, 69, 165, 30);
        homeSideBar.add(exploreBtn);
        
        
		cardLayout.show(contentPanel, "HOME");
        
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		frame.getContentPane().add(mainPanel);
		frame.setVisible(true);
	}
}
