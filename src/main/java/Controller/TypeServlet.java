package Controller;

import Bean.Result;
import Bean.Type;
import Service.TypeService;
import Service.TypeServiceImpl;
import Utils.HttpUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/admin/type/*")
public class TypeServlet extends HttpServlet {
    TypeService typeService = new TypeServiceImpl();
    Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String sector = requestURI.replace("/api/admin/type/", "");
        switch (sector){
            case "addType":
                addType(request,response);
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String sector = requestURI.replace("/api/admin/type/", "");
        switch (sector){
            case "getType":
                getType(request,response);
                break;
            case "deleteType":
                deleteType(request,response);
                break;
        }
    }

    private void addType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody= HttpUtils.getRequestBody(request);
        Type newType=gson.fromJson(requestBody, Type.class);
        int status=typeService.addType(newType);
        Result result=new Result();
        if(status==0)
        {
            result.setCode(0);
        }else {
            result.setCode(500);
            result.setMessage("添加类目失败！");
        }
        response.getWriter().println(gson.toJson(result, Result.class));
    }

    private void deleteType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int typeId=Integer.parseInt(request.getParameter("typeId"));
        int status=typeService.deleteType(typeId);
        Result result=new Result();
        if(status==0)
        {
            result.setCode(0);
        }else if(status==500){
            result.setCode(500);
            result.setMessage("删除类目失败！");
        }
        response.getWriter().println(gson.toJson(result, Result.class));
    }

    private void getType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Result result = new Result();
        result.setCode(0);
        result.setData(typeService.getType());
        response.getWriter().println(gson.toJson(result,Result.class));
    }
}
