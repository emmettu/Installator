package views.ui.gui;

import views.View;

/**
 * Created by eunderhi on 26/01/16.
 */
public abstract class Frame<T extends View> implements View {

    abstract void addPanel(T panel);
    abstract void removePanel(T panel);
    abstract void nextPanel();
    abstract void previousPanel();
}
