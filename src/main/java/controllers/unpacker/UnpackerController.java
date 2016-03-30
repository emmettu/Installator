package controllers.unpacker;

import controllers.Controller;
import models.unpacking.Unpacker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by eunderhi on 14/12/15.
 * Controller to trigger the unpacking action. This is done in a new thread
 * to prevent the unpacking action from blocking the GUI.
 */
public class UnpackerController implements Controller {

    private Unpacker unpacker;
    private ExecutorService runner;

    public UnpackerController(Unpacker unpacker) {
        this.unpacker = unpacker;
    }

    @Override
    public void performAction() {
        if(runner == null) {
            unpacker.unpack();
        }
        else {
            runner.submit((Runnable) () -> unpacker.unpack());
        }
    }

    public void multiThread() {
        runner = Executors.newSingleThreadExecutor();
    }

}
