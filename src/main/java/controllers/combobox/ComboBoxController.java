package controllers.combobox;

import controllers.Controller;
import views.ui.combobox.ComboBox;

/**
 * Created by eunderhi on 18/12/15.
 */
public class ComboBoxController implements Controller {

    private ComboBox comboBox;

    @Override
    public void performAction() {
        System.out.println(comboBox.getCurrentSelection());
    }

    public void setComboBox(ComboBox comboBox) {
        this.comboBox = comboBox;
    }
}
