import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

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
        JPanel newPlanetPanel = new JPanel();
        newPlanetPanel.setLayout(new BoxLayout(newPlanetPanel, BoxLayout.Y_AXIS));

        JPanel lblChoosePlanetPanel = new JPanel();
        newPlanetPanel.add(lblChoosePlanetPanel);
        lblChoosePlanetPanel.setLayout(null);
        
        JLabel lblChooseAPlanet = new JLabel("Choose a Planet ");
        lblChooseAPlanet.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblChooseAPlanet.setBounds(24, 12, 300, 15);
        lblChoosePlanetPanel.add(lblChooseAPlanet);
        
        JPanel planets = new JPanel(new GridLayout(1,3));
        planets.setPreferredSize(new Dimension(1000, 120));
        newPlanetPanel.add(planets);

        ButtonGroup planetButtonGroup = new ButtonGroup();


        for (int i = 0; i < 3; i++) {
                JPanel insidePanel = new JPanel();
                //memberPanel.setBorder(new LineBorder(new Color(119, 119, 119)));
                insidePanel.setLayout(null);

                JRadioButton planetRadioBtn = new JRadioButton("Mars");
                planetRadioBtn.setBounds(20, 140, 150, 23);

                JLabel planetImageLabel = new JLabel("");
                planetImageLabel.setBounds(22, 10, 120, 120);
                planetImageLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
                planetImageLabel.setIcon(Funcs.getScaledIcon("mars.png", 120, 120));

                planetButtonGroup.add(planetRadioBtn);
                insidePanel.add(planetImageLabel);
                insidePanel.add(planetRadioBtn);
                planets.add(insidePanel);
        }

        ButtonGroup btnGroup = new ButtonGroup();
      
        JPanel lblChooseMemberOnePanel = new JPanel();
        newPlanetPanel.add(lblChooseMemberOnePanel);
        lblChooseMemberOnePanel.setLayout(null);
        
        JLabel lblChooseMemberTwo = new JLabel("Choose Member One");
        lblChooseMemberTwo.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblChooseMemberTwo.setBounds(12, 12, 300, 15);
        lblChooseMemberOnePanel.add(lblChooseMemberTwo);

        JPanel membersPanelOne = new JPanel(new GridLayout(1, 4));
        membersPanelOne.setPreferredSize(new Dimension(1000, 120));
        
            JPanel insidePanel = new JPanel();
            insidePanel.setLayout(null);

            JRadioButton memberRadio = new JRadioButton("Name");
            memberRadio.setBounds(20, 140, 150, 23);

            JLabel memberImageLabel = new JLabel("");
            memberImageLabel.setBounds(22, 10, 120, 120);
            memberImageLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
            memberImageLabel.setIcon(Funcs.getScaledIcon("barter.jpg", 120, 120));


            btnGroup.add(memberRadio);
            insidePanel.add(memberImageLabel);
            insidePanel.add(memberRadio);
            membersPanelOne.add(insidePanel);
        
        
        newPlanetPanel.add(membersPanelOne);
        JPanel lblChooseMemberTwoPanel = new JPanel();
        newPlanetPanel.add(lblChooseMemberTwoPanel);
        lblChooseMemberTwoPanel.setLayout(null);
        
        JLabel lblChooseMemberOne = new JLabel("Choose Member Two");
        lblChooseMemberOne.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblChooseMemberOne.setBounds(12, 12, 300, 15);
        lblChooseMemberTwoPanel.add(lblChooseMemberOne);
        
        ButtonGroup btnGroupTwp = new ButtonGroup();
        JPanel membersPanelTwo = new JPanel(new GridLayout(1, 4));
        membersPanelTwo.setPreferredSize(new Dimension(1000, 120));
        
            JPanel insidePanel2 = new JPanel();
            insidePanel.setLayout(null);
            

            JRadioButton memberRadio2 = new JRadioButton("Name");
            memberRadio2.setBounds(20, 140, 150, 23);

            JLabel memberImageLabel2 = new JLabel("");
            memberImageLabel2.setBounds(22, 10, 120, 120);
            memberImageLabel2.setBorder(new LineBorder(new Color(0, 0, 0)));
            memberImageLabel2.setIcon(Funcs.getScaledIcon("barter.jpg", 120, 120));


            btnGroupTwp.add(memberRadio2);
            insidePanel2.add(memberImageLabel2);
            insidePanel2.add(memberRadio2);
            membersPanelTwo.add(insidePanel2);
        
        
        newPlanetPanel.add(membersPanelTwo);
        
        JPanel goBtnPanel = new JPanel();
        goBtnPanel.setPreferredSize(new Dimension(1000, 20));
        goBtnPanel.setLayout(null);
        JButton doActionBtn = new JButton("Go");
        //doActionBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        doActionBtn.setBounds(797, 12, 52, 25);
        goBtnPanel.add(doActionBtn);
        newPlanetPanel.add(goBtnPanel);
        
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		frame.getContentPane().add(newPlanetPanel);
		frame.setVisible(true);
	}
}
