package views.ui.gui;

import views.lookandfeel.patternfly.PatternflyProgressBarUI;

import javax.swing.JProgressBar;

/**
 * Created by eunderhi on 25/01/16.
 */
public class GUIProgressBar extends GUIComponent implements ProgressBar{

    private JProgressBar bar = new JProgressBar();

    public GUIProgressBar() {
        setJComponent(bar);
        bar.setStringPainted(true);
        bar.setUI(new PatternflyProgressBarUI());
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
