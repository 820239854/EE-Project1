package Dao;

import Bean.Admin;
import Bean.PWD;
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

    int changePwd(PWD admin) throws SQLException;
}
