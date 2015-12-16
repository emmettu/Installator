package views.ui.progressbar;

import javax.swing.*;

/**
 * Created by eunderhi on 16/12/15.
 */
public class GUIProgressBar extends ProgressBar {

    private JProgressBar bar = new JProgressBar();

    public GUIProgressBar() {
        bar.setValue(getPercentDone());
    }

    @Override
    public void update() {
        bar.setValue(getPercentDone());
        display();
    }

    @Override
    public void display() {
        bar.update(bar.getGraphics());
    }

    public JProgressBar getBar() {
        return bar;
    }

}
