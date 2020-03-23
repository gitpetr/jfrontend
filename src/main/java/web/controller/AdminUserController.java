package web.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminUserController {
    UserService userService;
    private String API_URL = "http://localhost:8081/api/v1/admin";
    Gson gson = new Gson();

    @Autowired
    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String listUsers() {
        return "admin-list-users";
    }

    @ResponseBody
    @GetMapping("/user/all")
    public List<User> userAll() {
        return userService.getUsers();
    }

    @ResponseBody
    @GetMapping("/user/{username}")
    public User getUser(@PathVariable("username") String username) {
        return userService.getUser(username);
    }

    @GetMapping("/user/delete/{userId}")
    public void deleteUser(@PathVariable("userId") long id) {
        userService.deleteUser(API_URL + "/delete/" + id);
    }

    @ResponseBody
    @PostMapping("/saveUser/")
    public User saveUser(@ModelAttribute("user") User user) {
        final String savedUser = userService.saveUser(user, API_URL + "/saveUser");
        return gson.fromJson(savedUser, User.class);
    }
}
