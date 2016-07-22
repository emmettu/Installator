package controllers.models;

import controllers.Controller;
import models.panels.InfinispanModel;
import org.infinispan.configuration.cache.TransactionMode;
import org.infinispan.eviction.EvictionStrategy;
import views.ui.gui.Combobox;
import views.ui.gui.TextInputField;
import views.ui.gui.panels.InfinispanPanel;

/**
 * Created by eunderhi on 22/07/16.
 */
public class InfinispanController implements Controller {

    private InfinispanModel model;
    private TextInputField name;
    private TextInputField jndi;
    private TextInputField localCache;
    private Combobox<TransactionMode> transactionMode;
    private Combobox<EvictionStrategy> evictionStrategy;
    private TextInputField maxEntries;
    private TextInputField maxIdle;

    public InfinispanController(InfinispanPanel panel, InfinispanModel model) {
        subscribeToPanel(panel);
        this.model = model;
    }

    @Override
    public void performAction() {
        model.setContainer(name.getText());
        model.setJndiName(jndi.getText());
        model.setLocalCache(localCache.getText());
        model.setTransactionMode(transactionMode.getSelectedItem());
        model.setEvictionStrategy(evictionStrategy.getSelectedItem());
        model.setMaxEntries(maxEntries.getText());
        model.setMaxIdle(maxIdle.getText());

    }

    private void subscribeToPanel(InfinispanPanel panel) {
        name = panel.getName();
        jndi = panel.getJndi();
        localCache = panel.getLocalCache();
        transactionMode = panel.getTransactionMode();
        evictionStrategy = panel.getEvictionStrategy();
        maxEntries = panel.getMaxEntries();
        maxIdle = panel.getMaxIdle();
    }

}
