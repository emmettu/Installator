package views.ui.progressbar;

import javax.swing.*;

/**
 * Created by eunderhi on 16/12/15.
 */
public class GUIProgressBar extends ProgressBar {

    private JProgressBar bar = new JProgressBar();

    public GUIProgressBar() {
        bar.setValue(getPercentDone());
        bar.setStringPainted(true);
    }

    @Override
    public void update() {
        bar.setValue(getPercentDone());
        display();
    }

    @Override
    public void display() {
        bar.revalidate();
    }

    public JProgressBar getBar() {
        return bar;
    }

}
