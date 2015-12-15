package views.ui.textstream;

import views.ui.UIComponent;

/**
 * Created by eunderhi on 14/12/15.
 */
public abstract class TextStream extends UIComponent {

    protected String text;

    public void updateText(String text) {
        this.text = text;
    }

}
