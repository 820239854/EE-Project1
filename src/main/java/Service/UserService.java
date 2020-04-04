package Service;

import Bean.User;

import java.util.List;

public interface UserService {
    List<User> queryAllAUsers();
    int deleteUser(String id);
    List<User> searchUser(String word);
}
