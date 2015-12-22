import controllers.combobox.ComboBoxController;
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
        StandardPackage mainPack = new StandardPackage("wildfly-10.0.0.CR4");
        mainPack.addExclude("docs");
        mainPack.addExclude("appclient");
        mainPack.addExclude("bin");
        mainPack.addExclude("domain");
        mainPack.addExclude("modules");
        mainPack.addExclude("standalone");
        mainPack.addExclude("welcome-content");
        StandardPackage docs = new StandardPackage("wildfly-10.0.0.CR4/docs");
        StandardPackage appclient = new StandardPackage("wildfly-10.0.0.CR4/appclient");
        StandardPackage bin = new StandardPackage("wildfly-10.0.0.CR4/bin");
        StandardPackage domain = new StandardPackage("wildfly-10.0.0.CR4/domain");
        StandardPackage modules = new StandardPackage("wildfly-10.0.0.CR4/modules");
        StandardPackage standalone = new StandardPackage("wildfly-10.0.0.CR4/standalone");
        StandardPackage welcome = new StandardPackage("wildfly-10.0.0.CR4/welcome-content");
        Unpacker mainUnpacker = new Unpacker(mainPack);
        Unpacker docsUnpacker = new Unpacker(docs);
        Unpacker appclientUnpacker = new Unpacker(appclient);
        Unpacker binUnpacker = new Unpacker(bin);
        Unpacker domainUnpacker = new Unpacker(domain);
        Unpacker modulesUnpacker = new Unpacker(modules);
        Unpacker standaloneUnpacker = new Unpacker(standalone);
        Unpacker welcomeUnpacker = new Unpacker(welcome);
        UnpackerController mainController = new UnpackerController(mainUnpacker);
        UnpackerController docsController = new UnpackerController(docsUnpacker);
        UnpackerController appclientController = new UnpackerController(appclientUnpacker);
        UnpackerController binController = new UnpackerController(binUnpacker);
        UnpackerController domainController = new UnpackerController(domainUnpacker);
        UnpackerController modulesController = new UnpackerController(modulesUnpacker);
        UnpackerController standaloneController = new UnpackerController(standaloneUnpacker);
        UnpackerController welcomeController = new UnpackerController(welcomeUnpacker);
        mainController.multiThread();
        docsController.multiThread();
        appclientController.multiThread();
        binController.multiThread();
        domainController.multiThread();
        modulesController.multiThread();
        standaloneController.multiThread();
        welcomeController.multiThread();
        GUITextInputField field = new GUITextInputField();
        GUIButton button = new GUIButton();
        PathInputController controller = new PathInputController();
        controller.setTextInputField(field);
        controller.addPackage(mainPack);
        controller.addPackage(docs);
        controller.addPackage(appclient);
        controller.addPackage(bin);
        controller.addPackage(domain);
        controller.addPackage(modules);
        controller.addPackage(standalone);
        controller.addPackage(welcome);
        button.addController(mainController);
        button.addController(docsController);
        button.addController(appclientController);
        button.addController(binController);
        button.addController(modulesController);
        button.addController(standaloneController);
        button.addController(welcomeController);
        button.addController(domainController);
        PathInputPanel panel = new PathInputPanel();
        GUITextStream unpackerStream = new GUITextStream();
        UnpackerDisplayController udc = new UnpackerDisplayController();
        udc.setTextStream(unpackerStream);
        udc.setUnpacker(mainUnpacker);
        mainUnpacker.addController(udc);

        Installer installer = new Installer();

        GUIProgressBar bar = new GUIProgressBar();
        ProgressBarController pbc = new ProgressBarController(bar);
        pbc.setUnpacker(mainUnpacker);
        mainUnpacker.addController(pbc);

        GUIProgressBar docBar = new GUIProgressBar();
        ProgressBarController docBarController = new ProgressBarController(docBar);
        docBarController.setUnpacker(docsUnpacker);
        docsUnpacker.addController(docBarController);

        GUIProgressBar appBar = new GUIProgressBar();
        ProgressBarController appBarController = new ProgressBarController(appBar);
        appBarController.setUnpacker(appclientUnpacker);
        appclientUnpacker.addController(appBarController);

        GUIProgressBar binBar = new GUIProgressBar();
        ProgressBarController binBarController = new ProgressBarController(binBar);
        binBarController.setUnpacker(binUnpacker);
        binUnpacker.addController(binBarController);

        GUIProgressBar domainBar = new GUIProgressBar();
        ProgressBarController domainBarController = new ProgressBarController(domainBar);
        domainBarController.setUnpacker(domainUnpacker);
        domainUnpacker.addController(domainBarController);

        GUIProgressBar moduleBar = new GUIProgressBar();
        ProgressBarController modulesBarController = new ProgressBarController(moduleBar);
        modulesBarController.setUnpacker(modulesUnpacker);
        modulesUnpacker.addController(modulesBarController);

        GUIProgressBar standaloneBar = new GUIProgressBar();
        ProgressBarController standaloneBarController = new ProgressBarController(standaloneBar);
        standaloneBarController.setUnpacker(standaloneUnpacker);
        standaloneUnpacker.addController(standaloneBarController);

        GUIProgressBar welcomeBar = new GUIProgressBar();
        ProgressBarController welcomeBarController = new ProgressBarController(welcomeBar);
        welcomeBarController.setUnpacker(welcomeUnpacker);
        welcomeUnpacker.addController(welcomeBarController);

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
        panel.setTextStream(unpackerStream.getTextArea());
        panel.addBar(bar.getBar());
        panel.addBar(docBar.getBar());
        panel.addBar(appBar.getBar());
        panel.addBar(binBar.getBar());
        panel.addBar(domainBar.getBar());
        panel.addBar(moduleBar.getBar());
        panel.addBar(standaloneBar.getBar());
        panel.addBar(welcomeBar.getBar());

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

}
