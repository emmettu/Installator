package views.lookandfeel;

import javax.swing.*;
import java.awt.*;

/**
 * General UI resources, such as colors and styles
 * on 3/26/15.
 */
public class UiResources {
    public static final Color progressBarGradientLight = new Color(0x00a8e1);
    public static final Color progressBarGradientDark = new Color(0x006e9c);
    public static final Color blueButtonLight = new Color(0x01abec);
    public static final Color blueButtonDark = new Color(0x0099d3);
    public static final Color blueButtonStroke = new Color(0x21799e);
    public static final Color blueButtonShadow = new Color(0x227ba1);
    public static final Color blueFocus = new Color(0x80b2FF);
    public static final Color grayButtonLight = new Color(0xfafafa);
    public static final Color grayButtonDark = new Color(0xededed);
    public static final Color grayButtonStroke = new Color(0xbbbbbb);
    public static final Color grayButtonShadow = new Color(0xbebebe);
    public static final Color grayDisabledButton = new Color(0xf5f5f5);
    public static final Color grayDisabledText = new Color(0x969696);
    public static final Color grayDisabledStroke = new Color(0xd1d1d1);
    public static final Color grayQuestionMark = new Color(0x72767b);
    public static final Color blueListRollover = new Color(0x38a3de);
    public static final Color checkMarkGreen = new Color(0x56a81c);
    public static final Color sideNavigationBackGround = new Color(0xF5F5F5);
    public static final Color sidenavigationTextInactive = new Color(0x72767b);
    public static final Color sidenavigationTextActive = Color.BLACK;
    public static final Color inactiveTextField = new Color(0xF5F5F5);
    public static final Color navPanelBackground = new Color(0xDDDDDD);
    public static final Color headingPanelBackground = new Color(0x212121);
    public static final Color validationFail = new Color(0xFF4F4C);
    public static final Color validationWarn = new Color(0xFF9710);

    public static Paint getGradientPaint(JComponent component, Color top, Color bottom){
        return new GradientPaint(component.getWidth()/2, 0, top, component.getWidth()/2, component.getHeight(), bottom);
    }

    public static Paint getGradientPaint(Rectangle bounds, Color top, Color bottom){
        return new GradientPaint(bounds.width/2, 0, top, bounds.width/2, bounds.height, bottom);
    }
}
