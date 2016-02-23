package models.packaging.utils;

import controllers.unpacker.PackageSetController;
import controllers.unpacker.UnpackerController;
import models.InstallerModel;
import models.packaging.StandardPackage;
import models.unpacking.Unpacker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by eunderhi on 08/01/16.
 * A utility class that makes operating on a large amount of packages easier
 */
public class PackageSet extends InstallerModel {

    private List<StandardPackage> packages = new ArrayList<>();
    private List<Unpacker> unpackers = new ArrayList<>();
    private List<UnpackerController> controllers = new ArrayList<>();
    private String rootDirectory;
    private AtomicLong unpackedAmount = new AtomicLong();
    private long size;

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

    public List<UnpackerController> getUnpackerControllers() {
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

    public void calculateSize() {
        for (StandardPackage p : packages) {
            size += p.getSize();
        }
    }

    private void addUnpackers(boolean multiThread) {
        for(StandardPackage sp : packages) {
            Unpacker unpacker = new Unpacker(sp);
            unpackers.add(unpacker);
            UnpackerController uc = new UnpackerController(unpacker);
            unpacker.addController(new PackageSetController(this, unpacker));

            if(multiThread) {
                uc.multiThread();
            }
            controllers.add(uc);
        }
    }

    public void addSize(long amount) {
        unpackedAmount.addAndGet(amount);
        notifyListeners();
    }

}
