package views.ui.checkbox;

/**
 * Created by eunderhi on 08/01/16.
 */
public class ConsoleCheckBox extends CheckBox {

    public ConsoleCheckBox(String option) {
        super(option);
    }

    @Override
    public void update() {
        display();
        String userSelection = System.console().readLine();
        updateChecked(userSelection);
    }

    @Override
    public void display() {
        System.out.println(getOption());
        System.out.print("Enter 1 to select: ");
        System.out.print("[" + (isChecked() ? "X" : " ") + "] ");
    }

    public void updateChecked(String userSelection) {
        if("1".equals(userSelection)) {
            setChecked(true);
        }
    }

}
