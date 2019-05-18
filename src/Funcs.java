import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import java.awt.Image;

public class Funcs {
    public static boolean isValidNumDays(int days) {
        if (days >= 3 && days <= 10) {
            return true;
        }
        return false;
    }

    public static boolean isValidNumCharacters(int num) {
        if (num >= 2 && num <= 4) {
            return true;
        }
        return false;
    }

    public static JRadioButton selectedButton(ButtonGroup buttonGroup) {
        JRadioButton selectedButton = null;
        for (Enumeration<AbstractButton> e = buttonGroup.getElements(); e.hasMoreElements();) {
            JRadioButton button = (JRadioButton) e.nextElement();
            if (button.isSelected()) {
                selectedButton = button;
            }
        }
        return selectedButton;
    }

    public static ImageIcon getScaledIcon(String path, int width, int height) {
        ImageIcon imageIcon = new ImageIcon(path);
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}