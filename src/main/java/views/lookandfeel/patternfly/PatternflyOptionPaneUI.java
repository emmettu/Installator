package views.lookandfeel.patternfly;

import views.lookandfeel.ButtonFactory;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.*;
import java.util.Locale;

/**
 * Created by thauser on 4/14/15.
 */
public class PatternflyOptionPaneUI extends BasicOptionPaneUI {

    private static JButton okButton;
    private static JButton cancelButton;
    private static JButton yesButton;
    private static JButton noButton;

    public static ComponentUI createUI(JComponent c) {
        return new PatternflyOptionPaneUI();
    }

    @Override
    protected Object[] getButtons() {
        JButton[] defaultOptions = null;
        if (this.optionPane != null) {
            int type = this.optionPane.getOptionType();
            Locale l = this.optionPane.getLocale();
            if (type == 0) {
                defaultOptions = new JButton[2];
                defaultOptions[0] = getYesButton(l);
                defaultOptions[1] = getNoButton(l);
            } else if (type == 1) {
                defaultOptions = new JButton[3];
                defaultOptions[0] = getYesButton(l);
                defaultOptions[1] = getNoButton(l);
                defaultOptions[2] = getCancelButton(l);

            } else if (type == 2) {
                defaultOptions = new JButton[2];
                defaultOptions[0] = getOkButton(l);
                defaultOptions[1] = getCancelButton(l);
            } else {
                defaultOptions = new JButton[1];
                defaultOptions[0] = getOkButton(l);
            }
        }
        return defaultOptions;
    }

    private JButton getYesButton(Locale l) {
        if (yesButton == null) {
            yesButton = ButtonFactory.createButton(UIManager.getString("OptionPane.yesButtonText", l), this.getMnemonic("OptionPane.yesButtonMnemonic", l));
        }
        return yesButton;
    }

    private JButton getNoButton(Locale l) {
        if (noButton == null) {
            noButton = ButtonFactory.createButton(UIManager.getString("OptionPane.noButtonText", l), this.getMnemonic("OptionPane.noButtonMnemonic", l));
        }
        return noButton;
    }

    private JButton getCancelButton(Locale l) {
        if (cancelButton == null){
            cancelButton = ButtonFactory.createButton(UIManager.getString("OptionPane.cancelButtonText", l), this.getMnemonic("OptionPane.cancelButtonMnemonic", l));
        }
        return cancelButton;
    }

    private JButton getOkButton(Locale l) {
        if (okButton == null) {
            okButton = ButtonFactory.createButton(UIManager.getString("OptionPane.okButtonText", l), this.getMnemonic("OptionPane.okButtonMnemonic", l));
        }
        return okButton;
    }

    @Override
    protected void addButtonComponents(Container container, Object[] buttons, int initialIndex) {
        super.addButtonComponents(container, buttons, initialIndex);
        for (int index = 0; index < buttons.length; index++) {
            JButton aButton = (JButton) buttons[index];
            aButton.addActionListener(createButtonActionListener(index));
        }
    }

    private int getMnemonic(String key, Locale l) {
        String value = (String) UIManager.get(key, l);
        if (value == null) {
            return 0;
        } else {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException nfe) {
                return 0;
            }
        }
    }
}
