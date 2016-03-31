package models.resources;

import models.packaging.InstallLocationModel;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Created by eunderhi on 30/03/16.
 * Class to add users to the EAP installation
 */
public class UserResource {

    private InstallLocationModel loc;

    public UserResource(InstallLocationModel loc) {
        this.loc = loc;
    }

    public void addUser(String userName, String password) {

        Path script = loc.getInstallLocation().resolve("bin")
                                              .resolve("add-user.sh");
        try {
            new ProcessBuilder(
                script.toString(),
                "-s",
                "-u", userName,
                "-p", password
            ).start();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
