package models;

import views.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eunderhi on 04/12/15.
 */
public abstract class InstallerModel implements Model {
    private List<View> views = new ArrayList<>();

    @Override
    public void notifyListeners() {
        for(View view : views) {
            view.update();
        }
    }

    public List<View> getViews() {
        return views;
    }

    public void addView(View view) {
        views.add(view);

    }
}
