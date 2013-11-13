package program;

import menu.MenuWorker;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Victor Rotar
 * Date: 13.11.13
 * Time: 10:05
 * To change this template use File | Settings | File Templates.
 */
public class MainProgram {
    Logger logger = Logger.getLogger(MainProgram.class);
    public static void main(String[] args)
    {
        BasicConfigurator.configure();
        MenuWorker menuWorker = new MenuWorker();
        menuWorker.startWork();

    }
}
