package views.ui.combobox;

import views.ui.UIComponent;

/**
 * Created by eunderhi on 18/12/15.
 */
public abstract class ComboBox extends UIComponent {

    protected String[] selections;
    protected String userSelection;

    ComboBox(String...args) {
        selections = args;
    }

    public String getCurrentSelection() {
        return userSelection;
    }

    public String[] getSelections() {
        return selections;
    }

}
