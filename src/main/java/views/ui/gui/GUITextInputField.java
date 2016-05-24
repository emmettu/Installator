package views.ui.gui;

import models.validation.FieldValidation;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Created by eunderhi on 11/02/16.
 */
public class GUITextInputField extends GUIComponent implements TextInputField, Validated {

    protected JTextField field;
    private FieldValidation container = new FieldValidation(this);

    public GUITextInputField() {
        field = new JTextField();
        setUpJComponent();
    }

    protected void setUpJComponent() {
        setJComponent(field);
        field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {}

            @Override
            public void focusLost(FocusEvent focusEvent) {
                update();
            }
        });
        field.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                update();
            }
            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                update();
            }
            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                update();
            }
        });
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
    public boolean validate() {
        return container.validate();
    }

    @Override
    public FieldValidation validation() {
        return container;
    }

    public void setColumns(int size) {
        field.setColumns(size);
    }

}
