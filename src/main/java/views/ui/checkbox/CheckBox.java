package views.ui.checkbox;

import views.ui.UserInputView;

/**
 * Created by eunderhi on 08/01/16.
 */
public abstract class CheckBox extends UserInputView {

    private boolean checked;
    private String option;

    public CheckBox(String option) {
        this.option = option;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

}
