package views.ui.console;

import models.validation.FieldValidation;
import views.ui.UIComponent;
import views.ui.gui.TextInputField;
import views.ui.gui.Validated;

/**
 * Created by eunderhi on 31/03/16.
 */
public class ConsoleTextInputField extends UIComponent implements TextInputField, Validated {

    private FieldValidation validation;
    private String text;
    private String prompt = "";

    public ConsoleTextInputField() {
        validation = new FieldValidation(this);
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void update() {

    }

    @Override
    public void display() {
        do {
            System.out.print(prompt);
            String input = System.console().readLine();
            setText(input);
        } while (!validation.validate());
    }

    @Override
    public boolean validate() {
        return validation.validate();
    }

    @Override
    public FieldValidation validation() {
        return validation;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

}
