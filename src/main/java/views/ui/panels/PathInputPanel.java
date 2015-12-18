package views.ui.panels;

import javax.swing.*;
import java.awt.*;

/**
 * Created by eunderhi on 11/12/15.
 */
public class PathInputPanel extends GUIPanel {

    private JTextField field;
    private JButton button;
    private JScrollPane stream;
    private JButton nextButton;
    private JButton previousButton;
    private JProgressBar bar;

    @Override
    public void build() {
        button.setText("Install");
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(field, BorderLayout.NORTH);

        JPanel progressPanel = new JPanel();
        progressPanel.setLayout(new BoxLayout(progressPanel, BoxLayout.PAGE_AXIS));
        progressPanel.add(bar);
        progressPanel.add(stream);

        frame.getContentPane().add(progressPanel, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(previousButton);
        buttonsPanel.add(button);
        buttonsPanel.add(nextButton);

        frame.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);

        frame.setSize(900, 600);
    }

    public void setField(JTextField field) {
        this.field = field;
    }

    public void setButton(JButton button) {
        this.button = button;
    }

    public void setTextStream(JScrollPane stream) {
        this.stream = stream;
    }

    public void setNextButton(JButton button) {
        this.nextButton = button;
    }

    public void setPreviousButton(JButton button) {
        this.previousButton = button;
    }

    public void setBar(JProgressBar bar) {
        this.bar = bar;
    }

}
