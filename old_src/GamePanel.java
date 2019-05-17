import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Rectangle;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.Font;

public class GamePanel {
    public GameEnvironment environment;

    public JFrame window;

    public JPanel startGamePanel;

    public JPanel setUpPanel;

    public JPanel gameHomePanel;

    public SpaceOutPostPanel spaceOutPostPanel;

    public JPanel contentPanel;

    public CardLayout cardLayout;
    
    public static final String GAME_HOME_PANEL_STRING = "GAME_HOME_PANEL";

    GamePanel() {
        init();
    }

    public static void main(String[] args) {
        new GamePanel();
    }

    public void init() {
        window = new JFrame();
        window.setBounds(new Rectangle(0, 0, 900, 700));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        cardLayout = new CardLayout();

        contentPanel = new JPanel();
        contentPanel.setLayout(cardLayout);

        addPanels();
        
        cardLayout.show(contentPanel, setUpPanel.);

        window.getContentPane().add(contentPanel);
        window.setVisible(true);
    }

    public void addPanels() {
        this.setUpPanel = new SetUpPanel(this);
        this.contentPanel.add(setUpPanel, SET_UP_PANEL_STRING);
    
        this.gameHomePanel = new JPanel();
        this.contentPanel.add(gameHomePanel, GAME_HOME_PANEL_STRING);
        gameHomePanel.add(new JLabel("ASDASDASD"));
        for (CrewMember member: getGameEnvironment().getCrew().getMembers()) {
            gameHomePanel.add(new JLabel("Name" + member.getName()));
        }
    }
    
    public void startGame() {
    	cardLayout.show(contentPanel, GAME_HOME_PANEL_STRING);
    }

    public void setGameEnvironment(GameEnvironment environment) {
        this.environment = environment;
    }
    
    public GameEnvironment getGameEnvironment() {
        return environment;
    }
}