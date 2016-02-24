package models.packaging;

import controllers.Controller;
import models.packaging.utils.PackageSet;

/**
 * Created by eunderhi on 24/02/16.
 */
public class PackageSetDoneController implements Controller {

    private PackageSet packageSet;

    public PackageSetDoneController(PackageSet set) {
        packageSet = set;
    }

    @Override
    public void performAction() {
        if (packageSet.getSize() == packageSet.getUnpackedAmount()) {
            System.out.println("All packages unpacked");
        }
    }

}
