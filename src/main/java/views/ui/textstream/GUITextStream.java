package views.ui.textstream;

import javax.swing.*;
import java.awt.*;

/**
 * Created by eunderhi on 15/12/15.
 */
public class GUITextStream extends TextStream {

    private JTextArea area = new JTextArea();
    private JScrollPane scroller = new JScrollPane();

    public GUITextStream() {
        area.setEditable(false);
        scroller.setViewportView(area);
        scroller.createVerticalScrollBar();
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroller.setPreferredSize(new Dimension(50, 30));
    }

    @Override
    public void update() {
        area.append(text + "\n");
        display();
    }

    @Override
    public void display() {
        JScrollBar bar = scroller.getVerticalScrollBar();
        bar.setValue(bar.getMaximum());
    }

    public JScrollPane getTextArea() {
        return scroller;
    }

}
