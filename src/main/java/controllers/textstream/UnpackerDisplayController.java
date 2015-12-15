package controllers.textstream;

import controllers.Controller;
import models.unpacking.Unpacker;
import views.ui.textstream.TextStream;

import java.nio.file.Path;

/**
 * Created by eunderhi on 15/12/15.
 * this controller updates a text stream so it streams the latest
 * files unpacked from the unpacker
 */
public class UnpackerDisplayController implements Controller {

    private Unpacker unpacker;
    private TextStream textStream;

    @Override
    public void performAction() {
        Path path = unpacker.getUnpackedFiles().peek();
        textStream.setText(path.toString());
        textStream.update();
    }

    public void setUnpacker(Unpacker unpacker) {
        this.unpacker = unpacker;
    }

    public void setTextStream(TextStream textStream) {
        this.textStream = textStream;
    }

}
