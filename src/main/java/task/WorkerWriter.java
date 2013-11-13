package task;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 13.11.13
 * Time: 16:54
 * To change this template use File | Settings | File Templates.
 */
public class WorkerWriter extends WorkerThread {
    ClassToCommunicate comClass;
    public WorkerWriter(String s, int work) {
        super(s, work);
    }

    public void setComClass(ClassToCommunicate classToCommunicate)
    {
        this.comClass=classToCommunicate;
    }

    public void run()
    {
        int i=0;
        while(true)
        {
            comClass.put(i++);
        }
    }
}
