package views.ui.button;

import views.ui.UIComponent;

/**
 * Created by eunderhi on 08/12/15.
 */
public abstract class Button extends UIComponent {

    private String text = "";

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
