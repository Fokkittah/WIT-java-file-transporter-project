package controller;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Fokkittah
 */

public class ThreadManager {
    private ExecutorService executorService;

    public ThreadManager(int numThreads) {
        this.executorService = Executors.newFixedThreadPool(numThreads);
    }

    public void executeTasks(List<? extends Runnable> tasks) {
        for (Runnable task : tasks) {
            executorService.execute(task);
        }

        executorService.shutdown();

        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            // Log the exception
            // e.printStackTrace();
        }
    }
}