package models.resources;

import models.panels.VaultModel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eunderhi on 11/04/16.
 * Class to generate Keystores
 */
public class KeyStore {

    public void make(VaultModel model) {
        makeDirectories(model.getStoreLocation());
        List<String> keyToolCommand = generateCommand(model);
        submitCommand(keyToolCommand);
    }

    private void makeDirectories(Path storeLocation) {
        Path parent = storeLocation.getParent();
        File parentFile = parent.toFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
    }

    private List<String> generateCommand(VaultModel model) {
        List<String> command = new ArrayList<>();
        command.add("keytool");
        command.add("-genseckey");
        command.add("-keystore");
        command.add(model.getStoreLocation().toString());
        command.add("-storepass");
        command.add(model.getPassword());
        command.add("-keypass");
        command.add(model.getPassword());
        command.add("-alias");
        command.add(model.getAlias());
        command.add("-keyalg");
        command.add("AES");
        command.add("-keysize");
        command.add("128");
        command.add("-storetype");
        command.add("jceks");
        command.add("-validity");
        command.add("36500");
        return command;
    }

    private void submitCommand(List<String> command) {
        ProcessBuilder pb = new ProcessBuilder().command(command);
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
