package controller;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Fokkittah
 */

/**
 * The ThreadManager class provides a mechanism for executing multiple tasks in parallel using a fixed thread pool.
 * It manages the execution of tasks by creating a thread pool and submitting the tasks to the pool for execution.
 */

public class ThreadManager {
    private ExecutorService executorService;

    /**
     * Constructs a ThreadManager object with the specified number of threads in the thread pool.
     *
     * @param numThreads the number of threads in the thread pool
     */
    
    public ThreadManager(int numThreads) {
        this.executorService = Executors.newFixedThreadPool(numThreads);
    }

    /**
     * Executes the given list of tasks in parallel using the thread pool.
     * The tasks are submitted to the thread pool for execution.
     * This method blocks until all tasks have completed their execution.
     *
     * @param tasks the list of tasks to be executed
     */
    
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