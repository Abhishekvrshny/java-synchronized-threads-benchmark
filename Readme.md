# java-synchronized-threads-benchmark
Virtual threads were introduced in Java 19 as a peview feature, but they suffer from a major performance issue wherein they get [`pinned`](https://docs.oracle.com/en/java/javase/21/core/virtual-threads.html#GUID-704A716D-0662-4BC7-8C7F-66EE74B1EDAD) to the carrier thread. This happens when:
- The virtual thread runs code inside a `synchronized` block or method.
- The virtual thread runs a native method or a foreign function.

The purpose of this repository is to highlight the difference in performance of `platform` vs `virtual` threads using a sample scenario.

## Test Setup
1. The code in this repository creates a thread pool of `platform` and `virtual` threads using `ExecutorService` with variable thread pool size.
2. The threads then execute a long running taks in a `synchronized` block. This is simulated by `Thread.sleep(4000)`. The tasks are executed by all threads concurrently.
3. The total time taken to execute all the tasks are recorded.

## Run
```
Total Available Cores 12
========================
Thread Count = 2
Thread Type: PLATFORM   Time: 4012 ms
Thread Type: VIRTUAL    Time: 4006 ms
------------------------
Thread Count = 4
Thread Type: PLATFORM   Time: 4006 ms
Thread Type: VIRTUAL    Time: 4005 ms
------------------------
Thread Count = 8
Thread Type: PLATFORM   Time: 4002 ms
Thread Type: VIRTUAL    Time: 4006 ms
------------------------
Thread Count = 11
Thread Type: PLATFORM   Time: 4007 ms
Thread Type: VIRTUAL    Time: 4006 ms
------------------------
Thread Count = 13
Thread Type: PLATFORM   Time: 4002 ms
Thread Type: VIRTUAL    Time: 8004 ms
------------------------
Thread Count = 16
Thread Type: PLATFORM   Time: 4007 ms
Thread Type: VIRTUAL    Time: 8004 ms
------------------------
Thread Count = 23
Thread Type: PLATFORM   Time: 4004 ms
Thread Type: VIRTUAL    Time: 8003 ms
------------------------
Thread Count = 25
Thread Type: PLATFORM   Time: 4007 ms
Thread Type: VIRTUAL    Time: 12009 ms
------------------------
Thread Count = 32
Thread Type: PLATFORM   Time: 4008 ms
Thread Type: VIRTUAL    Time: 12008 ms
------------------------
```

## Interpretation
It is evident from the run that `platform` threads `yield` on `sleeping` and release the processor to other threads, while `virtual` threads seem to be holding on to the processor and hence total execution time adds up once the total number of threads executing exceeds the total processor count.

![result](https://github.com/Abhishekvrshny/java-synchronized-threads-benchmark/assets/12811812/1352b2eb-4313-4a36-91a0-7bd68e88afaf) 

## Detailed Explanation
TBD
