package controllers.textinput;

import controllers.Controller;
import views.ui.textinput.TextInputField;

/**
 * Created by eunderhi on 07/12/15.
 */
public abstract class TextInputController implements Controller {

    private TextInputField textInputField;

    public void setTextInputField(TextInputField textInputField) {
        textInputField.addController(this);
        this.textInputField = textInputField;
    }

    protected TextInputField getTextInputField() {
        return textInputField;
    }

}
