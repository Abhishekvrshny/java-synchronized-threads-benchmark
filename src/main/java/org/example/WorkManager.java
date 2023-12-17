package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class WorkManager {
    private ThreadType workType;
    private ThreadFactory threadFactory;
    private int threads;
    public WorkManager(ThreadType workType, int threads) {
        this.workType = workType;
        this.threads = threads;
        switch (workType){
            case VIRTUAL -> this.threadFactory = Thread.ofVirtual().factory();
            case PLATFORM -> this.threadFactory = Thread.ofPlatform().factory();
        }
    }

    public void start() throws InterruptedException {
        try (ExecutorService executor = Executors.newFixedThreadPool(this.threads, this.threadFactory)) {
            for (int j = 0; j < this.threads; j++) {
                TaskExecutor taskExecutor = new TaskExecutor();
                executor.submit(() -> {
                    try {
                        taskExecutor.execute();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }
    }
}
