package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminUserController {
    private UserService userService;

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
        userService.deleteUser(id);
    }

    @ResponseBody
    @PostMapping("/saveUser/")
    public User saveUser(@ModelAttribute("user") User user) {
        return userService.saveUser(user);
    }
}
