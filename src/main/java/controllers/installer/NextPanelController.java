package controllers.installer;

/**
 * Created by eunderhi on 15/12/15.
 */
public class NextPanelController extends InstallerController {

    @Override
    public void performAction() {
        installer.next();
        installer.update();
    }

}
