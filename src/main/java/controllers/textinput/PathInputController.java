package controllers.textinput;

import models.packaging.Package;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eunderhi on 07/12/15.
 */
public class PathInputController extends TextInputController {

    private List<Package> packages = new ArrayList<>();

    @Override
    public void performAction() {
        String path = getTextInputField().getText();
        for(Package thePackage: packages) {
            thePackage.setUnpackDirectory(path);
        }
    }

    public void addPackage(Package thePackage) {
        packages.add(thePackage);
    }

}
