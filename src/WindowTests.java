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
		
		JPanel explorePanel = new JPanel();
		explorePanel.setLayout(new BoxLayout(explorePanel, BoxLayout.Y_AXIS));

		JPanel planetPanel = new JPanel();
		planetPanel.setLayout(null);
        JLabel planetImageLabel = new JLabel("");
        planetImageLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
        planetImageLabel.setIcon(Funcs.getScaledIcon("barter.jpg", 150, 150));

        planetPanel.add(planetImageLabel);
        explorePanel.add(planetPanel);
        
        JLabel lblAsadsad = new JLabel("asadsad");
        lblAsadsad.setHorizontalAlignment(SwingConstants.CENTER);
        lblAsadsad.setBounds(391, 174, 150, 15);
        planetPanel.add(lblAsadsad);
        
        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setBounds(391, 12, 150, 150);
        planetPanel.add(lblNewLabel);
        
        JPanel crewMemberPanel = new JPanel();
        crewMemberPanel.setLayout(new GridLayout(0, 2, 0, 0));
        explorePanel.add(crewMemberPanel);

        JPanel memberPanel = new JPanel();
        memberPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        memberPanel.setLayout(null);
        
        JLabel memberImageLabel = new JLabel("");
        memberImageLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
        memberImageLabel.setBounds(185, 12, 120, 120);
        memberImageLabel.setIcon(Funcs.getScaledIcon("barter.jpg", 150, 150));
        memberPanel.add(memberImageLabel);

        crewMemberPanel.add(memberPanel);
        
        JRadioButton rdbtnCrewMember = new JRadioButton("Crew Member");
        rdbtnCrewMember.setBounds(176, 147, 144, 23);
        memberPanel.add(rdbtnCrewMember);

        JPanel exploreBtnPanel = new JPanel();
        JButton exploreBtn = new JButton("Go exploring!");
        exploreBtnPanel.add(exploreBtn);
        explorePanel.add(exploreBtnPanel);
        
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		frame.getContentPane().add(explorePanel);
		frame.setVisible(true);
	}
}
