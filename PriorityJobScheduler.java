import java.util.Comparator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityJobScheduler {

    private ExecutorService priorityJobPoolExecutor;
    private ExecutorService priorityJobScheduler
            = Executors.newSingleThreadExecutor();

    private PriorityBlockingQueue<Job> priorityBlockingQueue;

    public PriorityJobScheduler(Integer poolSize, Integer queueSize){
        priorityJobPoolExecutor = Executors.newFixedThreadPool(poolSize);
        priorityBlockingQueue = new PriorityBlockingQueue<>(queueSize,
                Comparator.comparing(Job::getJobPriority));
        priorityJobScheduler.execute(() -> {
            while (true){
                try {
                    priorityJobPoolExecutor.execute(priorityBlockingQueue.take());
                }catch (InterruptedException e){
                    break;

                }
            }
        });
    }

    public void scheduleJob(Job job){
        priorityBlockingQueue.add(job);
    }

}



