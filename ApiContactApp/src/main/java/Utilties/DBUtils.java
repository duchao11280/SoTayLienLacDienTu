package Utilties;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

public abstract class DBUtils{
    private static final String DatabaseName="jdbc:mysql://localhost:3306/contactapp";
//    private static final String DatabaseName="jdbc:mysql://localhost:3306/contactapp";
    private static final String DBUser = "root";
    private static final String DBPass ="";
    private static Connection instance;
    public static Connection getConnection(){
        if(instance==null){
            instance = new Sql2o(DatabaseName,DBUser,DBPass).open();
        }
        return instance;
    }
}
