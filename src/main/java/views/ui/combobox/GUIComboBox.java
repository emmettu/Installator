package views.ui.combobox;

import controllers.exceptions.ControllerFailException;
import controllers.exceptions.ControllerWarnException;
import views.lookandfeel.patternfly.PatternflyComboBoxUI;

import javax.swing.*;

/**
 * Created by eunderhi on 18/12/15.
 */
public class GUIComboBox extends ComboBox {

    private JComboBox<String> box;

    public GUIComboBox(String...args) {
        super(args);
        box = new JComboBox<>(selections);
        box.setUI(new PatternflyComboBoxUI());
        box.addActionListener((actionEvent) -> update());
    }

    @Override
    public void update() {
        int selectedIndex = box.getSelectedIndex();
        userSelection = selections[selectedIndex];
        notifyControllers();
    }

    @Override
    public void display() {}

    public JComboBox<String> getBox() {
        return box;
    }

    @Override
    protected void onValidationFail(ControllerFailException e) {

    }

    @Override
    protected void onValidationSuccess() {

    }

    @Override
    protected void onControllerFail(ControllerFailException e) {

    }

    @Override
    protected void onControllerWarn(ControllerWarnException e) {

    }
}
