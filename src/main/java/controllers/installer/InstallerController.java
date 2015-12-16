package controllers.installer;

import controllers.Controller;
import views.ui.Installer;

/**
 * Created by eunderhi on 16/12/15.
 */
public abstract class InstallerController implements Controller {

    protected Installer installer;

    public void setInstaller(Installer installer) {
        this.installer = installer;
    }
}
