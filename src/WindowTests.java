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
		frame.setBounds(new Rectangle(0, 0, 900, 700));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		JPanel shipStatusPanel = new JPanel();
		frame.getContentPane().add(shipStatusPanel);		
		shipStatusPanel.setLayout(null);

        JLabel spaceShipImageLabel = new JLabel("");
        spaceShipImageLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
        spaceShipImageLabel.setBounds(51, 50, 200, 200);
        spaceShipImageLabel.setIcon(new ImageIcon(getClass().getResource("images/spaceship.jpg")));
        shipStatusPanel.add(spaceShipImageLabel);

        JLabel spaceShipName = new JLabel("Space Ship Name");
        spaceShipName.setFont(new Font("Dialog", Font.BOLD, 18));
        spaceShipName.setHorizontalAlignment(SwingConstants.CENTER);
        spaceShipName.setBounds(51, 50, 200, 50);

		JLabel spaceShipHealth = new JLabel("Shield Health: " + "100");
        spaceShipHealth.setBounds(300, 112, 250, 50);

		JLabel peicesRequired = new JLabel("Peices Required: " + "2");
		peicesRequired.setBounds(300, 148, 250, 50);

        JLabel partsFound = new JLabel("Peices found: " + "0");
        partsFound.setBounds(300, 181, 250, 50);

        shipStatusPanel.add(spaceShipName);
        shipStatusPanel.add(spaceShipHealth);
        shipStatusPanel.add(peicesRequired);
        shipStatusPanel.add(partsFound);
	}

}
