import controllers.button.VisibilityController;
import controllers.combobox.ComboBoxController;
import controllers.installer.NextPanelController;
import controllers.installer.PreviousPanelController;
import controllers.progressbar.ProgressBarController;
import controllers.textinput.PathInputController;
import controllers.textinput.PathValidator;
import models.packaging.PackageSetDoneController;
import models.validation.FailValidationAction;
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
import views.ui.panels.ConsolePanel;
import views.ui.panels.LanguageSelectPanel;
import views.ui.panels.PathInputPanel;
import views.ui.panels.TargetPanel;
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
        standardPackage.addExclude("docs/contrib");
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
        }
        catch(Exception e) {
            e.printStackTrace();
        }
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

        views.ui.gui.GUITextInputField field = new views.ui.gui.GUITextInputField();
        field.validation().add(new PathValidator());
        field.validation().addHook(new FailValidationAction(field), Validation.Type.FAIL);
        setLookAndFeel();

        views.ui.gui.GUIButton button = new views.ui.gui.GUIButton("Install");
        PathInputController controller = new PathInputController(field);
        field.addController(controller);

        for(StandardPackage pack : packages.getPackages()) {
            controller.addPackage(pack);
        }
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

        firstPanel.addComponent(field);
        firstPanel.addComponent(button);

        String[] packageNames = {"Wildfly", "docs", "appclient", "bin", "domain", "modules", "standalone", "welcome-content"};

        for(int i = 0; i < packageNames.length; i++) {
            firstPanel.addComponent(bars.get(i));
        }

        packages.addController(new PackageSetDoneController(packages));

        frame.addPanel(new TargetPanel());
        frame.display();
    }

}
