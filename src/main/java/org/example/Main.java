package org.example;

public class Main {
  public static void main(String[] args) throws InterruptedException {
    System.out.println("Total Cores " + Runtime.getRuntime().availableProcessors());
    int[] threadsList = new int[] {2, 4, 8, 11, 13, 16, 23, 25, 32};
    for (int threads : threadsList) {
      execute(ThreadType.PLATFORM, threads);
      execute(ThreadType.VIRTUAL, threads);
    }
  }

  static void execute(ThreadType threadType, int threads) {
    long start = System.currentTimeMillis();

    WorkManager workManager = new WorkManager(threadType, threads);
    workManager.start();

    long finish = System.currentTimeMillis();
    long timeElapsed = finish - start;
    System.out.println(
        "Thread Type: "
            + threadType
            + " Thread Count "
            + threads
            + " Time: "
            + timeElapsed
            + " ms");
  }
}
