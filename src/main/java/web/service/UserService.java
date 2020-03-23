package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    User getUser(String username);

    void deleteUser(String url);

    User saveUser(User user);
}
