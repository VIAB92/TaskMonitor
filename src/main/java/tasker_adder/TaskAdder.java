package tasker_adder;

import org.apache.log4j.Logger;
import property_workers.PropertyReader;
import task.WorkerThread;

import java.util.Scanner;


public class TaskAdder {
    private PropertyReader reader;

    public TaskAdder(PropertyReader reader)
    {
       this.reader=reader;
    }

    Logger logger = Logger.getLogger(TaskAdder.class);
    public void ask()
    {
        Scanner sc = new Scanner(System.in);
        logger.info("Желаете добавить задачу в текущую программу? (yes/no)");
        String answer = sc.nextLine();
        if (answer.toLowerCase().equals("yes"))
        {
            addTask();
            logger.info("Продолжаем работу...");
        }
        else
        {
            logger.info("Продолжаем работу...");
        }

    }

    private void addTask()
    {
        Scanner scan = new Scanner(System.in);
        logger.info("Сколько заданий желаете добавить?");
        int count = scan.nextInt();
        for (int i=0; i<count; i++)
        {
            Scanner scanner = new Scanner(System.in);
            logger.info("Введите имя "+(i+1)+" задачи:");
            String taskName = scanner.nextLine();
            Scanner intScanner = new Scanner(System.in);
            logger.info("Введите продолжительность (в секундах) "+(i+1)+" задачи:");
            int time = intScanner.nextInt();
            WorkerThread wt = new WorkerThread(taskName, time);
            reader.addTaskToList(wt);
        }

    }
}
