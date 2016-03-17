package views.ui.button;

import controllers.exceptions.ControllerFailException;
import controllers.exceptions.ControllerWarnException;

/**
 * Created by eunderhi on 08/12/15.
 */
public class ConsoleButton extends Button {

    @Override
    public void update() {
        display();
        System.console().readLine();
        notifyControllers();
    }

    @Override
    public void display() {
        System.out.print(getText());
    }

}
