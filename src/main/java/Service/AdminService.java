package Service;

import Bean.Admin;
<<<<<<< HEAD
import Bean.AdminPwd;
import Bean.Message;
=======
import Bean.PWD;
>>>>>>> origin/master
import Bean.User;

import java.sql.SQLException;
import java.util.List;

public interface AdminService {
    Admin login(Admin admin);
    int addAdmin(Admin admin);
    List<Admin> queryAllAdmins();
    List<User> queryAllUsers();
    int deletAdmins(String id);
    int deleteUser(String id);

    List<User> getSearchUser(String word);

    List<Admin> getSearchAdmins(Admin admin);

    List<User> queryAllAUsers();

<<<<<<< HEAD
    int changePwd(AdminPwd pwd);

    List<Message> getNoReplyMsg() throws SQLException;
=======
    int changePwd(PWD pwd) throws SQLException;
>>>>>>> origin/master
}
