package views.ui.gui;

import javax.swing.*;

/**
 * Created by eunderhi on 25/01/16.
 */
public class GUIProgressBar extends GUIComponent implements ProgressBar{

    private JProgressBar bar = new JProgressBar();

    public GUIProgressBar() {
        getJComponent().add(bar);
    }

    @Override
    public int getPercentDone() {
        return bar.getValue();
    }

    @Override
    public void setPercentDone(int percentDone) {
        bar.setValue(percentDone);
    }
}
