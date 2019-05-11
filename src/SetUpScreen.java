import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Rectangle;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;

public class SetUpScreen {

	private JFrame window;
	private Game game;
	private JTextField rocketShipName;
	private JTextField teamName;
	/**
	 * Create the application.
	 */
	public SetUpScreen(Game game) {
		this.game = game;
		initialize();
		window.setVisible(true);
	}

	public void finishedWindow() {
		window.dispose();
	}

	public void closeWindow() {
		game.closeSetUpScreen(this);
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setBounds(new Rectangle(0, 0, 880, 610));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(new BoxLayout(window.getContentPane(), BoxLayout.X_AXIS));
		
		JPanel panel_1 = new JPanel();
		window.getContentPane().add(panel_1);
		
		panel_1.setLayout(null);
		
		JLabel lblGameName = new JLabel("Game Name");
		lblGameName.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameName.setBounds(0, 0, 878, 42);
		panel_1.add(lblGameName);
		
		JLabel lblHowManyCharacters = new JLabel("Team name");
		lblHowManyCharacters.setBounds(35, 80, 276, 29);
		panel_1.add(lblHowManyCharacters);
		
		JLabel label = new JLabel("Rocket ship name");
		label.setBounds(37, 132, 276, 29);
		panel_1.add(label);
		
		rocketShipName = new JTextField();
		rocketShipName.setColumns(10);
		rocketShipName.setBounds(444, 132, 176, 24);
		panel_1.add(rocketShipName);
		
		teamName = new JTextField();
		teamName.setColumns(10);
		teamName.setBounds(444, 85, 176, 24);
		panel_1.add(teamName);
		
		JLabel label_1 = new JLabel("How many days do you want to play?");
		label_1.setBounds(35, 184, 276, 29);
		panel_1.add(label_1);

		JLabel label_2 = new JLabel("How many characters do you want to play?");
		label_2.setBounds(35, 242, 325, 29);
		panel_1.add(label_2);
		
		JSlider sliderDaysAmount = new JSlider();
		panel_1.add(sliderDaysAmount);
		sliderDaysAmount.setSnapToTicks(true);
		sliderDaysAmount.setPaintLabels(true);
		sliderDaysAmount.setMinimum(3);
		sliderDaysAmount.setMaximum(10);
		sliderDaysAmount.setMajorTickSpacing(1);
		sliderDaysAmount.setBounds(444, 169, 264, 45);
		sliderDaysAmount.setValue(1);
		
		JSlider sliderCharactersAmount = new JSlider();
		sliderCharactersAmount.setValue(2);
		sliderCharactersAmount.setPaintLabels(true);
		sliderCharactersAmount.setMajorTickSpacing(1);
		sliderCharactersAmount.setMaximum(4);
		sliderCharactersAmount.setMinimum(2);
		sliderCharactersAmount.setSnapToTicks(true);
		sliderCharactersAmount.setMinorTickSpacing(1);
		sliderCharactersAmount.setBounds(444, 232, 264, 35);
		panel_1.add(sliderCharactersAmount);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.setBounds(707, 527, 114, 25);
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.setGameEnvironment(new GameEnvironment(
						teamName.getText().trim(), 
						sliderCharactersAmount.getValue(), 
						sliderDaysAmount.getValue(), 
						rocketShipName.getText()
				));
				closeWindow();
			}
		});
		panel_1.add(btnContinue);
		
	}
}
