package views.ui.gui;

import models.validation.FieldValidation;
import models.validation.Validation;
import views.lookandfeel.ButtonFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * Created by eunderhi on 17/03/16.
 * A text input field but with a file chooser.
 */
public class GUIPathComponent extends GUIComponent implements TextInputField, Validated {

    private JFileChooser fileChooser;
    private JButton browseButton;
    private JTextField pathField;
    private JPanel container;
    private FieldValidation validator = new FieldValidation(this);

    public GUIPathComponent() {
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        browseButton = ButtonFactory.createButton("Browse");
        pathField = new JTextField();
        container = new JPanel();
        setJComponent(container);
        container.add(pathField);
        container.add(browseButton);
        browseButton.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                fileChooser.showDialog(container, "Choose");
                File chosenFile = fileChooser.getSelectedFile();
                if (chosenFile != null) {
                    setText(chosenFile.getPath());
                }
            }
        });
        container.setLayout(new GridLayout(1, 2));
    }

    @Override
    public void update() {
        if (validator.validate()) {
            super.update();
        }
    }

    @Override
    public String getText() {
        return pathField.getText();
    }

    @Override
    public void setText(String text) {
        pathField.setText(text);
    }

    @Override
    public void validate() {
        validation().validate();
    }

    @Override
    public Validation validation() {
        return validator;
    }

}
