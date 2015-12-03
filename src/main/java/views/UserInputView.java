package views;

import controllers.Controller;
import controllers.Validator;

import java.util.List;

/**
 * Created by eunderhi on 27/11/15.
 */
public abstract class UserInputView implements View {
    List<Controller> controllers;

    public void notifyControllers() {
        for(Controller controller : controllers) {
            controller.performAction();
        }
    }

    public void addValidator(Validator validator) {
        validator.setUserInputView(this);
        controllers.add(validator);
    }

}
