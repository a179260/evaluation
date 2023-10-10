package evaluate.DataInfo;

public class EvaluateInfo {
    private int evaluationId;
    private String evaluationTitle;
    private String complaint;
    private String evaluationText;
    private int evaluationResult;
    public int getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(int evaluationId) {
        this.evaluationId = evaluationId;
    }

    public String getEvaluationTitle() {
        return evaluationTitle;
    }

    public void setEvaluationTitle(String evaluationTitle) {
        this.evaluationTitle = evaluationTitle;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public String getEvaluationText() {
        return evaluationText;
    }

    public void setEvaluationText(String evaluationText) {
        this.evaluationText = evaluationText;
    }

    public int getEvaluationResult() {
        return evaluationResult;
    }

    public void setEvaluationResult(int evaluationResult) {
        this.evaluationResult = evaluationResult;
    }
}
