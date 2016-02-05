package views.ui.textstream;

import controllers.exceptions.ControllerFailException;
import controllers.exceptions.ControllerWarnException;

/**
 * Created by eunderhi on 14/12/15.
 */
public class ConsoleTextStream extends TextStream {

    @Override
    public void update() {
        display();
    }

    @Override
    public void display() {
        System.out.println(text);
    }

    @Override
    protected void onControllerFail(ControllerFailException e) {

    }

    @Override
    protected void onControllerWarn(ControllerWarnException e) {

    }
}
