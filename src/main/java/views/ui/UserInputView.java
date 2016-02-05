package views.ui;

import controllers.Validator;
import controllers.exceptions.ControllerFailException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eunderhi on 27/11/15.
 */
public abstract class UserInputView extends UIComponent {

    private List<Validator> validators = new ArrayList<>();
    private boolean isValid = false;

    public void addValidator(Validator validator) {
        validators.add(validator);
    }

    public void validate() {
        try {
            checkAllValidators();
        }
        catch (ControllerFailException e) {
            setValid(false);
            onValidationFail(e);
        }
    }

    private void checkAllValidators() throws ControllerFailException {
        for (Validator v : validators) {
            v.validate();
        }
        setValid(true);
        onValidationSuccess();
    }

    protected abstract void onValidationFail(ControllerFailException e);
    protected abstract void onValidationSuccess();

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

}
