package controllers.installer;

import controllers.Controller;
import views.ui.Installer;

/**
 * Created by eunderhi on 15/12/15.
 */
public class NextPanelController implements Controller {

    private Installer installer;

    @Override
    public void performAction() {
        installer.next();
        installer.update();
    }

    public void setInstaller(Installer installer) {
        this.installer = installer;
    }

}
