package views.ui.gui;

import models.validation.Validation;
import views.lookandfeel.UiResources;

import java.awt.*;

/**
 * Created by eunderhi on 18/03/16.
 * Wraps GUITextInputField and adds the ability to have sweet red text under it saying how the
 * validation is totally wrong :D!
 */

public class FancyGUITextField extends GUIPanel implements Validated, TextInputField {

    private GUITextInputField field = new GUITextInputField();
    private GUILabel message = new GUILabel();

    public FancyGUITextField() {
        setLayout(new BorderLayout());
        addComponent(field, BorderLayout.NORTH);
        addComponent(message, BorderLayout.SOUTH);
        message.setColor(UiResources.validationFail);
    }

    @Override
    public String getText() {
        return field.getText();
    }

    @Override
    public void setText(String text) {
        field.setText(text);
    }

    @Override
    public void validate() {
        field.validate();
    }

    @Override
    public Validation<String> validation() {
        return field.validation();
    }

    public void fail(String errorMsg) {
        message.setText(errorMsg);
        message.setVisible(true);
    }

    public void succeed() {
        message.setVisible(false);
    }

}
