package controllers.textinput;

import models.packaging.Package;

/**
 * Created by eunderhi on 07/12/15.
 */
public class PathInputController extends TextInputController {

    private Package thePackage;

    @Override
    public void performAction() {
        String path = getTextInputField().getText();
        thePackage.setUnpackDirectory(path);
    }

    public void setPackage(Package thePackage) {
        this.thePackage = thePackage;
    }

}
