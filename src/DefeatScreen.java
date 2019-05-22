import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.border.LineBorder;

public class DefeatScreen {

    /**
     * The game window manager.
     */ 
    private Game game;

    /**
     * The main game, the Game Environment holds all the data as the user goes through the game.
     * It holds the crew members, space out post, space ship, medical supplies etc.
     */ 
    private GameEnvironment environment;

    /**
     * A string representation of why the player lost the game.
     */ 
    private String error;
    
    /**
     * Defeat Screen constructor
     * @param game The game manager
     * @param error The error message
     */
    public DefeatScreen(Game game, String error) {
        this.game = game;
        this.error = error;
        this.environment = game.getGameEnvironment();
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        JFrame window = new JFrame();
        window.setBounds(new Rectangle(0, 0, 1000,810));
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setLayout(new BoxLayout(window.getContentPane(), BoxLayout.X_AXIS));
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        window.getContentPane().add(panel);
        
        JLabel lblNewLabel = new JLabel("Defeat");
        lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 24));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(12, 12, 974, 81);
        panel.add(lblNewLabel);
        
        JButton btnNewButton = new JButton("Play Again");
        btnNewButton.setBounds(200, 635, 115, 40);
        panel.add(btnNewButton);
        
        JButton button = new JButton("Exit");
        button.setBounds(700, 635, 115, 40);
        panel.add(button);
        
        JLabel lblNewLabel_1 = new JLabel(error);
        lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 16));
        lblNewLabel_1.setBounds(200, 102, 615, 40);
        panel.add(lblNewLabel_1);
        window.setVisible(true);
    }
}
