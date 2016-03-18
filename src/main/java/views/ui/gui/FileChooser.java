package views.ui.gui;

import javax.swing.*;
import java.io.File;

/**
 * Created by eunderhi on 18/03/16.
 * Wrapper on JFileChooser
 */
public class FileChooser extends GUIComponent {

    private JFileChooser chooser = new JFileChooser();

    public FileChooser() {
        setJComponent(chooser);
    }

    public void directoriesOnly() {
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    }

    public void filesOnly() {
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    }

    public void filesAndDirectories() {
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    }

    public void setCurrentDirectory(File directory) {
        chooser.setCurrentDirectory(directory);
    }

    public void showDialog(GUIComponent context, String name) {
        chooser.showDialog(context.getJComponent(), name);
    }

    public File getSelectedFile() {
        return chooser.getSelectedFile();
    }

}
