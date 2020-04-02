package Service;

import Bean.Admin;
import Bean.User;
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
}
