package web.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserRestClient;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminUserController {
    UserRestClient userRestClient;
    private String API_URL = "http://localhost:8081/api/v1/admin";

    @Autowired
    public AdminUserController(UserRestClient userRestClient) {
        this.userRestClient = userRestClient;
    }

    Gson gson = new Gson();

    @GetMapping("/user")
    public String listUsers() {
        return "admin-list-users";
    }

    @ResponseBody
    @GetMapping("/user/all")
    public List<User> userAll() {
        final String users = userRestClient.getUsers(API_URL + "/user");
        final List<User> fromJson = gson.fromJson(users,  List.class);
        return fromJson;
    }

    @GetMapping("/user/delete/{userId}")
    public void deleteUser(@PathVariable("userId") long id) {
        userRestClient.deleteUser(API_URL + "/delete/" + id);
    }

    @ResponseBody
    @PostMapping("/saveUser/")
    public User saveUser(@ModelAttribute("user") User user) {
        final String savedUser = userRestClient.saveUser(user, API_URL + "/saveUser");
        return gson.fromJson(savedUser, User.class);
    }
}
