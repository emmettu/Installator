package views.ui.gui;

import models.ValidatorContainers.FieldValidation;

import javax.swing.*;

/**
 * Created by eunderhi on 11/02/16.
 */
public class GUITextInputField extends GUIComponent implements TextInputField, Validated {

    private JTextField field = new JTextField();
    private FieldValidation container = new FieldValidation(this);

    public GUITextInputField() {
        setJComponent(field);
    }

    @Override
    public void update() {
        if (container.validate()) {
            super.update();
        }
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
        container.validate();
    }

    @Override
    public FieldValidation validation() {
        return container;
    }

}
