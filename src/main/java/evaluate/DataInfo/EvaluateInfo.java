package evaluate.DataInfo;

public class EvaluateInfo {
    private int evaluationId;//评标编号
    private String evaluationTitle;//评标标题
    private String complaint;//投诉
    private String evaluationText;//评标内容
    private int evaluationResult;//评标结果
    /*
        评标信息对象的构造函数
     */
    public int getEvaluationId() {//获取评标编号
        return evaluationId;
    }

    public void setEvaluationId(int evaluationId) {//设置评标编号
        this.evaluationId = evaluationId;
    }

    public String getEvaluationTitle() {//获取评标标题
        return evaluationTitle;
    }

    public void setEvaluationTitle(String evaluationTitle) {//设置评标标题
        this.evaluationTitle = evaluationTitle;
    }

    public String getComplaint() {//获取投诉
        return complaint;
    }

    public void setComplaint(String complaint) {//设置投诉
        this.complaint = complaint;
    }

    public String getEvaluationText() {//获取评标内容
        return evaluationText;
    }

    public void setEvaluationText(String evaluationText) {//设置评标内容
        this.evaluationText = evaluationText;
    }

    public int getEvaluationResult() {//获取评标结果
        return evaluationResult;
    }

    public void setEvaluationResult(int evaluationResult) {//设置评标结果
        this.evaluationResult = evaluationResult;
    }
}
