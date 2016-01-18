package views.lookandfeel.patternfly;


import views.lookandfeel.CustomDefaultListCellRenderer;
import views.lookandfeel.UiResources;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.metal.MetalComboBoxUI;
import java.awt.*;

/**
 * Created by thauser on 4/21/15.
 */
public class PatternflyComboBoxUI extends MetalComboBoxUI {

    public PatternflyComboBoxUI() {

    }

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        ((JComboBox) c).setRenderer(new CustomDefaultListCellRenderer());
    }

    @Override
    protected JButton createArrowButton() {
        JButton button = new ComboBoxCustomButton(this.comboBox, new ImageIcon(this.getClass().getResource("/img/chevron-down_000000_12.png")), this.currentValuePane, this.listBox);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setHorizontalTextPosition(SwingConstants.LEFT);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(true);
        return button;
    }

    @Override
    public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
        Graphics2D g2d = (Graphics2D) g;
        Paint paint;

        if (this.comboBox.isEnabled()) {
            paint = UiResources.getGradientPaint(bounds, UiResources.grayButtonLight, UiResources.grayButtonDark);
        } else {
            paint = UiResources.getGradientPaint(bounds, UiResources.grayDisabledStroke, UiResources.grayDisabledStroke);
        }
        g2d.setPaint(paint);
        g2d.fill(bounds);
    }

    public static ComponentUI createUI(JComponent c) {
        return new PatternflyComboBoxUI();
    }

     public class ComboBoxCustomButton extends JButton {
        private final JList listBox;
        private final CellRendererPane rendererPane;
        private JComboBox comboBox;
        private Icon arrowIcon;

        public ComboBoxCustomButton(JComboBox comboBox, Icon arrowIcon, CellRendererPane rendererPane, JList dropDown) {
            super();
            this.arrowIcon = arrowIcon;
            this.setIcon(arrowIcon);
            this.listBox = dropDown;
            this.rendererPane = rendererPane;
            this.comboBox = comboBox;
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            String selectedText;
            Object selected = comboBox.getSelectedItem();
            if (selected instanceof FileFilter) {
                FileFilter item = (FileFilter) selected;
                selectedText = item.getDescription();
            } else {
                selectedText = selected.toString();
            }
            FontMetrics fm = getFontMetrics(getFont());
            int stringWidth = fm.stringWidth(selectedText);
            int iconWidth = getIcon().getIconWidth();
            int iconTextGap = comboBox.getWidth() - stringWidth - iconWidth - getMargin().left - getMargin().right + 25;
            setIconTextGap(iconTextGap);
            setText(selectedText);
        }
    }

}
