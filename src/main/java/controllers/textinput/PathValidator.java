package controllers.textinput;

import controllers.Validator;
import controllers.exceptions.ControllerFailException;
import controllers.exceptions.ControllerWarnException;

import java.io.File;

/**
 * Created by eunderhi on 19/01/16.
 * Given a string, checks if it is a valid path:
 * 1. Exists
 * 2. Is a directory
 * 3. Is write-able
 */
public class PathValidator implements Validator<String> {

    @Override
    public void validate(String pathName) throws ControllerFailException, ControllerWarnException {

        File file = new File(pathName);

        if (!file.exists()) {
            throw new ControllerFailException("This path does not exist.");
        }
        else if (!file.isDirectory()) {
            throw new ControllerFailException("This path must be a directory.");
        }
        else if (!file.canWrite()) {
            throw new ControllerFailException("The path must be write-able.");
        }
    }

}
