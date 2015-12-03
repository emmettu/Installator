package controllers;

import views.UserInputView;

/**
 * Created by eunderhi on 27/11/15.
 */
public abstract class Validator implements Controller {

    private UserInputView userInputView;

    @Override
    public void performAction() {
        validate();
    }

    protected abstract void validate();

    public void setUserInputView(UserInputView userInputView) {
        this.userInputView = userInputView;
    }

}
