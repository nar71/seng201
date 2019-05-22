import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import java.awt.Image;

public class Funcs {

    /**
     * Helper method that checks if given days are valid.
     * @param numDays The given number of days.
     * @return boolean True if valid days False otherwise.
     */
    public static boolean isValidNumDays(int numDays) {
        if (numDays >= 3 && numDays <= 10) {
            return true;
        }
        return false;
    }

    /**
     * Helper method that checks if given number of characters is valid.
     * @param numChars The given number of characters.
     * @return boolean True if valid days False otherwise.
     */
    public static boolean isValidNumCharacters(int numChars) {
        if (numChars >= 2 && numChars <= 4) {
            return true;
        }
        return false;
    }
    
    /**
     * Helper method that finds the selected JRadioButton and returns it.
     * @param buttonGroup The given button group.
     * @return selectedButton The selected JRadioButton.
     */
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

    /**
     * Helper method that returns a scaled image to the given dimensions.
     * @param path The image path as a string.
     * @param width The width as an integer.
     * @param height The height as an integer.
     * @return new ImageIcon..
     */
    public static ImageIcon getScaledIcon(String path, int width, int height) {
        ImageIcon imageIcon = new ImageIcon(path);
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}