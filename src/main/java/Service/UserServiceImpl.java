package Service;

import Bean.User;
import Dao.UserDao;
import Dao.UserDaoImpl;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();
    @Override
    public List<User> queryAllAUsers() {
        return userDao.queryAllUsers();
    }

    @Override
    public List<User> searchUser(String word) {
        return userDao.searchUser(word);
    }

    @Override
    public int deleteUser(String id) {
        return userDao.deleteUser(id);
    }
}
