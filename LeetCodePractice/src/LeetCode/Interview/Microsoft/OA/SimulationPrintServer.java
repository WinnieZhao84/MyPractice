package LeetCode.Interview.Microsoft.OA;

import java.util.concurrent.*;

/**
 * You are writing a simulation for a print server. This print server can accept jobs from 3 places -
 * network, USB, or operator. It can dispatch only one job at a time. Each input job should contain an
 * integer t which is the time in seconds it will take to process the job.
 * Write a multi-threaded program to simulate the server and provide some simulated load with jobs.
 * Think, of some interesting statistics your program should emit and code them in
 *
 * Created by WinnieZhao on 2/27/2018.
 */
public class SimulationPrintServer {

    enum TaskType {NETWORK, USB, OPERATOR}
    enum TaskStatus {IN_PROCESS, COMPLETED}

    class PrintTask {
        TaskType type;
        TaskStatus status;
        String input;
        int timeInSeconds;

        public PrintTask(TaskType type, String input, int timeInSeconds) {
            this.type = type;
            this.input = input;
            this.timeInSeconds = timeInSeconds;
            this.status = TaskStatus.IN_PROCESS;
        }

        public String print() {
            int executionTime = this.timeInSeconds;
            while (executionTime > 0) {
                executionTime--;
            }

            System.out.println(this.input);
            return this.input;
        }
    }

    class PrintWorker implements Callable<String> {
        private PrintTask task;

        public PrintWorker(PrintTask task) {
            this.task = task;
        }

        @Override
        public String call() {
            return this.task.print();
        }
    }

    BlockingQueue<PrintWorker> workQueue;
    ExecutorService executorService;

    public SimulationPrintServer() {
        workQueue = new LinkedBlockingQueue<>();
        executorService = Executors.newSingleThreadExecutor();
    }

    public void performPrinting() {
        try {
            PrintWorker worker = workQueue.take();

            Future<String> future = executorService.submit(worker);


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
