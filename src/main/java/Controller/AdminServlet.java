package Controller;

import Bean.Admin;
import Bean.Result;
import Bean.User;
import Service.AdminService;
import Service.AdminServiceImp;
import Utils.HttpUtils;
import com.alibaba.druid.util.StringUtils;
import com.google.gson.Gson;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/api/admin/*")
public class AdminServlet extends javax.servlet.http.HttpServlet {
    private AdminService service = new AdminServiceImp();
    private Gson gson = new Gson();


    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String requestURI = request.getRequestURI();
        String which = requestURI.replace("/api/admin/","");

        if ("login".equals(which)){
            login(request,response);
        } else if ("addAdmins".equals(which)){
            addAdminss(request,response);
        } else if ("getSearchAdmins".equals(which)){
            getSearchAdmins(request,response);
        } else if ("changePwd".equals(which)){
        }

    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        Result result = new Result();
        Admin requestAdmin = gson.fromJson(requestBody, Admin.class);
        Admin responseAdmin = service.login(requestAdmin);

        if (StringUtils.isEmpty(requestBody)){
            result.setCode(0);
            result.setMessage("Can't be blank");
            return;
        }

        if (StringUtils.isEmpty(requestAdmin.getEmail()) || StringUtils.isEmpty(requestAdmin.getPwd())){
            System.out.println(requestAdmin.getEmail());
            System.out.println("Can't be blank");
            result.setCode(0);
            result.setMessage("Can't be blank");
            System.out.println(gson.toJson(result));
            return;
        }



        // Test
        result.setCode(0);
        Map<String,String> map = new HashMap<>();
        map.put("token",responseAdmin.getEmail());
        map.put("name",responseAdmin.getNickname());
        result.setData(map);
        response.getWriter().println(gson.toJson(result));

//        if (requestAdmin != null){
//            result.setCode(0);
//            Map<String,String> map = new HashMap<>();
//            map.put("token",responseAdmin.getEmail());
//            map.put("name",responseAdmin.getNickname());
//            result.setData(map);
//            System.out.println(gson.toJson(result));
//        }
//        else{
//            result.setCode(10000);
//            result.setMessage("Username or Password is wrong");
//        }
//        response.getWriter().println(gson.toJson(result));
    }

    private void getSearchAdmins(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        Admin admin = gson.fromJson(requestBody,Admin.class);
        Result result = new Result();
        List<Admin> searchAdmins = service.getSearchAdmins(admin);
        if (searchAdmins == null){
            result.setCode(1);
            result.setMessage("Error");
        }else {
            result.setCode(0);
            result.setData(searchAdmins);
        }
        response.getWriter().println(gson.toJson(result));
    }

    private void addAdminss(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        Admin admin = gson.fromJson(requestBody,Admin.class);
        Result result = new Result();
        int i = service.addAdmin(admin);
        result.setCode(i);
        response.getWriter().println(gson.toJson(result));
    }

    private void allAdmins(HttpServletRequest request,HttpServletResponse response) throws IOException {
        List<Admin> adminList = service.queryAllAdmins();
        Result result = new Result();
        result.setCode(0);
        result.setData(adminList);
        response.getWriter().println(gson.toJson(result));
    }


    
    



    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("get");
        String requestURI = request.getRequestURI();
        String which = requestURI.replace("api/admin/","");
        if ("allAdmins".equals(which)){
            allAdmins(request,response);
        }else if ("allUser".equals(which)){
            allUser(request,response);
        }else if (which.contains("deleteAdmins")){
            String id = request.getParameter("id");
            deleteAdmins(request,response,id);
        }else if (which.contains("deleteUser")){
            String id = request.getParameter("id");
            deleteUser(request,response,id);
        }else if (which.contains("searchUser")){
            String word = request.getParameter("word");
            getSearchAdmins(request,response,word);
        }
    }

    private void getSearchAdmins(HttpServletRequest request, HttpServletResponse response, String word) throws IOException {
        List<User> searchUser = service.getSearchUser(word);
        Result result = new Result();
        if (searchUser == null){
            result.setCode(1);
            result.setMessage("Error");
        }else {
            result.setCode(0);
            result.setData(searchUser);
        }
        response.getWriter().println(gson.toJson(result));
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response,String id) throws IOException {
        int i = service.deleteUser(id);
        Result result = new Result();
        result.setCode(0);
        response.getWriter().println(gson.toJson(result));
    }

    private void deleteAdmins(HttpServletRequest request, HttpServletResponse response, String id) throws IOException {
        int i = service.deletAdmins(id);
        Result result = new Result();
        result.setCode(0);
        response.getWriter().println(gson.toJson(result));
    }

    private void allUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<User> userList = service.queryAllAUsers();
        Result result = new Result();
        result.setCode(0);
        result.setData(userList);
        response.getWriter().println(gson.toJson(result));
    }
}
