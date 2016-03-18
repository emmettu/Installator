package models.validation;

import controllers.Validator;
import controllers.exceptions.ControllerFailException;
import controllers.exceptions.ControllerWarnException;
import views.ui.gui.TextInputField;

/**
 * Created by eunderhi on 16/02/16.
 * Validation class for TextInputFields
 */
public class FieldValidation extends Validation<String> {

    private TextInputField field;

    public FieldValidation(TextInputField field) {
        this.field = field;
    }

    @Override
    protected void validateComponent(Validator<String> v) throws ControllerWarnException, ControllerFailException {
        v.validate(field.getText());
    }

    @Override
    public void handleWarn(ControllerWarnException e) {
        runHooks(Type.WARN, e);
    }

    @Override
    public void handleFail(ControllerFailException e) {
        runHooks(Type.FAIL, e);
    }

    @Override
    protected void handleSuccess() {
        runHooks(Type.SUCCESS, null);
    }

    @Override
    protected void handleConditionalSuccess() {
        runHooks(Type.CONDITIONAL_SUCCESS, null);
    }

}
