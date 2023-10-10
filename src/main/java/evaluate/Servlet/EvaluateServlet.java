package evaluate.Servlet;
/*
    导入对应的包,并且还需要将自己需要的工具类导入该包中
 */
import evaluate.DBControl.EvaluateDB;
import evaluate.DataInfo.EvaluateInfo;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "EvaluateServlet", value = "/EvaluateServlet")//设置访问的名字和路径
/*
    评标管理Servlet类
    负责处理前端发送来的访问请求执行对应函数
    如果需要增加函数,则需要在switch中增加对应的case
    执行函数并且返回执行结果
 */
public class EvaluateServlet extends HttpServlet {
    /*
        重写doGet函数
        处理前端发送来的GET请求
        由于前端发送的请求都是POST请求,所以该函数中的代码不会执行
        但为了防止出现意外情况,所以将doGet函数中的代码设置为调用doPost函数
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);//调用doPost函数
    }
    /*
        重写doPost函数
        处理前端发送来的POST请求
        根据前端发送来的action参数执行对应的函数
        并且将执行结果返回给前端
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");//获取前端发送来的action参数
        switch (action){//根据action参数执行对应的函数
            case "addEvaluate":
                addEvaluate(request,response);//执行addEvaluate函数
                break;
            case "deleteEvaluate":
                deleteEvaluate(request,response);//执行deleteEvaluate函数
                break;
            case "updateEvaluate":
                updateEvaluate(request,response);//执行updateEvaluate函数
                break;
            case "queryEvaluate":
                queryEvaluate(request,response);//执行queryEvaluate函数
                break;
            case "getEvaluate":
                getEvaluate(request,response);//执行getEvaluate函数
                break;
        }
    }

    /*
        getEvaluate函数
        获取所有评标信息,并将信息发送至前端
        request与response为前端发送来的请求和需要返回的结果
        一般情况下输入request与response不需要用户进行操作
     */
    private void getEvaluate(HttpServletRequest request, HttpServletResponse response) {
        EvaluateDB evaluateDB = new EvaluateDB();//定义一个评标数据库操作对象
        ArrayList<EvaluateInfo> evaluateInfo = evaluateDB.getEvaluate();//获取所有评标信息
        response.setContentType("text/html;charset=utf-8");//设置返回的数据格式
        try {// 捕获异常
            response.getWriter().write(evaluateInfo.toString());//将评标信息转换为字符串并且返回给前端
        } catch (IOException e) {//捕获异常
            e.printStackTrace();//打印异常信息
        }

    }
/*
    queryEvaluate函数
    根据评标编号查询评标信息,输入的ID为评标编号,返回的评标信息对象中的数据为查询到的数据,如果返回的评标编号为0,则说明没有查询到对应的评标信息
    request与response为前端发送来的请求和需要返回的结果
    一般情况下输入request与response不需要用户进行操作
 */
    private void queryEvaluate(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));//获取前端发送来的评标编号
        EvaluateDB evaluateDB = new EvaluateDB();//定义一个评标数据库操作对象
        EvaluateInfo evaluateInfo = evaluateDB.queryEvaluate(id);//根据评标编号查询评标信息
        response.setContentType("text/html;charset=utf-8");//设置返回的数据格式
        try {//捕获异常
            response.getWriter().write(evaluateInfo.toString());//将评标信息转换为字符串并且返回给前端
        } catch (IOException e) {//捕获异常
            e.printStackTrace();//打印异常信息
        }
    }
