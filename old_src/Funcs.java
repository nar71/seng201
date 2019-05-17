import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

public class Funcs {
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
}