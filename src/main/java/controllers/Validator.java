package controllers;

import controllers.exceptions.ControllerFailException;
import controllers.exceptions.ControllerWarnException;

/**
 * Created by eunderhi on 27/11/15.
 */
public interface Validator<Data> {

    void validate(Data data) throws ControllerFailException, ControllerWarnException;

}
