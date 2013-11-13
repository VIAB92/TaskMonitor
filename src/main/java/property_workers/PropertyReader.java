package property_workers;

import org.apache.log4j.Logger;
import task.WorkerThread;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;


public class PropertyReader {
    Logger logger = Logger.getLogger(PropertyReader.class);
    private List<WorkerThread> tasks;
    private int delay;
    private int totalTasks;
    private int programWorkTime;
    private String user;
    private String password;
    private String url;

    public PropertyReader()
    {
        tasks = new LinkedList<WorkerThread>();
    }

    public void readProperty()
    {
        Properties properties = new Properties();
        try
        {
            properties.load(new FileInputStream("config.properties"));
            this.url=properties.getProperty("url");
            this.user=properties.getProperty("user");
            this.password=properties.getProperty("password");
            this.delay = Integer.parseInt(properties.getProperty("delay"));
            this.totalTasks = Integer.parseInt(properties.getProperty("total"));
            this.programWorkTime = Integer.parseInt(properties.getProperty("programwork"));
            for (int i=0; i<totalTasks; i++)
            {
                String name = properties.getProperty("task"+i+"name");
                int workTime = Integer.parseInt(properties.getProperty("task"+i+"time"));
                WorkerThread workerThread = new WorkerThread(name, workTime);
                tasks.add(workerThread);
            }
        }
        catch (IOException ex)
        {
            logger.error("",ex);
        }
    }

    public void addTaskToList(WorkerThread task)
    {
        this.tasks.add(task);
        totalTasks+=1;

    }

    public WorkerThread getTask(int i)
    {
        if (i>=totalTasks)
        {
            return tasks.get(0);
        }
        else
        {
            return tasks.get(i);
        }
    }

    public int getDelay()
    {
        return this.delay;
    }

    public int getTotalTasks()
    {
        return totalTasks;
    }

    public int getProgramWorkTime()
    {
        return programWorkTime;
    }

    public String getUser()
    {
        return this.user;
    }

    public String getPassword()
    {
        return this.password;
    }

    public String getUrl()
    {
        return this.url;
    }
}
