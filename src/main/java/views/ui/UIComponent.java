package views.ui;

import controllers.Controller;
import controllers.exceptions.ControllerFailException;
import controllers.exceptions.ControllerWarnException;
import views.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eunderhi on 07/12/15.
 */

public abstract class UIComponent implements View {

    List<Controller> controllers = new ArrayList<>();

    public void notifyControllers() {
        try {
            checkAllControllers();
        }
        catch (ControllerFailException e) {
            onControllerFail(e);
        }
    }

    private void checkAllControllers() throws ControllerFailException {
        for(Controller c : controllers) {
            performAction(c);
        }
    }

    private void performAction(Controller c) throws ControllerFailException {
        c.performAction();
    }

    protected abstract void onControllerFail(ControllerFailException e);
    protected abstract void onControllerWarn(ControllerWarnException e);

    public void addController(Controller controller) {
        controllers.add(controller);
    }

}
