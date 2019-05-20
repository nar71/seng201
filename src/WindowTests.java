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
        JPanel titlePanel = new JPanel();
        
        JPanel mainPanel = new JPanel();
        mainPanel.add(titlePanel);

        JLabel lblGameTitle = new JLabel("SPACE TRAVELLERS");
        lblGameTitle.setFont(new Font("Ringbearer", Font.PLAIN, 30));
        lblGameTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblGameTitle.setBounds(10, 30, 1000, 150);
        titlePanel.add(lblGameTitle);

        JPanel topPanel = new JPanel();
        //topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setLayout(null);

        //JPanel homeBtnPanel = new JPanel();
        //topPanel.add(homeBtnPanel);
        JButton homeBtn = new JButton("Home");
        homeBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
        homeBtn.setBounds(10, 30, 150, 20);
        homeBtn.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(homeBtn);
        //homeBtnPanel.add(homeBtn);

        //JPanel dayLabelPanel = new JPanel();
        //topPanel.add(dayLabelPanel);
        JLabel dayLabel = new JLabel("");
        dayLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        dayLabel.setBounds(10, 60, 150, 20);
        dayLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(dayLabel);
       // dayLabelPanel.add(dayLabel);

        //JPanel currentPlanetPanel = new JPanel();
        //topPanel.add(currentPlanetPanel);
        JLabel currentPlanetLabel = new JLabel("");
        currentPlanetLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        currentPlanetLabel.setBounds(10, 90, 150, 20);
        currentPlanetLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(currentPlanetLabel);
        //currentPlanetPanel.add(currentPlanetLabel);

        //JPanel peicesRequiredPanel = new JPanel();
        //topPanel.add(peicesRequiredPanel);
        JLabel peicesRequiredLabel = new JLabel("");
        peicesRequiredLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        peicesRequiredLabel.setBounds(10, 120, 150, 20);
        peicesRequiredLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(peicesRequiredLabel);
        
        mainPanel.add(topPanel);
        
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		frame.getContentPane().add(mainPanel);
		frame.setVisible(true);
	}
}
