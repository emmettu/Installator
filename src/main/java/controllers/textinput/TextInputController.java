package controllers.textinput;

import controllers.Controller;
import views.ui.gui.TextInputField;

/**
 * Created by eunderhi on 07/12/15.
 */
public abstract class TextInputController implements Controller {

    private TextInputField field;

    public TextInputController(TextInputField field) {
        this.field = field;
    }

    public void setTextInputField(TextInputField textInputField) {
        this.field = textInputField;
    }

    protected TextInputField getTextInputField() {
        return field;
    }

}
