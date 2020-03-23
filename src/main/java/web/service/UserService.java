package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    User getUser(String username);

    void deleteUser(String url);

    String saveUser(User user, String url);
}
