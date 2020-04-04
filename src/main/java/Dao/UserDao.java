package Dao;

import Bean.User;

import java.util.List;

public interface UserDao {
    int deleteUser(String uid);

    List<User> queryAllUsers();

    List<User> searchUser(String word);

    User queryUserById(Integer userId);

    User queryUserNameById(Integer userId);
}
