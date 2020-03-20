package web.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import web.model.User;

import java.util.Arrays;
import java.util.Base64;

@Service
public class UserRestClientImpl implements UserRestClient {

    @Override
    public String getUsers(String url) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(url , HttpMethod.GET, new HttpEntity<String>(authenticate()), String.class);
        return response.getBody();
    }

    @Override
    public void deleteUser(String url) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(url , HttpMethod.GET, new HttpEntity<String>(authenticate()), String.class);
    }

    @Override
    public String saveUser(User user, String url) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<User> entity = new HttpEntity<>(user, authenticate());
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        return response.getBody();
    }

    private HttpHeaders authenticate() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String authString =  "ADMIN:ADMIN";
        String authStringEnc = Base64.getEncoder().encodeToString(authString.getBytes());
        headers.set("Authorization", "Basic " + authStringEnc);
        return headers;
    }
}
