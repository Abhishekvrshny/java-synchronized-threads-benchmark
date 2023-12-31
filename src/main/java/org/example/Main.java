package org.example;

public class Main {
  public static void main(String[] args) throws InterruptedException {
    System.out.println("Total Available Cores " + Runtime.getRuntime().availableProcessors());
    System.out.println("========================");
    int[] threadsCountList = new int[] {2, 4, 8, 11, 13, 16, 23, 25, 32};
    for (int threads : threadsCountList) {
      System.out.println("Thread Count = " + threads);
      run(ThreadType.PLATFORM, threads);
      run(ThreadType.VIRTUAL, threads);
      System.out.println("------------------------");
    }
  }

  static void run(ThreadType threadType, int threads) {
    long start = System.currentTimeMillis();

    TaskManager taskManager = new TaskManager(threadType, threads);
    taskManager.start();

    long finish = System.currentTimeMillis();
    long timeElapsed = finish - start;
    System.out.println(
        "Thread Type: "
            + String.format("%-10s" , threadType)
            + " Time: "
            + timeElapsed
            + " ms");
  }
}
