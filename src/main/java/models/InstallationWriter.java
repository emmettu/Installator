package models;

import controllers.DataWriter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eunderhi on 25/11/15.
 */
public class InstallationWriter implements DataWriter {

    public List<PanelData> panels = new ArrayList<>();

    public void notifyListeners(Object message) {}

    public JSONObject writeDate() {
        JSONArray installData = new JSONArray();
        for(PanelData panel : panels) {
            JSONObject data = panel.writeDate();
            if(data != null) {
                installData.put(data);
            }
        }
        return new JSONObject(installData);
    }
}
