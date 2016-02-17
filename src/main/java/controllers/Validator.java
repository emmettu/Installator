package controllers;

import controllers.exceptions.ControllerFailException;
import controllers.exceptions.ControllerWarnException;

/**
 * Created by eunderhi on 27/11/15.
 */
public abstract class Validator<Data> {

    public abstract void validate(Data data) throws ControllerFailException, ControllerWarnException;

}
