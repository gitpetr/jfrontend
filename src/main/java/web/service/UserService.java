package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    User getUser(String username);

    void deleteUser(Long id);

    User saveUser(User user);
}
