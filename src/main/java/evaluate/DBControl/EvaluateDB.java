package evaluate.DBControl;

/*
    导入对应的包,并且还需要将自己需要的工具类导入该包中
*/
import evaluate.Tools.DBConnection;
import evaluate.DataInfo.EvaluateInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/*
    评标管理数据库操作类
 */
public class EvaluateDB {
    private static Connection conn = DBConnection.getConnection();//获取数据库链接
    public ArrayList<EvaluateInfo> getEvaluate() {//获取所有评标信息,并将信息发送至前端
        ArrayList<EvaluateInfo> evaluateInfo = new ArrayList<>();//定义一个评标信息的集合
        ResultSet rs = null;//定义一个结果集
        PreparedStatement pstmt = null;//定义一个预处理语句集
        try {//捕获异常
            String sql = "select * from evaluate";//定义一个查询语句
            pstmt = conn.prepareStatement(sql);//将查询语句传入预处理语句集中
            rs = pstmt.executeQuery();//执行查询语句
            while (rs.next()) {//遍历结果集
                /*
                    将结果集中的数据存入评标信息集合中
                 */
                EvaluateInfo evaluateInfo1 = new EvaluateInfo();
                evaluateInfo1.setEvaluationId(rs.getInt("EvaluationId"));
                evaluateInfo1.setEvaluationTitle(rs.getString("EvaluationTitle"));
                evaluateInfo1.setEvaluationText(rs.getString("EvaluationText"));
                evaluateInfo1.setEvaluationResult(rs.getInt("EvaluationResult"));
                evaluateInfo.add(evaluateInfo1);
            }
        } catch (Exception e) {//捕获异常
            e.printStackTrace();//打印异常信息
        }
        return evaluateInfo;//返回评标信息集合
    }
    public EvaluateInfo queryEvaluate(int id) {//根据评标编号查询评标信息,输入的ID为评标编号,返回的评标信息对象中的数据为查询到的数据,如果返回的评标编号为0,则说明没有查询到对应的评标信息
        ResultSet rs = null;//定义一个结果集
        PreparedStatement pstmt = null;//定义一个预处理语句集
        EvaluateInfo evaluateInfo = new EvaluateInfo();//定义一个评标信息对象
        evaluateInfo.setEvaluationId(0);//初始化评标编号,如果返回的评标编号为0,则说明没有查询到对应的评标信息
        try {//捕获异常
            String sql = "select * from evaluate where EvaluationId=?";//定义一个查询语句
            pstmt = conn.prepareStatement(sql);//将查询语句传入预处理语句集中
            pstmt.setInt(1, id);//设置查询语句中的参数
            rs = pstmt.executeQuery();//执行查询语句
            if (rs.next()) {//遍历结果集,并且将结果集中的数据存入评标信息对象中
                evaluateInfo.setEvaluationId(rs.getInt("EvaluationId"));
                evaluateInfo.setEvaluationTitle(rs.getString("EvaluationTitle"));
                evaluateInfo.setEvaluationText(rs.getString("EvaluationText"));
                evaluateInfo.setEvaluationResult(rs.getInt("EvaluationResult"));
            }
        } catch (Exception e) {//捕获异常
            e.printStackTrace();//打印异常信息
        }
        return evaluateInfo;//返回评标信息对象
    }
    public int addEvaluate(EvaluateInfo evaluateInfo) {//添加评标信息,输入的评标信息对象中的数据为添加的数据,返回的flag为标志位,如果标志位为0,则说明添加失败,如果标志位不为0,则说明添加成功
        int flag=0;//定义一个标志位,如果标志位为0,则说明添加失败,如果标志位不为0,则说明添加成功
        PreparedStatement pstmt = null;//定义一个预处理语句集
        try{//捕获异常
            pstmt = conn.prepareStatement("insert into evaluate(EvaluationTitle,EvaluationText,EvaluationResult) values(?,?,?)");//定义一个添加语句
            /*
                将评标信息对象中的数据存入添加语句中
             */
            pstmt.setString(1,evaluateInfo.getEvaluationTitle());
            pstmt.setString(2,evaluateInfo.getEvaluationText());
            pstmt.setInt(3,evaluateInfo.getEvaluationResult());
            flag=pstmt.executeUpdate();//执行添加语句
        }catch (Exception e){//捕获异常
            e.printStackTrace();//打印异常信息
        }
        return flag;//返回标志位
    }
    public int deleteEvaluate(int id) {//根据评标编号删除评标信息,输入的ID为评标编号,返回的flag为标志位,如果标志位为0,则说明删除失败,如果标志位不为0,则说明删除成功
        int flag=0;//定义一个标志位,如果标志位为0,则说明删除失败,如果标志位不为0,则说明删除成功
        PreparedStatement pstmt = null;//定义一个预处理语句集
        try{//捕获异常
            pstmt = conn.prepareStatement("delete from evaluate where EvaluationId=?");
            pstmt.setInt(1,id);
            flag=pstmt.executeUpdate();//执行删除语句
        }catch (Exception e){//捕获异常
            e.printStackTrace();//打印异常信息
        }
        return flag;//返回标志位
    }
    public int updateEvaluate(EvaluateInfo evaluateInfo) {//修改评标信息,输入的评标信息对象中的数据为修改后的数据,返回的flag为标志位,如果标志位为0,则说明修改失败,如果标志位不为0,则说明修改成功
        int flag=0;//定义一个标志位,如果标志位为0,则说明修改失败,如果标志位不为0,则说明修改成功
        PreparedStatement pstmt = null;//定义一个预处理语句集
        try{//捕获异常
            pstmt = conn.prepareStatement("update evaluate set EvaluationTitle=?,EvaluationText=?,EvaluationResult=? where EvaluationId=?");
            /*
                将评标信息对象中的数据存入修改语句中
             */
            pstmt.setString(1,evaluateInfo.getEvaluationTitle());
            pstmt.setString(2,evaluateInfo.getEvaluationText());
            pstmt.setInt(3,evaluateInfo.getEvaluationResult());
            pstmt.setInt(4,evaluateInfo.getEvaluationId());
            flag=pstmt.executeUpdate();//执行修改语句
        }catch (Exception e){//捕获异常
            e.printStackTrace();//打印异常信息
        }
        return flag;//返回标志位
    }
}
