package controllers.textinput;

import controllers.Validator;
import controllers.exceptions.ControllerFailException;
import controllers.exceptions.ControllerWarnException;

import java.io.File;

/**
 * Created by eunderhi on 22/06/16.
 */
public class FileValidator implements Validator<String> {


    @Override
    public void validate(String pathName) throws ControllerFailException, ControllerWarnException {

        File file = new File(pathName);

        if (file.exists()) {
            throw new ControllerFailException("This path exists already");
        }
        if (!file.getParentFile().canWrite()) {
            throw new ControllerFailException("The path must be write-able.");
        }
    }

}
