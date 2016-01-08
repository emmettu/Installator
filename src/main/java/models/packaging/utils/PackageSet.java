package models.packaging.utils;

import controllers.unpacker.UnpackerController;
import models.packaging.StandardPackage;
import models.unpacking.Unpacker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eunderhi on 08/01/16.
 * A utility class that makes operating on a large amount of packages easier
 */
public class PackageSet {

    private List<StandardPackage> packages = new ArrayList<>();
    private List<Unpacker> unpackers = new ArrayList<>();
    private List<UnpackerController> controllers = new ArrayList<>();
    private String rootDirectory;


    public PackageSet add(String packageName) {
        packages.add(new StandardPackage(rootDirectory + packageName));
        return this;
    }

    public PackageSet exclude(String excludeName) {
        packages.get(packages.size() - 1).addExclude(excludeName);
        return this;
    }

    public void setRootDirectory(String rootDirectory) {
        this.rootDirectory = rootDirectory;
    }

    public List<StandardPackage> getPackages() {
        return packages;
    }

    public List<UnpackerController> getControllers() {
        return controllers;
    }

    public List<Unpacker> getUnpackers() {
        return unpackers;
    }

    public void addUnpackers() {
        addUnpackers(false);
    }

    public void addMultiThreadedUnpackers() {
        addUnpackers(true);
    }

    private void addUnpackers(boolean multiThread) {
        for(StandardPackage sp : packages) {
            Unpacker unpacker = new Unpacker(sp);
            unpackers.add(unpacker);
            UnpackerController uc = new UnpackerController(unpacker);

            if(multiThread) {
                uc.multiThread();
            }
            controllers.add(uc);
        }
    }

}
