package web.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private RestTemplate restTemplate;
    private String USER_URL = "http://localhost:8081/api/v1/admin/user/";

    public UserServiceImpl(RestTemplateBuilder builder) {
        this.restTemplate = builder.basicAuthentication("ADMIN", "ADMIN").build();
    }

    @Override
    public List<User> getUsers() {
        return restTemplate.getForObject(USER_URL, List.class);
    }

    @Override
    public User getUser(String username) {
        return restTemplate.getForObject(USER_URL + username, User.class);
    }

    @Override
    public void deleteUser(Long id) {
        restTemplate.getForObject(USER_URL + "/delete/" + id, String.class);
    }

    @Override
    public User saveUser(User user) {
        return restTemplate.postForObject(USER_URL +"/saveUser", user, User.class);
    }
}
