import controllers.installer.NextPanelController;
import controllers.installer.PreviousPanelController;
import controllers.progressbar.ProgressBarController;
import controllers.textinput.PathInputController;
import controllers.textstream.UnpackerDisplayController;
import models.unpacking.Unpacker;
import controllers.unpacker.UnpackerController;
import models.packaging.StandardPackage;
import views.ui.Installer;
import views.ui.button.ConsoleButton;
import views.ui.button.GUIButton;
import views.ui.panels.ConsolePanel;
import views.ui.panels.LanguageSelectPanel;
import views.ui.panels.PathInputPanel;
import views.ui.progressbar.ConsoleProgressBar;
import views.ui.progressbar.GUIProgressBar;
import views.ui.textinput.ConsoleTextInputField;
import views.ui.textinput.GUITextInputField;
import views.ui.textstream.GUITextStream;

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
        Unpacker unpacker = new Unpacker(standardPackage);
        UnpackerController unpackerController = new UnpackerController(unpacker);
        ConsoleTextInputField field = new ConsoleTextInputField();
        field.setPrompt("Enter the unpacking directory: ");
        ConsoleButton button = new ConsoleButton();
        button.setText("Press enter to start unpacking.");
        PathInputController controller = new PathInputController();
        controller.setTextInputField(field);
        controller.setPackage(standardPackage);
        button.addController(unpackerController);
        ConsolePanel panel = new ConsolePanel();

        ConsoleProgressBar bar = new ConsoleProgressBar();
        ProgressBarController pbc = new ProgressBarController(bar);
        bar.setPrompt("Unpacking: ");
        bar.setLength(60);
        bar.setEndPrompt(" You did it. You unpacked the package.");
        unpacker.addController(pbc);
        pbc.setUnpacker(unpacker);

        panel.addComponent(field);
        panel.addComponent(button);

        panel.display();

    }

    public static void buildGUI() {
        StandardPackage standardPackage = new StandardPackage("wildfly-10.0.0.CR4");
        standardPackage.addExclude("docs/contrib");
        Unpacker unpacker = new Unpacker(standardPackage);
        UnpackerController unpackerController = new UnpackerController(unpacker);
        unpacker.setPackage(standardPackage);
        GUITextInputField field = new GUITextInputField();
        GUIButton button = new GUIButton();
        PathInputController controller = new PathInputController();
        controller.setTextInputField(field);
        controller.setPackage(standardPackage);
        button.addController(unpackerController);
        PathInputPanel panel = new PathInputPanel();
        GUITextStream unpackerStream = new GUITextStream();
        UnpackerDisplayController udc = new UnpackerDisplayController();
        udc.setTextStream(unpackerStream);
        udc.setUnpacker(unpacker);
        unpacker.addController(udc);

        Installer installer = new Installer();

        GUIProgressBar bar = new GUIProgressBar();
        ProgressBarController pbc = new ProgressBarController(bar);
        pbc.setUnpacker(unpacker);
        unpacker.addController(pbc);

        NextPanelController npc = new NextPanelController();
        npc.setInstaller(installer);

        PreviousPanelController ppc = new PreviousPanelController();
        ppc.setInstaller(installer);

        GUIButton nextButton = new GUIButton();
        nextButton.setText("Next");
        nextButton.addController(npc);

        GUIButton previousButton = new GUIButton();
        previousButton.setText("Previous");

        previousButton.addController(ppc);

        panel.setField(field);
        panel.setButton(button);
        panel.setTextStream(unpackerStream);
        panel.setNextButton(nextButton);
        panel.setPreviousButton(previousButton);
        panel.setBar(bar);

        //installer.addPanel(new LanguageSelectPanel());
        installer.addPanel(panel);
        installer.display();
    }

}
