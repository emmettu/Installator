package views.lookandfeel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileFilter;
import java.awt.*;

/**
 * Created by thauser on 4/27/15.
 */
public class CustomDefaultListCellRenderer extends DefaultListCellRenderer {
    private Border BORDER_MOUSEOVER = BorderFactory.createMatteBorder(1, 0, 1, 0, UiResources.blueButtonStroke);

    public CustomDefaultListCellRenderer() {
        this.setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList jList, Object value, int i, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(jList, value, i, isSelected, cellHasFocus);
        if (value instanceof FileFilter) {
            setText(((FileFilter) value).getDescription());
        } else {
            setText(value.toString());
        }
        Color background;
        Color foreground = jList.getForeground();

        if (i == -1) {
            setOpaque(false);
        } else {
            setOpaque(true);
        }

        if (isSelected && i != -1) {
            background = UiResources.blueFocus;
            setBorder(BORDER_MOUSEOVER);
        } else {
            background = Color.WHITE;
            setBorder(BorderFactory.createEmptyBorder());
        }

        setBackground(background);
        setForeground(foreground);

        return this;
    }
}


