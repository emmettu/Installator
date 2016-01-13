package views.ui.progressbar;

import views.lookandfeel.patternfly.PatternflyProgressBarUI;

import javax.swing.*;

/**
 * Created by eunderhi on 16/12/15.
 */
public class GUIProgressBar extends ProgressBar {

    private JProgressBar bar = new JProgressBar();

    public GUIProgressBar() {
        bar.setValue(getPercentDone());
        bar.setStringPainted(true);
        bar.setUI(new PatternflyProgressBarUI());
        bar.setAlignmentX(0.5f);
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
