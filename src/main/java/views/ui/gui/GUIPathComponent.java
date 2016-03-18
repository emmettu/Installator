package views.ui.gui;

import controllers.textinput.PathValidator;
import models.validation.Validation;

import java.awt.*;
import java.io.File;

/**
 * Created by eunderhi on 17/03/16.
 * A text input field but with a file chooser.
 */
public class GUIPathComponent extends GUIPanel {

    private FileChooser fileChooser = new FileChooser();
    private GUIButton browseButton = new GUIButton("Browse");
    private FancyGUITextField pathField = new FancyGUITextField();

    public GUIPathComponent() {
        setLayout(new GridLayout(1, 2));
        fileChooser.directoriesOnly();

        browseButton.addController(() -> {
            fileChooser.setCurrentDirectory(getCurrentDirectory());
            fileChooser.showDialog(pathField, "Choose");
            File chosenFile = fileChooser.getSelectedFile();
            if (chosenFile != null) {
                pathField.setText(chosenFile.getPath());
                pathField.validate();
            }
        });
        pathField.setText(getDefaultPath());
        pathField.validation().add(new PathValidator());
        pathField.validation().addHook(e -> pathField.fail(e.getMessage()), Validation.Type.FAIL);
        pathField.validation().addHook(e -> pathField.succeed(), Validation.Type.SUCCESS);
        addComponent(pathField);
        addComponent(browseButton);
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