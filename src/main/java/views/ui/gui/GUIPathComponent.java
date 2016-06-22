package views.ui.gui;

import controllers.Validator;
import controllers.textinput.DirectoryValidator;
import controllers.textinput.FileValidator;

import java.awt.*;
import java.io.File;

/**
 * Created by eunderhi on 17/03/16.
 * A text input field but with a file chooser.
 */
public class GUIPathComponent extends GUIPanel {

    private FileChooser fileChooser = new FileChooser();
    private FancyGUITextField pathField = new FancyGUITextField();

    public static GUIPathComponent newFileComponent() {
        return new GUIPathComponent(new FileValidator());
    }

    public static GUIPathComponent newDirectoryComponent() {
        return new GUIPathComponent(new DirectoryValidator());
    }

    private GUIPathComponent(Validator<String> validator) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        fileChooser.directoriesOnly();
        GUIButton browseButton = new GUIButton("Browse");
        browseButton.addController(() -> {
            fileChooser.setCurrentDirectory(getCurrentDirectory());
            fileChooser.showDialog(GUIPathComponent.this, "Choose");
            File chosenFile = fileChooser.getSelectedFile();
            if (chosenFile != null) {
                pathField.setText(chosenFile.getPath());
                pathField.validate();
            }
        });
        pathField.validation().add(validator);
        pathField.setText(getDefaultPath());
        addComponent(pathField, gbc);
        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.fill = GridBagConstraints.NONE;
        gbcButton.anchor = GridBagConstraints.NORTHEAST;
        //gbcButton.weightx = 1.0;
        gbcButton.weighty = 1.0;
        addComponent(browseButton, gbcButton);
    }

    private File getCurrentDirectory() {
        File curDir = new File(pathField.getText());
        return curDir.exists() ? curDir : new File(getDefaultPath());
    }

    private String getDefaultPath() {
        return System.getProperty("user.home");
    }

    public FancyGUITextField getField() {
        return pathField;
    }

}
