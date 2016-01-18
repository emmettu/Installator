package controllers.installer;

import controllers.Controller;
import views.ui.InstallerFrame;

/**
 * Created by eunderhi on 16/12/15.
 */
public abstract class InstallerController implements Controller {

    protected InstallerFrame installer;

    public void setInstaller(InstallerFrame installer) {
        this.installer = installer;
    }
}
