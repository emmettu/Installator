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

    protected GUITextInputField field;
    private GUILabel message = new GUILabel();

    public FancyGUITextField() {
        setField();
        setLayout(new BorderLayout());
        addComponent(field, BorderLayout.NORTH);
        addComponent(message, BorderLayout.SOUTH);
        message.setColor(UiResources.validationFail);
        field.addController(FancyGUITextField.this::notifyControllers);
        validation().addHook(e -> fail(e.getMessage()), Validation.Type.FAIL);
        validation().addHook(e -> succeed(), Validation.Type.SUCCESS);
        validation().addHook(e -> warn(e.getMessage()), Validation.Type.WARN);
    }

    protected void setField() {
        field = new GUITextInputField();
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
    public boolean validate() {
        return field.validate();
    }

    @Override
    public Validation<String> validation() {
        return field.validation();
    }

    public void fail(String errorMsg) {
        message.setText(errorMsg);
        message.setColor(UiResources.validationFail);
        message.setVisible(true);
    }

    public void succeed() {
        message.setVisible(false);
    }

    public void warn(String warnMsg) {
        message.setText(warnMsg);
        message.setColor(UiResources.validationWarn);
        message.setVisible(true);
    }

    public void setColumns(int cols) {
        field.setColumns(cols);
    }

}
