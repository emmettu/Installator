package controllers.textinput;

import models.packaging.Package;
import models.packaging.StandardPackage;
import models.packaging.utils.PackageSet;
import views.ui.gui.TextInputField;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eunderhi on 07/12/15.
 * Controller that sets the unpack directory of a package from a
 * textInputField
 */

public class PathInputController extends TextInputController {

    private List<Package> packages = new ArrayList<>();

    public PathInputController(TextInputField field) {
        super(field);
    }

    @Override
    public void performAction() {
        String path = getTextInputField().getText();
        for(Package p : packages) {
            p.setUnpackDirectory(path);
        }
    }

    public void addPackageSet(PackageSet packages) {
        for (StandardPackage pack : packages.getPackages()) {
            addPackage(pack);
        }
    }

    public void addPackage(Package thePackage) {
        packages.add(thePackage);
        thePackage.setUnpackDirectory(getTextInputField().getText());
    }

}
