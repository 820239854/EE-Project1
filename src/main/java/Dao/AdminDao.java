package Dao;

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

public interface AdminDao {
    Admin login(Admin admin);
    int addAdmin(Admin admin);
    List<Admin> queryAllAdmins();
    int deleteAdmins(String id);
<<<<<<< HEAD
    int deleteUser(String id);

<<<<<<< HEAD
=======
>>>>>>> be2e47a
    int changePwd(AdminPwd pwd);

    List<Admin> getSearchAdmins(Admin admin);

    List<Message> getNoReplyMsg() throws SQLException;
=======
    int changePwd(PWD admin) throws SQLException;
>>>>>>> origin/master
}
