package controllers.progressbar;

import controllers.Controller;
import models.unpacking.Unpacker;
import views.ui.progressbar.ProgressBar;

/**
 * Created by eunderhi on 16/12/15.
 */
public class ProgressBarController implements Controller {

    private ProgressBar bar;
    private Unpacker unpacker;

    public ProgressBarController(ProgressBar bar) {
        this.bar = bar;
    }

    @Override
    public void performAction() {
        int percentDone = getPercentDone();
        if(bar.getPercentDone() != percentDone) {
            bar.setPercentDone(percentDone);
            bar.display();
        }
    }

    public int getPercentDone() {
        float fractionDone = (float) unpacker.getUnpackedAmount() / unpacker.getSize() * 100;
        return (int) fractionDone;
    }

    public void setUnpacker(Unpacker unpacker) {
        this.unpacker = unpacker;
    }
}
