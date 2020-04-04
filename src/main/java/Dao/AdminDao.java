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
    int deleteAdmins(String id);
    int changePwd(AdminPwd pwd);

    List<Admin> getSearchAdmins(Admin admin);

    List<Message> getNoReplyMsg() throws SQLException;
}
