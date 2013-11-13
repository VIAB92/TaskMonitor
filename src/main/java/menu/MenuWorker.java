package menu;

import org.apache.log4j.Logger;
import property_workers.PropertyWriter;
import task.WorkerPool;
import task.WorkerThread;

import java.util.Scanner;

public class MenuWorker {
    Logger logger = Logger.getLogger(MenuWorker.class);

    public void startWork()
    {
        while(true)
        {
            Scanner sc = new Scanner(System.in);
            logger.info("==================================");
            logger.info("Выберите опцию");
            logger.info("1 - заполнить property файл");
            logger.info("2 - перейти к выполнению задач (предварительно необходимо заполнить property)");
            logger.info("3 - выход");
            int answer = sc.nextInt();
            switch(answer)
            {
                case 1: writeProperty();
                    break;
                case 2: startProgram();
                    break;
                case 3:
                    logger.info("Программа завершается...");
                    System.exit(0);
                default:
                    logger.info("Неверная команда");
                    break;
            }

        }
    }

    private void startProgram()
    {
        WorkerPool pool = new WorkerPool();
        pool.letsWork();
    }

    public void writeProperty()
    {
        PropertyWriter writer = new PropertyWriter();
        Scanner sc = new Scanner(System.in);

        logger.info("Введите общее время выполнения программы (в секундах):");
        int totalTime = sc.nextInt();
        writer.setTotalWorkTime(totalTime);
        logger.info("Введите общее количество задач:");
        int tasks = sc.nextInt();
        logger.info("Введите время задержки:");
        int delay = sc.nextInt();
        writer.setDelay(delay);
        Scanner scbd1 = new Scanner(System.in);
        logger.info("Введите имя пользователя БД:");
        String user = scbd1.nextLine();
        Scanner scdb2 = new Scanner(System.in);
        logger.info("Введите пароль БД:");
        String password = scdb2.nextLine();
        Scanner scdb3 = new Scanner(System.in);
        logger.info("Введите url БД:");
        String url = scdb3.nextLine();
        writer.setUrl(url);
        writer.setUser(user);
        writer.setPassword(password);
        for (int i=0; i<tasks; i++)
        {
            Scanner scan = new Scanner(System.in);
            logger.info("Введите имя задачи "+(i+1)+":");
            String taskName = scan.nextLine();

            logger.info("Введите продолжительность задачи (в секундах)" + (i+1)+":");
            int time = scan.nextInt();
            WorkerThread workerThread = new WorkerThread(taskName, time);
            writer.addTaskToList(workerThread);

        }
        writer.writeProperty();


    }
}
