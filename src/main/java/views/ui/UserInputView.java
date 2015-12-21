package views.ui;

import controllers.Controller;
import controllers.Validator;
import views.ui.UIComponent;

/**
 * Created by eunderhi on 27/11/15.
 */
public abstract class UserInputView extends UIComponent {

    public void addValidator(Validator validator) {
        validator.setUserInputView(this);
        addController(validator);
    }

    @Override
    public void addController(Controller controller) {
        super.addController(controller);
    }

}
