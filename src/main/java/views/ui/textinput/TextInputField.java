package views.ui.textinput;

import views.ui.UIComponent;

/**
 * Created by eunderhi on 07/12/15.
 */
public abstract class TextInputField extends UIComponent {

    private String text = "";

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isEmpty() {
        return getText().isEmpty();
    }

}
