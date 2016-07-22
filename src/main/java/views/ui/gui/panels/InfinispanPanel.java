package views.ui.gui.panels;

import org.infinispan.configuration.cache.TransactionMode;
import org.infinispan.eviction.EvictionStrategy;
import views.ui.gui.*;
import views.ui.gui.layout.Constraints;

import java.awt.*;

/**
 * Created by eunderhi on 27/06/16.
 */
public class InfinispanPanel extends InstallerPanel {

    private FancyGUITextField name;
    private FancyGUITextField jndi;
    private FancyGUITextField localCache;
    private GUICombobox<TransactionMode> transactionMode;
    private GUICombobox<EvictionStrategy> evictionStrategy;
    private FancyGUITextField maxEntries;
    private FancyGUITextField maxIdle;
    private static final int INDENT = 200;
    private static final int BOTTOM_SPACE = 200;

    public InfinispanPanel(String title, String description) {
        super(title, description);
    }

    @Override
    protected void build(GUIPanel contentPanel) {
        initializeComponents();
        int row = 2;
        contentPanel.addComponent(new GUILabel("Infinispan Name"), Constraints.createFullLineElementConstraint(row, 0, 0));
        contentPanel.addComponent(name, Constraints.createAlignedElementConstraint(row, 0, INDENT, GridBagConstraints.NONE));
        row++;
        contentPanel.addComponent(new GUILabel("JNDI Name"), Constraints.createFullLineElementConstraint(row, 0, 0));
        contentPanel.addComponent(jndi, Constraints.createAlignedElementConstraint(row, 0, INDENT, GridBagConstraints.NONE));
        row++;
        contentPanel.addComponent(new GUILabel("Local Cache Name"), Constraints.createFullLineElementConstraint(row, 0, 0));
        contentPanel.addComponent(localCache, Constraints.createAlignedElementConstraint(row, 0, INDENT, GridBagConstraints.NONE));
        row++;
        contentPanel.addComponent(new GUILabel("Transaction Mode"), Constraints.createFullLineElementConstraint(row, 0, 0));
        contentPanel.addComponent(transactionMode, Constraints.createFixedSizeAlignedElementConstraint(row, 0, INDENT, GridBagConstraints.NONE));
        row++;
        contentPanel.addComponent(new GUILabel("Eviction Strategy"), Constraints.createFullLineElementConstraint(row, 0, 0));
        contentPanel.addComponent(evictionStrategy, Constraints.createFixedSizeAlignedElementConstraint(row, 0, INDENT, GridBagConstraints.NONE));
        row++;
        contentPanel.addComponent(new GUILabel("Eviction Max Entries"), Constraints.createFullLineElementConstraint(row, 0, 0));
        contentPanel.addComponent(maxEntries, Constraints.createAlignedElementConstraint(row, 0, INDENT, GridBagConstraints.NONE));
        row++;
        contentPanel.addComponent(new GUILabel("Expiration Max Idle"), Constraints.createFullLineElementConstraint(row, 0, 0));
        contentPanel.addComponent(maxIdle, Constraints.createAlignedElementConstraint(row, 0, INDENT, GridBagConstraints.NONE));
        row++;
        contentPanel.addComponent(new GUIPanel(), Constraints.bottomElement(row, INDENT, BOTTOM_SPACE));
    }

    private void initializeComponents() {
        name = new FancyGUITextField();
        jndi = new FancyGUITextField();
        localCache = new FancyGUITextField();
        transactionMode = new GUICombobox<>(TransactionMode.values());
        transactionMode.setWidth(195);
        evictionStrategy = new GUICombobox<>(EvictionStrategy.values());
        evictionStrategy.setWidth(195);
        maxEntries = new FancyGUITextField();
        maxIdle = new FancyGUITextField();
        name.setColumns(16);
        jndi.setColumns(16);
        localCache.setColumns(16);
        maxEntries.setColumns(16);
        maxIdle.setColumns(16);
    }


}
