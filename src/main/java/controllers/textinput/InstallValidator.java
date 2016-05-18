package controllers.textinput;

import controllers.Validator;
import controllers.exceptions.ControllerFailException;
import controllers.exceptions.ControllerWarnException;

import java.nio.file.Paths;

/**
 * Created by eunderhi on 18/05/16.
 * Ensures the installation location is not
 * over top an existing one
 */
public class InstallValidator implements Validator<String> {

    private String installationName;

    public InstallValidator(String installationName) {
        this.installationName = installationName;
    }

    @Override
    public void validate(String s) throws ControllerFailException, ControllerWarnException {
        if (Paths.get(s).resolve(installationName).toFile().exists()) {
            throw new ControllerFailException("Cannot install on top of an existing EAP instance.");
        }
    }

}
