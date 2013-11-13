package monitor;

import db_worker.DatabaseWorker;
import org.apache.log4j.Logger;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created with IntelliJ IDEA.
 * User: Victor Rotar
 * Date: 13.11.13
 * Time: 8:29
 * To change this template use File | Settings | File Templates.
 */
public class MonitorThread implements Runnable {
    Logger logger = Logger.getLogger(MonitorThread.class);
    private ThreadPoolExecutor executor;
    private DatabaseWorker dbWorker;
    private int seconds;
    private boolean run=true;

    public MonitorThread(DatabaseWorker wk,ThreadPoolExecutor executor, int delay)
    {
        this.executor=executor;
        this.seconds=delay;
        this.dbWorker=wk;
        dbWorker.configureConnection();
    }

    public void shutdown()
    {
        this.run=false;
    }

    public void run()
    {
        while(run)
        {
            int poolSize=this.executor.getPoolSize();
            int corePoolSize = this.executor.getCorePoolSize();
            int activeCount = this.executor.getActiveCount();
            long completeTasks = this.executor.getCompletedTaskCount();
            long taskCount = this.executor.getTaskCount();
            boolean isShutdown = this.executor.isShutdown();
            boolean isTerminated = this.executor.isTerminated();
            String report = String.format("[monitor] [%d/%d] Active: %d, Completed: %d, Task: %d, isShutdown: %s, isTerminated: %s",
                  poolSize, corePoolSize, activeCount, completeTasks, taskCount, isShutdown, isTerminated);
            logger.info(report);
            dbWorker.makeQuery(poolSize, corePoolSize, activeCount, completeTasks, taskCount, isShutdown, isTerminated);

            try
            {
                Thread.sleep(seconds*1000);
            }
            catch(InterruptedException ex)
            {
                logger.error("",ex);
            }
        }

    }
}
