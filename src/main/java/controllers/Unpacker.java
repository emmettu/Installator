package controllers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eunderhi on 25/11/15.
 */
public abstract class Unpacker implements Controller {

    List<Package> packages = new ArrayList<Package>();

    @Override
    public void performAction() {
    }
}
