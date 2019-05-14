import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrewStatusPanel extends JPanel {
    private Game game;

    CrewStatusPanel(Game game) {
        super();
        this.game = game;

        setLayout(new GridLayout(1, 0, 0, 0));
        addPanels();
    }

    public void refresh() {   
        removeAll();
        addPanels();
    }

    public void addPanels() {
        for (CrewMember member: game.getGameEnvironment().getCrew().getMembers()) {
            JPanel panelMember = new JPanel();
            panelMember.setLayout(new BoxLayout(panelMember, BoxLayout.Y_AXIS));
            
            JPanel panel1M = new JPanel();
            JLabel label = new JLabel(member.getName());
            panel1M.add(label);
            panelMember.add(panel1M);
            
            JPanel panel2M = new JPanel();
            JLabel label2 = new JLabel("Max Health: " + member.getMaxHealth());
            panel2M.add(label2);
            panelMember.add(panel2M);
            
            JPanel panel3M = new JPanel();
            JLabel label3 = new JLabel("Current Health: " + member.getCurrentHealth());
            panel3M.add(label3);
            panelMember.add(panel3M);
            
            JPanel panel4M = new JPanel();
            JLabel label4 = new JLabel("Tiredness level: " + member.getTiredness());
            panel4M.add(label4);
            panelMember.add(panel4M);
            
            JPanel panel5M = new JPanel();
            JLabel label5 = new JLabel("Hunger level: " + member.getHungerLevel());
            panelMember.add(label5);
            panel5M.add(label5);
            panelMember.add(panel5M);
            
            JPanel panel6M = new JPanel();
            JLabel label6 = new JLabel("Speciality: " + member.getSpecialty());
            panel6M.add(label6);
            panelMember.add(panel6M);
            
            JPanel panel7M = new JPanel();
            JLabel label7 = new JLabel("Description: " + member.getDescription());
            panel7M.add(label7);
            panelMember.add(label7);
            
            String isSickStr = "no";
            if (member.isSick()) {
                isSickStr = "yes";
            }
            JPanel panel8M = new JPanel();
            JLabel label8 = new JLabel("Is sick: " + isSickStr);
            panel8M.add(label8);
            panelMember.add(panel8M);
            
            JPanel buttonSleep = new JPanel();
            JButton btnSleep = new JButton("Sleep");
            buttonSleep.add(btnSleep);
            panelMember.add(buttonSleep);

            btnSleep.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Sleep
                    if (member.hasActionsLeft()) {
                        member.sleep();
                        member.removeAction();
                    }
                }
            });
            
            add(panelMember);
        }
    }
}