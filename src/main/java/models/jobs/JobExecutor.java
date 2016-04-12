package models.jobs;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by eunderhi on 29/03/16.
 */
public class JobExecutor {

    private ExecutorService executor = Executors.newFixedThreadPool(10);
    private ConcurrentHashMap<String, Job> jobPool = new ConcurrentHashMap<>();
    private final Object lock = new Object();

    public void runRunnableJobs() {
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
