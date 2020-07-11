public class Job implements  Runnable {
    private  String jobName;
    private JobPriority jobPriority;

    @Override

    public void  run(){
        System.out.println("Job: "  + jobName +
                " Priority " + jobPriority);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Job(String jobName, JobPriority jobPriority) {
        this.jobName = jobName;
        this.jobPriority = jobPriority;
    }

    public String getJobName() {
        return jobName;
    }

    public JobPriority getJobPriority() {
        return jobPriority;
    }
}
