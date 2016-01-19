package views.ui;

import controllers.Validator;
import controllers.exceptions.ValidationException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eunderhi on 27/11/15.
 */
public abstract class UserInputView extends UIComponent {

    List<Validator> validators = new ArrayList<>();

    public void addValidator(Validator validator) {
        validators.add(validator);
    }

    public void validate() {
        try {
            checkAllValidators();
        }
        catch (ValidationException e) {
            onValidationFail(e);
        }
    }

    private void checkAllValidators() throws ValidationException {
        for (Validator v : validators) {
            v.validate();
        }

        onValidationSuccess();

    }

    protected abstract void onValidationFail(ValidationException e);
    protected abstract void onValidationSuccess();

}
