package controllers.textinput;

import controllers.Validator;
import controllers.exceptions.ValidationException;
import views.ui.textinput.TextInputField;

import java.io.File;

/**
 * Created by eunderhi on 19/01/16.
 */
public class PathValidator extends Validator {

    private TextInputField field;

    @Override
    public void validate() throws ValidationException {

        String pathName = field.getText();
        File file = new File(pathName);

        if (!file.exists()) {
            throw new ValidationException("Path must exist");
        }
        else if (!file.isDirectory()) {
            throw new ValidationException("Path must be a directory.");
        }

        else if (!file.canWrite()) {
            throw new ValidationException("Path must be write-able.");
        }
    }

    public void setField(TextInputField field) {
        this.field = field;
    }

}
