import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.JPanel;
import javax.swing.JFrame;

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
		frame.setBounds(new Rectangle(0, 0, 1200, 800));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel main = new JPanel();
		frame.getContentPane().add(main);

		main.setLayout(new GridLayout(0, 3, 0, 0));  

        JPanel heroPanel = new JPanel();
        heroPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        heroPanel.setLayout(null);
        main.add(heroPanel);
        
        JLabel lblHeroPortrait = new JLabel("");
        lblHeroPortrait.setBorder(new LineBorder(new Color(0, 0, 0)));
        lblHeroPortrait.setBounds(30, 13, 150, 150);
        lblHeroPortrait.setIcon(new ImageIcon(getClass().getResource("images/barter.jpg")));
        heroPanel.add(lblHeroPortrait);
        
        JLabel lblHeroName = new JLabel("NAME");
        lblHeroName.setHorizontalAlignment(SwingConstants.CENTER);
        lblHeroName.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblHeroName.setBounds(10, 172, 190, 25);
        heroPanel.add(lblHeroName);

        JLabel lblHeroType = new JLabel(String.format("%s", "Barter"));
        lblHeroType.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblHeroType.setHorizontalAlignment(SwingConstants.CENTER);
        lblHeroType.setBounds(10, 195, 190, 25);
        heroPanel.add(lblHeroType);
        
        JLabel lblSpecialAbility = new JLabel("Special Ability:");
        lblSpecialAbility.setHorizontalAlignment(SwingConstants.LEFT);
        lblSpecialAbility.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblSpecialAbility.setBounds(10, 228, 190, 24);
        heroPanel.add(lblSpecialAbility);
        
        JTextPane txtpnTheSpecialAbility = new JTextPane();
        txtpnTheSpecialAbility.setBackground(UIManager.getColor("Panel.background"));
        txtpnTheSpecialAbility.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtpnTheSpecialAbility.setText("Speciality");
        txtpnTheSpecialAbility.setBounds(20, 251, 170, 38);
        heroPanel.add(txtpnTheSpecialAbility);
        
        JLabel lblCurrentHealth = new JLabel("Current health:");
        lblCurrentHealth.setHorizontalAlignment(SwingConstants.LEFT);
        lblCurrentHealth.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblCurrentHealth.setBounds(10, 328, 377, 24);
        heroPanel.add(lblCurrentHealth);
        
        JLabel lblCurrentHealth2 = new JLabel("Current health 1:");
        lblCurrentHealth2.setHorizontalAlignment(SwingConstants.LEFT);
        lblCurrentHealth2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblCurrentHealth2.setBounds(10, 353, 377, 24);
        heroPanel.add(lblCurrentHealth2);

        
        JLabel lblCurrentHealth3 = new JLabel("Current health 2:");
        lblCurrentHealth3.setHorizontalAlignment(SwingConstants.LEFT);
        lblCurrentHealth3.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblCurrentHealth3.setBounds(10, 380, 110, 24);
        heroPanel.add(lblCurrentHealth3);

        /*JLabel lblTheHealth = new JLabel(String.format("%d/%d", "20", "50"));
        lblTheHealth.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblTheHealth.setHorizontalAlignment(SwingConstants.LEFT);
        lblTheHealth.setBounds(119, 300, 81, 24);
        heroPanel.add(lblTheHealth);*/
	}

}
