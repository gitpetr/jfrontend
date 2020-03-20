package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserRestClient;

@Controller
@RequestMapping("/admin")
public class AdminUserController {
    UserRestClient userRestClient;
    private String API_URL = "http://localhost:8081/api/v1/admin";

    @Autowired
    public AdminUserController(UserRestClient userRestClient) {
        this.userRestClient = userRestClient;
    }

    @GetMapping("/user")
    public String listUsers() {
        return "admin-list-users";
    }

    @ResponseBody
    @GetMapping("/user/all")
    public String userAll() {
        return userRestClient.getUsers(API_URL + "/user");
    }

    @GetMapping("/user/delete/{userId}")
    public void deleteUser(@PathVariable("userId") long id) {
        userRestClient.deleteUser(API_URL + "/delete/" + id);
    }

    @ResponseBody
    @PostMapping("/saveUser/")
    public String saveUser(@ModelAttribute("user") User user) {
        return userRestClient.saveUser(user, API_URL + "/saveUser");
    }
}
