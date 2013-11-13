package db_worker;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;


public class DatabaseWorker {
    Logger logger = Logger.getLogger(DatabaseWorker.class);
    String user;
    String password;
    String url;
    Connection con;
    public DatabaseWorker(String url, String user, String password)
    {
        this.url=url;
        this.user=user;
        this.password=password;
    }

    public void configureConnection()
    {
        try
        {
            // загрузка Native-драйвера
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.print("JDBC-Driver is OK!\n");
        }
        catch(java.lang.ClassNotFoundException er)
        {

            logger.error(er.getMessage());

        }

        //устанавливаем соединение
        try
        {
            Locale locale = Locale.getDefault();
            Locale.setDefault(Locale.ENGLISH);
            // открытие соединения
            this.con= DriverManager.getConnection(
                    url, user, password);
            System.out.println("Connection is OK!");
            Locale.setDefault(locale);
        }
        catch(SQLException er)
        {
            logger.error(er.getMessage());
        }
    }

    public void makeQuery(int poolSize, int corePoolSize, int activeCount, long completeTasks, long taskCount, boolean isShutdown, boolean isTerminated)
    {
        try
        {
            String monitor = String.format("[%d/%d]", poolSize, corePoolSize);
            String isShutted;
            String isTerm;
            if (isShutdown) isShutted="yes";
            else isShutted="no";
            if (isTerminated) isTerm="yes";
            else isTerm="no";
            Statement stm =con.createStatement();
            String query="insert into reporter values('"+monitor+"', '"+activeCount+"', '"+completeTasks+"', '"+taskCount+"', '"+isShutted+"', '"+isTerm+"')";
            int k = stm.executeUpdate(query);
            stm.close();
            con.commit();
        }catch(SQLException ex)
        {
            logger.error(ex.getMessage());
        }
    }


}
