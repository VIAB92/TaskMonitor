package task;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 13.11.13
 * Time: 16:54
 * To change this template use File | Settings | File Templates.
 */
public class WorkerReader extends WorkerThread {
    ClassToCommunicate comClass;
    public WorkerReader(String s, int work) {

        super(s, work);
    }

    public void setComClass(ClassToCommunicate comClass)
    {
        this.comClass=comClass;
    }

    public void run()
    {
        while(true)
            comClass.get();
    }

}
