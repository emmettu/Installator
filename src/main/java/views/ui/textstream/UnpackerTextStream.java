package views.ui.textstream;

import controllers.unpacker.Unpacker;

import java.nio.file.Path;

/**
 * Created by eunderhi on 14/12/15.
 */
public abstract class UnpackerTextStream extends TextStream {

    protected Unpacker unpacker;

    public UnpackerTextStream(Unpacker unpacker) {
        this.unpacker = unpacker;
        unpacker.addView(this);
    }

    @Override
    public void update() {
        Path latestFile = unpacker.getUnpackedFiles().peek();
        updateText(latestFile.toString());
        display();
    }

}
