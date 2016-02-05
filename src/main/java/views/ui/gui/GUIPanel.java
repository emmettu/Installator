package views.ui.gui;

import controllers.exceptions.ControllerFailException;

/**
 * Created by eunderhi on 29/01/16.
 */
public class GUIPanel extends GUIComponent {

    public void clear() {
        getJComponent().removeAll();
    }

    @Override
    protected void onControllerFail(ControllerFailException e) {

    }
}
