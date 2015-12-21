package views.ui.combobox;

import javax.swing.*;

/**
 * Created by eunderhi on 18/12/15.
 */
public class GUIComboBox extends ComboBox {

    private JComboBox<String> box;

    public GUIComboBox(String...args) {
        super(args);
        box = new JComboBox<>(selections);
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

}
