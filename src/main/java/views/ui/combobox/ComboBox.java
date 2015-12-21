package views.ui.combobox;

import views.ui.UserInputView;

/**
 * Created by eunderhi on 18/12/15.
 */
public abstract class ComboBox extends UserInputView {

    protected String[] selections;
    protected String userSelection;

    ComboBox(String...args) {
        selections = args;
    }

    public String getCurrentSelection() {
        return userSelection;
    }

}
