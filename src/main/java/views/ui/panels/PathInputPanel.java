package views.ui.panels;

import views.ui.button.GUIButton;
import views.ui.textinput.GUITextInputField;
import views.ui.textstream.GUIUnpackerTextStream;

import javax.swing.*;
import java.awt.*;

/**
 * Created by eunderhi on 11/12/15.
 */
public class PathInputPanel extends GUIPanel {

    private GUITextInputField field;
    private GUIButton button;
    private GUIUnpackerTextStream stream;

    public PathInputPanel(JFrame frame) {
        super(frame);
    }

    @Override
    public void build() {
        button.setText("Install");
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(field.getTextField(), BorderLayout.NORTH);
        frame.getContentPane().add(button.getButton(), BorderLayout.SOUTH);
        frame.getContentPane().add(stream.getTextArea(), BorderLayout.CENTER);
        frame.setSize(900, 600);
    }

    public void setField(GUITextInputField field) {
        this.field = field;
    }

    public void setButton(GUIButton button) {
        this.button = button;
    }

    public void setUnpackStream(GUIUnpackerTextStream stream) {
        this.stream = stream;
    }

}
