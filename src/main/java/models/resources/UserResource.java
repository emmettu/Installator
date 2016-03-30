package models.resources;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by eunderhi on 30/03/16.
 * Class to add users to the EAP installation
 */
public class UserResource {

    String installPath  = "";
    String name = "";

    public void addUser(String userName, String password) {
        Path script = Paths.get(installPath, name, "bin", "add-user.sh");
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
