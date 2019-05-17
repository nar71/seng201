import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

public class Test {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test window = new Test();
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
	public Test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel main = new JPanel();
		main.setLayout(null);
		frame.getContentPane().add(main);
		
        JLabel memberImageLabel = new JLabel("");
        memberImageLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
        memberImageLabel.setBounds(534, 56, 150, 150);
        memberImageLabel.setIcon(new ImageIcon(getClass().getResource("images/barter.jpg")));
        main.add(memberImageLabel);
        
        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setBounds(544, 218, 140, 128);
        main.add(lblNewLabel);
        
        JTextArea textArea = new JTextArea();
        textArea.setBounds(544, 377, 1, 15);
        main.add(textArea);
        
        JTextPane txtpnDescriptionOfBarter = new JTextPane();
        txtpnDescriptionOfBarter.setText("Description of barter asdasdsa");
        txtpnDescriptionOfBarter.setBounds(534, 218, 150, 89);
        main.add(txtpnDescriptionOfBarter);
	}
}
