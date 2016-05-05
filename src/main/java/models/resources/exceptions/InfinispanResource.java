package models.resources.exceptions;

import models.panels.InfinispanModel;
import models.resources.servers.ServerResource;

/**
 * Created by eunderhi on 05/05/16.
 * Resource used for installing infinispan
 */
public class InfinispanResource {

    private ServerResource server;

    public InfinispanResource(ServerResource server) {
        this.server = server;
    }

    public void installInfinispan(InfinispanModel model) throws CommandFailedException {
        createInfinispanContainer(model);
        addInfinispanLocalCache(model);
        addInfinispanEviction(model);
        addInfinispanTransaction(model);
        addInfinispanExpiration(model);
    }

    private void createInfinispanContainer(InfinispanModel model) throws CommandFailedException {
        String command =
                "/subsystem=infinispan/cache-container=" +
                model.getContainer() +
                ":add(jndi-name=\"" +
                model.getJndiName() +
                "\")";

        server.submit(command);
    }

    private void addInfinispanLocalCache(InfinispanModel model) throws CommandFailedException {
        String command =
                "/subsystem=infinispan/cache-container=" +
                model.getContainer() +
                "/local-cache=" +
                model.getLocalCache() +
                ":add()";
        server.submit(command);
    }

    private void addInfinispanEviction(InfinispanModel model) throws CommandFailedException {
        String command = "/subsystem=infinispan/cache-container=" +
                model.getContainer() +
                "/local-cache=" +
                model.getLocalCache() +
                "/eviction=EVICTION:add(strategy=" +
                model.getEvictionStrategy() +
                ",max-entries=" +
                model.getMaxEntries() +
                ")";
        server.submit(command);
    }

    private void addInfinispanTransaction(InfinispanModel model) throws CommandFailedException {
        String command =
                "/subsystem=infinispan/cache-container=" +
                model.getContainer() +
                "/local-cache=" +
                model.getLocalCache() +
                "/transaction=TRANSACTION:add(mode=" +
                model.getTransactionMode() +
                ")";
        server.submit(command);
    }

    public void addInfinispanExpiration(InfinispanModel model) throws CommandFailedException {
        String command = "/subsystem=infinispan/cache-container=" +
                model.getContainer() +
                "/local-cache=" +
                model.getLocalCache() +
                "/expiration=EXPIRATION:add(max-idle=" +
                model.getMaxIdle() +
                ")";
        server.submit(command);
    }

}
