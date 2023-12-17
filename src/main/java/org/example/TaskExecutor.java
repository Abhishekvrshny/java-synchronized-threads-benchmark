package org.example;

public class TaskExecutor {

  public void execute() throws InterruptedException {
    synchronized (this) {
      Thread.sleep(4000);
    }
  }
}
