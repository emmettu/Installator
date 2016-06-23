import controllers.PanelController;
import controllers.QuitController;
import controllers.button.VisibilityController;
import controllers.combobox.ComboBoxController;
import controllers.installer.NextPanelController;
import controllers.installer.PreviousPanelController;
import controllers.models.SSLController;
import controllers.models.UserController;
import controllers.models.VaultController;
import controllers.progressbar.ProgressBarController;
import controllers.textinput.InstallValidator;
import controllers.textinput.PathInputController;
import controllers.textinput.DirectoryValidator;
import models.jobs.InstallerJob;
import models.jobs.JobExecutor;
import models.packaging.InstallLocationModel;
import models.packaging.PackageSetDoneController;
import models.panels.*;
import models.panels.logging.LoggingModel;
import models.panels.logging.LoggingResource;
import models.panels.securitydomain.ports.StandalonePorts;
import models.resources.*;
import models.resources.exceptions.CommandFailedException;
import models.resources.exceptions.InfinispanResource;
import models.resources.exceptions.LDAPResource;
import models.resources.servers.ServerBuilder;
import models.resources.servers.ServerResource;
import models.validation.Validation;
import models.packaging.utils.PackageSet;
import models.unpacking.Unpacker;
import controllers.unpacker.UnpackerController;
import models.packaging.StandardPackage;
import org.infinispan.configuration.cache.TransactionMode;
import org.infinispan.eviction.EvictionStrategy;
import views.lookandfeel.ButtonFactory;
import views.lookandfeel.FontResources;
import views.lookandfeel.UiResources;
import views.lookandfeel.patternfly.PatternflyButtonUI;
import views.lookandfeel.patternfly.PatternflyFileChooserUI;
import views.lookandfeel.patternfly.PatternflyOptionPaneUI;
import views.lookandfeel.patternfly.PatternflyScrollBarUI;
import views.ui.InstallerFrame;
import views.ui.button.ConsoleButton;
import views.ui.button.GUIButton;
import views.ui.combobox.ConsoleComboBox;
import views.ui.combobox.GUIComboBox;
import views.ui.gui.*;
import views.ui.gui.GUIPanel;
import views.ui.gui.panels.SSLPanel;
import views.ui.gui.panels.UserCreationPanel;
import views.ui.gui.panels.VaultPanel;
import views.ui.panels.*;
import views.ui.progressbar.ConsoleProgressBar;
import views.ui.progressbar.GUIProgressBar;
import views.ui.textinput.ConsoleTextInputField;
import views.ui.textinput.GUITextInputField;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.nio.file.Paths;
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
        textInputField.validation().add(new DirectoryValidator());
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
        DirectoryValidator pv = new DirectoryValidator();
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

        GUIButton nextButton = new GUIButton();
        nextButton.addController(npc);

        GUIButton previousButton = new GUIButton();

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
            if (value != null && value instanceof javax.swing.plaf.FontUIResource)
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

        for (views.ui.gui.GUIProgressBar bar : bars) {
            firstPanel.addComponent(bar);
        }

        packages.addController(new PackageSetDoneController(packages));

        TargetPanel targetPanel = new TargetPanel("Path Selection Panel", "Select the path for your wildfly installation");
        views.ui.gui.GUIButton nextButton = targetPanel.getButtonPanel().getNext();
        nextButton.setEnabled(false);
        UnpackPanel unpackPanel = new UnpackPanel(packages, packageNames);
        PathInputController pic = new PathInputController(targetPanel.getField());
        views.ui.gui.FancyGUITextField pathField = targetPanel.getField();
        pathField.validation().addHook(e -> nextButton.setEnabled(false), Validation.Type.FAIL);
        pathField.validation().addHook(e -> nextButton.setEnabled(true), Validation.Type.SUCCESS);
        pathField.validation().addHook((e) -> {
            nextButton.setEnabled(false);
            nextButton.setEnabled(true);
        }, Validation.Type.CONDITIONAL_SUCCESS);

        targetPanel.getField().addController(pic);
        pic.addPackageSet(packages);

        PanelController nextPanel = new PanelController(frame);
        views.ui.gui.GUIButton targetNextButton = targetPanel.getButtonPanel().getNext();
        for (UnpackerController uc : packages.getUnpackerControllers()) {
            targetNextButton.addController(uc);
        }
        InstallLocationModel ilm = new InstallLocationModel();
        pathField.validation().add(new InstallValidator(ilm.getName()));
        pathField.validate();
        targetPanel.getButtonPanel().getNext().addController(nextPanel);
        targetPanel.getButtonPanel().getPrev().setEnabled(false);
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

        VaultPanel vaultPanel = new VaultPanel("Vault Panel", "Enter the information for password vault installation.");
        VaultModel vaultModel = new VaultModel();
        VaultController vc = new VaultController(vaultPanel, vaultModel);
        vaultPanel.getButtonPanel().getNext().addController(new PanelController(frame));
        vaultPanel.getAlias().addController(vc);
        vaultPanel.getSalt().addController(vc);
        vaultPanel.getIterationCount().addController(vc);
        vaultPanel.getEncryptedFileDirectory().getField().addController(vc);
        vaultPanel.getKeystorePassword().addController(vc);
        vaultPanel.getKeyStoreLocation().getField().addController(vc);

        UserCreationPanel userCreationPanel = new UserCreationPanel("User Creation", "Enter the username and password of the admin user");
        UserModel userModel = new UserModel();
        UserController uc = new UserController(userCreationPanel, userModel);
        userCreationPanel.getUserField().addController(uc);
        userCreationPanel.getPasswordField().addController(uc);
        userCreationPanel.getButtonPanel().getNext().addController(new PanelController(frame));

        SSLPanel sslPanel = new SSLPanel("SSL", "Enable SSL security for management interfaces");
        SSLModel sslModel = new SSLModel();
        SSLController sc = new SSLController(sslPanel, sslModel);
        sslPanel.getKeystoreLocation().addController(sc);
        sslPanel.getSSLPassword().addController(sc);
        sslPanel.getButtonPanel().getNext().addController(new PanelController(frame));

        unpackPanel.getButtonPanel().getNext().addController(() -> createServer(ilm, vaultModel, userModel, sslModel));
        frame.addPanel(targetPanel);
        frame.addPanel(vaultPanel);
        frame.addPanel(sslPanel);
        frame.addPanel(userCreationPanel);
        frame.addPanel(unpackPanel);
        frame.display();
    }

    private static void createServer(InstallLocationModel ilm, VaultModel vaultModel, UserModel userModel, SSLModel sslModel) {
        JobExecutor executor = new JobExecutor();
        executor.addJob(new InstallerJob("userCreation") {
            @Override
            protected void runJob() {
                new UserResource(ilm).addUser(userModel);
            }
        });
        ServerBuilder builder = new ServerBuilder(ilm);
        ServerResource standalone = builder.newStandaloneServer("standalone.xml");
        ServerResource domain = builder.newHostServer("host.xml");
        ServerResource standaloneFullHa = builder.newStandaloneServer("standalone-full-ha.xml");
        VaultResource vault = new VaultResource(standalone);
        InstallerJob makeKeyStore = new InstallerJob("create keystore") {
            @Override
            protected void runJob() {
                new KeyStore().make(vaultModel);
            }
        };
        InstallerJob addVault = new InstallerJob("add vault") {
            @Override
            protected void runJob() {
                vault.makeVault(vaultModel);
                //hostVault.makeVault(vaultModel);
            }
        };

        SSLResource sslRes = new SSLResource(standalone);
//        SSLModel sslMod = new SSLModel();
//        sslMod.setVault(vaultModel);
//        sslMod.setKeyStoreLocation(Paths.get("/home/eunderhi/vault/vault.keystore"));
//        sslMod.setPassword("qwer`123");
//        sslMod.setRealm("ManagementRealm");

        InstallerJob addSSL = new InstallerJob("add ssl") {
            @Override
            protected void runJob() {
                try {
                    sslRes.installSSL(sslModel);
                    sslRes.addHttps();
                } catch (CommandFailedException e) {
                    e.printStackTrace();
                }
            }
        };

        LDAPResource ldapRes = new LDAPResource(standalone);
        LDAPResource ldapHost = new LDAPResource(domain);
        LdapModel ldap = new LdapModel();
        ldap.setName("test");
        ldap.setPassword("test");
        ldap.setUrl("test");
        ldap.setdN("test");
        ldap.setVaultModel(vaultModel);
        ldap.setRecursive(true);
        ldap.setFilterType(LdapModel.FilterType.ADVANCED);
        ldap.setFilter("test");
        ldap.setBaseDN("test");
        ldap.setRealmName("ldap");
        ldap.setSSL(sslModel);

        InstallerJob addLDAP = new InstallerJob("add ldap") {
            @Override
            protected void runJob() {
                try {
                    ldapRes.installLdap(ldap);
                    //ldapHost.installLdap(ldap);
                }
                catch (CommandFailedException e) {
                    e.printStackTrace();
                }
            }
        };

        InfinispanResource infiniRes = new InfinispanResource(standalone);
        InfinispanModel infiniMod = new InfinispanModel();
        infiniMod.setContainer("test");
        infiniMod.setEvictionStrategy(EvictionStrategy.LIRS);
        infiniMod.setJndiName("test");
        infiniMod.setMaxEntries(1000);
        infiniMod.setMaxIdle(500);
        infiniMod.setTransactionMode(TransactionMode.FULL_XA);
        infiniMod.setLocalCache("test");

        InstallerJob addInfinispan = new InstallerJob("infinispan") {
            @Override
            protected void runJob() {
                try {
                    infiniRes.installInfinispan(infiniMod);
                }
                catch (CommandFailedException e) {
                    setState(State.FAILED);
                    e.printStackTrace();
                }
            }
        };

        StandalonePorts ports = new StandalonePorts(standalone);
        InstallerJob writePorts = new InstallerJob("ports") {
            @Override
            protected void runJob() {
                try {
                    ports.writePorts();
                }
                catch (CommandFailedException e) {
                    setState(State.FAILED);
                    e.printStackTrace();
                }
            }
        };

        LoggingModel model = new LoggingModel();
        LoggingResource loggingResource = new LoggingResource(standalone);
        LoggingResource sfhLoggingResource = new LoggingResource(standaloneFullHa);
        InstallerJob loggingLevel = new InstallerJob("logging") {
            @Override
            protected void runJob() {
                try {
                    loggingResource.installLogging(model);
                    sfhLoggingResource.installLogging(model);
                }
                catch (CommandFailedException e) {
                    setState(State.FAILED);
                    e.printStackTrace();
                }
            }
        };

        InstallerJob shutDown = new InstallerJob("bye bye") {
            @Override
            protected void runJob() {
                System.out.println("Installation complete, shutting down");
                executor.shutDown();
                standalone.shutDown();
                standaloneFullHa.shutDown();
                domain.shutDown();
            }
        };

        addVault.addDependency(makeKeyStore);
        addSSL.addDependency(addVault);
        addLDAP.addDependency(addSSL);
        shutDown.addDependency(addSSL);
        shutDown.addDependency(addLDAP);
        shutDown.addDependency(addInfinispan);
        shutDown.addDependency(writePorts);
        shutDown.addDependency(loggingLevel);
        executor.addJob(makeKeyStore);
        executor.addJob(addVault);
        executor.addJob(addSSL);
        executor.addJob(addLDAP);
        executor.addJob(addInfinispan);
        executor.addJob(shutDown);
        executor.addJob(writePorts);
        executor.addJob(loggingLevel);
        executor.go();
    }

}
