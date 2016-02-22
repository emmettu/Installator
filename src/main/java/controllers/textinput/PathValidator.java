package controllers.textinput;

import controllers.Validator;
import controllers.exceptions.ControllerFailException;
import controllers.exceptions.ControllerWarnException;

import java.io.File;

/**
 * Created by eunderhi on 19/01/16.
 */
public class PathValidator implements Validator<String> {

    @Override
    public void validate(String pathName) throws ControllerFailException, ControllerWarnException {

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

}
