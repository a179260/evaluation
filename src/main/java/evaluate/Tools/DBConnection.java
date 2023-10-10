package evaluate.Tools;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static Connection conn = null;

    public static Connection getConnection(){
        if(conn == null){
            try{
                String driver = "com.mysql.cj.jdbc.Driver";
                Class.forName(driver);
                String url = "jdbc:mysql://localhost:3306/123?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8";
                conn = DriverManager.getConnection(url, "root", "zxcvbnm");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return conn;
    }
    public static void closeConnection(){
        if(conn != null){
            try{
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
