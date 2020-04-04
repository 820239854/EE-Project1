package Dao;

import Bean.Admin;
import Bean.AdminPwd;
import Bean.Message;
import Bean.User;

import java.sql.SQLException;
import java.util.List;

public interface AdminDao {
    Admin login(Admin admin);
    int addAdmin(Admin admin);
    List<Admin> queryAllAdmins();
    List<User> queryAllUsers();
    int deleteAdmins(String id);
    int deleteUser(String id);

    int changePwd(AdminPwd pwd);

    List<User> getSearchUser(String word);
    List<Admin> getSearchAdmins(Admin admin);

    List<Message> getNoReplyMsg() throws SQLException;
}
