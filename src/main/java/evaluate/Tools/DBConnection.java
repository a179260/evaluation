package evaluate.Tools;
/*
导入对应的包,并且还需要将自己需要的工具类导入该包中
 */
import java.sql.Connection;
import java.sql.DriverManager;
/*
    数据库连接类
    用来获取数据库链接
 */
public class DBConnection {
    private static Connection conn = null;//定义一个数据库链接对象

    public static Connection getConnection(){//获取数据库链接的函数
        if(conn == null){//如果数据库链接对象为空,则创建一个数据库链接对象
            try{//捕获异常
                String driver = "com.mysql.cj.jdbc.Driver";//定义数据库驱动
                Class.forName(driver);//加载数据库驱动
                /*
                    定义数据库链接
                    数据库链接的格式为:jdbc:mysql://localhost:3306/数据库名?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8
                    其中localhost:3306为数据库的地址和端口号
                    数据库名为123
                    useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8为数据库的编码格式
                 */
                String url = "jdbc:mysql://localhost:3306/123?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8";
                conn = DriverManager.getConnection(url, "root", "zxcvbnm");
            }catch (Exception e){//捕获异常
                e.printStackTrace();//打印异常信息
            }
        }
        return conn;//返回数据库链接对象
    }
    public static void closeConnection(){//关闭数据库链接的函数
        if(conn != null){//如果数据库链接对象不为空,则关闭数据库链接对象
            try{//捕获异常
                conn.close();//关闭数据库链接对象
            }catch (Exception e){//捕获异常
                e.printStackTrace();//打印异常信息
            }
        }
    }
}
