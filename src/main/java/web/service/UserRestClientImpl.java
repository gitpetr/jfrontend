package web.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import web.model.User;

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
        headers.setBasicAuth("ADMIN", "ADMIN");
        return headers;
    }
}
