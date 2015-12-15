package controllers.unpacker;

import controllers.Controller;
import models.unpacking.Unpacker;

/**
 * Created by eunderhi on 14/12/15.
 * controller to trigger the unpacking action
 */
public class UnpackerController implements Controller {

    private Unpacker unpacker;

    public UnpackerController(Unpacker unpacker) {
        this.unpacker = unpacker;
    }

    @Override
    public void performAction() {
        unpacker.unpack();
    }
}
