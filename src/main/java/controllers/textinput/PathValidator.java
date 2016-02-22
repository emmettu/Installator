package controllers.textinput;

import controllers.Validator;
import controllers.exceptions.ControllerFailException;
import controllers.exceptions.ControllerWarnException;
import views.ui.textinput.TextInputField;

import java.io.File;

/**
 * Created by eunderhi on 19/01/16.
 */
public class PathValidator extends Validator<String> {

    @Override
    public void validate(String pathName) throws ControllerFailException, ControllerWarnException {

        File file = new File(pathName);

        if (!file.exists()) {
            throw new ControllerFailException("Path must exist");
        }
        else if (!file.isDirectory()) {
            throw new ControllerFailException("Path must be a directory.");
        }
        else if (file.listFiles() != null && file.listFiles().length != 0) {
            throw new ControllerFailException("Path must be empty");
        }
        else if (!file.canWrite()) {
            throw new ControllerFailException("Path must be write-able.");
        }
    }

}
