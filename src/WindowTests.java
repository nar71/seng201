import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class WindowTests {

	private JFrame frame;
	private final JPanel panel_2 = new JPanel();

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

        
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		frame.setVisible(true);
	}
}
