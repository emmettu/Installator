package views;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eunderhi on 07/12/15.
 */
public abstract class Panel implements View {

    private List<View> viewList = new ArrayList<>();

    @Override
    public void update() {

    }

    @Override
    public void display() {
        displayPanel();
        displayComponents();
    }

    protected abstract void displayPanel();

    private void displayComponents() {
        for(View view : viewList) {
            view.display();
        }
    }

    public void addView(View view) {
        viewList.add(view);
    }

}
