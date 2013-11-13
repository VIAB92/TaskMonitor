package rejector;

import org.apache.log4j.Logger;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created with IntelliJ IDEA.
 * User: Victor Rotar
 * Date: 13.11.13
 * Time: 8:27
 * To change this template use File | Settings | File Templates.
 */
public class RejectedExecutionHandlerImpl implements RejectedExecutionHandler {
    Logger logger = Logger.getLogger(RejectedExecutionHandlerImpl.class);
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        logger.info(r.toString() + "is rejected");
    }
}
