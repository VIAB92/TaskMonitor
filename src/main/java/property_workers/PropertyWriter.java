package property_workers;

import org.apache.log4j.Logger;
import task.WorkerThread;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Victor Rotar
 * Date: 13.11.13
 * Time: 8:44
 * To change this template use File | Settings | File Templates.
 */
public class PropertyWriter {
    Logger logger = Logger.getLogger(PropertyWriter.class);
    private List<WorkerThread> tasks;
    int delay;
    int totalWorkTime;
    String user;
    String password;
    String url;
    public PropertyWriter()
    {
        this.tasks=new LinkedList<WorkerThread>();
    }

    public void addTaskToList(WorkerThread task)
    {
        this.tasks.add(task);
    }

    public void setDelay(int value)
    {
        this.delay=value;
    }

    public void setUser(String value)
    {
        this.user=value;
    }

    public void setPassword(String value)
    {
        this.password=value;
    }

    public void setUrl(String value)
    {
        this.url=value;
    }
    public void setTotalWorkTime(int value)
    {
        this.totalWorkTime=value;
    }
    public void writeProperty()
    {
        int count = tasks.size();
        Properties properties = new Properties();
        try
        {
            properties.setProperty("total", Integer.toString(count));
            properties.setProperty("delay", Integer.toString(delay));
            properties.setProperty("user", user);
            properties.setProperty("password", password);
            properties.setProperty("url", url);
            properties.setProperty("programwork", Integer.toString(totalWorkTime));
            for (int i=0; i<count; i++)
            {
                properties.setProperty("task"+i+"name", tasks.get(i).getTaskName());
                properties.setProperty("task"+i+"time", Integer.toString(tasks.get(i).getWorkTime()));
            }
            properties.store(new FileOutputStream("config.properties"), null);
        }
        catch (IOException ex)
        {
            logger.error("",ex);
        }
    }

}
