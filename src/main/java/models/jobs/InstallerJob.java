package models.jobs;

import models.InstallerModel;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by eunderhi on 29/03/16.
 * Base job class that can be executed by JobExecutor
 */
public abstract class InstallerJob extends InstallerModel implements Job {

    private HashMap<String, Job> dependencies = new HashMap<>();
    private HashMap<String, Job> dependents = new HashMap<>();
    private String id;
    private Object lock;
    public enum State { RUNNING, FINISHED, WAITING, FAILED }
    private State state;
    private String status;

    public InstallerJob(String id) {
        this.id = id;
        setState(State.WAITING);
    }

    @Override
    public Map<String, Job> getDependencies() {
        return dependencies;
    }

    @Override
    public void addDependency(Job job) {
        dependencies.put(job.getId(), job);
        job.addDependent(this);
    }

    @Override
    public void addDependent(Job job) {
        dependents.put(job.getId(), job);
    }

    @Override
    public void removeDependency(Job job) {
        dependencies.remove(job.getId());
    }

    @Override
    public boolean canRun() {
        return dependencies.isEmpty();
    }

    @Override
    public void run() {
        synchronized (lock) {
            setState(State.RUNNING);
            runJob();
            for (Job job : dependents.values()) {
                job.removeDependency(this);
            }
            setState(State.FINISHED);
            lock.notify();
        }
    }

    protected abstract void runJob();

    @Override
    public String getId() {
        return id;
    }

    public void setLock(Object lock) {
        this.lock = lock;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
        notifyListeners();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        notifyListeners();
    }

}
