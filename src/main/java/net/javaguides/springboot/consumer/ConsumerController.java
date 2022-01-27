package net.javaguides.springboot.consumer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.javaguides.springboot.User;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/consumer")
@RequiredArgsConstructor
@Slf4j
public class ConsumerController {
    private final RestTemplate restTemplate;

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUser() {
        log.info("ConsumerController class getAllUser method");
        // exchange method
//        ResponseEntity<User[]> userArray = restTemplate.exchange("http://localhost:8081/user", HttpMethod.GET, null, User[].class);

        // getForEntity method
//        ResponseEntity<User[]> userArray = restTemplate.getForEntity("http://localhost:8081/user", User[].class);
//        var users = userArray.getBody();

        // getForObject method
        User[] users = restTemplate.getForObject("http://localhost:8081/user", User[].class);

        assert users != null;
        return new ResponseEntity<>(Arrays.asList(users), HttpStatus.OK);
    }

    /* get single user object by id */
    @GetMapping("{id}")
    public ResponseEntity<User> getById(
            @PathVariable(value = "id", required = true) int id
    ) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        // exchange method
//        return restTemplate.exchange("http://localhost:8081/user/{id}", HttpMethod.GET, null, User.class, params);

        // getForEntity method
        return restTemplate.getForEntity("http://localhost:8081/user/{id}", User.class, params);
    }

    @PostMapping("")
    public ResponseEntity<User> postUser(@RequestBody User user) {
        HttpEntity<User> entity = new HttpEntity<>(user);

//        return restTemplate.exchange("http://localhost:8081/user", HttpMethod.POST, entity, User.class);

        return restTemplate.postForEntity("http://localhost:8081/user",entity, User.class);
    }
}
