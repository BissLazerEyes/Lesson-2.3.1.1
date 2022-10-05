package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);

    void updateUser(User updateUser);

    void removeUserById(int id);

    List<User> getAllUsers();

    User getUserById(int id);

}