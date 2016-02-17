package views.ui.gui;

import views.lookandfeel.patternfly.PatternflyComboBoxUI;

import javax.swing.*;

/**
 * Created by eunderhi on 12/02/16.
 */
public class GUICombobox<T> extends GUIComponent implements Combobox<T> {

    private JComboBox<T> box = new JComboBox<>();

    public GUICombobox(T...items) {
        for (T item : items) {
            addItem(item);
        }
        setJComponent(box);
        box.setUI(new PatternflyComboBoxUI());
    }

    @Override
    public T getSelectedItem() {
        return box.getItemAt(box.getSelectedIndex());
    }

    @Override
    public void addItem(T item) {
        box.addItem(item);
    }

}
