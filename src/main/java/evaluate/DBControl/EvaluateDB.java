package evaluate.DBControl;

import evaluate.Tools.DBConnection;
import evaluate.DataInfo.EvaluateInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EvaluateDB {
    private static Connection conn = DBConnection.getConnection();
    public ArrayList<EvaluateInfo> getEvaluate() {
        ArrayList<EvaluateInfo> evaluateInfo = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
            String sql = "select * from evaluate";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                EvaluateInfo evaluateInfo1 = new EvaluateInfo();
                evaluateInfo1.setEvaluationId(rs.getInt("EvaluationId"));
                evaluateInfo1.setEvaluationTitle(rs.getString("EvaluationTitle"));
                evaluateInfo1.setEvaluationText(rs.getString("EvaluationText"));
                evaluateInfo1.setEvaluationResult(rs.getInt("EvaluationResult"));
                evaluateInfo.add(evaluateInfo1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return evaluateInfo;
    }
    public EvaluateInfo queryEvaluate(int id) {
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        EvaluateInfo evaluateInfo = new EvaluateInfo();
        evaluateInfo.setEvaluationId(0);
        try {
            String sql = "select * from evaluate where EvaluationId=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                evaluateInfo.setEvaluationId(rs.getInt("EvaluationId"));
                evaluateInfo.setEvaluationTitle(rs.getString("EvaluationTitle"));
                evaluateInfo.setEvaluationText(rs.getString("EvaluationText"));
                evaluateInfo.setEvaluationResult(rs.getInt("EvaluationResult"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return evaluateInfo;
    }
    public int addEvaluate(EvaluateInfo evaluateInfo) {
        int flag=0;
        PreparedStatement pstmt = null;
        try{
            pstmt = conn.prepareStatement("insert into evaluate(EvaluationTitle,EvaluationText,EvaluationResult) values(?,?,?)");
            pstmt.setString(1,evaluateInfo.getEvaluationTitle());
            pstmt.setString(2,evaluateInfo.getEvaluationText());
            pstmt.setInt(3,evaluateInfo.getEvaluationResult());
            flag=pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
    public int deleteEvaluate(int id) {
        int flag=0;
        PreparedStatement pstmt = null;
        try{
            pstmt = conn.prepareStatement("delete from evaluate where EvaluationId=?");
            pstmt.setInt(1,id);
            flag=pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
    public int updateEvaluate(EvaluateInfo evaluateInfo) {
        int flag=0;
        PreparedStatement pstmt = null;
        try{
            pstmt = conn.prepareStatement("update evaluate set EvaluationTitle=?,EvaluationText=?,EvaluationResult=? where EvaluationId=?");
            pstmt.setString(1,evaluateInfo.getEvaluationTitle());
            pstmt.setString(2,evaluateInfo.getEvaluationText());
            pstmt.setInt(3,evaluateInfo.getEvaluationResult());
            pstmt.setInt(4,evaluateInfo.getEvaluationId());
            flag=pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
}