/*
    updateEvaluate函数
    修改评标信息,输入的评标信息对象中的数据为修改的数据,返回的flag为标志位,如果标志位为0,则说明修改失败,如果标志位不为0,则说明修改成功
    request与response为前端发送来的请求和需要返回的结果
    一般情况下输入request与response不需要用户进行操作
    用户输入的数据系统能够调用对应函数获取到,并且将数据存入对应的对象中
 */
    private void updateEvaluate(HttpServletRequest request, HttpServletResponse response) {
        EvaluateInfo evaluateInfo = new EvaluateInfo();//定义一个评标信息对象
        evaluateInfo.setEvaluationId(Integer.parseInt(request.getParameter("EvaluationId")));
        evaluateInfo.setEvaluationText(request.getParameter("EvaluationText"));
        evaluateInfo.setEvaluationTitle(request.getParameter("EvaluationTitle"));
        evaluateInfo.setEvaluationResult(Integer.parseInt(request.getParameter("EvaluationResult")));
        //将前端发送来的数据存入评标信息对象中
        EvaluateDB evaluateDB = new EvaluateDB();//定义一个评标数据库操作对象
        int flag=evaluateDB.updateEvaluate(evaluateInfo);//修改评标信息
        if(flag==0){//判断修改是否成功
            try {//捕获异常
                response.getWriter().write("0");//返回修改失败标志
            } catch (IOException e) {//捕获异常
                e.printStackTrace();//打印异常信息
            }
        }else{//判断修改是否成功
            try {//捕获异常
                response.getWriter().write("1");//返回修改成功标志
            } catch (IOException e) {//捕获异常
                e.printStackTrace();//打印异常信息
            }
        }
    }
/*
    deleteEvaluate函数
    删除评标信息,输入的ID为评标编号,返回的flag为标志位,如果标志位为0,则说明删除失败,如果标志位不为0,则说明删除成功
 */
    private void deleteEvaluate(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));//获取前端发送来的评标编号
        EvaluateDB evaluateDB = new EvaluateDB();//定义一个评标数据库操作对象
        int flag=evaluateDB.deleteEvaluate(id);//删除评标信息
        if(flag==0){//判断删除是否成功
            try {//捕获异常
                response.getWriter().write("0");//返回删除失败标志
            } catch (IOException e) {//捕获异常
                e.printStackTrace();//打印异常信息
            }
        }else{//判断删除是否成功
            try {//捕获异常
                response.getWriter().write("1");//返回删除成功标志
            } catch (IOException e) {//捕获异常
                e.printStackTrace();//打印异常信息
            }
        }
    }

    /*
        addEvaluate函数
        添加评标信息,输入的评标信息对象中的数据为添加的数据,返回的flag为标志位,如果标志位为0,则说明添加失败,如果标志位不为0,则说明添加成功
        request与response为前端发送来的请求和需要返回的结果
        一般情况下输入request与response不需要用户进行操作
        用户输入的数据系统能够调用对应函数获取到,并且将数据存入对应的对象中
     */
    private void addEvaluate(HttpServletRequest request, HttpServletResponse response) {
        EvaluateInfo evaluateInfo = new EvaluateInfo();//定义一个评标信息对象
        evaluateInfo.setEvaluationId(Integer.parseInt(request.getParameter("EvaluationId")));
        evaluateInfo.setEvaluationText(request.getParameter("EvaluationText"));
        evaluateInfo.setEvaluationTitle(request.getParameter("EvaluationTitle"));
        evaluateInfo.setEvaluationResult(Integer.parseInt(request.getParameter("EvaluationResult")));
        //将前端发送来的数据存入评标信息对象中
        EvaluateDB evaluateDB = new EvaluateDB();//定义一个评标数据库操作对象
        int flag=evaluateDB.addEvaluate(evaluateInfo);//添加评标信息
        if(flag==0){//判断添加是否成功
            try {//捕获异常
                response.getWriter().write("0");//返回添加失败标志
            } catch (IOException e) {//捕获异常
                e.printStackTrace();//打印异常信息
            }
        }else{//判断添加是否成功
            try {//捕获异常
                response.getWriter().write("1");//返回添加成功标志
            } catch (IOException e) {//捕获异常
                e.printStackTrace();//打印异常信息
            }
        }
    }
}
