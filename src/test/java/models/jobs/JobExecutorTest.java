package models.jobs;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by eunderhi on 29/03/16.
 */
public class JobExecutorTest {

    @Test
    public void testRunRunnableJobs() throws Exception {
        List<String> jobResults = new ArrayList<>();
        JobExecutor executor = new JobExecutor();
        InstallerJob job1 = new InstallerJob("Job1") {
            @Override
            protected void runJob() {
                jobResults.add("1");
            }
        };

        InstallerJob job2 = new InstallerJob("Job2") {
            @Override
            protected void runJob() {
                jobResults.add("2");
            }
        };

        InstallerJob job3 = new InstallerJob("Job3") {
            @Override
            protected void runJob() {
                jobResults.add("3");
            }
        };

        for (int i = 0; i < 1000; i++) {
            executor.addJob(
                    new InstallerJob("test"+i) {
                        @Override
                        protected void runJob() {
                            jobResults.add("test");
                        }
                    }
            );
        }

        job3.addDependency(job2);
        job2.addDependency(job1);
        executor.addJob(job1);
        executor.addJob(job2);
        executor.addJob(job3);

        executor.go();
        Thread.sleep(100);

        assertTrue(jobResults.indexOf("1") < jobResults.indexOf("2") && jobResults.indexOf("2") < jobResults.indexOf("3"));
        assertEquals(1003, jobResults.size());
    }

    @Test
    public void testNonExistentDep() throws Exception {
        List<String> jobResults = new ArrayList<>();
        JobExecutor executor = new JobExecutor();
        InstallerJob job1 = new InstallerJob("j1") {
            @Override
            protected void runJob() {
                jobResults.add("1");
            }
        };
        InstallerJob job2 = new InstallerJob("j2") {
            @Override
            protected void runJob() {
                jobResults.add("2");
            }
        };
        job2.addDependency(job1);
        executor.addJob(job2);
        executor.go();
        assertEquals("2", jobResults.get(0));
    }

}