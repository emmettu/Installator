package models;

import controllers.Controller;
import views.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eunderhi on 04/12/15.
 */
public abstract class InstallerModel implements Model {
    private List<Controller> controllers = new ArrayList<>();

    @Override
    public void notifyListeners() {
        for(Controller controller : controllers) {
            controller.performAction();
        }
    }

    public List<Controller> getControllers() {
        return controllers;
    }

    public void addController(Controller controller) {
        controllers.add(controller);

    }
}
