package views.lookandfeel.buttonstyles;

import views.lookandfeel.FontResources;
import views.lookandfeel.UiResources;
import views.lookandfeel.patternfly.PatternflyButtonUI;
import views.ui.gui.GUIButton;

import java.awt.*;

/**
 * Created by eunderhi on 13/01/16.
 * A collection of useful buttons
 */
public class ButtonEmporium {

    public static GUIButton nextButton(String text) {
        GUIButton button = new GUIButton("<html>" + text + " " + FontResources.buttonRightIcon + "</html>");
        button.setUI(new PatternflyButtonUI(Color.WHITE, UiResources.blueButtonLight, UiResources.blueButtonDark, UiResources.blueButtonShadow, UiResources.blueButtonStroke));
        button.setForeground(Color.WHITE);
        return button;
    }

    public static GUIButton previousButton(String text) {
        return new GUIButton("<html>" +FontResources.buttonLeftIcon + " " +  text + "</html>");
    }

}
