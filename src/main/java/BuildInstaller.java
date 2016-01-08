import controllers.combobox.ComboBoxController;
import controllers.installer.NextPanelController;
import controllers.installer.PreviousPanelController;
import controllers.progressbar.ProgressBarController;
import controllers.textinput.PathInputController;
import controllers.textstream.UnpackerDisplayController;
import models.packaging.utils.PackageSet;
import models.unpacking.Unpacker;
import controllers.unpacker.UnpackerController;
import models.packaging.StandardPackage;
import views.ui.Installer;
import views.ui.button.ConsoleButton;
import views.ui.button.GUIButton;
import views.ui.combobox.ConsoleComboBox;
import views.ui.combobox.GUIComboBox;
import views.ui.panels.ConsolePanel;
import views.ui.panels.LanguageSelectPanel;
import views.ui.panels.PathInputPanel;
import views.ui.progressbar.ConsoleProgressBar;
import views.ui.progressbar.GUIProgressBar;
import views.ui.textinput.ConsoleTextInputField;
import views.ui.textinput.GUITextInputField;
import views.ui.textstream.GUITextStream;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

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
        controller.addPackage(standardPackage);
        button.addController(unpackerController);
        ConsolePanel panel = new ConsolePanel();

        ConsoleProgressBar bar = new ConsoleProgressBar();
        ProgressBarController pbc = new ProgressBarController(bar);
        bar.setPrompt("Unpacking: ");
        bar.setLength(60);
        bar.setEndPrompt(" You did it. You unpacked the package.");
        unpacker.addController(pbc);
        pbc.setUnpacker(unpacker);

        ConsoleComboBox combo = new ConsoleComboBox("yeah", "sure", "maybe");
        ComboBoxController cbc = new ComboBoxController();
        cbc.setComboBox(combo);
        combo.addController(cbc);

        panel.addComponent(field);
        panel.addComponent(button);
        panel.addComponent(combo);

        panel.display();

    }

    public static void buildGUI() {
        SwingUtilities.invokeLater(BuildInstaller::setLookAndFeel);
        PackageSet packages = new PackageSet();
        packages.setRootDirectory("wildfly-10.0.0.CR4/");
        packages.add("")
                    .exclude("docs").exclude("appclient").exclude("bin").exclude("domain")
                    .exclude("modules").exclude("standalone").exclude("welcome-content")
                .add("docs").add("appclient").add("bin").add("domain")
                .add("modules").add("standalone").add("welcome-content");
        packages.addMultiThreadedUnpackers();


        GUITextInputField field = new GUITextInputField();
        GUIButton button = new GUIButton();
        PathInputController controller = new PathInputController();
        controller.setTextInputField(field);
        for(StandardPackage pack : packages.getPackages()) {
            controller.addPackage(pack);
        }
        for(UnpackerController uc : packages.getControllers()) {
            button.addController(uc);
        }
        PathInputPanel panel = new PathInputPanel();

        Installer installer = new Installer();

        List<GUIProgressBar> bars = new ArrayList<>();

        for(Unpacker unpacker : packages.getUnpackers()) {
            GUIProgressBar bar = new GUIProgressBar();
            ProgressBarController pbc = new ProgressBarController(bar);
            pbc.setUnpacker(unpacker);
            unpacker.addController(pbc);
            bars.add(bar);
        }

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

        panel.setField(field.getTextField());
        panel.setButton(button.getButton());
        for(GUIProgressBar bar : bars) {
            panel.addBar(bar.getBar());
        }

        installer.addNavButton(previousButton.getButton());
        installer.addNavButton(nextButton.getButton());

        GUIComboBox box = new GUIComboBox("English", "French", "Japanese", "Spanish");
        ComboBoxController cbc = new ComboBoxController();
        cbc.setComboBox(box);
        box.addController(cbc);
        LanguageSelectPanel langPanel = new LanguageSelectPanel();
        langPanel.setComboBox(box.getBox());
        installer.addPanel(langPanel);
        installer.addPanel(panel);
        installer.display();
    }


    private static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
