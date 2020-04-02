package Service;

import Bean.Admin;
import Bean.User;
import Dao.AdminDao;
import Dao.AdminDaoImp;

import java.util.List;

public class AdminServiceImp implements AdminService{
    private AdminDao adminDao = new AdminDaoImp();

    @Override
    public List<User> getSearchUser(String word) {
        return null;
    }

    @Override
    public List<Admin> getSearchAdmins(Admin admin) {
        return null;
    }

    @Override
    public List<User> queryAllAUsers() {
        return null;
    }

    @Override
    public Admin login(Admin admin) {
        return adminDao.login(admin);
    }

    @Override
    public int addAdmin(Admin admin) {
        return adminDao.addAdmin(admin);
    }

    @Override
    public List<Admin> queryAllAdmins() {
        return adminDao.queryAllAdmins();
    }

    @Override
    public List<User> queryAllUsers() {
        return adminDao.queryAllUsers();
    }

    @Override
    public int deletAdmins(String id) {
        return adminDao.deleteAdmins(id);
    }

    @Override
    public int deleteUser(String id) {
        return adminDao.deleteUser(id);
    }
}
