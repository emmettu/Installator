package models.panels;

import org.infinispan.configuration.cache.TransactionMode;
import org.infinispan.eviction.EvictionStrategy;

/**
 * Created by eunderhi on 05/05/16.
 * Model that represents the users desired infinispan install
 */
public class InfinispanModel {

    private String container;
    private String jndiName;
    private String localCache;
    private TransactionMode transactionMode;
    private EvictionStrategy evictionStrategy;
    private int maxEntries;
    private int maxIdle;

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public String getJndiName() {
        return jndiName;
    }

    public void setJndiName(String jndiName) {
        this.jndiName = jndiName;
    }

    public String getLocalCache() {
        return localCache;
    }

    public void setLocalCache(String localCache) {
        this.localCache = localCache;
    }

    public TransactionMode getTransactionMode() {
        return transactionMode;
    }

    public void setTransactionMode(TransactionMode transactionMode) {
        this.transactionMode = transactionMode;
    }

    public EvictionStrategy getEvictionStrategy() {
        return evictionStrategy;
    }

    public void setEvictionStrategy(EvictionStrategy evictionStrategy) {
        this.evictionStrategy = evictionStrategy;
    }

    public int getMaxEntries() {
        return maxEntries;
    }

    public void setMaxEntries(int maxEntries) {
        this.maxEntries = maxEntries;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

}
