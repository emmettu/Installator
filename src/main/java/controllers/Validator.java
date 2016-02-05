package controllers;

import controllers.exceptions.ControllerFailException;

/**
 * Created by eunderhi on 27/11/15.
 */
public abstract class Validator {

    public abstract void validate() throws ControllerFailException;

}
