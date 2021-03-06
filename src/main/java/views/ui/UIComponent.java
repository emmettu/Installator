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
        for(Controller c : controllers) {
            c.performAction();
        }
    }

    public void addController(Controller controller) {
        controllers.add(controller);
    }

    public void addControllers(List<Controller> controllers) {
        this.controllers.addAll(controllers);
    }

}
