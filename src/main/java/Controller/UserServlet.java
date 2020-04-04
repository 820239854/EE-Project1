package Controller;

import Bean.Result;
import Bean.User;
import Service.AdminService;
import Service.AdminServiceImp;
import Service.UserService;
import Service.UserServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/admin/user/*")
public class UserServlet extends HttpServlet {
    private UserService  userService = new UserServiceImpl();
    private Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/user/", "");
        if("allUser".equals(action)){
            allUser(request,response);
        }else if(action.contains("deleteUser")){
            deleteUser(request, response);
        }else if (action.contains("searchUser")){
            searchUser(request,response);
        }
    }

    private void searchUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nickname = request.getParameter("word");
        List<User> users = userService.searchUser(nickname);
        Result result = new Result();
        result.setCode(0);
        result.setData(users);
        response.getWriter().println(gson.toJson(result));
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uid = request.getParameter("id");
        int result = userService.deleteUser(uid);
        Result res = new Result();
        if (result == 0){
            res.setCode(0); // 删除成功
        } else {
            res.setCode(10000); // 删除失败
        }
        response.getWriter().println(gson.toJson(res));
    }

    private void allUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<User> userList = userService.queryAllAUsers();
        Result result = new Result();
        result.setCode(0);
        result.setData(userList);
        response.getWriter().println(gson.toJson(result));
    }
}
