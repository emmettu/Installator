package models.panels;

import models.packaging.StandardPackage;
import models.unpacking.Unpacker;
import org.json.JSONObject;

/**
 * Created by eunderhi on 15/12/15.
 */
public class PathInputPanelData extends PanelData {

    private StandardPackage packageToUnpack;
    private Unpacker unpacker;

    @Override
    public JSONObject writeData() {
        return null;
    }

    @Override
    public void loadData(JSONObject json) {

    }

    public StandardPackage getPackageToUnpack() {
        return packageToUnpack;
    }

    public void setPackageToUnpack(StandardPackage packageToUnpack) {
        this.packageToUnpack = packageToUnpack;
    }

    public Unpacker getUnpacker() {
        return unpacker;
    }

    public void setUnpacker(Unpacker unpacker) {
        this.unpacker = unpacker;
    }

}
