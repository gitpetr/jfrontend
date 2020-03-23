package web.service;

import web.model.User;

public interface UserService {
    String getUsers(String url);

    User getUser(String username);

    void deleteUser(String url);

    String saveUser(User user, String url);
}
