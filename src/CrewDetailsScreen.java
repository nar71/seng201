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
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);
		window.getContentPane().add(mainPanel);
		
		JLabel lblNewLabel = new JLabel("Game Name");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 851, 27);
		mainPanel.add(lblNewLabel);
		
		for (CrewMember member: game.getGameEnvironment().getCrew().getMembers()) {
			JPanel panel = new JPanel();
			panel.add(new JLabel(member.getName()));
			mainPanel.add(panel);
		}
	}
}
