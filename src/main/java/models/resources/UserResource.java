package models.resources;

import models.packaging.InstallLocationModel;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by eunderhi on 30/03/16.
 * Class to add users to the EAP installation
 */
public class UserResource {

    private static final Logger LOGGER = Logger.getLogger(UserResource.class.getName());
    private InstallLocationModel loc;

    public UserResource(InstallLocationModel loc) {
        try {
            LOGGER.addHandler(new FileHandler(loc.getInstallLocation().resolve("log.txt").toString()));
            LOGGER.setLevel(Level.ALL);
        }
        catch (IOException ignored) {}
        this.loc = loc;
    }

    public void addUser(String userName, String password) {
        Path script = loc.getInstallLocation().resolve("bin")
                                              .resolve("add-user.sh");
        ProcessBuilder pb = new ProcessBuilder(
                script.toString(),
                "-s",
                "-u", userName,
                "-p", password
        );
        pb.redirectErrorStream(true);
        try {
            Process p = pb.start();
            p.waitFor();
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
