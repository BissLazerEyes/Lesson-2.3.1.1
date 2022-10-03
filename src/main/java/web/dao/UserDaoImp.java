package web.dao;

import org.springframework.stereotype.Repository;
import web.Service.UserService;
import web.model.User;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
    private UserService userService;

    public UserDaoImp(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void saveUser(User user) {
        userService.saveUser(user);
    }

    @Override
    public void updateUser(User updateUser) {
        userService.updateUser(updateUser);
    }

    @Override
    public void removeUserById(int id) {
        userService.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Override
    public User getUserById(int id) {
        return userService.getUserById(id);
    }
}