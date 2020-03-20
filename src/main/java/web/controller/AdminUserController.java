package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserRestClientImpl;

@Controller
@RequestMapping("/admin")
public class AdminUserController {
    UserRestClientImpl userRestClient;
    private String SERVER_URL = "http://localhost:8081/api/v1/admin";

    @Autowired
    public AdminUserController(UserRestClientImpl userRestClient) {
        this.userRestClient = userRestClient;
    }

    @GetMapping("/user")
    public String listUsers() {
        return "admin-list-users";
    }

    @ResponseBody
    @GetMapping("/user/all")
    public String userAll() {
        return userRestClient.getUsers(SERVER_URL + "/user");
    }

    @GetMapping("/user/delete/{userId}")
    public void deleteUser(@PathVariable("userId") long id) {
        userRestClient.deleteUser(SERVER_URL + "/delete/" + id);
    }

    @ResponseBody
    @PostMapping("/saveUser/")
    public String saveUser(@ModelAttribute("user") User user) {
        return userRestClient.saveUser(user, SERVER_URL + "/saveUser");
    }
}
