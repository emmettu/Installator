package controllers.installer;

/**
 * Created by eunderhi on 16/12/15.
 */
public class PreviousPanelController extends InstallerController {

    @Override
    public void performAction() {
        installer.previous();
        installer.update();
    }

}
