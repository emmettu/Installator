package views.ui.gui;

import controllers.progressbar.ProgressBarController;
import models.packaging.utils.PackageSet;
import models.unpacking.Unpacker;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;

/**
 * Created by eunderhi on 18/03/16.
 * Convenience panel to show the progress of a packageSet getting unpacked.
 */
public class PackageProgressPanel extends GUIPanel {

    public PackageProgressPanel(PackageSet packages, String[] names) {
        setJComponent(new JPanel());
        setLayout(new GridLayout(names.length + 1, 1));
        GUILabel currentFile = new GUILabel();
        GUIPanel currentFilePanel = new GUIPanel(new GridLayout());
        currentFilePanel.addComponent(currentFile);
        addComponent(currentFilePanel);
        for (Unpacker unpacker : packages.getUnpackers()) {
            GUIProgressBar bar = setUpProgressBar(unpacker);
            GUIPanel panel = new GUIPanel(new GridLayout(1, 2));
            panel.addComponent(bar);
            panel.addComponent(new GUILabel("Test"));
            addComponent(panel);
        }
        packages.addController(() -> {
            Path unpackedFile = packages.getCurrentFile();
            if (unpackedFile != null) {
                currentFile.setText("Unpacking: " + unpackedFile.toString());
            }
        });
    }

    private GUIProgressBar setUpProgressBar(Unpacker unpacker) {
        GUIProgressBar bar = new GUIProgressBar();
        ProgressBarController pbc = new ProgressBarController(bar);
        pbc.setUnpacker(unpacker);
        unpacker.addController(pbc);
        return bar;
    }

}
