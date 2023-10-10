package evaluate.Servlet;

import evaluate.DBControl.EvaluateDB;
import evaluate.DataInfo.EvaluateInfo;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "EvaluateServlet", value = "/EvaluateServlet")
public class EvaluateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action){
            case "addEvaluate":
                addEvaluate(request,response);
                break;
            case "deleteEvaluate":
                deleteEvaluate(request,response);
                break;
            case "updateEvaluate":
                updateEvaluate(request,response);
                break;
            case "queryEvaluate":
                queryEvaluate(request,response);
                break;
            case "getEvaluate":
                getEvaluate(request,response);
                break;
        }
    }

    private void getEvaluate(HttpServletRequest request, HttpServletResponse response) {
        EvaluateDB evaluateDB = new EvaluateDB();
        ArrayList<EvaluateInfo> evaluateInfo = evaluateDB.getEvaluate();
        response.setContentType("text/html;charset=utf-8");
        try {
            response.getWriter().write(evaluateInfo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void queryEvaluate(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        EvaluateDB evaluateDB = new EvaluateDB();
        EvaluateInfo evaluateInfo = evaluateDB.queryEvaluate(id);
        response.setContentType("text/html;charset=utf-8");
        try {
            response.getWriter().write(evaluateInfo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateEvaluate(HttpServletRequest request, HttpServletResponse response) {
        EvaluateInfo evaluateInfo = new EvaluateInfo();
        evaluateInfo.setEvaluationId(Integer.parseInt(request.getParameter("EvaluationId")));
        evaluateInfo.setEvaluationText(request.getParameter("EvaluationText"));
        evaluateInfo.setEvaluationTitle(request.getParameter("EvaluationTitle"));
        evaluateInfo.setEvaluationResult(Integer.parseInt(request.getParameter("EvaluationResult")));
        EvaluateDB evaluateDB = new EvaluateDB();
        int flag=evaluateDB.updateEvaluate(evaluateInfo);
        if(flag==0){
            try {
                response.getWriter().write("0");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                response.getWriter().write("1");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void deleteEvaluate(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        EvaluateDB evaluateDB = new EvaluateDB();
        int flag=evaluateDB.deleteEvaluate(id);
        if(flag==0){
            try {
                response.getWriter().write("0");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                response.getWriter().write("1");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void addEvaluate(HttpServletRequest request, HttpServletResponse response) {
        EvaluateInfo evaluateInfo = new EvaluateInfo();
        evaluateInfo.setEvaluationId(Integer.parseInt(request.getParameter("EvaluationId")));
        evaluateInfo.setEvaluationText(request.getParameter("EvaluationText"));
        evaluateInfo.setEvaluationTitle(request.getParameter("EvaluationTitle"));
        evaluateInfo.setEvaluationResult(Integer.parseInt(request.getParameter("EvaluationResult")));
        EvaluateDB evaluateDB = new EvaluateDB();
        int flag=evaluateDB.addEvaluate(evaluateInfo);
        if(flag==0){
            try {
                response.getWriter().write("0");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                response.getWriter().write("1");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
