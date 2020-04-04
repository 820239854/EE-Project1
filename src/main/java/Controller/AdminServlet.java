package Controller;

import Bean.*;
import Service.AdminService;
import Service.AdminServiceImp;
import Utils.HttpUtils;
import com.alibaba.druid.util.StringUtils;
import com.google.gson.Gson;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/api/admin/admin/*")
public class AdminServlet extends javax.servlet.http.HttpServlet {
    private AdminService service = new AdminServiceImp();
    private Gson gson = new Gson();

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String requestURI = request.getRequestURI();
        String sector = requestURI.replace("/api/admin/admin/","");
        if ("allAdmins".equals(sector)){
            allAdmins(request,response);
        }else if ("allUser".equals(sector)){
            allUser(request,response);
        }else if (sector.contains("deleteAdmins")){
            String id = request.getParameter("id");
            deleteAdmins(request,response,id);
        }else if (sector.contains("deleteUser")){
            String id = request.getParameter("id");
            deleteUser(request,response,id);
        }else if (sector.contains("searchUser")){
            String word = request.getParameter("word");
            getSearchUser(request,response,word);
        }else if ("noReplyMsg".equals(sector)){
            try {
                getNoReplyMsg(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void getNoReplyMsg(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        List<Message> massageList = service.getNoReplyMsg();
        Result result = new Result();
        result.setCode(0);
        result.setData(massageList);
        response.getWriter().println(gson.toJson(result));
    }

    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String requestURI = request.getRequestURI();
        String sector = requestURI.replace("/api/admin/admin/","");

        if ("login".equals(sector)){
            login(request,response);
        } else if ("addAdmins".equals(sector)){
            addAdminss(request,response);
        } else if ("getSearchAdmins".equals(sector)){
            getSearchAdmins(request,response);
        } else if ("changePwd".equals(sector)){
            changePwd(request,response);
        }
    }

    private void getSearchUser(HttpServletRequest request, HttpServletResponse response, String word) throws IOException {
        List<User> searchUser = service.getSearchUser(word);
        Result result = new Result();
        if (searchUser == null){
            result.setCode(1);
            result.setMessage("Failed");
        }else {
            result.setCode(0);
            result.setData(searchUser);
        }
        response.getWriter().println(gson.toJson(result));
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

    private void changePwd(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        AdminPwd pwd = gson.fromJson(requestBody, AdminPwd.class);
        Result result = new Result();
        int i = service.changePwd(pwd);
        if (i==0){
            result.setCode(0);
        }else{
            result.setCode(1);
            if (i==100){
                result.setMessage("Can't be blank");
            }else if (i==101){
                result.setMessage("Wrong pass");
            }else if (i==102){
                result.setMessage("The same");
            }else if (i==103){
                result.setMessage("Don't confirm");
            }else {
                result.setMessage("Error");
            }
        }
        response.getWriter().println(gson.toJson(result));
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        Admin requestAdmin = gson.fromJson(requestBody, Admin.class);
        Admin responseAdmin = service.login(requestAdmin);
        Result result = new Result();

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
            return;
        }

        if (requestAdmin != null){
            result.setCode(0);
            Map<String,Object> map = new HashMap<>();
            map.put("token",responseAdmin.getEmail());
            map.put("name",responseAdmin.getNickname());
            result.setData(map);
            System.out.println(gson.toJson(result));

        }
        else{
            result.setCode(10000);
            result.setMessage("Username or Password is wrong");
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
        Result result = new Result();

        List<Admin> adminList = service.queryAllAdmins();
        result.setCode(0);
        result.setData(adminList);

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
