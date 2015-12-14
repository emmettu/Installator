package views.ui.textstream;

import controllers.unpacker.Unpacker;

import javax.swing.*;
import java.awt.*;

/**
 * Created by eunderhi on 14/12/15.
 */
public class GUIUnpackerTextStream extends UnpackerTextStream {

    private JTextArea area = new JTextArea();
    JScrollPane scroller = new JScrollPane();

    public GUIUnpackerTextStream(Unpacker unpacker) {
        super(unpacker);
        area.setEditable(false);
        scroller.setViewportView(area);
        scroller.createVerticalScrollBar();
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroller.setPreferredSize(new Dimension(50, 30));
    }

    @Override
    public void update() {
        super.update();
        double percentDone = ((double) unpacker.getUnpackedAmount() / (double) unpacker.getSize()) * 100.0;
        area.append(String.format("%s %d\n", text, (int) Math.ceil(percentDone)));
        scroller.update(scroller.getGraphics());
    }

    @Override
    public void display() {
    }

    public JScrollPane getTextArea() {
        return scroller;
    }

}
