package models;

import controllers.DataWriter;
import org.json.JSONObject;

/**
 * Created by eunderhi on 25/11/15. PanelData encapsulates models into a
 * coherent panel structure in the same way that Panel encapsulates views.
 */

public abstract class PanelData implements Model, DataWriter {

    public abstract void writeData();
    public abstract void loadData(JSONObject json);

}
