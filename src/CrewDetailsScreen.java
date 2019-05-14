import java.awt.CardLayout;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.GridBagLayout;

public class CrewDetailsScreen {
	private JFrame window;
	private Game game;
	public JPanel contentPanel;
	public CardLayout cardLayout;
	/**
	 * Create the application.
	 */
	public CrewDetailsScreen(Game game) {
		this.game = game;
		initialize();
		window.setVisible(true);
	}

	public void finishedWindow() {
		window.dispose();
	}

	public void closeWindow() {
		game.closeCrewDetailsScreen(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setBounds(new Rectangle(0, 0, 880, 610));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel gameNamePanel = new JPanel();
		JLabel gameName = new JLabel("Game Name");
		gameNamePanel.add(gameName);
		
		window.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		for (CrewMember member: game.getGameEnvironment().getCrew().getMembers()) {
			JPanel panel = new JPanel();
			JLabel label = new JLabel(member.getName());
			panel.add(label);
			JLabel label2 = new JLabel("Max Health: " + member.getMaxHealth());
			panel.add(label2);
			JLabel label3 = new JLabel("Current Health: " + member.getCurrentHealth());
			panel.add(label3);
			JLabel label4 = new JLabel("Tiredness level: " + member.getTiredness());
			panel.add(label4);
			JLabel label5 = new JLabel("Hunger level: " + member.getHungerLevel());
			panel.add(label5);
			JLabel label6 = new JLabel("Speciality: " + member.getSpecialty());
			panel.add(label6);
			JLabel label7 = new JLabel("Description: " + member.getDescription());
			panel.add(label7);
			String isSickStr = "no";
			if (member.isSick()) {
				isSickStr = "yes";
			}
			JLabel label8 = new JLabel("Is sick: " + isSickStr);
			panel.add(label8);
			window.getContentPane().add(panel);
		}
		
		JPanel homeBtnPanel = new JPanel();
		JButton homeBtn = new JButton("Home");
		homeBtnPanel.add(homeBtn);
		
		window.getContentPane().add(homeBtnPanel);
		
		/*
		JPanel panel = new JPanel();
		window.getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		window.getContentPane().add(panel_1);
		
		JPanel panel_2 = new JPanel();
		window.getContentPane().add(panel_2);
		
		JPanel panel_3 = new JPanel();
		window.getContentPane().add(panel_3);*/
	}
}
