package views.ui.gui;

import views.lookandfeel.FontResources;

import javax.swing.*;
import java.awt.*;

/**
 * Created by eunderhi on 25/05/16.
 */
public class Title extends GUILabel {

    public static GUILabel create(String text) {
        GUILabel title = new GUILabel(text, SwingConstants.LEFT);
        Font font = FontResources.getOpenSansLight();
        title.setFont(font);
        return title;
    }

}
