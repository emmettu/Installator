package views.ui.textstream;

import controllers.unpacker.Unpacker;

/**
 * Created by eunderhi on 14/12/15.
 */
public class ConsoleUnpackerTextStream extends UnpackerTextStream {

    public ConsoleUnpackerTextStream(Unpacker unpacker) {
        super(unpacker);
    }

    @Override
    public void display() {
        System.out.println("Unpacked: " + text);
    }

}
