package controllers.textinput;

import controllers.Validator;
import controllers.exceptions.ControllerFailException;
import controllers.exceptions.ControllerWarnException;

import java.io.File;

/**
 * Created by eunderhi on 23/06/16.
 */
public class ExistingFileValidator implements Validator<String> {

    @Override
    public void validate(String pathName) throws ControllerFailException, ControllerWarnException {
        File file = new File(pathName);
        if (!file.exists()) {
            throw new ControllerFailException("This path does not exist");
        }
    }

}
