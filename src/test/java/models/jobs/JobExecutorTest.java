package models.jobs;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by eunderhi on 29/03/16.
 */
public class JobExecutorTest {

    @Test
    public void testRunRunnableJobs() throws Exception {
        JobExecutor executor = new JobExecutor();
        InstallerJob job1 = new InstallerJob("Job1") {
            @Override
            protected void runJob() {
                System.out.println("1");
            }
        };

        InstallerJob job2 = new InstallerJob("Job2") {
            @Override
            protected void runJob() {
                System.out.println("2");
            }
        };

        InstallerJob job3 = new InstallerJob("Job3") {
            @Override
            protected void runJob() {
                System.out.println("3");
            }
        };

        InstallerJob job4 = new InstallerJob("Independent1") {
            @Override
            protected void runJob() {
                System.out.println("I can be in any order");
            }
        };

        InstallerJob job5 = new InstallerJob("Independent2") {
            @Override
            protected void runJob() {
                System.out.println("yo.");
            }
        };

        InstallerJob job6 = new InstallerJob("Independent3") {
            @Override
            protected void runJob() {
                System.out.println("whatever.");
            }
        };

        job3.addDependency(job2);
        job2.addDependency(job1);
        executor.addJob(job1);
        executor.addJob(job2);
        executor.addJob(job3);
        executor.addJob(job4);
        executor.addJob(job5);
        executor.addJob(job6);

        executor.runRunnableJobs();
    }

}