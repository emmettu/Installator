import controllers.PanelController;
import controllers.QuitController;
import controllers.button.VisibilityController;
import controllers.combobox.ComboBoxController;
import controllers.installer.NextPanelController;
import controllers.installer.PreviousPanelController;
import controllers.progressbar.ProgressBarController;
import controllers.textinput.PathInputController;
import controllers.textinput.PathValidator;
import models.packaging.InstallLocationModel;
import models.packaging.PackageSetDoneController;
import models.resources.UserResource;
import models.validation.Validation;
import models.packaging.utils.PackageSet;
import models.unpacking.Unpacker;
import controllers.unpacker.UnpackerController;
import models.packaging.StandardPackage;
import views.lookandfeel.ButtonFactory;
import views.lookandfeel.FontResources;
import views.lookandfeel.UiResources;
import views.lookandfeel.buttonstyles.ButtonEmporium;
import views.lookandfeel.patternfly.PatternflyButtonUI;
import views.lookandfeel.patternfly.PatternflyFileChooserUI;
import views.lookandfeel.patternfly.PatternflyOptionPaneUI;
import views.lookandfeel.patternfly.PatternflyScrollBarUI;
import views.ui.InstallerFrame;
import views.ui.button.ConsoleButton;
import views.ui.button.GUIButton;
import views.ui.combobox.ConsoleComboBox;
import views.ui.combobox.GUIComboBox;
import views.ui.gui.GUICombobox;
import views.ui.gui.GUIFrame;
import views.ui.gui.GUIPanel;
import views.ui.panels.*;
import views.ui.progressbar.ConsoleProgressBar;
import views.ui.progressbar.GUIProgressBar;
import views.ui.textinput.ConsoleTextInputField;
import views.ui.textinput.GUITextInputField;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eunderhi on 25/11/15.
 */
public class BuildInstaller {

    public static void main(String args[]) {
        if (args.length >= 1 && args[0].equals("-console")) {
            buildConsole();
        }
        else {
            //buildGUI();
            testGUIRefactor();
        }
    }

    public static void buildConsole() {
        StandardPackage standardPackage = new StandardPackage("wildfly-10.0.0.CR4");
        Unpacker unpacker = new Unpacker(standardPackage);
        UnpackerController unpackerController = new UnpackerController(unpacker);
        ConsoleTextInputField field = new ConsoleTextInputField();
        field.setPrompt("Enter the unpacking directory: ");
        ConsoleButton button = new ConsoleButton();
        button.setText("Press enter to start unpacking.");
        PathInputController controller = new PathInputController(new views.ui.gui.GUITextInputField());
        //controller.setTextInputField(field);
        controller.addPackage(standardPackage);
        button.addController(unpackerController);
        ConsolePanel panel = new ConsolePanel();

        ConsoleProgressBar bar = new ConsoleProgressBar();
        bar.setPrompt("Unpacking: ");
        bar.setLength(60);
        bar.setEndPrompt(" You did it. You unpacked the package.");

        ConsoleComboBox combo = new ConsoleComboBox("yeah", "sure", "maybe");
        ComboBoxController cbc = new ComboBoxController();
        cbc.setComboBox(combo);
        combo.addController(cbc);

        panel.addComponent(field);
        panel.addComponent(button);
        panel.addComponent(combo);

        views.ui.console.ConsoleTextInputField textInputField = new views.ui.console.ConsoleTextInputField();
        textInputField.validation().add(new PathValidator());
        textInputField.validation().addHook((e) -> System.out.println("WARN: " + e.getMessage()), Validation.Type.WARN);
        textInputField.validation().addHook((e) -> System.out.println("FAIL: " + e.getMessage()), Validation.Type.FAIL);
        textInputField.display();
        panel.display();

    }

    public static void buildGUI() {
        //SwingUtilities.invokeLater(BuildInstaller::setLookAndFeel);
        setLookAndFeel();

        PackageSet packages = new PackageSet();
        packages.setRootDirectory("wildfly-10.0.0.CR4/");
        packages.add("")
                    .exclude("docs")
                    .exclude("appclient")
                    .exclude("bin")
                    .exclude("domain")
                    .exclude("modules")
                    .exclude("standalone")
                    .exclude("welcome-content")
                .add("docs")
                .add("appclient")
                .add("bin")
                .add("domain")
                .add("modules")
                .add("standalone")
                .add("welcome-content");
        packages.addMultiThreadedUnpackers();

        GUITextInputField field = new GUITextInputField();
        PathValidator pv = new PathValidator();
        //pv.setField(field);
        field.addValidator(pv);
        GUIButton button = new GUIButton();
        PathInputController controller = new PathInputController(new views.ui.gui.GUITextInputField());
        //controller.setTextInputField(field);

        VisibilityController ebc = new VisibilityController(new GUICombobox<>("test", "test"), true);
        field.addController(ebc);

        for(StandardPackage pack : packages.getPackages()) {
            controller.addPackage(pack);
        }
        for(UnpackerController uc : packages.getUnpackerControllers()) {
            button.addController(uc);
        }
        PathInputPanel panel = new PathInputPanel();

        InstallerFrame installer = new InstallerFrame();

        List<GUIProgressBar> bars = new ArrayList<>();

        NextPanelController npc = new NextPanelController();
        npc.setInstaller(installer);

        PreviousPanelController ppc = new PreviousPanelController();
        ppc.setInstaller(installer);

        GUIButton nextButton = new GUIButton(ButtonEmporium.nextButton("Next"));
        nextButton.addController(npc);

        GUIButton previousButton = new GUIButton(ButtonEmporium.previousButton("Previous"));

        previousButton.addController(ppc);

        panel.setField(field.getTextField());
        panel.setButton(button.getButton());
        String[] packageNames = {"Wildfly", "docs", "appclient", "bin", "domain", "modules", "standalone", "welcome-content"};

        for(int i = 0; i < packageNames.length; i++) {
            panel.addBar(bars.get(i).getBar(), packageNames[i]);
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
            ButtonFactory.useHighlightButtons(true);
            ButtonFactory.useButtonIcons();
            ButtonFactory.useButtonIcons(false);
            UIManager.put("Panel.background", Color.WHITE);
            UIManager.put("OptionPane.background", Color.WHITE);
            UIManager.put("PasswordField.inactiveBackground", UiResources.inactiveTextField);
            UIManager.put("TextArea.inactiveBackground", UiResources.inactiveTextField);
            UIManager.put("TextField.inactiveBackground", UiResources.inactiveTextField);
            UIManager.put("ScrollBarUI", PatternflyScrollBarUI.class.getName());
            UIManager.put("Button.rollover", true);
            UIManager.put("ButtonUI", PatternflyButtonUI.class.getName());
            UIManager.put("FileChooserUI", PatternflyFileChooserUI.class.getName());
            UIManager.put("OptionPaneUI", PatternflyOptionPaneUI.class.getName());
            UIManager.put("Button.foreground", new ColorUIResource(Color.RED));
            setUIFont(new FontUIResource(FontResources.getOpenSansRegular()));
            FontResources.getFontAwesome();
            loadIcons();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadIcons() {
        ImageIcon icon =  new ImageIcon(BuildInstaller.class.getResource("/img/folder_4d5258_18.png"));
        UIManager.put("FileView.directoryIcon", icon);
    }

    public static void setUIFont(javax.swing.plaf.FontUIResource f) {
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value != null && value instanceof javax.swing.plaf.FontUIResource && !key.equals("PasswordField.font"))
                UIManager.put(key, f);
        }
    }

