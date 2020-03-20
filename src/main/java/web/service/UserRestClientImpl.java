package web.service;

import com.google.gson.Gson;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import web.model.User;

import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;

@Service
public class UserRestClientImpl implements UserRestClient {
    Gson gson = new Gson();

    public String getUsers(String url) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(url , HttpMethod.GET, new HttpEntity<String>(authenticate()), String.class);
        return response.getBody();
    }

    public void deleteUser(String url) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(url , HttpMethod.GET, new HttpEntity<String>(authenticate()), String.class);
    }

    public String saveUser(User user, String url) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<User> entity = new HttpEntity<>(user, authenticate());
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        return response.getBody();
    }


    public static void main(String[] args) {
        final UserRestClientImpl userRestClient = new UserRestClientImpl();
        System.out.println(userRestClient.getUsers("http://localhost:8081/api/v1/admin/user"));
    }

    private HttpHeaders authenticate() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        String authString =  "ADMIN:ADMIN";
        String authStringEnc = Base64.getEncoder().encodeToString(authString.getBytes());
        headers.set("Authorization", "Basic " + authStringEnc);
        return headers;
    }
}
