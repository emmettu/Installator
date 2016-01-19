package views.ui;

import controllers.Controller;
import views.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eunderhi on 07/12/15.
 */
public abstract class UIComponent implements View {

    List<Controller> controllers = new ArrayList<>();

    public void notifyControllers() {
        for (Controller controller : controllers) {
            controller.performAction();
        }
    }

    public void addController(Controller controller) {
        controllers.add(controller);
    }

}
