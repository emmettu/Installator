package views.ui.panels;


import javax.swing.*;
import java.awt.*;

/**
 * Created by eunderhi on 11/12/15.
 */
public class PathInputPanel extends GUIPanel {

    private JPanel field;
    private JButton button;
    private JScrollPane stream;
    private JPanel progressPanel = new JPanel();

    @Override
    public void build(JPanel contentPanel) {
        contentPanel.removeAll();
        contentPanel.setLayout(new BorderLayout());
        button.setText("Install");
        contentPanel.add(field, BorderLayout.NORTH);

        progressPanel.setLayout(new BoxLayout(progressPanel, BoxLayout.PAGE_AXIS));

        contentPanel.add(progressPanel, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(button);

        contentPanel.add(buttonsPanel, BorderLayout.SOUTH);
    }

    public void setField(JPanel field) {
        this.field = field;
    }

    public void setButton(JButton button) {
        this.button = button;
    }

    public void setTextStream(JScrollPane stream) {
        this.stream = stream;
    }

    public void addBar(JProgressBar bar, String barName) {
        JPanel barAndLabel = new JPanel(new GridLayout());
        JLabel label = new JLabel(barName);
        label.setPreferredSize(new Dimension(1, 1));
        barAndLabel.add(label);
        barAndLabel.add(bar);
        progressPanel.add(barAndLabel);
    }

}
