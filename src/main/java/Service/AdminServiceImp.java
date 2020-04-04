package Service;

import Bean.Admin;
import Bean.AdminPwd;
import Bean.Message;
import Bean.User;
import Dao.AdminDao;
import Dao.AdminDaoImp;

import java.sql.SQLException;
import java.util.List;

public class AdminServiceImp implements AdminService{
    private AdminDao adminDao = new AdminDaoImp();


    @Override
    public List<Admin> getSearchAdmins(Admin admin) {
        return adminDao.getSearchAdmins(admin);
    }


    @Override
    public int changePwd(AdminPwd pwd) {
        return adminDao.changePwd(pwd);
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
    public int deletAdmins(String id) {
        return adminDao.deleteAdmins(id);
    }


    @Override
    public List<Message> getNoReplyMsg() throws SQLException {
        return adminDao.getNoReplyMsg();
    }
}
