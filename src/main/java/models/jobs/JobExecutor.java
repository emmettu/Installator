package models.jobs;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by eunderhi on 29/03/16.
 * Maintains a job pool and using runRunnableJobs method will execute all
 * jobs (with dependencies being removed at each step) until the job pool
 * is empty.
 */
public class JobExecutor {

    private ExecutorService executor = Executors.newFixedThreadPool(4);
    private ConcurrentHashMap<String, Job> jobPool = new ConcurrentHashMap<>();
    private final Object lock = new Object();

    public void go() {
        initJobs();
        runRunnableJobs();
    }

    /**
     * Runs all currently runnable jobs. If the job pool is not
     * empty it recursively calls itself again, running the next
     * set of jobs that may have had dependencies on the previous
     * set.
     */
    private void runRunnableJobs() {
        synchronized (lock) {
            jobPool.values().forEach(this::run);
            try {
                lock.wait();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!jobPool.isEmpty()) {
                runRunnableJobs();
            }
        }
    }

    /**
     * Remove non-existent deps
     */
    private void initJobs() {
        for (Job j : jobPool.values()) {
            for (Job dep : j.getDependencies().values()) {
                if (!jobPool.contains(dep)) {
                    j.removeDependency(dep);
                }
            }
        }
    }

    private void run(Job job) {
        if (job.canRun()) {
            jobPool.remove(job.getId());
            executor.submit((Runnable) job::run);
        }
    }

    public void addJob(InstallerJob job) {
        job.setLock(lock);
        jobPool.put(job.getId(), job);
    }

    private void removeJob(InstallerJob job) {
        jobPool.remove(job.getId());
    }

    public void shutDown() {
        executor.shutdown();
    }

}
