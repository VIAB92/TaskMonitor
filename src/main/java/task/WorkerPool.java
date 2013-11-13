package task;

import db_worker.DatabaseWorker;
import monitor.MonitorThread;
import org.apache.log4j.Logger;
import property_workers.PropertyReader;
import rejector.RejectedExecutionHandlerImpl;
import tasker_adder.TaskAdder;

import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * User: Victor Rotar
 * Date: 13.11.13
 * Time: 9:18
 * To change this template use File | Settings | File Templates.
 */
public class WorkerPool {
    Logger logger = Logger.getLogger(WorkerPool.class);
    public void letsWork()
    {
        PropertyReader reader = new PropertyReader();
        reader.readProperty();
        TaskAdder adder = new TaskAdder(reader);
        adder.ask();
        DatabaseWorker dbWorker = new DatabaseWorker(reader.getUrl(), reader.getUser(), reader.getPassword());
        RejectedExecutionHandlerImpl rejectionHandler = new RejectedExecutionHandlerImpl();
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        ThreadPoolExecutor executorPool = new ThreadPoolExecutor(2,20,60, TimeUnit.SECONDS,  new ArrayBlockingQueue<Runnable>(2),threadFactory, rejectionHandler);
        MonitorThread monitor = new MonitorThread(dbWorker, executorPool, reader.getDelay());
        Thread monitorThread = new Thread(monitor);
        monitorThread.start();

        for (int i=0; i<reader.getTotalTasks(); i++)
        {
            executorPool.execute(reader.getTask(i));
        }

        try
        {
            Thread.sleep(reader.getProgramWorkTime()*1000);
            executorPool.shutdown();
            Thread.sleep(5000);
            monitor.shutdown();
        }
        catch (InterruptedException ex)
        {
            logger.error("", ex);
        }
    }
}
