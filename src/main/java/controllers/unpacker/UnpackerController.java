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

    /**
     * Tell the unpacker to start unpacking. Do this in a blocking fashion if runner is null
     * (i.e. multiThread() has not been called to initialize the ExecutorService). Otherwise
     * submit a new unpacking runnable to the executor so the action is performed in a new
     * thread. This allows multiple independent packages to be unpacked simultaneously.
     */
    @Override
    public void performAction() {
        if(runner == null) {
            unpacker.unpack();
        }
        else {
            runner.submit((Runnable) () -> unpacker.unpack());
        }
    }

    /**
     * Initialize the executor service so that future unpacking jobs are non-blocking.
     */
    public void multiThread() {
        runner = Executors.newSingleThreadExecutor();
    }

}
