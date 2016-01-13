package views.lookandfeel.buttonstyles;

import views.lookandfeel.ButtonFactory;
import views.lookandfeel.FontResources;
import views.lookandfeel.UiResources;
import views.lookandfeel.patternfly.PatternflyButtonUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by eunderhi on 13/01/16.
 */
public class ButtonEmporium {

    public static JButton blueGradient(String text) {
        JButton button = ButtonFactory.createButton("<html>" + text + " " + FontResources.buttonRightIcon + "</html>");
        button.setUI(new PatternflyButtonUI(Color.WHITE, UiResources.blueButtonLight, UiResources.blueButtonDark, UiResources.blueButtonShadow, UiResources.blueButtonStroke));
        button.setForeground(Color.WHITE);
        return button;
    }

    public static JButton greyGradient(String text) {
        JButton button = ButtonFactory.createButton("<html>" + text + " " + FontResources.buttonLeftIcon + "</html>");
        return button;
    }

}
