import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import javax.swing.SwingUtilities;
import javax.swing.SwingConstants;
import java.awt.CardLayout;

public class ChooseCharacterScreen {

	private Game game;
	private JFrame window;
	private JTextField textField_1;
	private int numTimesPressed = 0;
	private JTextField name;
	
	ChooseCharacterScreen(Game game) {
		this.game = game;
		initialize();
		window.setVisible(true);
	}

	public void finishedWindow() {
		window.dispose();
	}
	
	public void closeWindow() {
		game.closeChooseCharacterScreen(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setBounds(new Rectangle(0, 0, 880, 610));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(new BoxLayout(window.getContentPane(), BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		
		window.getContentPane().add(panel);
		
		panel.setLayout(null);
		
		JLabel lblGameName = new JLabel("Game Name");
		lblGameName.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameName.setBounds(0, 0, 878, 27);
		panel.add(lblGameName);
		
		JLabel lblCharacterName = new JLabel("Character Name:");
		lblCharacterName.setBounds(33, 53, 126, 27);
		panel.add(lblCharacterName);
		
		name = new JTextField();
		name.setBounds(233, 55, 143, 23);
		panel.add(name);
		name.setColumns(10);
		
		JLabel lblCharacterType = new JLabel("Character Type:");
		lblCharacterType.setBounds(33, 110, 126, 27);
		panel.add(lblCharacterType);
		
		CrewMember cm = new CrewMember("","","",0,"");
		String[] types = cm.getAllTypes();
		JComboBox type = new JComboBox(types);

		type.setBounds(233, 111, 143, 24);
		panel.add(type);
		
		
		JButton btnNext = new JButton("Add");
		btnNext.setBounds(688, 524, 114, 25);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numTimesPressed++;
				if (btnNext.getText() == "Continue") {
					closeWindow();
				}
				if (numTimesPressed == game.getGameEnvironment().getCrew().getNumMembers()) {
					type.setEnabled(false);
					name.setEditable(false);
					btnNext.setText("Continue");
				}
				String typeStr = String.valueOf(type.getSelectedItem());
				game.getGameEnvironment().getCrew().addCrewMember(typeStr, name.getText());
				name.setText("");
			}
		});
		panel.add(btnNext);
		
		Barter bart = new Barter("");
		JLabel descriptionLabel = new JLabel(bart.getDescription());
		descriptionLabel.setBounds(489, 59, 340, 258);
		panel.add(descriptionLabel);

		type.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Run a switch: and get the descriptions....update a local label to it...
				String label = "";
				switch ((String)  type.getSelectedItem()) {
		            case "Barter":
		                Barter bart = new Barter("");
		                label = bart.getDescription();
		            break;
		            case "Mechanic":
		                Mechanic mech = new Mechanic("");
		                label = mech.getDescription();
		            break;
		            case "Nerd":
		                Nerd nerd = new Nerd("");
		                label = nerd.getDescription();
		            break;
		            case "Scout":
		                Scout scout = new Scout("");
		                label = scout.getDescription();
		            break;
		            case "Tank":
		                Tank tank = new Tank("");
		                label = tank.getDescription();
		            break;
				}
				descriptionLabel.setText(label);
			}
		});
		
	}
}
