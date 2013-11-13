package task;

import org.apache.log4j.Logger;

public class ClassToCommunicate {
    Logger logger = Logger.getLogger(ClassToCommunicate.class);
    int n;
    boolean valueSet=false;

    synchronized int get()
    {
        while(!valueSet)
            try
            {
                wait();
            }
            catch (InterruptedException ex)
            {
                logger.error("",ex);
            }
            logger.info("Получено: "+n);
            valueSet = false;
            notify();
            return n;

    }

    synchronized void put(int n)
    {
        while(valueSet)
        {
            try
            {
                wait();
            }
            catch (InterruptedException ex)
            {
                logger.error("",ex);
            }
            this.n=n;
            valueSet=true;
            logger.info("Отправлено: "+n);
            notify();
        }
    }
}
