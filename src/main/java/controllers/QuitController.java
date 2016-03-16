package controllers;

import views.ui.gui.Frame;

/**
 * Created by eunderhi on 16/03/16.
 */
public class QuitController implements Controller {

    public Frame frame;

    public QuitController(Frame frame) {
        this.frame = frame;
    }

    @Override
    public void performAction() {
        frame.quit();
    }

}
