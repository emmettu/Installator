package controllers.textinput;

import controllers.Validator;
import controllers.exceptions.ControllerFailException;
import views.ui.textinput.TextInputField;

import java.io.File;

/**
 * Created by eunderhi on 19/01/16.
 */
public class PathValidator extends Validator {

    private TextInputField field;

    @Override
    public void validate() throws ControllerFailException {

        String pathName = field.getText();
        File file = new File(pathName);

        if (!file.exists()) {
            throw new ControllerFailException("Path must exist");
        }
        else if (!file.isDirectory()) {
            throw new ControllerFailException("Path must be a directory.");
        }

        else if (!file.canWrite()) {
            throw new ControllerFailException("Path must be write-able.");
        }
    }

    public void setField(TextInputField field) {
        this.field = field;
    }

}
