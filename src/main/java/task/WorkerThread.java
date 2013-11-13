package task;

import org.apache.log4j.Logger;

public class WorkerThread implements Runnable {
    Logger logger = Logger.getLogger(WorkerThread.class);
    private String taskName;
    private int workTime;

    public WorkerThread(String s, int work)
    {
        this.taskName=s;
        this.workTime=work;
    }

    @Override
    public void run() {
        logger.info(Thread.currentThread().getName()+" Start. Task = "+taskName);
        processTask();
        logger.info(Thread.currentThread().getName()+" End.");

    }

    private void processTask()
    {
        try
        {
            Thread.sleep(workTime*1000);
        }
        catch(InterruptedException ex)
        {
            logger.error("",ex);
        }
    }

    public String toString()
    {
        return this.taskName;
    }

    public String getTaskName()
    {
        return this.taskName;
    }

    public int getWorkTime()
    {
        return this.workTime;
    }

}
