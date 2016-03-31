package views.ui.console;

import views.ui.UIComponent;
import views.ui.gui.Combobox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eunderhi on 31/03/16.
 */

public class ConsoleCombobox<T> extends UIComponent implements Combobox<T> {

    private List<T> items = new ArrayList<>();
    private int selectedItem;
    private String prompt = "Enter the number of a selection: ";

    @SafeVarargs
    public ConsoleCombobox(T...items) {
        for (T item : items) {
            addItem(item);
        }
    }

    @Override
    public T getSelectedItem() {
        return items.get(selectedItem);
    }

    @Override
    public void addItem(T item) {
        items.add(item);
    }

    @Override
    public void update() {
        display();
    }

    @Override
    public void display() {
        Integer selection = null;
        while (selection == null) {
            printChoices();
            System.out.print(prompt);
            selection = getSelection();
        }
        selectedItem = selection;
        notifyControllers();
    }

    private Integer getSelection() {
        String input = System.console().readLine();
        int selection;
        try {
            selection = Integer.parseInt(input);
        }
        catch (NumberFormatException e) {
            System.out.println("Non-numeric input");
            return null;
        }
        if (selection > items.size() - 1 || selection < 0) {
            System.out.println("Input not within bounds");
            return null;
        }
        return selection;
    }

    private void printChoices() {
        for (int i = 0; i < items.size(); i++) {
            System.out.println(i + ". " + items.get(i));
        }
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

}
