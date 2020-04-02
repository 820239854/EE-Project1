package Dao;

import Bean.Admin;
import Bean.User;

import java.util.List;

public interface AdminDao {
    Admin login(Admin admin);
    int addAdmin(Admin admin);
    List<Admin> queryAllAdmins();
    List<User> queryAllUsers();
    int deleteAdmins(String id);
    int deleteUser(String id);
}
