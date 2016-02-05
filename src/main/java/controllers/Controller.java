package controllers;

import controllers.exceptions.ControllerFailException;
import controllers.exceptions.ControllerWarnException;

/**
 * Created by eunderhi on 25/11/15.
 */
public interface Controller {
    void performAction() throws ControllerFailException, ControllerWarnException;
}
