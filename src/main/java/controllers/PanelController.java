package controllers;

import views.ui.gui.Frame;

/**
 * Created by eunderhi on 10/03/16.
 */
public class PanelController implements Controller {

    private Frame frame;
    private boolean reverse = false;

    public PanelController(Frame frame) {
        this.frame = frame;
    }

    public void setReverse() {
        reverse = true;
    }

    @Override
    public void performAction() {
        if (reverse) {
            frame.previousPanel();
        }
        frame.nextPanel();
    }

}