    public static void testGUIRefactor() {
        PackageSet packages = new PackageSet();
        packages.setRootDirectory("wildfly-10.0.0.CR4/");
        packages.add("")
                    .exclude("docs")
                    .exclude("appclient")
                    .exclude("bin")
                    .exclude("domain")
                    .exclude("modules")
                    .exclude("standalone")
                    .exclude("welcome-content")
                .add("docs")
                .add("appclient")
                .add("bin")
                .add("domain")
                .add("modules")
                .add("standalone")
                .add("welcome-content");
        packages.addMultiThreadedUnpackers();
        packages.calculateSize();
        setLookAndFeel();

        views.ui.gui.GUIButton button = new views.ui.gui.GUIButton("Install");

        for(UnpackerController uc : packages.getUnpackerControllers()) {
            button.addController(uc);
        }

        List<views.ui.gui.GUIProgressBar> bars = new ArrayList<>();

        for(Unpacker unpacker : packages.getUnpackers()) {
            views.ui.gui.GUIProgressBar bar = new views.ui.gui.GUIProgressBar();
            ProgressBarController pbc = new ProgressBarController(bar);
            pbc.setUnpacker(unpacker);
            unpacker.addController(pbc);
            bars.add(bar);
        }

        GUIFrame frame = new GUIFrame();
        GUIPanel firstPanel = new GUIPanel();

        String[] packageNames = {"Wildfly", "docs", "appclient", "bin", "domain", "modules", "standalone", "welcome-content"};

        for(views.ui.gui.GUIProgressBar bar : bars) {
            firstPanel.addComponent(bar);
        }

        packages.addController(new PackageSetDoneController(packages));

        TargetPanel targetPanel = new TargetPanel();
        views.ui.gui.GUIButton nextButton = targetPanel.getButtonPanel().getNext();
        nextButton.setEnabled(false);
        UnpackPanel unpackPanel = new UnpackPanel(packages, packageNames);
        PathInputController pic = new PathInputController(targetPanel.getField());
        views.ui.gui.FancyGUITextField pathField = targetPanel.getField();
        pathField.validation().addHook(e -> nextButton.setEnabled(false), Validation.Type.FAIL);
        pathField.validation().addHook(e -> nextButton.setEnabled(true), Validation.Type.SUCCESS);
        pathField.validation().addHook((e) ->{
            nextButton.setEnabled(false);
            nextButton.setEnabled(true);
        }, Validation.Type.CONDITIONAL_SUCCESS);

        pathField.validate();
        targetPanel.getField().addController(pic);
        pic.addPackageSet(packages);

        PanelController nextPanel = new PanelController(frame);
        views.ui.gui.GUIButton targetNextButton = targetPanel.getButtonPanel().getNext();
        for (UnpackerController uc : packages.getUnpackerControllers()) {
            targetNextButton.addController(uc);
        }
        InstallLocationModel ilm = new InstallLocationModel();
        targetPanel.getButtonPanel().getNext().addController(nextPanel);
        views.ui.gui.FancyGUITextField field = targetPanel.getField();
        field.addController(() -> ilm.setInstallLocation(field.getText()));

        PanelController prevPanel = new PanelController(frame);
        prevPanel.setReverse();
        unpackPanel.getButtonPanel().getPrev().addController(prevPanel);
        unpackPanel.getButtonPanel().getQuit().addController(new QuitController(frame));
        packages.addController(() -> {
            if (packages.getSize() == packages.getUnpackedAmount()) {
                unpackPanel.getButtonPanel().getNext().setEnabled(true);
            }
        });

        unpackPanel.getButtonPanel().getNext().addController(() -> new UserResource(ilm).addUser("admin", "qwer#1234"));
        frame.addPanel(targetPanel);
        //frame.addPanel(new UserCreationPanel());
        frame.addPanel(unpackPanel);
        frame.display();
    }

}
