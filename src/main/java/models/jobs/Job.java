package models.jobs;

import java.util.Map;

/**
 * Created by eunderhi on 29/03/16.
 */
public interface Job {

    Map<String, Job> getDependencies();
    void addDependency(Job job);
    void addDependent(Job job);
    void removeDependency(Job job);
    boolean canRun();
    void run();
    String getId();

}
