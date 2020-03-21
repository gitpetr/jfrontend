package web.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import web.model.User;

import java.nio.charset.Charset;
import java.util.Arrays;

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
        headers.setAccept(Arrays.asList(new MediaType("application", "json", Charset.forName("UTF-8"))));
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        headers.setAcceptCharset(Arrays.asList(Charset.forName("UTF-8")));
        headers.setBasicAuth("ADMIN", "ADMIN");
        return headers;
    }
}
