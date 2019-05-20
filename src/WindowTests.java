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
        mainPanel.setLayout(null);

        JLabel lblGameTitle = new JLabel("SPACE TRAVELLERS");
        lblGameTitle.setFont(new Font("Ringbearer", Font.PLAIN, 30));
        lblGameTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblGameTitle.setBounds(-14, 0, 1000, 70);
        mainPanel.add(lblGameTitle);

        
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		frame.getContentPane().add(mainPanel);
		
		String gameInfoString = "Welcome to Space Travellers.";
		gameInfoString += "You are stuck in our Solar System after having to perform an emergency landing on mercury which damaged your ship badly.";
		gameInfoString += "To win you must find enough transporter parts to be able to fix your spaceship.";
		gameInfoString += "Transporter parts can be found by exploring the planets, you can only find one transporter part per planet so you will have to travel to other planets to get all the parts.";
		gameInfoString += "Gold and medical supplies can also be found whilst exploring.";
		gameInfoString += "Use your gold at the shops to buy Food and medical supplies to keep your crew alive.";
		gameInfoString += "Each crew member will get two actions per day which can be used to Explore, Repair the Ships shield, sleep, or pilot the ship to another planet.";
		gameInfoString += "Good luck!";
		
		JTextArea textArea = new JTextArea(
				 "You are stuck in our Solar System after having to perform an emergency landing on mercury which damaged your ship badly." + 
				 "To win you must find enough transporter parts to be able to fix your spaceship." + 
				 "Transporter parts can be found by exploring the planets, you can only find one transporter part per planet so you will have to travel to other planets to get all the parts." + 
				 "Gold and medical supplies can also be found whilst exploring." + 
				 "Use your gold at the shops to buy Food and medical supplies to keep your crew alive." + 
				 "Each crew member will get two actions per day which can be used to Explore, Repair the Ships shield, sleep, or pilot the ship to another planet." + 
				 "Good luck!"
				, 
                6, 
                20);
		textArea.setSize(800, 225);
		textArea.setLocation(31, 114);
        textArea.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setOpaque(false);
        textArea.setEditable(false);
        mainPanel.add(textArea);
        
		frame.setVisible(true);
	}
}
