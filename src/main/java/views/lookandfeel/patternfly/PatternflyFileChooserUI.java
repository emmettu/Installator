package views.lookandfeel.patternfly;

import views.lookandfeel.ButtonFactory;
import views.lookandfeel.CustomDefaultListCellRenderer;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.metal.MetalFileChooserUI;
import java.awt.*;
import java.io.File;
import java.lang.reflect.Field;

/**
 * Created by thauser on 4/14/15.
 */
public class PatternflyFileChooserUI extends MetalFileChooserUI {
    JButton approveButton = null;
    JButton cancelButton = null;
    DirectoryComboBoxModel dcbm;

    public PatternflyFileChooserUI(JFileChooser jfc) {
        super(jfc);
    }

    public static ComponentUI createUI(JComponent c) {
        return new PatternflyFileChooserUI((JFileChooser) c);
    }

    @Override
    protected JButton getApproveButton(JFileChooser fc){
        return this.approveButton;
    }

    @Override
    public void installComponents(JFileChooser fc) {
        super.installComponents(fc);
        // over-come over privatization.
        createNewControlButtons(fc);
        Field jfc = getPrivateField("directoryComboBox");
        Field model = getPrivateField("directoryComboBoxModel");
        Field ffc = getPrivateField("filterComboBox");
        try {
            ((JComboBox)jfc.get(this)).setRenderer(new DirectoryCellRenderer());
            dcbm = (DirectoryComboBoxModel)model.get(this);
            ((JComboBox)ffc.get(this)).setUI(new PatternflyComboBoxUI());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    private Field getPrivateField(String fieldName) {
        Field returnValue = null;
        try {
            returnValue = MetalFileChooserUI.class.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        returnValue.setAccessible(true);
        return returnValue;
    }

    private void createNewControlButtons(JFileChooser fc) {
        removeControlButtons();
        getButtonPanel().removeAll();
        if (approveButton == null) {
            approveButton = ButtonFactory.createButton(getApproveButtonText(fc));
            approveButton.addActionListener(getApproveSelectionAction());
        }
        if (cancelButton == null){
            cancelButton = ButtonFactory.createButton(UIManager.getString("FileChooser.cancelButtonText", fc.getLocale()));
            cancelButton.addActionListener(getCancelSelectionAction());
        }
        getButtonPanel().add(cancelButton);
        getButtonPanel().add(approveButton);
        addControlButtons();
    }

    public String getApproveButtonText(JFileChooser fc){
        String buttonText = "OK";
        if (buttonText != null) {
            return buttonText;
        } else if (fc.getDialogType() == JFileChooser.OPEN_DIALOG) {
            return openButtonText;
        } else if (fc.getDialogType() == JFileChooser.SAVE_DIALOG) {
            return saveButtonText;
        } else {
            return null;
        }
    }

    protected class DirectoryCellRenderer extends CustomDefaultListCellRenderer {
        IndentIcon indentIcon = PatternflyFileChooserUI.this.new IndentIcon();

        DirectoryCellRenderer() {
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value == null) {
                this.setText("");
                return this;
            } else {
                File directory = (File) value;
                this.setText(PatternflyFileChooserUI.this.getFileChooser().getName(directory));
                Icon icon = PatternflyFileChooserUI.this.getFileChooser().getIcon(directory);
                this.indentIcon.icon = icon;
                this.indentIcon.depth = dcbm.getDepth(index);
                this.setIcon(this.indentIcon);
                return this;
            }
        }
    }

    /*
    essentially taken from the Metal LAF
     */
    class IndentIcon implements Icon {
        private Icon icon = null;
        private int depth = 0;

        IndentIcon() {
        }

        public void paintIcon(Component c, Graphics g, int x, int y) {
            if (c.getComponentOrientation().isLeftToRight()) {
                this.icon.paintIcon(c, g, x + this.depth * 10, y);
            } else {
                this.icon.paintIcon(c, g, x, y);
            }

        }

        public int getIconWidth() {
            return this.icon.getIconWidth() + this.depth * 10;
        }

        public int getIconHeight() {
            return this.icon.getIconHeight();
        }
    }
}
