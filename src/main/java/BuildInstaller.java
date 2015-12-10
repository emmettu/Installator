import controllers.textinput.PathInputController;
import controllers.unpacker.Unpacker;
import models.packaging.StandardPackage;
import views.ui.button.ConsoleButton;
import views.ui.button.GUIButton;
import views.ui.panels.GUIPanel;
import views.ui.textinput.ConsoleTextInputField;
import views.ui.textinput.GUITextInputField;

import javax.swing.*;

/**
 * Created by eunderhi on 25/11/15.
 */
public class BuildInstaller {

    public static void main(String args[]) {
        if(args.length >= 1 && args[0].equals("-console")) {
            buildConsole();
        }
        else {
            buildGUI();
        }
    }

    public static void buildConsole() {
        StandardPackage standardPackage = new StandardPackage("wildfly-10.0.0.CR4");
        standardPackage.addExclude("docs/contrib");
        Unpacker unpacker = new Unpacker();
        unpacker.setPackage(standardPackage);
        ConsoleTextInputField field = new ConsoleTextInputField();
        field.setPrompt("Enter the unpacking directory: ");
        ConsoleButton button = new ConsoleButton();
        button.setText("Press enter to start unpacking.");
        PathInputController controller = new PathInputController();
        controller.setTextInputField(field);
        controller.setPackage(standardPackage);
        button.addController(unpacker);

        field.update();
        button.update();
    }

    public static void buildGUI() {
        StandardPackage standardPackage = new StandardPackage("wildfly-10.0.0.CR4");
        standardPackage.addExclude("docs/contrib");
        Unpacker unpacker = new Unpacker();
        unpacker.setPackage(standardPackage);
        GUITextInputField field = new GUITextInputField();
        GUIButton button = new GUIButton();
        PathInputController controller = new PathInputController();
        controller.setTextInputField(field);
        controller.setPackage(standardPackage);
        button.addController(unpacker);
        JFrame frame = new JFrame("Installer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GUIPanel panel = new GUIPanel(frame);
        panel.setField(field);
        panel.setButton(button);
        panel.build();

        panel.display();
    }

}
