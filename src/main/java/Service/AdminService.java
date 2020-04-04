package Service;

import Bean.Admin;
import Bean.AdminPwd;
import Bean.Message;
import Bean.User;

import java.sql.SQLException;
import java.util.List;

public interface AdminService {
    Admin login(Admin admin);
    int addAdmin(Admin admin);
    List<Admin> queryAllAdmins();
    int deletAdmins(String id);

    List<Admin> getSearchAdmins(Admin admin);

    int changePwd(AdminPwd pwd);

    List<Message> getNoReplyMsg() throws SQLException;
}
