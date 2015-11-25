package models;

import controllers.DataWriter;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eunderhi on 25/11/15.
 */
public class InstallData implements Model, DataWriter {

    public List<Panel> panels = new ArrayList<Panel>();

    public void notifyListeners() {}

    public void notifyListeners(Object message) {}

    public JSONObject writeDate() {
        return null;
    }
}
