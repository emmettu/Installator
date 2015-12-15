package views.ui.panels;

import views.ui.button.GUIButton;
import views.ui.textinput.GUITextInputField;
import views.ui.textstream.GUITextStream;

import javax.swing.*;
import java.awt.*;

/**
 * Created by eunderhi on 11/12/15.
 */
public class PathInputPanel extends GUIPanel {

    private GUITextInputField field;
    private GUIButton button;
    private GUITextStream stream;
    private GUIButton nextButton;

    public PathInputPanel(JFrame frame) {
        super(frame);
    }

    @Override
    public void build() {
        button.setText("Install");
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(field.getTextField(), BorderLayout.NORTH);
        frame.getContentPane().add(stream.getTextArea(), BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(button.getButton());
        buttonsPanel.add(nextButton.getButton());

        frame.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);

        frame.setSize(900, 600);
    }

    public void setField(GUITextInputField field) {
        this.field = field;
    }

    public void setButton(GUIButton button) {
        this.button = button;
    }

    public void setTextStream(GUITextStream stream) {
        this.stream = stream;
    }

    public void setNextButton(GUIButton button) {
        this.nextButton = button;
    }

}
