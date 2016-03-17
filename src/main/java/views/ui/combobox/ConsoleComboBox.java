package views.ui.combobox;

import controllers.exceptions.ControllerFailException;
import controllers.exceptions.ControllerWarnException;

/**
 * Created by eunderhi on 18/12/15.
 */
public class ConsoleComboBox extends ComboBox {

    public ConsoleComboBox(String... args) {
        super(args);
    }

    @Override
    public void update() {
        display();
        userSelection = System.console().readLine();
        validateUserSelection(userSelection);
    }

    @Override
    public void display() {
        for(int i = 0; i < selections.length; i++) {
            System.out.println(i + 1 + ": " + selections[i]);
        }
    }

    private void validateUserSelection(String selection) {
        try {
            int selectionIndex = Integer.parseInt(selection);
            userSelection = selections[selectionIndex - 1];
            notifyControllers();
        }
        catch(NumberFormatException e) {
            System.out.println("Please enter a numeric value");
            update();
        }
        catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Choose a value from within the selection range.");
            update();
        }
    }

    @Override
    protected void onValidationFail(ControllerFailException e) {

    }

}
