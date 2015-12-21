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
    private JProgressBar bar;

    @Override
    public void build(JPanel contentPanel) {
        contentPanel.removeAll();
        button.setText("Install");
        contentPanel.add(field, BorderLayout.NORTH);

        JPanel progressPanel = new JPanel();
        progressPanel.setLayout(new BoxLayout(progressPanel, BoxLayout.PAGE_AXIS));
        progressPanel.add(bar);
        progressPanel.add(stream);

        contentPanel.add(progressPanel, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(button);

        contentPanel.add(buttonsPanel, BorderLayout.SOUTH);
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

    public void setBar(JProgressBar bar) {
        this.bar = bar;
    }

}
