package controllers.unpacker;

import controllers.Controller;

/**
 * Created by eunderhi on 14/12/15.
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
