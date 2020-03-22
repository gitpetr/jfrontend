package web.service;

import web.model.User;

public interface UserService {
    public String getUsers(String url);

    public void deleteUser(String url);

    public String saveUser(User user, String url);
}
