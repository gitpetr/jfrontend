package web.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;

@Service
public class RemoteQuery {
    static final String URI = "http://localhost:8081/api/v1/admin/user/";

    public String getUser(String name) {

        // HttpHeaders
        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        // Request to return JSON format
        headers.setContentType(MediaType.APPLICATION_JSON);
        String authString =  "ADMIN:ADMIN";
        String authStringEnc = Base64.getEncoder().encodeToString(authString.getBytes());

        headers.set("Authorization", "Basic " + authStringEnc);
        // HttpEntity<String>: To get result as String.
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        // RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Send request with GET method, and Headers.
        ResponseEntity<String> response = restTemplate.exchange(URI + name, //
                HttpMethod.GET, entity, String.class);

        return response.getBody();
    }
}
